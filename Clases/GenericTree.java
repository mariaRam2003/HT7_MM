/**
 * Hoja de Trabajo 7
 * @author Maria Marta Ramirez
 * Carne 21342
 * Algoritmos y Estructura de Datos
 * 
 * Clase Generic Binary Search Tree
 */

 //REFERENCIA: https://stackoverflow.com/questions/11263244/java-how-do-i-implement-a-generic-binary-search-tree
 
public class GenericTree<V extends Comparable<V>,K> {

    private class Node extends Association<V,K>{
        Node left, right;
        public Node(V value , K transaltion) {
            super(value, transaltion);
        }
    }
    
    private Node root;
    private int size;
    
    public int getSize() {
        return size;
    }
    
    public GenericTree() {
        this.root = null;
        this.size = 0;
    }
    
    public boolean isEmpty() {
        if (root == null) return true;
        else return false;
    }
    
    /**
     * @param value
     * @param transaltion
     */
    public void insert(V value , K transaltion) {
        size++;
        if (isEmpty()) root = new Node(value, transaltion);
        else insert(root, value, transaltion);
    }
    
    /**
     * @param value
     * @return
     */
    public boolean contains(V value) {
        return contains(root, value);
    }
    
    public void print() {
        print(root);
    }
    
    /**
     * @param key
     * @return
     */
    public K getValue(V key) {
        return getValue(root, key);

    }
    
    /**
     * @param value
     */
    public void delete(V value) {
        root = deleteRecursive(root,value);
    }

    /**
     * @param node
     * @param value
     * @return
     */
    private Node deleteRecursive(Node node, V value) {
        if (node == null){
            return null;
        }
        if(value.compareTo(node.key) == 0){
            
            //no hijos
            if (node.left == null && node.right == null) {
                return null;
            }

            // 1 hijo
            if (node.right == null) {
                return node.left;
            }

            if (node.left == null) {
                return node.right;
            }
            // 2 hijos

            Node smallestValue = findSmallestValue(node.right);
            node.key = smallestValue.key;
            node.value = smallestValue.value;
            node.right = deleteRecursive(node.right, smallestValue.key);
            return node;
        }if(value.compareTo(node.key) < 0){
            node.left = deleteRecursive(node.left, value);
            return node;
        }
        node.right = deleteRecursive(node.right, value);
        return node;
    }

    /**
     * @param node
     * @return
     */
    private Node findSmallestValue(Node node) {
        return node.left == null? node : findSmallestValue(node.left);
    }
    
    /**
     * @param node
     * @param key
     * @return
     */
    private K getValue(Node node, V key) {
        if (key.compareTo(node.key) == 0) {
            return node.value;
        } else if (key.compareTo(node.key) < 0) {
            if (node.left == null) return null;
            else return getValue(node.left, key);
        } else {
            if (node.right == null) return null;
            else return getValue(node.right, key);
        }

    }

    /**
     * @param node
     * @param value
     * @return
     */
    private boolean contains(Node node, V value) {
        if (value.compareTo(node.key) == 0) {
            return true;
        } else if (value.compareTo(node.key) < 0) {
            if (node.left == null) return false;
            else return contains(node.left, value);
        } else {
            if (node.right == null) return false;
            else return contains(node.right, value);
        }
    }
    
    /**s
     * @param node
     */
    private void print(Node node) {
        if (root == null) return;
        if (node.left != null) print(node.left);
        System.out.println(node.key + ": " + node.value);
        if (node.right != null) print(node.right);
    }
    
    /**
     * @param node
     * @param value
     * @param transaltion
     */
    private void insert(Node node, V value, K transaltion) {
        if(value.compareTo(node.key) <= 0) {
            if(node.left == null) node.left = new Node(value, transaltion);
            else insert(node.left, value, transaltion);
        } else {
            if(node.right == null) node.right = new Node(value, transaltion);
            else insert(node.right, value , transaltion);
        }
    }

}