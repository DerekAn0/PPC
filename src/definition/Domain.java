package definition;

import java.util.Iterator;

public interface Domain extends Iterable<Integer> {
    public Domain clone();
    
    public int size();

    // retourne vrai ssi la valeur v est dans le domaine
    public boolean contains(int v);

    // retourne la premiere valeur du domaine
    public int firstValue();

    // retourne la derniere valeur du domaine
    public int lastValue();

    // supprime la valeur v du domaine
    public void remove(int v);

    // supprime toutes les valeurs du domaine excepte v
    public void fix(int v);


    public Iterator<Integer> iterator();


}
