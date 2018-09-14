package definition;

public class Superieur implements Constraint {

	Variable[] variables;
	
	
	public Superieur(Variable[] variables) {
		// TODO Auto-generated constructor stub
		this.variables = variables;
	}
	
	@Override
	public Variable[] getVars() {
		// TODO Auto-generated method stub
		return variables;
	}

	@Override
	public boolean isSatisfied() {
		// TODO Auto-generated method stub
		return variables[0].getValue()>variables[1].getValue();
	}

	@Override
	public boolean isNecessary() {
		// TODO Auto-generated method stub
		return this.variables[0].getSup()>this.variables[1].getInf();
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return variables[0].name+" > "+ variables[1].name;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Variable a1 = new Variable("a1",1,0,3);
		Variable a2 = new Variable("a2",1,2,4);
		
		Variable[] listVar = new Variable[2];
		listVar [0]= a1;
		listVar [1]= a2;
//		a1.instantiate(1);
//		a2.instantiate(2);
		
		Superieur mt = new Superieur(listVar);
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(mt.isNecessary());
	}

	@Override
	public boolean filtrer() {
		int i = this.variables[0].getSup();
		while(i<=this.variables[1].getSup())
		{
			this.variables[1].remValue(i);
		}
		return this.variables[0].getDomainSize()!=0&&
				this.variables[1].getDomainSize()!=0;
	}

}
