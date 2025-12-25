import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        long sum = 0L;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int h : happiness) maxHeap.add(h);

        int decrease = 0;
        while (k-- > 0 && !maxHeap.isEmpty()) {
            int top = maxHeap.poll();
            sum += top;
            sum -= Math.min(decrease, top);
            decrease++;
        }
        return sum;
    }
}