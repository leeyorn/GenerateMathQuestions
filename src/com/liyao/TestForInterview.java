package com.liyao;

public class TestForInterview {
    public static void main(String[] args) {
        String a = "sadf asdfsd asdf ";
        String[] split = a.split(" ");
        String lastWord = split[split.length - 1];
        if (a.lastIndexOf(" ") == a.length() - 1){
            System.out.println(0);
        }else{
            System.out.println(lastWord.length());
        }
a.intern();

    }


    public int lengthOfLastWord(final String a)
    {
        int len = 0;

        /* String a is 'final'-- can not be modified
           So, create a copy and trim the spaces from
           both sides */
        String x = a.trim();

        for (int i=0; i<x.length(); i++)
        {
            if (x.charAt(i)==' ')
                len = 0;
            else
                len++;
        }

        return len;
    }
}
