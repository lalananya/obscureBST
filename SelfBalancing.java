/* 
 * insertion and deletion operation in a tree can lead to skewed trees, so while performing these operations
 * we are know gping to balance with each operation, so the other operations like searching becomes easy
 * KEY : TO KEEP HEIGHT OF THE TREE AS SMALL AS POSSIBLE
 * Types and its basic idea: 
 *  AVL Tree - try balance the left and right subtree, where the difference should not be more than 1 for every node
 *  Red/Black Tree - 
 *  Splay Tree - bring the most recent accessed / inserted element to the root by performing rotations (splaying)
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
        int leftH = head.left !=null ? head.left.height : 0;
        int rightH = head.right !=null ? head.right.height : 0;
        return leftH - rightH;
    }

    void updateHeight(Node head){
        int leftH = head.left !=null ? head.left.height : 0;
        int rightH = head.right !=null ? head.right.height : 0;
        head.height = 1 + Math.max(leftH, rightH);
    }
    
    /* Insertion on AVL Tree */

    Node insertElement(Node head, int element){
        if(head == null){
            return new Node(element);
        }
        else {
            if(head.id > element){
                /* loop over left subtree until a place found */
                head.left = this.insertElement(head.left,element);
            }
            else if (head.id < element) {
                /* loop over right subtree until a place found */         
                head.right = this.insertElement(head.right,element);
            }
            else return head;
        }
        this.updateHeight(head);

        /* logic needs recheck */

        int factor = this.checkBalanceFactor(head);
        if (factor > 1 && element < head.left.id) /* right rotation */
            return rightRotate(head);
 
        if (factor < -1 && element > head.right.id) /* left rotation */
            return leftRotate(head);

        if (factor > 1 && element > head.left.id) { /* left right rotation */
            head.left = leftRotate(head.left);
            return rightRotate(head);
        }
 
        if (factor < -1 && element < head.right.id) { /* right left rotation */
            head.right = rightRotate(head.right);
            return leftRotate(head);
        }
        
        return head;
    }

    /* inOrder */

    void inOrder(Node head){
        if(head !=null){
            inOrder(head.left);
            System.out.println("HEAD -> " + head.id + "Height ->" + head.height);
            inOrder(head.right);
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
        Node hr = head.right;
        Node hrf = hr.left;

        hr.left = head;
        head.right = hrf;

        updateHeight(head);
        updateHeight(hr);

        return hr;
    }

    Node rightRotate(Node head){
        Node hr = head.left;
        Node hrf = hr.right;

        hr.right = head;
        head.left = hrf;

        updateHeight(head);
        updateHeight(hr);

        return hr;
    }
    /* ---------------------- */

    public static void main(String[] args) {
        AVLTree avlObj = new AVLTree();
        avlObj.root = avlObj.insertElement(avlObj.root, 8);
        avlObj.root = avlObj.insertElement(avlObj.root, 7);
        avlObj.root = avlObj.insertElement(avlObj.root, 6);
        avlObj.root = avlObj.insertElement(avlObj.root, 9);
        avlObj.root = avlObj.insertElement(avlObj.root, 2);
        avlObj.root = avlObj.insertElement(avlObj.root, 3);
        avlObj.root = avlObj.insertElement(avlObj.root, 10);
        avlObj.root = avlObj.insertElement(avlObj.root, 1);
        avlObj.root = avlObj.insertElement(avlObj.root, 12);

        avlObj.inOrder(avlObj.root);
        avlObj.searchElement(avlObj.root, 123);
    }
}


public class SelfBalancing {
}
