package definition;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Created by IntelliJ IDEA.
 * User: xlorca
 */
public class Csp {

    Variable[] vars; // l'ensemble des variables du CSP. Note: les domaines sont connus au travers des variables
    Constraint[] cons; // l'ensemble des contraintes du CSP
    
    public Csp(Variable[] vars, Constraint[] cons) {
        this.vars = vars;
        this.cons = cons;
    }

    public Variable[] getVars() {
        return vars;
    }
    
    public Constraint[] getConstraints() {
    	return this.cons;
    }

    // retourne la premiere variable non instanciee du csp
    public Variable randomVar() {
        for (Variable v : this.vars) {
            if (!v.isInstantiated()) {
                return v;
            }
        }
        return null;
    }

    // retourne vrai ssi toutes les variables sont instanciees
    public boolean allInstanciated() {
        for (Variable v : this.vars) {
            if (!v.isInstantiated()) {
                return false;
            }
        }
        return true;
    }

    // retourne vrai ssi l'ensemble des contraintes du CSP est verifie
    public boolean hasSolution() {
        // ATTENTION A completer !!!!
    	for(Constraint c:cons)
    	{
    		if(c.isSatisfied())
    		{
    			continue;
    		}
    		else {
    			return false;
    		}
    	}
        return true;
    }
    
    // retourne vrai ssi l'ensemble des contraintes du CSP est verifie
    public boolean hasNecessary() {
        // ATTENTION A completer !!!!
    	for(Constraint c:cons)
    	{
    		if(c.isNecessary())
    		{
    			continue;
    		}
    		else {
    			return false;
    		}
    	}
        return true;
    }
    
    
    

    public String toString() {
        String s = "";
        for (Variable v : this.vars) {
            s += v + "\n";
        }
        return s;
    }
    


}
