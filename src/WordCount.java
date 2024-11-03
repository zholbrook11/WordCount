import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.HashMap;

public class WordCount {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a filename to read from:");
        String filename = scanner.nextLine();
        HashMap<String, Integer> map = countWords(filename);
        
        if (map != null) {
            System.out.println("Words have been counted! Enter a new filename to print to:");
            String userFileName = scanner.nextLine();
            writeWordCountToFile(map, userFileName);
            System.out.println("Word counts have been saved to file " + userFileName);
        } else {
            System.out.println("Counting failed due to file not found.");
        }
    }

    public static HashMap<String, Integer> countWords(String filename){
        try (Scanner scanner = new Scanner(new File(filename))){
            HashMap<String, Integer> map = new HashMap<>();
            while (scanner.hasNextLine() != false){
                String word = scanner.nextLine().toLowerCase();
                if (map.containsKey(word.toLowerCase())){
                    map.put(word.toLowerCase(), map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }
            return map;
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
            return null;
        }
    }

    public static void writeWordCountToFile(HashMap<String, Integer> map, String userFileName){
        try (PrintWriter pw = new PrintWriter(userFileName)){
            for (String key : map.keySet()){
                pw.println(key + " " + map.get(key));
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found. You have entered an invalid filename.");
        }
    }
}