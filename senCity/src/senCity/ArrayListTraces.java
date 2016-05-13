package senCity;

import java.io.IOException;
import java.util.ArrayList; //ligne dependant de la Structure choisie


public class ArrayListTraces extends Traces { //ligne dependant de la Structure choisie
	

	public ArrayListTraces(){ //ligne dependant de la Structure choisie
		list = new ArrayList<Trace>(); //ligne dependant de la Structure choisie
	}
	
	public ArrayListTraces(ArrayList<Trace> traces){ //ligne dependant de la Structure choisie
		this.list=traces;
		
	}
	
	public void initialiser() {
		this.list = new ArrayList<Trace>();
	}
	
	@Override
	public ArrayListTraces extract(String SSID) {
		ArrayListTraces extracted = new ArrayListTraces();
		for (Trace elt : this.list) {
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
		this.list = new ArrayList<Trace>(); //ligne dependant de la Structure choisie
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
/*		ArrayListTraces traces = new ArrayListTraces();  //ligne dependant de la Structure choisie
		/*long memory;
		long diff;
		memory = Runtime.getRuntime().freeMemory();
		System.out.println(memory+"");
		traces.load("capture_wifi.csv","capture_gps.csv",100.);
		diff = memory;
		memory = Runtime.getRuntime().freeMemory();
		diff = diff - memory;
		System.out.println(memory+"");
		System.out.println(diff+"");
		/*traces.load("capture_wifi.csv");
		diff = memory;
		memory = Runtime.getRuntime().freeMemory();
		diff = diff - memory;
		System.out.println(memory+"");
		System.out.println(diff+"");
		//traces.save("nouveau.csv");
		 */
		ArrayListTraces traces = new ArrayListTraces();  //ligne dependant de la Structure choisie
		traces.load("capture_wifi.csv","capture_gps.csv",0.85);
		System.out.println(System.currentTimeMillis());
		traces.extract("<hidden>");
		System.out.println(System.currentTimeMillis());
		//temps :1462382600852
		//       1462382600861
		//donc difference de 9
	}
}


	

