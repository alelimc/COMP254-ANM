//import org.w3c.dom.Node;
import java.util.Stack;

class Node {
    int val;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
    }
}

public class BinaryTreeTraversal {

    //The worst-case running time complexities of preorderNext(p):
    // O(n), where n is the number of nodes in the binary tree.
    //traverse the tree in a pre-order manner - root -> left subtree -> right subtree.
    public Node preorderNext(Node root, Node p) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node prev = null;

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            if (prev == p) {
                return curr;
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
            prev = curr;
        }

        return null;
    }

    //The worst-case running time complexities of inorderNext(p):
    // O(n), where n is the number of nodes in the binary tree.
    //iterative inorder traversal of the tree - left subtree -> root -> right subtree
    public Node inorderNext(Node root, Node p) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        boolean found = false;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();

            if (found) {
                return curr;
            }

            if (curr == p) {
                found = true;
            }

            curr = curr.right;
        }

        return null;
    }

    //The worst-case running time complexities of postorderNext(p):
    // O(n), where n is the number of nodes in the binary tree.
    //postorder traversal sequence - left subtree -> right subtree -> root
    public Node postorderNext(Node root, Node p) {
        Stack<Node> stack = new Stack<>();  //init
        Node prev = null;

        if (root == null) {
            return null;
        }

        stack.push(root);

        //traversal loop
        while (!stack.isEmpty()) {
            Node curr = stack.peek();
            //If prev is null (indicating the first iteration) or if curr is the left or right child of prev,
            // it means curr is not fully explored yet.
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == prev) {
                if (curr.right != null) {
                    stack.push(curr.right);     //pushes the root node onto the stack to begin the traversal.
                }
            } else {
                stack.pop();

                if (!stack.isEmpty()) {
                    return stack.peek();
                }
            }

            prev = curr;
        }

        return null;
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        BinaryTreeTraversal btt = new BinaryTreeTraversal();

        Node result1 = btt.preorderNext(root, root.left); // Test preorderNext
        Node result2 = btt.inorderNext(root, root.left); // Test inorderNext
        Node result3 = btt.postorderNext(root, root.left.left); // Test postorderNext

        //display the returned position visited after p
        System.out.println(result1.val);
        System.out.println(result2.val);
        System.out.println(result3.val);
    }

}




