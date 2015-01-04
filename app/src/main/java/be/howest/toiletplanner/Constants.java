package be.howest.toiletplanner;

/**
 * Created by Dylan on 3/12/2014.
 */
public enum Constants {
    TAG_NAME("roomname"),
    TAG_LASTUSED("lastaccessed"),
    TAG_STATE("status");

    private final String text;

    /**
     * @param text
     */
    private Constants(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}

