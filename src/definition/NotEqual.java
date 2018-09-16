package definition;


public class NotEqual implements Constraint {
	
	Variable[] variables;
	
	public NotEqual(Variable[] variables) {
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
		return this.variables[0].getValue()!=this.variables[1].getValue();
	}

	@Override
	public boolean isNecessary() {
		// TODO Auto-generated method stub
		if (this.variables[0].isInstantiated()&&this.variables[1].isInstantiated()) {
			return this.isSatisfied();
		}
		else {
			return true;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return variables[0].name+" != "+ variables[1].name;
	}


	@Override
	public boolean filtrer() {
		// TODO Auto-generated method stub
		if(this.variables[0].getDomainSize()!=0&&
				this.variables[1].getDomainSize()!=0) 
		{
			if (this.variables[0].isInstantiated()) {
				this.variables[1].remValue(this.variables[0].getValue());
			}
		}
		if(this.variables[0].getDomainSize()!=0&&
				this.variables[1].getDomainSize()!=0) 
		{
			if (this.variables[1].isInstantiated()) {
				this.variables[0].remValue(this.variables[1].getValue());
			}
		}
		
		return this.variables[0].getDomainSize()!=0&&
				this.variables[1].getDomainSize()!=0;
	}
	public static void main(String[] args) {
		Variable[] vL = new Variable[2];
		vL[0] = new Variable("v1",1,6,7);
		vL[1] = new Variable("v2",2,6,6);
		vL[0].instantiate(6);
		Constraint c1 = new NotEqual(vL);
		System.out.println(vL[0].getDomain());
		System.out.println(vL[1].getDomain());
		System.out.println(c1.filtrer());
		System.out.println(vL[0].getDomain());
		System.out.println(vL[1].getDomain());
	}

}
