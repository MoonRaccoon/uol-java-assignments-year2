import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DictionaryUI {
    private DictionaryEditor editor;
	private Scanner scanner;
	private boolean finished;
    private String filename;
    private List<DictionaryWord> words;

    public DictionaryUI(String filename) throws IOException, ClassNotFoundException {
        this.filename = filename;
        words = DictionaryFileManager.readFromFile(filename);
        editor = new DictionaryEditor(words);
		scanner = new Scanner(System.in);
        finished = false;
    }

    public static void main(String[] args) throws Exception {
    	DictionaryUI ui = new DictionaryUI("dictionary.txt");
    	ui.start();
	}

    private void start() {
    	String input;
		while (!finished) {
			printMainMenu();
			boolean invalidInput = false;
			do {
				input = getUserInput();
				if (isValidMenuChoice(input)) {
					processMenuChoice(input);
					invalidInput = false;
				} else {
					invalidInput(input);
					invalidInput = true;
				}
			} while (invalidInput);
		}
    }

    private void printMainMenu() {
		System.out.println();
		System.out.println("DICTIONARY");
		System.out.println("Select one of the following options:");
		System.out.println("1) Look up a word");
		System.out.println("2) Add word");
		System.out.println("3) Edit word");
		System.out.println("4) Remove word");
		System.out.println("5) View all words");
		System.out.println("6) Exit");
	}

	private void processMenuChoice(String input) {
		int i = Integer.parseInt(input);

		if (i == 1) {
		    lookup();
		}

		if (i == 2) {
		    addWord();
		}

		if (i == 3) {
		    editWord();
		}

		if (i == 4) {
			removeWord();
		}

		if (i == 5) {
			showAll();
		}

		if (i == 6) {
            quit();
		}
	}

	//print every word in the dictionary on its own line, showing the user the word field only
	private void showAll() {
    	for (int i = 0; i < words.size(); i++) {
    		System.out.println(words.get(i).getWord());
		}
	}

	//ask the user for a word
	//if the word is in the dictionary display the word with its fields
	//if the word is not in the dictionary tell the user the word is not found
	private void lookup() {
    	System.out.print("Enter word: ");
    	String input = getUserInput();
    	DictionaryWord word = editor.find(input);
    	if (word != null) {
			System.out.println(word);
		} else {
    		System.out.println("Not found.");
		}
	}

	//Ask the user for a word. Check the list to see if the word is already in the list. If not ask the user for a definition and a ussage example.
	//If the word is in the list tell the user their word is already in the dictionary and end the method.
	private void addWord() {
		System.out.println("Sorry I don't know how to do that yet.");
	}

	//Ask the user for a word. Check the list to see if the word is already in the list.
	//If the word NOT in the list tell the user cannot edit a word that isn't in the dictionary
	//If the word is in the list ask the user if they want to edit the definition (y/n)
	//if yes ask for new definition and save it in the definition field of the word (replace what is already there)
	//ask user if they want to edit the usage example, if yes ask for new usage example and save it in the usageExample field of the word
	//Show the user the fields of the edited word before ending the method
	private void editWord() {
		System.out.println("Sorry I don't know how to do that yet.");
	}
	//ask the user for a word
	//if the word is not in the list tell the user cannot remove a word that isn't in the dictionary
	//If the word is in the list delete the word from the list and tell the user it has been deleted
	private void removeWord() {
		System.out.println("Sorry I don't know how to do that yet.");
	}

	//ask the user if they want to save before exiting.
	//if yes save the current list to the text file, if no do not save (text file unchanged)
	//print a goodbye message, close the Scanner, quit the loop
	private void quit() {
       System.out.println("Sorry I don't know how to do that yet.");
    }

    private void save() {
        DictionaryFileManager.writeToFile(filename, words);
    }

	private String getUserInput() {
		return scanner.nextLine();
	}

	private void invalidInput(String input) {
    	System.out.println("I don't know what to do with '" + input + "'.");
	}

	private boolean isValidMenuChoice(String s) {
		try {
			int i = Integer.parseInt(s);
			if (i > 0 && i < 7) {
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
