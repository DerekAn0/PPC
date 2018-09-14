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
	public static void main(String[] args) {
		
		Variable a1 = new Variable("a1",1,1,1);
		Variable a2 = new Variable("a2",1,0,2);
		
		Variable[] listVar = new Variable[2];
		listVar [0]= a1;
		listVar [1]= a2;
		a1.instantiate(1);
		a2.instantiate(2);
		
		NotEqual mt = new NotEqual(listVar);
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(mt.isNecessary());
	}

	@Override
	public boolean filtrer() {
		// TODO Auto-generated method stub
		if (this.variables[0].isInstantiated()) {
			this.variables[1].remValue(this.variables[0].getValue());
		}
		if (this.variables[1].isInstantiated()) {
			this.variables[0].remValue(this.variables[1].getValue());
		}
		
		return this.variables[0].getDomainSize()!=0&&
				this.variables[1].getDomainSize()!=0;
	}

}
