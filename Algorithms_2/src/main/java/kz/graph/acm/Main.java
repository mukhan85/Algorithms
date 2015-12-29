package kz.graph.acm;

import java.util.*;

/**
 * Created by mmyrzaku on 22/12/2015.
 */
public class Main {
    public static void main(String... args) {
        TreeNode levelTwoLeft = new TreeNode(null, new TreeNode(3), 2);
        TreeNode levelTwoRight = new TreeNode(new TreeNode(3), null, 2);
        TreeNode tree = new TreeNode(levelTwoLeft, levelTwoRight, 1);
        System.out.println(tree.sumNumbers(tree));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this(null, null, val);
    }

    public TreeNode(TreeNode left, TreeNode right, int val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }

        if(isLeaf(root)) {
            return root.val == sum;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        if (isLeaf(root)) {
            result.add(Integer.toString(root.val));
            return result;
        }

        if (root.left != null) {
            createPath(root.left, Integer.toString(root.val), result);
        }

        if (root.right != null) {
            createPath(root.right, Integer.toString(root.val), result);
        }

        return result;
    }

    private void createPath(TreeNode root, String current, List<String> result) {
        current += "->" + Integer.toString(root.val);

        if (isLeaf(root)) {
            result.add(current);
        }

        if (root.left != null) {
            createPath(root.left, current, result);
        }

        if (root.right != null) {
            createPath(root.right, current, result);
        }
    }

    public boolean isSymmetric(TreeNode tree) {
        if(tree == null || isLeaf(tree)) {
            return true;
        }

        if((tree.left == null && tree.right != null) || (tree.left != null && tree.right == null)) {
            return false;
        }

        return isSymmetric(tree.left, tree.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left==null && right==null) return true;
        if(right==null || left==null) return false;

        return left.val==right.val
                && isSymmetric(left.left, right.right)
                && isSymmetric(left.right, right.left);
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public int sumNumbers(TreeNode root) {
        int sum = 0, val = 0;
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            val = node.val;

            if (isLeaf(node)) {
                sum += val;
            }

            if (node.right != null) {
                node.right.val = val * 10 + node.right.val;
                nodeStack.push(node.right);
            }
            if (node.left != null) {
                node.left.val = val * 10 + node.left.val;
                nodeStack.push(node.left);
            }
        }
        return sum;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {//Level by level BFS
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (i == count - 1) { // The right-most node of the current level
                    res.add(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }
}