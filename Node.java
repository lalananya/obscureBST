/*
 * This Node class used overall
 */
class Node {
    int id;
    int height;
    Node left;
    Node right;

    Node(int id){
        this.id = id;
        height = 1;
    }
}