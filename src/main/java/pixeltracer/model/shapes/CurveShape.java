package pixeltracer.model.shapes;

import pixeltracer.core.IdGenerator;
import pixeltracer.model.Pixel;
import pixeltracer.model.Shape;
import pixeltracer.model.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class CurveShape extends Shape {

    private final int x1,y1,x2,y2,x3,y3,x4,y4;

    public CurveShape(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4) {
        super(IdGenerator.nextId(), ShapeType.CURVE);
        this.x1=x1; this.y1=y1;
        this.x2=x2; this.y2=y2;
        this.x3=x3; this.y3=y3;
        this.x4=x4; this.y4=y4;
    }

    @Override
    public List<Pixel> toPixels() {
        List<Pixel> px = new ArrayList<>();

        int steps = 200;
        for (int i = 0; i <= steps; i++) {
            double t = i / (double) steps;

            double xt = bezier(t, x1, x2, x3, x4);
            double yt = bezier(t, y1, y2, y3, y4);

            px.add(new Pixel((int)Math.round(xt), (int)Math.round(yt)));
        }
        return px;
    }

    private double bezier(double t, double p0, double p1, double p2, double p3) {
        double u = 1 - t;
        return (u*u*u*p0) + (3*u*u*t*p1) + (3*u*t*t*p2) + (t*t*t*p3);
    }

    @Override
    public String info() {
        return "bezier ("+x1+","+y1+") ("+x2+","+y2+") ("+x3+","+y3+") ("+x4+","+y4+")";
    }
}
