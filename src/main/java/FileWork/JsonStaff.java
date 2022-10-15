package FileWork;
import Figures.Figure;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class JsonStaff {

    public void JsonWriteWhole (ArrayList<Figure> figure) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("JsonShit"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(bw, figure);
        bw.close();
    }

    public void JsonWriteSep (ArrayList<Figure> figure) throws IOException {
       BufferedWriter bw = new BufferedWriter(new FileWriter("JsonShit"));
       ObjectMapper mapper = new ObjectMapper();
       for(var f: figure){
           mapper.writeValue(bw, f);
        }
    }

    public ArrayList<Figure> JsonReadWhole() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("JsonShit"));
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Figure> fig= new ArrayList<>();
        fig.add(mapper.readValue(br, Figure.class));
        return fig;
    }
}