package senCity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stats {
	protected String nomFichier;
	protected String parametre;
	protected String valeur1;
	protected String valeur2;
	protected ArrayList<String> timestamp = new ArrayList<String>();
	protected ArrayList<String> Address = new ArrayList<String>();
	protected ArrayList<String> SSID= new ArrayList<String>();
	protected ArrayList<String> protocol = new ArrayList<String>();
	protected ArrayList<String> Frequency = new ArrayList<String>();
	protected ArrayList<String> Signal = new ArrayList<String>();
	protected ArrayList<String> Encryption = new ArrayList<String>();
	protected ArrayList<String> security = new ArrayList<String>();
	protected ArrayList<String> groupCipher = new ArrayList<String>();
	protected ArrayList<String> pairwiseCiphers = new ArrayList<String>();
	
	public Stats(String parametre,String nomFichier,String val1){
		this.parametre=parametre;
		this.valeur1=val1;
		this.nomFichier=nomFichier;
	}
	
	public Stats(String parametre,String val1,String val2,String nomFichier){
		this.parametre=parametre;
		this.valeur1=val1;
		this.valeur2=val2;
		this.nomFichier=nomFichier;
	}
	
	public void remplir(){
		try {
			BufferedReader flot = new BufferedReader(new FileReader(nomFichier));
			String ligne=flot.readLine();
			while(ligne!=null){
				Scanner filtre= new Scanner(ligne);
				filtre.useDelimiter(",");
				timestamp.add(filtre.next());
				Address.add(filtre.next());
				SSID.add(filtre.next());
				protocol.add(filtre.next());
				Frequency.add(filtre.next());
				Signal.add(filtre.next());
				Encryption.add(filtre.next());
				security.add(filtre.next());
				groupCipher.add(filtre.next());
				ligne=flot.readLine();
				filtre.close();
			}
			flot.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void captureWifi(int i){
		if(timestamp.get(i)==this.valeur1){
			System.out.print(timestamp.get(i)+" ");
			System.out.print(Address.get(i)+" ");
			System.out.print(SSID.get(i)+" ");
			System.out.print(protocol.get(i)+" ");
			System.out.print(Frequency.get(i)+" ");
			System.out.print(Signal.get(i)+" ");
			System.out.print(Encryption.get(i)+" ");
			System.out.print(groupCipher.get(i)+" ");
		}
	}
	
	public ArrayList<String> getTimestampList() {
		return timestamp;
	}
}