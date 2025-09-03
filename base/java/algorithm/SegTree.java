package base.java.algorithm;
/*
 * 세그먼트 트리
 * 여러 개의 데이터가 연속적으로 존재할 때 특정 범위의 데이터의 대표값(여기선 합)을 구하는 자료구조
 * 데이터의 합을 가장 빠르고 간단하게 구할 수 있는 자료구조
 * 합이 아닌 최대, 최소 값도 빠르게 구할 수 있다. (build를 바꿔야 함.)
 * 
 * 합을 구하는 방법
 * 1. 단순 구현 - 각 값을 그때그때 합쳐주는 것 - 쿼리마다 O(N)
 * 2. 구간 합 - 값을 하나씩 합쳐준 다음(누적 합), idx를 기준으로 a - b -> O(1) 시간복잡도
 * 하지만, 구간합의 경우 갱신이 들어올 때(idx값 변경) O(N)의 시간복잡도가 걸리게 된다.
 * 
 * 추가로 쿼리가 들어와서 값을 갱신할 경우
 * 구간합을 이용한 풀이로는 풀리지 않는다.
 * 따라서, 쿼리가 들어올 때 업데이트해줄 수 있는 자료구조가 필요한데, 그게 세그먼트 트리이다.
 * 
 * 자료구조
 * 1. segtree를 저장할 배열, 들어온 값을 저장할 배열
 * 1-1. segtree 크기 : N개보다 큰 가장 가까운 N의 제곱수의 2배, 기본적으론 4 * N 만큼의 사이즈를 할당해주면 됨.
 * 1-2. segtree 구조 : 1부터 시작, 1번에 모든 노드의 합, 2번 - 4 5 노드의 합, 3 - 6 7 번 노드의 합....
 * 
 * build
 * 재귀를 이용해 세그먼트 트리를 빌드한다.
 * build할 때 원 배열에 접근해서 빌드한 다음, 이후에는 원 배열에 접근하지 않는다.
 * 
 * query
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
    int N;
    int SEG_N;
    int[] arr;
    int[] segTree;

    SegTree(int n) {
        N = n;
        SEG_N = 4 * N;

        arr = new int[N];
        segTree = new int[SEG_N];
    }

    // 초기화
    void setArray(int[] data) {
        System.arraycopy(data, 0, arr, 0, N);
    }

    /**
     * arr가 있을 때 재귀로 segtree를 초기화해주는 함수.
     * @param node - segtree의 인덱스값
     * @param src - arr의 인덱스 시작 값
     * @param dst - arr의 인덱스 종료 값
     */
    void init(int node, int src, int dst) {
        if (src == dst) {
            segTree[node] = arr[src];
            return;
        }

        int mid = (src + dst) / 2;

        init(node * 2, src, mid);
        init(node * 2 + 1, mid + 1, dst);

        segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
    }

    void init() {
        init(1, 0, N - 1);
    }

    /**
     * 특정 인덱스의 값을 업데이트하는 메소드
     * arr[idx] += value 형태로 값을 증가시킴
     * 결국, diff값으로 업데이트가 됨.
     * @param node - segtree의 인덱스값
     * @param src - arr의 인덱스 시작 값
     * @param dst - arr의 인덱스 종료 값
     * @param idx - arr의 업데이트할 배열 인덱스
     * @param value - 증가시킬 값 (원본 값과의 diff값)
     */
    void update(int node, int src, int dst, int idx, int value) {
        if (src == dst) { // 리프노드에 도달할 경우 - segtree만 업데이트
            segTree[node] += value;
            return;
        }

        if (idx > dst || idx < src) return; // 기저 조건 : 찾는 범위를 벗어남.

        int mid = (src + dst) / 2;
        update(node * 2, src, mid, idx, value);
        update(node * 2 + 1, mid + 1, dst, idx, value);

        // 자식 노드 값을 받아 업데이트.
        segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
    }

    /**
     * 외부에서 실제로 부를 함수
     * @param idx - arr의 업데이트할 배열 인덱스
     * @param value - 증가시킬 값 (원본 값과 diff 값)
     */
    void update(int idx, int value) {
        update(1, 0, N - 1, idx, value);
    }

    /**
     * 구간 합 쿼리를 처리하는 메소드
     * [p, q] 범위의 원소들의 합을 리턴함.
     * @param node segtree의 현재 노드 인덱스
     * @param src arr의 시작 인덱스
     * @param dst arr의 종료 인덱스
     * @param p arr의 찾아야할 쿼리 시작 인덱스(포함)
     * @param q arr의 찾아야 할 쿼리 종료 인덱스 (포함)
     * @return 구간 합
     */
    public int query(int node, int src, int dst, int p, int q) {
        // 범위가 완전 아닐 때
        if (q < src || p > dst) return 0;
        // 범위일 때
        if (p <= src && dst <= q) return segTree[node];

        // 범위가 걸쳐있을 때
        int mid = (src + dst) / 2;
        return query(node * 2, src, mid, p, q) + query(node * 2 + 1, mid + 1, dst, p, q);
    }

    /**
     * 외부에서 실제로 부를 함수
     * @param p - arr의 시작 인덱스(포함)
     * @param q - arr의 끝 인덱스 (포함)
     * @return 구간 합
     */
    public int query(int p, int q) {
        return query(1, 0, N - 1, p, q);
    }

    void printArray() {
        System.out.print("Original Array: ");
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
