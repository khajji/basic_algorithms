import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

//This is an implementation of Dijakstra algorithm. It computes the single source minimum distances for a positive weighted graph.
public class Dijakstra {
	/**
	 * Computes the minimum distances between s and all the other nodes for a POSITIVE weighted graph.
	 * @param s int representing the source node number
	 * @param gWeights it's a matrix V*V where V is the number of verticies. gWeights[u][v] = w if there is an edge between u and v of weight w, gWeights[u][u]=0, gWeights[u][v]=INTEGER.MAX_INT if there is no edge between u and v
	 * @param gAdjencyList it's an arraylist of size V (where V is the number of verticies) that contains the neighbors for all the nodes. gAdjencyList[u] is a list that contains the nodes v such that there is an edge u->v
	 * @return the minimal distance from s to every node. The keys are the nodes and the values are the minimum distances
	 */
	public static HashMap<Integer, Integer> dijakstra(int s, int[][] gWeights, ArrayList<ArrayList<Integer>> gAdjencyList) {
		HashMap<Integer, Integer> nonVisitedNodes = new HashMap<Integer, Integer>(); //contains the non visited nodes as a key and their current computed distance to s as a value
		PriorityQueue<int[]> minValue = new PriorityQueue<int[]>(10, new Comparator<int[]>() {
			public int compare(int[] arg0, int[] arg1) {
				if (arg0[1]==arg1[1]) {
					return new Integer(arg0[0]).compareTo(new Integer(arg1[0]));
				}	
				return new Integer(arg0[1]).compareTo(new Integer(arg1[1]));
			}
			
		});//will store the minimal value of the non visited nodes. Could optimize runtime if we store the two min values in variables.
		
		for(int i=0; i < gAdjencyList.size(); i++){
			nonVisitedNodes.put(i, Integer.MAX_VALUE);
			if(i!=s) minValue.add(new int[]{i,Integer.MAX_VALUE});
		} //initialize non visited nodes with infiniy distances
		
		HashMap<Integer, Integer> visitedNodes = new HashMap<Integer,Integer>(); //contains the nodes visited as a key and their distances to s as a value. Initially empty
		HashMap<Integer,Integer> parents = new HashMap<Integer,Integer>(); //contains the nodes as a key and the parent node in the optimal path from s as a value.
		
		//start with the node s.
		minValue.add(new int[]{s,0});
		parents.put(s, null);
		
		while(!nonVisitedNodes.isEmpty()){
			//visit the node that has the current minimal distance
			int currentNode = minValue.peek()[0]; int value = minValue.poll()[1];
			visitedNodes.put(currentNode, value);
			nonVisitedNodes.remove(currentNode);
			//update the distance of all his non visited neighbors
			for(int i=0; i< gAdjencyList.get(currentNode).size(); i++){
				int neighbor = gAdjencyList.get(currentNode).get(i);
				if(nonVisitedNodes.containsKey(neighbor) && value+gWeights[currentNode][neighbor] < nonVisitedNodes.get(neighbor)){
					int neighValue = value+gWeights[currentNode][neighbor] ;
					nonVisitedNodes.put(neighbor, neighValue);
					minValue.add(new int[]{neighbor, neighValue});
					parents.put(neighbor, currentNode);
				}
			}		
		}
		return visitedNodes;		
	}
	
	public static void main(String[] args) {
		int[][] gWeights = new int[][] {
				{0,5,-1,9,0,2},
				{5,0,2,-1,-1,-1},
				{-1,2,0,3,-1,-1},
				{9,-1,3,0,2,-1},
				{-1,-1,-1,2,0,3},
				{2,-1,-1,-1,3,0}};
		
		ArrayList<ArrayList<Integer>> gAdjency = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> neighbors0 = new ArrayList<Integer>(); neighbors0.add(1); neighbors0.add(3); neighbors0.add(5);
		ArrayList<Integer> neighbors1 = new ArrayList<Integer>(); neighbors1.add(0); neighbors1.add(2);
		ArrayList<Integer> neighbors2 = new ArrayList<Integer>(); neighbors2.add(1); neighbors2.add(3);
		ArrayList<Integer> neighbors3 = new ArrayList<Integer>(); neighbors3.add(2); neighbors3.add(0); neighbors3.add(4);
		ArrayList<Integer> neighbors4 = new ArrayList<Integer>(); neighbors4.add(3); neighbors4.add(5);
		ArrayList<Integer> neighbors5 = new ArrayList<Integer>(); neighbors5.add(0); neighbors5.add(4);
		gAdjency.add(neighbors0); gAdjency.add(neighbors1); gAdjency.add(neighbors2); gAdjency.add(neighbors3); gAdjency.add(neighbors4); gAdjency.add(neighbors5);
		
		int s=0;
		System.out.println("Dijakstra for source node "+s+": ");
		System.out.println("The shortest distances are "+Dijakstra.dijakstra(s, gWeights, gAdjency));
		

	}

}
