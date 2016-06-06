package senCity;

import java.util.ArrayList;

public class GraphDesGPS {
	private ArrayList<SommetGraph> ensDesGPS;
	private boolean[][] arcs;
	private double[][] dist;
	private String nom;
	
	public GraphDesGPS() { //on créé le graph vide
		this.ensDesGPS = new ArrayList<SommetGraph>();
		this.arcs = new boolean[0][0];
	}
	
	public GraphDesGPS(ArrayList<SommetGraph> sommets, String name) { //on créé le graph en ayant au préalable la liste des sommets
		this.ensDesGPS = sommets;
		this.nom=name;
		this.arcs = new boolean[sommets.size()][sommets.size()];
		this.dist = new double[sommets.size()][sommets.size()];
	}
	
	public void initaliser(double distanceMaxi) {
		for (int i=0;i<this.taille();i++) {
			this.setConnected(i, distanceMaxi);
		}
		this.distanceDesSommetsConnectes();
	}
	
	public void setName(String name) {
		this.nom=name;
	}
	
	public String getName() {
		return this.nom;
	}
	
	public void addSommet(SommetGraph sommet, double distanceMaxi) { //on ajoute un sommet au graph, on le connectera aux sommet
		this.ensDesGPS.add(sommet);
		int taille = this.ensDesGPS.size();
		boolean[][] arcs1 = new boolean[taille][taille];
		for (int i=0;i<taille-1;i++) {
			for (int j=0;j<taille-1;j++) {
				arcs1[i][j] = this.arcs[i][j];
			}
		}
		for (int j=0;j<this.ensDesGPS.size();j++) {
			boolean connected = this.ensDesGPS.get(taille-1).isLowDistance(this.ensDesGPS.get(j),distanceMaxi);
			this.arcs[taille-1][j] = connected;
			this.arcs[j][taille-1] = connected;
		}
		this.arcs[taille-1][taille-1] = false;
	}
	
	public void setConnected(int i, double distanceMaxi) { //met en connection (dans arcs) deux sommets suffisament proche
		for (int j=0;j<this.ensDesGPS.size();j++) {
			boolean connected = this.ensDesGPS.get(i).isLowDistance(this.ensDesGPS.get(j),distanceMaxi);
			this.arcs[i][j] = connected;
			this.arcs[j][i] = connected;
		}
		this.arcs[i][i] = false;
	}
	
	public boolean isLowDistance(SommetGraph s1, SommetGraph s2, double distanceMaxi) { //deux sommets sont ils assez proches?
		double abs1 = Double.parseDouble(s1.getLocalisation().getLatitude());
		double abs2 = Double.parseDouble(s2.getLocalisation().getLatitude());
		double ord1 = Double.parseDouble(s1.getLocalisation().getLongitude());
		double ord2 = Double.parseDouble(s2.getLocalisation().getLongitude());
		double distance = 7.5*1000*Math.sqrt(Math.pow(Math.abs(abs1-abs2), 2)+Math.pow(Math.abs(ord1-ord2), 2));
		return (distanceMaxi>distance);
	}
	
	public double setDistance(SommetGraph s1, SommetGraph s2) { //on renvoie la distance entre deux sommets proches
		double abs1 = Double.parseDouble(s1.getLocalisation().getLatitude());
		double abs2 = Double.parseDouble(s2.getLocalisation().getLatitude());
		double ord1 = Double.parseDouble(s1.getLocalisation().getLongitude());
		double ord2 = Double.parseDouble(s2.getLocalisation().getLongitude());
		double distance = 7.5*1000*Math.sqrt(Math.pow(Math.abs(abs1-abs2), 2)+Math.pow(Math.abs(ord1-ord2), 2));
		return distance;
	}
	
	public boolean isConnected(int i, int j) {
		return this.arcs[i][j];
	}
	
	public int taille() {
		 return this.ensDesGPS.size();
	}
	
	public void distanceDesSommetsConnectes() { //on renvoie une matrice des distances pour les sommets connectés, on met une distance de 0. aux non-connectés
		int taille = this.taille();
		double[][] distances = new double[taille][taille];
		for (int i=0;i<taille;i++) {
			for (int j=0;j<taille;j++) {
				if (this.arcs[i][j]) {
					distances[i][j] = this.ensDesGPS.get(i).getDistance(this.ensDesGPS.get(j));
				}
				else {
					distances[i][j] = 0.;
				}
			}
		}
		this.dist = distances;
	}
	public double[][] getDistances() {
		return this.dist;
	}

	public int[] neighbors(int next) {
		ArrayList<Integer> voisins = new ArrayList<Integer>();
		for (int i=0;i<this.taille();i++) {
			if (this.isConnected(next, i) && i!=next) {
				voisins.add(i);
			}
		}
		int[] a = new int[voisins.size()];
		for (int j=0;j<a.length;j++) {
			a[j] = voisins.get(j);
		}
		return a;
	}

	public double getWeight(int next, int v) {
		return this.dist[next][v];
	}
	/*
	public Integer getLabel(int x) {
		return this.;
	}*/
}


