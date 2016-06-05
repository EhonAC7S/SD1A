package senCity;

import java.util.ArrayList;

public class GraphDesGPS {
	private ArrayList<SommetGraph> ensDesGPS;
	private boolean[][] arcs;
	private String nom;
	
	public GraphDesGPS() { //on créé le graph vide
		this.ensDesGPS = new ArrayList<SommetGraph>();
		this.arcs = new boolean[0][0];
	}
	
	public GraphDesGPS(ArrayList<SommetGraph> sommets, int nbSommets, String name) { //on créé le graph en ayant au préalable la liste des sommets
		this.ensDesGPS = sommets;
		this.nom=name;
		this.arcs = new boolean[sommets.size()][sommets.size()];
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
			boolean connected = isLowDistance(this.ensDesGPS.get(taille-1),this.ensDesGPS.get(j),distanceMaxi);
			this.arcs[taille-1][j] = connected;
			this.arcs[j][taille-1] = connected;
		}
		this.arcs[taille-1][taille-1] = false;
	}
	
	public void setConnected(int i, double distanceMaxi) { //met en connection (dans arcs) deux sommets suffisament proche
		for (int j=0;j<this.ensDesGPS.size();j++) {
			boolean connected = isLowDistance(this.ensDesGPS.get(i),this.ensDesGPS.get(j),distanceMaxi);
			this.arcs[i][j] = connected;
			this.arcs[j][i] = connected;
		}
	}
	
	public boolean isLowDistance(SommetGraph s1, SommetGraph s2, double distanceMaxi) { //deux sommets sont ils assez proches?
		double abs1 = Double.parseDouble(s1.getLocalisation().getLatitude());
		double abs2 = Double.parseDouble(s2.getLocalisation().getLatitude());
		double ord1 = Double.parseDouble(s1.getLocalisation().getLongitude());
		double ord2 = Double.parseDouble(s2.getLocalisation().getLongitude());
		double distance = Math.sqrt(Math.pow(Math.abs(abs1-abs2), 2)+Math.pow(Math.abs(ord1-ord2), 2));
		return (distanceMaxi<distance);
	}
	
	public double setDistance(SommetGraph s1, SommetGraph s2) { //on renvoie la distance entre deux sommets proches
		double abs1 = Double.parseDouble(s1.getLocalisation().getLatitude());
		double abs2 = Double.parseDouble(s2.getLocalisation().getLatitude());
		double ord1 = Double.parseDouble(s1.getLocalisation().getLongitude());
		double ord2 = Double.parseDouble(s2.getLocalisation().getLongitude());
		double distance = Math.sqrt(Math.pow(Math.abs(abs1-abs2), 2)+Math.pow(Math.abs(ord1-ord2), 2));
		return distance;
	}
	
	public int taille() {
		 return this.ensDesGPS.size();
	}
	
	public double[][] distanceDesSommetsConnectes() { //on renvoie une matrice des distances pour les sommets connectés, on met une distance de 0. aux non-connectés
		int taille = this.taille();
		double[][] distances = new double[taille][taille];
		for (int i=0;i<taille;i++) {
			for (int j=0;j<taille;j++) {
				if (this.arcs[i][j]) {
					distances[i][j] = this.setDistance(this.ensDesGPS.get(i), this.ensDesGPS.get(j));
				}
				else {
					distances[i][j] = 0.;
				}
			}
		}
		return distances;
	}
}
