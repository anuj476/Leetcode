class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int i = n - 1; i >= 0; i--) {
            int[] tempFreq = freq.clone();
            boolean possiblePrefix = true; 
            for (int k = 0; k < i; k++) {
                int charIdx = target.charAt(k) - 'a';
                if (tempFreq[charIdx] <= 0) {
                    possiblePrefix = false;
                    break;
                }
                tempFreq[charIdx]--;
            }
            if (!possiblePrefix) continue;
            int targetChar = target.charAt(i) - 'a';
            int greaterChar = -1;
            for (int j = targetChar + 1; j < 26; j++) {
                if (tempFreq[j] > 0) {
                    greaterChar = j;
                    break;
                }
            }
            if (greaterChar != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i);
                sb.append((char) ('a' + greaterChar));
                tempFreq[greaterChar]--;
                for (int j = 0; j < 26; j++) {
                    while (tempFreq[j] > 0) {
                        sb.append((char) ('a' + j));
                        tempFreq[j]--;
                    }
                }
                return sb.toString();
            }
        }
        return "";
    }
}