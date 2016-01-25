package kz.graph.acm;

import java.util.*;

/**
 * Created by mmyrzaku on 22/12/2015.
 */
public class Main {

    public static void main(String... args) {
        Scanner input = new Scanner(Main.class.getClassLoader().getResourceAsStream("input"));
//      Scanner input = new Scanner(System.in);


        input.close();
    }

    /**
     *  == Pseudo code ==
     * bool solve(configuration conf) {
     *     if(no more choices) { // Base case.
     *         return conf is goal state)
     *     }
     *
     *     for(each available choice) {
     *         try one choice c;
     *         ok = solve(conf with choice c made);
     *         if(ok) {
     *             return true;
     *         } else {
     *             unmake choice c;
     *         }
     *     }
     *
     *     return false;
     * }
     */
}