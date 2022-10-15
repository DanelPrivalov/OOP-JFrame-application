package com.company;
import FabricStaff.FigureCreator;
import Figures.*;
import Figures.Point;
import Graphic.GraphicApp;
//import Graphic.Test3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        Считывание файлов
//        ReadFiguresNameReader();
//        ReadPointsScanner();

	    Point p1 = new Point(-20,-20);
        Point p2 = new Point(-20,20);
        Point p3 = new Point(20,20);
        Point p4 = new Point(20,-20);

        ArrayList<Point> randomFigure1 = new ArrayList(Arrays.asList(p1, p2, p3));
        FigureCreator creator1 = new FigureCreator();
        Figure f1 = creator1.create(randomFigure1);
//        System.out.println(f1.toString());
//        f1.getPerimetr();
//        f1.getArea();
//        WriteStringFileWriter(f1);

        ArrayList<Point> randomFigure2 = new ArrayList<>(Arrays.asList(p1, p2));
        FigureCreator creator2 = new FigureCreator();
        Figure f2 = creator2.create(randomFigure2);
//        System.out.println(f2.toString());
//        f2.getArea();
//        f2.getPerimetr();
//        WriteStringFileWriter(f2);

        ArrayList<Point> randomFigure3 = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));
        FigureCreator creator3 = new FigureCreator();
        Figure f3 = creator3.create(randomFigure3);
//        System.out.println(f3.toString());
//        f3.getArea();
//        f3.getPerimetr();
//        WriteStringFileWriter(f3);
//        System.out.println(f3.getCenter());
//        f2.rotate(Math.PI/2);
//        f3.rotate(Math.PI/2);
//
//        System.out.println(f2.toString());
//        System.out.println(f3.toString());
//
//        f2.move(1, 1);
//        f3.move(1, 1);

//        System.out.println(f2.toString());
//        System.out.println(f3.toString());
//        System.out.println(f3.getCenter());
//        f2.scale(2);
//        f3.scale(2);

//        System.out.println(f2.toString());
//        System.out.println(f3.toString());

// DateFromFile();


//        Сериализация
        ArrayList<Figure> testSer= new ArrayList<>(Arrays.asList(f1));
//        fileSerializibleSep(testSer);
//
//        ArrayList<Object> object1 = fileDeSerializibleSep();
//        System.out.println(object1);
//        JsonStaff a = new JsonStaff();
//        a.JsonWriteWhole (testSer);

//        Test.AWTExample1 f = new Test.AWTExample1();
//
//        Test2.AWTExample2 awt_obj = new Test2.AWTExample2();
        ArrayList<Figure> graphic= new ArrayList<>();

        GraphicApp app = new GraphicApp(graphic);
//        JFrame frame = new JFrame();
//        frame.add(a);
        app.showFrame();

//        Test3.Lines a = new Test3.Lines();
//        a.paint();
        }
    }
