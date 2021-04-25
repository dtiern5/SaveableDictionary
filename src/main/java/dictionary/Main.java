package dictionary;

public class Main {

    public static void main(String[] args) {
        SaveableDictionary dictionary = new SaveableDictionary("words.txt");
        boolean wasSuccessful = dictionary.load();

        if (wasSuccessful) {
            System.out.println("Successfully loaded the dictionary from file");
        }

        dictionary.add("pantalones", "pants");
        dictionary.add("bolsa", "bag");
        
        dictionary.save();

    }
}
