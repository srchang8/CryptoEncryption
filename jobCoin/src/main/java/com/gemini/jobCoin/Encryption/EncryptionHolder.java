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

}


















