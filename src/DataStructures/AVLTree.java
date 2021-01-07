package DataStructures;

import Nodes.TreeNode;

/**
 * @Author: Husam Saleem
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {
    public AVLTree(int capacity) {
        super(capacity);
    }

    /**
     * Inserts into tree
     */
    @Override
    public void insert(E data) {
        if (search(data)) {
            return;
        }

        this.root = insert(this.root, data);
        this.size++;
    }

    private TreeNode<E> insert(TreeNode<E> root, E data) {
        if (root == null) {
            return new TreeNode<E>(data);
        }

        if (root.data.compareTo(data) > 0)
            root.left = insert(root.left, data);
        else
            root.right = insert(root.right, data);

        update(root);

        return balance(root);
    }

    /**
     * Checks to see whether or not a subtree needs to be rotated/balanced
     */
    private TreeNode<E> balance(TreeNode<E> node) {
        /** Case 1: Left heavy **/
        if (node.bf == 2) {
            if (node.left.bf >= 0)
                return rotateRight(node);
            else
                return rotateLeftRight(node);
        }
        /** Right Heavy **/
        else if (node.bf == -2) {
            if (node.right.bf <= 0)
                return rotateLeft(node);
            else
                return rotateRightLeft(node);
        }

        return node; // If the balance factor is either -1,0,1
    }

    /**
     * Updates a node's balance factor and height
     *
     * @param node
     */
    private void update(TreeNode<E> node) {
        node.bf = calculateBF(node);
        node.height = getHeight(node);
    }

    /**
     * Recursively deletes a value from the tree and balances after
     */
    @Override
    public void delete(E data) {
        if (search(data)) {
            this.root = delete(root, data);
            this.size--;
        }
    }

    /**
     * Recursive delete method
     */
    private TreeNode<E> delete(TreeNode<E> node, E data) {
        if (node == null) return null;

        if (node.data.compareTo(data) > 0) {
            node.left = delete(node.left, data);

        } else if (node.data.compareTo(data) < 0) {
            node.right = delete(node.right, data);

        } else {
            if (node.left == null) {
                return node.right;

            } else if (node.right == null) {
                return node.left;

            } else {
                if (node.left.height > node.right.height) {
                    // Swap the value of the successor into the node.
                    E successorValue = findMax(node.left);
                    node.data = successorValue;
                    node.left = delete(node.left, successorValue);
                } else {
                    // Swap the value of the successor into the node.
                    E successorValue = findInOrderSuccessor(node.right).data;
                    node.data = successorValue;
                    node.right = delete(node.right, successorValue);
                }
            }
        }

        // Update balance factor and height values.
        update(node);
        // Re-balance tree.
        return balance(node);
    }

    // Helper method to find the rightmost node (which has the largest value)
    private E findMax(TreeNode<E> node) {
        while (node.right != null) node = node.right;
        return node.data;
    }

    /**
     * ROTATION METHODS
     **/
    private TreeNode<E> rotateRight(TreeNode<E> node) {
        TreeNode<E> newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;

        update(node);
        update(newParent);
        return newParent;
    }

    private TreeNode<E> rotateLeft(TreeNode<E> node) {
        TreeNode<E> newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;

        update(node);
        update(newParent);
        return newParent;
    }

    private TreeNode<E> rotateRightLeft(TreeNode<E> node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    private TreeNode<E> rotateLeftRight(TreeNode<E> node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    /**
     * Calculates the balance factor of node
     * Left tree height - right tree height = balance factor
     */
    private int calculateBF(TreeNode<E> node) {
        return getHeight(node.left) - getHeight(node.right);
    }
}
