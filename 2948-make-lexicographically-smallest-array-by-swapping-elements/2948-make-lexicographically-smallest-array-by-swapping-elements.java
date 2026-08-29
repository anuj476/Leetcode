class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i][0] = nums[i];
            paired[i][1] = i;
        }        
        Arrays.sort(paired, (a, b) -> Integer.compare(a[0], b[0])); 
        int[] result = new int[n];
        int i = 0;
        while (i < n) {
            int j = i;
            while (j + 1 < n && paired[j + 1][0] - paired[j][0] <= limit) {
                j++;
            }
            int groupSize = j - i + 1;
            int[] indices = new int[groupSize];
            for (int k = 0; k < groupSize; k++) {
                indices[k] = paired[i + k][1];
            }
                        Arrays.sort(indices);
                        for (int k = 0; k < groupSize; k++) {
                result[indices[k]] = paired[i + k][0];
            }        
            i = j + 1;
        }
        return result;
    }
}