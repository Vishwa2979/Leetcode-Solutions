class Solution {
    List<String> ans=new ArrayList<>();

    public List<String> removeInvalidParentheses(String s) {
        int l=0,r=0;

        for(char c:s.toCharArray()){
            if(c=='(') l++;
            else if(c==')'){
                if(l>0) l--;
                else r++;
            }
        }

        solve(s,0,l,r);
        return ans;
    }

    void solve(String s,int start,int l,int r){
        if(l==0&&r==0){
            if(valid(s)&&!ans.contains(s)) ans.add(s);
            return;
        }

        for(int i=start;i<s.length();i++){
            if(i>start&&s.charAt(i)==s.charAt(i-1)) continue;

            if(r>0&&s.charAt(i)==')'){
                solve(s.substring(0,i)+s.substring(i+1),i,l,r-1);
            }

            if(l>0&&s.charAt(i)=='('){
                solve(s.substring(0,i)+s.substring(i+1),i,l-1,r);
            }
        }
    }

    boolean valid(String s){
        int c=0;

        for(char x:s.toCharArray()){
            if(x=='(') c++;
            else if(x==')'){
                c--;
                if(c<0) return false;
            }
        }

        return c==0;
    }
}