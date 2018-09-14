package definition;

import java.awt.print.Printable;
import java.time.chrono.ThaiBuddhistEra;

import jdk.nashorn.internal.ir.LiteralNode.PrimitiveLiteralNode;

public class Variable {

	public String name; // nom de la variable
    int idx; // identifiant unique
    Domain dom; // le domaine associe

    // construit une variable "name" definie entre les valeurs min et max (incluses)
    public Variable(String name, int idx, int min, int max) {
        this.name = name;
        this.idx = idx;
        this.dom = new DomainBitSet(min,max);
    }

    public Variable(Variable v) {
    	this.name = v.name;
    	this.idx = v.idx;
    	this.dom = v.getDomain().clone();
    }

    public Domain getDomain() {
        return this.dom;
    }

    public void setDomain(Domain d) {
        this.dom = d;
    }

    // retourne vrai ssi la variable est instanciee
    public boolean isInstantiated() {
        return this.dom.size() == 1;
    }

    // retourne vrai ssi le domaine de la variable contient la valeur v
    public boolean canBeInstantiatedTo(int v) {
        return this.dom.contains(v);
    }

    // retourne le nombre de valeurs dans le domaine de la variable
    public int getDomainSize() {
        return this.dom.size();
    }

    // supprime la valeur v du domaine de la variable
    public void remValue(int v) {
        this.dom.remove(v);
    }

    // instantie la variable a la valeur v
    public void instantiate(int v) {
        this.dom.fix(v);
    }

    // retourne la plus petite valeur du domaine
    public int getInf() {
        // ATTENTION A completer !!!!
        return this.dom.firstValue();
    }

    // retourne la plus grande valeur du domaine
    public int getSup() {
        // ATTENTION A completer !!!!
    	return this.dom.lastValue();
    }

    // retourne la valeur affectee a la variable ssi la variable est effectivement instanciee, sinon -1
    public int getValue() {
        // ATTENTION A completer !!!!
        if (this.isInstantiated()) {
			return this.dom.firstValue();
		}
        return -1;
    }

    // teste l'égalité des identifiants
    public boolean equals(Object o) {
        // ATTENTION A completer !!!!
    	try {
    		Variable oVariable = new Variable((Variable)o);
    		return oVariable.name==this.name;
		} catch (Exception e) {
			// TODO: handle exception
//			System.err.println(o.toString());
			return false;
		}
    }

    public String toString() {
        return this.name + " := " + this.dom;
    }
    
    public static void main(String[] args) {
		Variable v1 = new Variable("v1",1,45,100);
		System.out.println(v1.getSup());
		
	}
}