/**
 * 유기농 배추
 */

const readline = require("node:readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const lines = [];

const solve = () => {
  
}

rl.on("line", (line) => {
  //bfs를 통해 찾는 과정을 거침
  lines.push(line);
});

rl.on("close", () => {
	const testCase = lines[0];

});
