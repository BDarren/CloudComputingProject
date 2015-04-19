import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if( args==null || args.length < 5 ) {
			System.out.println("Usage:");
			System.out.println("  args[0]: path of unversitydata");
			System.out.println("  args[1]: path of the state income data");
			System.out.println("  args[2]: path of the state education rate data");
			System.out.println("  args[3]: path of the state gender ratio data");
			System.out.println("  args[4]: path of the state uneducated people data");
			System.exit(0);
		}
		
		
		String inputUniversity = args[0];
		String incomepath = args[1];
		String educationratepath = args[2]; 
		String malefemalepath = args[3];
		String uneducatedpeoplepath =args[4];
		
		HashMap<String, Double> res = new HashMap<String, Double>();
		
		AnalyzeUniversity test = new AnalyzeUniversity();
		HashMap<String, University> universities = test.getUniversity(inputUniversity);
		HashMap<String, State> states = test.getState(incomepath, educationratepath, malefemalepath, uneducatedpeoplepath);
		
		double weightincome=0.4, weightgender=0.2, weighteducation=0.4;
		for (Map.Entry<String, University> entry : universities.entrySet()) {
			double incomescore=0, genderscore=0, educationscore=0;
			double totalscore;
			String name = entry.getKey();
	        University uni = entry.getValue();
	        String state = uni.getState();
	        if(states.containsKey(state)){
		        State sta = states.get(state);
		        incomescore = 1/(uni.getExpenses()*2/sta.getAverageIncome());
		        genderscore = 1/Math.abs(1-uni.getMaleFemaleRatio()/sta.getMaleFemaleRatio());
		        educationscore = sta.getEducationRate();
		        totalscore = weightincome*incomescore+weightgender*genderscore+weighteducation*educationscore;
		        totalscore = Math.log10(totalscore);
		        res.put(name, totalscore);
	        }
	    }
		
		Map<String, Double> finallist = sortByComparator(res, false);
		
		for (Map.Entry<String, Double> entry : finallist.entrySet()) {
			System.out.println(entry.getKey()+"    "+ entry.getValue());
		}
		
	}
	
    private static Map<String, Double> sortByComparator(Map<String, Double> unsortMap, final boolean order)
    {

        List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Double>>()
        {
            public int compare(Entry<String, Double> o1,
                    Entry<String, Double> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Entry<String, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
