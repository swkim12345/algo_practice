package 못푼문제들;
/*
 * 2110 공유기 설치
 * 
 * 공유기간의 거리가 최대가 되게 만드는 곳에 설치하는 문제. 설치시 집에만 설치 가능(node)
 * 1. dp, greedy, 완전탐색?
 * dp -> ...거리를 어케 최적화 하는데... f꼴로 나오지 않음.
 * 2. greedy - 최대 거리를 나타내는 데에는 지금까지의 값 뿐만 아니라 다음 값도 필요함.
 * 3. 완전탐색 - NC? -> 최대 경우의 수는 200000C100000 -> 대략 조가 넘어감. 완탐도 아님.
 * 따라서, 아예 다른 방법으로 풀어야 함. 이분탐색?
 * 
 * 막히는 부분 - 이분탐색으로 구할 수 있는 것은 - 특정 숫자를 빠르게 구하는 것임.
 * 특정 숫자? -> 배분이 가장 이상적으로 되는 케이스는 각 공유기간의 거리가 모두 일정하게 떨어져 있는 것임.
 * 다만, 집 위에만 놓을 수 있으므로, 이를 최적화하기 위해서는 반올림을 해 집에 할당하는 것임.
 * 하지만, 이게 최적의 답이라는 것을 보증할 수 없음. 집이 한쪽에 몰려있을 수 있기 때문임.
 * 
 * 공유기 1개 - 2개까지는 최적의 답이 정해짐.
 * 
 * 답
 * 찾는 포인트가 "집"이 아니라 "거리"다. 거리를 바탕으로 집을 추정하면 되는 문제임.
 * 거리를 찾을 때, 최소, 최대를 설정한 다음, 이를 바탕으로 이분탐색을 돌리면 되는 문제.
 * lowerbound를 바탕으로 푸는 문제임.
 * 
 * 힌트
 * 이분 탐색은 맞으나, 매개 변수 탐색 키워드
 */

public class j_2110 {
    
}
