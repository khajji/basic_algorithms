import java.util.Arrays;

//Runtime: O(V^3). Shortest path between all the pairs of nodes, works with negative cycle.
public class FloydWarshall {
	//gWeights[i][j] must contain the weight of the edge ij if there is an edge i->j, 0 if i=j, otherwise +infinity if there is no edge i->j
	/**
	 * 
	 * @param gWeights it's a matrix V*V where V is the number of verticies. gWeights[u][v] = w if there is an edge between u and v of weight w, gWeights[u][u]=0, gWeights[u][v]=DOUBLE.POSITIVE_INFINITY if there is no edge between u and v
	 * @return matrix[V][V] where matrix[u][v]=min distance to go from u to v
	 */
	public static double[][] floydWarshall(double[][] gWeights){
		double[][] minDistances = gWeights.clone();
		int[][] paths = new int[gWeights.length][gWeights[0].length]; //paths[i][j] contains the optimal predecessor of j to reach i
		//initialize the current predessors with the edges of the graph
		for (int i = 0; i < gWeights.length; i++) {
			for (int j = 0; j < gWeights[0].length; j++) {
				if (gWeights[i][j] < Double.POSITIVE_INFINITY && i!=j) { //means there is an edge between i and j
					paths[i][j]= i; //mark i as the predecessor of j to reach i
				} else {
					paths[i][j]=-1; //there is not edge, so mark -1
				}
			}
		}
		//for every pair test if there is a shorter path gowing through another node
		for (int k = 0; k < gWeights.length; k++) {// test if ik->kj shorter than current i->j
			for (int i = 0; i < gWeights.length; i++) {
				for (int j = 0; j < gWeights.length; j++) { //pair i,j

					if (minDistances[i][j]> minDistances[i][k]+minDistances[k][j]) {
						minDistances[i][j] = minDistances[i][k]+minDistances[k][j];
						paths[i][j]=paths[k][j]; //the predecessor of j to reach i is the predecessor of j to reach k
					}
				}	
			}
		}
		//if one distance from a node to itself changed to a negative value, it means that there is a negative cycle in the graph, => return null
		for (int i = 0; i < minDistances.length; i++) {
			if (minDistances[i][i]!=0) {
				return null; //there is a negative cycle in the graph
			}
		}
		return minDistances;
	}
	
	
	public static void main(String[] args) {
		double[][] gWeights = new double[][] {
				{0,3,6,15},
				{Double.POSITIVE_INFINITY,0,-2,Double.POSITIVE_INFINITY},
				{Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0,2},
				{1,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0}};

		System.out.println("The weights matrix representing the graph :");
		for (int i = 0; i < gWeights.length; i++) {
			System.out.println(Arrays.toString(gWeights[i]));
		}
		System.out.println();
		System.out.println("Distances between all the pairs of node (Floyd Warshall Algo)");
		System.out.println("The shortest distances are ");
		double[][] distances = FloydWarshall.floydWarshall(gWeights);
		for (int i = 0; i < distances.length; i++) {
			System.out.println(Arrays.toString(distances[i]));
		}
	}

}
