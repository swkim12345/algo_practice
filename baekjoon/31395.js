/**
 * 정렬된 연속한 부분수열의 개수
 * 1. 그냥 구현하고, 시작점에서 끝까지 가면서 체크하는 방식은 시간초과가 났다. -> O(N ** 2)라서
 * 2. 구현할 때이동도 동시에 하면서 더하는 연산을 진행하면 ->  O(N);
 */

const readline = require("node:readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let lines = [];
let result = 0;
const solves = () => {
  //자기 자신도 포함해 계산하므로 먼저 더해줌.
  result = lines.length;
  for (let i = 0; i < lines.length; i++) {
    let tmp = 0;
    let j = 0;
    //j라는 변수를 통해 값을 이동시킴.
    //i + j가 끝값보다 하나 작을 때 까지 이동시켜가면서 체크를 진행함.
    for (j = 0; i + j < lines.length - 1; j++) {
      //만약 다음 값이 같다면 추가해줌.
      //+ 2가 아니다.
      if (lines[i + j] <= lines[i + j + 1]) tmp += j + 1;
      else break;
    }
    //만약 끝까지 계속 증가한다면 안쪽에서 잡지 못하는 문제가 존재함.
    //이미 검사가 진행된 범위까지 이동시킴.
    i += j;
    //이미 다 더해진 값을 더해줌.
    result += tmp;
  }
};

rl.on("line", (line) => {
  if (line.toLowerCase() === "exit") rl.close;
  else lines.push(line);
});

rl.on("close", () => {
  lines = lines[1].split(" ").map((a) => {
    return parseInt(a);
  });
  solves();
  console.log(result);
});
