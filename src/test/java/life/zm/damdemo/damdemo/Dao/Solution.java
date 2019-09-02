package life.zm.damdemo.damdemo.Dao;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static  void main(String[] args){
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        wordDict.add("le");
        wordDict.add("et");
        System.out.println(allString(s,wordDict));
    }
    public static List<String> allString(String s, List<String> wordDict){
        List<String> ans = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        if(s.length()<1){return ans;}
        for(int i = 0;i<s.length();++i){
            for(int j = 0;j<=i;++j){
                if(wordDict.contains(s.substring(j,i+1))){
                    dp[j][i] = true;
                }
            }
        }
        findList(ans,dp,"",s,0,0);
        return ans;
    }
    public static void findList(List<String> ans,boolean[][] dp,String temp,String s,int left,int end){
        if(end == dp.length+1&&dp[left+1][end]==true){
            ans.add(temp + " "+s.substring(left+1,end+1));
            return;
        }
        for(int i = end;i<s.length();i++){
            if(dp[left][i]==true){
                findList(ans,dp," "+ s.substring(left,i+1),s,i+1,i+1);

            }
        }
    }
    public static boolean isHas(String s, List<String> wordDict){
        //除了dp[0]，其他的表示前面个是否能找到
        boolean[] dp = new boolean[s.length()+1];
        //巧妙的处理了第一个值的复制问题
        dp[0] = true;
        for(int i = 1;i<dp.length;++i){
            for(int j = 0;j<i;++j){
                if(dp[j]&&wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                }

            }
        }
        return dp[s.length()];
    }
    public static int getOne(int[] arr){
        if(arr.length<1){return 0;}
        if(arr.length==1){return arr[0];}
        int ans = 0;
        for(int i = 0;i<arr.length;++i){
            ans = ans^arr[i];
        }
        return  ans;
    }
    public static int getCandy(int[] arr){
        int[] ans = new int[arr.length];
        if(arr.length<1){return 0;}
        if(arr.length == 1){return 1;}
        ans[0] = 1;
        int count = 0;
        for(int i = 0;i<arr.length-1;i++){
            if(arr[i+1]>arr[i]){
                ans[i+1] = ans[i]+1;
            }else{
                ans[i+1] = 1;
            }
        }
        for(int i = arr.length-1;i>0;--i){
            if(arr[i-1]>arr[i]&&ans[i-1]<=ans[i]){
                ans[i-1] = ans[i] + 1;
            }
        }
        for(int i = 0;i<arr.length;i++){

            count += ans[i];
        }
        return count;
    }
    public static String getShort(String s){

        return s;
    }

    public static int maxProfit(int[] prices){
       int minIndex = 0;
       int[] arr1 = new int[prices.length-1];
       arr1[0] = prices[1]-prices[0];
       for(int i = 1;i<arr1.length;i++){
           if(prices[i]<prices[minIndex]) {
               minIndex = i;

           }
           arr1[i] = Math.max(arr1[i-1],prices[i+1]-prices[minIndex]);

       }
       return arr1[prices.length-2];
   }
   public static int maxProfit1(int[] prices){
        int count = 0;
        for(int i = 0;i<prices.length-1;i++){
            if(prices[i+1]>prices[i]){
                count += prices[i+1]-prices[i];
            }
        }
        return count;
   }
   public static boolean isHui(String s){
        if(s.length()<=0){
            return false;
        }
        int left = 0;
        int end = s.length()-1;
        while(left<=end){
            if(s.charAt(left)<'A'||s.charAt(left)>'z'||(s.charAt(left)>'Z')&&s.charAt(left)<'a'){
                left++;
                continue;
            }
            if(s.charAt(end)<'A'||s.charAt(end)>'z'||(s.charAt(end)>'Z')&&s.charAt(end)<'a'){
                end--;
                continue;
            }
            if(left<=end&&(s.charAt(left)==s.charAt(end)||s.charAt(left)==s.charAt(end)+32||s.charAt(left)==s.charAt(end)-32)){
                left++;
                end--;
            }else{
                return false;
            }
        }
        return true;
   }
}