package Graphic;
import javax.swing.*;
import java.awt.*;
import java.applet.*;
public class Test {

    public static class AWTExample1 extends JFrame {

        // initializing using constructor
        public AWTExample1() {

            // creating a button
            Button b = new Button("Click Me!!");

            // setting button position on screen
            b.setBounds(30,100,80,30);

            // adding button into frame
            add(b);

            // frame size 300 width and 300 height
            setSize(300,300);

            // setting the title of Frame
            setTitle("This is our basic AWT example");

            // no layout manager
            setLayout(null);

            // now frame will be visible, by default it is not visible
            setVisible(true);


        }

    }
}
