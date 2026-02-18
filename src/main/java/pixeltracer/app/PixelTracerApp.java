package pixeltracer.app;

import pixeltracer.command.CommandExecutor;
import pixeltracer.command.CommandParser;
import pixeltracer.core.ErrorCode;
import pixeltracer.model.Area;
import pixeltracer.model.Layer;
import pixeltracer.model.Shape;
import pixeltracer.render.Renderer;
import pixeltracer.render.Screen;

import java.util.ArrayList;
import java.util.List;

public class PixelTracerApp {

    private final List<Area> areas = new ArrayList<>();
    private Area currentArea;
    private Layer currentLayer;
    private Shape currentShape;

    private final Renderer renderer = new Renderer();
    private final CommandParser parser = new CommandParser();
    private final CommandExecutor executor = new CommandExecutor();

    public void init() {
        Area area = Area.createDefault("Area1", 80, 40);
        areas.add(area);
        currentArea = area;

        Layer layer = Layer.createDefault("Layer 1");
        currentArea.getLayers().add(layer);
        currentLayer = layer;

        currentShape = null;
    }

    public void destroy() {
        areas.clear();
        currentArea = null;
        currentLayer = null;
        currentShape = null;
    }

    public void loop() {
        Screen.clear();
        renderer.plot(currentArea);
        renderer.draw(currentArea);

        while (true) {
            var cmd = parser.readCommand();

            ErrorCode code = executor.execute(cmd, this);

            System.out.println(code.message());

            if (code == ErrorCode.EXIT) break;

            if (code == ErrorCode.CLEAR) {
                Screen.clear();
                continue;
            }

            if (code == ErrorCode.HELP || code == ErrorCode.DONE) {
                continue;
            }

            if (code == ErrorCode.OK || code == ErrorCode.PLOT) {
                Screen.clear();
                renderer.plot(currentArea);
                renderer.draw(currentArea);
            }
        }
    }

    public List<Area> getAreas() {
        return areas;
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public void setCurrentArea(Area currentArea) {
        this.currentArea = currentArea;
    }

    public Layer getCurrentLayer() {
        return currentLayer;
    }

    public void setCurrentLayer(Layer currentLayer) {
        this.currentLayer = currentLayer;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(Shape currentShape) {
        this.currentShape = currentShape;
    }
}
