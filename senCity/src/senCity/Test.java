package senCity;

import java.io.*;
//import java.util.*;

public class Test {
	public static void main (String[] args) throws IOException {
	/*FileReader flot; 
	BufferedReader flotFiltre;
	*/
	/*Bloc permettant de vérifier la nature du chemin donné.
	
	File chemin = new File("capture_wifi.csv");
	
	 
	boolean existe = chemin.exists() ;
	
	boolean dossier = chemin.isDirectory() ;
	boolean lisible = chemin.canRead() ;
	System.out.println("Le chemin du fichier est "+chemin);
	System.out.println("Le fichier existe t'il ? "+existe);
	System.out.println("Lisible ? "+lisible);
	System.out.println("Est-ce un dossier ? "+dossier);
	
	*/
	/*Bloc première version lecture de la première capture
	try {
		flot = new FileReader("capture_wifi.csv"); //création du flot de données.
		flotFiltre = new BufferedReader(flot); //filtrage si nécessaire.
		String ligne = flotFiltre.readLine(); //on lit la ligne.
		System.out.println(ligne); //on l'affiche, c'est la ligne contenant les noms des informations (tableau).
		ligne = flotFiltre.readLine();	//ligne correspondant à la première capture wifi.
		System.out.println(ligne); //affichage dans la console.
		flot.close(); //on ferme le flot après avoir fini.
		}
	catch (IOException e) { //en cas d'erreur.
		System.out.println("Erreur lors de la lecture");
		e.printStackTrace();
		}
	*/
	
	/*
	 * 
	try {
	
		flot = new FileReader("capture_wifi.csv"); //création du flot de données.
		flotFiltre = new BufferedReader(flot); //filtrage si nécessaire.
		flotFiltre.readLine();
		Scanner filtre = new Scanner(flotFiltre.readLine());
		filtre.useDelimiter(",");
		System.out.println(filtre.next());
		System.out.println(filtre.next());
		System.out.println(filtre.next());
		System.out.println(filtre.next());
		System.out.println(filtre.next());
		System.out.println(filtre.next());
		System.out.println(filtre.next());
		System.out.println(filtre.next());
		System.out.println(filtre.next()); //comme il y a 9 éléments par lignes, on repète 9 fois la lecture next() du prochain mot.
		filtre.close(); //on ferme le flot après avoir fini.
		}
	catch (IOException e) { //en cas d'erreur.
		System.out.println("Erreur lors de la lecture");
		e.printStackTrace();
		}
	 */
	/*
	Stats s1= new Stats("timestamp","capture_wifi.csv",,"1438174357035008615");
	 
	s1.remplir();
	for(int i = 0 ; i < s1.getTimestampList().size() ; i++){
	System.out.println(s1.getTimestampList().get(1));}
	}
	 
	try {
		flot = new FileReader("capture_gps.csv"); //création du flot de données.
		flotFiltre = new BufferedReader(flot); //filtrage si nécessaire.
		String ligne = flotFiltre.readLine(); //on lit la ligne.
		System.out.println(ligne); //on l'affiche, c'est la ligne contenant les noms des informations (tableau).
		ligne = flotFiltre.readLine();	//ligne correspondant à la première capture wifi.
		System.out.println(ligne); //affichage dans la console.
		flot.close(); //on ferme le flot après avoir fini.
		}
	catch (IOException e) { //en cas d'erreur.
		System.out.println("Erreur lors de la lecture");
		e.printStackTrace();
		}
*/	
	/*
	LinkedList<Trace> list1= new LinkedList<>();
	ArrayList<Trace> list2= new ArrayList<>();
	Traces traces1= new LinkedListTraces(list1);
	Traces traces2= new ArrayListTraces(list2);
	traces1.load("capture_wifi.csv");
	System.out.println(traces1.toString());
	traces1.save("copie.csv");
	*/
	
	}
}