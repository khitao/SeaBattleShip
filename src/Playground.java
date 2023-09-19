import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Playground {
    private char[][] board;

    private Set<Coordinate> shipSet;




    public Playground() {
        this.board = new char[10][10];
        this.shipSet = new HashSet<>();

        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 10; i++) {
            Arrays.fill(board[i], '#');
        }
    }


    public void showBoard() {
        for (int i = 0; i < 11; i++) {
            if (i == 0) {
                System.out.print("N ");
                for (int j = 0; j < 10; j++) {
                    System.out.print(j + " ");
                }
                System.out.println();
            } else {
                System.out.print(i - 1 + " ");
                for (int j = 0; j < 10; j++) {
                    System.out.print(board[i - 1][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public Set<Coordinate> getShipSet() {
        return shipSet;
    }

    public void setShipSet(Set<Coordinate> shipSet) {
        this.shipSet = shipSet;
    }


    public void addShipInSet(Coordinate coordinate) {
        shipSet.add(coordinate);
    }


}
