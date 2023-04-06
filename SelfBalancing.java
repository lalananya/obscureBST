/* Self Balancing a BST, so the basic rule remains the same, 
    nodes left to the root, smaller | 
    nodes right to the root, greater |
 */
class AVLTree {
    Node root;

    AVLTree(){
        root = null;
    }

    
    int getHeight(Node node) {
        if(node != null) return node.height;
        return 0;
    }

    int balancingFactor(Node node){
        if(node != null){
            int left = getHeight(node.left);
            int right = getHeight(node.right);
            return left - right;
        }
        return 0;
    }

    void preOder(Node node){
        if(node != null){
            System.out.println("At : " + node.id + " with height : " + node.height);
            preOder(node.left);
            preOder(node.right);
        }
    }

    void updateHeight(Node node){
        if(node != null){
            int left = getHeight(node.left);
            int right = getHeight(node.right);
            node.height = 1 + Math.max(left, right);
        }
    }   

    Node leftRotate(Node node){
        if(node != null){
            Node newRoot = node.right;
            Node saveNode = node.right.left;

            newRoot.left = node;
            node.right = saveNode;

            updateHeight(newRoot);
            updateHeight(node);

            return newRoot;
        }
        return node;
    }

    Node rightRotate(Node node){
        if(node != null){
            Node newRoot = node.left;
            Node saveNode = node.left.right;

            newRoot.right = node;
            node.left = saveNode;

            updateHeight(newRoot);
            updateHeight(node);

            return newRoot;
        }
        return node;
    }

    Node insertElement(Node current, int id){ /* create a stack and check to understand */
        if(current == null){
            return new Node(id); /* searching for the null place to insert */
        }
        else {
            if(current.id < id) {
                current.right = insertElement(current.right,id);
            }
            else if(current.id > id) {
                current.left = insertElement(current.left, id);
            }
        }
        updateHeight(current);
        int factor = balancingFactor(current);
        if(factor < -1 && current.right.id < id) {
            current = leftRotate(current);
        }
        if(factor < -1 && current.right.id > id){
            current.right = rightRotate(current.right);
            current = leftRotate(current);
        }
        if(factor > 1 && current.left.id > id) {
            current = rightRotate(current);
        }
        if(factor > 1 && current.left.id < id){
            current.left = leftRotate(current.left);
            current = rightRotate(current);
        }
        return current; /* recursion keeps returning , at last it gets back to the starting point  */
    }

    void deleteElement(){

    }

    void findElement(){

    }

    void start(){
        root = insertElement(root,50);
        root = insertElement(root,40);
        root = insertElement(root,60);
        root = insertElement(root,70);
        root = insertElement(root,65);
        root = insertElement(root,30);
        root = insertElement(root,20);
        root = insertElement(root,10);
        System.out.println("Current Root" + root.id);
        preOder(root);

    }
}
public class SelfBalancing {
    
    static void initiate(){
        new AVLTree().start();
    }
    public static void main(String[] args) {
        initiate();
    }
}
