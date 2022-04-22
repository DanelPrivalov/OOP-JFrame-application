package com.company;
import FabricStaff.FigureCreator;
import Figures.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static FileWork.FileOperations.*;


public class Main {

    public static void main(String[] args) throws IOException {

//        Считывание файлов
//        ReadFiguresNameReader();
//        ReadPointsScanner();

	    Point p1 = new Point(0,0);
        Point p2 = new Point(0,4);
        Point p3 = new Point(3,4);
        Point p4 = new Point(3,0);

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
//       WriteStringFileWriter(f2);

        ArrayList<Point> randomFigure3 = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));
        FigureCreator creator3 = new FigureCreator();
        Figure f3 = creator3.create(randomFigure3);
//        System.out.println(f3.toString());
//        f3.getArea();
//        f3.getPerimetr();
//        WriteStringFileWriter(f3);

        DateFromFile();

    }
}
