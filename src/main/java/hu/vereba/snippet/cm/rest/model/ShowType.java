package hu.vereba.snippet.cm.rest.model;

public enum ShowType {
    MOVIE("MOVIE"), SERIES("SERIES");
    private String value;

    public String getValue() {
        return value;
    }

    ShowType(String value) {
        this.value = value;
    }
}