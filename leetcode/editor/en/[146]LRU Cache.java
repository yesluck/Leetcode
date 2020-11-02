//Design a data structure that follows the constraints of a Least Recently Used 
//(LRU) cache. 
//
// Implement the LRUCache class: 
//
// 
// LRUCache(int capacity) Initialize the LRU cache with positive size capacity. 
//
// int get(int key) Return the value of the key if the key exists, otherwise ret
//urn -1. 
// void put(int key, int value) Update the value of the key if the key exists. O
//therwise, add the key-value pair to the cache. If the number of keys exceeds the
// capacity from this operation, evict the least recently used key. 
// 
//
// Follow up: 
//Could you do get and put in O(1) time complexity? 
//
// 
// Example 1: 
//
// 
//Input
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//Explanation
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // cache is {1=1}
//lRUCache.put(2, 2); // cache is {1=1, 2=2}
//lRUCache.get(1);    // return 1
//lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//lRUCache.get(2);    // returns -1 (not found)
//lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//lRUCache.get(1);    // return -1 (not found)
//lRUCache.get(3);    // return 3
//lRUCache.get(4);    // return 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 3000 
// 0 <= value <= 104 
// At most 3 * 104 calls will be made to get and put. 
// 
// Related Topics Design


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// Only define DLinkedNode
class LRUCache {

    class DLinkedNode {
        private int key;
        private int value;
        private DLinkedNode prev;
        private DLinkedNode next;
    }

    Map<Integer, DLinkedNode> cache;
    DLinkedNode head, tail;
    int size;
    int capacity;

    private void addFirst(DLinkedNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void remove(DLinkedNode node) {
        DLinkedNode next = node.next;
        DLinkedNode prev = node.prev;
        next.prev = prev;
        prev.next = next;
    }

    private void moveFirst(DLinkedNode node) {
        remove(node);
        addFirst(node);
    }

    private DLinkedNode popLast() {
        DLinkedNode last = tail.prev;
        remove(last);
        return last;
    }

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        size = 0;
        this.capacity = capacity;

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null)
            return -1;
        moveFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            DLinkedNode node = cache.get(key);
            node.value = value;
            moveFirst(node);
            return;
        }

        DLinkedNode newNode = new DLinkedNode();
        cache.put(key, newNode);
        newNode.key = key;
        newNode.value = value;
        addFirst(newNode);
        size++;

        if (size > capacity) {
            DLinkedNode last = popLast();
            cache.remove(last.key);
            size--;
        }
    }
}

// Define both DLLNode and DoubleLinkedList, to expand to LFU
class LRUCache {

    class DLLNode {
        int key;
        int value;
        DLLNode prev;
        DLLNode next;

        DLLNode() {
            this.key = -1;
            this.value = -1;
        }

        DLLNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoubleLinkedList {
        DLLNode head, tail;

        DoubleLinkedList() {
            this.head = new DLLNode();
            this.tail = new DLLNode();
            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(DLLNode node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public void remove(DLLNode node) {
            DLLNode next = node.next;
            DLLNode prev = node.prev;
            next.prev = prev;
            prev.next = next;
        }

        public void moveFirst(DLLNode node) {
            remove(node);
            addFirst(node);
        }

        public DLLNode popLast() {
            DLLNode last = tail.prev;
            remove(last);
            return last;
        }
    }

    Map<Integer, DLLNode> cache;
    DoubleLinkedList list;
    int size;
    int capacity;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        list = new DoubleLinkedList();
        size = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        DLLNode node = cache.get(key);
        if (node == null)
            return -1;
        list.moveFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            DLLNode node = cache.get(key);
            node.value = value;
            list.moveFirst(node);
            return;
        }

        DLLNode node = new DLLNode(key, value);
        cache.put(key, node);
        list.addFirst(node);
        size++;

        if (size > capacity) {
            DLLNode last = list.popLast();
            cache.remove(last.key);
            size--;
        }
    }
}
