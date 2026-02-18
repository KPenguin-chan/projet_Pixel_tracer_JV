package pixeltracer.model.shapes;

import pixeltracer.core.IdGenerator;
import pixeltracer.model.Pixel;
import pixeltracer.model.Shape;
import pixeltracer.model.ShapeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une ligne entre deux points (x1,y1) et (x2,y2).
 * La conversion en pixels utilise l'algorithme de Bresenham.
 */
public class LineShape extends Shape {

    private final int x1, y1;
    private final int x2, y2;


    public LineShape(int x1, int y1, int x2, int y2) {
        super(IdGenerator.nextId(), ShapeType.LINE);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public List<Pixel> toPixels() {
        List<Pixel> pixels = new ArrayList<>();

        int x = x1;
        int y = y1;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        int err = dx - dy;

        while (true) {
            pixels.add(new Pixel(x, y));

            if (x == x2 && y == y2) break;

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }

            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }

        return pixels;
    }

    @Override
    public String info() {
        return "(" + x1 + "," + y1 + ") -> (" + x2 + "," + y2 + ")";
    }
}
