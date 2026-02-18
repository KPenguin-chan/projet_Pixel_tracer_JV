package pixeltracer.command;

import pixeltracer.core.TextUtil;

import java.util.Scanner;

public class CommandParser {

    private final Scanner scanner = new Scanner(System.in);

    public Command readCommand() {
        System.out.print("~> ");
        String line = scanner.nextLine();
        line = TextUtil.cleanLine(line);

        Command cmd = new Command();
        if (line.isBlank()) return cmd;

        String[] tokens = line.split("\\s+");
        for (String t : tokens) {
            if (TextUtil.isWord(t)) {
                cmd.words().add(t);
            } else if (TextUtil.isInt(t)) {
                cmd.ints().add(Integer.parseInt(t));
            } else {
                cmd.words().add("error");
                cmd.words().add("line");
                break;
            }
        }
        return cmd;
    }
}
