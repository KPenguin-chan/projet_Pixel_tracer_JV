package pixeltracer.model.shapes;

import pixeltracer.core.IdGenerator;
import pixeltracer.model.Pixel;
import pixeltracer.model.Shape;
import pixeltracer.model.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class SquareShape extends Shape {
    private final int x, y, length;

    public SquareShape(int x, int y, int length) {
        super(IdGenerator.nextId(), ShapeType.SQUARE);
        this.x = x;
        this.y = y;
        this.length = length;
    }

    @Override
    public List<Pixel> toPixels() {
        List<Pixel> px = new ArrayList<>();

        int x2 = x + length -1;
        int y2 = y + length -1;

        px.addAll(new LineShape(x, y, x2, y).toPixels());
        px.addAll(new LineShape(x2, y, x2, y2).toPixels());
        px.addAll(new LineShape(x2, y2, x, y2).toPixels());
        px.addAll(new LineShape(x, y2, x, y).toPixels());

        return px;
    }

    @Override
    public String info() {
        return "pos=(" + x + "," + y + "), length=" + length;
    }
}
