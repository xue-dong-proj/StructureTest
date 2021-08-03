package nuc.edu;

import nuc.edu.tools.Asserts;

/**
 * @author 薛东
 * @date 2021/5/12 12:44
 */
public class KMP {
    public static void main(String[] args) {
        Asserts.test(kmp("Hello World","or") == 7);
    }

    public static int kmp(String text,String pattern){
        if (text == null || pattern == null){
            return -1;
        }
        char[] textChar = text.toCharArray();
        int tlen = textChar.length;
        char[] partternChar = pattern.toCharArray();
        int plen = partternChar.length;
        if (tlen == 0 || plen == 0 || plen > tlen){
            return -1;
        }
        int[] next = next(pattern);
        int pi = 0;
        int ti = 0;
        int tMax = tlen - plen;
        while (pi < plen && ti - pi <= tMax){
            if (pi < 0 || textChar[ti] == partternChar[pi]){
                ti++;
                pi++;
            }else {
                pi = next[pi];
            }
        }
        return pi == plen ? ti - pi : -1;
    }

    public static int[] next(String pattern){
        int len = pattern.length();
        int[] next = new int[len];
        int i = 0;
        int n = next[0] = -1;
        int iMax = len - 1;
        while (i < iMax){
            if (n < 0 || pattern.charAt(i) == pattern.charAt(n)){
                i++;
                n++;
                if (pattern.charAt(i) == pattern.charAt(n)){
                    next[i] = next[n];
                }else {
                    n = next[n];
                }
            }
        }
        return next;
    }
}
