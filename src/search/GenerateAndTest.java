package search;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.sun.glass.ui.TouchInputSupport;
import com.sun.org.apache.regexp.internal.REUtil;
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
	
//	public static void propagationWithRouting(Csp csp)
//	{
//		int[] dsize = new int[csp.getVars().length];
//		for(int i=0;i<csp.getVars().length;i++)
//		{
//			dsize[i] = csp.getVars()[i].getDomainSize();
//		}
//		
//		HashMap<Variable, List<Constraint>> routingMap = new HashMap<Variable, List<Constraint>>();
//		for(Variable v:csp.getVars())
//		{
//			for(Constraint c: csp.getConstraints())
//			{
//
//				routingMap.put(v, value);
//			}
//		}
//	}
	
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

	public static void propagation(Csp csp) {
		int[] dsize = new int[csp.getVars().length];
		for(int i=0;i<csp.getVars().length;i++)
		{
			dsize[i] = csp.getVars()[i].getDomainSize();
		}
		for(Constraint c: csp.getConstraints())
		{
			System.out.println("pro");
			c.filtrer();
		}
		System.out.println(csp);
		for(int i=0;i<csp.getVars().length;i++)
		{
			if (csp.getVars()[i].getDomainSize()!=dsize[i]) {
				propagation(csp);
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
   
//	public static void resolutionPB(int valMax)
//	{
//		CspGenerator cspGenerator 
//	}
	
	
	
	
    public static void main(String[] args) throws Exception {
    	int variableNum = 10;
    	int variableSize = 10;
    	int constraintNum = 8;
    	
    	
    	int[] size  = new int[variableNum];
    	for(int i=0;i<variableNum;i++)
    	{
    		size[i]=variableSize;
    	}
    	CspGenerator cspGen  = new CspGenerator(variableNum, size, constraintNum);
    	Csp csp = cspGen.generateur();
    	System.out.println(csp);
//    	propagation(csp);

//		for(Constraint c: csp.getConstraints())
//		{
//			System.out.println("pro");
//			c.filtrer();
//		}
    	long startTime1 = System.currentTimeMillis();
    	backtrack(csp);
    	long endTime1 =  System.currentTimeMillis();	
    	System.out.println(" ----------------- Statistic start ---------------------- ");
    	System.out.println("bruteForceSearch is computed in : " + (endTime1 - startTime1) + " ms");
    	System.out.println("Soulution has : "+compteur);
    	System.out.println("Iteraters Node has : "+compteurParcours);
    	System.out.println("All Solutions have :"+(int)Math.pow(variableSize,variableNum));
    	System.out.println(" ----------------- Statistic end ------------------------- ");

//    	compteur=0;
//    	compteurParcours=0;
    	
//    	long startTime = System.currentTimeMillis();
//    	backtrack(csp);
//    	long endTime =  System.currentTimeMillis();
//    	
//    	System.out.println(" ----------------- Statistic start ---------------------- ");
//    	System.out.println("backtrackWithFligtage is computed in : " + (endTime - startTime) + " ms");
//    	System.out.println("Soulution has : "+compteur);
//    	System.out.println("Iteraters Node has : "+compteurParcours);
//    	System.out.println("All Solutions have :"+(int)Math.pow(variableSize,variableNum));
//    	System.out.println(" ----------------- Statistic end ------------------------- ");
	}
}
