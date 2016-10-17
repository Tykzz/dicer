package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Model {

    private Map<Integer, String> wordList;
    private Random numberGenerator;
    private char separator;
    private int wordNumber;

    public Model() {
        wordList = new HashMap<>();
        numberGenerator = new Random();
        this.separator = '.';
        this.wordNumber = 7;
        loadWordList("list.txt");
    }

    private void loadWordList(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                String[] split = line.split(" ");
                wordList.put(Integer.parseInt(split[0]), split[1]);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generatePassword() {
        String password = "";
        for(int i = 0; i < wordNumber; i++) {
            String word = wordList.get(generateWordPosition());
            password += word;
            if(i != wordNumber - 1) password += separator;
        }
        return password;
    }

    private int generateWordPosition() {
        int position = 0;
        for(int j = 4; j >= 0; j--) {
            int roll = numberGenerator.nextInt(6) + 1;
            position += roll * (int) Math.pow(10, j);
        }
        return position;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(int wordNumber) {
        this.wordNumber = wordNumber;
    }

    public Map<Integer, String> getWordList() {
        return wordList;
    }

    public void setWordList(Map<Integer, String> wordList) {
        this.wordList = wordList;
    }
}
