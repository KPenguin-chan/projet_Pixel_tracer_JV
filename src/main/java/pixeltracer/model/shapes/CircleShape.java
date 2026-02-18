package pixeltracer.model.shapes;

import pixeltracer.core.IdGenerator;
import pixeltracer.model.Pixel;
import pixeltracer.model.Shape;
import pixeltracer.model.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class CircleShape extends Shape {
    private final int cx, cy, r;

    public CircleShape(int cx, int cy, int r) {
        super(IdGenerator.nextId(), ShapeType.CIRCLE);
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }

    @Override
    public List<Pixel> toPixels() {
        List<Pixel> pixels = new ArrayList<>();

        int x = 0;
        int y = r;
        int d = 1 - r;

        while (x <= y) {
            addCirclePoints(pixels, x, y);

            if (d < 0) {
                d += 2 * x + 3;
            } else {
                d += 2 * (x - y) + 5;
                y--;
            }
            x++;
        }

        return pixels;
    }

    private void addCirclePoints(List<Pixel> pixels, int x, int y) {
        pixels.add(new Pixel(cx + x, cy + y));
        pixels.add(new Pixel(cx - x, cy + y));
        pixels.add(new Pixel(cx + x, cy - y));
        pixels.add(new Pixel(cx - x, cy - y));
        pixels.add(new Pixel(cx + y, cy + x));
        pixels.add(new Pixel(cx - y, cy + x));
        pixels.add(new Pixel(cx + y, cy - x));
        pixels.add(new Pixel(cx - y, cy - x));
    }

    @Override
    public String info() {
        return "center=(" + cx + "," + cy + "), r=" + r;
    }
}
