const rl = require("readline").createInterface(process.stdin, process.stdout);

let tmp = null;
rl.on("line", (line) => {
  tmp = parseInt(line);
}).on("close", () => {
  let stars = "*";
  for (let i = tmp; i > 0; i--) {
    let print = "";
    for (let j = 1; j < i; j++) {
      print += " ";
    }
    console.log(print + stars);
    stars += "**";
  }
});
