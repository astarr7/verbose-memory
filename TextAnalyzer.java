import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class TextAnalyzer {

	static String getPoemSection(String filename) {
		String poemSection = null;
		
		if (filename != null) {
			try {
				
				//Create new input stream
				FileInputStream fin = new FileInputStream(filename);
		        Scanner fileInput = new Scanner(fin);
		        
		        //Find start point of poem including Title
				String startPoint = "<h1>";
				String endPoint = "</div>";
				
				//Do not start until first <h1> found
				boolean startingPointReached = false;
				if (fileInput != null) {
			        
			        while (fileInput.hasNext()) {
			        	 
			            String nextWord = fileInput.next();
			            
			            //Start point found
			            if (nextWord.contains(startPoint)) {
			            	startingPointReached = true;
			            }
			            //If end point found, break
			            if (startingPointReached) {
			                if (nextWord.contains(endPoint)) {
			                	break;
			                }
			            	if (poemSection == null) {
			            		poemSection = nextWord;
			            	} else {
			            		
			            		//add space
			            		poemSection += " " + nextWord;
			            	}
			            }
			        }
				}
				
		        //Close
		        fileInput.close();
		        fin.close();
			} catch (Exception e) {
				System.out.println("FILE NOT FOUND!");
			}
		}
		return poemSection;
	}
	
	static String removeHtmlTags(String input) {
		String result = null;
		
		if (input != null) {
			
			//Remove all HTML tags with regular expression
			result = input.replaceAll("<[^>]+>", " ");
		}
		
		return result;
	}
	
	static String removePunctuation(String input) {
		String result = null;
		
		if (input != null) {
			
			//Remove punctuation with regular expression
			result = input.replaceAll("[^a-zA-Z0-9]", " ");
		}
		
		return result;
	}
	
	static void analyzeText(String input) {
		if (input != null) {
			String[] words = input.split(" ");
			
	      //Create new HashMap
	      Map<String, Integer> wordCount = new HashMap<>();
	
	      //Read through file and find words
	      for (String nextWord : words) {
	    	  
	      	//Get next word and capitalize all 
	          nextWord = nextWord.trim();
	          if (!nextWord.equals("")) {
		          nextWord = nextWord.toUpperCase();
		          
		          //Determine if word is in Map
		          if (wordCount.containsKey(nextWord)) {
		              int count = wordCount.get(nextWord);
		              
		              //If in map, add 1 to frequency
		              wordCount.put(nextWord, count + 1);
		              //Otherwise create new word with frequency
		          } else {
		              wordCount.put(nextWord, 1);
		          }
		      }
	      }
	      
	      //Order words from most occurred to least occurred
	      List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCount.entrySet());
	      Collections.sort(sortedEntries, new Comparator<Map.Entry<String, Integer>>() {
	          public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
	        	
	        	  // sort by descending order of value (count)
	              return e2.getValue().compareTo(e1.getValue());
	          }
	      });
	      System.out.println("Word : Frequency");
	      
	      //Print out results
	      for (Map.Entry<String, Integer> entry : sortedEntries) {
	      	System.out.println(entry.getKey() + ": " + entry.getValue() + " time(s)");
	      }
		}
	}
	
	//Main
    public static void main(String[] args) throws IOException {    	
        String filename = "TheRavenPoemWithHTMLTags.txt";
        String poemSection = getPoemSection(filename);        
        String poemWithoutHtmlTags = removeHtmlTags(poemSection);
        String poem = removePunctuation(poemWithoutHtmlTags);
        analyzeText(poem);
    }
}
