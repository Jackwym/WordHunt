import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    int i, j;
    ArrayList<String> possibleWords = new ArrayList<String>();
    private int longetSearchLength = 7;
    private final int printedLengthVary = 1;
    private int[] Xpath = new int[longetSearchLength];
    private int[] Ypath = new int[longetSearchLength];
    private ArrayList<String> nonViableLetters = new ArrayList<String>();
    private String[][] board;
    public static HashMap<String, Boolean> allViableWords = new HashMap<String, Boolean>();
    public Board(String[][] b) {
        nonViableLetters.add("q");
        nonViableLetters.add("w");
        nonViableLetters.add("e");
        nonViableLetters.add("r");
        nonViableLetters.add("t");
        nonViableLetters.add("y");
        nonViableLetters.add("u");
        nonViableLetters.add("i");
        nonViableLetters.add("o");
        nonViableLetters.add("p");
        nonViableLetters.add("a");
        nonViableLetters.add("s");
        nonViableLetters.add("d");
        nonViableLetters.add("f");
        nonViableLetters.add("g");
        nonViableLetters.add("h");
        nonViableLetters.add("j");
        nonViableLetters.add("k");
        nonViableLetters.add("l");
        nonViableLetters.add("z");
        nonViableLetters.add("x");
        nonViableLetters.add("c");
        nonViableLetters.add("v");
        nonViableLetters.add("b");
        nonViableLetters.add("n");
        nonViableLetters.add("m");
        board = b;
        for (String[] i : board) {
            for (String j : i) {
                nonViableLetters.remove(j);
            }
        }
    }


    public static void makeArrayList() {
        String lineRead;
        try { // code only runs once / is nearly optimized
            BufferedReader allWordReader = new BufferedReader(new FileReader("scrabbleWords.txt"));
            while (true) {
                lineRead = allWordReader.readLine();
                if (lineRead == null) break;
                lineRead = lineRead.toLowerCase();
                allViableWords.put(lineRead, true);
            }
        } catch(IOException e ) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> findWords() {
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) { // iterate through board squares
                traverseBoard(0);
            }
        }
        int length = longetSearchLength;
        for (int l = 0; l < longetSearchLength; l++) {
            for (String s : possibleWords) {
                if (l == s.length()) System.out.println(s);
            }
        }
        return possibleWords;
    }

    private void traverseBoard(int distanceTraversed) {
        if (distanceTraversed == longetSearchLength) { // base case

            if (checkForIntersections(distanceTraversed)) return;

            addPossible(distanceTraversed);

            return;
        }
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!(x == 0 && y == 0)) {
                    Xpath[distanceTraversed] = x;
                    Ypath[distanceTraversed] = y;
                    if (checkForIntersections(distanceTraversed)) continue;
                    addPossible(distanceTraversed);
                }
                traverseBoard(distanceTraversed + 1);
            }
        }
    }

    private boolean checkForIntersections(int lengthOfPath) {
        int index;
        int sumX;
        int sumY;
        for (int start = 0; start < lengthOfPath; start++) {
            for (int end = start + 1; end < lengthOfPath; end++) {
                index = start;
                sumX = 0;
                sumY = 0;
                while (index <= end) {
                    sumX += Xpath[index];
                    sumY += Ypath[index];
                    index++;
                }
                if (sumX == 0 && sumY == 0) return true;
            }
        }
        return false;
    }

    private void addPossible(int length) {
        String boardWord = "";
        int ySoFar = i;
        int xSoFar = j;
        try { // check if path goes off of board
            for (int s = 0; s < length; s++) {
                boardWord += board[ySoFar + Ypath[s]][xSoFar + Xpath[s]];
                ySoFar += Ypath[s];
                xSoFar += Xpath[s];
            }
        } catch (Exception e) {
            return;
        }
        if (allViableWords.containsKey(boardWord) && !possibleWords.contains(boardWord)) {
            possibleWords.add(boardWord);
            for (int i = printedLengthVary; i >= 1; i--) {
                if (boardWord.length() == longetSearchLength - i) System.out.println(boardWord);
            }
        }
    }
}
