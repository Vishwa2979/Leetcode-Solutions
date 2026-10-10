class Solution {
    public long minSumSquareDiff(int[] nums1,int[] nums2,int k1,int k2) {
        long k=(long)k1+k2;
        int n=nums1.length;
        long[] a=new long[n];
        long max=0,sum=0;
        for(int i=0;i<n;i++){
            a[i]=Math.abs((long)nums1[i]-nums2[i]);
            max=Math.max(max,a[i]);
            sum+=a[i];
        }
        if(sum<=k)return 0;
        long l=0,r=max;
        while(l<r){
            long m=(l+r)/2;
            long need=0;
            for(long x:a){
                if(x>m)need+=x-m;
                if(need>k)break;
            }
            if(need<=k)r=m;
            else l=m+1;
        }
        long ans=0,used=0;
        for(long x:a){
            long y=Math.min(x,l);
            ans+=y*y;
            if(x>l)used+=x-l;
        }
        long extra=k-used;
        for(long x:a){
            if(extra==0)break;
            if(x>=l&&l>0){
                ans-=l*l;
                ans+=(l-1)*(l-1);
                extra--;
            }
        }
        return ans;
    }
}