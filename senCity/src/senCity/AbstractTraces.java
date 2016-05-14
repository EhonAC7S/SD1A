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

	public abstract int taille();
	
	public abstract void initialiser();

	public String toString() {
		String sb = ""; 
		for(Trace elt : this){
			sb = sb + elt.toString() + "/n"; 
		}
		return sb;
	}
	
	public void printTrace() {
		for (Trace elt : this) {
			System.out.println(elt.toString());
		}
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
				timestampWifi = wifiF.next();
				timestampWifi = timestampWifi.substring(0, 10);
				timestampWifid = Double.parseDouble(timestampWifi);
				wifiF.next();
				SSID = wifiF.next();
				wifiF.next();
				wifiF.next();
				Signal = Integer.parseInt(wifiF.next());
			}
			if (!gpsReaded) {
				gpsF.useDelimiter(",");
				timestampGPS = gpsF.next();
				timestampGPS = timestampGPS.substring(0, 10);
				timestampGPSd = Double.parseDouble(timestampGPS);
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

				} else {
					wifiF.nextLine();
					wifiReaded = false;
					gpsReaded = true;
				}
			} else {
				if (timestampWifid < timestampGPSd) {
					wifiF.nextLine();
					wifiReaded = false;
					gpsReaded = true;
				} else {
					gpsF.nextLine();
					wifiReaded = true;
					gpsReaded = false;
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
		TreeTraces traces3 = new TreeTraces();
		System.out.println(System.currentTimeMillis());
		traces.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces.extract("<hidden>");
		System.out.println(System.currentTimeMillis());
		traces.save("hashmap");
		System.out.println(System.currentTimeMillis());
		traces1.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces1.extract("eduroam");
		System.out.println(System.currentTimeMillis());
		traces1.save("linkedlist");
		System.out.println(System.currentTimeMillis());
		traces2.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces2.extract("eduroam");
		System.out.println(System.currentTimeMillis());
		traces2.save("arraylist");
		System.out.println(System.currentTimeMillis());
		traces3.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces3.extract("eduroam");
		System.out.println(System.currentTimeMillis());
		traces3.save("arraylist");
		System.out.println(System.currentTimeMillis());
	}
	
	//temps d'acces instantanné.

	/*comparaison des resultats (Q7 TP4)

1463234141701			//debut du programme
0.8317958637107573		//premier load sur hashmap
1463234142565			//864 millisecondes pour load sur hashmap
1463234142565			//temps pour extract instantanné
1463234142577			//12 milli temps pour save hashmap
0.8317958637107573		//2eme load sur linkedlist 
1463234142968			//391 (213 avant) milli pour load une linkedlist		//augmentation inattendu du temps pour la linkedlist alors que le code n'a pas été changé
1463234142978			//10 (6 avant) milli pour l'extract
1463234143068			//90 (18 avant) milli pour save
0.8317958637107573		//3eme load sur la arraylist
1463234143281			//213 (192) milli pour load
1463234143284			//2;3 milli pour extract
1463234143307			//23 sec pour save
0.8317958637107573		//load sur TreeTraces
1463234143517			//210 milli pour un load sur TreeTraces
1463234143517			//extract instanné sur TreeTraces
1463234143539			//22 milli pour save sur TreeTraces


*/

}
