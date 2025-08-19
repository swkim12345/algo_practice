#!/bin/bash
set -euo pipefail

# ===== helpers =====
log() { echo "[$(date +%H:%M:%S)] $*"; }
die() { echo "❌ $*" >&2; exit 1; }

BASE_DIR="baekjoon/java"
PATTERN='j_[0-9]+\.java$'
REMOTE="origin"

# ===== sanity checks =====
git rev-parse --is-inside-work-tree >/dev/null 2>&1 || die "여기는 Git 저장소가 아닙니다."
[ -d "$BASE_DIR" ] || die "디렉터리가 없습니다: $BASE_DIR"

BRANCH="$(git rev-parse --abbrev-ref HEAD)"
git remote get-url "$REMOTE" >/dev/null 2>&1 || die "원격 '$REMOTE' 이(가) 없습니다."

# ===== update local (rebase + autostash) =====
log "원격 최신화: git pull --rebase --autostash $REMOTE $BRANCH"
git fetch "$REMOTE" "$BRANCH"
git pull --rebase --autostash "$REMOTE" "$BRANCH"

# ===== collect files =====
# 새 파일(미추적)
NEW_LIST="$(git ls-files --others --exclude-standard "$BASE_DIR" | grep -E "$PATTERN" || true)"

# 변경된 파일(추적 중, 인덱스/워킹트리)
MOD_UNSTAGED="$(git diff --name-only -- "$BASE_DIR" | grep -E "$PATTERN" || true)"
MOD_STAGED="$(git diff --name-only --cached -- "$BASE_DIR" | grep -E "$PATTERN" || true)"
MOD_LIST="$(printf "%s\n%s\n" "$MOD_UNSTAGED" "$MOD_STAGED" | sed '/^$/d' | sort -u || true)"

ANY_COMMIT=false

extract_num() {
  # 경로에서 j_<숫자>.java 숫자만 추출
  echo "$1" | sed -n 's#^.*/j_\([0-9][0-9]*\)\.java$#\1#p'
}

commit_file() {
  local file="$1"
  local msg="$2"
  log "add: $file"
  git add "$file"
  log "commit: $msg"
  # 같은 메시지로 이미 커밋되어 있거나 변경이 없으면 실패할 수 있으니 || true
  git commit -m "$msg" || true
  ANY_COMMIT=true
}

# ===== commit new files: "<번호> 추가" =====
if [ -n "$NEW_LIST" ]; then
  echo "$NEW_LIST" | while IFS= read -r f; do
    [ -z "$f" ] && continue
    num="$(extract_num "$f")"
    [ -z "$num" ] && { echo "⚠️  패턴 불일치(건너뜀): $f"; continue; }
    commit_file "$f" "${num} 추가"
  done
fi

# ===== commit modified files: "<번호> 업데이트" =====
# 새 파일과 중복되는 항목 제외
if [ -n "$MOD_LIST" ]; then
  # 집합 차집합: MOD_LIST - NEW_LIST
  FILTERED_MOD="$(comm -23 <(printf "%s\n" $MOD_LIST | sort -u) <(printf "%s\n" $NEW_LIST | sort -u) || true)"
  if [ -n "$FILTERED_MOD" ]; then
    echo "$FILTERED_MOD" | while IFS= read -r f; do
      [ -z "$f" ] && continue
      num="$(extract_num "$f")"
      [ -z "$num" ] && { echo "⚠️  패턴 불일치(건너뜀): $f"; continue; }
      commit_file "$f" "${num} 업데이트"
    done
  fi
fi

# ===== push =====
if [ "$ANY_COMMIT" = true ]; then
  log "push: $REMOTE $BRANCH"
  git push "$REMOTE" "$BRANCH"
  echo "✅ 완료: 새 파일은 '추가', 변경 파일은 '업데이트'로 커밋 후 푸시했습니다."
else
  echo "ℹ️  커밋할 변경이 없습니다. 푸시는 생략합니다."
fi
