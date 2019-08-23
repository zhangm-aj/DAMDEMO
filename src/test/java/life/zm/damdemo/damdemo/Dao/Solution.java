package life.zm.damdemo.damdemo.Dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i =0;i<n;i++){
            list.add(sc.nextInt());
        }

    }
    public static List<Integer> getModel(ArrayList<ArrayList<Integer>> ans,ArrayList<Integer> list){
        Collections.sort(list);
        for(int i = 0;i<list.size();i++){

        }
        return list;
    }
}
