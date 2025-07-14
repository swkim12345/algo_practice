//fs(Filesystem) 을 바탕으로 여러줄을 읽기
//다만, EOF가 필요해서 혹시나...라는 생각이 있긴 함.
//const fs = require('fs');

//let input = '';

//process.stdin.on('data', (chunk) => {
//	input += chunk;
//});

//process.stdin.on('end', () => {
//	const inputLines = input.toString().split('\n');
//	console.log(inputLines);
//});

//readline을 이용해 래핑해 사용하는 방법
const readline = require('node:readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const lines = [];
const solve = (P, partition) => {
	const set = new Set();
	for (let i = 0; i < partition.length; i++)
	{
		for (let j = i + 1; j < partition.length; j++) {
			set.add(partition[j] - partition[i]);
		}
	}
	return [...set].sort((a, b) => a - b);
}
rl.on('line', (line) => {
  if (line.toLowerCase() === 'exit') {
	rl.close();
  } else {
	lines.push(line);
  }
});

rl.on('close', () => {
	const [W, P] = lines[0].split(" ").map(x => parseInt(x));
	const tmp = [...lines[1].split(" ")];
	const partition = [0, ...tmp.map(x => parseInt(x)), W];
	const solution = solve(P, partition);
	let output = "";
	solution.forEach((s) => {
		output += `${s} `
	})
	console.log(output);
});
