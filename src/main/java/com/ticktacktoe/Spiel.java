package com.ticktacktoe;

import java.util.Scanner;

public class Spiel {
    private final char[][] board;
    private char currentPlayer;

    public Spiel() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // fill board with '-'
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }
    
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char[][] getBoard() {
        return board;
    }

    // make a move
    public boolean playMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    // switch players
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // check win
    public boolean checkWin() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                    board[i][1] == currentPlayer &&
                    board[i][2] == currentPlayer) {
                return true;
            }
        }
        // check cols
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer &&
                    board[1][j] == currentPlayer &&
                    board[2][j] == currentPlayer) {
                return true;
            }
        }
        // check diagonals
        if (board[0][0] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][2] == currentPlayer) {
            return true;
        }
        return board[0][2] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][0] == currentPlayer;
    }

    // check if board is full
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // run the game
    public static void main(String[] args) {
        Spiel game = new Spiel();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tic Tac Toe Game!");
        game.printBoard();

        while (true) {
            System.out.println("Player " + game.getCurrentPlayer() + ", enter row and col (0-2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.playMove(row, col)) {
                game.printBoard();

                if (game.checkWin()) {
                    System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                    break;
                } else if (game.isBoardFull()) {
                    System.out.println("It's a tie!");
                    break;
                }

                game.switchPlayer();
            } else {
                System.out.println("Invalid move, try again.");
            }
        }

        scanner.close();
    }
}
