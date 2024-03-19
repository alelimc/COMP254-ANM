class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTreeHeight {
    public void printNodeElementAndHeight(Node root) {
        if (root == null) {
            return;
        }

        postorderTraversal(root);
    }

    private int postorderTraversal(Node node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = postorderTraversal(node.left);  //traverses down the left subtree first and then the right subtree
        int rightHeight = postorderTraversal(node.right);

        int height = Math.max(leftHeight, rightHeight) + 1;     //calculates the height of the current node
        System.out.println("Element: " + node.val + ", Height: " + height);

        return height;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        BinaryTreeHeight bth = new BinaryTreeHeight();
        bth.printNodeElementAndHeight(root);
    }
}
