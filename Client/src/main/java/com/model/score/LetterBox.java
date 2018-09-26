package com.model.score;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class LetterBox {
    private static Random random = new Random();

    public LetterBox() {
        try{
            FileReader fileReader = new FileReader("com/model/score/letters.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String letter;
            while ((letter =bufferedReader.readLine()) != null){
                String[] letters = letter.split(",");
                int letterDistribution = Integer.parseInt(letters[1]);
                for (int i = 0; i < letterDistribution; i++){
                    char alphabet = letters[0].equalsIgnoreCase("blank")?' ' : letters[0].charAt(0);
                    int score = Integer.parseInt(letters[2]);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
