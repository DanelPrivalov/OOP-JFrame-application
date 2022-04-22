package Figures;
import java.util.ArrayList;


public abstract class Figure {

    public ArrayList<Point> points;
    public Point center;
    public Figure (ArrayList<Point> points){
        this.points = points;
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }

    public void getCenter(){
        this.center=new Point(0,0);
    }

    public String toString() {
        String className = getClass().getSimpleName();
        String result = "My name is " + className + ", my points: ";
        for (int i=0; i < this.points.size(); i++){
            result += (i+1) + "st point x,y = (" + this.points.get(i).getX() + ";" + this.points.get(i).getY() + "), ";
        }
        return result;
    }

    public void getPerimetr() {
        double perimetr = 0;
        for (int i = 1; i < this.points.size(); i++){
            perimetr += Math.sqrt(Math.pow((this.points.get(i).getX()-this.points.get(i-1).getX()),2)+Math.pow((this.points.get(i).getY()-this.points.get(i-1).getY()),2));
        }
        perimetr += Math.sqrt(Math.pow(this.points.get(this.points.size()-1).getX()-this.points.get(0).getX(),2)+Math.pow(this.points.get(this.points.size()-1).getY()-this.points.get(0).getY(),2));
        System.out.println("Perimetr of " + getClass().getSimpleName() + " = " + perimetr);
    }

    public void getArea() {
        double temp = 0;
        for(int i=0; i < this.points.size()-1; i++){
            temp += Math.abs(this.points.get(i).getX()*this.points.get(i+1).getY()-this.points.get(i+1).getX()*this.points.get(i).getY());
        }
        double area = (temp + Math.abs(this.points.get(this.points.size() - 1).getX() * this.points.get(0).getY() - this.points.get(0).getX() * this.points.get(this.points.size() - 1).getY()))/2;
        System.out.println("Area of " + getClass().getSimpleName() + " = " + area);  //????
    }
}