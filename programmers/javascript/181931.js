/*
    등차수열의 특정한 항만 더하기
    https://school.programmers.co.kr/learn/courses/30/lessons/181931
*/

function solution(a, d, included) {
    let answer = 0;
    
    included.forEach((element, i) => {
        if (element === true) {
            answer += a + d * i;
        }
    })
    return answer;
}