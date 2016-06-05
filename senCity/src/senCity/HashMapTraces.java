package senCity;

import java.util.HashMap;

public class HashMapTraces extends MapTraces {  
	public void initialiser() {
		this.list = new HashMap<String, Traces>();
	}

	public HashMapTraces() {
		super();
		this.list = new HashMap<String, Traces>();
	}
	
	public HashMapTraces(HashMap<String, Traces> hashmap) {
		this.list = hashmap;
	}

	public Traces extract(String ssid) {
		Traces extracted = this.list.get(ssid);
		return extracted;
	}
}
