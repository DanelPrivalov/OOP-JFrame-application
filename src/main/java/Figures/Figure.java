package Figures;
import FabricStaff.FigureCreator;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

@JsonAutoDetect
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Triangle.class, name = "Triangle"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "Rectangle"),
        @JsonSubTypes.Type(value = NAngle.class, name = "NAngle"),
        @JsonSubTypes.Type(value = Circle.class, name = "Circle")})

@JsonIgnoreProperties({"perimetr", "getFullPoints", "area"})

public abstract class Figure implements Serializable {

    public ArrayList<Point> points;
    public Point center;
    public double perimetr;
    public double area;
    public Figure (ArrayList<Point> points){

        this.points = points;
        setArea();
        setPerimetr();
        setCenter();
    }

    public void setCenter(){
        double minX = this.points.get(0).getX();
        double maxX = this.points.get(0).getX();
        double minY = this.points.get(0).getY();
        double maxY = this.points.get(0).getY();
        for (var point: this.points){
            if (point.getX()>maxX){
                maxX = point.getX();
            }else if (point.getX() < minX){
                minX = point.getX();
            }
            if (point.getY()>maxY){
                maxY = point.getY();
            }else if (point.getY() < minY){
                minY = point.getY();
            }
        }
        this.center = new Point(minX+(maxX-minX)/2, minY+(maxY-minY)/2);
    }

    public void setArea() {
        double temp = 0;
        for(int i=0; i < this.points.size()-1; i++){
            temp += Math.abs(this.points.get(i).getX()*this.points.get(i+1).getY()-this.points.get(i+1).getX()*this.points.get(i).getY());
        }
        double area = (temp + Math.abs(this.points.get(this.points.size() - 1).getX() * this.points.get(0).getY() - this.points.get(0).getX() * this.points.get(this.points.size() - 1).getY()))/2;
        this.area = area;
    }

    public void setPerimetr(){
        double perimetr = 0;
        for (int i = 1; i < this.points.size(); i++){
            perimetr += Math.sqrt(Math.pow((this.points.get(i).getX()-this.points.get(i-1).getX()),2)+Math.pow((this.points.get(i).getY()-this.points.get(i-1).getY()),2));
        }
        perimetr += Math.sqrt(Math.pow(this.points.get(this.points.size()-1).getX()-this.points.get(0).getX(),2)+Math.pow(this.points.get(this.points.size()-1).getY()-this.points.get(0).getY(),2));
        this.perimetr = perimetr;
    }


    public double getPerimetr() {
        return this.perimetr;
    }

    public double getArea(){
        return this.area;
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }

    public Point getCenter() {
        return center;
    }

    public void move (double a, double b){
        ArrayList<Point> newPoints = new ArrayList<>();
        for (int i=0; i < this.points.size(); i++){
          newPoints.add(new Point(this.points.get(i).getX() + a, this.points.get(i).getY() + b));
        }
        this.points = newPoints;
        setCenter();
    }

    public void rotate (double a) {
        ArrayList<Point> newPoints = new ArrayList<>();
        double side = 0;
        a = Math.toRadians(a);
        Point center=getCenter();
        for (int i = 0; i < points.size(); i++) {

            double x= ((points.get(i).getX()-center.getX())*Math.cos(a)-(points.get(i).getY()-center.getY())*Math.sin(a));
            double y= ((points.get(i).getX()-center.getX())*Math.cos(a)+(points.get(i).getY()-center.getY())*Math.sin(a));
            points.set(i,new Point(x+center.getX(),y+center.getY()));
        }
//        for (var point: this.points) {
//            double x1 = this.center.getX();
//            double y1 = this.center.getY();
//            double x2 = point.getX();
//            double y2 = point.getY();
//            double lenght = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
//            newPoints.add(new Point(x1+(x2-x1)*Math.cos(a)-(y2-y1)*Math.sin(a),
//                    y1+(x2-x1)*Math.sin(a)+(y2-y1)*Math.cos(a)));
//        }
        this.points = newPoints;
    }

    public void scale (double a){
        ArrayList<Point> newPoints = new ArrayList<>();
        for (var point: this.points){
            double Xnew = this.center.getX()+a*(point.getX()-this.center.getX());
            double Ynew = this.center.getY()+a*(point.getY()-this.center.getY());
            newPoints.add(new Point(Xnew, Ynew));
        }
        this.points = newPoints;
        setCenter();
        setArea();
        setPerimetr();
    }

    public String toString() {
        String className = getClass().getSimpleName();
        String result = "My name is " + className + ", my points: ";
        for (int i=0; i < this.points.size(); i++){
            result += (i+1) + "st point x,y = (" + this.points.get(i).getX() + ";" + this.points.get(i).getY() + "), ";
        }
        return result;
    }

    public boolean containPoint (int x, int y, int multiplierX, int multiplierY){
        boolean flag = false;
        for(int i =0; i<this.getPoints().size(); i++){
            int j = i==this.getPoints().size() - 1? 0: i+1;
            double x1 = this.getPoints().get(i).getX()*multiplierX;
            double x2 = this.getPoints().get(j).getX()*multiplierX;
            double y1 = this.getPoints().get(i).getY()*multiplierY;
            double y2 = this.getPoints().get(j).getY()*multiplierY;

            if(x2-x1!=0){
                double a = (y2-y1)/(x2-x1);
                double b = y1 - a * x1;
                if ((Math.abs(y - (int)(a*x+b))<=2)) flag = true;
            }
            else{
                if ((Math.abs(x-x1)<=2)&&(y>=Math.min(y1, y2)&& y<=Math.max(y1, y2))) flag = true;
            }
        }
        return flag;
    }

}
