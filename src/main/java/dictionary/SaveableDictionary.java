package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class SaveableDictionary {

    private HashMap<String, String> englishToSpanish;
    private HashMap<String, String> spanishToEnglish;
    private File file;

    public SaveableDictionary() {
        this.englishToSpanish = new HashMap<>();
        this.spanishToEnglish = new HashMap<>();
    }

    public SaveableDictionary(String file) {
        this();
        this.file = new File(file);
    }

    public boolean load() {
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(":");
                String newWord = parts[0];
                String newTranslation = parts[1];
                englishToSpanish.put(newTranslation, newWord);
                spanishToEnglish.put(newWord, newTranslation);
            }
        } catch (Exception e) {
            System.out.println("Error in load() method: " + e);
            return false;
        }

        return true;
    }

    public void add(String word, String translation) {
        if (!spanishToEnglish.containsKey(word)) {
            spanishToEnglish.put(word, translation);
        }
        if (!englishToSpanish.containsKey(translation)) {
            englishToSpanish.put(translation, word);
        }

    }

    public void delete(String word) {
        if (spanishToEnglish.containsKey(word)) {
            String translation = spanishToEnglish.get(word);
            spanishToEnglish.remove(word);
            englishToSpanish.remove(translation);
        }
        if (englishToSpanish.containsKey(word)) {
            String translation = englishToSpanish.get(word);
            englishToSpanish.remove(word);
            spanishToEnglish.remove(translation);
        }

    }

    public String translate(String word) {
        String translation = null;
        if (spanishToEnglish.containsKey(word)) {
            translation = spanishToEnglish.get(word);
        }
        if (englishToSpanish.containsKey(word)) {
            translation = englishToSpanish.get(word);
        }
        return translation;
    }

    public boolean save() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(file);
            for (String word : spanishToEnglish.keySet()) {
                writer.println(word + ":" + spanishToEnglish.get(word));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        writer.close();
        return true;
    }

}
