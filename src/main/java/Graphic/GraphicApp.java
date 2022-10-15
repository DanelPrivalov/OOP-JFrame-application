package Graphic;

import FabricStaff.FigureCreator;
import Figures.Circle;
import Figures.Figure;
import Figures.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GraphicApp extends JFrame {
    private ArrayList<Figure> figures;
    private ArrayList<Figures.Point> points;
    private JFrame frame;
    private JTextField xcoordinate;
    private JTextField ycoordinate;
    private GraphicPanel graphicPanel;
    private JTextField degree;
    private JTextField moveX;
    private JTextField moveY;
    private JTextField koef;
    private int index;


    private int clickCount = 0;
    private JList pointPanel;
    private JList figurePanel;
    private DefaultListModel dfmPoints;
    private DefaultListModel dfmFigures;
    boolean chooseFigureAvailable = false;
    Figure activeFigure = null;
    public ArrayList<Figure> figChosen = new ArrayList<>();

    //конструктор
    public GraphicApp(ArrayList<Figure> figures) {
        this.figures = figures;
        this.points = new ArrayList<>();
        this.clickCount = 0;
        this.graphicPanel = new GraphicPanel(this.figures);
        createFrame();
        collectElements();
    }

    //метод настройки фрейма, делает его, задает закрытие и размеры
    public void createFrame() {
        this.frame = new JFrame("Figure Drawer 40 000");
        this.frame.setSize(1000, 500);
        this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    //что б не пропечатывать setVisible, а просто применить метод
    public void showFrame() {
        this.frame.setVisible(true);
    }


    //накидывакм все созданные элементы на главную панель
    private void collectElements() {
        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        Box rightPanel = createRightPanel();
        mainContainer.add(rightPanel, BorderLayout.EAST);

        this.graphicPanel.setBackground(Color.WHITE);
        mainContainer.add(graphicPanel);
    }


    private Box createRightPanel() {
        Box panel = Box.createVerticalBox();

        JLabel mainTitle = new JLabel("Figure Drawer 40 000");
        mainTitle.setFont(new Font("Veranda", Font.BOLD, 14));
        panel.add(mainTitle);
        panel.add(Box.createVerticalStrut(20));

        JLabel one = new JLabel("Введите координату точки х");
        panel.add(one);
        this.xcoordinate = new JTextField(); //текстовое поле x
        xcoordinate.setMaximumSize(new Dimension(200, 30));
        panel.add(xcoordinate);

        JLabel two = new JLabel("Введите координату точки y");
        panel.add(two);
        this.ycoordinate = new JTextField(); //текстовое поле y
        ycoordinate.setMaximumSize(new Dimension(200, 30));
        panel.add(ycoordinate);

        JButton button1 = new JButton("Добавить точку");
        panel.add(button1);

        JButton button11 = new JButton("Удалить точку");
        panel.add(button11);
        JScrollPane pointList = PointJList();
        panel.add(pointList);


        JButton button2 = new JButton("Создать фигуру");
        panel.add(button2);
        button2.setEnabled(false);

        JButton button21 = new JButton("Удалить фигуру");
        panel.add(button21);

        JScrollPane figureList = FigureJList();
        panel.add(figureList);

        JLabel three = new JLabel("Нажми что б изменить фигуру");
        panel.add(three);
        JButton button3 = new JButton("Изменить");
        panel.add(button3);

        JLabel four = new JLabel("Введите угол поворота");
        panel.add(four);
        this.degree = new JTextField(); //текстовое поле угла
        degree.setMaximumSize(new Dimension(200, 30));
        panel.add(degree);

        JButton button31 = new JButton("Провращать");
        panel.add(button31);

        JLabel five = new JLabel("Введите координаты перемещения по Х");
        panel.add(five);
        this.moveX = new JTextField(); //текстовое поле перемещения Х
        moveX.setMaximumSize(new Dimension(200, 30));
        panel.add(moveX);
        JLabel six = new JLabel("Введите координаты перемещения по Y");
        panel.add(six);
        this.moveY = new JTextField(); //текстовое поле перемещения Y
        moveY.setMaximumSize(new Dimension(200, 30));
        panel.add(moveY);
        JButton button32 = new JButton("Переместить");
        panel.add(button32);

        JLabel seven = new JLabel("Введите коэффициент масштабирования");
        panel.add(seven);
        this.koef = new JTextField(); //текстовое поле перемещения Y
        koef.setMaximumSize(new Dimension(200, 30));
        panel.add(koef);
        JButton button33 = new JButton("Смасштабировать");
        panel.add(button33);

//скрываем окно изменений

        four.setVisible(false);
        degree.setVisible(false);
        button31.setVisible(false);
        five .setVisible(false);
        moveX.setVisible(false);
        six .setVisible(false);
        moveY.setVisible(false);
        button32.setVisible(false);
        seven.setVisible(false);
        koef.setVisible(false);
        button33.setVisible(false);

        panel.add(Box.createVerticalGlue()); //заполнитель пустого места

        //добавить точку
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double x = Double.parseDouble(xcoordinate.getText());
                double y = Double.parseDouble(ycoordinate.getText());
                points.add(new Point(x, y));
                xcoordinate.setText("");
                ycoordinate.setText("");
                dfmPoints.addElement(new Point(x, y));
                clickCount++;
                System.out.println(clickCount);
                if (clickCount>=2) {button2.setEnabled(true);}
            }
        });

        //удалить точку
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = pointPanel.getSelectedIndex();
                dfmPoints.remove(selected);
                points.remove(selected);
                clickCount--;
                System.out.println(clickCount);
                if (clickCount<2){button2.setEnabled(false);}
            }
        });

        //добавить фигуру
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FigureCreator creator1 = new FigureCreator();
                Figure f = creator1.create(points);
                figures.add(f);
                dfmFigures.addElement(f);
                points = new ArrayList<>();
                dfmPoints.removeAllElements();
                clickCount = 0;
                System.out.println(clickCount);
                button2.setEnabled(false);
                graphicPanel.repaintAll(figures);
            }
        });

        //удалить фигуру
        button21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int selected = figurePanel.getSelectedIndex();
                dfmFigures.remove(selected);
                figures.remove(selected);
                graphicPanel.repaintAll(figures);

            }
        });

        //кнопка изменить
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chooseFigureAvailable==false) {

                    one.setVisible(false);
                    xcoordinate.setVisible(false);
                    two.setVisible(false);
                    ycoordinate.setVisible(false);
                    button1.setVisible(false);
                    button11.setVisible(false);
                    button2.setVisible(false);
                    button21.setVisible(false);
                    pointList.setVisible(false);
                    figureList.setVisible(false);

                    four.setVisible(true);
                    degree.setVisible(true);
                    button31.setVisible(true);
                    five.setVisible(true);
                    moveX.setVisible(true);
                    six.setVisible(true);
                    moveY.setVisible(true);
                    button32.setVisible(true);
                    seven.setVisible(true);
                    koef.setVisible(true);
                    button33.setVisible(true);

                    chooseFigureAvailable=true;

                    graphicPanel.repaintAll(figures);

                    ArrayList<Figure> figChosen = new ArrayList<>();

                }else{
                    one.setVisible(true);
                    xcoordinate.setVisible(true);
                    two.setVisible(true);
                    ycoordinate.setVisible(true);
                    button1.setVisible(true);
                    button11.setVisible(true);
                    button2.setVisible(true);
                    button21.setVisible(true);
                    pointList.setVisible(true);
                    figureList.setVisible(true);

                    four.setVisible(false);
                    degree.setVisible(false);
                    button31.setVisible(false);
                    five.setVisible(false);
                    moveX.setVisible(false);
                    six.setVisible(false);
                    moveY.setVisible(false);
                    button32.setVisible(false);
                    seven.setVisible(false);
                    koef.setVisible(false);
                    button33.setVisible(false);

                    chooseFigureAvailable=false;

                    graphicPanel.repaintAll(figures);

                    ArrayList<Figure> figChosen = new ArrayList<>();
                }
            }
        });

        //кнопка вращения
        button31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int deg = Integer.parseInt(degree.getText());
                figChosen.get(0).rotate(deg);
                figures.add(figChosen.get(0));
                graphicPanel.repaintAll(figChosen);
            }
        });

        //кнопка перемещения
        button32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = Double.parseDouble(moveX.getText());
                double y = Double.parseDouble(moveY.getText());
                for (Figure f: figChosen){
                    f.move(x, y);
                    System.out.println(figChosen);
                    figures.add(f);
                    graphicPanel.repaintAll(figChosen);
                }
            }
        });

        //кнопка масштабирования
        button33.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double k = Double.parseDouble(koef.getText());
                for (Figure f: figChosen){
                    f.scale(k);
                    System.out.println(figChosen);
                    figures.add(f);
                    graphicPanel.repaintAll(figChosen);
                }
            }
        });

        //слушатель мыши
        graphicPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() - graphicPanel.getWidth()/2;
                int y = -e.getY() + graphicPanel.getHeight()/2;
                activeFigure = defineFigureByCursor(x, y, graphicPanel.multiplierX, graphicPanel.multiplierY, figures);

                if(chooseFigureAvailable && activeFigure!= null){
                    figChosen.add(activeFigure);
                    figures.remove(activeFigure);
                    graphicPanel.repaintAll(figChosen);
                    System.out.println(activeFigure);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        return panel;
    }

    public Figure defineFigureByCursor (int x, int y, int multiplierX, int multiplierY, ArrayList<Figure> figures){
        for (Figure f: figures){
            if (f.containPoint(x, y, multiplierX, multiplierY)) {
                this.index = points.indexOf(f);
                return f;
            }
        }
        return null;
    }

    private JScrollPane PointJList() {
        dfmPoints = new DefaultListModel();
        this.pointPanel = new JList<>(dfmPoints);
        JScrollPane pointScroll = new JScrollPane(pointPanel);
        pointScroll.setMaximumSize(new Dimension(800, 100));
        pointPanel.setLayoutOrientation(JList.VERTICAL);
        return pointScroll;
    }

    private JScrollPane FigureJList() {
        dfmFigures = new DefaultListModel();
        this.figurePanel = new JList<>(dfmFigures);
        JScrollPane figureScroll = new JScrollPane(figurePanel);
        figureScroll.setMaximumSize(new Dimension(800, 100));
        figurePanel.setLayoutOrientation(JList.VERTICAL);
        return figureScroll;
    }
}