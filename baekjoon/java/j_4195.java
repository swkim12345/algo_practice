/*
 * 친구 네트워크
 * 
 * 유니온 파인드를 사용하는 문제지만, 친구 관계의 "수"를 물어보는 문제이다.
 * 수는 가장 최상위 노드에 값을 저장하고, 트리끼리 합쳐지면 그때 자신이 가지고 있는 숫자를 최상위 노드에 더해준다.
 * 부모 노드는 compareTo를 통해 비교했을 때 가장 값이 작은 노드가 된다.
 * 숫자를 더하는 액션은 union 연산에서 발생한다.
 * 
 * 현재 막히는 부분
 * 1. 기존의 유니온파인드는 노드 숫자로 되어 있고, 이 숫자가 10만정도여서 바로 선언한 다음 사용해도 무방했다.
 * 하지만, 여기서는 친구의 "이름"으로 노드가 특정되는 게 단점이다.
 * 친구의 모든 이름이 처음에 주어지는 것도 아니라서 이진탐색으로 접근하는 것도 불가능해보인다.
 * 그럼 숫자 - 이름으로 1:1 매칭하고, 이름을 선형탐색하는 방법은? -> 친구 관계 수가 10만을 넘지 않으므로 가능할 수도?
 * 그럼 친구의 수가 최대 20만정도이고, 이를 선형탐색을 10만번 한다고 가정하면 200억....이구나..... 안 돼;
 * 
 * 힌트 : 해시를 사용하면 된다. - hashmap을 이용해 문제 해결
 * 
 * 알고리즘 유형 : 자료구조, 해시를 사용한 집합과 맵, 집합과 맵, 분리 집합.
 * 
 * 오답노트
 * 1. string 업데이트도 같이 진행해줘야 하는 데 까먹고 하지 못함.
 * 2. 더하는 흐름과 부모 노드를 갱신하는 것은 정 반대여야 함. (자손 노드 - 부모노드 갱신 / 아니면 추가)
 * 3. 부모가 동일할 경우 합칠 필요가 없음. 이걸 처리해야 함.
 * 
 * 참고한 답
 * https://www.acmicpc.net/source/96297272
 * 
 * 1. StringBuilder를 사용해서 시간 단축
 * 2. find을 class 메소드로 추가함.
 * 3. 이름을 Pair가 아닌 node로 선언해 가독성 증진
 */
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class j_4195 {
    static class Pair {
        String st;
        Integer num;
        Pair(String st, Integer num) {
            this.st = st;
            this.num = num;
        }
    }
    static HashMap<String, Pair> hash;

    static String find(String one) {
        if (hash.get(one).st == one) return one; // 종료조건 : 부모와 자기 자신이 같을 경우(최상위 노드)
        else {
            Pair tmp = hash.get(one);
            tmp.st = find(tmp.st);//재귀 구현 (경로 단축)
            return tmp.st;
        }
    }
    static void union(String one, String two) {
        one = find(one);
        two = find(two);

        if (one == two) {
            return ;
        }
        Pair onePair = hash.get(one);
        Pair twoPair = hash.get(two);
        if (one.compareTo(two) <= 0) {
            onePair.num += twoPair.num;
            twoPair.st = onePair.st; //작은 쪽을 부모로 침.
        } else {
            twoPair.num += onePair.num;
            onePair.st = twoPair.st;
        }
    }
    static void solution(String one, String two) {
        /*
         * 1. one, two가 이미 있는 지 확인
         * 1-1. 없으면 그냥 추가하기. 추가한 다음 2-1로 넘어가기.
         * 2-1. 부모가 자기 자신인 노드끼리 합칠 경우 - 부모 중 작은 값에 더 큰 부모의 값을 추가하기
         * 2-2. 아닌 노드는 계속 find를 하면 됨
         * 3. 마지막에 친구의 개수는 새롭게 부모가 된 노드의 값으로 출력하면 끝
         */

        if (!hash.containsKey(one)) {
            hash.put(one, new Pair(one, 1));
        }
        if (!hash.containsKey(two)) {
            hash.put(two, new Pair(two, 1));
        }

        union(one, two);
        String tmp = find(two);
        System.out.println(hash.get(tmp).num);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int F, connection;
        String one, two;

        F = Integer.parseInt(br.readLine());
        for (int i = 0; i < F; i++) {
            hash = new HashMap<>();
            connection = Integer.parseInt(br.readLine());

            for (int j = 0; j < connection; j++) {
                st = new StringTokenizer(br.readLine());
                one = st.nextToken();
                two = st.nextToken();
                solution(one, two);
            }
        }
    }
}
