class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int cnt = 0; int n = grid.length; int m = grid[0].length;
        for(int i=0; i<n-2; i++){
            for(int j=0; j<m-2; j++){
                if(solve(grid, i, j)){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public boolean solve(int grid[][], int i, int j){
        boolean check[] = new boolean[10];
        for(int p=i; p<i+3; p++){
            for(int q=j; q<j+3; q++){
                int num = grid[p][q];
                if(num < 1 || num > 9 || check[num]){
                    return false;
                }
                check[num] = true;
            }
        }
        int sum = grid[i][j] + grid[i][j+1] + grid[i][j+2];
        for(int p=0; p<3; p++){
            if(grid[i+p][j] + grid[i+p][j+1] + grid[i+p][j+2] != sum){
                return false;
            }
            if(grid[i][j+p] + grid[i+1][j+p] + grid[i+2][j+p] != sum){
                return false;
            }
        }
        if(grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2] != sum){
            return false;
        }
        if(grid[i][j+2] + grid[i+1][j+1] + grid[i+2][j] != sum){
            return false;
        }
        return true;
    }
}
