package acm;

import java.util.Scanner;

public class Main {
    private static final int size = 5;

    public static void main(String[] args) {
        Scanner input = new Scanner(Main.class.getClassLoader().getResourceAsStream("input"));
//      Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            int[] data = readData(input);
            if (isFinalInput(data)) {
                return;
            }
            solve(data);
        }
        input.close();
    }

    private static boolean isFinalInput(int[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static int[] readData(Scanner input) {
        String[] line = input.nextLine().split("\\s");
        int[] data = new int[size];

        for (int i = 0; i < line.length; i++) {
            data[i] = Integer.parseInt(line[i]);
        }
        return data;
    }

    private static void solve(int[] data) {

    }

    enum op {add, subtract, multiply}
}