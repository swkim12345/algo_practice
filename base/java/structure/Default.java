package base.java.structure;

import java.util.concurrent.CopyOnWriteArraySet;

/*
 * 자바의 기본 자료구조 사용법을 저장하는 곳이다.
 * Set
 * HashSet - hash 기반 set
 * TreeSet - 정렬 지원 set
 * 
 * List
 * ArrayList
 * LinkedList
 * 
 * Deque
 * ArrayDeque
 * LinkedDeque
 * 
 * 
 */

import java.util.*;

public class Default {
    public static void main(String[] args) {
        /* ===== List 계열 ===== */
        List<String> arrayList = new ArrayList<>();
        arrayList.add("ArrayList");
        
        List<String> linkedList = new LinkedList<>();
        linkedList.add("LinkedList");
        
        /* ===== Set 계열 ===== */
        /* 내부적으론 Map을 이용해 구현됨 */
        Set<String> hashSet = new HashSet<>();
        hashSet.add("HashSet");
        
        // HashSet + LinkedList 자료구조, 입력값의 순서를 보장함.
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("LinkedHashSet");
        
        //정렬이 가능한 set, Set Interface에는 정렬이 없으므로, SortedSet에 할당해 사용해야 한다.
        SortedSet<String> treeSet = new TreeSet<>();
        treeSet.add("TreeSet");
        
        // noneof이 구현되어 factory 패턴으로 리턴함.
        EnumSet<Day> enumSet = EnumSet.of(Day.MONDAY, Day.FRIDAY);

        /* ===== Queue / Deque 계열 ===== */
        Queue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("PriorityQueue");
        
        Deque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.add("ArrayDeque");
        
        Deque<String> dequeLinkedList = new LinkedList<>();
        dequeLinkedList.add("Deque(LinkedList)");

        /* ===== Map 계열 ===== */
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("HashMap", 1);
        
        // Map 구현에 linkedlist를 추가해 삽입순서를 보전한 map 자료구조
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("LinkedHashMap", 2);
        
        SortedMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("TreeMap", 3);
        
        EnumMap<Day, String> enumMap = new EnumMap<>(Day.class);
        enumMap.put(Day.MONDAY, "EnumMap");

        /* ===== 출력 ===== */
        System.out.println("=== List 계열 ===");
        System.out.println(arrayList);
        System.out.println(linkedList);

        System.out.println("\n=== Set 계열 ===");
        System.out.println(hashSet);
        System.out.println(linkedHashSet);
        System.out.println(treeSet);
        System.out.println(enumSet);

        System.out.println("\n=== Queue / Deque 계열 ===");
        System.out.println(priorityQueue);
        System.out.println(arrayDeque);
        System.out.println(dequeLinkedList);

        System.out.println("\n=== Map 계열 ===");
        System.out.println(hashMap);
        System.out.println(linkedHashMap);
        System.out.println(treeMap);
        System.out.println(enumMap);
    }

    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
