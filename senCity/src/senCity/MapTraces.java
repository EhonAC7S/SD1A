package senCity;

//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.util.HashMap;
//import java.io.IOException;
import java.util.Map;

//import java.util.Scanner;
import java.util.Iterator;

public abstract class MapTraces extends AbstractTraces implements Iterable<Trace> {
	protected Map<String, Traces> list;
	
	protected MapTraces(){
		this.initialiser();
	}
	
	protected MapTraces(Map<String, Traces> c) {
		list = c;
	}
	
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
	public abstract void initialiser();
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
		//ne marche pas, recherche d'une autre methode pour le parcours par trace dans une hashmap/map
		return traces.iterator();
		
	}
	
	
	public abstract Traces extract(String ssid);
	/*
	public void load(String tWifi, String tGPS, double taux) throws IOException {
		this.initialiser();
		double lue = 0;
		double stocker = 0;
		BufferedReader flot1 = new BufferedReader(new FileReader(tWifi));
		BufferedReader flot2 = new BufferedReader(new FileReader(tGPS));
		Scanner wifiF = new Scanner(flot1);
		Scanner gpsF = new Scanner(flot2);
		wifiF.nextLine();
		gpsF.nextLine();
		int Signal = 0;
		Trace trace;
		String SSID = "";
		Boolean wifiReaded = false;
		Boolean gpsReaded = false;
		GPS localisation = new GPS("0", "0");
		double timestampGPSd = 0.;
		String timestampGPS = "";
		double timestampWifid = 0.;
		String timestampWifi = "";
		do {
			if (!wifiReaded) {

				wifiF.useDelimiter(",");
				lue++;
				// System.out.println(lue);
				timestampWifi = wifiF.next();
				// System.out.println(timestampWifi);
				timestampWifi = timestampWifi.substring(0, 10);
				timestampWifid = Double.parseDouble(timestampWifi);
				// System.out.println(timestampWifid);
				wifiF.next();
				SSID = wifiF.next();
				// System.out.println(SSID);
				// System.out.println(wifiF.next());
				// System.out.println(wifiF.next());
				wifiF.next();
				wifiF.next();
				Signal = Integer.parseInt(wifiF.next());
				// System.out.println(Signal);
			}
			if (!gpsReaded) {
				gpsF.useDelimiter(",");
				timestampGPS = gpsF.next();
				// System.out.println(timestampGPS);
				timestampGPS = timestampGPS.substring(0, 10);
				timestampGPSd = Double.parseDouble(timestampGPS);

				// System.out.println(timestampGPSd);
				String lat = gpsF.next();
				String longi = gpsF.next();
				localisation = new GPS(lat, longi);
			}
			if (timestampWifid == timestampGPSd) {
				if (!SSID.equals("<hidden>") && SSID!=null) {
					trace = new Trace(timestampWifi, SSID, Signal, localisation);
					this.ajouter(trace);
					stocker++;
					wifiF.nextLine();
					wifiReaded = false;
					gpsReaded = true;
					// System.out.println(stocker);

				} else {
					wifiF.nextLine();
					wifiReaded = false;
					gpsReaded = true;
					// System.out.println("2");
				}
			} else {
				if (timestampWifid < timestampGPSd) {
					wifiF.nextLine();
					wifiReaded = false;
					gpsReaded = true;
					// System.out.println("3");
				} else {
					gpsF.nextLine();
					wifiReaded = true;
					gpsReaded = false;
					// System.out.println("4");
				}
			}

		} while (wifiF.hasNextLine() && gpsF.hasNextLine());
		wifiF.close();
		gpsF.close();
		flot1.close();
		flot2.close();
		double tauxreel = stocker / lue;
		System.out.println(tauxreel);
		if (taux < tauxreel) {
			throw new IOException();
		}
	}
	*/

	

/*

	public String toString() {
		String sb = "";
		for (String ssid : this.list.keySet()) { // puisque la Collection en
													// permet pas d'acceder aux
													// éléments par leurs
													// indices comme les sous
													// classes peuvent le faire
													// (par get(Int i)), alorqs
													// on parcourt la liste par
													// element.
			sb = sb + this.list.get(ssid).toString() + "/n";
		}
		return sb;
	}
	

	public void save(String File) throws IOException {
		try {
			BufferedWriter flot1 = new BufferedWriter(new FileWriter(File));
			for (String ssid : this.list.keySet()) {
				for (Trace elt : this.list.get(ssid)) {
					flot1.write(elt.toString());
					flot1.newLine();
				}
			}
			flot1.close();
		} catch (IOException e) {
			System.out.println("Erreur lors de la lecture");
			e.printStackTrace();
		}
	}

	*/
}
