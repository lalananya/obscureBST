import java.util.Random;

/* Self Balancing a BST, so the basic rule remains the same, 
    nodes left to the root, smaller | 
    nodes right to the root, greater |
 */
class AVLTree {
    /* AVL is about the balance factor not about the height of the tree */
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

            updateHeight(node);
            updateHeight(newRoot);
            

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

            updateHeight(node);
            updateHeight(newRoot);

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

    Node deleteElement(Node current, int id){
        if(current == null){
            return current;
        }
        else {
            if(id < current.id){
                current.left = deleteElement(current.left, id);
            }
            else if(id > current.id){
                current.right = deleteElement(current.right, id);
            }
            else {
                if(current.left == null || current.right == null){
                    Node temp = null;
                    if(temp == current.left) temp = current.right;
                    else temp = current.left;

                    if(temp != null) {
                        current = temp;
                    }
                    else current = null;

                }
                else {
                    /* if the node to be deleted  has both trees, the minimum element in the right subtree will
                     * always be greater than the root (to be deleted)
                     */
                    Node min = findMinElement(current.right);
                    current.id = min.id;

                    /* after replacing the element with the min value, the tree will have two same min value
                     * we need to delete that leaf node min value.
                     */
                    current.right = deleteElement(current.right, min.id); 
                }
            }

        }

        updateHeight(current);
        int factor = balancingFactor(current);

        /* we checked for balance factor, of the node, 
         * as this is a case of deletion not insertion , there is no comparision between the element then balancing
         * but we need to check the balance factor of left and right subtree of the unbalanced node, then balance it
         */

        if(factor < -1 && balancingFactor(current.right) <= 0) {
            current = leftRotate(current);
        }
        if(factor < -1 && balancingFactor(current.right) > 0){
            current.right = rightRotate(current.right);
            current = leftRotate(current);
        }
        if(factor > 1 &&  balancingFactor(current.left) >= 0) {
            current = rightRotate(current);
        }
        if(factor > 1 &&  balancingFactor(current.left) < 0){
            current.left = leftRotate(current.left);
            current = rightRotate(current);
        }
        return current;
    }

    Node findMinElement(Node node){
        Node current = node;
        while(current.left != null){
            current = current.left;
        }

        return current;
    }

    static void start(){
        System.out.println("---------AVL---------");
        AVLTree obj = new AVLTree();
        obj.root = obj.insertElement(obj.root,50);
        obj.root = obj.insertElement(obj.root,40);
        obj.root = obj.insertElement(obj.root,45);
        obj.root = obj.insertElement(obj.root,60);
        obj.root = obj.insertElement(obj.root,70);
        obj.root = obj.insertElement(obj.root,65);
        obj.root = obj.insertElement(obj.root,20);
        obj.root = obj.insertElement(obj.root,10);
        System.out.println("Current obj.Root" + obj.root.id);
        obj.root = obj.deleteElement(obj.root,10);
        obj.preOder(obj.root);

    }
}

class RedBlack {

    RedBlackNodes root;
    boolean conflictCase = false;
    RedBlack(){
        root = null;
    }
    /* nodes are colored either red or black */
    class RedBlackNodes {
        int id;
        RedBlackNodes left;
        RedBlackNodes right;
        char color;
        RedBlackNodes parent;

        RedBlackNodes(int id){
            this.id = id;
            this.left = null;
            this.right = null;
            this.color = 'R';
            this.parent = null;
        }
    }
    
    
    void preOder(RedBlackNodes node){
        if(node != null){
            System.out.println("At : " + node.id);
            preOder(node.left);
            preOder(node.right);
        }
    }

    RedBlackNodes leftRotate(RedBlackNodes head){
        RedBlackNodes newHead = head.right;
        RedBlackNodes y = newHead.left;
        newHead.left = head;
        head.right = y;
        head.parent = newHead;
        if(y!=null)
            y.parent = head;
        return newHead;
    }

    RedBlackNodes rightRotate(RedBlackNodes head){
        RedBlackNodes newHead = head.left;
        RedBlackNodes y = newHead.right;
        newHead.right = head;
        head.left = y;
        head.parent = newHead;
        if(y!=null)
            y.parent = head;
        return newHead;
    }

    RedBlackNodes RRInsert(RedBlackNodes head, int id){
        conflictCase = false;
        if(head == null){
            return new RedBlackNodes(id);
        }

        if(head.id < id){
            head.right = RRInsert(head.right, id);
            head.right.parent = head;
            if(head != root){
                /* this is not the root position, we will check for conflicts */
                if(head.color == 'R' && head.right.color == 'R'){
                    conflictCase = true;
                }
            }
        }
        else {
            head.left = RRInsert(head.right, id);
            head.left.parent = head;
            if(head != root) {
                if(head.color == 'R' && head.left.color == 'R'){
                    conflictCase = true;
                }
            }
        }

        if(conflictCase) {
            /* in conflict case we check the ancestors, thus we kept parent as one of the keys */
            if(head.parent.right == head){ 
                /* to check conflict occured on which left /right : can check also check by which if else ran, but why extra variable when we can use this logic */

            }
            else {
                /* to do later */
            }
        }
        return head;
    }
    RedBlackNodes insert(RedBlackNodes head, int id){
        if(head == null){
            RedBlackNodes newnode = new RedBlackNodes(id);
            newnode.color = 'B';
            return newnode;
        }
        else {
            return RRInsert(head, id);
        }
    }

    static void start() {
        System.out.println("---------RED / BLACK---------");
        RedBlack obj = new RedBlack();
        obj.root = obj.insert(obj.root,50);
        obj.root = obj.insert(obj.root,40);
        obj.root = obj.insert(obj.root,60);
        obj.root = obj.insert(obj.root,45);
        obj.root = obj.insert(obj.root,55);
        obj.root = obj.insert(obj.root,70);
        obj.root = obj.insert(obj.root,80);
        obj.preOder(obj.root);
    }

}

class Splay {
   
    Node root = null;

    Node rightRotate(Node head){
        Node newRoot = head.left;
        head.left = newRoot.right;
        newRoot.right = head;
        return newRoot;
    }

    Node leftRotate(Node head) {
        Node newRoot = head.right;
        head.right = newRoot.left;
        newRoot.left = head;
        return newRoot;
    }

    Node splay(Node head, int id) {
        if(head == null || head.id == id){
            return head;
        }
        if(head.id > id) {
            if(head.left == null) return head;
            if(head.left.id > id) {
                head.left.left = splay(head.left.left, id);
                head = rightRotate(head);
            }
            else if(head.left.id < id) {
                head.left.right = splay(head.left.right, id);
                if(head.left.right != null) {
                    head.left = leftRotate(head.left);
                }
            }
            return head.left == null ? head : rightRotate(head);
        }
        else {
            if(head.right == null) return head;
            if(head.right.id > id) {
                head.right.left = splay(head.right.left, id);
                if(head.right.left != null){
                    head.right = rightRotate(head.right);
                }
            }
            else if(head.right.id < id) {
                head.right.right = splay(head.right.right, id);
                head = leftRotate(head);
            }
            return head.right == null ? head : leftRotate(head);
        }
    }

    Node insert(Node head, int id){
        if(head == null) return new Node(id);

        head = splay(head, id);
        if(head.id == id) return head;

        Node newnode = new Node(id); /* this has not child */
        if(head.id > id) {
            newnode.right = head; /* going to make this root of the returned splayed root */
            newnode.left = head.left; /* here new node left will be null, splayed root left will be assigned */
            head.left = null; /* cause no repeated elements, we will point this as null */
        }
        else {
            newnode.left = head;
            newnode.right = head.right;
            head.right = null;
        }
        System.out.println("------------------");
        preOder(newnode);
        return newnode;
    }

    void preOder(Node node){
        if(node != null){
            System.out.println("At : " + node.id);
            preOder(node.left);
            preOder(node.right);
        }
    }

    static void start(){
        System.out.println("---------SPLAY---------");
        Splay obj = new Splay();
        obj.root = obj.insert(obj.root,50);
        obj.root = obj.insert(obj.root,40);
        obj.root = obj.insert(obj.root,60);
        obj.root = obj.insert(obj.root,45);
        obj.root = obj.insert(obj.root,55);
        obj.root = obj.insert(obj.root,70);
        obj.root = obj.insert(obj.root,80);
        obj.preOder(obj.root);
    }
}


class Treaps {
    
    TreapsNode root;

    Treaps(){
        root = null;
    }
    class TreapsNode {
        int id;
        TreapsNode left;
        TreapsNode right;
        int priority; /* can be as per input , for now we are using a random number */

        TreapsNode(int id){
            this.id = id;
            this.left = null;
            this.right = null;
            this.priority = new Random().nextInt(100);
        }
    }

    void preOder(TreapsNode node){
        if(node != null){
            System.out.println("At : " + node.id + " with height : " + node.priority);
            preOder(node.left);
            preOder(node.right);
        }
    }

    TreapsNode leftRotate(TreapsNode head){
        TreapsNode newHead = head.right;
        TreapsNode y = newHead.left;

        newHead.left = head;
        head.right = y;

        return newHead;
    }

    TreapsNode rightRotate(TreapsNode head){
        TreapsNode newHead = head.left;
        TreapsNode y = newHead.right;

        newHead.right = head;
        head.left = y;

        return newHead;
    }

    TreapsNode insert(TreapsNode head, int id){
        if(head == null){
            return new TreapsNode(id);
        }
        else {
            if(head.id < id) {
                head.right = insert(head.right, id);
                if(head.right !=null && head.right.priority > head.priority) {
                    head = leftRotate(head);
                }
            }
            else {
                head.left = insert(head.left, id);
                if(head.left !=null && head.left.priority > head.priority){
                    head = rightRotate(head);
                }
            }
        }

        return head;
    }

    static void start(){
        System.out.println("---------TREAPS---------");
        Treaps obj = new Treaps();
        obj.root = obj.insert(obj.root,50);
        obj.root = obj.insert(obj.root,40);
        obj.root = obj.insert(obj.root,60);
        obj.root = obj.insert(obj.root,45);
        obj.root = obj.insert(obj.root,55);
        obj.root = obj.insert(obj.root,70);
        obj.root = obj.insert(obj.root,80);
        obj.preOder(obj.root);
    }
}
public class SelfBalancing {
    
    static void initiate(){
        AVLTree.start();
        RedBlack.start();
        Splay.start();
        Treaps.start();
    }
    public static void main(String[] args) {
        initiate();
    }
}
