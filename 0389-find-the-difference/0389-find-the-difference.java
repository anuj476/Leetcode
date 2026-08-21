class Solution {
    public char findTheDifference(String s, String t) {
        int c = 0;
        for(char ch : (s + t).toCharArray()) c ^= ch;
        return (char) c;
    }
}