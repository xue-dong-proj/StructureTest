package nuc.edu;

import nuc.edu.tools.Asserts;

/**
 * @author 薛东
 * @date 2021/5/12 10:28
 */
public class BruteForce {
    public static void main(String[] args) {
        Asserts.test(bruteForce("Hello World","or") == 7);
    }

    public static int bruteForce(String text,String pattern){
        if (text == null || pattern == null){
            return -1;
        }
        char[] textChars = text.toCharArray();
        int tlen = textChars.length;
        char[] patternChars = pattern.toCharArray();
        int plen = patternChars.length;
        if (tlen == 0 || plen == 0 || plen > tlen){
            return -1;
        }
        int pi = 0;
        int ti = 0;
        int tMax = tlen - plen;
        while (pi < plen && ti - pi <= tMax){
            if (textChars[ti] == patternChars[pi]){
                ti++;
                pi++;
            }else {
                ti -= pi - 1;
                pi = 0;
            }
        }
        return pi == plen ? ti - pi : -1;
    }

    public static int bruteForce02(String text,String pattern){
        if (text == null || pattern == null){
            return -1;
        }
        char[] textChars = text.toCharArray();
        int tlen = textChars.length;
        char[] patternChars = pattern.toCharArray();
        int plen = patternChars.length;
        if (tlen == 0 || plen == 0 || plen > tlen){
            return -1;
        }
        int pi = 0;
        int ti = 0;
        while (pi < plen && ti < tlen){
            if (textChars[ti] == patternChars[pi]){
                ti++;
                pi++;
            }else {
                ti -= pi - 1;
                pi = 0;
            }
        }
        return pi == plen ? ti - pi : -1;
    }
}
