            Trees are a little complex data structure, and there are many types as per use cases, 
            tried to understand different trees on basis of where to use , their difference, 
                                    and performing complex operations

                                TO KNOW ABOUT DS, A LITTLE MORE 

                                        *LANGUAGE : JAVA*
                        *REFERENCE : GeeksForGeeks, YouTube, Upgrad, GOOGLE*

*BST.java*

Binary Search Tree
 = atmost 2 subtress
 = the left has elements lesser than the root, and right has greater
 = OPERATION
   insertion : normal rescursion
   traversal : inOrder, preOrder, postOrder
   deletion : there are cases ->    
                            1. if the element to be deleted has no subtrees, assign the element to null
                            2. if the element to be deleted has one subtree, assign that to the element
                            3. if the element to be deleted has both, inOrder and go to the left most and replace
    as we are dealing with subtrees here, replacing is the approach in case of deletion
 = BFS & DFS usual

*Node.java (common used)*
 = Unique Id for the nodes, wrt class
 = element refers Node class 

*Creation.java (initial practice)*
 = self

*Self Balancing*

 = as per elements, operations like insertion in a tree, the height of the tree could gradually increase, and accessing the element can lead upto complexity of O(height), worse case.
 = Self Balancing trees can help us reduce the complexity and making operation on the trees easier.
 = Types : AVL | Splay | AA | RedBlack | Scapegoat | BTree | Treaps
 :: Tried to cover as much as I could find on google ::
 = In any tree, rotations are of two types only, left or right now according to the use and type, we can do it multiple times
   or one after the other, so this repo ignores the conventional name.
 = We do not create the whole tree, then balance it, this is a wrong approach.

 1. AVL Tree
    After every operation, we make sure the difference of the heights of left subtree and right subtree of any node, can not
    be greater than 1.
    Operation will use recursion, so think of Stack will writing the code
    If -1 > H(left-Right) < 1, perform Rotations
    
    CASE 1 :

    8
        9   
            10
    
    h(root8) = 0 - 2  = -2, left rotate it

            9
        8       10  
    
    , root changes to 9

    if element 10 needs to be searched, in the first tree, we will get at the third comparision, 
    but in the second tree, we will get at the second comparision,

    reduces the checking of element, reduces time complexity
    now , think about the large number of data.

    CASE 2 :

                8
            6
        5

    h(root8) = 2 - 0 = 2, right rotate it

            6
        5       8
    
    , root changes to 6

    CASE 3/4 : 

                            8
                    6                   10   
                5       7           9        15
            4
        3

    let's say the last element inserted is 3, 
    unbalaced nodes
    h(root6) = 3 - 1 = 2, right rotate it, this is first parent root that is affected

                            8
                5                       10   
            4       6               9        15
        3               7

    h(root8) = 3 - 2 = 1, no rotation needed

    NOTE : whenever we insert the element, this is a recursion process so we will keep updating the height as well
    and keep checking for the unbalanced node

    CASE 5/6 : 

                10
            7
              9

    leftRotate by root.left

                10
            9
        7

    rightRotate it       
            
            9
        7       10

    this is double rotation, in this case suppose we try to rotate right first
    below would be the structure, this is still not balance
    
            7
                10
        9     

    rightRotate by root.left

        7
            9
                10

    now, left rotate it for         
                9
            7       10

    Above are just the known cases, we are going to add some random exceptions as well, to make the rotations complex
    still would want the tree to be balanced

2. Splay Tree

3. Red Black Tree

4. AA Tree

5. Treaps

6. ScapeGoat Tree

7. BTree
