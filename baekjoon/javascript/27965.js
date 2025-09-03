/**
 * N결수
 * 실버3
 * N결수의 나머지를 구하고, 그 나머지를 N + 1 앞에 붙여
 * 나머지를 구하면 그게 N+1결수의 나머지
 */

const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on("line", (line) => {
	input.push(line.trim());
}).on("close", () => {
	const [N, K] = input[0].split(" ").map((i) => BigInt(i));
	let remainder = `${BigInt(1) % K}`;
	for (let i = 2; i <= N; i++) {
		remainder = `${BigInt(`${remainder}` + i) % K}`;
	}
	console.log(remainder);
});



//const input = [];
//rl.on("line", (line) => {
//  input.push(line.trim());
//}).on("close", () => {
//  const [N, K] = input[0].split(" ").map((i) => parseInt(i));
//  let remainder = 0;
//  for (let i = 1; i <= N; i++) {
//    remainder = (remainder * Math.pow(10, i.toString().length) + i) % K;
//  }
//  console.log(remainder);
//});


//백준에서는 question을 쓰면 안된다.

//rl.question("", (answer) => {
//  rl.close();
//  const [N, K] = answer.split(" ").map((i) => parseInt(i));

//  let remainder = `${1 % K}`;
//  for (let i = 2; i <= N; i++) {
//    remainder = `${parseInt(remainder + i) % K}`;
//  }
//  console.log(remainder);
//});
