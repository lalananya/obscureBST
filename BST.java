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

    public static void main(String[] args) {
        BST obj = new BST();
        obj.insert(50);
        obj.insert(20);
        obj.insert(60);
        obj.insert(10);
        obj.insert(5);
        obj.insert(1);
        obj.preOder(obj.root);
    }
}
