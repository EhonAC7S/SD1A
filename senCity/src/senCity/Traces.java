package senCity;

import java.util.Collection;
import java.util.Iterator;

public abstract class Traces extends AbstractTraces implements Iterable<Trace> {
	protected Collection<Trace> list ;
	
	public void ajouter(Trace elt){
		this.list.add(elt);
	}

	public int taille(){
		return list.size();
	}

	public abstract Traces extract(String SSID);
	
	public abstract void initialiser();
	
	@Override
	public Iterator<Trace> iterator() {
		return this.list.iterator();
	}
}
