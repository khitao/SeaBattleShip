import java.util.*;

public class Player {

    private String name;

    private Playground playground;

    private Playground opponentBoard;

    private HashMap<Integer, ArrayList<Ship>> allAccessShips;


    private int superBomb = 1;


    public Player(String name) {
        this.name = name;
        this.playground = new Playground();
        this.allAccessShips = new HashMap<>();
        this.opponentBoard = new Playground();

        initializeShips();
    }


    private void initializeShips() {
        allAccessShips.put(1, new ArrayList<>(List.of(new Ship(1), new Ship(1)/*, new Ship(1)*/)));
        allAccessShips.put(3, new ArrayList<>(List.of(new Ship(3)/*, new Ship(3)*/)));
        //    allAccessShips.put(5, new ArrayList<>(Collections.singletonList(new Ship(5))));
    }


    public Playground getPlayground() {
        return playground;
    }

    public void setPlayground(Playground playground) {
        this.playground = playground;
    }

    public Playground getOpponentBoard() {
        return opponentBoard;
    }

    public void setOpponentBoard(Playground opponentBoard) {
        this.opponentBoard = opponentBoard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, ArrayList<Ship>> getAllAccessShips() {
        return allAccessShips;
    }

    public void setAllAccessShips(HashMap<Integer, ArrayList<Ship>> allAccessShips) {
        this.allAccessShips = allAccessShips;
    }

    public void setSuperBomb(int superBomb) {
        this.superBomb = superBomb;
    }

    public int getSuperBomb() {
        return superBomb;
    }
}
