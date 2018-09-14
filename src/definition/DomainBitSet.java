package definition;
import java.awt.print.Printable;
import java.util.BitSet;
import java.util.Iterator;



public class DomainBitSet implements Domain {

    BitSet values; // un bitset represente le domaine (voir api java pour plus d'infos)
    
    int index=0;
    // Construit un domaine dont les valeurs sont comprises entre min et max (inclus)
    public DomainBitSet(int min, int max) {
    	this.values = new BitSet();
    	this.values.set(min,max+1);
    }
    
    public DomainBitSet()
    {
    	this.values= new BitSet();
    }
    public DomainBitSet(DomainBitSet dom) {
    	this.values = (BitSet) dom.values.clone();
    }
    public DomainBitSet clone() {
	// ATTENTION A completer !!!!
    	BitSet bitSet= new BitSet();
    	bitSet = (BitSet)this.values.clone();
    	DomainBitSet dbs = new DomainBitSet();
    	dbs.values=bitSet;
    	return dbs;
    }
    // retourne la taille du domaine, ie le nombre de valeurs dans le domaine
    public int size() {
    	return this.values.cardinality();
    }

    // retourne la premiere valeur du domaine
    public int firstValue() {
	// ATTENTION A completer !!!!
    	return this.values.nextSetBit(0);
    }

    // retourne la derniere valeur du domaine
    public int lastValue() {
	// ATTENTION A completer !!!!
    	return this.values.length()-1;
    }

    public String toString() {
    	return this.values.toString();
    }

    public Iterator<Integer> iterator() {
	// ATTENTION a completer
    	Iterator<Integer> iter = new Iterator<Integer>() {
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index<values.length()-1;
			}
			@Override
			public Integer next() {
				// TODO Auto-generated method stub
				index++;
				while(values.get(index)==false)
				{
					index++;
				}
				return index;
			}
		};
		return iter;
    }

    public boolean contains(int v) {
    	return this.values.get(v);
    }

    public void remove(int v) {
	// TODO Auto-generated method stub
    	this.values.set(v,false);
    }

    public void fix(int v) {
	// TODO Auto-generated method stub
    	this.values.clear();
    	this.values.set(v);
    }
    
    public static void main(String[] args) {
		DomainBitSet domainBitSet = new DomainBitSet(1,5);
		domainBitSet.remove(4);
		Iterator<Integer> domainIter = domainBitSet.iterator();
		for (Integer integer : domainBitSet) {
			System.out.println(integer);
		}
		
		
		
		
    }
}

