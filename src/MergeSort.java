import java.util.ArrayList;

//Average runtime: O(nlogn) WorstCase runtime: O(n^2)
public class MergeSort {
	/**
	 * sorts the list using merge sort
	 * @param list list to sort
	 * @return sorted list
	 */
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> list) {
		if (list.size()>1) {
			int index = (list.size()-1)/2;
			return MergeSortedLists(mergeSort(new ArrayList<Integer>(list.subList(0, index+1))), mergeSort(new ArrayList<Integer>(list.subList(index+1, list.size()))));        
		} else {
			return list;
		}	       
	}
	
	private static ArrayList<Integer> MergeSortedLists(ArrayList<Integer> a, ArrayList<Integer> b) {
		//merges two sorted lists
		if (a.size() == 0 && b.size()>0) {
			return b;
		}else if(b.size() == 0){
			return a;
		}else{
			//both > 0
			ArrayList<Integer> c = new ArrayList<Integer>();
			int i= 0; int j=0;
			while (i<a.size() && j<b.size()) {
				if (a.get(i)<= b.get(j)) {
					c.add(a.get(i));
					i++;
				}
				else {
					c.add(b.get(j));
					j++;
				}

			}
			
			if (i<a.size()) {
				c.addAll(new ArrayList(a.subList(i, a.size())));
			} else if(j<b.size()){
				c.addAll(new ArrayList(b.subList(j,b.size())));
			}
			
			return c;
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(5); list.add(1); list.add(4); list.add(2); list.add(3);
		System.out.println(QuickSort.quickSort(list));

	}

}
