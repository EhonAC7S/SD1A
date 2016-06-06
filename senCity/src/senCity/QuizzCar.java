package senCity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizzCar {

	public static void main(String[] args) throws IOException {
		try {
			
		Scanner input1 = new Scanner(System.in);
		
		System.out.println("Entrez le nom du fichier contenant les données Wifi");
		String wifi = input1.nextLine();
		System.out.println("Entrez le nom du fichier contenant les données GPS");
		String gps = input1.nextLine();
		System.out.println("Entrez le taux de perte entre 0 et 1 acceptable");
		double taux = Double.parseDouble(input1.nextLine());
		TreeTraces donnees = new TreeTraces();
		donnees.load(wifi,gps,taux);
		boolean cont = true;
		String contstr;
		String choix;
		String ssid;
		Traces traces;
		Node noeudApprocher;
		ArrayList<String> listSSIDSugg;
		while (cont) {
			do {
			System.out.println("Souhaitez-vous une recherche direct d'un SSID ou une suggestion de SSID?");
			System.out.println("'suggestion', 'recherche' : ");
			choix = input1.nextLine();
			} while (!choix.equals("recherche") && !choix.equals("suggestion"));
			if (choix.equals("recherche")) {
				System.out.println("Veuillez entrer le SSID complet d'un wifi");
				System.out.println("SSID recherché : ");
				ssid = input1.nextLine();
				traces = donnees.extract(ssid);
				if (traces != null) {
					traces.printTrace();
				}
				else {
					System.out.println("Pas de Trace");
				}
			}
			if (choix.equals("suggestion")){
				System.out.println("Veuillez entrer le SSID partiel d'un wifi");
				System.out.println("SSID recherché ou préfixe : ");
				ssid = input1.nextLine();
				noeudApprocher = donnees.cherche(ssid); //on cherche un noeud precis, mais on s'arrete dès qu'on ne peut trouver le chemin
				listSSIDSugg = noeudApprocher.getSubSSID();
				if (listSSIDSugg.size()==0) {
					System.out.println("Pas de suggestion");
				}
				else {
					System.out.println("Souhaitiez-vous dire? : ");
					for (String name : listSSIDSugg) {
						System.out.println(name);
					}
					System.out.println("Voulez-vous afficher toutes les traces de ces SSID?");
					do {
						System.out.println("Afficher? 'true' or 'false' : ");
						choix = input1.nextLine();
					}
					while (!choix.equals("true") && !choix.equals("false"));
					if (choix.equals("true")) {
						traces = donnees.extractAll(listSSIDSugg);
						traces.printTrace();
					}

				}
			}
			do {
			System.out.println("Recommencer? Taper 'true' or 'false' : ");
			contstr = input1.nextLine();
			}
			while (!contstr.equals("true") && !contstr.equals("false"));
			cont = Boolean.parseBoolean(contstr);
		}
		
		input1.close();
		System.out.println("Fin du programme");
		}
		catch (IOException e) {
			System.out.println("Erreur lors de la sasie/lecture");
			e.printStackTrace();
		}
	}
}
