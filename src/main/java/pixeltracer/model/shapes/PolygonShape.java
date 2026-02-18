package pixeltracer.model.shapes;

import pixeltracer.core.IdGenerator;
import pixeltracer.model.Pixel;
import pixeltracer.model.Shape;
import pixeltracer.model.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class PolygonShape extends Shape {

    private final int[] coords; // x1 y1 x2 y2 ...

    public PolygonShape(int[] coords) {
        super(IdGenerator.nextId(), ShapeType.POLYGON);
        this.coords = coords;
    }

    @Override
    public List<Pixel> toPixels() {
        List<Pixel> px = new ArrayList<>();
        int n = coords.length / 2;

        for (int i = 0; i < n; i++) {
            int x1 = coords[(2 * i)];
            int y1 = coords[(2 * i) + 1];

            int j = (i + 1) % n;
            int x2 = coords[(2 * j)];
            int y2 = coords[(2 * j) + 1];

            px.addAll(new LineShape(x1, y1, x2, y2).toPixels());
        }
        return px;
    }

    @Override
    public String info() {
        return "points=" + (coords.length / 2);
    }
}
