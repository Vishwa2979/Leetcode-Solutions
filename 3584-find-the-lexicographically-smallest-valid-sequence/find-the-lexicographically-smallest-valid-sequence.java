class Solution {
    public int[] validSequence(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int[] lastPos=new int[m+1];
        lastPos[m]=n;

        int idx1=n-1;
        for(int j=m-1;j>=0;j--){
            while(idx1>=0 && word1.charAt(idx1)!=word2.charAt(j)){
                idx1--;
            }
            lastPos[j]=idx1;
            if(idx1>=0){
                idx1--;
            }
        }
            int[] result=new int[m];
            boolean changed=false;
            int word1Idx=0;

            for(int j=0;j<m;j++){
                boolean matched=false;

                while (word1Idx < n) {
                if (word1.charAt(word1Idx) == word2.charAt(j)) {
                    result[j] = word1Idx;
                    word1Idx++;
                    matched = true;
                    break;
                } else if (!changed && lastPos[j + 1] > word1Idx) {
                    // Change word1[word1Idx] to word2[j] greedily
                    result[j] = word1Idx;
                    word1Idx++;
                    changed = true;
                    matched = true;
                    break;
                }
                word1Idx++;
            }

            if (!matched) {
                return new int[0];
            }
        }
        return result;
    }
}