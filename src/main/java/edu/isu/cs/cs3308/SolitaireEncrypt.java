package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.CircularlyLinkedList;
import edu.isu.cs.cs3308.structures.impl.SinglyLinkedList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SolitaireEncrypt {


    /*the filePath containing the requested starting
    deck to use. Obtained from the constructor.
    */
    private String filePath;

    /*
    Circularly linked list to hold the order of the cards
     */
    private CircularlyLinkedList<Integer> deck = new CircularlyLinkedList<>();

    /*
    Message after conversion to integers
     */
    private SinglyLinkedList<Integer> numericalMessage = new SinglyLinkedList<>();

    /*
    Generated keystream
     */
    private SinglyLinkedList<Integer> keystream = new SinglyLinkedList<>();

    /*
    NumericalMessage elements added to keystream elements modulo 26 if necessary
     */
    private SinglyLinkedList<Integer> almostEncrypted = new SinglyLinkedList<>();

    /*
    Constructor for SolitairEncrypt, accepts and sets the
    file path to the requested starting deck
     */
    public SolitaireEncrypt(String fp){
        filePath = fp;
    }

    /*

     */
    public String execute(String message){
        String toReturn = "";

        buildDeck();
        stringConversion(message);
        keystreamGeneration();
        numberMash();

        for (int count = 0; count < almostEncrypted.size(); count++){
            toReturn += numberToLetter(almostEncrypted.get(count));
        }

        return toReturn;
    }

    /*
    Reads the deck order in from a file, splits the string into an array,
    and fills the deck list with the parsed ints

    Conversion from string array to Char Array found at https://stackoverflow.com/questions/19594587/how-to-convert-a-string-array-to-char-array-in-java
     */
    public void buildDeck(){
        String line;
        String[] splitString = new String[27];
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((line = br.readLine()) != null){
                splitString = line.split(" ");
            }
            br.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File Not Found");
        }
        catch (IOException ex){
            System.out.println("Error Reading File");
        }


        for (int count = 0; count < splitString.length; count++) {
            deck.addLast(Integer.parseInt(splitString[count]));
        }
    }

    /*
    Reads in a string,  removes white space,  converts to char array, removes non letters from char array, populates numericalMessage list with
    corresponding integers
     */
    public void stringConversion(String message){
        String[] messageString = message.split(" ");
        String stringToChar = "";


        for (String n:messageString){
            stringToChar += n;
        }
        char[] ch = stringToChar.toCharArray();
        for (int count = 0; count < ch.length; count++) {
            if ((ch[count] >= 'a' && ch[count] <= 'z') || (ch[count] >= 'A' && ch[count] <= 'Z')) {
                numericalMessage.addLast(letterToNumber(ch[count]));
            }
        }

        while(numericalMessage.size() < 25){
            numericalMessage.addLast(letterToNumber('X'));
        }
    }

    /*
    converts letters to their corresponding numbers.
    */
    private int letterToNumber(char letter){
        int intToReturn = 0;
        if (letter == 'A' || letter == 'a'){
            intToReturn = 1;
        }
        else if (letter == 'B' || letter == 'b'){
            intToReturn = 2;
        }
        else if (letter == 'C' || letter == 'c'){
            intToReturn = 3;
        }
        else if (letter == 'D' || letter == 'd'){
            intToReturn = 4;
        }
        else if (letter == 'E' || letter == 'e'){
            intToReturn = 5;
        }
        else if (letter == 'F' || letter == 'f'){
            intToReturn = 6;
        }
        else if (letter == 'G' || letter == 'g'){
            intToReturn = 7;
        }
        else if (letter == 'H' || letter == 'h'){
            intToReturn = 8;
        }
        else if (letter == 'I' || letter == 'i'){
            intToReturn = 9;
        }
        else if (letter == 'J' || letter == 'j'){
            intToReturn = 10;
        }
        else if (letter == 'K' || letter == 'k'){
            intToReturn = 11;
        }
        else if (letter == 'L' || letter == 'l'){
            intToReturn = 12;
        }
        else if (letter == 'M' || letter == 'm'){
            intToReturn = 13;
        }
        else if (letter == 'N' || letter == 'n'){
            intToReturn = 14;
        }
        else if (letter == 'O' || letter == 'o'){
            intToReturn = 15;
        }
        else if (letter == 'P' || letter == 'p'){
            intToReturn = 16;
        }
        else if (letter == 'Q' || letter == 'q'){
            intToReturn = 17;
        }
        else if (letter == 'R' || letter == 'r'){
            intToReturn = 18;
        }
        else if (letter == 'S' || letter == 's'){
            intToReturn = 19;
        }
        else if (letter == 'T' || letter == 't'){
            intToReturn = 20;
        }
        else if (letter == 'U' || letter == 'u'){
            intToReturn = 21;
        }
        else if (letter == 'V' || letter == 'v'){
            intToReturn = 22;
        }
        else if (letter == 'W' || letter == 'w'){
            intToReturn = 23;
        }
        else if (letter == 'X' || letter == 'x'){
            intToReturn = 24;
        }
        else if (letter == 'Y' || letter == 'y'){
            intToReturn = 25;
        }
        else if (letter == 'Z' || letter == 'z'){
            intToReturn = 26;
        }
        return intToReturn;
    }

    /*
    converts numbers back to letters
     */
    private String numberToLetter(int number){
        String toReturn = "";

        if (number == 1){
            toReturn = "A";
        }
        else if (number == 2){
            toReturn = "B";
        }
        else if (number == 3){
            toReturn = "C";
        }
        else if (number == 4){
            toReturn = "D";
        }
        else if (number == 5){
            toReturn = "E";
        }
        else if (number == 6){
            toReturn = "F";
        }
        else if (number == 7){
            toReturn = "G";
        }
        else if (number == 8){
            toReturn = "H";
        }
        else if (number == 9){
            toReturn = "I";
        }
        else if (number == 10){
            toReturn = "J";
        }
        else if (number == 11){
            toReturn = "K";
        }
        else if (number == 12){
            toReturn = "L";
        }
        else if (number == 13){
            toReturn = "M";
        }
        else if (number == 14){
            toReturn = "N";
        }
        else if (number == 15){
            toReturn = "O";
        }else if (number == 16){
            toReturn = "P";
        }
        else if (number == 17){
            toReturn = "Q";
        }
        else if (number == 18){
            toReturn = "R";
        }
        else if (number == 19){
            toReturn = "S";
        }
        else if (number == 20){
            toReturn = "T";
        }
        else if (number == 21){
            toReturn = "U";
        }
        else if (number == 22){
            toReturn = "V";
        }
        else if (number == 23){
            toReturn = "W";
        }
        else if (number == 24){
            toReturn = "X";
        }
        else if (number == 25){
            toReturn = "Y";
        }
        else if (number == 26){
            toReturn = "Z";
        }

        return toReturn;


    }

    /*
    finds the index of element 27, removes it and reinserts it 1 position later
    find the index of element 28, removes it and reinserts it 2 positions later
    */
    private void stepOne() {
        int indexOf27 = deck.indexOf(27);
        deck.remove(indexOf27);
        deck.insert(27, (indexOf27 + 1));
        indexOf27 = deck.indexOf(27);
        int indexOf28 = deck.indexOf(28);
        deck.remove(indexOf28);
        deck.insert(28, (indexOf28 + 2));
        indexOf28 = deck.indexOf(28);
    }

    /*
    locates the position of value 27 and 28.  takes the values before the smallest position, and the values after
    the larger position and swaps them
     */
    private void stepTwo(){
        int indexOf27 = deck.indexOf(27);
        int indexOf28 = deck.indexOf(28);
        SinglyLinkedList<Integer> frontOfDeck = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> backOfDeck = new SinglyLinkedList<>();

        if (indexOf27 < indexOf28){
            for (int count = indexOf28 +1; count <= 27; count++){
                backOfDeck.addLast(deck.remove(deck.get(indexOf28)+1));
            }
            for (int count = 0; count < indexOf27; count++){
                frontOfDeck.addLast(deck.removeFirst());
            }

        }
        else{
            for (int count = indexOf27 +1; count <= 27; count++){
                backOfDeck.addLast(deck.remove((indexOf27+1)));
            }
            for (int count = 0; count < indexOf28; count++){
                frontOfDeck.addLast(deck.remove(0));
            }
        }
        frontOfDeck.printList();
        System.out.println("");
        backOfDeck.printList();
        while (frontOfDeck.size() > 0){
            deck.addLast(frontOfDeck.removeFirst());
        }
        while (backOfDeck.size() > 0){
            deck.addLast(backOfDeck.removeFirst());
        }

    }

    /*
    Gets the value of the bottom card in the deck, if its a joker the value is 27, counts down that value from the top
    of the deck and moves those cards to the bottom of the deck.
    */
    private void stepThree(){
        int bottomCard;
        if (deck.removeLast() >= 27){
            bottomCard = 27;
        }else {
            bottomCard = deck.removeLast();
        }
        //= deck.removeLast();
        //SinglyLinkedList<Integer> tempDeck = new SinglyLinkedList<>();

        for (int count = 0; count < bottomCard; count++){
            deck.addLast(deck.removeFirst());
        }
        deck.addLast(bottomCard);

    }

    /*
    final step in generating a keystream value.  takes the value of the first card and gets the value
    in the index of that value +1.  checks if it a 27 or 28, if it is, the process begins again, if not that
    value becomes the keystream value and is returned.
    */
    private int stepFour(){
        int first = deck.first();
        int keystreamValue = deck.get(first + 1);

        if (keystreamValue == 27 || keystreamValue == 28){
            keystreamValue = keystreamSteps();
        }

        return keystreamValue;


    }

    /*
    Runs steps 1 through 4 to generate a keystream value
     */
    private int keystreamSteps(){
        stepOne();
        stepTwo();
        stepThree();
        return stepFour();
    }

    /*
    Loop to generate the proper ammount of keystream values
     */
    private void keystreamGeneration(){
        for (int count = 0; count < numericalMessage.size(); count++){
            keystream.addLast(keystreamSteps());
        }
    }

    /*
    Adds NumericalMessage elements to keystream elements modulo 26 if necessary
     */
    private void numberMash(){
        for (int count = 0; count < numericalMessage.size(); count++){
            if (numericalMessage.get(count) + keystream.get(count) > 26){
                almostEncrypted.addLast((numericalMessage.get(count) + keystream.get(count)) - 26);
            }
            else {
                almostEncrypted.addLast(numericalMessage.get(count) + keystream.get(count));
            }
        }
    }


}