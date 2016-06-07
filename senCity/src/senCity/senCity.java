package senCity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.HashMap;

public class senCity {

	public static void main(String[] args) throws IOException {
		try {
			//QuizzCar.main(null);
			Scanner input = new Scanner(System.in);
			System.out.println("Entrez le nom du fichier contenant les données Wifi");
			String wifi = input.nextLine();
			System.out.println("Entrez le nom du fichier contenant les données GPS");
			String gps = input.nextLine();
			System.out.println("Entrez le taux de perte entre 0 et 1 acceptable");
			double taux = Double.parseDouble(input.nextLine());
			TreeTraces donnees = new TreeTraces();
			donnees.load(wifi, gps, taux);
			System.out.println("Le nombre de SSID disponible est : " + donnees.getRoot().getSubSSIDv2().size());
			ArrayList<SommetGraph> ensDesGPS = new ArrayList<SommetGraph>();
			for (Trace elt : donnees) {
				if (!ensDesGPS.contains(elt.getloc())) {
					ensDesGPS.add(new SommetGraph(elt.getloc()));
				}
			}
			TreeTraces donneesRestrict = new TreeTraces();
			String puissancestr;
			int puissance;
			String distancestr;
			double distance;
			System.out.println("puissance? : ");
			puissancestr = input.nextLine();
			puissance = Integer.parseInt(puissancestr);
			System.out.println("distance? : ");
			distancestr = input.nextLine();
			distance = Double.parseDouble(distancestr);
			double compt = 0.;
			double compt2 = 0.;
			for (Trace elt : donnees) { //pour toutes les trace, on compte celle qui verifient les contraintes des proximité et de puissance du signal
				compt = compt+1.;
				boolean near = false;
				for (SommetGraph smt : ensDesGPS) {
					if (!elt.getloc().equals(smt.getLocalisation())) { //on ne regarde pas si le point est proche de lui meme.
						near = near || smt.isLowDistance(new SommetGraph(elt.getloc()), distance);
					}
				}
				if (elt.getSignal()>puissance && near) {
					compt2 = compt2+1.;
					donneesRestrict.ajouter(elt);
				}
			}
			System.out.println("Le taux de traces conservées après filtrage est : " + compt2/compt);
			System.out.println("Le nombre de SSID maintenant disponible est : " + donneesRestrict.getRoot().getSubSSIDv2().size());
			for (String ssid : donneesRestrict.getRoot().getSubSSIDv2()) {
				System.out.println("Le SSID : " + ssid + " a " + donneesRestrict.cherche(ssid).getTraces().taille() + " points de ressensement");
			}
			//System.out.println("Chargement de la table des graphs...");
			//HashMap<String,GraphDesGPS> tableDesGraphParSSID = new HashMap<String,GraphDesGPS>();
			/*ensDesGPS = new ArrayList<SommetGraph>();
			for (Trace elt : donneesRestrict) {
				if (!ensDesGPS.contains(elt.getloc())) {
					ensDesGPS.add(new SommetGraph(elt.getloc()));
				}
			}*//*
			GraphDesGPS graphSSID = new GraphDesGPS(ensDesGPS,"Tout le reseau");
			graphSSID.initaliser(distance);
			tableDesGraphParSSID.put("Tout le reseau", graphSSID);
			for (String ssid : donneesRestrict.getRoot().getSubSSIDv2()) {
				ArrayList<SommetGraph> pointGPSSSID = new ArrayList<SommetGraph>();
				for (Trace elt : donneesRestrict.cherche(ssid).getTraces()) {
					if (!pointGPSSSID.contains(elt.getloc())) {
						pointGPSSSID.add(new SommetGraph(elt.getloc()));
					}
				}
				//System.out.println(pointGPSSSID.size());
				GraphDesGPS graphdessid = new GraphDesGPS(pointGPSSSID,ssid);
				graphdessid.initaliser(distance);
				tableDesGraphParSSID.put(ssid, graphdessid);
				//System.out.println("le nombre de sommets est : " + tableDesGraphParSSID.get(ssid).taille() + " pour " + ssid);

			}
			System.out.println("Table des graphs créée");
			*/
			ensDesGPS = new ArrayList<SommetGraph>();
			String choixssid;
			System.out.println("Quel Graph de SSID ('Tout le reseau' pour avoir le graph complet): ");
			choixssid = input.nextLine();
			if (choixssid.equals("Tout le reseau")) {
				for (Trace elt : donneesRestrict) {
					if (!ensDesGPS.contains(elt.getloc())) {
						ensDesGPS.add(new SommetGraph(elt.getloc()));
					}
				}
			}
			else {
				for (Trace elt : donneesRestrict.cherche(choixssid).getTraces()) {
					if (!ensDesGPS.contains(elt.getloc())) {
						ensDesGPS.add(new SommetGraph(elt.getloc()));
					}
				}
			}
			GraphDesGPS graphdessid = new GraphDesGPS(ensDesGPS,choixssid);
			graphdessid.initaliser(distance);
			String sommetstr;
			int sommet;
			int sommet1;
			//System.out.println("Quel Graph de SSID ('Tout le reseau' pour avoir le graph complet): ");
			//choixssid = input.nextLine();
			//System.out.println("le nombre de sommets est : " + tableDesGraphParSSID.get(choixssid).taille());
			System.out.println("Quel sommet de départ : ");
			sommetstr = input.nextLine();
			sommet = Integer.parseInt(sommetstr);
			//System.out.println(tableDesGraphParSSID.get(choixssid).taille());
			int[] listdespredecesseurs = Dijkstra.dijkstra(graphdessid,sommet);
			if (!(listdespredecesseurs.length == 1)) {
				System.out.println("Quel sommet d'arrivée : ");
				sommetstr = input.nextLine();
				sommet1 = Integer.parseInt(sommetstr);
				Dijkstra.printPath(listdespredecesseurs, sommet, sommet1);
			}
			
			input.close();
			System.out.println("Fin du programme");
		} catch (IOException e) {
			System.out.println("Erreur lors de la sasie/lecture");
			e.printStackTrace();
		}
	}
}
