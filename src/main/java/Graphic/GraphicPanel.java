package Graphic;
import Figures.Circle;
import Figures.Figure;
import Figures.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicPanel extends JPanel {
    private ArrayList<Figure> figures;
    ArrayList<Figures.Point> points;
    public int multiplierX=1;
    public int multiplierY=1;
    private int width;
    private int height;

    public GraphicPanel (ArrayList<Figure> figures){
        this.figures = figures;
        this.points = points;
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.width = getWidth();
        this.height = getHeight();
        drawGrid(g);
        drawAxis(g);
        drawFigures(g);
    }

    public void repaintAll(ArrayList <Figure> figures){
        this.figures = figures;
        repaint();
    }

    private void drawGrid(Graphics g){
        int width = this.width;
        int height = this.height;
        int startX = 0;
        int startY = 0;
        Graphics canvas = (Graphics)g.create(startX,startY,width,height);
        canvas.setColor(Color.LIGHT_GRAY);
        for(int x=width/2; x<width; x+=30){
            canvas.drawLine(x, 0, x, height);}

        for(int x=width/2; x>0; x-=30){
            canvas.drawLine(x, 0, x, height);}

        for(int y=height/2; y<height; y+=30){
            canvas.drawLine(0, y, width, y);}

        for(int y=height/2; y>0; y-=30){
            canvas.drawLine(0, y, width, y);}
    }

    private void drawAxis(Graphics g){
        g.setColor(Color.BLACK);
        g.drawLine(width/2, 0, width/2, height);
        g.drawLine(0, height/2, width, height/2);
    }

    public void setMultipliers(){
        double minX = figures.get(0).getPoints().get(0).getX();
        double minY = figures.get(0).getPoints().get(0).getY();
        double maxX = figures.get(0).getPoints().get(0).getX();
        double maxY = figures.get(0).getPoints().get(0).getY();
        for (Figure figure: figures){
            for (Point point: figure.getPoints()){
                if (point.getX() < minX){minX= point.getX();}
                if (point.getY() < minY){minY= point.getY();}
                if (point.getX() > maxX){maxX= point.getX();}
                if (point.getX() > maxY){maxY= point.getY();}
            }
        }
        multiplierX = (int)(this.getWidth()*0.5/(maxX-minX));
        multiplierY = (int)(this.getHeight()*0.5/(maxY-minY));
    }

    public void drawFigures(Graphics g){
        if (!figures.isEmpty()){
            setMultipliers();
        }
        for(var fig: figures){
            if (fig.getPoints().size()==2){
                int multiplier = (int)Math.min(multiplierX, multiplierY);
                Circle circle = (Circle) fig;
                double radius = circle.getRadius();
                int x = (int)(circle.getCenter().getX()*multiplier)+getWidth()/2;
                int y = -(int)(circle.getCenter().getY()*multiplier)+getHeight()/2;
                g.drawOval(x - (int)radius, y - (int)radius, (int)radius*2, (int)radius*2);
            }
            else {
                for(int i = 0; i < fig.getPoints().size()-1; i++){
                    int x1 = getWidth()/2 + (int)(fig.getPoints().get(i).getX()*multiplierX);
                    int y1 = getHeight()/2 - (int)(fig.getPoints().get(i).getY()*multiplierY);
                    int x2 = getWidth()/2 + (int)(fig.getPoints().get(i+1).getX()*multiplierX);
                    int y2 = getHeight()/2 - (int)(fig.getPoints().get(i+1).getY()*multiplierY);
                    g.drawLine(x1, y1, x2, y2);
                }
                int x1 = getWidth()/2 + (int)(fig.getPoints().get(fig.getPoints().size()-1).getX()*multiplierX);
                int y1 = getHeight()/2 - (int)(fig.getPoints().get(fig.getPoints().size()-1).getY()*multiplierY);
                int x2 = getWidth()/2 + (int)(fig.getPoints().get(0).getX()*multiplierX);
                int y2 = getHeight()/2 - (int)(fig.getPoints().get(0).getY()*multiplierY);
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
}
