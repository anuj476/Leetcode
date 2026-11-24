class Solution {
    public int maxLength(int[] nums) {
        int res = 2;
        int left = 0;
        long prod = 1;
        for (int right = 0; right < nums.length; right++) {
            int curr = nums[right];
            while (gcd(prod, curr) > 1) {
                prod /= nums[left];
                left++;
            }
            prod *= curr;
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}