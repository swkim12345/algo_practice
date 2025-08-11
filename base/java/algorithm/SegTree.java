package base.java.algorithm;
/*
 * 세그먼트 트리
 * 여러 개의 데이터가 연속적으로 존재할 때 특정 범위의 데이터의 합을 구하는 방법
 * 데이터의 합을 가장 빠르고 간단하게 구할 수 있는 자료구조
 * 
 * 합을 구하는 방법
 * 1. 단순 구현 - 각 값을 그때그때 합쳐주는 것 - 쿼리마다 O(N)
 * 2. 구간 합 - 값을 하나씩 합쳐준 다음(누적 합), idx를 기준으로 a - b -> O(1) 시간복잡도
 * 하지만, 구간합의 경우 갱신이 들어올 때(idx값 변경) O(N)의 시간복잡도가 걸리게 된다.
 * 
 * 추가로 쿼리가 들어와서 값을 갱신할 경우
 * 구간합을 이용한 풀이로는 풀리지 않는다.
 * 따라서, 쿼리가 들어올 때 업데이트해줄 수 있는 자료구조가 필요한데, 그게 세그먼트 트리이다.
 * 자료구조
 * 1. segtree를 저장할 배열, 들어온 값을 저장할 배열
 * 1-1. segtree 크기 : N개보다 큰 가장 가까운 N의 제곱수의 2배, 기본적으론 4 * N 만큼의 사이즈를 할당해주면 됨.
 * 1-2. segtree 구조 : 1부터 시작, 1번에 모든 노드의 합, 2번 - 4 5 노드의 합, 3 - 6 7 번 노드의 합....
 * 
 * build
 * 재귀를 이용해 세그먼트 트리를 빌드한다.
 * build할 때 원 배열에 접근해서 빌드한 다음, 이후에는 원 배열에 접근하지 않는다.
 * 
 * sum
 * 
 * update
 * 
 * 주의점
 * 기본적으론 찾을 때 기본 배열의 범위를 가지고 찾되, 이분탐색으로 찾게 된다.
 * 구간합의 특징은 
 * 
 * 인덱스 번호는 1부터 시작하게 된다.
 * 
 * https://m.blog.naver.com/ndb796/221282210534
 */
public class SegTree {
    static int[] tree;
    static int[] arr;
    
    // node - segtree 의 인덱스값
    static int build(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;

        return tree[node] = build(start, mid, node * 2) + build(mid + 1, end, node * 2 + 1);
    }

    // start : 기본 배열의 시작 인덱스, end : 기본 배열의 끝 인덱스

    // node : 세그먼트 트리의 인덱스 값
    // left, right : 기본 배열에서 구간합을 구하고자 하는 범위
    static int sum(int start, int end, int node, int left, int right) {
        // 종료조건 : 만약 범위 밖에 있는 경우
        if (left > end || right < start) return 0;
        // end <= left && right <= start

        // 범위 안에 있는 경우 - 바로 리턴
        if (left <= start && end <= right) return tree[node];

        // start <= right < end || start < left <= end; 
        // 남은 조건 - 세그먼트 트리를 분할하며 합을 찾음
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    // start : 기본 배열의 시작 인덱스, end : 기본 배열의 끝 인덱스
    // index : 구간 합을 수정하고자 하는 노드 (기본 배열의 인덱스 값)

    // node : 세그먼트 트리의 idx 값
    // diff : 수정하고자 하는 값의 차이(수정값 - 원본)
    static void update(int start, int end, int node, int index, int diff) {
        // 구간 합을 수정하고자 하는 노드가 세그트리의 인덱스에 들어가있지 않은 경우
        if (index < start || index > end) return;

        // 범위 안에 있으면 구간 합 노드, 리프 노드 가릴 것 없이 갱신
        tree[node] += diff;
        if (start == end) return ; // 리프 노드일 때 업데이트 이후 리턴
        int mid = (start + end) / 2;

        update(start, mid, node * 2, index, diff); // 자식 노드 찾기
        update(mid + 1, end, node * 2 + 1, index, diff);
    }

    public static void main(String[] args) {
        arr = new int[]{1, 9, 3, 8, 4, 5, 5, 9, 10, 3, 4, 5};
        tree = new int[arr.length * 4]; // size는 러프하게 4를 곱해서 처리

        // end 는 배열의 사이즈가 아님!
        build(0, arr.length - 1, 1);
        
        // left, right 만큼
        // 1, 2, 3번 값은 고정
        System.out.println(sum(0, arr.length - 1, 1, 0, arr.length - 1));

        // 3 ~ 8까지 합을 구해서 출력
        System.out.println(sum(0, arr.length - 1, 1, 3, 8));

        // 5번 인덱스의 값을 -5만큼 수정 (diff값임!!!)
        update(0, arr.length  -1, 1, 5, -5);

        System.out.println(sum(0, arr.length - 1, 1, 3, 8));
    }
}
