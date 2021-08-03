package nuc.edu;

import nuc.edu.tools.Asserts;

/**
 * @author 薛东
 * @date 2021/5/12 10:49
 */
public class BruteForce02 {
    public static void main(String[] args) {
        Asserts.test(bruteForce("Hello World","or") == 7);
    }

    public static int bruteForce(String text,String pattern){
        if (text == null || pattern == null){
            return -1;
        }
        char[] textChar = text.toCharArray();
        int tlen = textChar.length;
        char[] patternChar = pattern.toCharArray();
        int plen = patternChar.length;
        if (tlen == 0 || plen == 0 || plen > tlen){
            return -1;
        }

        for (int ti = 0; ti < tlen; ti++){
            int pi = 0;
            for (; pi < plen; pi++){
                if (textChar[ti + pi] != patternChar[pi]){
                    break;
                }
            }
            if (pi == plen){
                return ti;
            }
        }
        return -1;
    }
}
