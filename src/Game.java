import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Game {
    private Player playerOne;
    private Player playerTwo;

    public Game() {
    }

    public void playGame() {

        clearScreen();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Введите первого игрока: ");
            String nameOne = br.readLine();
            playerOne = new Player(nameOne);
            setGame(playerOne, br);

            System.out.print("Введите второго игрока: ");
            String nameTwo = br.readLine();
            playerTwo = new Player(nameTwo);
            setGame(playerTwo, br);


            battle(playerOne, playerTwo, br);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setGame(Player player, BufferedReader br) {
        while (!player.getAllAccessShips().isEmpty()) {
            System.out.println("Разместите ваши корабли на арене");
            player.getPlayground().showBoard();
            System.out.println("Вам доступны:");

            Iterator<Integer> iterator = player.getAllAccessShips().keySet().iterator();

            while (iterator.hasNext()) {

                Integer shipSize = iterator.next();

                if (!player.getAllAccessShips().get(shipSize).isEmpty()) {

                    int countOfShips = player.getAllAccessShips().get(shipSize).size();

                    if (countOfShips == 1) {
                        System.out.println("У вас " + countOfShips + " " + shipSize + "-ый корабль");
                    } else {
                        System.out.println("У вас " + countOfShips + " " + shipSize + "x корабля");
                    }

                }
            }

            System.out.println();

            try {
                setShips(player, br);
            } catch (Exception e) {
                clearScreen();
                System.out.println("\u001B[31m" + "Ошибка ввода некорректные данные" + "\u001B[0m");
                continue;
            }

            clearScreen();
        }

    }

    private static void clearScreen() {
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
    }

    private void setShips(Player player, BufferedReader br) throws IOException {
        System.out.print("Введите количество палуб корабля, который вы хотите разместить: ");
        int sizeOfShip = Integer.parseInt(br.readLine());

        System.out.print("Введите координату по вертикали координату по горизонтали вашего корабля: ");
        String[] str = br.readLine().split(" ");
        int x = Integer.parseInt(str[0]);
        int y = Integer.parseInt(str[1]);

        Ship ship = player.getAllAccessShips().get(sizeOfShip).get(0);

        if (sizeOfShip > 1) {
            System.out.print("Введите букву г или в в зависимости от того, как вы хотите расположить ваш корабль(вертикально || горизонтально): ");
            String position = br.readLine();


            if (position.equals("г")) {
                for (int i = y - sizeOfShip / 2; i < y + sizeOfShip / 2 + 1; ++i) {
                    player.getPlayground().addShipInSet(new Coordinate(x, i));
                    player.getPlayground().getBoard()[x][i] = 'S';
                }

                ship.setPosition("г");
            } else {
                if (!position.equals("в")) {
                    throw new IOException();
                }

                for (int i = x - sizeOfShip / 2; i < x + sizeOfShip / 2 + 1; ++i) {
                    player.getPlayground().addShipInSet(new Coordinate(x, i));
                    player.getPlayground().getBoard()[i][y] = 'S';
                }

                ship.setPosition("в");
            }

            ship.setPosition(position);
        } else {
            player.getPlayground().addShipInSet(new Coordinate(x, y));
            player.getPlayground().getBoard()[x][y] = 'S';
            ship.setPosition("в");
        }

        player.getAllAccessShips().get(sizeOfShip).remove(0);

        if (player.getAllAccessShips().get(sizeOfShip).isEmpty()) {
            player.getAllAccessShips().remove(sizeOfShip);
        }

    }

    private void battle(Player playerOne, Player playerTwo, BufferedReader br) throws IOException {

        while (!checkTheWinner(playerOne, playerTwo)) {

            playerAttack(playerOne, playerTwo, br);


            if (checkTheWinner(playerOne, playerTwo))
                break;


            playerAttack(playerTwo, playerOne, br);


            if (checkTheWinner(playerOne, playerTwo))
                break;

        }
    }


    private void playerAttack(Player playerOne, Player playerTwo, BufferedReader br) throws IOException {

        System.out.println("Ход игрока " + playerOne.getName() + " ");
        playerOne.getOpponentBoard().showBoard();

        System.out.print("Введите координаты для удара: ");
        String[] str = br.readLine().split(" ");

        int x = Integer.parseInt(str[0]);
        int y = Integer.parseInt(str[1]);
        Coordinate coordinate = new Coordinate(x, y);

        if (playerTwo.getPlayground().getShipSet().contains(coordinate)) {
            clearScreen();
            System.out.println("\u001B[32m" + "Есть попадание!" + "\u001B[0m");
            playerOne.getOpponentBoard().getBoard()[x][y] = 'H';
            playerTwo.getPlayground().getShipSet().remove(coordinate);
        } else {
            clearScreen();
            System.out.println("\u001B[32m" + "Промах" + "\u001B[0m");
        }

    }

    private boolean checkTheWinner(Player playerOne, Player playerTwo) {

        if (playerOne.getPlayground().getShipSet().isEmpty() && playerTwo.getPlayground().getShipSet().isEmpty()) {
            System.out.println("\u001B[33m" + "Ничья" + "\u001B[0m");
            return true;
        }

        if (playerOne.getPlayground().getShipSet().isEmpty()) {
            System.out.println("\u001B[33m" + "Выиграл игрок" + playerOne.getName() + "\u001B[0m");
            return true;
        }

        if (playerTwo.getPlayground().getShipSet().isEmpty()) {
            System.out.println("\u001B[33m" + "Выиграл игрок" + playerTwo.getName() + "\u001B[0m");
            return true;
        }

        return false;

    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public void setPlayerOne(Player player) {
        this.playerOne = player;
    }

    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    public void setPlayerTwo(Player player) {
        this.playerTwo = player;
    }
}
