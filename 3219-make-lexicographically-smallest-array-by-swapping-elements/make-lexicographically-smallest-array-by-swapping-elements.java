class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int i = 0;

        while (i < n) {
            int j = i;

            while (j + 1 < n && arr[j + 1][0] - arr[j][0] <= limit) {
                j++;
            }

            int[] values = new int[j - i + 1];
            int[] indexes = new int[j - i + 1];

            for (int k = i; k <= j; k++) {
                values[k - i] = arr[k][0];
                indexes[k - i] = arr[k][1];
            }

            Arrays.sort(indexes);

            for (int k = 0; k < values.length; k++) {
                nums[indexes[k]] = values[k];
            }

            i = j + 1;
        }

        return nums;
    }
}