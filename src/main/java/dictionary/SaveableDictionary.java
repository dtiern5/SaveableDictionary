package dictionary;

import java.util.HashMap;

public class SaveableDictionary {

    private HashMap<String, String> dictionary;

    public SaveableDictionary() {
        this.dictionary = new HashMap<>();
    }

    public void add(String word, String translation) {
        if (!dictionary.containsKey(word)) {
            dictionary.put(word, translation);
        }
        if (!dictionary.containsValue(word)) {
            dictionary.put(translation, word);
        }
    }

    public String translate(String word) {
            return dictionary.get(word);
    }
    
    public void printDict() {
        for (String word : dictionary.keySet()) {
            System.out.println(word);
        }
    }

}
