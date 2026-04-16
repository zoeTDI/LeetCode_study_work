package org.example.section1_10;

import java.util.Arrays;
import java.util.HashMap;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode generate(int[] nums) {
        ListNode result = new ListNode();
        ListNode current = result;
        if (nums.length == 0) return result;

        for (int i = 0; i < nums.length - 1; i++) {
            current.val = nums[i];
            current.next = new ListNode(nums[i + 1]);
            current = current.next;
        }
        return result;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}

public class Solution {
    // https://leetcode.cn/problems/two-sum/
    public void twoSumTest() {
        // eg1
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result));
        // eg2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        result = twoSum(nums2, target2);
        System.out.println(Arrays.toString(result));
        // eg3
        int[] nums3 = {3, 3};
        int target3 = 6;
        result = twoSum(nums3, target3);
        System.out.println(Arrays.toString(result));
        // eg4
        int[] nums4 = {0, 4, 3, 0};
        int target4 = 0;
        result = twoSum(nums4, target4);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(len - 1 > 1 ? len - 1 : 2);
        map.put(nums[0], 0);
        for (int i = 1; i < len; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                return new int[]{map.get(another), i};
            } else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    // https://leetcode.cn/problems/add-two-numbers/description/
    public void addTwoNumbersTest() {
        // eg1
        ListNode l1_1 = ListNode.generate(new int[]{2, 4, 3});
        ListNode l2_1 = ListNode.generate(new int[]{5, 6, 4});
        ListNode eg1 = addTwoNumbers(l1_1, l2_1);
        ListNode.printList(eg1);
        // eg2
        ListNode l1_2 = ListNode.generate(new int[]{0});
        ListNode l2_2 = ListNode.generate(new int[]{0});
        ListNode eg2 = addTwoNumbers(l1_2, l2_2);
        ListNode.printList(eg2);
        // eg3
        ListNode l1_3 = ListNode.generate(new int[]{9, 9, 9, 9, 9, 9, 9});
        ListNode l2_3 = ListNode.generate(new int[]{9, 9, 9, 9});
        ListNode eg3 = addTwoNumbers(l1_3, l2_3);
        ListNode.printList(eg3);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return dummy.next;
    }

    // https://leetcode.cn/problems/longest-substring-without-repeating-characters/
    public void lengthOfLongestSubstringTest() {
        // eg1
        String s1 = "abcabcbb";
        int rs1 = lengthOfLongestSubstring(s1);
        System.out.print("s = \"" + s1 + "\"\n");
        System.out.println(rs1);
        // eg2
        String s2 = "bbbbb";
        int rs2 = lengthOfLongestSubstring(s2);
        System.out.print("s = \"" + s2 + "\"\n");
        System.out.println(rs2);
        // eg3
        String s3 = "pwwkew";
        int rs3 = lengthOfLongestSubstring(s3);
        System.out.print("s = \"" + s3 + "\"\n");
        System.out.println(rs3);
        // eg4
        String s4 = "au";
        int rs4 = lengthOfLongestSubstring(s4);
        System.out.print("s = \"" + s4 + "\"\n");
        System.out.println(rs4);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) return s.length();
        HashMap<Character, Integer> map = new HashMap<>(127);
        map.put(s.charAt(0), 0);
        int left = 0;
        int max = 1 - left;
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                left = Math.max(left, map.get(ch) + 1);
            }
            // update the character last of index
            map.put(ch, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
