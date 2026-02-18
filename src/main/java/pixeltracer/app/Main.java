package pixeltracer.app;

public class Main {
    public static void main(String[] args) {
        PixelTracerApp app = new PixelTracerApp();
        app.init();
        app.loop();
        app.destroy();
    }
}
