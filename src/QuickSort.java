import java.util.ArrayList;

//Average runtime: O(nlogn) WorstCase runtime: O(n^2)
public class QuickSort {
	/**
	 * sorts the list using quick sort
	 * @param list list to sort
	 * @return sorted list
	 */
	public static ArrayList<Integer> quickSort(ArrayList<Integer> list){
		if (list.size()<=1) {
			return list; //There is no more division that can be made, so return the current list of 1 element (which is sorted) (or 0 element)
		}
		//select a pivot and put all the smaller elements than the pivot at the right and all the larger elements than the pivot at the left
		int piv = list.get(0);
		ArrayList<Integer> left = new ArrayList<Integer>(); ArrayList<Integer> right = new ArrayList<Integer>();//the merge sort could be done in place (with no additional copies), but we for code clarity we are choosing this version that copies lists
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i)<piv) left.add(list.get(i));
			else right.add(list.get(i));
		}
		//Apply the same quick sort to the left, add the pivot, and the result of the quick sort on the right
		ArrayList<Integer> result = quickSort(left);
		result.add(piv); result.addAll(quickSort(right));
		return result;
	}

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(5); list.add(1); list.add(4); list.add(2); list.add(3);
		System.out.println(QuickSort.quickSort(list));

	}

}
