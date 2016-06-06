package senCity;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeTraces extends AbstractTraces {
	private Node lettres;
	
	public TreeTraces() {
		this.lettres = new Node();
	}
	
	public Node getRoot() {
		return this.lettres;
	}
	
	@Override
	public void ajouter(Trace elt) {
		String SSID = elt.getSSID();
		Node curr = this.lettres;
		for (int i=0;i<SSID.length();i++) {
			curr = curr.addNodeHorizontal(SSID.charAt(i));
			if (i<SSID.length()-1) {
				curr = curr.addNodeVertical();
			}
		}
		if (curr.getTraces()==null) {
			curr.setTraces(new ArrayListTraces());
		}
		curr.getTraces().ajouter(elt);
		curr.setChemin(SSID);
	}
	
	@Override
	public Iterator<Trace> iterator() {
		ArrayListTraces traces = new ArrayListTraces();
		ArrayList<Node> listNoeuds = this.lettres.getFamily();
		for (Node noeud : listNoeuds) {
			if (noeud.getTraces()!=null) {
				for (Trace elt : noeud.getTraces()) {
					traces.ajouter(elt);
				}
			}
		}
		return traces.iterator();
	}

	@Override
	public Traces extract(String ssid) {
		Node curr = this.lettres;
		for (int i=0;i<ssid.length();i++) {
			curr = curr.hasBrotherNamed(ssid.charAt(i));
			if (curr == null) {
				System.out.println("No Such SSID " + ssid + " found");
				return null;
			}
			if (i<ssid.length()-1) {
				curr = curr.getFils();
				if (curr == null) {
					System.out.println("No Such SSID " + ssid + " found");
					return null;
				}
			}
		}
		if (curr.getTraces()==null) {
			return new ArrayListTraces();
		}
		return curr.getTraces();
	}
	
	public Traces extractAll(ArrayList<String> listSSID) {
		Traces tracesTot = new ArrayListTraces();
		Traces tracesPartielles = new ArrayListTraces();
		for (String SSID : listSSID) {
			tracesPartielles = this.extract(SSID);
			for (Trace elt : tracesPartielles) {
				tracesTot.ajouter(elt);
			}
		}
		return tracesTot;
	}

	@Override
	public int taille() {
		return this.lettres.getFamily().size();
	}

	@Override
	public void initialiser() {
		lettres = new Node();
	}
	
	public Node cherche(String ssid) {
		Node curr = this.lettres;
		for (int i=0;i<ssid.length();i++) {
			if (curr.hasBrotherNamed(ssid.charAt(i)) == null) {
				return curr;
			}
			else {
				curr = curr.hasBrotherNamed(ssid.charAt(i));
			}
			if (i<ssid.length()-1) {
				if (curr.getFils() == null) {
					return curr;
				}
				else {
					curr = curr.getFils();
				}
			}
		}
		return curr;
	}
}
