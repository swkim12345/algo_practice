/**
 * 트리 순회
 * 이게 preorder, inorder, postorder이다.
 * 출력 순서가 정해진 게 시각적으로 보인다.
 */

const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const lines = [];
const tree = {};

const preOrder = (head) => {
  process.stdout.write(head);
  const [left, right] = tree[head];
  if (left !== ".") preOrder(left);
  if (right !== ".") preOrder(right);
};

const inOrder = (head) => {
  const [left, right] = tree[head];
  if (left !== ".") inOrder(left);
  process.stdout.write(head);
  if (right !== ".") inOrder(right);
};

const postOrder = (head) => {
  const [left, right] = tree[head];
  if (left !== ".") postOrder(left);
  if (right !== ".") postOrder(right);
  process.stdout.write(head);
};

rl.on("line", (line) => {
  lines.push(line);
});

rl.on("close", () => {
  parseInt(lines.shift());

  lines.map((line) => {
    const [head, left, right] = line.split(" ");
    tree[head] = [left, right];
  });
  preOrder("A");
  console.log("");
  inOrder("A");
  console.log("");
  postOrder("A");
});
