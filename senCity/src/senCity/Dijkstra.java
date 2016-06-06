package senCity;

import java.util.ArrayList;

public class Dijkstra {
	// Dijkstra's algorithm to find shortest path from s to all other nodes
	public static int[] dijkstra(GraphDesGPS G, int s) {
		final double[] dist = new double[G.taille()]; // shortest known distance
		System.out.println(dist.length);										// from "s"
		final int[] pred = new int[G.taille()]; // preceeding node in path
		final boolean[] visited = new boolean[G.taille()]; // all false
		System.out.println("1");										// initially
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Double.MAX_VALUE;
		}
		dist[s] = 0.;
		for (int i = 0; i < dist.length; i++) {
			final int next = minVertex(dist, visited);
			visited[next] = true;
			System.out.println("2");
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
			System.out.println("3");
			if (!v[i] && dist[i] < x) {
				y = i;
				x = dist[i];
			}
		}
		return y;
	}

	public static void printPath(GraphDesGPS G, int[] pred, int s, int e) {
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
		Dijkstra.dijkstra(graph, 1);
	}
}
