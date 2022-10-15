package FabricStaff;
import Figures.*;

import java.util.ArrayList;

public class FigureCreator implements IFigureCreator{

    public Figure create(ArrayList<Point>points){
        int nPoints = points.size();

        if(nPoints==2) {
            return new Circle(points);
      }
        else if (nPoints==3) {
            return new Triangle(points);
      }
        else if (nPoints==4){
            return new Rectangle(points);
        }
      else if (nPoints >= 2) {
          return new NAngle(points);
      }
      else {
          return null;
        }
    }
}
