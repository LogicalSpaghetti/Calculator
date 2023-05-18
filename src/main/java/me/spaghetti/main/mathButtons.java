package me.spaghetti.main;

public class mathButtons {
    
    int y = 0;

    //static void ;

    static String[] buttons(char character, int charCode,String textfieldText) {
       
        /*
        add an input of the current state of every relevant variable, 
        and output all new values as strings, then convert them on the other side, 
        probably in their own method if it would be too cluttered on the other side, 
        but a few variable = entry x of string shouldn't be too difficult.
        */

        switch(charCode) {
            case 10: //enter
                character = '=';
                break;
            case 27: //escape/clear
                break;
            case 8: //backspace
                break;
        }
        switch(character) { //handles the cases where it has a valid ascii symbol
            case '=':
                break;
            case '^':
                break;
            case '*':
                break;
            case '/':
                break;
            case '+':
                break;
            case '-':
                break;
        }
        String[] stringArray = {/*put all the different variables I need to take out in here*/};
        return stringArray;
    }

}
