class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n=s.length();
        int[] count=new int[26];

        for(char ch:s.toCharArray()){
            count[ch-'a']++;
        }

        for(int i=0;i<n;i++){
            int x=target.charAt(i)-'a';

            if(count[x]>0){
                count[x]--;
            }else{
                for(int c=x+1;c<26;c++){
                    if(count[c]>0){
                        count[c]--;

                        StringBuilder ans=new StringBuilder();
                        ans.append(target,0,i);
                        ans.append((char)('a'+c));

                        for(int j=0;j<26;j++){
                            while(count[j]>0){
                                ans.append((char)('a'+j));
                                count[j]--;
                            }
                        }

                        return ans.toString();
                    }
                }

                for(int j=i-1;j>=0;j--){
                    count[target.charAt(j)-'a']++;

                    int prev=target.charAt(j)-'a';

                    for(int c=prev+1;c<26;c++){
                        if(count[c]>0){
                            count[c]--;

                            StringBuilder ans=new StringBuilder();
                            ans.append(target,0,j);
                            ans.append((char)('a'+c));

                            for(int k=0;k<26;k++){
                                while(count[k]>0){
                                    ans.append((char)('a'+k));
                                    count[k]--;
                                }
                            }

                            return ans.toString();
                        }
                    }
                }

                return "";
            }
        }

        for(int i=n-1;i>=0;i--){
            count[target.charAt(i)-'a']++;

            int x=target.charAt(i)-'a';

            for(int c=x+1;c<26;c++){
                if(count[c]>0){
                    count[c]--;

                    StringBuilder ans=new StringBuilder();
                    ans.append(target,0,i);
                    ans.append((char)('a'+c));

                    for(int j=0;j<26;j++){
                        while(count[j]>0){
                            ans.append((char)('a'+j));
                            count[j]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}