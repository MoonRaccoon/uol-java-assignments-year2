import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThesaurusFileManager {


    public static List<ThesaurusWord> readFromFile(String filename) {
        Scanner in = null;
        List<ThesaurusWord> words = new ArrayList<ThesaurusWord>();
        try {
            in = new Scanner(new FileInputStream(filename), "UTF-8");
        } catch (IOException e) {
            System.err.println("File " + filename + "does not exist.");
        }

        while(in.hasNextLine()) {
            String line1 = in.nextLine();
            if (in.hasNextLine()) {
                String line2 = in.nextLine();
                if (in.hasNextLine()) {
                    String line3 = in.nextLine();
                    ThesaurusWord w = parseThesaurusWord(line1, line2, line3);
                    if (w != null) words.add(w);
                }
            }
        }
        in.close();
        return words;
    }

    private static ThesaurusWord parseThesaurusWord(String line1, String line2, String line3) {
        String regex = "\\:\\s+"; //regular expression to find a colon followed by at least 1 character of whitespace
        String[] word = line1.split(regex, 2);
        String[] synonyms = line2.split(regex, 2);
        String[] antonyms = line3.split(regex, 2);

        //return null if ': ' not found in all the Strings
        if (word.length == 2 && synonyms.length == 2 && antonyms.length == 2) {
            String[] syns = synonyms[1].split(",\\s+");
            String[] ants = antonyms[1].split(",\\s+");
            return new ThesaurusWord(word[1], Arrays.asList(syns), Arrays.asList(ants));
        } else {
            return null;
        }
    }

    public static boolean writeToFile(String filename, List<ThesaurusWord> words) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
            for (ThesaurusWord w : words) {
                String s = String.format("%s%n", w);
                out.write(s);
            }
            out.close();
            return true;
        } catch (Exception e) {
            System.err.println("error writing to file " + filename + ". " + e.getLocalizedMessage());
            return false;
        }
    }
}
