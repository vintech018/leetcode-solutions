class Solution {

    public void quickSortHelper(int[] nums, int low, int high) {
        if (low < high) {

            // 🔑 Randomized pivot to avoid TLE
            int randomIndex = low + (int)(Math.random() * (high - low + 1));
            swap(nums, low, randomIndex);

            int pIndex = partition(nums, low, high);
            quickSortHelper(nums, low, pIndex - 1);
            quickSortHelper(nums, pIndex + 1, high);
        }
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        int i = low;
        int j = high;

        while (i < j) {
            while (i <= high - 1 && nums[i] <= pivot) i++;
            while (j >= low + 1 && nums[j] > pivot) j--;

            if (i < j) {
                swap(nums, i, j);
            }
        }

        swap(nums, low, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] sortArray(int[] nums) {
        quickSortHelper(nums, 0, nums.length - 1);
        return nums;
    }
}
