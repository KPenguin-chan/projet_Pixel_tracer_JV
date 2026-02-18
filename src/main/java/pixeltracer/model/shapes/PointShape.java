package pixeltracer.model.shapes;

import pixeltracer.core.IdGenerator;
import pixeltracer.model.Pixel;
import pixeltracer.model.Shape;
import pixeltracer.model.ShapeType;

import java.util.List;

public class PointShape extends Shape {
    private final int x, y;

    public PointShape(int x, int y) {
        super(IdGenerator.nextId(), ShapeType.POINT);
        this.x = x;
        this.y = y;
    }

    @Override
    public List<Pixel> toPixels() {
        return List.of(new Pixel(x, y));
    }

    @Override
    public String info() {
        return "(" + x + "," + y + ")";
    }
}
