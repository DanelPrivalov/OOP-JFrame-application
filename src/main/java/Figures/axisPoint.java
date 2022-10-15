package Figures;

import java.util.ArrayList;

public class axisPoint extends Point{
    private String axis;

    public axisPoint (double x, double y){
        super(x, y);
        if(x==0 && y==0)
            this.axis = "I'm a center";
        else if (x==0)
            this.axis="I'm y point";
        else if (y==0)
            this.axis="I'm x point";
        else
            this.axis="???";
    }

    public String getAxis(){
        return axis;
    }

    public String toString(){
        return this.axis;
    }

}
