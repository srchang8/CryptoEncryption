package com.gemini.jobCoin.Encryption;


import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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

    /**
     * 0 = open parentheses, 1 = close parentheses
     * every 0 should be paired with 1
     * @param s
     * @param left
     * @param right
     * @param list
     */
    public void findBinaryCombinations(String s, int left, int right, List<String> list){

        if (left == 0 && right == 0){
            list.add(s);
            return;
        }

        if (left > 0){
            findBinaryCombination(s + "0", left-1, right, list);
        }

        if (left > right){
            findBinaryCombination(s + "1", left, right-1, list);
        }
    }

    /**
     * sort large number of buy, sell and pending transactions
     * space complexity o(1)
     * time complexity o(n)
     *
     * buy = 0, pending = 1, sell = 2
     */
    public void sortAccounts(int[] transactions){

        int start = 0;
        int end = transactions.length-1;
        int search = 0;

        while (search <= end){

            if (transactions[search] = 0){
                transactions[search] = transactions[start];
                transactions[start] = 0;
                start++;
                search++;
            }else if (transactions[search] = 2){
                transactions[search] = transactions[end];
                transactions[end] = 2;
                end--;
            }else{
                search++;
            }
        }

    }

    /**
     *
     * find left and right accounts using inorder traversal
     * space complexity o(n)
     * time complexity o(n)
     */
    public List<Integer> searchInorder(AccountNode root){

        Stack<AccountNode> stack = new Stack();
        List<Integer> result = new ArrayList<Integer>();

        while (!stack.isEmpty() || root != null){

            while (root != ull){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }

        return result;
    }

    /**
     * Shuffle accounts
     *
     *
     */
    public List<List<AccountNode>> shuffleAccount (AccountNode root){

        List<List<Integer>> result = new ArrayList();
        if (root == null){
            return result;
        }

        Queue<AccountNode> queue = LinkedList<AccountNode>();
        int level =0;
        queue.add(root);
        boolean reverse = false;

        while (!queue.isEmpty()){

            result.add(new LinkedList<Integer>());
            int currQsize = queue.size();

            for (int i =0; i<currQsize; i++){

                AccountNode node = queue.poll();

                if (reverse){
                    result.get(level).add(0, node.val);
                }else{
                    result.get(level).add(node.val);
                }

                if (node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            level++;
            reverse = !reverse;
        }

        return result;
    }


    //will return -1 if combination does not exist
    public int minAmountCoins(int[] coins, int dollars){

        int[] amountTracker = new int[dollars];

        return minAmountCoinsHelper(coins, dollars, amountTracker);

    }

    public int minAmountCoinsHelper(int[] coins, int subAmount, int[] amountTracker){

        if (subAmount == 0) return 0;
        if (subAmount < 0) return -1;
        if (amountTracker[subAmount-1] != 0) return amountTracker[subAmount-1];

        int min = Integer.MAX_VALUE;

        for (int coin : coins){

            int result = minAmountCoinsHelper(coins, subAmount - coin, amountTracker);
            if (result < min && result >= 0){
                min = 1 + result;
            }
        }

        amountTracker[subAmount-1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return amountTracker[subAmount-1];
    }


    HashMap<String, String> FamilyMap = new HashMap<String,String>();
    List<String> result = new ArrayList<>();

    public List<String> idFamilyCombinationFinder(String digits){

        List<String> result = new ArrayList<>();

        if (digits.length() == 0) return result;

        idFamilyCombinationFinderHelper("", digits);
        return result;
    }

    public void idFamilyCombinationFinderHelper(String combo, String nextDigit){

        if (nextDigit.length() == 0){
            result.add(combo);
            return;
        }

        String digit = nextDigit.substring(0,1);
        String letters = FamilyMap.get(digit);

        for (int i=0; i<letters.length(); i++){

            String letter = letters.substring(i, i+1);
            idFamilyCombinationFinderHelper(combo + letter, nextDigit.substring(1));
        }

    }

    public void cleanGrid(int[][] grid){
        
        boolean[] row = new boolean[grid.length];
        boolean[] col = new boolean[grid[0].length];
        
        for (int i=0; i<row.length; i++){
            for (int j=0; j<col.length; i++){
                 if (grid[i][j] == 0){
                    row[i] = true;
                    col[j] = true;
                 }
            }
            
         for (int i=0; i<row.length; i++){
             if (row[i]) setRow(grid, i);             
         }
         for (int i=0; i<col.length; i++){
            if (col[i]) setCol(grid, i);   
         }
    }
    

     public void setRow(int[][] g, int r){
            for (int i = 0; i<g[0].length; i++){
                g[r][i] = 0;
            }
        }
      
     public void setCol(int[][] g, int c){
            for (int i=0; i<g.length; i++){
                g[i][c] = 0;
            }
     }
        
     
     public List<Integer> inOrderFinder(Treenode node){
         
      List<Integer> result = new ArrayList();
      if (node == null) return result;
         
         Stack<Integer> stack = new Stack();
                 
         while (!stack.isEmpty() || node != null){
             while (node != null){
                stack.push(node);
                node = node.left;
             }
             
             node = stack.pop();
             result.add(node);
             node = node.right;
         }
       
        return result
     }
}


















