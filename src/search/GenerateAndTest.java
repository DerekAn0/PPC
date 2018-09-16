package search;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import definition.*;


/**
 * Created by IntelliJ IDEA.
 * User: xlorca
 */
public class GenerateAndTest {
	static ArrayList<Variable> inst = new ArrayList<Variable>();
	private Csp csp;
	static boolean hasInitVar = false;
	static int compteur = 0;
	static int compteurParcours = 0;	
	static HashMap<String, Domain> routing = new HashMap<String, Domain>();
	
	public GenerateAndTest(Csp csp) {
		// TODO Auto-generated constructor stub
		this.csp = csp ;
	}
	
	public static void backtrack(Csp csp) {
			if (csp.allInstanciated()) {
				compteurParcours++;
	    		if (csp.hasSolution()) {    		
	    			compteur++;
//	    			System.out.println(csp);
				}
			}else {
				Variable y  = csp.randomVar();
				
	    		for(int i = y.getInf();i<y.getSup()+1;i++)
	        	{
	    			HashMap<String, Domain> map = new HashMap<String, Domain>();
					for(Variable v:csp.getVars())
					{
						if (!v.equals(y)) {
							map.put(v.name, v.getDomain().clone());
						}
					}
	        		Domain d1 = y.getDomain().clone();
	        		y.instantiate(i);
	        		if (csp.hasNecessary()) {
	        			propagation(csp, y);
	        			if (AllGood(csp)) {
	        				backtrack(csp);
						}
					}
		        	y.setDomain(d1);
		    		for(Variable v:csp.getVars())
					{
						if (!v.equals(y)) {
							v.setDomain(map.get(v.name));
						}
					}
	        	}
			}	
	}
	public static boolean AllGood(Csp csp )
	{
		for(Variable v:csp.getVars())
		{
			if (v.getDomainSize()==0) {
				return false;
			}
		}
		return true;
	}
	public static void propagation(Csp csp,Variable v) {
		List l = new ArrayList<Integer>();
		List ecart = new ArrayList<Variable>();
		boolean empty = true;
		for(Variable v1: csp.getVars())
		{
			l.add(v1.getDomainSize());
		}
		for(Constraint c: csp.getConstraints())
		{
			if (c.getVars()[0].equals(v)||c.getVars()[1].equals(v)) {
				c.filtrer();
			}
		}
//		List l1 = new ArrayList<Variable>();
//		for(Variable v1: csp.getVars())
//		{
//			l1.add(v1.getDomainSize());
//		}

		for (int i = 0; i < csp.getVars().length; i++) {
			if ((Integer)csp.getVars()[i].getDomainSize()!=l.get(i)) {
				ecart.add(csp.getVars()[i]);
			}
		}
		if (!ecart.isEmpty()) {
			for (Iterator iterator = ecart.iterator(); iterator.hasNext();) {
				Variable variable = (Variable) iterator.next();
				propagation(csp, variable);
			}
		}

	}
	
	public static void bruteForceSearch(Csp csp) {
        // ATTENTION A completer !!!!
    	for(Variable v: csp.getVars())
    	{
    		if(v.isInstantiated()&&!inst.contains(v))
    		{
    			hasInitVar = true;
    			inst.add(v);
    		}
    	}
    	if(csp.allInstanciated())
    	{
    		compteurParcours++;
    		if (csp.hasSolution()) {    		
    			compteur++;
    			System.out.println("bruteforce get solution: "+inst);
			}
    	}
    	else {
    		Variable yVariable  = csp.randomVar();
    		for(int i = yVariable.getInf();i<=yVariable.getSup();i++)
    		{
        		Domain dd = yVariable.getDomain().clone();
    			yVariable.instantiate(i);
    			bruteForceSearch(csp);
    			yVariable.setDomain(dd);
    		}
    	}
    	if (!hasInitVar) {
    		Variable y  = csp.randomVar();
    		for(int i = y.getInf();i<y.getSup()+1;i++)
        	{
        		Domain d1 = y.getDomain().clone();
        		y.instantiate(i);
        		hasInitVar = true;
        		bruteForceSearch(csp);
        		y.setDomain(d1);
        	}
		}
    }
   
	public static void resolutionPB(int valMax)
	{
		Variable[] v = new Variable[10];
		for(int i=0;i<10;i++)
		{
			v[i] = new Variable("V"+i, i, 1, valMax);
			System.out.println(v[i]);
		}
		Variable[] vc1 = new Variable[2];
		vc1[0] = v[3];
		vc1[1] = v[0];
		
		Variable[] vc2 = new Variable[2];
		vc2[0] = v[5];
		vc2[1] = v[0];
		
		Variable[] vc3 = new Variable[2];
		vc3[0] = v[4];
		vc3[1] = v[1];
		
		Variable[] vc4 = new Variable[2];
		vc4[0] = v[6];
		vc4[1] = v[1];
		
		Variable[] vc5 = new Variable[2];
		vc5[0] = v[8];
		vc5[1] = v[1];
		
		Variable[] vc6 = new Variable[2];
		vc6[0] = v[3];
		vc6[1] = v[2];
		
		Variable[] vc7 = new Variable[2];
		vc7[0] = v[1];
		vc7[1] = v[4];
		
		Variable[] vc8 = new Variable[2];
		vc8[0] = v[6];
		vc8[1] = v[4];
		
		Variable[] vc9 = new Variable[2];
		vc9[0] = v[4];
		vc9[1] = v[7];
		
		Variable[] vc10 = new Variable[2];
		vc10[0] = v[9];
		vc10[1] = v[5];
		
		Variable[] vc11 = new Variable[2];
		vc11[0] = v[7];
		vc11[1] = v[6];
		
		Variable[] vc12 = new Variable[2];
		vc12[0] = v[6];
		vc12[1] = v[8];
		
		Variable[] vc13 = new Variable[2];
		vc13[0] = v[8];
		vc13[1] = v[7];
		
		Variable[] vc14 = new Variable[2];
		vc14[0] = v[3];
		vc14[1] = v[8];
		
		Variable[] vc15 = new Variable[2];
		vc15[0] = v[8];
		vc15[1] = v[9];
		
		Constraint cc[] = new Constraint[15];
		cc[0] = new Superieur(vc1);
		cc[1] = new Superieur(vc2);
		cc[2] = new NotEqual(vc3);
		cc[3] = new Superieur(vc4);
		cc[4] = new Superieur(vc5);
		cc[5] = new Superieur(vc6);
		cc[6] = new Superieur(vc7);
		cc[7] = new SuperieurEqual(vc8);
		cc[8] = new NotEqual(vc9);
		cc[9] = new Superieur(vc10);
		cc[10] = new SuperieurEqual(vc11);
		cc[11] = new NotEqual(vc12);
		cc[12] = new Superieur(vc13);
		cc[13] = new SuperieurEqual(vc14);
		cc[14] = new SuperieurEqual(vc15);
		
		Csp csp = new Csp(v, cc);
		System.out.println(csp);
		
		long startTime1 = System.currentTimeMillis();
    	
		System.out.println(csp);
		backtrack(csp);
		long endTime1 =  System.currentTimeMillis();	
		System.out.println(" ----------------- Statistic start ---------------------- ");
    	System.out.println("bruteForceSearch is computed in : " + (endTime1 - startTime1) + " ms");
    	System.out.println("Soulution has : "+compteur);
    	System.out.println("Iteraters Node has : "+compteurParcours);
    	System.out.println("All Solutions have :"+(int)Math.pow(valMax,10));
    	System.out.println(" ----------------- Statistic end ------------------------- ");
		
    	compteur=0;
		compteurParcours=0;
	}
	
	public static void circuitSuperieur(int nb){
		Variable[] variable = new Variable[nb];
		for(int i=0;i<nb;i++)
		{
			variable[i] = new Variable("v"+i,i,1,10);
		}
		Constraint cList[] = new SuperieurEqual[nb];
		
		for(int i=0;i<nb/2-1;i++)
		{
			Variable v[] = new Variable[2];
			v[0]=variable[i];
			v[1]=variable[i+1];
			cList[i] = new SuperieurEqual(v);
		}
		for(int i=nb/2;i<nb-1;i++)
		{
			Variable v1[] = new Variable[2];
			v1[0]=variable[i];
			v1[1]=variable[i+1];
			cList[i] = new SuperieurEqual(v1);
		}
		Variable v[] = new Variable[2];
		v[0]=variable[nb/2-1];
		v[1]=variable[0];
		cList[nb/2-1] = new SuperieurEqual(v);
		
		Variable v2[] = new Variable[2];
		v2[0]=variable[nb-1];
		v2[1]=variable[nb/2];
		cList[nb-1] = new SuperieurEqual(v2);
		
		Constraint cListR[] = new Constraint[nb+1];
		for(int i=0;i<nb;i++)
		{
			cListR[i]=cList[i];
		}
		Variable v3[] = new Variable[2];
		v3[0]=variable[0];
		v3[1]=variable[nb/2];
		cListR[nb] = new Superieur(v3);
		
//		Variable v4[] = new Variable[2];
//		v4[0]=variable[nb-1];
//		v4[1]=variable[0];
//		cListR[nb+1] = new Superieur(v4);
		//CSP GEN
		
		Csp csp = new Csp(variable, cListR);
//		System.out.println(csp);
//		 csp.getVars()[2].instantiate(2);
//		propagation(csp, csp.getVars()[2]);
		System.out.println(csp);
		

		long startTime1 = System.currentTimeMillis();
		backtrack(csp);
		long endTime1 =  System.currentTimeMillis();	
		System.out.println(" ----------------- Statistic start ---------------------- ");
    	System.out.println("bruteForceSearch is computed in : " + (endTime1 - startTime1) + " ms");
    	System.out.println("Soulution has : "+compteur);
    	System.out.println("Iteraters Node has : "+compteurParcours);
    	System.out.println("All Solutions have :"+(int)Math.pow(10,nb));
    	System.out.println(" ----------------- Statistic end ------------------------- ");
		compteur=0;
		compteurParcours=0;
	}
	
	
   
	public static void main(String[] args) throws Exception {
		//1
//    	resolutionPB(3);
//    	resolutionPB(4);
//    	resolutionPB(5);
//    	resolutionPB(6);
//    	resolutionPB(7);
		//2.1
//    	circuitSuperieur(1000);
    	//2.2
//    	circuitSuperieur(1000);
    	

    	
	}



}
