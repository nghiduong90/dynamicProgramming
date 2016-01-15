package dynamicProgramming;

import java.util.*;

public class DP {

	
	
	/* helper function */
	public static int max (int x, int y) {
		return (x > y) ? x : y;
	}
	
    public static boolean wordPattern(String pattern, String str) {
        if (pattern == null  && str == null) return true;
        if (pattern == null || str == null) return false;
        Vector<String> v = getSubString(str);
        if (pattern.length() != v.size()) return false;
        
        HashMap<Character, String> map = new HashMap<Character, String>();
        for (int i = 0; i < v.size(); ++i) {
        	System.out.println ("letter = " + pattern.charAt(i) + " string = " + v.get(i));
            if (map.containsKey(pattern.charAt(i))){
            	if (map.get(pattern.charAt(i)).equals(v.get(i)) == false)
                    return false;
            } else {
                if (map.containsValue(v.get(i))) {
                	System.out.println ("here");
                	return false;
                }
                map.put (pattern.charAt(i), v.get(i));
            }
        }
        return true;
        
    }
    
    
    public static Vector<String> getSubString (String str) {
		if (str == null) return null;
		
		Vector<String> vectorString = new Vector<String>();
		int startIndex = 0;
		int endIndex = 0;
		String newString = "";
		for (int i = 0; i < str.length(); ++i) {
			if (Character.isWhitespace(str.charAt(i))) {
				endIndex = i;
				newString = str.substring(startIndex, endIndex);
				vectorString.add(newString);
				startIndex = i + 1;
			}
		}
		newString = str.substring(startIndex, str.length());
		vectorString.add(newString);
		
		return vectorString;
	}
	
	public static int pascalNum (int r, int c) {
		int [][] storage = new int[r + 1][c + 1];
		
		for (int i = 0; i <= r; i++) {
			storage[i][0] = 1;
			//System.out.println("1 loop, " + storage[i][0]);
		}
		
		for (int j = 0; j <= c; j++) {
			storage[0][j] = 1;
			//System.out.println("2 loop");
		}
		
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				storage[i][j] = storage[i - 1][j] + storage[i][j - 1]; 
				//System.out.println("3 loop");
			}
		}
		
		return storage[r - 1][c - 1];
	}
	
	
	public static boolean getChange (int [] coins, int value) {
		boolean [] v = new boolean [value + 1];
		int count = 1;
		v[0] = true;
		
		for (int i = 1; i <= value; i++) {
			v[i] = false;
		}
		
		for (int i = 1; i <= value; i++) {
			System.out.print(i + "    ");
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					v[i] = v[i - coins[j]];
				}
				else
					v[i] = false;
				
				if (i == value && v[i] == true)
					return true;
				
				
				System.out.print(v[i] + "  " + count++ + "  ");
			}
			
			System.out.print("\n");
		}
		
		return v[value];
	}
	
	
    public static int maxSubArray(int[] nums) {
        int sumMax = nums[0];
        int temp = nums[0];
        
        System.out.println ("S[" + 1 + "] = " + sumMax );
        
        for (int i = 1; i < nums.length; i++) {
            if (temp < 0) {
                temp = nums[i];
            	sumMax = 0;
            }
            else {
                temp = temp + nums[i];
                if (temp > sumMax)
                	sumMax = temp;
            }
                
            
            //if (sumMax <temp)
            //    sumMax = temp;
            
            System.out.println ("S[" + (i + 1) + "] = " + sumMax + " temp = " + temp);
        }
        
        	
        
        return sumMax;      
    }
    
    
    public static int LPS(char[] str) {
    	int [][] le = new int [str.length][str.length];
    	
    	for (int i = 0; i < str.length; i++) {
    		le[i][i] = 1;
    	}
    	
    	for (int i = 1; i < str.length; i++) {
    		le[i][i - 1] = 0;
    	}
    	
    	
    	for (int k = 1; k < str.length; k++) {
    		for (int i = 0; i < str.length - k; i++) {
    			if (str[i] == str[i + k]) {
    				le[i][i + k] = le[i + 1][i + k - 1] + 2;
    			}
    			else {
    				le[i][i + k] = max (le[i + 1][i + k], le[i][i + k - 1]);
    			}
    			//System.out.print (le[k][i] + "  ");
    		}
    		//System.out.println ("\n");
    	}
    	

    	for (int k = 0; k < str.length; k++) {
    		for (int i = 0; i < str.length; i++) {
    			System.out.print (le[k][i] + "  ");
    		}
    		System.out.println ("\n");
    	}
    	return le[0][str.length - 1];
    }
	
	public static void test0() {
		System.out.println("at pas[0,0] = " + pascalNum(0,0));
		System.out.println("at pas[1,0] = " + pascalNum(1,0));
		System.out.println("at pas[2,0] = " + pascalNum(2,0));
		System.out.println("at pas[0,1] = " + pascalNum(0,1));
		System.out.println("at pas[0,2] = " + pascalNum(0,2));
		System.out.println("at pas[1,1] = " + pascalNum(1,1));
		System.out.println("at pas[2,2] = " + pascalNum(2,2));
		System.out.println("at pas[2,3] = " + pascalNum(2,3));
		System.out.println("at pas[3,2] = " + pascalNum(3,2));
		System.out.println("at pas[4,4] = " + pascalNum(4,4));
	}
	
	
	
	public static void test1() {
		int [] coins = {1,5,2,2,10,25};
		int value = 10;
		
		System.out.println ("value of getChange(10) = " + getChange(coins, value));
	}
	
	
	public static void test2 () {
		int [] nums = {5,15,-30,10,-5,40,10,-4, 2};
		
		System.out.println ("max of contiguous sequence: " + maxSubArray(nums));
	}
	
	public static void test3() {
		char [] test = {'a','b','c','d','d','a','b','a','c'};
		System.out.println ("result of str abcddabacis " + LPS(test));

		char [] test2 = {'A','C','G','T','G','T','C','A','A','A','A','T','C','G'};
		System.out.println ("result of str ACGTGTCAAAATCG is " + LPS(test2));

	}
	
    public static int numberOfSolutions(int total, Vector<Integer> nums){
        
        int temp[][] = new int[nums.size()][total+1];
        
        for(int i=0; i <= nums.size(); i++){
            temp[i][0] = 1;
        }
        
        for(int i=1; i <= nums.size(); i++){
            for(int j=1; j <= total ; j++){
                if(nums.get(i-1) > j){
                    temp[i][j] = temp[i-1][j];
                }
                else{
                    temp[i][j] = temp[i][j - nums.get(i - 1)] + temp[i - 1][j];
                }
            System.out.print (temp[i][j] + " ");
            }
            System.out.println("");
        }
        return temp[nums.size()][total];
    }
    
    public static int minCost(int[][] costs) {
    	int houseCount = costs.length;
    	if (houseCount == 0) return 0;
    	int colorCount = costs[0].length;
    	int [][] costMatrix = new int [houseCount + 1][colorCount];
    	for (int j = 0; j < colorCount; ++j) costMatrix[0][j] = 0;
    	for (int i = 1; i <= houseCount; ++i) {
    		costMatrix[i][0] = Math.min(costMatrix[i - 1][1], costMatrix[i - 1][2]) + costs[i - 1][0];
    		costMatrix[i][1] = Math.min(costMatrix[i - 1][2], costMatrix[i - 1][0]) + costs[i - 1][1];
    		costMatrix[i][2] = Math.min(costMatrix[i - 1][0], costMatrix[i - 1][1]) + costs[i - 1][2];
    	}
    	
    	return costMatrix[houseCount][minAt(costMatrix[houseCount])];
    }
    
    public static int minAt(int [] values) {
        int min = values[0];
        int index = 0;
        for (int i = 1; i < values.length; ++i) {
            if (values[i] < min) {
                min = values[i];
                index = i;
            }
        }
        return index;
    }
    
    public static int listSquare(int number) {
    	if (number <= 0) return 0;
    	int result [] = new int [number + 1];
    	Arrays.fill(result, Integer.MAX_VALUE);
    	for (int i = 1; i * i <= number; ++i) 
    		result[i * i] = 1;
    	for (int i = 1; i <=number; ++i) {
    		System.out.println ("i = " + i);
    		for (int j = 1; i + j * j <= number; ++j) {
    			System.out.println ("         j = " + 1 + ", number is " + (i + j*j));
    			System.out.println ("         result is min of " + (result[i] + 1) + "  and " + result[i + j*j]);
    			result[i + j*j] = Math.min(result[i] + 1, result[i + j*j]);
    		}
    	}
    	System.out.println ("==============================================");
    	return result[number];
    }
    
    public static int longestIncreasingContinuousSubsequence(int[] A) {
        // Write your code here
        if (A.length <= 1) return 0;
        
        int maxIncreasingRange = 0;
        int currentCount = 1;
        int current = A[0];
        for (int i = 1; i < A.length; ++i) {
            if (A[i] > A[i - 1]) {
                currentCount++;
                if (i == A.length - 1 && (currentCount > maxIncreasingRange)) {
                	maxIncreasingRange = currentCount;
                }
            } else {
                if (currentCount > maxIncreasingRange) {
                    maxIncreasingRange = currentCount;
                    System.out.println ("forward");
                    System.out.println ("currentCount = " + currentCount + ",. maxIncreasingRange " + maxIncreasingRange);
                }
                currentCount = 1;
            }
        }
        currentCount = 1;
        for (int i = A.length - 2; i>= 0; --i) {
            if (A[i] > A[i + 1]) {
                currentCount++;
                if (i == 0 && (currentCount > maxIncreasingRange)) {
                	maxIncreasingRange = currentCount;
                }
            } else {
                if (currentCount > maxIncreasingRange) {
                    maxIncreasingRange = currentCount;
                    System.out.println ("currentCount = " + currentCount + ",. maxIncreasingRange " + maxIncreasingRange);
                }
                currentCount = 1;
            }
        }
        return maxIncreasingRange;
    }
    
    public static void wiggleSort(int[] nums) {
        if (nums.length <= 1) return;
        Arrays.sort(nums);

        ArrayList<Integer> helperList1 = new ArrayList<Integer>();
        ArrayList<Integer> helperList2 = new ArrayList<Integer>();
        for (int i = 0; i  < nums.length - 1; ++i) {
            helperList1.add(nums[i]);
            helperList2.add(nums[++i]);
        }
        if (nums.length % 2 != 0) helperList1.add(nums[nums.length - 1]);
        System.out.println (helperList1);
        System.out.println (helperList2);
     
        for (int i = 0; i < helperList1.size(); ++i) {
            if (i == (helperList1.size() - 1) && nums.length % 2 != 0) {
                nums[nums.length - 1] = helperList1.get(helperList1.size() - 1);
            }
           
            	System.out.println ("i = " + i);
                nums[2*i] = helperList1.get(i);
            
                if (helperList2.size() - 1 - i < 0) {
                	nums[2*i + 1] = helperList2.get(0);
                }
                nums[2*i + 1] = helperList2.get(helperList2.size() - 1 - i);
           
        }
    }
    
    public static int findPeakElement(int[] num) {

        int length = num.length;
        int left = 0;
        int right = length-1;

        int a=0;
        while(left<=right){
        	System.out.println ("left = " + left);
        	System.out.println ("right = " + right);
            if(left==right){
               a = left;
               break;
            } 
            int mid = (left+right)/2;
            System.out.println ("mid = " + mid);
            if(num[mid]<num[mid+1]){
                left = mid+1;
            }else{
                right = mid;
            }

        }
        return a;
    }
    
    public static void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        if (m != 0 && n != 0) {
            int size = m + n;
            int indexA = 0;
            int indexB = 0;
            int temp = A[indexA];
            
            for (int i = 0; i < size; ++i) {
            	System.out.println ("temp = " + temp + "    indexA = " + indexA + "        indexB = " + indexB);
                if (indexA > m - 1) {
                    A[i] = B[indexB];
                    System.out.println ("1 if");
                    if (indexB < n - 1) indexB++;
                } else if (indexB >= n) {
                    A[i] = A[indexA];
                    System.out.println ("2 if");
                    if (indexA < m - 1) indexA++;
                } else if (temp < B[indexB]) {
                    A[i] = temp;
                    System.out.println ("3 if");
                    if (indexA < m - 1) temp = A[++indexA];
                } else if (temp > B[indexB]) {
                	System.out.println ("4 if");
                    A[i] = B[indexB];
                    if (indexB < n - 1) indexB++;
                }
            }
        }
    }
    
    public static int minSubArray(int[] nums) {
        // write your code
        if (nums == null || nums.length == 0) return 0;
        
        int minSum [] = new int [nums.length];
        minSum[0] = nums[0];
        int currentMin = minSum[0];
        
        for (int i = 1; i < nums.length; ++i) {
            minSum[i] = Math.min(nums[i], minSum[i - 1] + nums[i]);
            System.out.println ("minSum = " + minSum[i] + "      currentMin = " + currentMin);
            if (currentMin > minSum[i]) currentMin = minSum[i];
        }
        
        return currentMin;
    }
	
    public static void main(String[] args)  {
    	int [] A = {1,-1,-2,1};
    	//A[0] = 1;
    	//A[1] = 2;
    	//A[2] = 3;
    	//int [] B = {4,5};
    	//mergeSortedArray(A,3,B,2);
    	///wiggleSort(test);
    	//for (int i = 0; i < A.length; ++i) {
    	//	System.out.print (A[i] + " ");
    	//}
    	System.out.println (minSubArray(A));
    	
    	
    }
}
