/*
 * Name: Shamoun Syed
 * Student Number: 140313701
*/

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
        System.out.print("Enter the word you'd like to add: ");
    	String input = getUserInput();
    	DictionaryWord word = editor.find(input);
    	if (word == null) {
			System.out.print("Enter a definition for the word: ");
            String definition = getUserInput();

            System.out.print("Enter a usage example for the word: ");
            String usageExample = getUserInput();

            editor.setWord(input, definition, usageExample);
		}
        else {
    		System.out.println("That word is already in the dictionary.");
		}

	}

	//Ask the user for a word. Check the list to see if the word is already in the list.
	//If the word NOT in the list tell the user cannot edit a word that isn't in the dictionary
	//If the word is in the list ask the user if they want to edit the definition (y/n)
	//if yes ask for new definition and save it in the definition field of the word (replace what is already there)
	//ask user if they want to edit the usage example, if yes ask for new usage example and save it in the usageExample field of the word
	//Show the user the fields of the edited word before ending the method
	private void editWord() {
        System.out.print("Enter the word you'd like to edit: ");
        String input = getUserInput();
        DictionaryWord word = editor.find(input);
        if (word == null) {
            System.out.println("Word not found - unable to edit words not already present in the dictionary.");
        }
        else {
            System.out.print("Would you like to edit the definition of \"" + input + "\"? (y/n): ");
            String answer = getUserInput();

            if (answer.equals("y")) {
                System.out.print("Enter a new definition for the word: ");
                String definition = getUserInput();
                editor.setDefinition(input, definition);
            }

            System.out.print("Would you like to edit the usage example of \"" + input + "\"? (y/n): ");
            answer = getUserInput();

            if (answer.equals("y")) {
                System.out.print("Enter a new usage example for the word: ");
                String usageExample = getUserInput();
                editor.setUsageExample(input, usageExample);
            }

            System.out.println(word.toString());
        }
	}
	//ask the user for a word
	//if the word is not in the list tell the user cannot remove a word that isn't in the dictionary
	//If the word is in the list delete the word from the list and tell the user it has been deleted
	private void removeWord() {
		System.out.print("Enter the word you'd like to remove: ");
        String input = getUserInput();
        DictionaryWord word = editor.find(input);
        if (word == null) {
            System.out.println("Word not found - unable to remove words not already present in the dictionary.");
        }
        else {
            editor.delete(word);
            System.out.println("\"" + input + "\" has been deleted.");
        }

	}

	//ask the user if they want to save before exiting.
	//if yes save the current list to the text file, if no do not save (text file unchanged)
	//print a goodbye message, close the Scanner, quit the loop
	private void quit() {
       System.out.print("Would you like to save this dictionary before exiting? (y/n): ");
       String input = getUserInput();

       if (input.equals("y")) {
           this.save();
           scanner.close();
           this.finished = true;
           System.out.println("Successfully saved and exited Dictionary.");
       }
       else if (input.equals("n")) {
           scanner.close();
           this.finished = true;
           System.out.println("Successfully exited Dictionary.");
       }

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
