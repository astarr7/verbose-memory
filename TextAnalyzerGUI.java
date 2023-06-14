package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.*;

/**
 * A text analyzer application that analyzes the frequency of words in a given text.
 */

public class TextAnalyzerGUI extends Application {
    private TextArea textArea;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The entry point of the JavaFX application.
     *
     * @param args The command-line arguments.
     */
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Analyzer");

        // Create TextArea
        textArea = new TextArea();
        textArea.setWrapText(true);

        // Create Button
        Button analyzeButton = new Button("Analyze");
        analyzeButton.setOnAction(e -> analyzeText());

        // Create VBox layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(textArea, analyzeButton);

        // Create Scene
        Scene scene = new Scene(vbox, 400, 300);

        // Set the Scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Analyzes the text entered in the TextArea.
     */

    private void analyzeText() {
        String poemSection = getPoemSection("TheRavenPoemWithHTMLTags2.txt");
        String poemWithoutHtmlTags = removeHtmlTags(poemSection);
        String poem = removePunctuation(poemWithoutHtmlTags);
        analyzeAndDisplayResult(poem);
    }

    /**
     * Retrieves the poem section from the given file.
     *
     * @param filename The name of the file.
     * @return The poem section.
     */
    
    private String getPoemSection(String filename) {
        String poemSection = null;

        if (filename != null) {
            try {
                // Create new input stream
                FileInputStream fin = new FileInputStream(filename);
                Scanner fileInput = new Scanner(fin);

                // Find start point of poem including Title
                String startPoint = "<h1>";
                String endPoint = "</div>";

                // Do not start until first <h1> found
                boolean startingPointReached = false;
                if (fileInput != null) {

                    while (fileInput.hasNext()) {

                        String nextWord = fileInput.next();

                        // Start point found
                        if (nextWord.contains(startPoint)) {
                            startingPointReached = true;
                        }
                        // If end point found, break
                        if (startingPointReached) {
                            if (nextWord.contains(endPoint)) {
                                break;
                            }
                            if (poemSection == null) {
                                poemSection = nextWord;
                            } else {

                                // add space
                                poemSection += " " + nextWord;
                            }
                        }
                    }
                }

                // Close
                fileInput.close();
                fin.close();
            } catch (Exception e) {
                System.out.println("FILE NOT FOUND!");
            }
        }
        return poemSection;
    }

    /**
     * Removes HTML tags from the given input.
     *
     * @param input The input string.
     * @return The input string with HTML tags removed.
     */
    
    private String removeHtmlTags(String input) {
        String result = null;

        if (input != null) {

            // Remove all HTML tags with regular expression
            result = input.replaceAll("<[^>]+>", " ");
        }

        return result;
    }

    /**
     * Removes punctuation from the given input.
     *
     * @param input The input string.
     * @return The input string with punctuation removed.
     */
    
    private String removePunctuation(String input) {
        String result = null;

        if (input != null) {

            // Remove punctuation with regular expression
            result = input.replaceAll("[^a-zA-Z0-9]", " ");
        }

        return result;
    }

    /**
     * Analyzes the given text and displays the result in the TextArea.
     *
     * @param input The text to analyze.
     */
    
    private void analyzeAndDisplayResult(String input) {
        if (input != null) {
            String[] words = input.split(" ");

            // Create new HashMap
            Map<String, Integer> wordCount = new HashMap<>();

            // Read through file and find words
            for (String nextWord : words) {

                // Get next word and capitalize all
                nextWord = nextWord.trim();
                if (!nextWord.equals("")) {
                    nextWord = nextWord.toUpperCase();

                    // Determine if word is in Map
                    if (wordCount.containsKey(nextWord)) {
                        int count = wordCount.get(nextWord);

                        // If in map, add 1 to frequency
                        wordCount.put(nextWord, count + 1);
                        // Otherwise create new word with frequency
                    } else {
                        wordCount.put(nextWord, 1);
                    }
                }
            }

            // Order words from most occurred to least occurred
            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCount.entrySet());
            Collections.sort(sortedEntries, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {

                    // sort by descending order of value (count)
                    return e2.getValue().compareTo(e1.getValue());
                }
            });

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("Word : Frequency\n");

            // Print out results
            int count = 0;
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                resultBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append(" time(s)\n");
                count++;
                if (count == 20) {
                    break;
                }
            }

            textArea.setText(resultBuilder.toString());
        }
    }
}  