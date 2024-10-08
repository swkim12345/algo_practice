/**
 * 금광 - 골드 3
 * 두 방 사이의 거리는 홀수여야 함.
 * 1.1번을 선택함
 * 2. 1번을 선택할 때 나머지 방과 홀수 거리여야 함.
 * 홀수 + 홀수 => 짝수이다 => 따라서 모든 기업이 가질 수 있는 최대의 금광 개수는 2이다.
 * 1 또는 2인것을 검증할 때, 다음과 같이 작동한다.
 * 1은 문제 없음
 * 2일 때는 크게 부모 - 자식, 자식 - 자식, 부모 - 손자, 손자 - 자식
 * 	부모 - 자식 -> 문제 없음
 *  자식 - 자식 -> 같은 부모를 둔다면 짝수 거리 -> 문제 발생
 *  손자 - 자식 -> 홀수 거리, 문제 없음.
 *  이런 방식이면 경우의 수가 무한해짐
 * 
 */
