package definition;

public class SuperieurEqual implements Constraint {

	Variable[] variables;
	
	public SuperieurEqual(Variable[] variables) {
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
		return variables[0].getValue()>=variables[1].getValue();
	}

	@Override
	public boolean isNecessary() {
		// TODO Auto-generated method stub
		return this.variables[0].getSup()>=this.variables[1].getInf();
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return variables[0].name+" >= "+ variables[1].name;
	}

	@Override
	public boolean filtrer() {
		// TODO Auto-generated method stub
		int domainSize0 = this.variables[0].getDomainSize();
		int domainSize1 = this.variables[1].getDomainSize();
		for(int i = this.variables[0].getInf();i<this.variables[1].getInf();i++)
		{
			if(this.variables[0].getDomainSize()!=0&&
					this.variables[1].getDomainSize()!=0) 
			{
				this.variables[0].remValue(i);
			}else {
				return false;
			}
		}

		for(int i = this.variables[0].getSup()+1;i<=this.variables[1].getSup();i++)
		{
			if(this.variables[0].getDomainSize()!=0&&
					this.variables[1].getDomainSize()!=0) 
			{
				this.variables[1].remValue(i);
			}else {
				return false;
			}				
		}
		return this.variables[0].getDomainSize()!=0&&
				this.variables[1].getDomainSize()!=0;
	}
	public static void main(String[] args) {
		Variable[] vL = new Variable[3];
		vL[0] = new Variable("v1",1,1,3);
		vL[1] = new Variable("v2",2,1,3);
		vL[0].instantiate(1);
		Constraint c1 = new SuperieurEqual(vL);
		
		System.out.println(vL[0].getDomain());
		System.out.println(vL[1].getDomain());
		System.out.println(c1.filtrer());
		System.out.println(vL[0].getDomain());
		System.out.println(vL[1].getDomain());
	}

}
