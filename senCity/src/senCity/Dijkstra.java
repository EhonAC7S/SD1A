package senCity;

import java.util.ArrayList;

public class Dijkstra {
	// Dijkstra's algorithm to find shortest path from s to all other nodes
	public static int[] dijkstra(GraphDesGPS G, int s) {
		final double[] dist = new double[G.taille()]; // shortest known distance
		final int[] pred = new int[G.taille()]; 
		final boolean[] visited = new boolean[G.taille()]; // all false
									
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Double.MAX_VALUE;
		}
		dist[s] = 0.;
		for (int i = 0; i < dist.length; i++) {
			final int next = minVertex(dist, visited);
			if (next==-1) {
				return pred;
			}
			
			visited[next] = true;
			// The shortest path to next is dist[next] and via pred[next].
			final int[] n = G.neighbors(next); // les voisins (les sommets
												// touchés)
			for (int j = 0; j < n.length; j++) {
				final int v = n[j];
				final double d = dist[next] + G.getWeight(next, v);
				if (dist[v] > d) {
					dist[v] = d;
					pred[v] = next;
				}
			}
		}
		return pred; // (ignore pred[s]==0!)
	}

	private static int minVertex(double[] dist, boolean[] v) {
		double x = Double.MAX_VALUE;
		int y = -1; // graph not connected, or no unvisited vertices
		for (int i = 0; i < dist.length; i++) {
			if (!v[i] && dist[i] < x) {
				y = i;
				x = dist[i];
			}
		}
		return y;
	}

	public static void printPath(int[] pred, int s, int e) {
		final ArrayList<Integer> path = new ArrayList<Integer>();
		int x = e;
		while (x != s) {
			path.add(0, x);
			x = pred[x];
		}
		path.add(0, s);
		System.out.println(path);

	}
	public static void main(String[] args) {
		ArrayList<SommetGraph> gps = new ArrayList<SommetGraph>();
		for (int i=0;i<3;i++){
			String istr = String.valueOf(i);
			gps.add(new SommetGraph(new GPS(istr,istr)));
		}
		GraphDesGPS graph = new GraphDesGPS(gps,"test");
		graph.initaliser(1.5);
		Dijkstra.printPath(Dijkstra.dijkstra(graph, 0), 0, 2);
	}
}
