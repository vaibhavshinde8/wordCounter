import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringJoiner;

public class WordCounter {

    public static void main(String[] args) {
        try {
            String userText = getUserText();
            String[] words = splitTextIntoWords(userText);
            int totalWordCount = words.length;
            Set<String> uniqueWords = countUniqueWords(words);
            Map<String, Integer> wordFrequency = countWordFrequency(words);

            System.out.println("Total words: " + totalWordCount);
            System.out.println("Unique words: " + uniqueWords.size());
            System.out.println("Word frequencies:");
            wordFrequency.forEach((word, frequency) -> System.out.println(word + ": " + frequency + " times"));
        } catch (IOException e) {
            System.err.println("An error occurred while processing the input.");
        }
    }

    private static String getUserText() throws IOException {
        System.out.print("Enter a text or provide a file path: ");
        String input;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            input = reader.readLine();
        }
        if (input.isEmpty()) {
            System.out.println("Input is empty. Please provide text or a valid file path.");
            System.exit(1);
        }

        if (input.toLowerCase().endsWith(".txt")) {
            return readTextFromFile(input);
        } else {
            return input;
        }
    }

    private static String readTextFromFile(String filePath) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = fileReader.readLine()) != null) {
                text.append(line).append(" ");
            }
            return text.toString();
        }
    }

    private static String[] splitTextIntoWords(String text) {
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(text.toLowerCase());

        StringJoiner words = new StringJoiner(" ");

        while (matcher.find()) {
            words.add(matcher.group());
        }

        return words.toString().split(" ");
    }

    private static Set<String> countUniqueWords(String[] words) {
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word);
        }
        return uniqueWords;
    }

    private static Map<String, Integer> countWordFrequency(String[] words) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        return wordFrequency;
    }
}



