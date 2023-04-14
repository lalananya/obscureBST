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
    **------------------------LOGIC**
    Operation will use recursion, so think of Stack will writing the code
    If -1 > H(left-Right) < 1, perform Rotations
    
    **CASE 1 :**

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

    **CASE 2 :**

                8
            6
        5

    h(root8) = 2 - 0 = 2, right rotate it

            6
        5       8
    
    , root changes to 6

    **CASE 3/4 :** 

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

    **CASE 5/6 :** 

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

   **------------------------LOGIC**
   Organisation of the tree on basis of frequently accessed / inserted elements become closer to the root node

   Before inserting we rotate the tree based on conditions, so that the inserted element is closer to the root or
   becomes the root itself.

   Inserted element is the root, and by splaying we get the node to which the new element will be pointing to.
                 
3. Red Black Tree

   To Balance, we use recoloring and rotation

   Recoloring meaning : change the color of the node, if it is red, then change it to black
   **------------------------LOGIC**
   insert the node like we insert in binary tree, assign the color as 'red'
   if the node is root, change the color to 'black'
   if the node is not root, check it's parent color (after insertion ofcourse)
        = if the parent node is black let it be, 
        = if the parent node is red, (conflict)
                then to resolve conflict we have to check the ancestors, it must be alternate in color, 
                immediate parent color (from red - change it to black), then it's parent if black , 
                change to red
                make sure it follows, but also make sure the root must be black
                if red check, it's immediate parent color is black ( no conflict)

    

   
4. AA Tree

   Variatio of Red Black Tree

5. Treaps
   
   Somewhat similar, to red black and AVL , but they do not guarantee O(logn), uses randomization  and binary Heap
   to maintain balance with high probability.

   Every Node of Treap maintains = key (acc to BST), priority(follows max heap property)

   Here, instead of colors or height we check the priority, and then rotate it

   = insert as per normal BST
   = check if inserted node priority is greater than it's parent, this is violation and then we rotate it

6. ScapeGoat Tree

7. BTree
