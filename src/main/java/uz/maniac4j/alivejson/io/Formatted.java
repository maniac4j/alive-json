package uz.maniac4j.alivejson.io;

import uz.maniac4j.alivejson.Json;

/**
 * Formatted JSON decorator.
 *
 * @since 1.0
 */
public final class Formatted implements Json {

    /**
     * Original JSON.
     */
    private final Json origin;

    /**
     * Constructor.
     *
     * @param json Original JSON
     */
    public Formatted(final Json json) {
        this.origin = json;
    }

    @Override
    public String text() {
        final String raw = this.origin.text();
        final StringBuilder builder = new StringBuilder();
        int indent = 0;
        boolean inString = false;
        for (int i = 0; i < raw.length(); i++) {
            final char c = raw.charAt(i);
            if (c == '\"' && (i == 0 || raw.charAt(i - 1) != '\\')) {
                inString = !inString;
            }
            if (inString) {
                builder.append(c);
            } else {
                if (c == '{' || c == '[') {
                    builder.append(c).append("\n");
                    indent++;
                    appendIndent(builder, indent);
                } else if (c == '}' || c == ']') {
                    builder.append("\n");
                    indent--;
                    appendIndent(builder, indent);
                    builder.append(c);
                } else if (c == ',') {
                    builder.append(c).append("\n");
                    appendIndent(builder, indent);
                } else if (c == ':') {
                    builder.append(c).append(" ");
                } else {
                    builder.append(c);
                }
            }
        }
        return builder.toString();
    }

    private void appendIndent(final StringBuilder builder, final int indent) {
        for (int i = 0; i < indent; i++) {
            builder.append("  ");
        }
    }

    @Override
    public Json value(final String key) {
        return this.origin.value(key);
    }

    @Override
    public Json value(final int index) {
        return this.origin.value(index);
    }
}
