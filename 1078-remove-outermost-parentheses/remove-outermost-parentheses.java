class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i) == '('){
                if(!st.isEmpty()){
                    sb.append('(');
                }
                st.add('(');
            }
            else{
                char ele = st.pop();
                if(!st.isEmpty()){
                    sb.append(')');
                }
            }
        }
        return sb.toString();
    }
}