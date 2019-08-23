package life.zm.damdemo.damdemo.Dao;
import java.util.*;
 class A{
    public static void main(String[] args)
    {
        for(int i =0;i<10;i++){
            System.out.print(i);
        }
        System.out.println();
        for(int i = 0;i<10;++i){
            System.out.print(i);
        }
    }
    public static Node buildTree(String[] arr)
    {
        Node root=new Node();
        Node root_temp=root;
        for (String str :arr )
        {
            for (int i=0;i<str.length() ;i++ )
            {
                char temp=str.charAt(i);
                boolean find=false;
                for (Node child:root_temp.children )
                {
                    if (child.c==temp)
                    {
                        find=true;
                        child.count++;
                        root_temp=child;
                        break;
                    }
                }
                if (!find)
                {
                    Node add_temp=new Node(temp,1);
                    root_temp.children.add(add_temp);
                    root_temp=add_temp;
                }
            }
            root_temp=root;
        }
        return root;
    }
    public static String[] search(String[] arr,Node root)
    {
        Node root_temp=root;
        String[] res=new String[arr.length];
        int index=0;
        for (String str:arr )
        {
            StringBuilder sb=new StringBuilder();
            boolean not_all_match=false;
            for (int i=0;i<str.length() ;i++ )
            {
                Node res_temp=find(str.charAt(i),root_temp);
                if (res_temp!=null&&res_temp.count>1)
                {
                    sb.append(str.charAt(i)+"");
                    root_temp=res_temp;
                }
                else
                {
                    sb.append(str.charAt(i)+"");
                    res[index++]=sb.toString();
                    not_all_match=true;
                    break;
                }
            }
            if (!not_all_match)
                res[index++]=sb.toString();
            sb=new StringBuilder();
            root_temp=root;
        }
        return res;
    }
    public static Node find(char c,Node root)
    {
        for (Node child:root.children )
        {
            if (child.c==c)
            {
                return child;
            }
        }
        return null;
    }
}
class Node
{
    public char c;
    public int count;
    public List<Node> children=new ArrayList<>();
    public Node(){}
    public Node(char c,int count)
    {
        this.c=c;
        this.count=count;
    }
}