package pixeltracer.model;

import pixeltracer.core.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private final long id;
    private final String name;
    private boolean visible = true;
    private final List<Shape> shapes = new ArrayList<>();

    public Layer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Layer createDefault(String name) {
        return new Layer(IdGenerator.nextId(), name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

}
