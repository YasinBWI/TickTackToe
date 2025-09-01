package com.ticktacktoe;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

    
    @Test
    void testPlayMoveOutOfBounds() {
        Spiel g = new Spiel();
        assertFalse(g.playMove(-1, 0));
        assertFalse(g.playMove(3, 3));
    }

    @Test
    void testSwitchPlayerTwiceBackToX() {
        Spiel g = new Spiel();
        g.switchPlayer();
        g.switchPlayer();
        assertEquals('X', g.getCurrentPlayer());
    }

    @Test
    void testWinColumn() {
        Spiel g = new Spiel();
        g.playMove(0,0);
        g.playMove(1,0);
        g.playMove(2,0);
        assertTrue(g.checkWin());
    }

    @Test
    void testWinDiagonal() {
        Spiel g = new Spiel();
        g.playMove(0,0);
        g.playMove(1,1);
        g.playMove(2,2);
        assertTrue(g.checkWin());
    }

    @Test
    void testNoWinYet() {
        Spiel g = new Spiel();
        g.playMove(0,0);
        g.playMove(0,1);
        assertFalse(g.checkWin());
    }

    @Test
    void testBoardNotFull() {
        Spiel g = new Spiel();
        assertFalse(g.isBoardFull());
    }

    @Test
    void testCurrentPlayerStartsX() {
        Spiel g = new Spiel();
        assertEquals('X', g.getCurrentPlayer());
    }

    @Test
    void testCurrentPlayerAfterSwitchIsO() {
        Spiel g = new Spiel();
        g.switchPlayer();
        assertEquals('O', g.getCurrentPlayer());
    }

    @Test
    void testBoardDimensions() {
        Spiel g = new Spiel();
        char[][] b = g.getBoard();
        assertEquals(3, b.length);
        for (int i = 0; i < 3; i++) assertEquals(3, b[i].length);
    }

    @Test
    void testPrintBoardInitialHasDashes() {
        Spiel g = new Spiel();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream orig = System.out;
        System.setOut(new PrintStream(out));
        g.printBoard();
        System.setOut(orig);
        String s = out.toString();
        assertTrue(s.contains("-"));
    }

    @Test
    void testPrintBoardAfterMoveShowsX() {
        Spiel g = new Spiel();
        g.playMove(0,0);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream orig = System.out;
        System.setOut(new PrintStream(out));
        g.printBoard();
        System.setOut(orig);
        String s = out.toString();
        assertTrue(s.contains("X"));
    }

}
