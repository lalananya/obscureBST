package BinaryTree;

public class BTree {
    
    class BTreeNode {
        int n;
        boolean leaf = true;
        int key[] = new int[order - 1];
        BTreeNode child[] = new BTreeNode[order];

        int findKey(int k){ /* find the position of a key in the key's list */
            for(int i=0 ;i< this.n; i++){
                if(this.key[i] == k){
                    return i;
                }
            }
            
            return -1;
        }
    }

    private int order;
    BTreeNode root;

    BTree(int order){
        this.order = order;
        root = new BTreeNode();
        root.n = 0;
        root.leaf = true;
    }
    
    void insert(int key){
        
    }

    public static void main(String[] args) {
        BTree obj = new BTree(3);
        obj.insert(8);
        obj.insert(9);
        obj.insert(10);
        obj.insert(11);
        obj.insert(15);
        obj.insert(20);
        obj.insert(17);
    }
}
