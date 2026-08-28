class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }        
        int oddCount = 0;
        char mid = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                oddCount++;
                mid = (char) ('a' + i);
            }
            cnt[i] /= 2;
        }      
        if (oddCount > 1) {
            return "";
        }
        int half = n / 2;
        int[] avail = cnt.clone();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < half; i++) {
            int c = target.charAt(i) - 'a';
            if (avail[c] > 0) {
                sb.append((char) ('a' + c));
                avail[c]--;
            } else {
                break;
            }
        }
        if (sb.length() == half) {
            String cand = build(sb.toString(), mid);
            if (cand.compareTo(target) > 0) {
                return cand;
            }
        }
        while (sb.length() >= 0) {
            int idx = sb.length();
            if (idx < half) {
                int startChar = target.charAt(idx) - 'a' + 1;
                for (int c = startChar; c < 26; c++) {
                    if (avail[c] > 0) {
                        sb.append((char) ('a' + c));
                        avail[c]--;
                        for (int k = 0; k < 26; k++) {
                            while (avail[k] > 0) {
                                sb.append((char) ('a' + k));
                                avail[k]--;
                            }
                        }
                        return build(sb.toString(), mid);
                    }
                }
            }
            if (sb.length() == 0) {
                break;
            }

            char last = sb.charAt(sb.length() - 1);
            avail[last - 'a']++;
            sb.deleteCharAt(sb.length() - 1);
        }

        return "";
    }
    private String build(String left, char mid) {
        StringBuilder res = new StringBuilder(left);
        if (mid != 0) {
            res.append(mid);
        }
        for (int i = left.length() - 1; i >= 0; i--) {
            res.append(left.charAt(i));
        }
        return res.toString();
    }
}