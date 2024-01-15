import org.example.BoardElements;
import org.example.Game;
import org.example.exceptions.WrongBoardSize;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;
    private List<List<BoardElements>> board = new ArrayList<>();

    private int startX;
    private int startY;
    private int stopX;
    private int stopY;
    private int pawnPositionX;
    private int pawnPositionY;
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String expectedSuccessMessage = """
            YOU WON!
                .--.
               |o_o |
               |:_/ |
              //   \\ \\
             (|     | )
            /'\\_   _/`\\
            \\___)=(___/""";


    @Test
    void test_should_work() {
        assertTrue(true);
    }

    @Test
    void boardShouldHaveDesiredSize() {
        List<Integer> possibleSizes = List.of(4, 8, 10, 20);

        possibleSizes.forEach(size -> {
            game = new Game(size);
            board = game.getBoardElementsList();

            assertEquals(size, board.size());
        });
    }

    @Test
    void boardSizeLessThan_4_and_boardSizeHigherThan_20_shouldBeForbidden() {
        List<Integer> forbiddenSizes = List.of(-2, 0, 1, 2, 3, 21, 50);

        forbiddenSizes.forEach(size -> {
            assertThrows(WrongBoardSize.class, () -> {
                game = new Game(size);
            });
        });

    }

    @Test
    void startShouldBeOnTheEdgesOnTheBoard() {
        int boardSize = 8;
        game = new Game(boardSize);
        board = game.getBoardElementsList();
        startX = game.getStartPositionList().get(0);
        startY = game.getStartPositionList().get(1);

        boolean isOnTheEdge = startX == 0 || startX == boardSize - 1 || startY == 0 || startY == boardSize - 1;

        assertTrue(isOnTheEdge);
    }

    @Test
    void stopShouldBeOnTheEdgesOnTheBoard() {
        int boardSize = 8;
        game = new Game(boardSize);
        board = game.getBoardElementsList();
        stopX = game.getStopPositionList().get(0);
        stopY = game.getStopPositionList().get(1);

        boolean isOnTheEdge = stopX == 0 || stopX == boardSize - 1 || stopY == 0 || stopY == boardSize - 1;

        assertTrue(isOnTheEdge);
    }

    @Test
    void boardShouldHaveAtLeast_barriers_amount_size_as_board_size() {
        List<Integer> possibleSizes = List.of(4, 5, 6, 7, 8, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        possibleSizes.forEach(size -> {
            game = new Game(size);
            board = game.getBoardElementsList();
            int barriersAmount = 0;

            for (List<BoardElements> row : board) {
                for (BoardElements position : row) {
                    if (position == BoardElements.EMPTY) {
                        barriersAmount++;
                    }
                }
            }
            assertTrue(size <= barriersAmount);
        });
    }

    @Test
    void boardShouldHaveOnly_1_START() {
        List<Integer> possibleSizes = List.of(4, 5, 6, 7, 8, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        possibleSizes.forEach(size -> {
            game = new Game(size);
            board = game.getBoardElementsList();
            int barriersAmount = 0;

            for (List<BoardElements> row : board) {
                for (BoardElements position : row) {
                    if (position == BoardElements.START) {
                        barriersAmount++;
                    }
                }
            }
            assertEquals(1, barriersAmount);
        });
    }

    @Test
    void boardShouldHaveOnly_1_STOP() {
        List<Integer> possibleSizes = List.of(4, 5, 6, 7, 8, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        possibleSizes.forEach(size -> {
            game = new Game(size);
            board = game.getBoardElementsList();
            int barriersAmount = 0;

            for (List<BoardElements> row : board) {
                for (BoardElements position : row) {
                    if (position == BoardElements.STOP) {
                        barriersAmount++;
                    }
                }
            }
            assertEquals(1, barriersAmount);
        });
    }

    @RepeatedTest(5)
    void moveLeft_IsAvailableWhen_NoBarrier_Or_EndOfBoard() {
        int boardSize = 8;
        game = new Game(boardSize);
        board = game.getBoardElementsList();
        startX = game.getStartPositionList().get(0);
        startY = game.getStartPositionList().get(1);
        pawnPositionX = game.getPawnX();
        pawnPositionY = game.getPawnY();

        if (pawnPositionY - 1 >= 0 && pawnPositionY - 1 < boardSize) {
            if (board.get(pawnPositionX).get(pawnPositionY - 1) == BoardElements.STOP) {
                System.setOut(new PrintStream(outputStreamCaptor));
                game.moveLeft();
                System.setOut(System.out);
                String consoleOutput = outputStreamCaptor.toString().trim();
                assertEquals(expectedSuccessMessage, consoleOutput);
            } else if (board.get(pawnPositionX).get(pawnPositionY - 1) == BoardElements.START) {
                game.moveLeft();
                assertEquals(BoardElements.START, board.get(pawnPositionX).get(pawnPositionY));
                assertEquals(BoardElements._OOO_, board.get(pawnPositionX).get(pawnPositionY - 1));
            } else {
                game.moveLeft();
                assertEquals(BoardElements.START, board.get(pawnPositionX).get(pawnPositionY));
                assertEquals(BoardElements._OOO_, board.get(pawnPositionX).get(pawnPositionY - 1));
            }
        } else {
            System.setOut(new PrintStream(outputStreamCaptor));
            game.moveLeft();
            System.setOut(System.out);
            String expectedMessage = "Move left is not possible";
            String consoleOutput = outputStreamCaptor.toString().trim();
            assertTrue(consoleOutput.contains(expectedMessage));
        }
    }

    @RepeatedTest(5)
    void moveRight_IsAvailableWhen_NoBarrier_Or_EndOfBoard() {
        int boardSize = 8;
        game = new Game(boardSize);
        board = game.getBoardElementsList();
        startX = game.getStartPositionList().get(0);
        startY = game.getStartPositionList().get(1);
        pawnPositionX = game.getPawnX();
        pawnPositionY = game.getPawnY();

        if (pawnPositionY + 1 >= 0 && pawnPositionY + 1 < boardSize) {
            if (board.get(pawnPositionX).get(pawnPositionY + 1) == BoardElements.STOP) {
                System.setOut(new PrintStream(outputStreamCaptor));
                game.moveRight();
                System.setOut(System.out);
                String consoleOutput = outputStreamCaptor.toString().trim();
                assertEquals(expectedSuccessMessage, consoleOutput);
            } else if (board.get(pawnPositionX).get(pawnPositionY + 1) == BoardElements.START) {
                game.moveRight();
                assertEquals(BoardElements.START, board.get(pawnPositionX).get(pawnPositionY));
                assertEquals(BoardElements._OOO_, board.get(pawnPositionX).get(pawnPositionY + 1));
            } else {
                game.moveRight();
                assertEquals(BoardElements.START, board.get(pawnPositionX).get(pawnPositionY));
                assertEquals(BoardElements._OOO_, board.get(pawnPositionX).get(pawnPositionY + 1));
            }
        } else {
            System.setOut(new PrintStream(outputStreamCaptor));
            game.moveRight();
            System.setOut(System.out);
            String expectedMessage = "Move right is not possible";
            String consoleOutput = outputStreamCaptor.toString().trim();
            assertTrue(consoleOutput.contains(expectedMessage));
        }
    }

    @RepeatedTest(5)
    void moveUp_IsAvailableWhen_NoBarrier_Or_EndOfBoard() {
        int boardSize = 8;
        game = new Game(boardSize);
        board = game.getBoardElementsList();
        startX = game.getStartPositionList().get(0);
        startY = game.getStartPositionList().get(1);
        pawnPositionX = game.getPawnX();
        pawnPositionY = game.getPawnY();

        if (pawnPositionX - 1 >= 0 && pawnPositionX - 1 < boardSize) {
            if (board.get(pawnPositionX - 1).get(pawnPositionY) == BoardElements.STOP) {
                System.setOut(new PrintStream(outputStreamCaptor));
                game.moveUp();
                System.setOut(System.out);
                String consoleOutput = outputStreamCaptor.toString().trim();
                assertEquals(expectedSuccessMessage, consoleOutput);
            } else if (board.get(pawnPositionX - 1).get(pawnPositionY) == BoardElements.START) {
                game.moveUp();
                assertEquals(BoardElements.START, board.get(pawnPositionX).get(pawnPositionY));
                assertEquals(BoardElements._OOO_, board.get(pawnPositionX - 1).get(pawnPositionY));
            } else {
                game.moveUp();
                assertEquals(BoardElements.START, board.get(pawnPositionX).get(pawnPositionY));
                assertEquals(BoardElements._OOO_, board.get(pawnPositionX - 1).get(pawnPositionY));
            }
        } else {
            System.setOut(new PrintStream(outputStreamCaptor));
            game.moveUp();
            System.setOut(System.out);
            String expectedMessage = "Move up is not possible";
            String consoleOutput = outputStreamCaptor.toString().trim();
            assertTrue(consoleOutput.contains(expectedMessage));
        }
    }

    @RepeatedTest(5)
    void moveDown_IsAvailableWhen_NoBarrier_Or_EndOfBoard() {
        int boardSize = 8;
        game = new Game(boardSize);
        board = game.getBoardElementsList();
        startX = game.getStartPositionList().get(0);
        startY = game.getStartPositionList().get(1);
        pawnPositionX = game.getPawnX();
        pawnPositionY = game.getPawnY();

        if (pawnPositionX + 1 >= 0 && pawnPositionX + 1 < boardSize) {
            if (board.get(pawnPositionX + 1).get(pawnPositionY) == BoardElements.STOP) {
                System.setOut(new PrintStream(outputStreamCaptor));
                game.moveDown();
                System.setOut(System.out);
                String consoleOutput = outputStreamCaptor.toString().trim();
                assertEquals(expectedSuccessMessage, consoleOutput);
            } else if (board.get(pawnPositionX + 1).get(pawnPositionY) == BoardElements.START) {
                game.moveDown();
                assertEquals(BoardElements.START, board.get(pawnPositionX).get(pawnPositionY));
                assertEquals(BoardElements._OOO_, board.get(pawnPositionX + 1).get(pawnPositionY));
            } else {
                game.moveDown();
                assertEquals(BoardElements.START, board.get(pawnPositionX).get(pawnPositionY));
                assertEquals(BoardElements._OOO_, board.get(pawnPositionX + 1).get(pawnPositionY));
            }
        } else {
            System.setOut(new PrintStream(outputStreamCaptor));
            game.moveDown();
            System.setOut(System.out);
            String expectedMessage = "Move down is not possible";
            String consoleOutput = outputStreamCaptor.toString().trim();
            assertTrue(consoleOutput.contains(expectedMessage));
        }
    }

    @RepeatedTest(5)
    public void startAndStop_shouldNotBe_nextToEachOther() {
        int boardSize = 8;
        game = new Game(boardSize);
        board = game.getBoardElementsList();
        startX = game.getStartPositionList().get(0);
        startY = game.getStartPositionList().get(1);
        stopX = game.getStopPositionList().get(0);
        stopY = game.getStopPositionList().get(1);

        boolean isNextToEachOther = (startX - stopX == 0 && Math.abs(startY - stopY) == 1) || (startY - stopY == 0 && Math.abs(startX - stopY) == 1);
        assertFalse(isNextToEachOther);
    }
}
