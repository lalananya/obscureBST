/* linked representation of a tree
 * @addNodesToTree
 * @calculateHeight
 * @displayTree
 * @inOrder
 * @postOrder
 * @preorder
 */
public class Creation {
    Node root;
    static int size;

    Creation(int id){
        root = new Node(id);
    }

    Node createNode(int id){
        return new Node(id);
    }

    void addNodesToTree(Node head, int id, String type, Node newnode){
        if(head !=null && head.id == id) {
            switch(type) {
                case "left" : head.left = newnode;
                break;
                case "right" : head.right = newnode;
                break;
            }
        }
        if(head.left != null) addNodesToTree(head.left, id, type, newnode);
        if(head.right !=null) addNodesToTree(head.right, id, type, newnode);
    }

    int calculateHeight(Node head, int height, String type){
        if(head != null){
            height++;
            switch(type){
                case "left" : height = this.calculateHeight(head.left, height, type);
                break;
                case "right" : height = this.calculateHeight(head.right, height, type);
                break;
            }
            
        }
        return height;
    }

    void displayTree(Node head){
        if(head != null){
            System.out.print(head.id + "->" );
            size++;
            displayTree(head.left);
            displayTree(head.right);
        }  
    }

    void inOrder(Node head){
        if(head != null){
            inOrder(head.left);
            System.out.print(head.id + "->" );
            inOrder(head.right);
        } 
    }

    void preOrder(Node head){
        if(head != null){
            System.out.print(head.id  + "->" );
            preOrder(head.left); 
            preOrder(head.right);
        } 
    }

    void postOrder(Node head){
        if(head != null){
            postOrder(head.left);
            postOrder(head.right);
            System.out.print(head.id  + "->" );
        } 
    }

    public static void main(String[] args) {
        Creation bst = new Creation(0);
        bst.addNodesToTree(bst.root, 0, "left", bst.createNode(1));
        bst.addNodesToTree(bst.root, 0, "right", bst.createNode(2));
        bst.addNodesToTree(bst.root, 1, "left", bst.createNode(3));
        bst.addNodesToTree(bst.root, 1, "right", bst.createNode(4));
        bst.addNodesToTree(bst.root, 2, "left", bst.createNode(5));
        bst.addNodesToTree(bst.root, 2, "right", bst.createNode(6));
        bst.addNodesToTree(bst.root, 3, "left", bst.createNode(7));
        bst.addNodesToTree(bst.root, 3, "right", bst.createNode(8));
        bst.addNodesToTree(bst.root, 4, "left", bst.createNode(9));

        bst.displayTree(bst.root);
        System.out.println("Size of the tree = " + size);
        System.out.println("Root of the tree = " + bst.root.id);
        
        int left = bst.calculateHeight(bst.root, 0, "left");
        int right = bst.calculateHeight(bst.root, 0, "right");
        if(left > right) {
            System.out.println("Height of the tree = " + (left-1));
        }
        else  System.out.println("Height of the tree = " + (right-1));
       
        System.out.println("\n In Order traversal : Left Root Right");
        bst.inOrder(bst.root);
        System.out.println("\n Pre Order traversal : Root Left Right");
        bst.preOrder(bst.root);
        System.out.println("\n Post Order traversal : Left Right Root");
        bst.postOrder(bst.root);
    }  
}
