package senCity;
import java.util.ArrayList;



public class GraphDesGPS {
	private ArrayList<SommetGraph> ensDesGPS;
	private boolean[][] arcs;
	
	public GraphDesGPS() {
		this.ensDesGPS = new ArrayList<SommetGraph>();
		this.arcs = new boolean[0][0];
	}
	
	public void addSommet(SommetGraph sommet, double distanceMaxi) {
		this.ensDesGPS.add(sommet);
		int taille = this.ensDesGPS.size();
		boolean[][] arcs1 = new boolean[this.ensDesGPS.size()][this.ensDesGPS.size()];
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
	
	public void setConnected(int i, double distanceMaxi) {
		for (int j=0;j<this.ensDesGPS.size();j++) {
			boolean connected = isLowDistance(this.ensDesGPS.get(i),this.ensDesGPS.get(j),distanceMaxi);
			this.arcs[i][j] = connected;
			this.arcs[j][i] = connected;
			
		}
	}
	
	public boolean isLowDistance(SommetGraph s1, SommetGraph s2, double distanceMaxi) {
		double abs1 = Double.parseDouble(s1.getLocalisation().getLatitude());
		double abs2 = Double.parseDouble(s2.getLocalisation().getLatitude());
		double ord1 = Double.parseDouble(s1.getLocalisation().getLongitude());
		double ord2 = Double.parseDouble(s2.getLocalisation().getLongitude());
		double distance = Math.sqrt(Math.pow(Math.abs(abs1-abs2), 2)+Math.pow(Math.abs(ord1-ord2), 2));
		return (distanceMaxi<distance);
	}
	
	
}
