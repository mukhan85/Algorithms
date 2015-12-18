import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by mukhan on 18/12/2015.
 */
public class Main {
    public static void main(String ... args) throws FileNotFoundException {

        Scanner sc = new Scanner(Main.class.getResourceAsStream("input"));
        System.out.println(sc.nextLine());
        sc.close();
    }
}
