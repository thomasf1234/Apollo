/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apollo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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
    
    public static boolean compareFiles(String textPath1, String textPath2) throws IOException {
        byte[] f1 = Files.readAllBytes(Paths.get(textPath1));
        byte[] f2 = Files.readAllBytes(Paths.get(textPath2));
        
        return Arrays.equals(f1, f2);
    }
}
