package pixeltracer.model;

import pixeltracer.core.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Area {
    private final long id;
    private final String name;
    private final int width;
    private final int height;

    private final List<Layer> layers = new ArrayList<>();

    private char emptyChar = '.';
    private char fullChar = '@';

    private final char[][] buffer;

    public Area(long id, String name, int width, int height) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.buffer = new char[height][width];
        clear();
    }

    public static Area createDefault(String name, int width, int height) {
        return new Area(IdGenerator.nextId(), name, width, height);
    }

    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                buffer[i][j] = emptyChar;
            }
        }
    }

    public void setPixel(int x, int y) {
        if (x < 0 || y < 0) return;
        if (x >= height || y >= width) return;
        buffer[x][y] = fullChar;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getBuffer() {
        return buffer;
    }

    public char getEmptyChar() {
        return emptyChar;
    }

    public void setEmptyChar(char emptyChar) {
        this.emptyChar = emptyChar;
    }

    public char getFullChar() {
        return fullChar;
    }

    public void setFullChar(char fullChar) {
        this.fullChar = fullChar;
    }
}
