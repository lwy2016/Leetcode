```

class LRUCache {
    
    private int capacity;
    private int listSize;
    private Map<Integer, Node> map;
    private LinkedList<Node> cache;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        listSize = 0;
        map = new HashMap<Integer, Node>();
        cache = new LinkedList<Node>();
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            int index = cache.indexOf(node);
            // add to the head of the list
            cache.remove(index);
            cache.addFirst(node);
            
            return node.getValue();
        }
        
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            int index = cache.indexOf(node);
            // remove the old node in the list, not the hashMap
            cache.remove(index);
            // change the value of the node then add to the head of the list
            node.setValue(value);
            cache.addFirst(node);
        } else {
            // create the new node
            Node node = new Node(key, value);
            map.put(key, node);
            
            if (listSize < capacity) {
                cache.addFirst(node);
                listSize++;
            } else if (listSize == capacity) {
                // delete the tail node
                Node removedNode = cache.removeLast();
                int keyOfRemoveNode = removedNode.getKey();
                // remove the instance from hashMap
                map.remove(keyOfRemoveNode);
                // add the new node to the head of the list
                cache.addFirst(node);
            }
        }
    }
        
}
class Node {
    private int key;
    private int value;
    
    public Node (int key, int value) {
        this.key = key;
        this.value = value;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```