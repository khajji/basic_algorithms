import java.util.ArrayList;

//Computes a topological sorting of the graph. This version of the algorithm assumes that a topological sorting exists in the graph. In a graph a topological sorting is a sorting of nodes v1, ..,vn such that vn cannot be reached before vn-1, which cannot be reached before vn-2, etc...
//Runtime: O(V)
public class TopologicalSort {
	/**
	 * Computes a topological sorting of the graph. This version of the algorithm assumes that a topological sorting exists in the graph.
	 * @param adjencyList it's an arraylist of size V (where V is the number of verticies) that contains the neighbors for all the nodes. gAdjencyList[u] is a list that contains the nodes v such that there is an edge u->v
	 * @return a list representing a topological sorting of the graph 
	 * In a graph a topological sorting is a sorting of nodes v1, ..,vn such that vn cannot be reached before vn-1, which cannot be reached before vn-2, etc...
	 */
	public static ArrayList<Integer> kahnAlgo(ArrayList<ArrayList<Integer>> adjencyList){
		boolean[] visited = new boolean [adjencyList.size()];
		ArrayList<Integer> order = new ArrayList<Integer>();	
		kahnAlgo(adjencyList, order, visited, 0);
		return order;
	}
	
	private static void kahnAlgo(ArrayList<ArrayList<Integer>> adjencyList, ArrayList<Integer> order, boolean[] visited, int i){
		if(i==adjencyList.size()){
		}else if(visited[i]==false){
			visited[i]=true;
			for(int c=0; c<adjencyList.get(i).size(); c++){
				kahnAlgo(adjencyList, order, visited, adjencyList.get(i).get(c));
			}
			order.add(0,i);
			kahnAlgo(adjencyList, order, visited, i+1);	
		} else {
			kahnAlgo(adjencyList, order, visited, i+1);
		}		
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> gAdjency = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> neighbors0 = new ArrayList<Integer>(); neighbors0.add(2); 
		ArrayList<Integer> neighbors1 = new ArrayList<Integer>(); neighbors1.add(2); neighbors1.add(3);
		ArrayList<Integer> neighbors2 = new ArrayList<Integer>(); neighbors2.add(4); 
		ArrayList<Integer> neighbors3 = new ArrayList<Integer>(); neighbors3.add(5); 
		ArrayList<Integer> neighbors4 = new ArrayList<Integer>(); neighbors4.add(5); neighbors4.add(7);
		ArrayList<Integer> neighbors5 = new ArrayList<Integer>(); neighbors5.add(6); 
		ArrayList<Integer> neighbors6 = new ArrayList<Integer>(); 
		ArrayList<Integer> neighbors7 = new ArrayList<Integer>();
		gAdjency.add(neighbors0); gAdjency.add(neighbors1); gAdjency.add(neighbors2); gAdjency.add(neighbors3); gAdjency.add(neighbors4); gAdjency.add(neighbors5); gAdjency.add(neighbors6); gAdjency.add(neighbors7);
		System.out.println(gAdjency);
		System.out.println(kahnAlgo(gAdjency));
	}
	

}
