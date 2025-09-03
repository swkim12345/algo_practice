/**
 * 1
 * BigInt써서 풀기
 */

const readline = require("readline");

const lines = [];
readline
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    lines.push(BigInt(line));
  })
  .on("close", () => {
    lines.map((line) => {
      let ones = "";
      while (true) {
        ones = ones + "1";
        let divider = BigInt(ones);
        if (divider % line === 0n) {
          console.log(ones.length);
          break;
        }
      }
    });
  });
