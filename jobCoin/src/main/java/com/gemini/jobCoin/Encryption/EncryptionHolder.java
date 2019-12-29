package com.gemini.jobCoin.EncryptionHolder;


public class EncryptionHolder {

    //Build chain using inorder and preordered nodes
    //the ordered nodes should not have duplicates
    public TreeNode buildChain(int[] preOrder, int[] inOrder){

        HashMap<Integer, Integer> map = new HashMap();
        for (int i=0; i<inOrder.length; i++){
            map.put(inOrder[i], i);
        }

        Stack<TreeNode> stack = new Stack();
        TreeNode root = new TreeNode(preOrder[0]);
        stack.push(root);

        for (int i=1; i<preOrder.length; i++){

            TreeNode node = new TreeNode(preOrder[i]);

            if ( map.get(preOrder[i]) < map.get(stack.peek().val) ){
                stack.peek().left = node;
            }else{

                TreeNode parent = null;
                while(!stack.isEmpty() && map.get(preOrder[i]) > map.get(stack.peek.val)){
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }

        return root;
    }


    public List<String> fillMissingAccounts(int[] account, int lower, int higher){

        List<String> result = new ArrayList<String>();

        if (account.length == 0){
            result.add(getRanges(lower,higher));
            return result;
        }

        if (lower < account[0]){
            result.add(lower, account[0]-1);
        }

        for (int i=0; i<account.length; i++){
            if (account[i] != account[i+1] && account[i+1] > account[i]+1){
                result.add(getRanges(account[i]+1, account[i+1]-1));
            }
        }

        if (account[account.length-1] < higher){
            result.add(account[account.length-1]+1, higher);
        }

        return result;
    }

    public String getRanges(int start, int end){

        if (start == end){
            return start;
        }
        return String.valueOf(start) + " Account ranges " + String.valueOf(higher);
    }

}
