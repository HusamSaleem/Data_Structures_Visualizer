package DataStructures;

import DataStructuresInterfaces.BSTInterface;
import Nodes.TreeNode;

/**
 * @Author: Husam Saleem
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements BSTInterface<E> {
    public BinarySearchTree(int capacity) {
        super(capacity);
    }

    public BinarySearchTree(BinarySearchTree<E> copy) {
        this.size = copy.size;
        this.capacity = copy.capacity;
        this.root = copy.root;
    }

    /**
     * O(logn) run-time
     * Recursive method using the insertHelper method as a helper method
     */
    @Override
    public void insert(E data) {
        if (search(data))
            return;
        this.root = insert(this.root, data);
        this.size++;
    }

    private TreeNode<E> insert(TreeNode<E> root, E data) {
        if (root == null) {
            root = new TreeNode<E>(data);
            return root;
        }

        if (root.data.compareTo(data) > 0) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }

        return root;
    }

    /**
     * O(logn) run-time
     * 3 Cases :
     * Case 1: No Children -> Just delete the node then
     * Case 2: 1 Child -> Connect the parent node to the child that is alive
     * Case 3: 2 Children -> Find the in-order successor, then replace the node that needs to be deleted with it, then delete the in-order successor
     */
    @Override
    public void delete(E data) {
        if (root == null || !this.search(data))
            return;

        TreeNode<E> curNode = this.root;
        TreeNode<E> prevNode = null;

        /** First find the node we want to delete **/
        while (curNode != null) {
            if (curNode.data.compareTo(data) == 0)
                break;

            prevNode = curNode;
            if (curNode.data.compareTo(data) > 0) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }

        // Case 1
        if (curNode.right == null && curNode.left == null) {
            // Check if its the root
            if (this.root == curNode) {
                this.root = null;
            } else {
                if (prevNode.left == curNode)
                    prevNode.left = null;
                else
                    prevNode.right = null;
            }
            this.size--;
        }
        // Case 3: 2 child nodes
        else if (curNode.right != null && curNode.left != null) {
            TreeNode<E> successor = findInOrderSuccessor(curNode.right);

            delete(successor.data);
            // Root case
            if (this.root == curNode) {
                this.root.data = successor.data;
            } else {
                if (prevNode.right == curNode) {
                    prevNode.right.data = successor.data;
                } else {
                    prevNode.left.data = successor.data;
                }
            }
            this.size--;
        }
        // Case 2: 1 child node
        else if (curNode.right != null || curNode.left != null) {
            // Check root case
            if (this.root == curNode) {
                if (curNode.right != null) {
                    this.root = curNode.right;
                } else {
                    this.root = curNode.left;
                }
            } else {
                if (curNode.right != null) {
                    if (prevNode.right == curNode) {
                        prevNode.right = curNode.right;
                    } else {
                        prevNode.left = curNode.right;
                    }
                } else if (curNode.left != null) {
                    if (prevNode.right == curNode) {
                        prevNode.right = curNode.left;
                    } else {
                        prevNode.left = curNode.left;
                    }
                }
            }

            this.size--;
        }
    }

    /**
     * Used for the delete method
     */
    protected final TreeNode<E> findInOrderSuccessor(TreeNode<E> node) {
        if (node.left != null) {
            return findInOrderSuccessor(node.left);
        } else {
            return node;
        }
    }

    /**
     * O(logn) run-time
     * Recursively traverses through the tree
     */
    @Override
    public final boolean search(E data) {
        return searchHelper(this.root, data);
    }

    private boolean searchHelper(TreeNode<E> root, E data) {
        if (root == null) {
            return false;
        }

        if (root.data.compareTo(data) == 0) {
            return true;
        }

        if (root.data.compareTo(data) > 0) {
            return searchHelper(root.left, data);
        } else {
            return searchHelper(root.right, data);
        }
    }
}