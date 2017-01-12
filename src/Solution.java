import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        if (i == j) return;
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
                if (A[start] <= target && A[mid] >= target) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (A[mid] <= target && A[end] >= target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

        }
        if (A[start] == target) return start;
        if (A[end] == target) return end;
        return -1;
    }

    /**
     * @param a, b: Two integer
     *           return: An integer
     */
    public static int bitSwapRequired(int a, int b) {
        // write your code here
        int tmp = a ^ b;
        int yu = 0;
        int ret = 0;

        int flag = 1;
        while (flag != 0) {
            if ((tmp ^ flag) != 0) ret++;
            flag = flag << 1;
        }
        return ret;
    }

    /*
    * @param n: An integer
    * @return: True or false
    */
    public boolean checkPowerOf2(int n) {
        // write your code here
        if (n < 0) return false;
        return (n & (n - 1)) == 0 ? true : false;

    }

    /*
    * param n: As desciption
    * return: An integer, denote the number of trailing zeros in n!
    */
    public long trailingZeros(long n) {
        // write your code here
        long count = 0;
        long temp = n / 5;
        while (temp != 0) {
            count += temp;
            temp = temp / 5;
        }
        return count;
    }

    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if (m <= 0 || n <= 0) return 0;
        int s[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            s[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            s[0][j] = 1;
        }
        for (int p = 1; p < m; p++) {
            for (int q = 1; q < n; q++) {
                s[p][q] = s[p - 1][q] + s[p][q - 1];
            }
        }

        return s[m - 1][n - 1];
    }

    /**
     * @param n, m: Two integer
     * @param i, j: Two bit positions
     *           return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        // write your code here
        int shiftm = m << i;
        int shift1 = 0;
        if (j < 31) {
            shift1 = (-1) << (j + 1);
        }
        int sum = 0;
        for (int q = 0; q < i; q++) {
            sum = (int) (sum + Math.pow(2, q));
        }
        shift1 = shift1 + sum;
        return (n & shift1) ^ shiftm;
    }

    /**
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        // write your code here
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;

        int[] cache = new int[n + 1];

        cache[1] = 1;
        cache[2] = 2;

        for (int j = 3; j < n + 1; j++) {
            int tmp = 0;
            for (int k = 2; k < j; k++) {
                tmp = tmp + cache[k - 1] * cache[j - k];
            }
            cache[j] = 2 * cache[j - 1] + tmp;
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

        long product = fastPower(a, b, n / 2);
        product = (product * product) % b;
        if (n % 2 == 1)
            product = (product * (a % b)) % b; //equal: product = (product * a)%b;
        return (int) (product);
    }

    /**
     * @param n: Given a decimal number that is passed in as a string
     * @return: A string
     */
    public String binaryRepresentation(String n) {
        // write your code here
        StringBuilder sb = new StringBuilder();
        int pIndex = n.indexOf(".");
        String pre = n.substring(0, pIndex);
        String after = n.substring(pIndex, n.length());
        long zheng = Integer.valueOf(pre);
        double xiao = Double.valueOf(after);
        if (zheng == 0)
            sb.append(0);
        while (zheng > 0) {
            sb.append(zheng % 2);
            zheng = zheng / 2;
        }
        sb.reverse();
        if (Math.abs(xiao) >= 1e-5) {
            sb.append(".");
        } else {
            return sb.toString();
        }
        int i = 0;
        while (Math.abs(xiao) >= 1e-5) {
            xiao = xiao * 2;
            int tmp = (int) (xiao);
            if (i < 32) {
                sb.append(tmp);
                xiao = xiao - tmp;
                i++;
            } else {
                return "ERROR";
            }
        }
        return sb.toString();
    }

    /**
     * @param A : an integer array
     *          return : a integer
     */
    public int singleNumber(int[] A) {
        // Write your code here
        int ret = 0;
        for (int i = 0; i < A.length; i++) {
            ret = ret ^ A[i];
        }
        return ret;
    }

    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        int temp = nums.get(0);
        int n = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) == temp) {
                n++;
            } else {
                n--;
                if (n == 0) {
                    temp = nums.get(i);
                    n++;
                }
            }
        }
        return temp;
    }

    /**
     * @param num: A list of non negative integers
     * @return: A string
     */
    public String largestNumber(int[] num) {
        // write your code here
        if (num == null) return null;
        sortQuick(num, 0, num.length - 1);
        StringBuilder sb = new StringBuilder();
        boolean isStart = false;
        for (int k = num.length - 1; k > -1; k--) {
            if (num[k] == 0 && !isStart && k != 0) {
                continue;
            } else {
                isStart = true;
                sb.append(num[k]);
            }

        }

        return sb.toString();

    }


    public void sortQuick(int[] num, int start, int end) {
        if (start >= end) return;
        int key = num[start];
        int i = start;
        int j = end;
        while (i < j) {
            while (compareTo(num[i], key) >= 0 && i < j) i++;
            while (compareTo(num[j], key) < 0 && i <= j) j--;
            if (i < j) {
                swap(num, i, j);
            }
        }
        if (j != start) {
            swap(num, j, start);
        }
        sortQuick(num, start, j - 1);
        sortQuick(num, j + 1, end);
    }

    /*
     * a<b return 1;
     * a==b return 0;
     * a>b return -1;
     */
    public int compareTo(int a, int b) {
        String stra = String.valueOf(a) + String.valueOf(b);
        String strb = String.valueOf(b) + String.valueOf(a);
        for (int i = 0; i < stra.length() && i < strb.length(); i++) {
            char ca = stra.charAt(i);
            char cb = strb.charAt(i);
            if (ca < cb) return 1;
            else if (ca > cb) return -1;
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
        while (i <= maxN)  //能走的最大步数都不能到达当前元素 循环终止
        {
            maxN = Math.max(maxN, i + A[i]);   //随时更新能走的最大步数
            ++i;                      //每次往前走一步
            if (maxN >= A.length - 1)
                return true;
        }
        return false;
    }

    /**
     * @param gas:  an array of integers
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
        for (int i = 0; i < gas.length; i++) {
            cur += gas[i];
            cur -= cost[i];
            totalGas += gas[i];
            totalCost += cost[i];
            if (cur < 0) {
                start = i + 1;
                cur = 0;
            }
        }
        if (totalGas >= totalCost)
            return start;
        return -1;
    }

    /**
     * @param A: A positive integer which has N digits, A is a string.
     * @param k: Remove k digits.
     * @return: A string
     */
    public String DeleteDigits(String A, int k) {
        // write your code here
        String ret = "";
        if (A == null)
            return ret;
        ret = A;
        while (k > 0) {
            int i = 0;
            while (i < ret.length() - 1 && ret.charAt(i) <= ret.charAt(i + 1))
                ++i;

            ret = ret.substring(0, i) + ret.substring(i + 1);
            k--;
        }
        int i;
        for (i = 0; ret.charAt(i) == '0'; ++i) ;
        ret = ret.substring(i);
        return ret;
    }

    /**
     * @param nums: an array of integers
     * @return: return nothing (void), do not return anything, modify nums in-place instead
     */
    public int[] nextPermutation(int[] nums) {
        // write your code here
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] < nums[j]) {
                    swap(nums, i, j);
                    Arrays.sort(nums, i + 1, nums.length - 1);
                    return nums;
                }
            }
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * @param head: The first node of linked list.
     * @param n:    An integer.
     * @return: The head of linked list.
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if (head == null) return null;
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        int deleteIndex = length - n;
        node = head;
        int i = 0;
        while (node != null) {
            if (deleteIndex == 0) {
                return head.next;
            } else {
                if (i == deleteIndex - 1) {
                    ListNode deleteNode = node.next;
                    node.next = deleteNode.next;
                    return head;
                }
            }
            i++;
            node = node.next;
        }
        return head;
    }

    /**
     * @param head: The first node of linked list.
     * @param x:    an integer
     * @return: a ListNode
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        if (head == null) return null;
        ListNode ret = head;
        boolean isHeadTarget = false;
        ListNode targetPre = null;
        ListNode node = head;
        if (head.val >= x) isHeadTarget = true;
        else {
            while (node.next != null) {
                if (node.next.val >= x) {
                    targetPre = node;
                    break;
                }
                node = node.next;
            }
        }
        if (isHeadTarget) {
            ListNode start = head;
            ListNode record = null;
            while (start.next != null) {
                if (start.next.val < x) {
                    ListNode tmp = start.next;
                    start.next = tmp.next;
                    tmp.next = head;
                    if (record != null) {
                        record.next = tmp;
                    } else {
                        ret = tmp;
                    }
                    record = tmp;
                    continue;
                }
                start = start.next;
            }
        } else {
            if (targetPre == null) {
                return head;
            } else {
                ListNode start = targetPre.next;
                ListNode record = targetPre;
                while (start.next != null) {
                    if (start.next.val < x) {
                        ListNode tmp = start.next;
                        start.next = tmp.next;
                        tmp.next = record.next;
                        record.next = tmp;
                        record = tmp;
                        continue;
                    }
                    start = start.next;
                }
            }

        }
        return isHeadTarget ? ret : head;
    }

    /**
     * @param head head is the head of the linked list
     * @return: ListNode head of linked list
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // write your code here
        ListNode node = head;
        while (node != null) {
            ListNode tmp = node.next;
            if (tmp != null) {
                if (tmp.val == node.val) {
                    node.next = tmp.next;
                    continue;
                }
                node = node.next;
            } else {
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
        if (head == null) return null;
        Map<ListNode, ListNode> map = new HashMap<>();
        ListNode node = head;
        while (node != null) {
            map.put(node, node.next);
            node = node.next;
        }
        ListNode ret = null;
        for (Map.Entry<ListNode, ListNode> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                ret = entry.getKey();
                continue;
            }
            if (entry.getKey() == head) {
                entry.getValue().next = entry.getKey();
                entry.getKey().next = null;
                continue;
            }
            entry.getValue().next = entry.getKey();
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
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode ret = null;
        ListNode index = null;
        ListNode node1 = l1;
        ListNode node2 = l2;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                if (ret == null) ret = node1;
                if (index == null) index = node1;
                else {
                    index.next = node1;
                    index = node1;
                }
                node1 = node1.next;
            } else {
                if (ret == null) ret = node2;
                if (index == null) index = node2;
                else {
                    index.next = node2;
                    index = node2;
                }
                node2 = node2.next;
            }
        }
        while (node1 != null) {
            index.next = node1;
            index = node1;
            node1 = node1.next;
        }
        while (node2 != null) {
            index.next = node2;
            index = node2;
            node2 = node2.next;
        }
        return ret;
    }

    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list,
     * using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        // write your code here
        if (head == null || head.next == null) return head;
        ListNode low = head;
        ListNode fast = head;
        ListNode pre = head;
        while (low != null && fast != null && fast.next != null) {
            pre = low;
            low = low.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return merge2List(sortList(head), sortList(low));
    }

    public ListNode merge2List(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode head = null;
        ListNode ret = null;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                if (ret == null) {
                    ret = list1;
                    head = ret;
                } else {
                    ret.next = list1;
                    ret = ret.next;
                }
                list1 = list1.next;
            } else {
                if (ret == null) {
                    ret = list2;
                    head = ret;
                } else {
                    ret.next = list2;
                    ret = ret.next;
                }
                list2 = list2.next;
            }
        }
        while (list1 != null) {
            ret.next = list1;
            ret = ret.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            ret.next = list2;
            ret = ret.next;
            list2 = list2.next;
        }
        return head;

    }

    /**
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        // write your code here
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null && fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow == fast) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        RandomListNode node = head;
        while (node != null) {
            RandomListNode clonedNode = new RandomListNode(node.label);
            clonedNode.next = node.next;
            node.next = clonedNode;
            node = clonedNode.next;
        }

        node = head;
        while (node != null) {
            RandomListNode clonedNode = node.next;
            if (node.random != null) {
                clonedNode.random = node.random.next;
            }
            node = clonedNode.next;
        }

        node = head;
        RandomListNode clonedHead = head.next;
        RandomListNode clonedNode = null;

        while (node.next.next != null) {

            clonedNode = node.next;
            node.next = clonedNode.next;
            node = clonedNode.next;
            clonedNode.next = node.next;
            clonedNode = node.next;
        }

        node.next = null;
        return clonedHead;
    }

    /**
     * @param head: The head of linked list.
     * @return: void
     */
    public void reorderList(ListNode head) {
        // write your code here
        if (head == null) return;
        int size = 0;
        ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        int halfSize = size / 2;
        node = head;
        size = 0;
        while (node != null) {
            if (size < halfSize) {
                size++;
                node = node.next;
            } else {
                break;
            }
        }
        ListNode node2 = node.next;
        node.next = null;
        //reverse node2
        node = node2;
        ListNode nodePre = null;
        ListNode nodeNext = null;
        while (node != null) {
            nodeNext = node.next;
            node.next = nodePre;
            nodePre = node;
            node = nodeNext;
        }
        node2 = nodePre;
        node = head;
        ListNode index = null;
        while (node != null && node2 != null) {
            ListNode tmp1 = node.next;
            ListNode tmp2 = node2.next;
            if (index == null) {
                index = node;
                index.next = node2;
            } else {
                index.next = node;
                index.next.next = node2;
            }
            index = node2;
            node = tmp1;
            node2 = tmp2;
        }
        while (node != null) {
            if (index == null) {
                index = node;
                node = node.next;
            } else {
                index.next = node;
                index = node;
                node = node.next;
            }

        }
        while (node2 != null) {
            if (index == null) {
                index = node2;
                node2 = node2.next;
            } else {
                index.next = node2;
                index = node2;
                node2 = node2.next;
            }
        }
    }

    /**
     * @param head: the List
     * @param k:    rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        if (head == null) return null;
        int size = 0;
        ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        int offset = size - k % size;
        int index = 0;
        node = head;
        while (index < offset) {
            index++;
            node = node.next;
        }

        if (node == null) return head;
        else {
            ListNode end = node;
            while (node.next != null) {
                node = node.next;
            }
            node.next = head;
            while (node.next != end) {
                node = node.next;
            }
            node.next = null;
            return end;
        }
    }

    /**
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {
        // write your code here
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }

    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
        // write your code here
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if (root == null) return node;
        if (node == null) return root;
        TreeNode index = root;
        while (index != null) {
            if (node.val < index.val) {
                if (index.left != null) index = index.left;
                else {
                    index.left = node;
                    break;
                }
            } else {
                if (index.right != null) index = index.right;
                else {
                    index.right = node;
                    break;
                }
            }
        }
        return root;
    }

    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        // write your code here
        if (root == null) return true;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) <= 1) {
            return isBalanced(root.left) && isBalanced(root.right);
        } else {
            return false;
        }
    }

    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // write your code here\

        ArrayList<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            ret.add(node.val);
            if (node.right != null) stack.add(node.right);
            if (node.left != null) stack.add(node.left);
        }
        return ret;
    }

    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write your code here
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        ArrayList<TreeNode> index = new ArrayList<>();
        index.add(root);
        while (!index.isEmpty()) {
            ArrayList<Integer> newList = new ArrayList<>();
            int size = index.size();
            for (int i = 0; i < size; i++) {
                newList.add(index.get(i).val);
                if (index.get(i).left != null) index.add(index.get(i).left);
                if (index.get(i).right != null) index.add(index.get(i).right);
            }
            ret.add(newList);
            for (int i = 0; i < size; i++) {
                index.remove(0);
            }
        }
        return ret;
    }

    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    /*
    若有效，中序遍历一定有序
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
        List<Integer> list = new ArrayList<>();
        LDR(root, list);
        if (list.size() == 1) return true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
            } else {
                return false;
            }
        }
        return true;

    }

    public void LDR(TreeNode node, List<Integer> list) {
        if (node == null) return;
        LDR(node.left, list);
        list.add(node.val);
        LDR(node.right, list);
    }

    /**
     * @param root: The root of the binary search tree.
     * @param k1    and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in ascending order.
     */
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        List<Integer> list = new ArrayList<>();
        LDR(root, list);
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            if (value >= k1 && value <= k2) {
                ret.add(value);
            }
        }
        return ret;
    }

    /**
     * @param preorder : A list of integers that preorder traversal of a tree
     * @param inorder  : A list of integers that inorder traversal of a tree
     * @return : Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
        if (preorder == null || inorder == null) return null;
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int start1, int end1, int[] inorder, int start2, int end2) {
        if (start2 > end2 || start1 > end1) {
            return null;
        }
        int rootValue = preorder[start1];
        int rootValueIndex = -1;
        for (int i = start2; i <= end2; i++) {
            if (inorder[i] == rootValue) {
                rootValueIndex = i;
                break;
            }
        }
        if (rootValueIndex == -1) return null;
        TreeNode node = new TreeNode(rootValue);
        int leftChildSize = rootValueIndex - start2;
        if (leftChildSize == 0) node.left = null;
        else {
            node.left = buildTree(preorder, start1 + 1, start1 + leftChildSize, inorder, start2, rootValueIndex - 1);
        }
        int rightChildSize = end2 - rootValueIndex;
        if (rightChildSize == 0) node.right = null;
        else {
            node.right = buildTree(preorder, start1 + leftChildSize + 1, end1, inorder, rootValueIndex + 1, end2);
        }

        return node;
    }

    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        StringBuilder sb = new StringBuilder();
        nodeToString(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void nodeToString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        nodeToString(root.left, sb);
        nodeToString(root.right, sb);
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        String[] tokens = data.split(",");
        //Integer index=new Integer(0);
        AtomicInteger index = new AtomicInteger(0);
        return stringToNode(tokens, index);
    }

    public TreeNode stringToNode(String[] tokens, AtomicInteger index) {
        if (tokens[index.get()].equals("#")) {
            index.incrementAndGet();
            return null;
        }
        int value = Integer.valueOf(tokens[index.get()]);
        TreeNode root = new TreeNode(value);
        index.incrementAndGet();
        root.left = stringToNode(tokens, index);
        root.right = stringToNode(tokens, index);
        return root;
    }

    /**
     * @param root:  The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        // write your code here
        if (root == null) return null;
        TreeNode node = root;
        TreeNode parent = root;
        boolean isLeft = false;
        while (node != null) {
            if (value == node.val) {
                //to do
                if (node.left != null && node.right != null) {
                    int val = findReplaceableNode(node);
                    node.val = val;
                } else if (node.left == null) {
                    if (node == root) return node.right;
                    else {
                        if (isLeft) {
                            parent.left = node.right;
                        } else {
                            parent.right = node.right;
                        }
                        return root;
                    }
                } else {
                    if (node == root) return node.left;
                    else {
                        if (isLeft) {
                            parent.left = node.left;
                        } else {
                            parent.right = node.left;
                        }
                        return root;
                    }

                }
            } else if (value < node.val) {
                isLeft = true;
                parent = node;
                node = node.left;
            } else {
                isLeft = false;
                parent = node;
                node = node.right;
            }
        }
        return root;
    }


    public int findReplaceableNode(TreeNode root) {
        boolean isLeft = true;
        if (root.left != null) {
            TreeNode parent = root;
            TreeNode node = root.left;
            isLeft = true;
            while (node.right != null) {
                parent = node;
                node = node.right;
                isLeft = false;
            }
            if (isLeft) parent.left = null;
            else parent.right = null;

            return node.val;
        } else if (root.right != null) {
            TreeNode parent = root;
            TreeNode node = root.right;
            isLeft = false;
            while (node.left != null) {
                parent = node;
                node = node.left;
                isLeft = true;
            }
            if (isLeft) parent.left = null;
            else parent.right = null;
            return node.val;
        }
        return 0;
    }

    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (nums == null) return ret;
        permute(nums, 0, ret);
        return ret;
    }

    public void permute(int[] nums, int start, List<List<Integer>> ret) {
        if (start >= nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            ret.add(list);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            int startIndex = start;
            int curIndex = i;
            swap(nums, startIndex, curIndex);
            permute(nums, start + 1, ret);
            swap(nums, startIndex, curIndex);
        }

    }

    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        // write your code here
        List<List<Integer>> ret = new ArrayList<>();
        if (n < k) {
            return ret;
        }
        Set<Integer> set = new HashSet<>();
        combine(n, n - k, set, ret, 1);
        return ret;
    }

    public void combine(int n, int p, Set<Integer> set, List<List<Integer>> ret, int start) {
        if (p == 0) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (!set.contains(i)) {
                    list.add(i);
                }
            }
            ret.add(list);
            return;
        }
        for (int i = start; i <= n; i++) {
            if (set.contains(i)) {
                continue;
            } else {
                set.add(i);
                combine(n, p - 1, set, ret, i + 1);
                set.remove(i);
            }
        }

    }

    /**
     * @param start, a string
     * @param end,   a string
     * @param dict,  a set of string
     * @return an integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        //BFS
        if (start.equals(end)) return 1;
        Map<String, Integer> visited = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.put(start, 1);
        while (queue.size() != 0) {
            String tmp = queue.poll();
            int layer = visited.get(tmp);
            if (isChange(tmp, end)) return layer + 1;
            for (String str : dict) {
                if (visited.get(str) == null) {
                    if (isChange(tmp, str)) {
                        queue.add(str);
                        visited.put(str, layer + 1);
                    }
                }
            }
        }
        return 0;

    }

    public boolean isChange(String src, String dst) {
        char[] srcChar = src.toCharArray();
        char[] dstChar = dst.toCharArray();
        int difCount = 0;
        for (int i = 0; i < srcChar.length; i++) {
            if (srcChar[i] != dstChar[i]) {
                difCount++;
            }
        }
        if (difCount == 1) return true;
        return false;
    }

    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        subsetsCore(nums, 0, list, ret);
        return ret;
    }

    public void subsetsCore(int[] nums, int start, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ret) {
        if (start == nums.length) {
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            ret.add(list);
            return;
        }
        ArrayList<Integer> list2 = new ArrayList<>(list);
        list2.add(nums[start]);
        subsetsCore(nums, start + 1, list, ret);
        subsetsCore(nums, start + 1, list2, ret);

    }

    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        subsetsCore(nums, 0, list, ret);
        return ret;
    }

    public void subsetsWithDupCore(int[] nums, int start, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ret) {
        if (start == nums.length) {
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            ret.add(list);
            return;
        }
        if (list.size() > 0) {
            if (nums[start] == list.get(list.size() - 1)) {

                list.add(nums[start]);
                subsetsWithDupCore(nums, start + 1, list, ret);
            } else {
                ArrayList<Integer> list2 = new ArrayList<>(list);
                list2.add(nums[start]);
                subsetsWithDupCore(nums, start + 1, list, ret);
                subsetsWithDupCore(nums, start + 1, list2, ret);
            }
        } else {
            ArrayList<Integer> list2 = new ArrayList<>(list);
            list2.add(nums[start]);
            subsetsWithDupCore(nums, start + 1, list, ret);
            subsetsWithDupCore(nums, start + 1, list2, ret);
        }

    }

    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // Write your code here
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (nums == null) return ret;
        permuteUnique(nums, 0, ret);
        return ret;
    }

    public void permuteUnique(int[] nums, int start, List<List<Integer>> ret) {
        if (start >= nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            ret.add(list);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            /*
            int startIndex=start;
            int curIndex=i;
            if(startIndex!=curIndex&&nums[startIndex]==nums[curIndex]){
                continue;
            }else{
                swap(nums, startIndex, curIndex);
                permuteUnique(nums, start + 1, ret);
                swap(nums, startIndex, curIndex);
            }
            */
            /*
            * 每遍历到一个数 就从num[start]--nums[i]检查有没有与nums[i]相同的数，有则跳过这次遍历。
             */

            boolean flag = true;
            //int tmp=nums[i];
            int j = i;
            while (j >= start) {
                if (nums[j] == nums[i] && j != i) {
                    flag = false;
                }
                j--;
            }
            if (flag) {
                int startIndex = start;
                int curIndex = i;
                swap(nums, startIndex, curIndex);
                permuteUnique(nums, start + 1, ret);
                swap(nums, startIndex, curIndex);
            }
        }
    }


    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     * 不用递归
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (nums == null) return ret;
        do {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(nums[i]);
            }
            ret.add(list);
        } while (nextPermute(nums));
        return ret;
    }

    public boolean nextPermute(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] < nums[j]) {
                    swap(nums, i, j);
                    Arrays.sort(nums, i + 1, nums.length);
                    return true;
                }
            }
        }
        Arrays.sort(nums, 0, nums.length - 1);
        return false;
    }

    /**
     * @param candidates: A list of integers
     * @param target:An   integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        Arrays.sort(candidates);
        int[] nums = new int[candidates.length];
        int index = 1;
        nums[0] = candidates[0];
        for (int i = 1; i < candidates.length; i++) {
            if (candidates[i] == candidates[i - 1]) {
                continue;
            } else {
                nums[index] = candidates[i];
                index++;
            }
        }
        int[] arg = Arrays.copyOfRange(nums, 0, index);
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSumCore(arg, 0, target, list, ret);
        return ret;
    }

    public void combinationSumCore(int[] candidates, int start, int target, List<Integer> list, List<List<Integer>> ret) {
        if (start >= candidates.length) return;
        if (candidates[start] == target) {
            list.add(candidates[start]);
            ret.add(list);
            return;
        }
        if (candidates[start] > target) {
            if (list.size() != 0) {
                list.remove(list.size() - 1);
                combinationSumCore(candidates, start + 1, target, list, ret);
            }
            return;
        }
        List<Integer> list2 = new ArrayList<>(list);
        combinationSumCore(candidates, start + 1, target, list, ret);
        list2.add(candidates[start]);
        combinationSumCore(candidates, start, target - candidates[start], list2, ret);
    }

    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     * 拓扑排序，把一个有向无环图转换成一维的拓扑排序。拓扑排序是对有向无环图的一种排序。
     * 表示了顶点按边的方向出现的先后顺序。如果有环，则无法表示两个顶点的先后顺序。
     * 一个简单的求拓扑排序的算法：首先要找到任意入度为0的一个顶点，删除它及所有相邻的边，再找入度为0的顶点，
     * 以此类推，直到删除所有顶点。顶点的删除顺序即为拓扑排序。这里要用到BFS。
     * <p/>
     * 1）首先计算每个点的入度。
     * <p/>
     * 2）把入度为0的节点加进队列。
     * <p/>
     * 3）用队列进行BFS操作，每次从队列中拿出节点的时候，更新它的后继结点的入度（后继结点入度减1）。
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        //record 0 in-degree node
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            ArrayList<DirectedGraphNode> neighbors = node.neighbors;
            for (DirectedGraphNode neighbor : neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1);
                }
            }
        }
        ArrayList<DirectedGraphNode> ret = new ArrayList<>();
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                queue.add(node);
            }
        }
        //BFS
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            ret.add(node);
            ArrayList<DirectedGraphNode> neighbors = node.neighbors;
            for (DirectedGraphNode neighbor : neighbors) {
                int indegree = map.get(neighbor) - 1;
                if (indegree == 0) {
                    queue.add(neighbor);
                    map.remove(neighbor);
                } else {
                    map.put(neighbor, indegree);
                }
            }
        }
        return ret;

    }

    /**
     * Get all distinct N-Queen solutions
     *
     * @param n: The number of queens
     * @return: All distinct solutions
     * For example, A string '...Q' shows a queen on forth position
     */
    ArrayList<ArrayList<String>> solveNQueens(int n) {
        // write your code here
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        if (n < 1) return ret;
        int[] record = new int[n];
        solveNQueuesCore(n, 0, record, ret);
        return ret;
    }

    public void solveNQueuesCore(int n, int curRow, int[] record, ArrayList<ArrayList<String>> ret) {
        if (curRow == n) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < record.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == record[i]) sb.append("Q");
                    else sb.append(".");
                }
                list.add(sb.toString());
            }
            ret.add(list);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(record, curRow, col)) {
                record[curRow] = col;
                solveNQueuesCore(n, curRow + 1, record, ret);
                record[curRow] = 0;
            }
        }
    }

    public boolean isValid(int[] record, int curRow, int col) {
        for (int k = 0; k < curRow; k++) {
            if (col == record[k] || Math.abs(k - curRow) == Math.abs(record[k] - col)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param start, a string
     * @param end,   a string
     * @param dict,  a set of string
     * @return a list of lists of string
     */
    public List<List<String>> findLadders(String start, String end,
                                          Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);

        bfs(map, distance, start, end, dict);

        List<String> path = new ArrayList<String>();

        dfs(ladders, path, end, start, distance, map);

        return ladders;
    }

    void dfs(List<List<String>> ladders, List<String> path, String crt,
             String start, Map<String, Integer> distance,
             Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(start)) {
            Collections.reverse(path);
            ladders.add(new ArrayList<String>(path));
            Collections.reverse(path);
        } else {
            for (String next : map.get(crt)) {
                if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) {
                    dfs(ladders, path, next, start, distance, map);
                }
            }
        }
        path.remove(path.size() - 1);
    }

    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
             String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }

        while (!q.isEmpty()) {
            String crt = q.poll();

            List<String> nextList = expand(crt, dict);
            for (String next : nextList) {
                map.get(next).add(crt);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }

    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }

        return expansion;
    }

    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxSum)
                maxSum = sum;
            if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }

    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        int row = triangle.length;
        int[][] s = new int[row][];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            int col = i + 1;
            s[i] = new int[col];
            if (i == 0) {
                s[i][0] = triangle[i][0];
                if (i == row - 1) {
                    if (s[i][0] < min) min = s[i][0];
                }
            } else {
                for (int j = 0; j < i + 1; j++) {
                    if (j == 0) {
                        s[i][j] = s[i - 1][j] + triangle[i][j];
                    } else if (j == i) {
                        s[i][j] = s[i - 1][j - 1] + triangle[i][j];
                    } else {
                        s[i][j] = Math.min(s[i - 1][j - 1], s[i - 1][j]) + triangle[i][j];
                    }
                    if (i == row - 1) {
                        if (s[i][j] < min) min = s[i][j];
                    }
                }
            }

        }
        return min;
    }

    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        // write your code here
        if (n < 0) return -1;
        if (n == 0 || n == 1) return 1;
        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[n];
    }

    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m <= 0 || n <= 0) return 0;
        int s[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                s[i][0] = 1;
            } else {
                while (i < m) {
                    s[i][0] = 0;
                    i++;
                }
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 0) {
                s[0][j] = 1;
            } else {
                while (j < n) {
                    s[0][j] = 0;
                    j++;
                }
                break;
            }
        }
        for (int p = 1; p < m; p++) {
            for (int q = 1; q < n; q++) {
                if (obstacleGrid[p][q] == 1) {
                    s[p][q] = 0;
                    continue;
                }
                if (obstacleGrid[p - 1][q] == 1) {
                    s[p][q] = s[p][q - 1];
                } else if (obstacleGrid[p][q - 1] == 1) {
                    s[p][q] = s[p - 1][q];
                } else {
                    s[p][q] = s[p - 1][q] + s[p][q - 1];
                }
            }
        }
        return s[m - 1][n - 1];
    }

    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        int col = m;
        int row = A.length;
        int[][] s = new int[row][col + 1];
        for (int j = 0; j <= col; j++) {
            if (j >= A[0]) {
                s[0][j] = A[0];
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j <= col; j++) {
                if (j < A[i]) s[i][j] = s[i - 1][j];
                else {
                    s[i][j] = Math.max(s[i - 1][j], s[i - 1][j - A[i]] + A[i]);
                }
            }
        }
        return s[row - 1][col];
    }

    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    /*
    * dp1[i]:以第i个数结尾的连续子序列最大乘积

dp2[i]:以第i个数结尾的连续子序列最小乘积
转移方程:

dp1[i]=max(data[i],dp1[i-1]*data[i],dp2[i-1]*data[i]);

dp2[i]=min(data[i],dp1[i-1]*data[i],dp2[i-1]*data[i]);

最后遍历dp1得到最大值即为答案。
     */
    public int maxProduct(int[] nums) {
        // write your code here
        int ret = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int localMax = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            int localMin = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
            max = localMax;
            min = localMin;
            ret = max > ret ? max : ret;
        }
        return ret;
    }

    /**
     * Determine whether s3 is formed by interleaving of s1 and s2.
     *
     * @param s1, s2, s3: As description.
     * @return: true or false.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3 == null || s2 == null || s1 == null) return false;
        if (s1.equals("")) {
            return s2.equals(s3);
        }
        if (s2.equals("")) {
            return s1.equals(s3);
        }
        int length1 = s1.length();
        int length2 = s2.length();
        int length3 = s3.length();
        if (length1 + length2 > length3) {
            return false;
        }
        boolean[][] dp = new boolean[length1 + 1][length2 + 1];
        for (int i = 0; i < length1; i++) {
            if (s1.charAt(i) == s3.charAt(i)) {
                dp[i + 1][0] = true;
            }
        }

        for (int j = 0; j < length2; j++) {
            if (s2.charAt(j) == s3.charAt(j)) {
                dp[0][j + 1] = true;
            }
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]) {
                    dp[i][j] = true;
                }
                if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[length1][length2];

    }

    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    /*
    *首先定义这样一个函数——edit(i, j)，它表示第一个字符串的长度为i的子串到第二个字符串的长度为j的子串的编辑距离。

显然可以有如下动态规划公式：

if i == 0 且 j == 0，edit(i, j) = 0
if i == 0 且 j > 0，edit(i, j) = j
if i > 0 且j == 0，edit(i, j) = i
if i ≥ 1  且 j ≥ 1 ，edit(i, j) == min{ edit(i-1, j) + 1, edit(i, j-1) + 1, edit(i-1, j-1) + f(i, j) }，当第一个字符串的第i个字符不等于第二个字符串的第j个字符时，f(i, j) = 1；否则，f(i, j) = 0。

    *
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        if (word1 == null || word1.equals("")) return word2.length();
        if (word2 == null || word2.equals("")) return word1.length();
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        dp[0][0] = 0;
        for (int j = 1; j <= length2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= length1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                int f = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + f));
            }
        }
        return dp[length1][length2];
    }

    /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
    /*
    *一般来说，如果题目里面给出两个字符串，基本是两种思路，一种就是递归判断，一种就是动态规划，
    * 这里我们可以用f(i,j)表示S中前i个字符串中，T的前j个字符出现的次数，不管S[i]和T[j]相不相等，首先f(i,j)=f(i-1,j)，
    * 其次要是S[i]==T[j]的话，f(i,j) = f(i-1,j)+f(i-1,j-1),可以看到，i的状态只与i-1有关，于是可以用滚动数组来进行优化。
    * 代码类似01背包。
     */
    public int numDistinct(String S, String T) {
        // write your code here
        if (S == null || S.equals("")) return 0;
        if (T == null || T.equals("")) return 1;
        int length1 = S.length();
        int length2 = T.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        //dp[0][0]=0;
        for (int i = 0; i < length1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[length1][length2];

    }

    /**
     * @param s:    A string s
     * @param dict: A dictionary of words dict
     */
    public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
        if (s == null || s.length() == 0) {
            return true;
        }
        int wordMaxLEngth = maxLength(dict);
        boolean canSegment[] = new boolean[s.length() + 1];
        canSegment[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            canSegment[i] = false;
            for (int j = 1; j <= i && j <= wordMaxLEngth; j++) {
                if (canSegment[i - j] != true) {
                    continue;
                }
                if (dict.contains(s.substring(i - j, i))) {
                    canSegment[i] = true;
                    break;
                }
            }
        }
        return canSegment[s.length()];
    }

    public int maxLength(Set<String> dict) {
        int maxLength = 0;
        for (String item : dict) {
            maxLength = Math.max(item.length(), maxLength);
        }
        return maxLength;
    }

    /**
     * @param num: A list of integers
     * @return an integer
     */
    /*
    * 遍历数组生成哈希表

  遍历数组

    序列长度1 = 找连续序列函数(当前值，增加方向)

    序列长度2 = 找连续序列函数(当前值 - 1，减少方向)

    如果序列长度1 + 序列长度2 > 当前最长序列，更新最长序列

  遍历结束
     */
    public int longestConsecutive(int[] num) {
        // write you code here
        if (num == null || num.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < num.length; i++) {
            set.add(num[i]);
        }
        int maxLength = 0;
        for (int i = 0; i < num.length; i++) {
            if (visited.contains(num[i])) {
                continue;
            }
            int ascLength = 0;
            int descLength = 0;
            int targetDesc = num[i] - 1;
            while (true) {
                if (set.contains(targetDesc)) {
                    descLength++;
                    visited.add(targetDesc);
                    targetDesc--;
                    continue;
                } else {
                    break;
                }
            }
            int targetAsc = num[i] + 1;
            while (true) {

                if (set.contains(targetAsc)) {
                    ascLength++;
                    visited.add(targetAsc);
                    targetAsc++;

                    continue;
                } else {
                    break;
                }
            }
            int length = 1 + descLength + ascLength;
            if (length > maxLength) maxLength = length;
            visited.contains(num[i]);
        }
        return maxLength;
    }

    /**
     * @param n an integer
     * @return the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        // Write your code here
        int index = 1;

        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int pMultiply2 = 0;
        int pMultiply3 = 0;
        int pMultiply5 = 0;
        while (index < n) {

            int uglyNum2 = uglyNumbers[pMultiply2] * 2;
            int uglyNum3 = uglyNumbers[pMultiply3] * 3;
            int uglyNum5 = uglyNumbers[pMultiply5] * 5;
            int num = Math.min(uglyNum2, Math.min(uglyNum3, uglyNum5));
            uglyNumbers[index++] = num;
            while (uglyNumbers[pMultiply2] * 2 <= num) pMultiply2++;
            while (uglyNumbers[pMultiply3] * 3 <= num) pMultiply3++;
            while (uglyNumbers[pMultiply5] * 5 <= num) pMultiply5++;
        }
        return uglyNumbers[n - 1];

    }

    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        // write your code here
        /*if(nums==null) return null;
        int length=nums.length;
        int[] ret=new int[length];
        ret[0]=nums[0];
        for(int i=1;i<length;i++){
            binarySort(nums,0,i-1,nums[i]);
            int mid=i/2;
            ret[i]=nums[mid];
        }
        return ret;*/
        // write your code here
        if (nums == null) return null;
        int length = nums.length;
        int[] ret = new int[length];
        ret[0] = nums[0];
        for (int i = 1; i < length; i++) {
            Arrays.sort(nums, 0, i + 1);
            int mid = i / 2;
            ret[i] = nums[mid];
        }
        return ret;
    }

    /*从小到大排序*/
    public void binarySort(int[] array, int start, int last, int num) {
        int end = last;
        int ret = -1;
        if (array[start] > num) {
            ret = start;
        } else if (array[end] < num) {
            ret = end + 1;
        } else {
            while (start <= end) {
                int mid = (start + end) / 2;
                if (array[mid] <= num && array[mid + 1] > num) {
                    ret = mid + 1;
                    break;
                }
                if (array[mid] < num) {
                    start = mid + 1;
                }
                if (array[mid] > num) {
                    end = mid - 1;
                }
            }
        }
        for (int i = last + 1; i > ret; i--) {
            array[i] = array[i - 1];
        }
        array[ret] = num;

    }

    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        // write your code here
        if (lists == null || lists.isEmpty()) return null;

        ListNode ret = null;
        ListNode node = null;
        int max = lists.size();
        Queue<ListNode> priorityQueue = new PriorityQueue<>(max, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) return -1;
                else if (o1.val > o2.val) return 1;
                else return 0;
            }
        });

        for (ListNode tmp : lists) {
            if (tmp != null)
                priorityQueue.add(tmp);
        }

        while (!priorityQueue.isEmpty()) {
            if (ret == null) {
                ret = priorityQueue.poll();
                node = ret;
            } else {
                ListNode tmp = priorityQueue.poll();
                node.next = tmp;
                node = tmp;
            }
            if (node.next != null) {
                priorityQueue.add(node.next);
            }
        }
        return ret;
    }

}



