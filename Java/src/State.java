
public class State {

	String name;
	double averageincome;
	double educationrate;
	double malefemaleratio;
	int uneducatedpeople;
	
	public State(String n, double a, double e, double m, int u){
		this.name = n;
		this.averageincome = a;
		this.educationrate = e;
		this.malefemaleratio = m;
		this.uneducatedpeople = u;
	}
	
	
	public double getAverageIncome(){
		return this.averageincome;
	}
	
	public double getEducationRate(){
		return this.educationrate;
	}
	
	public double getMaleFemaleRatio(){
		return this.malefemaleratio;
	}
	
	public int getUneducatedPeople(){
		return this.uneducatedpeople;
	}
	
}
