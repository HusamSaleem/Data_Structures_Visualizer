package Nodes;

/**
 * Used for AVL and BST
 *
 * @param <E>
 */
public class TreeNode<E> {
    public E data;
    public int bf, height;
    public TreeNode<E> left, right;

    public TreeNode(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.bf = 0;
        this.height = 0;
    }

}
