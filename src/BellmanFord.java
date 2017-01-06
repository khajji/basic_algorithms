import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//This is an implementation of the Bellman ford algorithm. It computes the single source minimum distances for a weighted graph (could contain negative weights).
//Running time O(E*V) where E is the number of edges and V is the number of verticies
public class BellmanFord {
	/**
	 * Computes the minimum distances between s and all the other nodes for a weighted graph (can have negative weights).
	 * @param s int representing the source node number
	 * @param gWeights it's a matrix V*V where V is the number of verticies. gWeights[u][v] = w if there is an edge between u and v of weight w, gWeights[u][u]=0, gWeights[u][v]=DOUBLE.POSITIVE_INFINITY if there is no edge between u and v
	 * @param edges an arraylist of pairs of nodes (int[2]) where every pair represents an edge from int[0]->int[1]
	 * @return the minimal distance from s to every node. The keys are the nodes and the values are the minimum distances. returns null if it detects a minimal cycle
	 */
	public static HashMap<Integer, Double> bellmanFord(int s, double[][] gWeights, ArrayList<int[]> edges){
		HashMap<Integer, Double> minDistances = new HashMap<Integer, Double>();
		HashMap<Integer, Integer> parents = new HashMap<Integer, Integer>();
		for(int i=0; i<gWeights.length; i++){
			minDistances.put(i, Double.POSITIVE_INFINITY);
		}
		minDistances.put(s, 0.0);
		//Iterate V-1 times and update all the edges
		for(int i=0; i<gWeights.length-1; i++){
			//update all the edges, if in one iteration there were no changes, then we converged to an optimal solution
			if(!bfIteration(s, gWeights, edges, minDistances, parents)) break;
		}
		//If after V-1 iteration there is still an optimization that can be made, this means that there is the presence of a negative cycle => return null
		if (bfIteration(s, gWeights, edges, minDistances, parents)) {
			return null;
		}
		return minDistances;	
	}
	
	public static boolean bfIteration(int s, double[][] gWeights, ArrayList<int[]> edges, HashMap<Integer, Double> minDistances, HashMap<Integer, Integer> parents){
		boolean didUpdate = false;
		for (int[] e : edges) {
			if(minDistances.get(e[1])> (minDistances.get(e[0])+gWeights[e[0]][e[1]])){
				minDistances.put(e[1], minDistances.get(e[0])+gWeights[e[0]][e[1]]);
				parents.put(e[1], e[0]); didUpdate=true;
			}
		}	
		return didUpdate;
	}

	public static void main(String[] args) {
		double[][] gWeights = new double[][] {
				{0,4,5,6,Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY,0,-3,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,4},
				{Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0,2,Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0,2},
				{Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,1,0}};
		
		//Build edges list
		ArrayList<int[]> edges = new ArrayList<int[]>();
		for (int i = 0; i < gWeights.length; i++) {
			for (int j = 0; j < gWeights[0].length; j++) {
				if (gWeights[i][j]<Double.POSITIVE_INFINITY && i!=j) {
					edges.add(new int[]{i,j});
				}
			}
		}
		int s=0;
		System.out.println("The weights matrix representing the graph :");
		for (int i = 0; i < gWeights.length; i++) {
			System.out.println(Arrays.toString(gWeights[i]));
		}
		
		System.out.println("Bellman Ford for source node "+s+": ");
		System.out.println("The shortest distances are "+BellmanFord.bellmanFord(s, gWeights, edges));
		

	}

}
