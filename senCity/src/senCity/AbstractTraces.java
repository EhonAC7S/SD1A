package senCity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Iterator;

public abstract class AbstractTraces implements Iterable<Trace> {

	public abstract void ajouter(Trace elt);

	public abstract Iterator<Trace> iterator();
	
	public abstract Traces extract(String ssid);

	public String toString() {
		//this.initialiser();
		String sb = ""; 
		for(Trace elt : this){ //puisque la Collection en permet pas d'acceder aux éléments par leurs indices comme les sous classes peuvent le faire (par get(Int i)), alorqs on parcourt la liste par element.
			sb = sb + elt.toString() + "/n"; 
		}
		return sb;
	}
	
	public void save(String File) throws IOException{
		try {
			BufferedWriter flot1 = new BufferedWriter(new FileWriter(File)) ;
			for (Trace elt : this){
				flot1.write(elt.toString());
				flot1.newLine();
			}
			flot1.close() ;
		}catch (IOException e) {
			System.out.println("Erreur lors de la lecture");
			e.printStackTrace();
		}
	}

	public abstract int taille();
	
	public abstract void initialiser();
	
	//public abstract String toString();

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
	
	public static void main(String[] args) throws IOException{
		HashMapTraces traces = new HashMapTraces();
		LinkedListTraces traces1 = new LinkedListTraces();
		ArrayListTraces traces2 = new ArrayListTraces();
		System.out.println(System.currentTimeMillis());
		traces.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces.extract("<hidden>");
		System.out.println(System.currentTimeMillis());
		//traces.toString();
		traces.save("hashmap");
		System.out.println(System.currentTimeMillis());
		traces1.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces1.extract("<hidden>");
		System.out.println(System.currentTimeMillis());
		traces1.save("linkedlist");
		System.out.println(System.currentTimeMillis());
		traces2.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces2.extract("<hidden>");
		System.out.println(System.currentTimeMillis());
		traces2.save("arraylist");
		System.out.println(System.currentTimeMillis());
		
	}
	
	//temps d'acces instantanné.

	/*comparaison des resultats (Q7 TP4)

1463088103715			//debut du programme
0.8317958637107573		//premier load sur hashmap
1463088104513			//798 millisecondes pour load sur hashmap
1463088104513			//temps pour extract instantanné
1463088104594			//temps pour save hashmap : 81 milli --on passe a 40 milli apres optimisation de l'iterator<Trace> pour map
0.8317958637107573		//2eme load sur linkedlist 
1463088104807			//213 milli pour load une linkedlist
1463088104813			//6 milli pour l'extract
1463088104831			//18 milli pour save
0.8317958637107573		//3eme load sur la arraylist
1463088105023			//192 milli pour load
1463088105025			//2 milli pour extract
1463088105038			//13 sec pour save



*/

}
