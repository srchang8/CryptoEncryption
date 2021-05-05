package com.gemini.jobCoin.Encryption;


import javax.swing.tree.TreeNode;
import java.util.*;

import com.gemini.jobCoin.domain.AccountNode;

public class EncryptionHolder {

    private Object AccountNode;

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

            TreeNode node = new TreeNode(preOrder[i]) {
                @Override
                public TreeNode getChildAt(int childIndex) {
                    return null;
                }

                @Override
                public int getChildCount() {
                    return 0;
                }

                @Override
                public TreeNode getParent() {
                    return null;
                }

                @Override
                public int getIndex(TreeNode node) {
                    return 0;
                }

                @Override
                public boolean getAllowsChildren() {
                    return false;
                }

                @Override
                public boolean isLeaf() {
                    return false;
                }

                @Override
                public Enumeration children() {
                    return null;
                }
            };

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
            result.add(getRanges(lower, account[0] - 1));
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
            return Integer.toString(start);
        }
        return String.valueOf(start) + " Account ranges " + String.valueOf(end);
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

    private void findBinaryCombination(String s, int left, int i, List<String> list) {
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

        Stack<com.gemini.jobCoin.domain.AccountNode> stack = new Stack();
        List<Integer> result = new ArrayList<Integer>();

        while (!stack.isEmpty() || root != null){

            while (root != null){
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

    public int maxProfit(int[] days){

        if (days.length==0) return 0;
        int profit =0;
        int min=days[0];

        for (int i=1; i<days.length; i++){
            if (days[i] < min){
                min = days[i];
            }else if (days[i] > min ){
                profit = Math.max(profit, days[i] - min);
            }
        }


        return profit;
    }


    public boolean isTrippleIncrease(int[] days){
        
        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        
        for (int i=0; i<days.length; i++){
            
            if (days[i] <= min){
                min = days[i];
            }else if (days[i] <= min2){
                min2 = days[i];
            }else{
                return false;
            }
        }
        
        return false;
    }

    List<List<Integer>> resultingPrice = new ArrayList();
    public List<List<Integer>> findCombinations(int[] days){

        if (days.length == 0) return resultingPrice;
        findCombinationHelper(new ArrayList<Integer>(), days);
        return resultingPrice;

    }

    public void findCombinationHelper(List<Integer> tempList, int[] days){

        if (tempList.size() == days.length){
            resultingPrice.add(new ArrayList(tempList));
            return;
        }

        for (int price : days){

            if (tempList.contains(price)){
                continue;
            }
            tempList.add(price);
            findCombinationHelper(tempList, days);
            tempList.remove(tempList.size()-1);
        }

    }

    public List<Integer> getKthMostFrequentyAccount(int[] accounts, int k){

        List<Integer> result = new ArrayList();
        List<Integer>[] bucket = new List[accounts.length+1];
        HashMap<Integer, Integer> freqMap = new HashMap();
        for (int account : accounts){
            freqMap.put(account, freqMap.getOrDefault(account, 0) + 1);
        }

        for (int key : freqMap.keySet()){
            int frequency = freqMap.get(key);
            if (bucket[frequency] == null){
                bucket[frequency]= new ArrayList();
            }
            bucket[frequency].add(key);
        }

        for (int i=bucket.length-1; i>=0 && result.size() < k; i--){
            if (bucket[i] != null){
                result.addAll(bucket[i]);
            }
        }


        return result;
    }

    static char accountBoard[][] = new char[10][10];
    static boolean visited[][] = new boolean[10][10];

    public boolean accountExist(char[][] board, String account){

        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                if (board[i][j] == account.charAt(0) && accountExistHelper(board, account, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean accountExistHelper(char[][] board, String account, int row, int col, int count){

        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length ||
                board[row][col] != account.charAt(count) || visited[row][col]){
            return false;
        }

        visited[row][col] = true;

        if (accountExistHelper(board, account, row+1, col, count+1) ||
                accountExistHelper(board, account, row-1, col, count+1) ||
                accountExistHelper(board, account, row, col+1, count+1) ||
                accountExistHelper(board, account, row, col-1, count+1)){
            return true;
        }

        visited[row][col] = false;
        return false;
    }


    /*
    // 1. Find initial rotten oranges
//   count fresh oranges
//
//
// 2. BFS traverse(level)
//     check bounds
//     increase minutes
//     find rotten oranges
//     store in Queue
//      minues fresh org


public int orangesRotting(int[][] grid) {


Queue<int[]> queue = new LinkedList();
int freshOrg = 0;
int minutes = 0;

int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

for (int y=0; y<grid.length; y++){
  for (int x=0; x<grid[0].length; x++){
    if (grid[y][x] == 2){
      queue.offer(new int[]{y,x});
    }else if ( grid[y][x] == 1){
      freshOrg++;
    }
  }
}


if (freshOrg == 0) return 0;

while (!queue.isEmpty()){
  int qSize = queue.size();
  minutes++;
  for (int i=0; i<qSize; i++){
    int[] orange = queue.poll();

    for (int[] direction : directions){
      int y = orange[0] + direction[0];
      int x = orange[1] + direction[1];

      if (y<0 || y>=grid.length || x<0 || x>=grid[0].length || grid[y][x] !=1){
        continue;
      }
      grid[y][x] = 2;
      queue.offer(new int[]{y,x});
      freshOrg--;
    }

  }
}











     */


}


















