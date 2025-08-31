package com.ticktacktoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpielTest {

    @Test
    void testInitialBoardIsEmpty() {
        Spiel game = new Spiel();
        char[][] board = game.getBoard();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                assertEquals('-', board[i][j]);
    }

    @Test
    void testPlayMove() {
        Spiel game = new Spiel();
        assertTrue(game.playMove(0, 0));
        assertEquals('X', game.getBoard()[0][0]);
        assertFalse(game.playMove(0, 0));
    }

    @Test
    void testSwitchPlayer() {
        Spiel game = new Spiel();
        char first = game.getCurrentPlayer();
        game.switchPlayer();
        char second = game.getCurrentPlayer();
        assertNotEquals(first, second);
    }

    @Test
    void testWinRow() {
        Spiel game = new Spiel();
        game.playMove(0,0);
        game.playMove(1,0);
        game.playMove(0,1);
        game.playMove(1,1);
        game.playMove(0,2);
        assertTrue(game.checkWin());
    }


    @Test
    void testBoardFull() {
        Spiel game = new Spiel();
        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++)
                game.getBoard()[i][j] = 'X';
        assertTrue(game.isBoardFull());
    }
}
