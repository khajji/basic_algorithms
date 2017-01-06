import java.util.ArrayList;

//This is an implementation of the 0/1 knapsack problem (each object can only be picked once)
//Runtime is O(KC) where K is the number of items and C the maximum weight of the bag
public class BinKnapsack {
	/**
	 * This is an implementation of the 0/1 knapsack problem (each object can only be picked once)
	 * @param v an array of size k representing the values of the different items
	 * @param w an array of size k representing the weights of the different items
	 * @param c the maximum weight allowed
	 * @return a list of ids representing the items selected such that they maximize the value and do not exceed total weight C
	 */
	public static ArrayList<Integer> binaryKnapsack(int[] v, int [] w, int c) {
		int k = v.length; int[][] sol = new int[k+1][c+1]; 
		for (int i = 1; i < k+1; i++) {
		//compute the optimal solutions when we have only i items (for the weights from 0 to c)	
			for (int j = 0; j < c+1; j++) {
				if (w[i-1]>j) {//note that the index i=1 corresponds to the first item which has a weight of index i-1=0 w[i-1] and value v[i-1]
					sol[i][j] = sol[i-1][j];//if the weight is bigger than the allowed weight j then the optimal solution is the same as the optimal solution for weight j without the considering the item i
				} else {
					sol[i][j]=Math.max((v[i-1]+sol[i-1][j-w[i-1]]), sol[i-1][j]); //else the weight is the maximum between the optimal solution when selecting the item i and the optimal solution without selecting the item i
				}		 
			}
		}
		return collectSelectedObjects(w, sol, v.length-1, c, new ArrayList<Integer>());
	}
	
	private static ArrayList<Integer> collectSelectedObjects(int [] w, int[][]sol, int i, int j, ArrayList<Integer> objects){
		//if solution with one less item (and same weight) has the same optimal value, then the object was not added to the optimal solution. Otherwise, it was
		if (sol[i][j] == 0) {
			return objects;
		} else if (sol[i][j]!= sol[i-1][j]) {
			objects.add(i);
			return collectSelectedObjects(w, sol, i-1, j-w[i], objects);
		} else {
			return collectSelectedObjects(w, sol, i-1, j, objects);
		}	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] v = new int []{30, 14, 16, 9};
		int[] w = new int[] {6, 3, 4, 2};
		int c = 10;
		
		System.out.println(BinKnapsack.binaryKnapsack(v, w, c));
	}

}
