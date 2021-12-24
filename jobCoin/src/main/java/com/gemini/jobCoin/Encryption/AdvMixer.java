package Encryption;

import java.util.Stack;

public class AdvMixer {

    public String minRemove(String s){

        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack();

        for (int i=0; i<s.length(); i++){

            char c = s.charAt(i);

            if (c == '('){
                stack.push(i);
                continue;
            }

            if (c == ')'){

                if (!stack.isEmpty()){
                    stack.pop();
                }else{
                    sb.setCharAt(i, '*');
                }
            }
        }

        while (!stack.isEmpty()){
            sb.setCharAt(stack.pop(), '*');
        }

        return sb.toString().replaceAll("\\*", "");
Q
    }


    public boolean checkSingleRemove(String s){

        int i = 0;
        int j = s.length()-1;

        while (i < j){
            if (s.charAt(i) != s.charAt(j)){
                return checkSingleRemoveHelper(s, i+1, j) || checkSingleRemoveHelper(s, i, j-1);
            };
            i++;
            j--;
        }

        return true;
    }

    public boolean checkSingleRemoveHelper(String s, int start, int end){

        while (start < end){
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    public int[] findViews(int[] heights){

        int[] res = new int[heights.length];
        int count = heights.length-1;

        int max = 0;

        for (int i=heights.length-1; i>=0; i--){
            if (heights[i] > max){
                res[count--] = i;
                max = heights[i];
            }
        }
        return res;
    }
}











