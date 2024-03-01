import java.util.*;

public class Main {
    public static void main(String[] args) {
        Board.makeArrayList();
        Scanner s = new Scanner(System.in);
        Board b;
        String[][] letters = new String[4][4];

        String[] rows = new String[4];
        rows[0] = s.nextLine();
        rows[1] = s.nextLine();
        rows[2] = s.nextLine();
        rows[3] = s.nextLine();

        System.out.println("Searching...\n");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                letters[i][j] = rows[i].substring(j, j + 1);
            }
        }
        b = new Board(letters);

        long startTime = System.currentTimeMillis();
        b.findWords();
        long endTime = System.currentTimeMillis();

        System.out.println("\n" + ((1.0 * endTime - startTime) / 1000) + " seconds");
    }
}


/*
test boards:
hcwa
hntn
oweo
fmts

ahta
aaaa
aaaa
aaaa

*/
