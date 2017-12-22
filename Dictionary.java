import java.util.List;

public class Dictionary {
    protected List<DictionaryWord> words;

    public Dictionary(List<DictionaryWord> words) {
        this.words = words;
    }

    public DictionaryWord find(String word) {
        for (int i = 0; i < words.size(); i++) {
            if(word.toLowerCase().equals(words.get(i).getWord().toLowerCase())) {
                return words.get(i);
            }
        }
        //word not found
        return null;
    }
}

