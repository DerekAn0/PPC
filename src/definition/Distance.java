package definition;
import search.*;
public class Distance implements Constraint {
	
	
	Variable[] vars;
	public Distance(Variable[] vars) {
		// TODO Auto-generated constructor stub
		this.vars=vars;
	}
	@Override
	public Variable[] getVars() {
		// TODO Auto-generated method stub
		return vars;
	}

	@Override
	public boolean isSatisfied() {
		// TODO Auto-generated method stub
		return this.vars[0].getValue()==Math.abs(this.vars[2].getValue()-this.vars[1].getValue());
	}

	@Override
	public boolean isNecessary() {
		// TODO Auto-generated method stub
		int p1 = Math.abs(this.vars[1].getInf()-this.vars[2].getInf());
		int p2 = Math.abs(this.vars[1].getSup()-this.vars[2].getSup());
		int p3 = Math.abs(this.vars[1].getInf()-this.vars[2].getSup());
		int p4 = Math.abs(this.vars[1].getSup()-this.vars[2].getInf());
		
		int max = Math.max( Math.max(p1, p2),Math.max(p3, p4));
		int min = Math.min( Math.min(p1, p2),Math.min(p3, p4));
		for(int i=min;i<max+1;i++)
		{
			if (this.vars[0].getDomain().contains(i)) {
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean filtrer() {
		// TODO Auto-generated method stub
		int p1 = Math.abs(this.vars[1].getInf()-this.vars[2].getInf());
		int p2 = Math.abs(this.vars[1].getSup()-this.vars[2].getSup());
		int p3 = Math.abs(this.vars[1].getInf()-this.vars[2].getSup());
		int p4 = Math.abs(this.vars[1].getSup()-this.vars[2].getInf());
		
		int max = Math.max( Math.max(p1, p2),Math.max(p3, p4));
		int min = Math.min( Math.min(p1, p2),Math.min(p3, p4));
		
		for(int i=this.vars[0].getInf();i<min;i++ )
		{
			this.vars[0].remValue(i);
		}
		
		for(int i=max+1;i<=this.vars[0].getSup();i++ )
		{
			this.vars[0].remValue(i);
		}
		return this.vars[0].getDomainSize()==0;
	}
	
	public static void main(String[] args) {
		Variable[] vars = new Variable[3];
		vars[0] = new Variable("d", 0, 1, 4);
		vars[1] = new Variable("x", 1, 6, 10);
		vars[2] = new Variable("y", 2, 1, 3);
		Constraint[] cons= new Distance[1];
		cons[0] = new Distance(vars);
		
		
		Csp csp = new Csp(vars, cons);
		GenerateAndTest.backtrack(csp);
		
	}
		
}
