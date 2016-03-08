package acm;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(Main.class.getClassLoader().getResourceAsStream("input"));
//      Scanner input = new Scanner(System.in);

        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(16, new TreeNode(12), new TreeNode(30));
        TreeNode root = new TreeNode(10, left, right);
        System.out.println(minDiff(root));

        input.close();
    }

    private static int minDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        TreeNode current = root;

        while (current != null) {
            int leftNode = getLeftClosest(root.left);
            if (Math.abs(leftNode - root.data) < min) {
                min = Math.abs(leftNode - root.data);
            }
            current = current.left;
        }

        current = root;
        while (current != null) {
            int rightNode = getRightClosest(root.right);
            if (Math.abs(root.data - rightNode) < min) {
                min = Math.abs(root.data - rightNode);
            }
            current = current.right;
        }

        min = Math.min(getMinFromLeft(root), getMinFromRight(root));

        return min;
    }

    private static int getRightClosest(TreeNode right) {
        TreeNode pointer = right;
        int result = pointer.data;
        while (pointer != null) {
            pointer = pointer.left;
            if (pointer != null) {
                result = pointer.data;
            }
        }

        return result;
    }

    private static int getLeftClosest(TreeNode left) {
        TreeNode pointer = left;
        int result = pointer.data;
        while (pointer != null) {
            pointer = pointer.right;
            if (pointer != null) {
                result = pointer.data;
            }
        }

        return result;
    }

    private static void runServerFarmTest() {
        int[] jobs = {15, 30, 15, 5, 10};
        int numServers = 3;
        System.out.println(serverFarm(jobs, numServers));
    }

    public static int[][] serverFarm(int[] jobs, int servers) {
        return null;
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this(data, null, null);
    }

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "{" + data + ", left=" + left + ", right=" + right +"}";
    }
}