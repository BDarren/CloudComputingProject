import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class AnalyzeUniversity {
	
	public HashMap<String, University> getUniversity(String path) throws IOException{
		
		FileInputStream instream = new FileInputStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
		HashMap<String, University> res = new HashMap<String, University>();
		
		String line;
		String name="";
		String state="";
		int studentNo=0;
		int expenses=0;
		double MaleFemaleRatio=0;
        while((line = reader.readLine()) != null){
        	if(line.contains("(DEF-INSTANCE")){
        		name = line.substring(14);
        	}
        	else if(line.contains("(STATE")){
        		state = line.substring(9, line.length()-1);
        	}
        	else if(line.contains("(NO-OF-STUDENTS THOUS")){
        		int start = line.indexOf(':');
        		int end = 0;
        		if(line.contains("+")){
        			end = line.lastIndexOf('+');
        		}
        		else{
        			end = line.lastIndexOf('-');
        		}
        		studentNo = Integer.parseInt(line.substring(start+1, end))*1000;
        	}
        	else if(line.contains("(EXPENSES THOUS$")){
        		int start = line.indexOf(':');
        		int end = 0;
        		if(line.contains("+")){
        			end = line.lastIndexOf('+');
        		}
        		else{
        			end = line.lastIndexOf('-');
        		}
        		expenses = Integer.parseInt(line.substring(start+1, end))*1000;
        	}
        	else if(line.contains("(MALE:FEMALE RATIO")){
        		int start = line.indexOf('O');
        		int end = line.lastIndexOf(':');
        		int realend = line.lastIndexOf(')');
        		double male = Integer.parseInt(line.substring(start+2, end));
        		double female = Integer.parseInt(line.substring(end+1, realend));
        		MaleFemaleRatio = male/female;      		
        	}
        	else if(line.equals(")")){
        		University instance = new University(name, state, studentNo, expenses, MaleFemaleRatio);
        		res.put(name, instance);
        	}
        }
        
        
        reader.close();
        instream.close();
        return res;
	}
	
	public HashMap<String, State> getState(String incomepath, String educationratepath, 
			String malefemalepath, String uneducatedpeoplepath) throws IOException{		
		
		FileInputStream instreamincome = new FileInputStream(incomepath);
		BufferedReader readerincome = new BufferedReader(new InputStreamReader(instreamincome));
		FileInputStream instreameducation = new FileInputStream(educationratepath);
		BufferedReader readereducation = new BufferedReader(new InputStreamReader(instreameducation));
		FileInputStream instreammalefemale = new FileInputStream(malefemalepath);
		BufferedReader readergender = new BufferedReader(new InputStreamReader(instreammalefemale));
		FileInputStream instreampeople = new FileInputStream(uneducatedpeoplepath);
		BufferedReader readerpeople = new BufferedReader(new InputStreamReader(instreampeople));
		HashMap<String, State> res = new HashMap<String, State>();
		
		String lineIncome, lineEducation, lineGender, linePeople;
		String name="";
		double income=0;
		double educationrate=0;
		double genderrate = 0;
		int people = 0;		
		
		 while((lineIncome = readerincome.readLine()) != null&&(lineEducation = readereducation.readLine())!=null
				 &&(lineGender = readergender.readLine())!=null&&(linePeople = readerpeople.readLine())!=null){
			 String incometokens[] = lineIncome.split("[\t]+");
			 String educationtokens[] = lineEducation.split("[\t]+");
			 String gendertokens[] = lineGender.split("[\t]+");
			 String peopletokens[] = linePeople.split("[\t]+");
			 name = incometokens[0].toUpperCase();
			 income = Double.parseDouble(incometokens[1]);
			 educationrate = Double.parseDouble(educationtokens[1]);
			 genderrate = Double.parseDouble(gendertokens[1]);
			 people = Integer.parseInt(peopletokens[1]);
			 State instance = new State(name, income, educationrate, genderrate, people);
			 res.put(name, instance);
		 }
		
	    readerincome.close();
	    instreamincome.close();
	    readereducation.close();
	    instreameducation.close();
	    readergender.close();
	    instreammalefemale.close();
	    readerpeople.close();
	    instreampeople.close();
		return res;
		
	}
}
