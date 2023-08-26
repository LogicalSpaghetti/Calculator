package me.spaghetti.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

// the text box hasn't worked since I changed the size of the text box

public class Calculator implements ActionListener, KeyListener {

    int minX = 340;
    int minY = 450;

    int panelW = 300;
    int panelH = 300;
    int panelX = minX / 2 - panelW / 2 - 8;
    int panelY = 100;

    JFrame frame;
    JTextField textField;
    JTextField textField2;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Consolas", Font.BOLD, 25);

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean repeat = true;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int locX = (int) ((screenSize.getWidth() - minX) / 2); // accounts for the size of the window when centering it
    int locY = (int) (((screenSize.getHeight() - minY) / 2));

    Boolean topBoolean = false;
    JButton toggleTop = new JButton();
    ImageIcon icon = new ImageIcon("src/main/resources/stayOnTop.png");
    ImageIcon icon2 = new ImageIcon("src/main/resources/onTop.png");
    JButton pwrButton;
    ImageIcon bsIcon = new ImageIcon("src/main/resources/backspace.png");
    Image bsImage = bsIcon.getImage(); // transform it
    Image newImg = bsImage.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    ImageIcon bsIcon2 = new ImageIcon(newImg); // transform it back

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

    void runPlMiMuDi(char getChar) {
        num1 = Double.parseDouble(textField.getText());
        textField2.setText(textField.getText());
        operator = getChar;
        repeat = false;
        textField.setText("0");
    }

    void runPwr(char getChar) {
        textField.setText(String.valueOf(Math.pow(Double.parseDouble(textField.getText()), 2)));
        textField.setText(removeZeroPoint(textField.getText()));
        textField2.setText("");
        operator = getChar;
    }

    void runEquals() {
        if (!repeat) {
            num2 = Double.parseDouble(textField.getText());
        }
        textField2.setText("");
        switch (operator) {
            case '+', ' ' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> result = num1 / num2;
            case '^' -> result = Math.pow(Double.parseDouble(textField.getText()), 2);
            default -> result = Double.parseDouble(textField.getText());
        }
        textField.setText(String.valueOf(result));
        textField.setText(removeZeroPoint(textField.getText()));
        repeat = true;
        num1 = result;
    }

    void clear() {
        textField.setText("0");
        operator = ' ';
        num1 = 0;
        textField2.setText("");
    }

    void backSpace() {
        String string = textField.getText();
        textField.setText("");
        for (int i = 0; i < string.length() - 1; i++) {
            textField.setText(textField.getText() + string.charAt(i));
        }
        if (textField.getText().isEmpty()) {
            textField.setText("0");
        }
    }

    void decimal() {
        // have it only trigger if there isn't already a decimal place
        if (textField.getText().indexOf('.') == -1) {
            textField.setText(textField.getText().concat("."));
        }
    }

    Calculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(locX, locY);
        frame.setSize(minX, minY);
        frame.setLayout(null);
        frame.setMinimumSize(frame.getSize());

        textField = new JTextField();
        textField.setBounds(50, 25, minX - 100, 50);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setBorder(null);
        textField.setText("0");

        textField2 = new JTextField();
        textField2.setBounds(50, 15, minX - 100, 10);
        textField2.setFont(new Font("Consolas", Font.BOLD, 10));
        textField2.setEditable(false);
        textField2.setBorder(null);
        frame.add(textField2);

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

        for (int i = 0; i < 10; i++) {
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
        pwrButton.setFont(new Font("Consolas", Font.BOLD, 15));

        panel = new JPanel();
        panel.setBounds(panelX, panelY, panelW, panelH);
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        negButton.setFont(new Font("Consolas", Font.BOLD, 20));

        Color color1 = new Color(0xFFFFFF);
        for (int i = 0; i < 10; i++) {
            functionButtons[i].setBackground(color1);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i].setBackground(color1);
        }
        toggleTop.setBackground(color1);
        frame.setBackground(color1);

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
        frame.add(textField);
        frame.setLocation(locX, locY);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    static String numberPress(String text, char i) {
        if (text.equals("0")) {
            text = "";
        } // clears the lead zero
        return text.concat(Character.toString(i));
    }

    static String backspace(String textFieldText) {

        String string = textFieldText;
        StringBuilder textFieldTextBuilder = new StringBuilder();
        for (int i = 0; i < string.length() - 1; i++) {
            textFieldTextBuilder.append(string.charAt(i));
        }
        textFieldText = textFieldTextBuilder.toString();
        if (textFieldText.isEmpty()) {
            textFieldText = ("0");
        }
        return textFieldText;
    }

    static String invertSign(String textFieldText) {

        double temp = Double.parseDouble(textFieldText);
        temp *= -1;
        String temp2 = String.valueOf(temp);
        temp2 = backspace(temp2);
        temp2 = backspace(temp2);
        return temp2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(numberPress(textField.getText(), (char) (i + '0')));
            }
        }
        if (e.getSource() == decButton) {
            decimal();
        }
        if (e.getSource() == pwrButton) {
            operator = '^';
            runPwr(operator);
        }

        JButton clickedButton;
        Object evtSrc = e.getSource();
        if (evtSrc instanceof JButton) {
            clickedButton = (JButton) evtSrc;
        } else {
            return;
        }
        if (Arrays.asList(addButton, subButton, mulButton, divButton).contains(clickedButton)) {
            if (e.getSource() == addButton) {
                operator = '+';
            } else if (e.getSource() == subButton) {
                operator = '-';
            } else if (e.getSource() == mulButton) {
                operator = '*';
            } else {
                operator = '/';
            }
            runPlMiMuDi(operator);
        }
        if (e.getSource() == equButton) {
            runEquals();
        }
        if (e.getSource() == clrButton) {
            clear();
        }
        if (e.getSource() == delButton) {
            backSpace();
        }
        if (e.getSource() == negButton) {
            textField.setText(invertSign(textField.getText()));
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
    public void keyReleased(KeyEvent e) {
        char character = e.getKeyChar();
        int keyCode = e.getKeyCode();
        boolean isDigit = Character.isDigit(character);

        if (isDigit) {
            textField.setText(numberPress(textField.getText(), character));
        } else if (Arrays.asList('*', '/', '-', '+').contains(character)) {
            runPlMiMuDi(character);
        } else if (character == '^') {
            runPwr(character);
        } else if (character == '.') {
            decimal();
        } else if (keyCode == 10 || e.getKeyChar() == '=') {
            runEquals();
        } else if (keyCode == 27) {
            clear();
        } else if (keyCode == 8) {
            textField.setText(backspace(textField.getText()));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}