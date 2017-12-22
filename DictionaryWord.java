public class DictionaryWord extends Word {
    private String definition;
    private String usageExample;

    public DictionaryWord(String word, String definition, String usageExample) {
        super(word);
        this.definition = definition;
        this.usageExample = usageExample;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getUsageExample() {
        return usageExample;
    }

    public void setUsageExample(String usageExample) {
        this.usageExample = usageExample;
    }

    public String toString() {
        String string = super.toString();
        return String.format("%s%nDefinition: %s%nUsage Example: %s", string, definition, usageExample);
    }
}
