package senCity;

//import java.io.BufferedReader;

//import java.io.FileReader;


import java.util.Collection;
import java.util.Iterator;
//import java.util.Scanner;


public abstract class Traces extends AbstractTraces implements Iterable<Trace> {
	
	
	protected Collection<Trace> list ; //ligne dependant de la Structure choisie
	
	public void ajouter(Trace elt){
		this.list.add(elt);
	}

	public int taille(){
		return list.size();
	}
	@Override
	public Iterator<Trace> iterator() {
		return this.list.iterator();
	}

	public abstract Traces extract(String SSID);
	
	public abstract void initialiser();
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
		for(Trace elt : this.list){ //puisque la Collection en permet pas d'acceder aux éléments par leurs indices comme les sous classes peuvent le faire (par get(Int i)), alorqs on parcourt la liste par element.
			sb = sb + elt.toString() + "/n"; 
		}
		return sb;
	}*/
/*
	public void save(String File) throws IOException{
		try {
			BufferedWriter flot1 = new BufferedWriter(new FileWriter(File)) ;
			for (Trace elt : this.list){
				flot1.write(elt.toString());
				flot1.newLine();
			}
			flot1.close() ;
		}catch (IOException e) {
			System.out.println("Erreur lors de la lecture");
			e.printStackTrace();
		}
	}
	*/
	
	
}
