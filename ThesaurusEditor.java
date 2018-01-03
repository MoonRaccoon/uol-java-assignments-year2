/*
 * Name: Shamoun Syed
 * Student Number: 140313701
*/

import java.util.List;

public class ThesaurusEditor extends Thesaurus {


    public ThesaurusEditor(List<ThesaurusWord> words) {
        super(words);
    }

    public void add(ThesaurusWord word) {
	    words.add(word);
	}

	public void delete(ThesaurusWord word) {
	    words.remove(word);
    }

    public void setSynonyms(String word, List<String> synonyms) {
        ThesaurusWord thesaurusWord = find(word);
        if (thesaurusWord != null) {
            thesaurusWord.setSynonyms(synonyms);
        }
    }

    public void setAntonyms(String word, List<String> antonyms) {
        ThesaurusWord thesaurusWord = find(word);
        if (thesaurusWord != null) {
            thesaurusWord.setAntonyms(antonyms);
        }
    }

    public void removeWord(String word) {
        ThesaurusWord thesaurusWord = find(word);
        if (thesaurusWord != null) {
            delete(thesaurusWord);
        }
    }

    public void setWord(String word, List<String> synonyms, List<String> antonyms) {
        ThesaurusWord thesaurusWord = find(word);

        if (thesaurusWord != null) { //update existing word
            thesaurusWord.setSynonyms(synonyms);
            thesaurusWord.setAntonyms(antonyms);
        } else { //word isn't already in the thesaurus
            add(new ThesaurusWord(word, synonyms, antonyms));
        }
    }
}

