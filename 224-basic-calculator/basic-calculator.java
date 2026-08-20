class Solution {
    public int calculate(String s) {
        Stack<Integer> stack=new Stack<>();

        int result=0;
        int sign=1;

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(Character.isDigit(ch)){
                int num=0;

                while(i<s.length() && Character.isDigit(s.charAt(i))){
                    num=num*10+(s.charAt(i)-'0');
                    i++;
                }
                i--;
                result+=num*sign;
            }
            else if(ch=='+'){
                sign=1;
            }
            else if(ch=='-'){
                sign=-1;
            }
            else if(ch=='('){
                stack.push(result);
                stack.push(sign);
                result=0;
                sign=1;
            }
            else if(ch==')'){
                int pSign=stack.pop();
                int pResult=stack.pop();
                result=pResult+pSign*result;
            }
        }
        return result;
    }
}