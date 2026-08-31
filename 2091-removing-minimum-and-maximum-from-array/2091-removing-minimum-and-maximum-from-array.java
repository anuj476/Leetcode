class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0;
        int maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        int l = Math.min(minIdx, maxIdx);
        int r = Math.max(minIdx, maxIdx);
        int removeBothFront = r + 1;
        int removeBothBack = n - l;
        int removeBothSides = (l + 1) + (n - r);
        return Math.min(removeBothFront, Math.min(removeBothBack, removeBothSides));
    }
}