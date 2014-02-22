package Quiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class First {
	
	private static Scanner s;
	
	public static Map<String, String> createDict(String filename){
		Map<String, String> dictionary = new HashMap<String, String>();		
		String val[];
			
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    for(String line; (line = br.readLine()) != null; ) {		    	
		    	val = line.split(",");
		       dictionary.put(val[1].trim().toLowerCase(),val[0].trim().toLowerCase());
		    }
		    br.close();
		    // line is not visible here.
		} catch (FileNotFoundException e) {
			System.out.println(filename+" must be from the same directory where .class is located");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/O Error occured.");
			e.printStackTrace();
		}
		
		return dictionary;
		
	}
	public static String getKeyFromValue(Map<String, String> dict,String findme){
		
		Iterator<Entry<String, String>> it = dict.entrySet().iterator();
	    while (it.hasNext()) {
	         Entry<String, String> pairs = it.next();
	        //System.out.println(pairs.getKey() + " = " + pairs.getValue());
	         if(pairs.getValue().equalsIgnoreCase(findme)){
	        	 return pairs.getKey();
	         }	        	 	        
	    }
	    return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		s = new Scanner(System.in);
		Map<String, String> dict;
		String state,askthis;
		int correct=0,mistakes=0,ctr=0;
		
		/*
		if(args.length != 1){
			System.out.println("quizfile not found ");
			return;
		}
		
		dict = createDict(args[0]);
		*/
		dict = createDict("c:\\tmp\\statecaps.csv");
		List<String> list = new ArrayList<String>(dict.values());		
		java.util.Collections.shuffle((List<?>) list);		
		Iterator<String> iterator = list.iterator();
		
		System.out.print("This is an Identification Quiz and is about United States Capitals, Goodluck!\n\n");
		
		do{
			askthis = iterator.next();				
			System.out.print("\n"+ ++ctr+". Enter the state for capital "+askthis+":");					
			state = dict.get(s.nextLine().toLowerCase());
			if(state!=null){
				System.out.println("Correct Answer!");
				correct++;
			}
			else{
				System.out.println("Wrong Answer! should be "+getKeyFromValue(dict,askthis));
				mistakes++;
			}
			
			System.out.print("Current Score "+correct);
			System.out.print("\n\nDo you want to continue the quiz [y/n]?");
			
			
			
			if(s.nextLine().equalsIgnoreCase("n"))
				break;

		}while (iterator.hasNext());	
		System.out.print("Overall Score : "+correct+"/"+(correct+mistakes));
		System.out.print("\n---- End Of Quiz ----");
	}

}
