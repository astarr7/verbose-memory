import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The TextAnalyzer class provides methods to analyze and process text data.
 */

public class TextAnalyzer {

	/**
     * Retrieves the poem section from the given file.
     *
     * @param filename the name of the file
     * @return the poem section as a string, or null if not found or an error occurs
     */
	
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

	/**
     * Removes HTML tags from the input string.
     *
     * @param input the input string containing HTML tags
     * @return the input string with HTML tags removed, or null if the input is null
     */
	
	static String removeHtmlTags(String input) {
		String result = null;
		
		if (input != null) {
			
			//Remove all HTML tags with regular expression
			result = input.replaceAll("<[^>]+>", " ");
		}
		
		return result;
	}

	/**
     * Removes punctuation marks from the input string.
     *
     * @param input the input string containing punctuation marks
     * @return the input string with punctuation marks removed, or null if the input is null
     */
	
	static String removePunctuation(String input) {
		String result = null;
		
		if (input != null) {
			
			//Remove punctuation with regular expression
			result = input.replaceAll("[^a-zA-Z0-9]", " ");
		}
		
		return result;
	}

	/**
     * Analyzes the input text and displays the word frequency.
     *
     * @param input the input text to analyze
     */
	
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
		int count = 0;
	      for (Map.Entry<String, Integer> entry : sortedEntries) {
	      	System.out.println(entry.getKey() + ": " + entry.getValue() + " time(s)");
		      count++;
	      	if (count == 20) {
	      		break;}
	      }
		}
	}

	/**
     * The main method reads a poem from a file, removes HTML tags and punctuation marks, and analyzes the text.
     *
     * @param args command-line arguments (not used)
     * @throws IOException if an I/O error occurs
     */
	
	//Main
    public static void main(String[] args) throws IOException {    	
        String filename = "TheRavenPoemWithHTMLTags.txt";
        String poemSection = getPoemSection(filename);        
        String poemWithoutHtmlTags = removeHtmlTags(poemSection);
        String poem = removePunctuation(poemWithoutHtmlTags);
        analyzeText(poem);
    }
}

public class removeHtmlTagsTest {

    @Test
    public void testRemoveHtmlTags() {
        String input = "<p>This is a test.</p>";
        String expectedOutput = " This is a test. ";
        
        String actualOutput = TextAnalyzer.removeHtmlTags(input);
        
        assertEquals(expectedOutput, actualOutput);
    }
}
public class removePunctuationTest {

    @Test
    public void testRemovePunctuation() {
        String input = "This!is?a:test";
        String expectedOutput = "This is a test";
        
        String actualOutput = TextAnalyzer.removePunctuation(input);
        
        assertEquals(expectedOutput, actualOutput);
    }
}
