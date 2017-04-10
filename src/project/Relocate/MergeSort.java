package project.Relocate;
/**
 * @author Jenny Feng Chen
 * This class would be use for sorting cities by outlook
 */
import java.util.ArrayList;

public class MergeSort {
	private static ArrayList<Comparable> aux;
	
	/**
	 * merge sort
	 * @param x the input array containing times of jobs that need to be sorted.
	 * @param n the size of the input array
	 */
	public static void sortMerge ( ArrayList<Comparable> x, int n ) {
		aux = new ArrayList<Comparable>();
		for (int i = 0; i < n; i++)
			aux.add(null);
		sortMerge(x, 0, n-1);
	}

	/***
	 * Split and merge sort recursion 
	 * @param x array 
	 * @param lo index of the lowest element
	 * @param hi index of the highest element
	 */
	private static void sortMerge(ArrayList<Comparable> x, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        //recursively split and merge sort the left half 
        sortMerge(x, lo, mid);
        //recursively split and merge sort the front half
        sortMerge(x, mid + 1, hi);
        //combine the two half sorted arrays
        merge(x, mid,lo, hi);
	}

	/***
	 * This merge and sort the array with extra memory 
	 * @param x the original array 
	 * @param mid index of the middle element
	 * @param lo index of the lowest element
	 * @param hi index of the highest element
	 */
	private static void merge(ArrayList<Comparable> x, int mid,int lo, int hi) {
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
        	aux.set(k, x.get(k));
        } 
        int i = lo, j = mid+1;
        // merge back to x[]
        //sort elements to the original array from the aux when merging
        for (int k = lo; k <= hi; k++) {
            if (i > mid)  
            	x.set(k, aux.get(j++));
            else if (j > hi)                     
            	x.set(k, aux.get(i++));
            else if (aux.get(j).compareTo(aux.get(i)) == 1)
            	x.set(k, aux.get(j++));
            else  
                //if no changes above are made, meaning the two elements which are compared are sorted
                //then copy back the element from aux to the original
            	x.set(k, aux.get(i++));
        }
		
	}
}