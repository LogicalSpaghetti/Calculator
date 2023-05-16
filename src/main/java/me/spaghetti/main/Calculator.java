package me.spaghetti.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener, KeyListener {

    int minX = 400;
    int minY = 500;

    JFrame frame;
    JTextField textfield;
    JTextField textfield2;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Consolas", Font.BOLD, 25);

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean repeat = false;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int locX = (int) ((screenSize.getWidth() - minX) / 2); // accounts for the size of the window when centering it
    int locY = (int) (((screenSize.getHeight() - minY) / 2));

    Boolean topBoolean = false;
    JButton toggleTop = new JButton();
    ImageIcon icon = new ImageIcon("stayOnTop.png");
    ImageIcon icon2 = new ImageIcon("onTop.png");
    JButton pwrButton = new JButton();
    ImageIcon bsIcon = new ImageIcon("backspace.png");
    Image bsImage = bsIcon.getImage(); // transform it
    Image newimg = bsImage.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    ImageIcon bsIcon2 = new ImageIcon(newimg); // transform it back

    String stringSansZero;

    String removeZeroPoint(String startString) {
        String stringA = String.valueOf(startString.charAt(startString.length() - 1));
        String stringB = String.valueOf(startString.charAt(startString.length() - 2));
        if (stringA.equals("0") & stringB.equals(".")) {
            stringSansZero = startString.substring(0, startString.length() - 2);
        } else {
            stringSansZero = startString;
        }
        return stringSansZero;
    }

    Calculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(locX, locY);
        frame.setSize(minX, minY);
        frame.setLayout(null);
        frame.setMinimumSize(frame.getSize());

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.setBorder(null);
        textfield.setText("0");

        textfield2 = new JTextField();
        textfield2.setBounds(50, 15, 300, 10);
        textfield2.setFont(new Font("Consolas", Font.BOLD, 10));
        textfield2.setEditable(false);
        textfield2.setBorder(null);
        frame.add(textfield2);

        toggleTop.setBounds(minX - 50, 5, 30, 30);
        frame.add(toggleTop);
        toggleTop.setIcon(icon);
        toggleTop.addActionListener(this);
        toggleTop.setBorder(null);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton();
        clrButton = new JButton("C");
        negButton = new JButton("-/+");

        pwrButton = new JButton("x^2");

        delButton.setIcon(bsIcon2);

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = pwrButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }
        pwrButton.addActionListener(this);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        negButton.setFont(new Font("Consolas", Font.BOLD, 20));

        Color oogle = new Color(0xFFFFFF);
        for (int i = 0; i < 10; i++) {
            functionButtons[i].setBackground(oogle);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i].setBackground(oogle);
        }
        toggleTop.setBackground(oogle);
        frame.setBackground(oogle);

        panel.add(clrButton);
        panel.add(pwrButton);
        panel.add(delButton);
        panel.add(divButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(negButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);

        frame.setFocusable(true);
        frame.addKeyListener(this);

        frame.add(panel);
        frame.add(textfield);
        frame.setLocation(locX, locY);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (textfield.getText().equals("0")) {
                    textfield.setText("");
                }
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == pwrButton) {
            textfield.setText(String.valueOf(Math.pow(Double.parseDouble(textfield.getText()), 2)));
            textfield.setText(removeZeroPoint(textfield.getText()));
            operator = '^';
        }
        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            textfield2.setText(textfield.getText());
            operator = '+';
            repeat = false;
            textfield.setText("0");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            textfield2.setText(textfield.getText());
            operator = '-';
            repeat = false;
            textfield.setText("0");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            textfield2.setText(textfield.getText());
            operator = '*';
            repeat = false;
            textfield.setText("0");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            textfield2.setText(textfield.getText());
            operator = '/';
            repeat = false;
            textfield.setText("0");
        }
        if (e.getSource() == equButton) {
            if (!repeat) {
                num2 = Double.parseDouble(textfield.getText());
            }
            textfield2.setText("");
            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
                case '^' -> result = Math.pow(Double.parseDouble(textfield.getText()), 2);
                default -> result = Double.parseDouble(textfield.getText());
            }
            textfield.setText(String.valueOf(result));
            textfield.setText(removeZeroPoint(textfield.getText()));
            repeat = true;
            num1 = result;
        }
        if (e.getSource() == clrButton) {
            textfield.setText("0");
            operator = ' ';
            num1 = 0;
            textfield2.setText("");
        }
        if (e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textfield.setText(textfield.getText() + string.charAt(i));
            }
            if (textfield.getText().equals("")) {
                textfield.setText("0");
            }
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            textfield.setText(String.valueOf(temp));
        }
        if (e.getSource() == toggleTop) {
            topBoolean = !topBoolean;
            frame.setAlwaysOnTop(topBoolean);
            if (topBoolean) {
                toggleTop.setIcon(icon2);
            } else {
                toggleTop.setIcon(icon);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        System.out.println("keyPressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("You released key char: " + e.getKeyChar());
        textfield.setText(textfield.getText().concat(Character.toString(e.getKeyChar())));
        System.out.println(e.getKeyChar());
        System.out.println("keyReleased");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        System.out.println("keyTyped");
    }
}