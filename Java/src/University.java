
public class University {
	
	String name;
	String state;
	int studentNo;
	int expenses;
	double MaleFemaleRatio;
	
	public University(String n, String s, int no, int expenses, double mf){
		this.name = n;
		this.state = s;
		this.studentNo = no;
		this.expenses = expenses;
		this.MaleFemaleRatio = mf;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getState(){
		return this.state;
	}
	
	public int getStudentNo(){
		return this.studentNo;
	}
	
	public int getExpenses(){
		return this.expenses;
	}
	
	public double getMaleFemaleRatio(){
		return this.MaleFemaleRatio;
	}
}
