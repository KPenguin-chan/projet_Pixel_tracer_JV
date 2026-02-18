package pixeltracer.model;

import java.util.List;

public abstract class Shape {
    protected final long id;
    protected final ShapeType type;

    protected Shape(long id, ShapeType type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public ShapeType getType() {
        return type;
    }

    public abstract List<Pixel> toPixels();

    public abstract String info();
}
