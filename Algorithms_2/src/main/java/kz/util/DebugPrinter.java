package kz.util;

import java.util.Arrays;

/**
 * Created by mmyrzaku on 22/01/2016.
 */
public class DebugPrinter {
    public static String printTwoDimIntArray(int[][] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(Arrays.toString(data[i]) + " ");
        }
        return sb.toString();
    }

    public static <T>String printIterable(Iterable<T> collection) {
        StringBuilder sb = new StringBuilder();
        for (T eachElem : collection) {
            sb.append(eachElem.toString() + " ");
        }
        return sb.toString();
    }
}
