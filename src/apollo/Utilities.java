/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

/**
 *
 * @author ad
 */
public class Utilities {

    public static boolean contains(final int[] array, final int v) {
        for (final int e : array) {
            if (e == v) {
                return true;
            }
        }

        return false;
    }
}
