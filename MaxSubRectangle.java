import java.util.Arrays;

public class MaxSubRectangle 
{
	// -------======== GLOBAL =========--------
	static int maxSum;
	static int iLeft, jLeft;
	static int iRight, jRight;
	static int ROW, COL;
	static int start, end;

	// -------========= Matrix Constructor=======-----------
	public MaxSubRectangle(int[][] data)
	{ 
		MaxSubRectangle.ROW = data.length;
		MaxSubRectangle.COL = data[0].length;
		maxSumRectangle(data);
	}
	// -----========== Getters ===========--------
	public int getMaxSum() {
		return maxSum;
	}
	public int getILeft() {
		return iLeft;
	}
	public int getJLeft() {
		return jLeft;
	}
	public int getIRight() {
		return iRight;
	}
	public int getJRight() {
		return jRight;
	}

	// -----========= Best Algo for 1-D Array ==========---------
	public static int kadaneAlgo(int arr[], int start, int end) {    //find max sum and starting and ending location
		int sum = 0, maxSum = Integer.MIN_VALUE;

		end = -1;    //at first no place is selected

		int tempStart = 0;    //starting from 0

		for (int i = 0; i < ROW; i++) {
			sum += arr[i];
			if (sum < 0) {
				sum = 0;
				tempStart = i+1;
			}else if (sum > maxSum) {     //get maximum sum, and update start and end index
				maxSum = sum;
				MaxSubRectangle.start = tempStart;
				MaxSubRectangle.end = i;
			}
		}

		if (MaxSubRectangle.end != -1)
			return maxSum;

		//when all elements are negative in the array
		maxSum = arr[0];
		MaxSubRectangle.start = MaxSubRectangle.end = 0;

		// Find the maximum element in array
		for (int i = 1; i < ROW; i++) {
			if (arr[i] > maxSum) {
				maxSum = arr[i];
				MaxSubRectangle.start = MaxSubRectangle.end = i;
			}
		}
		return maxSum;
	}
	// ---------======= Find maxSumRectangle using best =======----------
	public static void maxSumRectangle(int[][] M) 
	{
		int maxSum = Integer.MIN_VALUE;
		int endLeft = -1, endRight = -1, tempStartIndex = -1, tempEndIndex = -1;

		//int left, right;
		int[] temp = new int[ROW];
		int sum;

		for (int left = 0; left < COL; left++) {
			Arrays.fill(temp, 0); // initialize 0

			for (int right = left; right < COL; ++right) {
				for (int i = 0; i < ROW; ++i)    //for each row, find the sum
					temp[i] += M[i][right];
				/*
				for (int i = 0; i < COL; ++i)    //for each col, find the sum
					temp[i] += M[right][i];
				*/
				sum = kadaneAlgo(temp, start, end);    //find sum of rectangle (top, left) and (bottom right)

				if (sum > maxSum) {    //find maximum value of sum, then update corner points
					maxSum = sum;
					endLeft = left; // for loop up
					endRight = right; // for loop
					tempStartIndex = start; 
					tempEndIndex = end;
				}
			}
			MaxSubRectangle.maxSum = maxSum;

			
			MaxSubRectangle.iLeft = tempStartIndex; 
			MaxSubRectangle.jLeft = endLeft; 
			
			//cout << "(Top, Left) ("<<endTop<<", "<<endLeft<<")"<<endl;
			MaxSubRectangle.iRight = tempEndIndex; //maybe need to swap
			MaxSubRectangle.jRight = endRight;
			


		}
		
		System.out.println("The max Sum is: "+maxSum);	
		System.out.println("Bottom Right: (" + iRight + "," + jRight + ")");		
		System.out.println("Top Left: (" + iLeft + "," + jLeft + ")");
	}
	public static void main(String[] args) {
		 int[][] M = {
				{1, 2, -1, -4, -20},
				{-8, -3, 4, 2, 1},
				{3, 8, 10, 1, 3},
				{-4, -1, 1, 7, -6}
							};
		MaxSubRectangle msr = new MaxSubRectangle(M);
		

	}
}
