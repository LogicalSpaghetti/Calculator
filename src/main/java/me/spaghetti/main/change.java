package me.spaghetti.main;

public class change {

    static String numberPress(String text, char i) {
        if (text.equals("0")) {
            text = "";
        } //clears the lead zero
        return text.concat(Character.toString(i));
    }

    static String backspace(String textfieldText) {
       
        String string = textfieldText;
                textfieldText = "";
                for (int i = 0; i < string.length() - 1; i++) {
                    textfieldText = (textfieldText + string.charAt(i));
                }
                if (textfieldText.equals("")) {
                    textfieldText = ("0");
                }
        return textfieldText;
    }
    static String invertSign(String textfieldText) {
        
        double temp = Double.parseDouble(textfieldText);
        temp *= -1;
        String temp2 = String.valueOf(temp);
        temp2 = change.backspace(temp2);
        temp2 = change.backspace(temp2);
        return temp2;
    }
}
