package org.example;

import org.example.exceptions.WrongBoardSize;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends JFrame {
    private final int boardSize;
    private List<Integer> startPositionList = new ArrayList<>();
    private List<Integer> stopPositionList = new ArrayList<>();
    private final List<List<BoardElements>> boardElementsList = new ArrayList<>();
    Random randomPosition = new Random();
    SecureRandom secureRandom = new SecureRandom();
    private int pawnX;
    private int pawnY;

    public Game(int boardSize) {
        if (boardSize < 4 || boardSize > 20) {
            try {
                throw new WrongBoardSize("Board needs to have at least 4x4 size but not more than 20x20.");
            } catch (WrongBoardSize e) {
                throw new WrongBoardSize(e.getMessage());
            }
        } else {
            this.boardSize = boardSize;
        }
        this.createBoard();
        this.generateStart();
        this.generateStop();
        this.setBarriersPosition();
        printBoard();
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new MyKeyListener());
        setVisible(true);
    }

    private void createBoard() {
        for (int i = 0; i < boardSize; i++) {
            List<BoardElements> row = new ArrayList<>();
            for (int j = 0; j < boardSize; j++) {
                row.add(BoardElements.EMPTY);
            }
            boardElementsList.add(row);
        }
    }

    private void generateStart() {
        int randomStartX = randomPosition.nextInt(boardSize);
        int randomStartY;
        if (randomStartX == 0 || randomStartX == boardSize - 1) {
            randomStartY = randomPosition.nextInt(boardSize);
        } else {
            randomStartY = randomPosition.nextBoolean() ? 0 : boardSize - 1;
        }
        this.startPositionList = List.of(randomStartX, randomStartY);
        this.pawnX = randomStartX;
        this.pawnY = randomStartY;
        boardElementsList.get(randomStartX).set(randomStartY, BoardElements.START);
    }

    private void generateStop() {
        int randomStopX = randomPosition.nextInt(boardSize);
        int randomStopY = 0;
        while(randomStopX == this.startPositionList.get(0)){
            randomStopX = randomPosition.nextInt(boardSize);
        }
        if (this.startPositionList.get(0) == 0) {
            randomStopX = boardSize - 1;
            randomStopY = randomPosition.nextInt(boardSize);
        } else if (this.startPositionList.get(0) == boardSize - 1) {
            randomStopX = 0;
            randomStopY = randomPosition.nextInt(boardSize);
        } else if (this.startPositionList.get(1) == 0){
            randomStopY = boardSize - 1;
        }
        this.stopPositionList = List.of(randomStopX, randomStopY);
        boardElementsList.get(randomStopX).set(randomStopY, BoardElements.STOP);
    }

    private void setBarriersPosition() {
        int randomBarrierX;
        int randomBarrierY;
        for (int i = 0; i <= 100; i++) {
            randomBarrierX = randomPosition.nextInt(boardSize);
            randomBarrierY = secureRandom.nextInt(boardSize);
            if (startPositionList.get(0) == randomBarrierX || startPositionList.get(1) == randomBarrierY || stopPositionList.get(0) == randomBarrierX || stopPositionList.get(1) == randomBarrierY) {
                continue;
            }
            boardElementsList.get(randomBarrierX).set(randomBarrierY, BoardElements.__X__);
        }
    }

    public void moveDown() {
        if (pawnX + 1 < boardSize && boardElementsList.get(pawnX + 1).get(pawnY) == BoardElements.STOP) {
            printSuccessMessageAndStopTheProgram();
        } else if (pawnX + 1 < boardSize && boardElementsList.get(pawnX + 1).get(pawnY) == BoardElements.EMPTY || pawnX + 1 < boardSize && boardElementsList.get(pawnX + 1).get(pawnY) == BoardElements.START) {
            if (boardElementsList.get(pawnX).get(pawnY) != BoardElements.START) {
                boardElementsList.get(pawnX).set(pawnY, BoardElements.EMPTY);
            }
            boardElementsList.get(pawnX + 1).set(pawnY, BoardElements._OOO_);
            pawnX++;
            this.printBoard();
        } else {
            System.out.println("Move down is not possible\n");
        }
    }

    public void moveUp() {
        if (pawnX + 1 < boardSize && pawnX - 1 >= 0 && boardElementsList.get(pawnX - 1).get(pawnY) == BoardElements.STOP) {
            printSuccessMessageAndStopTheProgram();
        } else if (pawnX - 1 >= 0 && boardElementsList.get(pawnX - 1).get(pawnY) == BoardElements.EMPTY || pawnX - 1 >= 0 && boardElementsList.get(pawnX - 1).get(pawnY) == BoardElements.START) {
            if (boardElementsList.get(pawnX).get(pawnY) != BoardElements.START) {
                boardElementsList.get(pawnX).set(pawnY, BoardElements.EMPTY);
            }
            boardElementsList.get(pawnX - 1).set(pawnY, BoardElements._OOO_);
            pawnX--;
            this.printBoard();
        } else {
            System.out.println("Move up is not possible\n");
        }
    }

    public void moveRight() {
        if (pawnX + 1 < boardSize && pawnY + 1 < boardSize && boardElementsList.get(pawnX).get(pawnY + 1) == BoardElements.STOP) {
            printSuccessMessageAndStopTheProgram();
        } else if (pawnY + 1 < boardSize && boardElementsList.get(pawnX).get(pawnY + 1) == BoardElements.EMPTY || pawnY + 1 < boardSize && boardElementsList.get(pawnX).get(pawnY + 1) == BoardElements.START) {
            if (boardElementsList.get(pawnX).get(pawnY) != BoardElements.START) {
                boardElementsList.get(pawnX).set(pawnY, BoardElements.EMPTY);
            }
            boardElementsList.get(pawnX).set(pawnY + 1, BoardElements._OOO_);
            pawnY++;
            this.printBoard();
        } else {
            System.out.println("Move right is not possible\n");
        }
    }

    public void moveLeft() {
        if (pawnY - 1 < boardSize && pawnY - 1 >= 0 && boardElementsList.get(pawnX).get(pawnY - 1) == BoardElements.STOP) {
            printSuccessMessageAndStopTheProgram();
        } else if (pawnY - 1 >= 0 && boardElementsList.get(pawnX).get(pawnY - 1) == BoardElements.EMPTY || pawnY - 1 >= 0 && boardElementsList.get(pawnX).get(pawnY - 1) == BoardElements.START) {
            if (boardElementsList.get(pawnX).get(pawnY) != BoardElements.START) {
                boardElementsList.get(pawnX).set(pawnY, BoardElements.EMPTY);
            }
            boardElementsList.get(pawnX).set(pawnY - 1, BoardElements._OOO_);
            pawnY--;
            this.printBoard();
        } else {
            System.out.println("Move left is not possible\n");
        }
        printBoard();
    }

    private void printBoard() {
        for (List<BoardElements> row : boardElementsList) {
            for (BoardElements element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printSuccessMessageAndStopTheProgram() {
        System.out.println("""
                YOU WON!
                    .--.
                   |o_o |
                   |:_/ |
                  //   \\ \\
                 (|     | )
                /'\\_   _/`\\
                \\___)=(___/""");
        System.exit(0);
    }

    public List<List<BoardElements>> getBoardElementsList() {
        return boardElementsList;
    }

    public List<Integer> getStartPositionList() {
        return startPositionList;
    }

    public List<Integer> getStopPositionList() {
        return stopPositionList;
    }

    public int getPawnX() {
        return pawnX;
    }

    public int getPawnY() {
        return pawnY;
    }

    public class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            switch (keyCode) {
                case KeyEvent.VK_UP:
                    moveUp();
                    break;
                case KeyEvent.VK_DOWN:
                    moveDown();
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRight();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
