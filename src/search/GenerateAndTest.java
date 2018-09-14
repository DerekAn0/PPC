package search;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.glass.ui.TouchInputSupport;
import com.sun.org.apache.xerces.internal.parsers.NonValidatingConfiguration;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import definition.*;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import sun.misc.Cleaner;

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
	
	public GenerateAndTest(Csp csp) {
		// TODO Auto-generated constructor stub
		this.csp = csp ;
	}
	
	
	public static void backtrack1(Csp csp) {
		boolean skip = false;
		if (csp.hasNecessary()&&hasInitVar) {
			for(Variable v: csp.getVars())
	    	{
	    		if(v.isInstantiated()&&!inst.contains(v))
	    		{
	    			if (propagation(csp,v)) {
	    				inst.add(v);
					}
	    			else {
						skip=true;
					}
	    			System.out.println("----------------------");
	    			System.out.println(propagation(csp, v));
	    			System.out.println(csp);
	    			System.out.println("----------------------");
	    		}
	    	}
			if (!skip) {
				skip = false;
				if (csp.allInstanciated()) {
					System.out.println("all init");
					compteurParcours++;
		    		if (csp.hasSolution()) {    		
		    			compteur++;
			    		System.out.println("get solution: "+inst);
					}
				}else {
					Variable y  = csp.randomVar();
		    		for(int i = y.getInf();i<y.getSup()+1;i++)
		        	{
		        		Domain d1 = y.getDomain().clone();
		        		if(d1.size()==0)
		        		{
		        			System.out.println(y+"        "+i);
		        		}else {
		        			System.out.println(y+"  no   "+i);
						}
		        		
		        		y.instantiate(i);
		        		hasInitVar = true;
		        		backtrack1(csp);
		        		y.setDomain(d1);
		        	}
				}
			}
		}
		//init une variable pour le premier temp
		if (!hasInitVar) {
    		Variable y  = csp.randomVar();
    		System.out.println("first born "+y);
    		for(int i = y.getInf();i<y.getSup()+1;i++)
        	{
        		Domain d1 = y.getDomain().clone();
        		System.out.println(y+"      down      "+i);
    			y.instantiate(i);
				hasInitVar = true;
        		backtrack1(csp);
        		y.setDomain(d1);
        	}
		}
	}
	
	public static void backtrack(Csp csp) {
		for(Variable v: csp.getVars())
    	{
    		if(v.isInstantiated()&&!inst.contains(v))
    		{
    			hasInitVar = true;
    			inst.add(v);
    		}
    	}
		if (csp.hasNecessary()) {
			if (csp.allInstanciated()) {
				compteurParcours++;
	    		if (csp.hasSolution()) {    		
	    			compteur++;
//	    			System.out.println("get solution: "+inst);
				}
			}else {
				Variable y  = csp.randomVar();
	    		for(int i = y.getInf();i<y.getSup()+1;i++)
	        	{
	        		Domain d1 = y.getDomain().clone();
	        		y.instantiate(i);
	        		hasInitVar = true;
	        		backtrack(csp);
	        		y.setDomain(d1);
	        	}
			}
			//init une variable pour le premier temp
			if (!hasInitVar) {
	    		Variable y  = csp.randomVar();
	    		for(int i = y.getInf();i<y.getSup()+1;i++)
	        	{
	        		Domain d1 = y.getDomain().clone();
	        		y.instantiate(i);
	        		hasInitVar = true;
	        		backtrack(csp);
	        		y.setDomain(d1);
	        	}
			}
		}
	}

	public static boolean propagation(Csp csp,Variable v) {
//		ArrayList lc = new ArrayList<Constraint>();
//		for(Constraint c: csp.getConstraints())
//		{
//			for(int i=0;i<2;i++)
//			{
////				System.out.println("in propagation"+c+"  "+i+" "+v);
//				if (c.getVars()[i].equals(v)&&!lc.contains(c)) {
//					lc.add(c);
//				}
//			}
//		}
//		for (Iterator iterator = lc.iterator(); iterator.hasNext();) {
//			Constraint c = (Constraint) iterator.next();
//			Domain d1 = c.getVars()[0].getDomain();
//			Domain d2 = c.getVars()[1].getDomain();
//			if (!c.filtrer()) {
//				System.out.println(d1);
//				System.out.println(d2);
//				c.getVars()[0].setDomain(d1);
//				c.getVars()[1].setDomain(d2);
//				return false;
//			}
//		}
//		return true;
		pro
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
   
    public static void main(String[] args) throws Exception {
    	int variableNum = 3;
    	int variableSize = 6;
    	int constraintNum = 2;
    	
    	
    	int[] size  = new int[variableNum];
    	for(int i=0;i<variableNum;i++)
    	{
    		size[i]=variableSize;
    	}
    	CspGenerator cspGen  = new CspGenerator(variableNum, size, constraintNum);
    	Csp csp = cspGen.generateur();
    	
    	


    	System.out.println(csp);
//    	long startTime1 = System.currentTimeMillis();
//    	bruteForceSearch(csp);
//    	long endTime1 =  System.currentTimeMillis();	
//    	System.out.println(" ----------------- Statistic start ---------------------- ");
//    	System.out.println("bruteForceSearch is computed in : " + (endTime1 - startTime1) + " ms");
//    	System.out.println("Soulution has : "+compteur);
//    	System.out.println("Iteraters Node has : "+compteurParcours);
//    	System.out.println("All Solutions have :"+(int)Math.pow(variableSize,variableNum));
//    	System.out.println(" ----------------- Statistic end ------------------------- ");

//    	compteur=0;
//    	compteurParcours=0;
//    	System.out.println("============================================================");
    	long startTime = System.currentTimeMillis();
    	backtrack1(csp);
    	long endTime =  System.currentTimeMillis();
    	
    	System.out.println(" ----------------- Statistic start ---------------------- ");
    	System.out.println("backtrackWithFligtage is computed in : " + (endTime - startTime) + " ms");
    	System.out.println("Soulution has : "+compteur);
    	System.out.println("Iteraters Node has : "+compteurParcours);
    	System.out.println("All Solutions have :"+(int)Math.pow(variableSize,variableNum));
    	System.out.println(" ----------------- Statistic end ------------------------- ");
    	

	}
}
