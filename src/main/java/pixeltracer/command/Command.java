package pixeltracer.command;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private final List<String> words = new ArrayList<>();
    private final List<Integer> ints = new ArrayList<>();

    public List<String> words() { return words; }
    public List<Integer> ints() { return ints; }

    public String cmdName() {
        return words.isEmpty() ? "" : words.get(0);
    }
}
