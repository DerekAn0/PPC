package definition;

import java.awt.print.Printable;

import sun.util.locale.provider.FallbackLocaleProviderAdapter;

public class CspGenerator {
	int nb_v;
	int[] taille;
	Constraint[] cons;
	int nb_cons;
	Variable[] vS;
	Csp csp ;
	static int freedom = 10;
	
	//taille indique la taille de la domain en generant un minimum valeur 
	
	public CspGenerator(int nb_v,int[] taille , int nb_cons) {
		this.nb_v=nb_v;
		this.taille = taille;
		this.nb_cons = nb_cons;
		this.vS = new Variable[nb_v];
		this.cons = new Constraint[nb_cons];
	}
	
	public Csp generateur() throws Exception {
		for(int i=1;i<nb_v+1;i++)
		{
			int randomUnder10 = (int)(Math.random()*freedom);
			Variable variable = new Variable("Variable "+i,i,randomUnder10, randomUnder10+taille[i-1]-1);
			vS[i-1] = variable;
		}
		vS[0] = new Variable("Variable "+1,1,2, 7);
		vS[1] = new Variable("Variable "+2,2,1, 6);
		vS[2] = new Variable("Variable "+3,3,2, 7);
		
		
		if(nb_cons>nb_v-1)
		{
			throw new Exception("Number of constraints bigger or equals nb of variables");
		}
		//generate constraints
		for(int i=1;i<1+nb_cons;i++)
		{
			Variable[] vartemp = new Variable[2];
			vartemp[0] = vS[i-1];
			vartemp[1] = vS[i];
			Constraint constraint = new Superieur(vartemp);
			cons[i-1] = constraint;
		}
		this.csp =  new Csp(vS, cons);
		return csp;
	}
	
	
	@Override
	public String toString() {
		try {
			StringBuffer conStr = new StringBuffer("");
			for(Constraint constraint : this.cons)
			{
				conStr.append(constraint.toString());
				conStr.append("\n\t\t");
			}
			return this.csp.toString()+"\n"+"constrains are: "+
					conStr;
		} catch (Exception e) {
			return "do it after generate()";
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int[] size = new int[5];
		size[0]=4;
		size[1]=4;
		size[2]=4;
		size[3]=4;
		size[4]=4;
		CspGenerator csg = new CspGenerator(5, size,4)  ;
		csg.generateur();
		System.out.println(csg);
		
		
	}

}
