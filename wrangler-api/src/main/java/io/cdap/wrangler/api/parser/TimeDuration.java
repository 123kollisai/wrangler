package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {
    private final long milliseconds;

    public TimeDuration(String value) {
        super(value);
        this.milliseconds = parseDuration(value);
    }

    private long parseDuration(String input) {
        input = input.trim().toLowerCase();

        double number = Double.parseDouble(input.replaceAll("[^\\d.]", ""));
        if (input.endsWith("ms")) return (long) number;
        if (input.endsWith("s") || input.endsWith("sec") || input.endsWith("seconds")) return (long) (number * 1000);
        if (input.endsWith("m") || input.endsWith("min") || input.endsWith("minutes")) return (long) (number * 60 * 1000);
        if (input.endsWith("h") || input.endsWith("hr") || input.endsWith("hours")) return (long) (number * 60 * 60 * 1000);

        throw new IllegalArgumentException("Unknown time duration unit in: " + input);
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}
