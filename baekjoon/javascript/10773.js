/**
 * 제로
 */
const readline = require("node:readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const lines = [];

rl.on("line", (line) => {
  lines.push(parseInt(line));
});

rl.on("close", () => {
  const testCase = lines.shift();
  const result = [];
  lines.map((l) => {
    if (l === 0) result.pop();
    else result.push(l);
  });
  console.log(
    result.reduce((acc, curr) => {
      acc += curr;
      return acc;
    }, 0)
  );
});
