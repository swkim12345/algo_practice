/**
 * 루트
 */

const rl = require("readline").createInterface(process.stdin, process.stdout);

const lines = [];

const diff = (a, b) => {
  return Math.abs(a - b);
};

rl.on("line", (line) => {
  if (line.split(" ")[0] === "0") rl.close();
  lines.push(line);
});
rl.on("close", () => {
  lines.forEach((line) => {
    const [B, N] = line.split(" ");
    let after = null;
    let i = 1;
    for (i = 1; ; i++) {
      after = Math.pow(i, N);
      if (after >= B) break;
    }
    let before = Math.pow(i - 1, N);
    if (diff(before, B) < diff(after, B)) console.log(i - 1);
    else console.log(i);
  });
});
