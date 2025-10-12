class Solution {
    public void moveZeroes(int[] nums) {
        int n=nums.length;
        // if(n<2){
        //     return nums;
        // }
        int [] arr = new int[n];
        int k=0;
        for(int i=0;i<n;i++){
            if(nums[i]!=0){
                arr[k]=nums[i];
                k++;
            }
        }
        for(int i=k;i<n;i++){
            arr[i]=0;
        }
        for(int i=0;i<n;i++){
            nums[i]=arr[i];
        }
        
    }
}