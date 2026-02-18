package pixeltracer.command;

import pixeltracer.app.PixelTracerApp;
import pixeltracer.core.ErrorCode;
import pixeltracer.model.Area;
import pixeltracer.model.Layer;
import pixeltracer.model.Shape;
import pixeltracer.model.shapes.*;

public class CommandExecutor {

    public ErrorCode execute(Command cmd, PixelTracerApp app) {

        if (cmd.words().isEmpty()) return ErrorCode.MISSING_COMMAND;

        String name = cmd.cmdName();

        switch (name) {
            case "exit":
                if (!check(cmd, 1, 0)) return ErrorCode.PARAM_ERROR;
                return ErrorCode.EXIT;

            case "clear":
                if (!check(cmd, 1, 0)) return ErrorCode.PARAM_ERROR;
                return ErrorCode.CLEAR;

            case "plot":
                if (!check(cmd, 1, 0)) return ErrorCode.PARAM_ERROR;
                return ErrorCode.PLOT;

            case "help":
                if (!check(cmd, 1, 0)) return ErrorCode.PARAM_ERROR;
                printHelp();
                return ErrorCode.HELP;

            case "point":
                if (!check(cmd, 1, 2)) return ErrorCode.PARAM_ERROR;
                app.getCurrentLayer().getShapes().add(new PointShape(cmd.ints().get(0), cmd.ints().get(1)));
                return ErrorCode.OK;

            case "line":
                if (!check(cmd, 1, 4)) return ErrorCode.PARAM_ERROR;
                app.getCurrentLayer().getShapes().add(new LineShape(
                        cmd.ints().get(0), cmd.ints().get(1),
                        cmd.ints().get(2), cmd.ints().get(3)
                ));
                return ErrorCode.OK;

            case "circle":
                if (!check(cmd, 1, 3)) return ErrorCode.PARAM_ERROR;
                app.getCurrentLayer().getShapes().add(new CircleShape(
                        cmd.ints().get(0), cmd.ints().get(1), cmd.ints().get(2)
                ));
                return ErrorCode.OK;

            case "square":
                if (!check(cmd, 1, 3)) return ErrorCode.PARAM_ERROR;
                app.getCurrentLayer().getShapes().add(new SquareShape(
                        cmd.ints().get(0), cmd.ints().get(1), cmd.ints().get(2)
                ));
                return ErrorCode.OK;

            case "rectangle":
                if (!check(cmd, 1, 4)) return ErrorCode.PARAM_ERROR;
                app.getCurrentLayer().getShapes().add(new RectangleShape(
                        cmd.ints().get(0), cmd.ints().get(1),
                        cmd.ints().get(2), cmd.ints().get(3)
                ));
                return ErrorCode.OK;

            case "polygon":
                if (cmd.words().size() != 1) return ErrorCode.PARAM_ERROR;
                if (cmd.ints().isEmpty() || cmd.ints().size() % 2 != 0) return ErrorCode.PARAM_ERROR;
                int[] coords = new int[cmd.ints().size()];
                for (int i = 0; i < coords.length; i++) coords[i] = cmd.ints().get(i);
                app.getCurrentLayer().getShapes().add(new PolygonShape(coords));
                return ErrorCode.OK;

            case "curve":
                if (!check(cmd, 1, 8)) return ErrorCode.PARAM_ERROR;
                app.getCurrentLayer().getShapes().add(new CurveShape(
                        cmd.ints().get(0), cmd.ints().get(1),
                        cmd.ints().get(2), cmd.ints().get(3),
                        cmd.ints().get(4), cmd.ints().get(5),
                        cmd.ints().get(6), cmd.ints().get(7)
                ));
                return ErrorCode.OK;

            case "list":
                return list(cmd, app);

            case "new":
                return newCmd(cmd, app);

            case "select":
                return select(cmd, app);

            case "delete":
                return delete(cmd, app);

            case "set":
                return set(cmd, app);

            default:
                return ErrorCode.UNKNOWN_COMMAND;
        }
    }

    private boolean check(Command cmd, int nbWords, int nbInts) {
        return cmd.words().size() == nbWords && cmd.ints().size() == nbInts;
    }

    private ErrorCode list(Command cmd, PixelTracerApp app) {
        if (cmd.words().size() != 2 || !cmd.ints().isEmpty()) return ErrorCode.PARAM_ERROR;

        switch (cmd.words().get(1)) {
            case "areas" -> {
                for (Area a : app.getAreas()) {
                    System.out.print(a == app.getCurrentArea() ? " * " : " - ");
                    System.out.printf("%3d %s%n", a.getId(), a.getName());
                }
                return ErrorCode.DONE;
            }
            case "layers" -> {
                for (Layer l : app.getCurrentArea().getLayers()) {
                    System.out.print(l == app.getCurrentLayer() ? " * " : " - ");
                    char vis = l.isVisible() ? 'V' : 'H';
                    System.out.printf("%3d (%c) %s%n", l.getId(), vis, l.getName());
                }
                return ErrorCode.DONE;
            }
            case "shapes" -> {
                for (Shape s : app.getCurrentLayer().getShapes()) {
                    System.out.print(s == app.getCurrentShape() ? " * " : " - ");
                    System.out.printf("%3d : %s %s%n", s.getId(), s.getType(), s.info());
                }
                return ErrorCode.DONE;
            }
            default -> {
                return ErrorCode.PARAM_ERROR;
            }
        }
    }

    private ErrorCode newCmd(Command cmd, PixelTracerApp app) {
        if (cmd.words().size() != 2 || !cmd.ints().isEmpty()) return ErrorCode.PARAM_ERROR;

        if (cmd.words().get(1).equals("area")) {
            Area a = Area.createDefault("area_name", 80, 40);
            app.getAreas().add(a);
            app.setCurrentArea(a);

            Layer l = Layer.createDefault("Layer 1");
            a.getLayers().add(l);
            app.setCurrentLayer(l);

            app.setCurrentShape(null);
            return ErrorCode.DONE;
        }

        if (cmd.words().get(1).equals("layer")) {
            Layer l = Layer.createDefault("layer_name");
            app.getCurrentArea().getLayers().add(l);
            app.setCurrentLayer(l);
            app.setCurrentShape(null);
            return ErrorCode.DONE;
        }

        return ErrorCode.PARAM_ERROR;
    }

    private ErrorCode select(Command cmd, PixelTracerApp app) {
        if (cmd.words().size() != 2 || cmd.ints().size() != 1) return ErrorCode.PARAM_ERROR;
        int id = cmd.ints().get(0);

        if (cmd.words().get(1).equals("area")) {
            for (Area a : app.getAreas()) {
                if (a.getId() == id) {
                    app.setCurrentArea(a);
                    if (!a.getLayers().isEmpty()) {
                        app.setCurrentLayer(a.getLayers().get(a.getLayers().size() - 1));
                    }
                    app.setCurrentShape(null);
                    System.out.printf("%3d %s : selected%n", a.getId(), a.getName());
                    return ErrorCode.DONE;
                }
            }
            return ErrorCode.UNKNOWN_ID;
        }

        if (cmd.words().get(1).equals("layer")) {
            for (Layer l : app.getCurrentArea().getLayers()) {
                if (l.getId() == id) {
                    app.setCurrentLayer(l);
                    app.setCurrentShape(null);
                    return ErrorCode.DONE;
                }
            }
            return ErrorCode.UNKNOWN_ID;
        }

        if (cmd.words().get(1).equals("shape")) {
            for (Shape s : app.getCurrentLayer().getShapes()) {
                if (s.getId() == id) {
                    app.setCurrentShape(s);
                    return ErrorCode.DONE;
                }
            }
            return ErrorCode.UNKNOWN_ID;
        }

        return ErrorCode.PARAM_ERROR;
    }

    private ErrorCode delete(Command cmd, PixelTracerApp app) {
        if (cmd.words().size() != 2 || cmd.ints().size() != 1) return ErrorCode.PARAM_ERROR;
        int id = cmd.ints().get(0);

        if (cmd.words().get(1).equals("shape")) {
            var it = app.getCurrentLayer().getShapes().iterator();
            while (it.hasNext()) {
                Shape s = it.next();
                if (s.getId() == id) {
                    it.remove();
                    app.setCurrentShape(null);
                    return ErrorCode.DONE;
                }
            }
            return ErrorCode.UNKNOWN_ID;
        }
        return ErrorCode.PARAM_ERROR;
    }

    private ErrorCode set(Command cmd, PixelTracerApp app) {
        if (cmd.words().size() != 3 || cmd.ints().size() != 1) return ErrorCode.PARAM_ERROR;
        int value = cmd.ints().get(0);

        if (cmd.words().get(1).equals("char")) {
            if (cmd.words().get(2).equals("border")) {
                app.getCurrentArea().setFullChar((char) value);
                return ErrorCode.OK;
            }
            if (cmd.words().get(2).equals("background")) {
                app.getCurrentArea().setEmptyChar((char) value);
                return ErrorCode.OK;
            }
            return ErrorCode.PARAM_ERROR;
        }

        if (cmd.words().get(1).equals("layer")) {
            if (!cmd.words().get(2).equals("visible") && !cmd.words().get(2).equals("unvisible"))
                return ErrorCode.PARAM_ERROR;

            boolean visible = cmd.words().get(2).equals("visible");
            int id = value;

            for (Layer l : app.getCurrentArea().getLayers()) {
                if (l.getId() == id) {
                    l.setVisible(visible);
                    return ErrorCode.OK;
                }
            }
            return ErrorCode.UNKNOWN_ID;
        }

        return ErrorCode.PARAM_ERROR;
    }

    private void printHelp() {
        System.out.println("\t**************************************************");
        System.out.println("\t****         VECTOR TEXT-BASED EDITOR         ****");
        System.out.println("\t**************************************************");

        System.out.println("\t==== Control ====");
        System.out.println("\tclear : clear screen");
        System.out.println("\texit : exit the program");
        System.out.println("\thelp : print this help");
        System.out.println("\tplot : draw screen");

        System.out.println("\t==== Draw shapes ====");
        System.out.println("\tpoint px py");
        System.out.println("\tline x1 y1 x2 y2");
        System.out.println("\tsquare x y length");
        System.out.println("\trectangle x y width height");
        System.out.println("\tcircle x y r");
        System.out.println("\tpolygon x1 y1 x2 y2 ...");
        System.out.println("\tcurve x1 y1 x2 y2 x3 y3 x4 y4");

        System.out.println("\t==== Draw manager ====");
        System.out.println("\tlist {areas, layers, shapes}");
        System.out.println("\tselect {area, layer, shape} {id}");
        System.out.println("\tdelete {shape} {id}");
        System.out.println("\tnew {area, layer}");

        System.out.println("\t==== Set ====");
        System.out.println("\tset char {border, background} ascii_code");
        System.out.println("\tset layer {visible, unvisible} {id}");
    }
}
