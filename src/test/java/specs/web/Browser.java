package specs.web;

public enum Browser {
    FIREFOX, CHROME;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
