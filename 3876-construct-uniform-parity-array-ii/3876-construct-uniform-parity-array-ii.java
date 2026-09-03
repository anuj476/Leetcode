class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1[0];
        boolean hasOdd = false;
        for(int i : nums1){
            if(i < n){
                n = i;
            }
            if((i & 1) == 1){
                hasOdd = true;
            }
        }
        if((n & 1) == 1){
            return true;
        }
        return !hasOdd;
    }
}