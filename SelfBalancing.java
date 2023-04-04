/* 
 * insertion and deletion operation in a tree can lead to skewed trees, so while performing these operations
 * we are know gping to balance with each operation, so the other operations like searching becomes easy
 * KEY : TO KEEP HEIGHT OF THE TREE AS SMALL AS POSSIBLE
 * Types and its basic idea: 
 *      AVL Tree - try balance the left and right subtree, where the difference should not be more than 1 for every node
 *      Red/Black Tree - 
 *      Splay Tree - bring the most recent accessed / inserted element to the root by performing rotations (splaying)
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

    /* inOrder */

    void preOder(Node head){
        if(head !=null){
            System.out.println("HEAD -> " + head.id + "Height ->" + head.height);
            preOder(head.left);
            preOder(head.right);
        }
    }

    /* Deletion on AVL Tree */

    void deleteElement(){

    }

    /* Searching on AVL Tree */

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

    public static void main(String[] args) {
        AVLTree avlObj = new AVLTree();
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


public class SelfBalancing {
}
