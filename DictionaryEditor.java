import java.util.List;

public class DictionaryEditor extends Dictionary {

    public void add(DictionaryWord word) {
	    words.add(word);
	}

	public void delete(DictionaryWord word) {
	    words.remove(word);
    }

    public void setUsageExample(String word, String usageExample) {
        DictionaryWord dictionaryWord = find(word);
        if (dictionaryWord != null) {
            dictionaryWord.setUsageExample(usageExample);
        }
    }

    public void setDefinition(String word, String definition) {
        DictionaryWord dictionaryWord = find(word);
        if (dictionaryWord != null) {
            dictionaryWord.setDefinition(definition);
        }
    }

    public void removeWord(String word) {
        DictionaryWord dictionaryWord = find(word);
        if (dictionaryWord != null) {
            delete(dictionaryWord);
        }
    }

    public void setWord(String word, String definition, String usageExample) {
        DictionaryWord dictionaryWord = find(word);

        if (dictionaryWord != null) { //update existing word
            dictionaryWord.setDefinition(definition);
            dictionaryWord.setUsageExample(usageExample);
        } else { //word isn't already in the dictionary
            add(new DictionaryWord(word, definition, usageExample));
        }
    }
}
