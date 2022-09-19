import java.util.Scanner;
import java.lang.*;


public class SimpleTicTacToe {

        public static void main(String[] args) {

            Scanner scan = new Scanner(System.in);
            String game = "         ";
            //count X O and empty cells
            int score_X = 0; //if X wins
            int score_O = 0; //if O wins
            int timesX = 0; //count X
            int timesO = 0; //count O
            int emptyCells = 0; //count _
            String gameStatus = "";
            int first; //coordinates
            int second; //coordinates
            boolean game_not_finished = true;
            int turnsPlayed = 1;

            //create 3x3 grid
            int y = 0;
            char[][] grid = new char[3][3];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = game.charAt(y++);
                }
            }

            System.out.println("Coordinates should be from 1 to 3 with a space between the numbers \nexample:2 3\n");
            while (game_not_finished) {
                System.out.println("Enter the coordinates: ");
                while (true) {
                    String input = scan.nextLine();
                    //if digit
                    if (input.matches("[0-9]{1}.[0-9]{1}")) {
                        //if correct digit
                        if (input.matches("[1-3]{1}.[1-3]{1}")) {
                            //try get 2 digits from String
                            try {
                                String[] pieces = input.split(" ");
                                first = Integer.parseInt(pieces[0]);
                                second = Integer.parseInt(pieces[1]);
                                //check if cell is empty
                                char cell = grid[first - 1][second - 1];
                                if (cell == ' ') {
                                    if (turnsPlayed % 2 == 1) {
                                        grid[first - 1][second - 1] = 'X';
                                        turnsPlayed++;
                                    } else {
                                        grid[first - 1][second - 1] = 'O';
                                        turnsPlayed++;
                                    }
                                    break;
                                } else {
                                    System.out.println("This cell is occupied! Choose another one!");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(e);
                            }
                        } else {
                            System.out.println("Coordinates should be from 1 to 3!");
                            //continue;
                        }
                    } else
                        System.out.println("You should enter numbers!");
                    System.out.println("Enter the coordinates: ");
                }

                if (turnsPlayed == 10) {
                    game_not_finished = false;
                }
                //print grid
                System.out.println("---------");
                for (char[] row : grid) {
                    System.out.print("| ");
                    for (char cell : row) {
                        System.out.print(cell + " ");
                    }
                    System.out.println("|");
                }
                System.out.println("---------");

                //count X O _
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        switch (grid[i][j]) {
                            case ' ':
                                emptyCells += 1;
                                break;
                            case 'X':
                                timesX += 1;
                                break;
                            case 'O':
                                timesO += 1;
                                break;
                            default:
                                System.out.println("Error - that's never happening");
                                break;
                        }
                    }
                }

                //rows and columns
                for (int i = 0; i < grid.length; i++) {
                    if (grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2] && grid[i][0] == 'X') {
                        ++score_X;
                    } else if (grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2] && grid[i][0] == 'O') {
                        ++score_O;
                    }
                    if (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i] && grid[0][i] == 'X') {
                        ++score_X;
                    } else if (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i] && grid[0][i] == 'O') {
                        ++score_O;
                    }
                }

                //for the diagonal
                int xInARow = 0;
                int oInARow = 0;

                for (int i = 0; i < grid.length; i++) {
                    if (grid[i][i] == 'X') {
                        xInARow++;
                    } else if (grid[i][i] == 'O') {
                        oInARow++;
                    }
                }

                if (xInARow == 3) {
                    score_X++;
                } else if (oInARow == 3) {
                    score_O++;
                } else {
                    xInARow = 0;
                    oInARow = 0;
                }

                //for the inverse diagonal
                for (int i = grid[0].length - 1, j = 0; i >= 0 ; i--, j++) {
                    if (grid[i][j] == 'X') {
                        xInARow++;
                    } else if (grid[i][j] == 'O') {
                        oInARow++;
                    }
                }

                if (xInARow == 3) {
                    score_X++;
                } else if (oInARow == 3) {
                    score_O++;
                }

                if (score_X == 1) {
                    gameStatus = "X wins";
                    game_not_finished = false;
                } else if (score_O == 1) {
                    gameStatus = "O wins";
                    game_not_finished = false;
                } else if (score_O == 0 && score_X == 0 && emptyCells > 0) {
                    gameStatus = "Game not finished";
                    gameStatus = "Draw";
                }
                if (score_O == 0 && score_X == 0 && emptyCells == 0) {
                    gameStatus = "Draw";
                    game_not_finished = false;
                }

            }
            System.out.println(gameStatus);

        } //main
    }


