package pixeltracer.core;

public enum ErrorCode {
    OK(""),
    UNKNOWN_COMMAND("commande inconnue"),
    MISSING_COMMAND("commande manquante"),
    PARAM_ERROR("erreur paramètres, consulter la commande help"),
    EXIT("exit"),
    CLEAR("clear"),
    PLOT("plot"),
    HELP("~~~ Help ~~~"),
    DONE("done"),
    UNKNOWN_ID("id inconnu dans la list");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
