const fs = require("fs");
//let inp = fs.readFileSync("input.txt").toString().trim().split(" ").map(Number);
let inp = fs.readFileSync("/dev/stdin").toString().trim().split(" ").map(Number);
let result = 0;
for(let i = 1;i<=inp[0];i++){
    var tns = 1;
    for(let j = 0;j<i.toString().length;j++){
        tns *= 10;
    }
    result = (result * tns % inp[1]+i);
}
result%=inp[1];
console.log(result);
