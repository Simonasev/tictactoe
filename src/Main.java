import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';

        while (true) {
            printBoard();
            int row = -1, col = -1;

            while (true) {
                try {
                    System.out.println("Player " + currentPlayer + ", choose row and column (0, 1 or 2):");
                    row = scanner.nextInt();
                    col = scanner.nextInt();

                    if (row < 0 || row > 2 || col < 0 || col > 2) {
                        System.out.println("Invalid input. You have to choose only numbers 0, 1 or 2.");
                    } else if (board[row][col] != ' ') {
                        System.out.println("This place is already taken. Try again.");
                    } else {
                        break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. You must enter numbers.");
                    scanner.nextLine();
                }
            }

            board[row][col] = currentPlayer;

            // Výhra
            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            // Remíza
            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a tie!");
                break;
            }

            //zmena playera
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||  // riadky
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player))    // stĺpce
                return true;
        }

        // diagonál
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') return false;
        return true;
    }
}