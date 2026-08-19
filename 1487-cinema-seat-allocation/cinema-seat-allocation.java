import java.util.*;

class Solution{
    public int maxNumberOfFamilies(int n,int[][] reservedSeats){

        Arrays.sort(reservedSeats,(a,b)->a[0]-b[0]);

        int ans=0;
        int i=0;

        while(i<reservedSeats.length){

            int row=reservedSeats[i][0];

            boolean left=true;
            boolean middle=true;
            boolean right=true;

            while(i<reservedSeats.length&&reservedSeats[i][0]==row){

                int seat=reservedSeats[i][1];

                if(seat>=2&&seat<=5){
                    left=false;
                }

                if(seat>=4&&seat<=7){
                    middle=false;
                }

                if(seat>=6&&seat<=9){
                    right=false;
                }

                i++;
            }

            if(left&&right){
                ans+=2;
            }else if(left||middle||right){
                ans++;
            }
        }

        ans+=(n-getRows(reservedSeats))*2;

        return ans;
    }

    public int getRows(int[][] reservedSeats){

        if(reservedSeats.length==0){
            return 0;
        }

        int count=1;

        for(int i=1;i<reservedSeats.length;i++){
            if(reservedSeats[i][0]!=reservedSeats[i-1][0]){
                count++;
            }
        }

        return count;
    }
}