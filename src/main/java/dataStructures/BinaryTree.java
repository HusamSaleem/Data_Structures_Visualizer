package dataStructures;

import node.TreeNode;

/**
 * The Binary Tree Super class
 *
 * @Author: Husam Saleem
 */
public class BinaryTree<E extends Comparable<E>> {
    protected TreeNode<E> root;
    protected int size, capacity;

    public BinaryTree() {
    }

    public BinaryTree(int capacity) {
        this.root = null;
        this.size = 0;
        this.capacity = capacity;
    }

    public void insert(E data) {
        insert(this.root, data);
        this.size++;
    }

    private void insert(TreeNode<E> root, E data) {
        if (root == null) {
            this.root = new TreeNode<>(data);
            return;
        }

        Queue<TreeNode<E>> queue = new Queue<>(this.capacity);
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TreeNode<E> dequeued = queue.dequeue();

            if (dequeued.left == null) {
                dequeued.left = new TreeNode<>(data);
                break;
            } else if (dequeued.right == null) {
                dequeued.right = new TreeNode<>(data);
                break;
            } else {
                queue.enqueue(dequeued.left);
                queue.enqueue(dequeued.right);
            }
        }
    }

    public void delete(E data) {
        if (this.root == null) return;
        delete(this.root, data);
        System.out.println("Here");
        this.size--;
    }

    private void delete(TreeNode<E> root, E data) {
        Queue<TreeNode<E>> queue = new Queue<>(this.getCapacity());
        queue.enqueue(this.root);

        while (!queue.isEmpty()) {
            TreeNode<E> dequeued = queue.dequeue();

            if (dequeued.data.compareTo(data) == 0) {
                TreeNode<E> successor = findRightMostNode(this.root);
                deleteSuccessor(this.root, successor);
                dequeued.data = successor.data;
            } else {
                if (dequeued.left != null) queue.enqueue(dequeued.left);
                if (dequeued.right != null) queue.enqueue(dequeued.right);
            }
        }
    }

    private void deleteSuccessor(TreeNode<E> root, TreeNode<E> successor) {
        Queue<TreeNode<E>> queue = new Queue<>(this.getCapacity());
        queue.enqueue(root);

        if (root == successor) {
            this.root = null;
            return;
        }

        while (!queue.isEmpty()) {
            TreeNode<E> dequeued = queue.dequeue();

            if (dequeued.left == successor) {
                dequeued.left = null;
                return;
            } else if (dequeued.right == successor) {
                dequeued.right = null;
                return;
            }

            if (dequeued.left != null) queue.enqueue(dequeued.left);
            if (dequeued.right != null) queue.enqueue(dequeued.right);
        }
    }

    private TreeNode<E> findRightMostNode(TreeNode<E> root) {
        Queue<TreeNode<E>> queue = new Queue<>(this.getCapacity());
        queue.enqueue(root);

        TreeNode<E> dequeued = null;
        while (!queue.isEmpty()) {
            dequeued = queue.dequeue();

            if (dequeued.left != null) queue.enqueue(dequeued.left);
            if (dequeued.right != null) queue.enqueue(dequeued.right);
        }

        return dequeued;
    }

    public void printLevelTraversal() {
        Queue<TreeNode<E>> queue = new Queue<>(this.capacity);
        StringBuilder treeAsLevelString = new StringBuilder();

        if (root != null) {
            queue.enqueue(root);

            while (!queue.isEmpty()) {
                TreeNode<E> curNode = queue.dequeue();
                if (curNode != null) {
                    treeAsLevelString.append(curNode.data).append(", ");
                }

                if (curNode.left != null)
                    queue.enqueue(curNode.left);
                if (curNode.right != null)
                    queue.enqueue(curNode.right);
            }

            treeAsLevelString.append("\n");
            System.out.println(treeAsLevelString);
        }
    }

    public TreeNode<E> invertTree(TreeNode<E> root) {
        if (root == null) {
            return null;
        }

        TreeNode<E> left = invertTree(root.left);
        TreeNode<E> right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public final int getDepth(TreeNode<E> node) {
        int depth = 0;

        TreeNode<E> curNode = this.root;

        while (curNode != null) {
            if (curNode == node) {
                return depth;
            }

            if (curNode.data.compareTo(node.data) > 0) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
            depth++;
        }

        return depth;
    }

    public final void printInOrder() {
        printInOrderHelper(this.root);
    }

    private void printInOrderHelper(TreeNode<E> root) {
        if (root != null) {
            printInOrderHelper(root.left);
            System.out.println(root.data);
            printInOrderHelper(root.right);
        }
    }

    public final void printPreOrder() {
        printPreOrderHelper(this.root);
    }

    private void printPreOrderHelper(TreeNode<E> root) {
        if (root != null) {
            System.out.println(root.data);
            printInOrderHelper(root.left);
            printInOrderHelper(root.right);
        }
    }

    public final void printPostOrder() {
        printPostOrderHelper(this.root);
    }

    private void printPostOrderHelper(TreeNode<E> root) {
        if (root != null) {
            printInOrderHelper(root.left);
            printInOrderHelper(root.right);
            System.out.println(root.data);
        }
    }

    public final TreeNode<E> getRoot() {
        return this.root;
    }

    public final void clear() {
        this.root = null;
        this.size = 0;
    }

    public final int getSize() {
        return this.size;
    }

    public final int getCapacity() {
        return this.capacity;
    }

    /**
     * Gets the height of the tree
     *
     * @param root
     * @return
     */
    public final int getHeight(TreeNode<E> root) {
        if (root == null)
            return -1;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
