package io.cdap.wrangler.api.parser;

public class ByteSize extends Token {
    private final long bytes;

    public ByteSize(String value) {
        super(value);
        this.bytes = parseBytes(value);
    }

    private long parseBytes(String input) {
        input = input.trim().toUpperCase();

        double number = Double.parseDouble(input.replaceAll("[^\\d.]", ""));
        if (input.endsWith("KB")) return (long) (number * 1024);
        if (input.endsWith("MB")) return (long) (number * 1024 * 1024);
        if (input.endsWith("GB")) return (long) (number * 1024 * 1024 * 1024);
        if (input.endsWith("TB")) return (long) (number * 1024L * 1024 * 1024 * 1024);
        if (input.endsWith("B")) return (long) number;

        throw new IllegalArgumentException("Unknown byte size unit in: " + input);
    }

    public long getBytes() {
        return bytes;
    }
}
