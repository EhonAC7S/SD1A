package senCity;

import java.util.ArrayList;

public class Node {
	private Character lettre;
	private Node fils;
	private Node frere;
	private Traces traces;

	public Node() {
		
	}
	
	public Node(char let) {
		this.lettre = let;
	}
	
	public void setLettre(char let) {
		this.lettre=let;
	}

	public void setFils(Node fils) {
		this.fils = fils;
	}

	public void setFrere(Node bro) {
		this.frere = bro;
	}

	public void setTraces(Traces traces) {
		this.traces = traces;
	}

	public Character getLettre() {
		return this.lettre;
	}
	
	public Node getFrere() {
		return this.frere;
	}

	public Node getFils() {
		return this.fils;
	}
	
	public Traces getTraces() {
		return this.traces;
	}
	
	public boolean hasBrother() {
		return (this.frere!=null);
	}
	
	public boolean hasSon() {
		return (this.fils!=null);
	}
	
	public Node addNodeHorizontal(char lettre) {
		Node temp = this;
		if (temp.getLettre()==null) {
			temp.setLettre(lettre);
		}
		if (temp.getLettre()==lettre) {
			return temp;
		}
		while (temp.hasBrother()) {
			temp = temp.getFrere();
			if (temp.getLettre()==lettre) {
				return temp;
			}
		}
		temp.setFrere(new Node(lettre));
		temp = temp.getFrere();
		return temp;
	}
	
	public Node addNodeVertical() {
		if (this.getFils() == null) {
			this.setFils(new Node());
		}
		return this.getFils();
	}
	
	public Node hasBrotherNamed(char c) {
		Node temp = this;
		if (temp.getLettre()==null) {
			return null;
		}
		if (temp.getLettre()==c) {
			return temp;
		}
		while (temp.hasBrother()) {
			temp = temp.getFrere();
			if (temp.getLettre()==c) {
				return temp;
			}
		}
		return null;
	}
	
	public int taille() {
		/*int t1 = 0;
		int t2 = 0;
		if (this.hasBrother()) {
			t1 = this.getFrere().taille();
		}
		if (this.getFils()!=null) {
			t2 = this.getFils().taille();
		}
		return 1+t1+t2;*/
		return this.getFamily().size();
	}

	public ArrayList<Node> getFamily() {
		ArrayList<Node> brothersFamily = new ArrayList<Node>();
		ArrayList<Node> myFamily = new ArrayList<Node>();
		if (this.hasBrother()) {
			brothersFamily = this.getFrere().getFamily();
		}
		if (this.hasSon()) {
			myFamily = this.getFils().getFamily();
		}
		myFamily.addAll(brothersFamily);
		myFamily.add(this);
		return myFamily;
	}
	

}
