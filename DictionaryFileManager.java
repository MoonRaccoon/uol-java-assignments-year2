import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryFileManager {
    public static List<DictionaryWord> readFromFile(String filename) {
		Scanner in = null;
		List<DictionaryWord> words = new ArrayList<DictionaryWord>();
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
                    DictionaryWord w = parseDictionaryWord(line1, line2, line3);
                    if (w != null) words.add(w);
                }
            }
        }
		in.close();
		return words;
	}

	private static DictionaryWord parseDictionaryWord(String line1, String line2, String line3) {
        String regex = "\\:\\s+"; //regular expression to find a colon followed by at least 1 character of whitespace
        //splits around a regulur expression designed to remove the first colon and any whitespace following the colon
        //see http://www.vogella.com/tutorials/JavaRegularExpressions/article.html and
        //https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#split(java.lang.String)

		String[] word = line1.split(regex, 2);
        String[] definition = line2.split(regex, 2);
        String[] usage = line3.split(regex, 2);

		//return null if ': ' not found in all the Strings
		if (word.length == 2 && definition.length == 2 && usage.length == 2) {
            return new DictionaryWord(word[1], definition[1], usage[1]);
        } else {
            return null;
        }
	}

	public static boolean writeToFile(String filename, List<DictionaryWord> words) {
		try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
            for (DictionaryWord w : words) {
                //String s = String.format("%s%n", w);
                out.write(w.toString()+"\n");
            }
            out.close();
            return true;
        } catch (Exception e) {
            System.err.println("error writing to file " + filename + ". " + e.getLocalizedMessage());
            return false;
        }
	}

}

