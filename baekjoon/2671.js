/**
 * 잠수함 식별
 * 이건.... 정규표현식임.
 * https://regex101.com
 */

const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const lines = [];

rl.on("line", (line) => {
  lines.push(line);
});
rl.on("close", () => {
  const regex = /^((100+1+)|01)+$/gy;
  const replaced = lines[0].replaceAll(regex, "");
  
  if (replaced.length > 0) {
    console.log("NOISE");
  } else {
    console.log("SUBMARINE");
  }
});
