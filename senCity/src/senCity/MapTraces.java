package senCity;

import java.util.Map;
import java.util.Iterator;

public abstract class MapTraces extends AbstractTraces implements Iterable<Trace> {
	protected Map<String, Traces> list;
	
	protected MapTraces(){
		this.initialiser();
	}
	
	protected MapTraces(Map<String, Traces> c) {
		list = c;
	}

	public abstract void initialiser();
	
	public abstract Traces extract(String ssid);
	
	public void ajouter(Trace t) {
		String ssid = t.getSSID();
		if (this.list.containsKey(ssid)) {
			this.list.get(ssid).ajouter(t);
		} else {
			Traces traces = new ArrayListTraces();
			traces.ajouter(t);
			this.list.put(ssid, traces);
		}
	}
	
	public int taille() {
		return this.list.size();
	}
	
	public Iterator<Trace> iterator() {
		HashMapTraces t = new HashMapTraces();
		Traces traces = new ArrayListTraces();
		for (Traces elts : this.list.values()) {
			for (Trace elt : elts) {
				t.ajouter(elt);
			}
		}
		return traces.iterator();
	}
}
