/* 
 * insertion and deletion operation in a tree can lead to skewed trees, so while performing these operations
 * we are know gping to balance with each operation, so the other operations like searching becomes easy
 * KEY : TO KEEP HEIGHT OF THE TREE AS SMALL AS POSSIBLE
 * Types and its basic idea: 
 *      AVL Tree - try balance the left and right subtree, where the difference should not be more than 1 for every node
 *                 key is balance as much as possible , accessing might take O(h)
 *      Red/Black Tree - 
 *      Splay Tree - bring the most recent accessed / inserted element to the root by performing rotations (splaying), searching
 *                   insertion , deletion O(logn), do not guarantee balanced, key is frequently accessed close to root    
 */

class AVLTree {
    Node root; /* refer - Node.java same package */

    AVLTree(){
        root = null;
    }

    /* Checking Balancing Factor on AVL Tree */

    int height(Node obj){
        return (obj !=null) ? obj.height : 0;
    }

    /* Checking Balancing Factor on AVL Tree */

    int checkBalanceFactor(Node head){
        int leftH = height(head.left);
        int rightH = height(head.right);
        return leftH - rightH;
    }

    void updateHeight(Node head){
        int leftH = height(head.left);
        int rightH = height(head.right);
        head.height = 1 + Math.max(leftH, rightH);
    }
    
    /* Insertion on AVL Tree */

    void insertElement(int element){
        root = insertElementOperation(root, element);
    }

    Node insertElementOperation(Node head, int element){
        if(head == null){
            return new Node(element);
        }
        else {
            if(element < head.id) {
                head.left = insertElementOperation(head.left, element);
            }
            else if(element > head.id) {
                head.right = insertElementOperation(head.right, element);
            }
        }

        updateHeight(head);
        int factor = checkBalanceFactor(head);

        // System.out.println("-----------------------------------start-------------------------------------------------");
        //     System.out.println("Factor-" + factor + "Element : " + element + "current pointer : " + head.id );
        //     if(head.left != null) {
        //         System.out.println("current's left : " + head.left.id);
        //     }
        //     if(head.right != null) {
        //         System.out.println("current's right : " + head.right.id);
        //     }
        // System.out.println("------------------------------------end-------------------------------------------------");
        
        /* to fix rotation logic */
        if(factor > 0 && head.left != null && head.left.id > element) {
            rightRotate(head);
        }
        if(factor > 0 && head.left != null && head.left.id < element) {
            head.left = leftRotate(head.left);
            rightRotate(head);
        }
        if(factor < 0 && head.right != null && head.right.id > element) {
            leftRotate(head);
        }
        if(factor < 0 && head.right != null && head.right.id < element) {
            head.right = rightRotate(head.right);
            leftRotate(head);
        }

        return head;
    }

    void preOder(Node head){
        if(head !=null){
            System.out.println("HEAD -> " + head.id + "Height ->" + head.height);
            preOder(head.left);
            preOder(head.right);
        }
    }

    /* Deletion on AVL Tree :
     * after every deletion , balance the tree
     */

    void deleteElement(){

    }

    /* Searching on AVL Tree:
     * try searching without balancing (BST.java), and with balancing
     */

    void searchElement(Node head, int element){
        if(head != null){
            if(head.id == element) {
                System.out.println("found");
            }
            else {
                if(head.id < element) searchElement(head.right, element);
                else searchElement(head.left, element);
            }
        }
    }

    /* Rotations on AVL Tree
     * rotate and check balance factor, then re-check : KEY
     */

    Node leftRotate(Node head){
        if(head != null) {
            Node hr = head.right;
            Node hrf = hr!=null ? hr.left : null;

            if(hr != null && head !=null){
                hr.left = head;
                head.right = hrf;
                updateHeight(head);
                updateHeight(hr);
                return hr;   
            }
            return head;
        }
        return head;
    }

    Node rightRotate(Node head){
        if(head != null){
            Node hr = head.left;
            Node hrf = hr!=null ? hr.right : null;

            if(hr!= null && head !=null){
                hr.right = head;
                head.left = hrf;
                updateHeight(head);
                updateHeight(hr);
                return hr;
            } 
            
            return head;
        }
        return head;
    }
    /* ---------------------- */

    void avl(AVLTree avlObj) {
        avlObj.insertElement(8);
        avlObj.insertElement(7);
        avlObj.insertElement(6);
        avlObj.insertElement(9);
        avlObj.insertElement(2);
        avlObj.insertElement(3);
        avlObj.insertElement(10);
        avlObj.insertElement(1);
        avlObj.insertElement(12);
        avlObj.preOder(avlObj.root);
        // avlObj.searchElement(avlObj.root, 123);
    }
}

class Splay {
    
    Node root;

    Splay(){
        root = null;
    }

    /* all functions not needed , added just to understand */
    Node rotate(Node head, int check){
        /* 0 : zig rotation - single right rotation
         * 1 : zag rotation - single left rotation
         * 2 : zig/ zag rotation - right rotate then left
         * 3 : zag/ zig rotation - left rotate the right
         * 4 : zig/ zig rotation - double right rotation
         * 5 : zag/ zag rotation - double left rotation
         */
        switch(check){
            case 0 : return zigRotate(head);
            case 1 : return zagRotate(head);
            case 2 : return zigzagRotate(head);
            case 3 : return zagzigRotate(head);
            case 4 : return zigzigRotate(head);
            case 5 : return zagzagRotate(head);
            default :  return head;
        }
    }

    Node zagRotate(Node head) {
        Node nextHead = head.right;
        Node leftRight = nextHead.left;

        nextHead.left = head;
        head.right = leftRight;
        
        return nextHead;
    }

    Node zigRotate(Node head) {
        Node nextHead = head.left;
        Node leftRight = nextHead.right;

        nextHead.right = head;
        head.left = leftRight;
        
        return nextHead;
    }

    Node zagzagRotate(Node head) {
        head = zagRotate(head);
        head = zagRotate(head);
        return head;
    }

    Node zigzigRotate(Node head) {
        head = zigRotate(head);
        head = zigRotate(head);
        return head;
    }

    Node zagzigRotate(Node head) {
        head = zagRotate(head);
        head = zigRotate(head);
        return head;
    }
    
    Node zigzagRotate(Node head) {
        head = zigRotate(head);
        head = zagRotate(head);
        return head;
    }
    
    /* all functions not needed , added just to understand */

    Node performSplay(Node head, int id){
        if(head == null || head.id == id) {
            return head; /* the element is either null or present at head */
        }

        if(head.id > id) { /* element lies in left */
            
            if(head.left == null ) return head;
            if(head.left.id > id) {
                    head.left.left = performSplay(head.left.left,id);
                    head = zigRotate(head);
            }
            else if(head.left.id < id){
                    head.left.right = performSplay(head.left.right, id);
                    if(head.left.right !=null){
                        head.left = zagRotate(head.left);
                    }
            }
            return head.left == null ? head : zigRotate(head);
        }
        else {
            if(head.right == null) return head;
            if(head.right.id > id) {
                head.right.left = performSplay(head.right.left, id);
                if(head.right.left !=null) {
                    root.right = zigRotate(head.right);
                }
            }
            else if( head.right.id  < id) {
                head.right.right = performSplay(head.right.right, id);
                head = zagRotate(head);
            }
            return head.right == null ? head : zagRotate(head); 
        }
    }

    void insertElement(int id){
        root = insertOperation(root, id);
    }

    Node insertOperation(Node head, int id){
        if(head == null){
            return new Node(id);
        }

        head = performSplay(head, id); /* to bring the closest leaf to node */

        if(head.id == id) return head;
        Node newnode = new Node(id);

        if(head.id > id) {
            newnode.left = head;
            newnode.right = head.right;
            head.right = null;
        }
        else {
            newnode.left = newnode;
            newnode.right = head.right;
            head.right = null;
        }
        return newnode;
    }

    void preOder(Node root){
        if(root != null){
            System.out.println("Element : " + root.id + " Height : " + root.height);
            preOder(root.left);
            preOder(root.right);
        }
    }

    void splay(Splay splay){
        splay.insertElement(50);
        splay.insertElement(60);
        splay.insertElement(55);
        splay.insertElement(20);
        splay.insertElement(10);
        splay.insertElement(25);
        splay.insertElement(65);
        splay.preOder(splay.root);
    }

}

public class SelfBalancing {
    
    static void initiate(){
        // AVLTree avlObj = new AVLTree();
        // avlObj.avl(avlObj);
        Splay splayObj = new Splay();
        splayObj.splay(splayObj);
    }
    public static void main(String[] args) {
        initiate();
    }
}
