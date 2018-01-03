/*
 * Name: Shamoun Syed
 * Student Number: 140313701
*/

import java.util.List;

public class Thesaurus {
    protected List<ThesaurusWord> words;

    public Thesaurus(List<ThesaurusWord> words) {
        this.words = words;
    }

    public ThesaurusWord find(String word) {
        for (int i = 0; i < words.size(); i++) {
            if(word.toLowerCase().equals(words.get(i).getWord().toLowerCase())) {
                return words.get(i);
            }
        }
        //word not found
        return null;
    }
}


