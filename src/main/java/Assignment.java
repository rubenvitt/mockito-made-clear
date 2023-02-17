public class Assignment {
    private String name;
    private String craft;


    public Assignment (final String name, final String craft) {
        this.name = name;
        this.craft = craft;
    }

    public Assignment () {
    }

    public String getName () {
        return name;
    }

    public void setName (final String name) {
        this.name = name;
    }

    public String getCraft () {
        return craft;
    }

    public void setCraft (final String craft) {
        this.craft = craft;
    }
}
