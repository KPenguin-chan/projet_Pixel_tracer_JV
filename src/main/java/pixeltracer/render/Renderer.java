package pixeltracer.render;

import pixeltracer.model.Area;
import pixeltracer.model.Layer;
import pixeltracer.model.Shape;

public class Renderer {

    public void plot(Area area) {
        area.clear();

        for (Layer layer : area.getLayers()) {
            if (!layer.isVisible()) continue;

            for (Shape shape : layer.getShapes()) {
                for (var p : shape.toPixels()) {
                    area.setPixel(p.x(), p.y());
                }
            }
        }
    }

    public void draw(Area area) {
        char[][] buf = area.getBuffer();

        for (int i = 0; i < area.getHeight(); i++) {
            for (int j = 0; j < area.getWidth(); j++) {
                System.out.print(buf[i][j]);
            }
            System.out.println();
        }
    }
}
