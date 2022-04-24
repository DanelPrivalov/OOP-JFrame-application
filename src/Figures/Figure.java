package Figures;
import FabricStaff.FigureCreator;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class Figure implements Serializable {

    public ArrayList<Point> points;
    public Point center = getCenter(this.points);
    public Figure (ArrayList<Point> points){
        this.points = points;
    }


    public Point getCenter(ArrayList<Point> points){
        return new Point(5,5);
    }

    public Figure move (int a, int b){
//        for (var points: this.points){
//            a = this.points;
//        }
        ArrayList<Point> newPoints = null;
        for (int i=0; i < this.points.size(); i++){
          newPoints.add(new Point(this.points.get(i).getX() + a, this.points.get(i).getY() + b));
        }
        return new FigureCreator().create(newPoints);
    }

    public Figure rotate (int a){
        ArrayList<Point> newPoints = null;
        for (int i=0; i < this.points.size(); i++){
            newPoints.add(new Point(center.getX()+(this.points.get(i).getX() - center.getX())*Math.cos(a) - (this.points.get(i).getY() - center.getY())*Math.sin(a),
                    center.getY()+(this.points.get(i).getX() - center.getX())*Math.sin(a) - (this.points.get(i).getY() - center.getY())*Math.cos(a)));
        }
        return new FigureCreator().create(newPoints);
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
//        for(var p:this.getPoints()){
//            temp+=p.getX();
//        }
        double area = (temp + Math.abs(this.points.get(this.points.size() - 1).getX() * this.points.get(0).getY() - this.points.get(0).getX() * this.points.get(this.points.size() - 1).getY()))/2;
        System.out.println("Area of " + getClass().getSimpleName() + " = " + area);  //????
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }
}