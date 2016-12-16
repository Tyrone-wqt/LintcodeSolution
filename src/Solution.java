import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.regex.Matcher;

/**
 * Created by lenovo on 2016/11/24.
 */
public class Solution {

    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     *
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        //write your code here
        if (source == null) return -1;
        if (target == null) return -1;

        if (target == "") return 0;

        int srcLength = source.length();
        int tgtLength = target.length();

        if (tgtLength > srcLength) return -1;

        for (int i = 0; i <= srcLength - tgtLength; i++) {
            for (int j = i, k = 0; j < tgtLength + i && k < tgtLength; j++, k++) {
                if (source.charAt(j) != target.charAt(k))
                    break;
                if (k == tgtLength - 1) return i;
            }
        }

        return -1;
    }

    /**
     * @param nums an integer array
     * @return nothing, do this in-place
     */
    public void moveZeroes(int[] nums) {
        // Write your code here
        if (nums == null) return;
        if (nums.length == 0) return;
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) count++;
            else {
                stack.push(nums[i]);
            }
        }
        for (int j = nums.length - 1; j >= stack.size(); j--) {
            nums[j] = 0;
        }
        for (int j = nums.length - count - 1; j >= 0; j--) {
            nums[j] = stack.pop();
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    /**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        // write your code here
        if (A == null) return false;
        if (B.length() == 0) return true;
        int[] array = new int[26];
        for (int i = 0; i < A.length(); i++) {
            int pos = A.charAt(i) - 'A';
            array[pos] = array[pos] + 1;
        }

        for (int j = 0; j < B.length(); j++) {
            int index = B.charAt(j) - 'A';
            array[index] = array[index] - 1;
            if (array[index] < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param s: The first string
     * @param t: The second string
     * @return true or false
     */
    public boolean anagram(String s, String t) {
        // write your code here
        if (s == null) return false;
        if (t.length() == 0) return true;
        int[] array = new int[256];
        for (int i = 0; i < s.length(); i++) {
            int pos = s.charAt(i);

            array[pos] = array[pos] + 1;
        }

        for (int j = 0; j < t.length(); j++) {
            int index = t.charAt(j);
            array[index] = array[index] - 1;
            if (array[index] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        List<String> result = new ArrayList<>();
        // write your code here
        if (strs == null || strs.length == 0) return null;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] array = new int[128];
            for (int i = 0; i < str.length(); i++) {
                int pos = str.charAt(i);
                array[pos] = array[pos] + 1;
            }
            String key = generateKey(array);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(str);
                map.put(key, newList);
            }
        }
        Set<String> keySet = map.keySet();
        for (String keystr : keySet) {
            if (map.get(keystr).size() > 1) {
                result.addAll(map.get(keystr));
            }
        }
        return result;
    }

    public String generateKey(int[] array) {
        String result = "";
        for (int i = 0; i < 128; i++) {
            if (array[i] != 0) {
                result = result + ((char) i) + array[i];
            }
        }
        return result;
    }


    /**
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        // write your code here
        if (strs == null) return null;
        if (strs.length == 0) return "";
        String commonPre = strs[0];

        for (String str : strs) {
            int index = commonPre.length();
            if (str.length() == 0) return "";
            for (int i = 0; i < commonPre.length() && i < str.length(); i++) {
                if (commonPre.charAt(i) != str.charAt(i)) {
                    index = i;
                    break;
                }
            }
            commonPre = commonPre.substring(0, index);
        }
        return commonPre;
    }

    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if (A == null || B == null || A.length() == 0 || B.length() == 0) return 0;
        int lengthA = A.length();
        int lengthB = B.length();
        int maxLength = 0;
        int[][] dyArray = new int[lengthA][lengthB];
        for (int i = 0; i < lengthA; i++) {
            if (A.charAt(i) == B.charAt(0)) {
                dyArray[i][0] = 1;
                maxLength = 1;
            }

        }

        for (int j = 0; j < lengthB; j++) {
            if (A.charAt(0) == B.charAt(j)) {
                dyArray[0][j] = 1;
                maxLength = 1;
            }
        }

        if (lengthA > 1 && lengthB > 1) {
            for (int i = 1; i < lengthA; i++) {
                for (int j = 1; j < lengthB; j++) {
                    if (A.charAt(i) == B.charAt(j)) {

                        dyArray[i][j] = dyArray[i - 1][j - 1] + 1;
                        if (dyArray[i][j] > maxLength) maxLength = dyArray[i][j];
                    } else {
                        dyArray[i][j] = 0;
                    }
                }
            }
        } else {
            return maxLength;
        }

        return maxLength;
    }

    /**
     * @param str: A string
     * @return An integer
     */

    public final int INT_MAX = 2147483647;
    public final int INT_MIN = -2147483647;

    public int atoi(String str) {
        // write your code here
        boolean isMinus = false;
        int num = 0;
        str = str.trim();
        if (str == null || str.length() == 0) return 0;
        if (str.charAt(0) == '-') {
            isMinus = true;
            num = atoiCore(str.substring(1), isMinus);
        } else if (str.charAt(0) <= '9' && str.charAt(0) >= '1') {
            num = atoiCore(str, isMinus);
        } else {
            num = atoi(str.substring(1));
        }
        return num;
    }

    public int atoiCore(String str, boolean isMinus) {
        int digit = 0;
        long num = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') break;
            if (str.charAt(i) > '9' || str.charAt(i) < '0') break;
            else {
                digit = str.charAt(i) - '0';
            }
            num = num * 10 + digit;
            if (!isMinus && num > INT_MAX) return INT_MAX;
            else if (isMinus && -num < INT_MIN) return INT_MIN;
        }
        if (!isMinus) return (int) num;
        else return (int) (-num);

    }

    /**
     * S
     *
     * @param nums: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int difnum = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[difnum] = nums[i];
                difnum++;
            }
        }
        return difnum;
    }

    /**
     * @param A:    A list of integers
     * @param elem: An integer
     * @return: The new length after remove
     */
    public int removeElement(int[] A, int elem) {
        // write your code here
        if (A == null || A.length == 0) return 0;
        int ret = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != elem) {
                A[ret++] = A[i];
            }
        }

        return ret;

    }

    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     * and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        if (nums == null) return null;
        ArrayList<Integer> ret = new ArrayList<>();
        if (nums.length == 0) return ret;
        int length = nums.length;
        int[] sum = new int[length];
        //sum[0] = nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        //map.put(sum[0], 0);
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                sum[i] = nums[i];
            } else {
                sum[i] = sum[i - 1] + nums[i];
            }

            if (!map.containsKey(sum[i])) map.put(sum[i], i);
            else {
                ret.add(map.get(sum[i]) + 1);
                ret.add(i);
                return ret;
            }
        }
        return ret;

    }

    /**
     * @param A: sorted integer array A which has m elements,
     *           but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        if (A == null || B == null) return;
        int index = m + n - 1;
        int posA = m - 1;
        int posB = n - 1;
        while (posA >= 0 && posB >= 0) {
            if (A[posA] > B[posB]) A[index--] = A[posA--];
            else A[index--] = B[posB--];
        }
        if (posA < 0) {
            while (posB >= 0) {
                A[index--] = B[posB--];
            }
        }

        if (posB < 0) {
            while (posA >= 0) {
                A[index--] = A[posA--];
            }
        }

    }

    /**
     * @param A: Given an integers array A
     * @return: A Long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
        // write your code
        if (A == null || A.size() == 0) return null;
        ArrayList<Long> ret = new ArrayList<>();
        if (A.size() == 1) {
            ret.add((long) 1);
            return ret;
        }
        int size = A.size();
        long[] lp = new long[size];
        lp[0] = A.get(0);
        long[] rp = new long[size];
        rp[size - 1] = A.get(size - 1);
        for (int i = 1; i < size; i++) {
            lp[i] = lp[i - 1] * A.get(i);
        }

        for (int j = size - 2; j > -1; j--) {
            rp[j] = rp[j + 1] * A.get(j);
        }


        for (int k = 0; k < size; k++) {
            if (k == 0) ret.add(rp[1]);
            else if (k == size - 1) ret.add(lp[size - 2]);
            else {
                ret.add(lp[k - 1] * rp[k + 1]);
            }
        }
        return ret;

    }

    /*
    * @param numbers : An array of Integer
    * @param target : target = numbers[index1] + numbers[index2]
    * @return : [index1 + 1, index2 + 1] (index1 < index2)
    */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        if (numbers == null)
            return null;
        int[] ret = new int[2];
        Map<Integer, List<Integer>> record = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            List<Integer> list = record.get(numbers[i]);
            if (list == null) {
                list = new ArrayList<>();
                list.add(i);
                record.put(numbers[i], list);
            } else {
                list.add(i);
            }
        }
        Arrays.sort(numbers);

        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            if (numbers[start] + numbers[end] == target) {
                if (numbers[start] == numbers[end]) {
                    ret[0] = record.get(numbers[start]).get(0) + 1;
                    ret[1] = record.get(numbers[start]).get(1) + 1;
                    return ret;
                } else {
                    int ret1 = record.get(numbers[start]).get(0);
                    int ret2 = record.get(numbers[end]).get(0);
                    if (ret1 < ret2) {
                        ret[0] = ret1 + 1;
                        ret[1] = ret2 + 1;
                        return ret;
                    } else {
                        ret[0] = ret2 + 1;
                        ret[1] = ret1 + 1;
                        return ret;
                    }

                }
            } else if (numbers[start] + numbers[end] < target) {
                start++;
            } else {
                end--;
            }
        }
        return ret;

    }

    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        // write your code here
        Arrays.sort(numbers);
        for (int k = 0; k < numbers.length; k++) {
            if (numbers[k] > 0) break;
            if (k > 0 && numbers[k] == numbers[k - 1]) continue;
            int target = 0 - numbers[k];
            int i = k + 1;
            int j = numbers.length - 1;
            while (i < j) {
                if (numbers[i] + numbers[j] == target) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(numbers[k]);
                    list.add(numbers[i]);
                    list.add(numbers[j]);
                    ret.add(list);
                    while (i < j && numbers[i] == numbers[i + 1]) i++;
                    while (i < j && numbers[j] == numbers[j - 1]) j--;
                    i++;
                    j--;
                } else if (numbers[i] + numbers[j] < target) {
                    i++;
                } else {
                    j--;
                }
            }

        }
        return ret;
    }

    /**
     * @param nums: The integer array you should partition
     * @param k:    As description
     *              return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {

        //write your code here
        if (nums == null) return 0;
        if (nums.length == 0) return 0;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            while (i <= j && nums[i] < k) i++;
            while (i <= j && nums[j] >= k) j--;
            if (i < j) {
                nums[i] = nums[i] ^ nums[j];
                nums[j] = nums[i] ^ nums[j];
                nums[i] = nums[i] ^ nums[j];
            } else {
                break;
            }
        }
        return i;
    }

    /**
     * @param A: an array of integers
     * @return: an integer
     */
    public int firstMissingPositive(int[] A) {
        // write your code here
        if (A == null) return 1;
        for (int i = 0; i < A.length; ) {
            if (A[i] < 1 || A[i] > A.length || A[i] == i + 1 || A[A[i] - 1] == A[i]) i++;
            else {
                swap(A, i, A[i] - 1);
            }
        }
        int j = 0;
        for (; j < A.length; j++) {
            if (A[j] != j + 1) break;
        }

        return j + 1;

    }

    public void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

    /**
     * @param numbers: Give an array numbers of n integer
     * @param target   : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers, int target) {
        // write your code here
        int ret = target;
        int closest = Integer.MAX_VALUE;
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) continue;
            int t = target - numbers[i];
            int p = i + 1;
            int q = numbers.length - 1;
            while (p < q) {
                int sum = numbers[p] + numbers[q];
                if (sum < t) p++;
                else if (sum > t) q--;
                else {
                    ret = target;
                    return ret;
                }
                if (Math.abs(sum + numbers[i] - target) < closest) {
                    closest = Math.abs(sum + numbers[i] - target);
                    ret = sum + numbers[i];
                }
            }
        }
        return ret;
    }

    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0) return false;
        int row = searchRowIndex(matrix, target, 0, matrix.length - 1);
        return searchColIndex(matrix, target, row);
    }

    public int searchRowIndex(int[][] matrix, int target, int start, int end) {
        //if(matrix[end][0]<=target) return end;
        if (start <= end) {
            int mid = (start + end) / 2;
            if (matrix[mid][0] == target) return mid;
            else if (matrix[mid][0] < target) return searchRowIndex(matrix, target, mid + 1, end);
            else return searchRowIndex(matrix, target, start, mid - 1);
        } else {
            if (end < 0) return 0;
            else return end;
        }
    }

    public boolean searchColIndex(int[][] matrix, int target, int row) {
        int end = matrix[row].length - 1;
        int start = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (matrix[row][mid] == target) return true;
            else if (matrix[row][mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    /**
     * @param nums:   The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        //write your code here\
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                if (mid == 0) return mid;
                else {
                    while (nums[mid] == nums[mid - 1]) mid--;
                    return mid;
                }
            } else if (nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }

    /**
     * param A : an integer sorted array
     * param target :  an integer to be inserted
     * return : an integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) return 0;
        if (A[0] > target) return 0;
        if (A[A.length - 1] < target) return A.length;
        int start = 0;
        int end = A.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == target) return mid;
            else if (A[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        if (A[start] < target) return start + 1;
        else return start;
    }

    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        int low = 0, high = x;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long square = (long) mid * (long) mid;
            long square1 = (long) (mid + 1) * (long) (mid + 1);
            long square2 = (long) (mid - 1) * (long) (mid - 1);

            if (square == x) return mid;
            if (square < x && square1 > x) return mid;
            if (square > x && square2 < x) return mid - 1;
            if (square < x) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (start == end - 1) break;
            int mid = (start + end) / 2;
            if (nums[start] < nums[mid] && nums[mid] < nums[end]) return nums[start];
            else if (nums[mid] > nums[end]) start = mid;
            else if (nums[start] > nums[mid]) end = mid;
        }
        Set<Integer> set;
        boolean[] has = new boolean[]{};
        return Math.min(nums[start], nums[end]);
    }

    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     *           return: The maximum length of the small pieces.
     */
    public int woodCut(int[] L, int k) {

        // write your code here
        if (L == null || L.length == 0) return 0;
        Arrays.sort(L);
        int start = 1;
        int end = L[L.length - 1];
        while (start < end) {
            if (start == end - 1) break;
            int mid = (int) ((((long) start) + ((long) end)) / 2);
            int pieces = getPieces(L, mid);
            if (pieces < k) {
                end = mid;
            } else {
                int low = mid;
                int high = end;
                while (low < high) {
                    if (low == high - 1) break;
                    int m = (int) ((((long) low) + ((long) high)) / 2);
                    ;
                    if (getPieces(L, low) == getPieces(L, high)) {
                        return high;
                    } else if (getPieces(L, m) >= k) {
                        low = m;
                    } else {
                        high = m;
                    }
                }
                if (getPieces(L, low) >= k) {
                    if (getPieces(L, high) >= k) {
                        return Math.max(low, high);
                    } else {
                        return low;
                    }
                } else {
                    if (getPieces(L, high) >= k) {
                        return high;
                    } else {
                        return 0;
                    }
                }
            }
        }
        if (getPieces(L, start) >= k) {
            if (getPieces(L, end) >= k) {
                return Math.max(start, end);
            } else {
                return start;
            }
        } else {
            if (getPieces(L, end) >= k) {
                return end;
            } else {
                return 0;
            }

        }


    }

    public int getPieces(int[] L, int pieceLength) {
        int ret = 0;
        for (int i = 0; i < L.length; i++) {
            ret = ret + L[i] / pieceLength;
        }
        return ret;
    }

    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
        int start = 1;
        int end = A.length - 2;
        while (start < end) {
            if (start == end - 1) break;
            int mid = (start + end) / 2;
            if (A[mid] < A[mid - 1]) {
                end = mid;
            } else if (A[mid] < A[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] < A[end]) return end;
        else return start;
    }

    /**
     * @param A      : an integer sorted array
     * @param target :  an integer to be inserted
     *               return : a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        // write your code here
        int[] ret = new int[2];
        if (A == null || A.length == 0) {
            ret[0] = -1;
            ret[1] = -1;
            return ret;
        }
        int start = 0;
        int end = A.length - 1;
        int index = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == target) {
                index = mid;
                break;
            } else if (A[mid] < target) start = mid + 1;
            else end = mid - 1;
        }

        if (index == -1) {
            ret[0] = -1;
            ret[1] = -1;
        } else {
            int i = index;
            for (; i >= start; i--) {
                if (A[i] != A[index]) {
                    break;
                }
            }
            ret[0] = i + 1;
            int j = index;
            for (; j <= end; j++) {
                if (A[j] != A[index]) {
                    break;
                }
            }
            ret[1] = j - 1;
        }
        return ret;
    }

    /**
     * @param A      : an integer rotated sorted array
     * @param target :  an integer to be searched
     *               return : an integer
     */
    public int search(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) return -1;
        int start = 0;
        int end = A.length - 1;
        while (start <= end) {
            if (start == end - 1) break;
            int mid = (start + end) / 2;

            if (A[mid] == target) return mid;

            if (A[mid] > A[start]) {
                if(A[start]<=target&&A[mid]>=target){
                    end=mid;
                }else{
                    start=mid;
                }
            } else {
                if(A[mid]<=target&&A[end]>=target){
                    start=mid;
                }else{
                    end=mid;
                }
            }

        }
        if (A[start] == target) return start;
        if (A[end] == target) return end;
        return -1;
    }

    /**
     *@param a, b: Two integer
     *return: An integer
     */
    public static int bitSwapRequired(int a, int b) {
        // write your code here
        int tmp=a^b;
        int yu=0;
        int ret=0;

        int flag=1;
        while(flag!=0){
            if((tmp^flag)!=0) ret++;
            flag=flag<<1;
        }
        return ret;
    }

    /*
    * @param n: An integer
    * @return: True or false
    */
    public boolean checkPowerOf2(int n) {
        // write your code here
        if(n<0) return false;
        return (n&(n-1))==0?true:false;

    }

    /*
    * param n: As desciption
    * return: An integer, denote the number of trailing zeros in n!
    */
    public long trailingZeros(long n) {
        // write your code here
        long count = 0;
        long temp=n/5;
        while (temp!=0) {
            count+=temp;
            temp=temp/5;
        }
        return count;
    }

    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if(m<=0||n<=0) return 0;
        int s[][]=new int[m][n];
        for(int i=0;i<m;i++){
            s[i][0]=1;
        }
        for(int j=0;j<n;j++){
            s[0][j]=1;
        }
        for(int p=1;p<m;p++){
            for(int q=1;q<n;q++){
                s[p][q]=s[p-1][q]+s[p][q-1];
            }
        }

        return s[m-1][n-1];
    }

    /**
     *@param n, m: Two integer
     *@param i, j: Two bit positions
     *return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        // write your code here
        int shiftm=m<<i;
        int shift1=0;
        if(j<31){
            shift1=(-1)<<(j+1);
        }
        int sum=0;
        for(int q=0;q<i;q++){
            sum= (int) (sum+Math.pow(2,q));
        }
        shift1=shift1+sum;
        return (n&shift1)^shiftm;
    }

    /**
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        // write your code here
        if(n==0||n==1) return 1;
        if(n==2) return 2;

        int[] cache=new int[n+1];

        cache[1]=1;
        cache[2]=2;

        for(int j=3;j<n+1;j++){
            int tmp=0;
            for(int k=2;k<j;k++){
                tmp=tmp+cache[k-1]*cache[j-k];
            }
            cache[j]=2*cache[j-1]+tmp;
        }
        return cache[n];
    }

    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     * equl:(a * b) % p = (a % p * b % p) % p
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        if (n == 0)
            return 1 % b;
        else if (n == 1)
            return a % b;
        else if (n < 0)
            return -1;

        long product = fastPower(a, b, n/2);
        product = (product*product) % b;
        if (n % 2 == 1)
            product = (product * (a%b))%b; //equal: product = (product * a)%b;
        return (int)(product);
    }

    /**
     *@param n: Given a decimal number that is passed in as a string
     *@return: A string
     */
    public String binaryRepresentation(String n) {
        // write your code here
        StringBuilder sb=new StringBuilder();
        int pIndex=n.indexOf(".");
        String pre=n.substring(0,pIndex);
        String after=n.substring(pIndex,n.length());
        long zheng=Integer.valueOf(pre);
        double xiao=Double.valueOf(after);
        if(zheng==0)
            sb.append(0);
        while(zheng>0){
            sb.append(zheng%2);
            zheng=zheng/2;
        }
        sb.reverse();
        if(Math.abs(xiao)>=1e-5){
            sb.append(".");
        }else{
            return sb.toString();
        }
        int i=0;
        while(Math.abs(xiao)>=1e-5){
            xiao=xiao*2;
            int tmp=(int)(xiao);
            if(i<32){
                sb.append(tmp);
                xiao=xiao-tmp;
                i++;
            }else{
                return "ERROR";
            }
        }
        return  sb.toString();
    }

    /**
     *@param A : an integer array
     *return : a integer
     */
    public int singleNumber(int[] A) {
        // Write your code here
        int ret=0;
        for(int i=0;i<A.length;i++){
            ret=ret^A[i];
        }
        return ret;
    }

    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        int temp=nums.get(0);
        int n=1;
        for(int i=1;i<nums.size();i++){
            if(nums.get(i)==temp){
                n++;
            }else{
                n--;
                if(n==0){
                    temp=nums.get(i);
                    n++;
                }
            }
        }
        return temp;
    }

    /**
     *@param num: A list of non negative integers
     *@return: A string
     */
    public String largestNumber(int[] num) {
        // write your code here
        if(num==null) return null;
       sortQuick(num,0,num.length-1);
        StringBuilder sb=new StringBuilder();
        boolean isStart=false;
        for(int k=num.length-1;k>-1;k--){
            if(num[k]==0&&!isStart&&k!=0){
                continue;
            }else{
                isStart=true;
                sb.append(num[k]);
            }

        }

        return sb.toString();

    }


    public void sortQuick(int[] num,int start,int end){
        if(start>=end) return;
        int key=num[start];
        int i=start;
        int j=end;
        while(i<j){
            while(compareTo(num[i],key)>=0&&i<j) i++;
            while (compareTo(num[j],key)<0&&i<=j) j--;
            if(i<j){
                swap(num,i,j);
            }
        }
        if(j!=start){
            swap(num,j,start);
        }
        sortQuick(num,start,j-1);
        sortQuick(num,j+1,end);
    }
    /*
     * a<b return 1;
     * a==b return 0;
     * a>b return -1;
     */
    public int compareTo(int a,int b){
        String stra=String.valueOf(a)+String.valueOf(b);
        String strb=String.valueOf(b)+String .valueOf(a);
        for(int i=0;i<stra.length()&&i<strb.length();i++){
            char ca=stra.charAt(i);
            char cb=strb.charAt(i);
            if(ca<cb) return 1;
            else if(ca>cb) return -1;
            else continue;
        }
       return 0;
    }

    /**
     * @param A: A list of integers
     * @return: The boolean answer
     */
    public boolean canJump(int[] A) {
        // wirte your code here
        int maxN = 0; //记录能走的最大步数
        int i = 0;    //记录当前走到第几个元素
        while(i<=maxN)  //能走的最大步数都不能到达当前元素 循环终止
        {
            maxN = Math.max(maxN, i + A[i]);   //随时更新能走的最大步数
            ++i;                      //每次往前走一步
            if(maxN>=A.length-1)
                return true;
        }
        return false;
    }

    /**
     * @param gas: an array of integers
     * @param cost: an array of integers
     * @return: an integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // write your code here
        // write your code here
        int cur = 0;
        int totalGas = 0;
        int totalCost = 0;
        int start = 0;
        for(int i=0;i<gas.length;i++)
        {
            cur+=gas[i];
            cur-=cost[i];
            totalGas+=gas[i];
            totalCost+=cost[i];
            if(cur<0)
            {
                start = i+1;
                cur = 0;
            }
        }
        if(totalGas>=totalCost)
            return start;
        return -1;
    }

    /**
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    public String DeleteDigits(String A, int k) {
        // write your code here
        String ret="";
        if(A==null)
            return ret;
        ret=A;
        while(k>0){
            int i=0;
            while(i<ret.length()-1&&ret.charAt(i)<=ret.charAt(i+1))
                ++i;

            ret=ret.substring(0, i)+ret.substring(i + 1);
            k--;
        }
        int i;
        for(i=0;ret.charAt(i)=='0';++i);
        ret=ret.substring(i);
        return ret;
    }

    /**
     * @param nums: an array of integers
     * @return: return nothing (void), do not return anything, modify nums in-place instead
     */
    public int[] nextPermutation(int[] nums) {
        // write your code here
        for(int i=nums.length-1;i>=0;i--){
            for(int j=nums.length-1;j>i;j--){
                if(nums[i]<nums[j]){
                    swap(nums,i,j);
                    Arrays.sort(nums,i+1,nums.length-1);
                    return nums;
                }
            }
        }
        Arrays.sort(nums);
        return nums;
    }
    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: The head of linked list.
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if(head==null) return null;
        int length=0;
        ListNode node=head;
        while(node!=null){
            length++;
            node=node.next;
        }

        int deleteIndex=length-n;
        node=head;
        int i=0;
        while(node!=null){
            if(deleteIndex==0){
                return head.next;
            }else{
                if(i==deleteIndex-1){
                    ListNode deleteNode=node.next;
                    node.next=deleteNode.next;
                    return head;
                }
            }
            i++;
            node=node.next;
        }
        return head;
    }

    /**
     * @param head: The first node of linked list.
     * @param x: an integer
     * @return: a ListNode
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        if(head==null)return null;
        ListNode ret=head;
        boolean isHeadTarget=false;
        ListNode targetPre=null;
        ListNode node=head;
        if(head.val>=x) isHeadTarget=true;
        else{
            while(node.next!=null){
                if(node.next.val>=x) {
                    targetPre=node;
                    break;
                }
                node=node.next;
            }
        }
        if(isHeadTarget){
            ListNode start=head;
            ListNode record=null;
            while(start.next!=null){
                if(start.next.val<x){
                    ListNode tmp=start.next;
                    start.next=tmp.next;
                    tmp.next=head;
                    if(record!=null) {
                        record.next=tmp;
                    }else{
                        ret=tmp;
                    }
                    record=tmp;
                    continue;
                }
                start=start.next;
            }
        }else{
            if(targetPre==null){
                return head;
            }else{
                ListNode start=targetPre.next;
                ListNode record=targetPre;
                while(start.next!=null){
                    if(start.next.val<x){
                        ListNode tmp=start.next;
                        start.next=tmp.next;
                        tmp.next=record.next;
                        record.next=tmp;
                        record=tmp;
                        continue;
                    }
                    start=start.next;
                }
            }

        }
        return isHeadTarget?ret:head;
    }

    /**
     * @param head head is the head of the linked list
     * @return: ListNode head of linked list
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // write your code here
        ListNode node=head;
        while(node!=null){
            ListNode tmp=node.next;
            if(tmp!=null){
                if(tmp.val==node.val){
                    node.next=tmp.next;
                    continue;
                }
                node=node.next;
            }else{
                break;
            }
        }
        return head;
    }

    /**
     * @param head: The head of linked list.
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        // write your code here
        if(head==null) return null;
        Map<ListNode,ListNode> map=new HashMap<>();
        ListNode node=head;
        while(node!=null){
            map.put(node,node.next);
            node=node.next;
        }
        ListNode ret=null;
        for(Map.Entry<ListNode,ListNode> entry:map.entrySet()){
            if(entry.getValue()==null){
                ret=entry.getKey();
                continue;
            }
            if(entry.getKey()==head){
                entry.getValue().next=entry.getKey();
                entry.getKey().next=null;
                continue;
            }
            entry.getValue().next=entry.getKey();
        }
        return ret;
    }

    /**
     * @param l1 l1 is the head of the linked list
     * @param l2 l2 is the head of the linked list
     * @return: ListNode head of linked list
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        if(l1==null) return l2;
        if(l2==null) return l1;
        ListNode ret=null;
        ListNode index=null;
        ListNode node1=l1;
        ListNode node2=l2;
        while(node1!=null&&node2!=null){
            if(node1.val<=node2.val){
                if(ret==null) ret=node1;
                if(index==null) index=node1;
                else{
                    index.next=node1;
                    index=node1;
                }
                node1=node1.next;
            }else{
                if(ret==null) ret=node2;
                if(index==null) index=node2;
                else{
                    index.next=node2;
                    index=node2;
                }
                node2=node2.next;
            }
        }
        while(node1!=null){
            index.next=node1;
            index=node1;
            node1=node1.next;
        }
        while(node2!=null){
            index.next=node2;
            index=node2;
            node2=node2.next;
        }
        return ret;
    }

}



