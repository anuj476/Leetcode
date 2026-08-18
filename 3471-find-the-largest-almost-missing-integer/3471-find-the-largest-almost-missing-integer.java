class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] subarr = new int[51];
        for(int i = 0; i<= n - k; i++){
            boolean[] seenarr = new boolean[51];
            for(int j = i; j < i + k; j++){
                int num = nums[j];
                if(!seenarr[num]){
                    seenarr[num]= true;
                    subarr[num]++;
                }
            }
        }

        int ans = -1;
        for(int num = 0; num <= 50; num++){
            if(subarr[num] == 1){
                ans = Math.max(ans, num);
            }
        }
        return ans;
    }
}