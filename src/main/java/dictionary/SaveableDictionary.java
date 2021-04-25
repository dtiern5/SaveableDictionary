package dictionary;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class SaveableDictionary {

    private HashMap<String, String> englishToFinnish;
    private HashMap<String, String> finnishToEnglish;
    private String file;

    public SaveableDictionary() {
        this.englishToFinnish = new HashMap<>();
        this.finnishToEnglish = new HashMap<>();
    }

    public SaveableDictionary(String file) {
        this();
        this.file = file;
    }

    public boolean load() {

        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(":");
                String newWord = parts[0];
                String newTranslation = parts[1];
                finnishToEnglish.put(newWord, newTranslation);
                englishToFinnish.put(newTranslation, newWord);
            }
        } catch (Exception e) {
            System.out.println("Error in load() method: " + e);
            return false;
        }

        return true;
    }

    public void add(String word, String translation) {
        if (!finnishToEnglish.containsKey(word)) {
            finnishToEnglish.put(word, translation);
        }
        if (!englishToFinnish.containsKey(translation)) {
            englishToFinnish.put(translation, word);
        }

    }

    public void delete(String word) {
        if (englishToFinnish.containsKey(word)) {
            String translation = englishToFinnish.get(word);
            englishToFinnish.remove(word);
            finnishToEnglish.remove(translation);
        }
        if (finnishToEnglish.containsKey(word)) {
            String translation = finnishToEnglish.get(word);
            finnishToEnglish.remove(word);
            englishToFinnish.remove(translation);
        }

    }

    public String translate(String word) {
        String translation = null;
        if (englishToFinnish.containsKey(word)) {
            translation = englishToFinnish.get(word);
        }
        if (finnishToEnglish.containsKey(word)) {
            translation = finnishToEnglish.get(word);
        }
        return translation;
    }

}
