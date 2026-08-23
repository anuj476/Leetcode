class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sum = 0;
        int quest  = 0;
        for(int i = 0; i < n; i++){
            char c = num.charAt(i);
            int sign = (i < n / 2) ? 1 : - 1;
            if(c == '?'){
                quest += sign;
            }else{
                sum += sign * (c - '0');
            }
        }
        if((quest & 1) != 0){
            return true;
        }
        return sum != -quest * 9 / 2;
    }
}