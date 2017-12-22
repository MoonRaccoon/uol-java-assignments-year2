import java.util.List;

public class ThesaurusWord extends Word {
    private List<String> synonyms;
    private List<String> antonyms;

    public ThesaurusWord(String word, List<String> synonyms, List<String> antonyms) {
        super(word);
        this.synonyms = synonyms;
        this.antonyms = antonyms;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<String> antonyms) {
        this.antonyms = antonyms;
    }

    public String toString() {
        String string = super.toString();
        return String.format("%s%nSynonyms: %s%nAntonyms: %s", string, formatList(synonyms), formatList(antonyms));
    }

    private static String formatList(List<String> list) {
        if (list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            sb.append(", ");
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
