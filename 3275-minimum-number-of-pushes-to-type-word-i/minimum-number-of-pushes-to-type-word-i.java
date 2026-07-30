class Solution {
    public int minimumPushes(String word) {
        int n=word.length();
        int totalPushes=0;

        if(n<=8){
            return n*1;
        } else if(n<=16){
            return 8*1 + (n-8)*2;
        } else if(n<=24){
            return 8*1 + 8*2 + (n-16)*3;
        } else{
            return  8*1 + 8*2 + 8*3 + (n-24)*4;
        }
    }
}