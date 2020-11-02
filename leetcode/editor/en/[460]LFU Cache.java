//Design and implement a data structure for Least Frequently Used (LFU) cache. 
//
// Implement the LFUCache class: 
//
// 
// LFUCache(int capacity) Initializes the object with the capacity of the data s
//tructure. 
// int get(int key) Gets the value of the key if the key exists in the cache. Ot
//herwise, returns -1. 
// void put(int key, int value) Sets or inserts the value if the key is not alre
//ady present. When the cache reaches its capacity, it should invalidate the least
// frequently used item before inserting a new item. For this problem, when there 
//is a tie (i.e., two or more keys with the same frequency), the least recently us
//ed key would be evicted. 
// 
//
// Notice that the number of times an item is used is the number of calls to the
// get and put functions for that item since it was inserted. This number is set t
//o zero when the item is removed. 
//
// Follow up: 
//Could you do both operations in O(1) time complexity? 
//
// 
// Example 1: 
//
// 
//Input
//["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "g
//et"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
//
//Explanation
//LFUCache lFUCache = new LFUCache(2);
//lFUCache.put(1, 1);
//lFUCache.put(2, 2);
//lFUCache.get(1);      // return 1
//lFUCache.put(3, 3);   // evicts key 2
//lFUCache.get(2);      // return -1 (not found)
//lFUCache.get(3);      // return 3
//lFUCache.put(4, 4);   // evicts key 1.
//lFUCache.get(1);      // return -1 (not found)
//lFUCache.get(3);      // return 3
//lFUCache.get(4);      // return 4
//
// 
//
// 
// Constraints: 
//
// 
// 0 <= capacity, key, value <= 104 
// At most 105 calls will be made to get and put. 
// 
// Related Topics Design


/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class LFUCache {

    class DLLNode {
        int key;
        int value;
        int frequency;
        DLLNode prev;
        DLLNode next;

        DLLNode() {
            this.key = -1;
            this.value = -1;
            this.frequency = 1;
        }

        DLLNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    class DoubleLinkedList {
        DLLNode head, tail;
        int size;

        DoubleLinkedList() {
            size = 0;
            head = new DLLNode();
            tail = new DLLNode();
            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(DLLNode node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size += 1;
        }

        public void remove(DLLNode node) {
            DLLNode next = node.next;
            DLLNode prev = node.prev;
            next.prev = prev;
            prev.next = next;
            size -= 1;
        }

        public void moveFirst(DLLNode node) {
            remove(node);
            addFirst(node);
        }

        public DLLNode popLast() {
            if (size == 0)
                return null;
            DLLNode last = tail.prev;
            remove(last);
            return last;
        }
    }

    Map<Integer, DLLNode> cache;    // key -> node
    Map<Integer, DoubleLinkedList> frequencyMap;    // freq -> list
    int totalSize;
    int capacity;
    int minFrequency;

    public LFUCache(int capacity) {
        cache = new HashMap<>();
        frequencyMap = new HashMap<>();
        totalSize = 0;
        this.capacity = capacity;
        minFrequency = 0;
    }

    public int get(int key) {
        DLLNode node = cache.get(key);
        if (node == null)
            return -1;
        updateNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;

        if (cache.containsKey(key)) {
            DLLNode node = cache.get(key);
            node.value = value;
            updateNode(node);
        } else {
            totalSize += 1;
            if (totalSize > capacity) {
                DoubleLinkedList minFreqList = frequencyMap.get(minFrequency);
                DLLNode popNode = minFreqList.popLast();
                cache.remove(popNode.key);
                totalSize -= 1;
            }
            minFrequency = 1;

            DLLNode node = new DLLNode(key, value);
            DoubleLinkedList list = frequencyMap.getOrDefault(1, new DoubleLinkedList());
            list.addFirst(node);
            cache.put(key, node);
            frequencyMap.put(1, list);
        }
    }

    private void updateNode(DLLNode node) {
        int freq = node.frequency;
        DoubleLinkedList list = frequencyMap.get(freq);
        list.remove(node);

        if (freq == minFrequency && list.size == 0) {
            minFrequency += 1;
        }

        node.frequency += 1;
        DoubleLinkedList newList = frequencyMap.getOrDefault(node.frequency, new DoubleLinkedList());
        newList.addFirst(node);
        frequencyMap.put(node.frequency, newList);
    }
}
