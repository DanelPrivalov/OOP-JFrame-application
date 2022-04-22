package Figures;
import java.util.ArrayList;

public class Circle extends Figure {
    double radius = this.getRadius();

    public Circle(ArrayList<Point> points) {
        super(points);
    }

    public double getRadius() {
        Point a = this.points.get(0);
        Point b = this.points.get(1);
        double radius = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
        return radius;
    }

    public void getPerimetr() {
        double perimetr = 2*Math.PI*getRadius();
        System.out.println("Perimetr of " + getClass().getSimpleName() + " = " + perimetr);
    }

    public void getArea() {
        double area = Math.PI * Math.pow(this.getRadius(),2);
        System.out.println("Area of " + getClass().getSimpleName() + " = " + area);
    }
}