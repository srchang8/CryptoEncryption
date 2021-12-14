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


}
