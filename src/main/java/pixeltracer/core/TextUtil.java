package pixeltracer.core;

public class TextUtil {

    private TextUtil() {}

    public static String cleanLine(String s) {
        if (s == null) return "";
        s = s.trim().toLowerCase();

        int idx = s.indexOf('#');
        if (idx >= 0) s = s.substring(0, idx);

        return s.trim();
    }

    public static boolean isInt(String token) {
        if (token == null || token.isEmpty()) return false;
        for (char c : token.toCharArray()) {
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    public static boolean isWord(String token) {
        if (token == null || token.isEmpty()) return false;
        for (char c : token.toCharArray()) {
            if (c < 'a' || c > 'z') return false;
        }
        return true;
    }
}
