package Figures;

import java.io.Serializable;

public class Point implements Serializable {
    protected double x;
    protected double y;

//геттеры
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String getFullPoints() {
        return "("+this.getX() + ";" + this.getY() + ")";
    }
//конструктор
    public Point(double x, double y){
            this.x=x;
            this.y=y;
    }
    public String toString(){
        return  "Point: {" + this.getX() + "; " + this.getY() + "}";
    }
}
