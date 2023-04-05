public class BST {
    Node root;

    BST(){
        root = null;
    }

    BST(int id){
        root = new Node(id);
    }

    void insert(int id){
        root = insertOperation(root, id);
    }

    Node insertOperation(Node root, int id){
        if(root == null){
            return new Node(id); /* if tree is empty, return a new node */
        }
        else if(id < root.id) {
            root.left = insertOperation(root.left, id);
        }
        else if(id > root.id) {
            root.right = insertOperation(root.right, id);
        }
        return root;
    }

    void preOder(Node root){
        if(root != null){
            System.out.print(root.id + " - ");
            preOder(root.left);
            preOder(root.right);
        }
    }

    void deleteElement(Node root, int element){
        System.out.println("checking : " + root.id);
        if(root != null){
            if(root.id == element){
                if(root.left == null && root.right == null){
                    root = null;
                    System.out.println("elememt deleted :" + root );
                }
                else if(root.left !=null && root.right == null){
                    root.id = root.left.id;
                    root.left = null;
                    System.out.println("elememt deleted :" + root );
                }
                else if(root.right !=null && root.left == null){
                    root.id = root.right.id;
                    root.right = null;
                    System.out.println("elememt deleted :" + root );
                }
                else {
                    /* get the inOrder Successor */
                    Node current = root;
                    while(current.left != null){
                        current = current.left;
                    }
                    root.id = current.id;
                    current = null;
                    System.out.println("elememt deleted :" + root );
                    
                }
            }
            if(root !=null){
                if(root.id > element) deleteElement(root.left, element);
                if(root.id < element) deleteElement(root.right, element);
            }
           
        }   
    }

    public static void main(String[] args) {
        BST obj = new BST();
        obj.insert(50);
        obj.insert(20);
        obj.insert(60);
        obj.insert(10);
        obj.insert(5);
        obj.insert(1);
        obj.preOder(obj.root);
        /* after insertion check the tree height, searching will take O(h), here self balancing comes into play */
        obj.deleteElement(obj.root,1);
        obj.preOder(obj.root);
    }
}
