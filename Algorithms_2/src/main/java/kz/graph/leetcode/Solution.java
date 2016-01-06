package kz.graph.leetcode;

/**
 * Created by mukhan on 24/12/2015.
 */
public class Solution {
    public static void main(String... args) {
        System.out.println(coolString("aAaAaAa"));
    }

    static boolean coolString(String inputString) {

        class Helper {
            boolean isLowercase(char symbol) {
                if ('a' <= symbol && symbol <= 'z') {
                    return true;
                }
                return false;
            }

            boolean isUppercase(char symbol) {
                if ('A' <= symbol && symbol <= 'Z') {
                    return true;
                }
                return false;
            }
        }

        Helper h = new Helper();
        for (int i = 1; i < inputString.length(); i++) {
            int current = i;
            int prev = i - 1;
            if(h.isLowercase(inputString.charAt(prev)) && h.isLowercase(inputString.charAt(current))) {
                System.out.printf("consequitive lowercases: (%d, %d) = %s, %s\n", prev, current, inputString.charAt(prev), inputString.charAt(current));
                return false;
            }

            if(h.isUppercase(inputString.charAt(prev)) && h.isUppercase(inputString.charAt(current))) {
                System.out.printf("consequitive Uppercase: (%d, %d) = %s, %s\n", prev, current, inputString.charAt(prev), inputString.charAt(current));
                return false;
            }
        }
        return true;
    }
}
