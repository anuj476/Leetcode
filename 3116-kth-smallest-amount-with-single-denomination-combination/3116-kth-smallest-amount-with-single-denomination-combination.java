class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        long left = 1;
        long right = (long) minCoin * k;
        long ans = right;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (countAmounts(coins, mid) >= k) {
                ans = mid;
                right = mid - 1; 
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    private long countAmounts(int[] coins, long target) {
        int n = coins.length;
        long count = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            long currentLcm = 1;
            int bitsCount = 0;
            boolean overflow = false;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bitsCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > target) {
                        overflow = true;
                        break;
                    }
                }
            }
            if (!overflow) {
                if (bitsCount % 2 == 1) {
                    count += target / currentLcm;
                } else {
                    count -= target / currentLcm;
                }
            }
        }
        return count;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    private long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }
}