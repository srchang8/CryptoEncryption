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



    public void nextPermutation (int[] nums){

        /*

        1,2,3,4  -  1,2,4,3 - swap end

        1,2,4,3  - find mod, find next biggest after mod, swap mod with next big, reverse everything after mod index

        4,3,2,1 - 1,2,3,4 - reverse all

         */

        int mod = nums.length-2;
        int tail = nums.length-1;

        while (mod >= 0 && nums[mod] >= nums[mod+1]) mod--;

        if (mod == nums.length-2){
            swap(nums, mod, tail);
            return;
        }

        if (mod < 0){
            reverse(nums, 0);
            return;
        }

        while (nums[tail] >= nums[mod]) tail--;

        swap(nums, mod, tail);
        reverse(nums, mod);


    }

    public void reverse(int[] nums, int start){

        int end = nums.length-1;

        while (start < end){
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}











