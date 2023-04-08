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

    void start(){
        root = insertElement(root,50);
        root = insertElement(root,40);
        root = insertElement(root,45);
        root = insertElement(root,60);
        root = insertElement(root,70);
        root = insertElement(root,65);
        root = insertElement(root,20);
        root = insertElement(root,10);
        System.out.println("Current Root" + root.id);
        root = deleteElement(root,10);
        preOder(root);

    }
}

class RedBlack {

}
public class SelfBalancing {
    
    static void initiate(){
        new AVLTree().start();
    }
    public static void main(String[] args) {
        initiate();
    }
}
