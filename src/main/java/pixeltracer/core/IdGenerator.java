package pixeltracer.core;

public class IdGenerator {
    private static long globalId = 0;

    public static long nextId() {
        globalId++;
        return globalId;
    }

    public static void setId(long id) {
        globalId = id;
    }
}
