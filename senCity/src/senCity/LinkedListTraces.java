package senCity;


import java.io.IOException;

import java.util.LinkedList; //ligne dependant de la Structure choisie


public class LinkedListTraces extends Traces { //ligne dependant de la Structure choisie
	//protected LinkedList<Trace> list ; //ligne dependant de la Structure choisie

	public LinkedListTraces(){ //ligne dependant de la Structure choisie
		list = new LinkedList<Trace>(); //ligne dependant de la Structure choisie
	}
	
	public LinkedListTraces(LinkedList<Trace> traces){ //ligne dependant de la Structure choisie
		this.list=traces;
		
	}
	public void initialiser() {
		this.list = new LinkedList<Trace>();
	}
	
	@Override
	public LinkedListTraces extract(String SSID) {
		LinkedListTraces extracted = new LinkedListTraces();
		for (Trace elt:this.list) {
			if ((elt.getSSID()).equals(SSID)) {
				extracted.ajouter(elt);
			}
		}
		return extracted;
	}

	/*
	public void ajouter(Trace elt){
	
		this.list.add(elt);
	}

	public int size(){
		return list.size();
	}

	public void load(String File) throws IOException{
		this.list = new LinkedList<Trace>();
		BufferedReader flot = new BufferedReader(new FileReader(File)) ;
		Scanner file = new Scanner(flot);
		file.nextLine();
		int Signal;
		Trace trace;
		while(file.hasNextLine()){
			file.useDelimiter(",");
			String timestamp = file.next();
			file.next();
			String SSID = file.next();
			file.next();
			file.next();
			Signal = Integer.parseInt(file.next());
			file.nextLine();
			trace = new Trace(timestamp, SSID, Signal);
			this.ajouter(trace);
		}
		file.close();

	}

	public String toString(){
		String sb = ""; 
		for(int i = 0; i < list.size() ; i++){
			sb = sb + list.get(i).toString() + "/n"; 
		}
		return sb;
	}

	public void save(String File) throws IOException{
		try {
			BufferedWriter flot1 = new BufferedWriter(new FileWriter(File)) ;
			for (int i = 0 ; i<list.size() ; i++){
				flot1.write(list.get(i).toString());
				flot1.newLine();
			}
			flot1.close() ;
		}catch (IOException e) {
			System.out.println("Erreur lors de la lecture");
			e.printStackTrace();
		}
	}
	*/

	public static void main(String[] args) throws IOException{
		/*LinkedListTraces traces = new LinkedListTraces(); //ligne dependant de la Structure choisie
		long memory;
		long diff;
		memory = Runtime.getRuntime().freeMemory();
		System.out.println(memory+"");
		diff = memory;
		traces.load("capture_wifi.csv","capture_gps.csv",.85);
		memory = Runtime.getRuntime().freeMemory();
		diff = diff - memory;
		System.out.println(memory);
		System.out.println(diff);
		diff = memory;
		/*
		traces.load("capture_wifi.csv");
		memory = Runtime.getRuntime().freeMemory();
		diff = diff - memory;
		System.out.println(memory);
		System.out.println(diff);
		*/
		//traces.save("nouveau.csv");
		LinkedListTraces traces = new LinkedListTraces();
		traces.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces.extract("<hidden>");
		System.out.println(System.currentTimeMillis());
		//temps : 1462382488912
		//        1462382488919 
		//donc 7 de difference
	}


	
}
