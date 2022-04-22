package FabricStaff;
import Figures.Figure;
import Figures.Point;
import java.util.ArrayList;

public interface IFigureCreator {
    public Figure create (ArrayList<Point> points);
}
