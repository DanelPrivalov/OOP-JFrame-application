package Figures;
import java.util.ArrayList;
import java.util.Arrays;

public class Circle extends Figure {
    double radius = getRadius();
    public Point center;
    public Circle(ArrayList<Point> points) {
        super(points);
    }

    @Override
    public void setCenter() {
        this.center = this.points.get(0);
    }

    public void setPerimetr() {
        this.perimetr = 2*Math.PI*getRadius();
    }

    public void setArea() {
        this.area = Math.PI * Math.pow(this.getRadius(),2);
    }

    @Override
    public void rotate(double a) {
    }

    public void scale (double a){
        double Xnew = this.center.getX()+a*(points.get(1).getX()-this.center.getX());
        double Ynew = this.center.getY()+a*(points.get(1).getY()-this.center.getY());
        this.points = new ArrayList<>(Arrays.asList(new Point(this.center.getX(), this.center.getY()), new Point(Xnew, Ynew)));
        setCenter();
        setArea();
        setPerimetr();
    }

    public double getRadius() {
        Point a = this.points.get(0);
        Point b = this.points.get(1);
        double radius = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
        return radius;
    }

    public Point getCenter() {
        return this.center;
    }

    @Override
    public boolean containPoint(int x, int y, int multiplierX, int multiplierY) {
        this.getCenter();
        int multiplier = Math.min(multiplierX, multiplierY);
        double distance = Math.sqrt(Math.pow(x-this.getCenter().getX()*multiplier, 2)
                + Math.pow(y - this.getCenter().getY()*multiplier, 2));
        return (Math.abs(radius*multiplier-distance)<2);

    }
}