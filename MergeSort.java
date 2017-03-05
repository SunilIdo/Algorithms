package lab4;

import java.util.Arrays;
//import runtime.*;
import java.util.Random;

public class MergeSort {
	final int ARRAY_SIZE = 33;
	final int MAX_VAL = 1000;
	int[] theArray;

	// public sorter
	public int[] sort(int[] input,int[] input1) {
		int n = input.length;
		int[] tempStorage = new int[n];
		int[] tempStorage1 = new int[n];
		theArray = input;
//		System.out.println("Time for sorting algorthms mergesortPlus:");
//		long startTime = System.nanoTime();
//		mergeSortPlus(tempStorage1, 0, n- 1);
//		long endTime = System.nanoTime();
//		System.out.println(endTime-startTime);
		System.out.println("Time for sorting algorthms mergeSort:");
		long startTime1 = System.nanoTime();
		mergeSort(tempStorage, 0, n - 1);
		long endTime1 = System.nanoTime();
		System.out.println(endTime1-startTime1);
		return theArray;
	}

	/**
	 * Merges the ranges [lowerPointer,upperPointer-1] and
	 * [upperPointer,upperBound] in place
	 */
	private void merge(int[] tempStorage, int lowerPointer, int upperPointer, int upperBound) {
		int j = 0; // tempStorage index
		int lowerBound = lowerPointer;
		int n = upperBound - lowerBound + 1; // total number of elements to
												// rearrange

		// view the range [lowerBound,upperBound] as two arrays
		// [lowerBound, mid], [mid+1,upperBound] to be merged
		int mid = upperPointer - 1;

		while (lowerPointer <= mid && upperPointer <= upperBound) {
			if (theArray[lowerPointer] < theArray[upperPointer]) {
				tempStorage[j++] = theArray[lowerPointer++];
			} else {
				tempStorage[j++] = theArray[upperPointer++];
			}
		}
		// left array may still have elements -- insert them into tempStorage
		while (lowerPointer <= mid) {
			tempStorage[j++] = theArray[lowerPointer++];
		}
		// right array may still have elements -- insert these into tempStorage
		while (upperPointer <= upperBound) {
			tempStorage[j++] = theArray[upperPointer++];
		}
		// replace the range [lowerBound,upperBound] in theArray with
		// the range [0,n-1] just created in tempStorage
		for (j = 0; j < n; ++j) {
			theArray[lowerBound + j] = tempStorage[j];
		}

	}

	void mergeSortPlus(int[] tempStorage, int lower, int upper) {
		if (lower == upper) {
			return;
		} else {
			int mid = (lower + upper) / 2;
			if (mid - lower <= 20) {
				int[] leftArray = Arrays.copyOfRange(tempStorage, lower, mid);
				insertionSort(leftArray);
			} else
				mergeSort(tempStorage, lower, mid); // sort left half
			if (upper - mid + 1 <= 20) {
				int[] rightArray = Arrays.copyOfRange(tempStorage, mid + 1, upper);
				insertionSort(rightArray);
			} else
				mergeSort(tempStorage, mid + 1, upper); // sort right half
			merge(tempStorage, lower, mid + 1, upper); // merge them
		}
	}

	void mergeSort(int[] tempStorage, int lower, int upper) {
		if (lower == upper) {
			return;
		} else {
			int mid = (lower + upper) / 2;
			mergeSort(tempStorage, lower, mid); // sort left half
			mergeSort(tempStorage, mid + 1, upper); // sort right half
			merge(tempStorage, lower, mid + 1, upper); // merge them
		}
	}

	void insertionSort(int[] arr) {
		int len = arr.length;
		int temp = 0;
		int j = 0;
		for (int i = 1; i < len; ++i) {
			temp = arr[i];
			j = i;
			while (j > 0 && temp < arr[j - 1]) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = temp;
		}
	}

	// set up routines
	public static void main(String[] args) {
		MergeSort ms = new MergeSort();
//		int[] testArray={5,6,20,45,76, 89, 22,82,1,10,12,7,3,51,72,23,35};
		int[] testArray=new int[20];
		int[] testArray1=new int[20];
		 for(int i = 0; i < testArray.length; i++) {
			 testArray[i] = (int)(Math.random()*990 + 10);
		    }
		 testArray1=testArray;
		ms.sort(testArray,testArray1);
//		 ms.insertionSort(testArray);
		for(int e:testArray1){System.out.print(e+",");}
	}

	public void testMerge() {

		// create an array whose left half is sorted and whose
		// right half is sorted
		loadArray();
		int n = theArray.length;
		int[] tempStorage = null;
		int half = n / 2;
		tempStorage = new int[n];
		mergeSortPlus(tempStorage, 0, half);
		tempStorage = new int[n];
		mergeSortPlus(tempStorage, (n / 2) + 1, n - 1);

		// begin display -- start with left half sorted, right half sorted
		System.out.println();
		System.out.println("an array with left half sorted and right half sorted:");
		displayArray(theArray);
		System.out.println();
		System.out.println("merging the two halves...");

		// call the merge method and display results
		tempStorage = new int[n];
		merge(tempStorage, 0, half + 1, n - 1);
		displayArray(theArray);
	}

	public void testMergeSort() {
		loadArray();
		System.out.println("initial array:");
		displayArray(theArray);
		int n = theArray.length;
		int[] tempStorage = new int[n];
		mergeSortPlus(tempStorage, 0, n - 1);
		System.out.println();
		System.out.println("sorted array:");
		displayArray(theArray);

	}

	private void loadArray() {
		theArray = new int[ARRAY_SIZE];
		Random r = new Random();
		int next = 0;
		for (int i = 0; i < ARRAY_SIZE; ++i) {
			next = r.nextInt();
			next = Math.abs(next % MAX_VAL);
			// System.out.println("next int "+next);
			theArray[i] = Math.abs(next);
		}
	}

	private void displayArray(int[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
	}

}
