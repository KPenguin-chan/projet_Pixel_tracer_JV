package pixeltracer.model.shapes;

import pixeltracer.core.IdGenerator;
import pixeltracer.model.Pixel;
import pixeltracer.model.Shape;
import pixeltracer.model.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class RectangleShape extends Shape {
    private final int x, y, w, h;

    public RectangleShape(int x, int y, int w, int h) {
        super(IdGenerator.nextId(), ShapeType.RECTANGLE);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public List<Pixel> toPixels() {
        List<Pixel> px = new ArrayList<>();

        int x2 = x + h;
        int y2 = y + w;

        px.addAll(new LineShape(x, y, x2, y).toPixels());
        px.addAll(new LineShape(x2, y, x2, y2).toPixels());
        px.addAll(new LineShape(x2, y2, x, y2).toPixels());
        px.addAll(new LineShape(x, y2, x, y).toPixels());

        return px;
    }

    @Override
    public String info() {
        return "pos=(" + x + "," + y + "), width=" + w + ", height=" + h;
    }
}
