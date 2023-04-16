package BinaryTree;

import java.util.ArrayList;

// class EmployeeDetails {
//     int eid;
//     String firstname;
//     String lastname;
//     String desg;
//     String email;
//     String doj;
//     int salary;
// }

public class BinaryT {
   
    EmployeeNode root;
    EmployeeNode lastVisited;
    class EmployeeNode {
        int eid;
        String desg;
        int parentId;
        ArrayList<EmployeeNode> children;

        EmployeeNode(int id, String desg, int parentId){
            this.eid = id;
            this.desg = desg;
            this.parentId = parentId;
            this.children = new ArrayList<EmployeeNode>();
        }
    }

    BinaryT(){
        root = null;
        lastVisited = null;
    }

    EmployeeNode findById(EmployeeNode head, int pid){    
        if(head.eid == pid) return head;
        else {
            int loop = 0;
            while(head.children.size() > loop) {
                head = findById(head.children.get(loop), pid);
                if(head.eid == pid){
                    break;
                }
                loop++;
            }
        }
        return head;

    }
    void insert(EmployeeNode newnode){
        if(root == null){
            root = newnode;
        }
        else {
            EmployeeNode nodeInfo = findById(root, newnode.parentId);
            nodeInfo.children.add(newnode);
        }
    }

    void display(EmployeeNode node){
        if(node != null){
            System.out.println("At : " + node.eid + " with desg : " + node.desg + " parent Id" + node.parentId);
            System.out.println(node.children);
            int loop = 0;
            while(node.children.size() > loop) {
                display(node.children.get(loop));
                loop++;
            }   
        }
    }

    void create(){
        insert(new EmployeeNode(0,"ceo", 0));
        insert(new EmployeeNode(1,"cto", 0));
        insert(new EmployeeNode(2,"cmo", 0));
        insert(new EmployeeNode(3,"cco", 0));
        insert(new EmployeeNode(4,"hod-p1", 1));
        insert(new EmployeeNode(5,"hod-p2", 1));
        display(root);
    }

    public static void main(String[] args) {
        BinaryT bt = new BinaryT();
        bt.create();
    }
}
