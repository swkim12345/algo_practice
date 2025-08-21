# ============================
# baekjoon_auto_commit.ps1
# New file  -> "<number> add"
# Modified  -> "<number> update"
# Auto pull (rebase + autostash), then push if any commit
# ============================

$ErrorActionPreference = "Stop"

function Write-Log {
    param([string]$Message)
    Write-Host "[$(Get-Date -Format HH:mm:ss)] $Message"
}

function Stop-Error {
    param([string]$Message)
    Write-Host "ERROR: $Message" -ForegroundColor Red
    exit 1
}

# Settings
$BaseDir = "baekjoon/java"
$Pattern = "j_\d+\.java$"
$Remote  = "origin"

# Validate Git repo
try { git rev-parse --is-inside-work-tree | Out-Null }
catch { Stop-Error "Not inside a Git repository." }

if (-not (Test-Path $BaseDir)) {
    Stop-Error "Directory does not exist: $BaseDir"
}

$Branch = git rev-parse --abbrev-ref HEAD
try { git remote get-url $Remote | Out-Null }
catch { Stop-Error "Remote '$Remote' does not exist." }

# Update local repo
Write-Log "Updating local branch: git pull --rebase --autostash $Remote $Branch"
git fetch $Remote $Branch
git pull --rebase --autostash $Remote $Branch

# Collect files
$NewList = git ls-files --others --exclude-standard $BaseDir | Where-Object { $_ -match $Pattern }

$ModList = @()
$ModList += git diff --name-only -- $BaseDir | Where-Object { $_ -match $Pattern }
$ModList += git diff --name-only --cached -- $BaseDir | Where-Object { $_ -match $Pattern }
$ModList  = $ModList | Sort-Object -Unique

$script:AnyCommit = $false

function Get-ProblemNumber {
    param([string]$Path)
    if ($Path -match "j_(\w+)\.java$") { return $matches[1] }
    return $null
}

function Invoke-CommitFile {
    param(
        [string]$Path,
        [string]$Message
    )
    Write-Log "Adding: $Path"
    git add -- $Path
    if (-not (git diff --cached --name-only -- $Path)) {
        Write-Log "No staged changes for: $Path (skipped commit)"
        return
    }
    Write-Log "Committing: $Message"
    git commit -m $Message | Out-Null
    $script:AnyCommit = $true
}

# Commit new files
foreach ($f in $NewList) {
    $num = Get-ProblemNumber -Path $f
    if ($null -eq $num) {
        Write-Host "WARNING: Pattern mismatch (skipped): $f"
        continue
    }
    Invoke-CommitFile -Path $f -Message "$num add"
}

# Commit modified files (excluding new)
$FilteredMod = $ModList | Where-Object { $NewList -notcontains $_ }
foreach ($f in $FilteredMod) {
    $num = Get-ProblemNumber -Path $f
    if ($null -eq $num) {
        Write-Host "WARNING: Pattern mismatch (skipped): $f"
        continue
    }
    Invoke-CommitFile -Path $f -Message "$num update"
}

# Push
if ($script:AnyCommit) {
    Write-Log "Pushing to: $Remote $Branch"
    git push $Remote $Branch
    Write-Host "Done: New files committed as 'add', modified files committed as 'update'." -ForegroundColor Green
} else {
    Write-Host "No changes to commit. Push skipped."
}
