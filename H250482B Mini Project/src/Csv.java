import java.util.ArrayList;
import java.util.List;

/** Tiny CSV helper: quotes fields that contain commas or quotes. */
final class Csv {
    private Csv() { }

    static String line(String... fields) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) sb.append(',');
            String f = fields[i];
            if (f.contains(",") || f.contains("\"")) f = "\"" + f.replace("\"", "\"\"") + "\"";
            sb.append(f);
        }
        return sb.toString();
    }

    static List<String> parse(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (quoted) {
                if (c == '"' && i + 1 < line.length() && line.charAt(i + 1) == '"') { cur.append('"'); i++; }
                else if (c == '"') quoted = false;
                else cur.append(c);
            } else if (c == '"') quoted = true;
            else if (c == ',') { out.add(cur.toString()); cur.setLength(0); }
            else cur.append(c);
        }
        out.add(cur.toString());
        return out;
    }
}
