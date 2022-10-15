package FileWork;
import FabricStaff.FigureCreator;
import Figures.Figure;
import Figures.Point;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class FileOperations {

    public static void ReadFiguresNameScanner () throws FileNotFoundException {
        File file = new File("Figures");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
    scanner.close();
    }

    public static void ReadFiguresNameReader () throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("WriteFigures"));
        String line;
        while ((line = br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
    }

    public static void ReadPointsScanner() throws FileNotFoundException{
        File file = new File("Numbers");
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] numbersString = line.split(" ");
        int [] numbers = new int [8];
        int counter = 0;
        for(String number : numbersString){
            numbers [counter++] = Integer.parseInt(number);
        }
        System.out.println(Arrays.toString(numbers));
    }

    public static void WriteStringFile (Figure figure) throws FileNotFoundException{
        File file = new File("Write");
        PrintWriter pw = new PrintWriter(file);
        pw.println(figure.getClass().getSimpleName());
        pw.close();
    }

    public static void WriteStringFileWriter (Figure figure) throws IOException {
        FileWriter writer = new FileWriter("WriteFigures", true);
        BufferedWriter bw = new BufferedWriter(writer);
        String result = figure.getClass().getSimpleName() + ": ";
        ArrayList<Point> list = figure.getPoints();
        for (var point: list){
            result += point.getFullPoints();
        }
        bw.append(result + "\n");
        System.out.println(result + "\n");
        bw.close();
    }

    public static void DateFromFile () throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("WriteFigures"));
        ArrayList<Figure> figureList = new ArrayList<>();
        String line;
        while ((line = br.readLine())!=null){
            ArrayList<Point> list = new ArrayList<>();
            String figureName = line.substring(0, line.indexOf(":"));
            String numbers = line.substring(line.indexOf(":")+1, line.length());
            String [] points = numbers.split("\\)");
            for(var p: points){
                String[] coordinates = p.split(";");
                double x = Double.valueOf(coordinates[0].trim().substring(1));
                double y = Double.valueOf(coordinates[1].trim());
                Point point = new Point(x, y);
                list.add(point);
            }
            System.out.println(figureName);

            for (var p: list){
                System.out.println(p);
            }
            figureList.add(new FigureCreator().create(list));
        }
        for (var p: figureList){
            System.out.println(p);
        br.close();
        }
    }

    public static void fileSerializible (ArrayList figurelist) throws IOException {
        ObjectOutputStream ser = new ObjectOutputStream(new FileOutputStream("Serializible"));
        ser.writeObject(figurelist);
        ser.close();
    }

    public static void fileSerializibleSep (ArrayList figurelist) throws IOException {
        ObjectOutputStream ser = new ObjectOutputStream(new FileOutputStream("Serializible"));
        for(var fig: figurelist) {
            ser.writeObject(fig);
        }
        ser.close();
    }

    public static ArrayList<Object> fileDeSerializible () throws IOException, ClassNotFoundException {
        ObjectInputStream deser = new ObjectInputStream(new FileInputStream("Serializible"));
        ArrayList<Object> arrayObjects;
        arrayObjects = ((ArrayList<Object>)deser.readObject());
        return arrayObjects;
    }

    public static ArrayList<Object> fileDeSerializibleSep () throws IOException, ClassNotFoundException {
        FileInputStream isstream = new FileInputStream("Serializible");
        ObjectInputStream deser = new ObjectInputStream(isstream);
        ArrayList<Object> arrayObjects = new ArrayList<>();
        while (isstream.available()>0){
            arrayObjects.add((Figure)deser.readObject());
        }
        return arrayObjects;
    }

}

