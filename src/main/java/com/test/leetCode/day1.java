package com.test.leetCode;

/**
 * @author myDemo
 * @since 2025/7/10
 **/
public class day1 {
    public static void main(String[] args) {

        int[] nums ={2,7,11,15};
        int target = 26;
        int[] ints = twoSum(nums, target);
        System.out.println("["+ints[0]+","+ints[1]+"]");
    }


    /**
     * @param nums   整数数组
     * @param target 整数目标值
     * @return 找出 和为目标值 target  的那 两个 整数 的数组下标。
     */
    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; ++i) {
            for (int j = i + 1; j < length; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}

