package edu.isu.cs.cs3308;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args){

        SolitaireEncrypt se = new SolitaireEncrypt("data/deck1.txt");
        System.out.println(se.execute("Professor Griffith is Crazy!"));
    }
}
