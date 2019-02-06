import java.math.BigDecimal;
import java.util.List;

/* 1 move for this solver is 1 move for either player.
   Basically, after each move both white and black knight move from their position.
*/
public class Solver {

    /* Hint : probability after 6 moves is 0.90531081 according to question posted. */
    public static void main(String[] args) {

        int movesToTestFor[] = {1,2,3,6,12,18,20,26,32};

        for(int n : movesToTestFor) {

            Board board = new Board(n);
            simulateMoves(board, n);

            BigDecimal totalCollisionLess = new BigDecimal(0);
            BigDecimal totalStates = new BigDecimal(0);

            for (int i = 0; i < Board.BOARD_SIZE; ++i) {
                for (int j = 0; j < Board.BOARD_SIZE; ++j) {
                    for (int k = 0; k < Board.BOARD_SIZE; ++k) {
                        for (int l = 0; l < Board.BOARD_SIZE; ++l) {

                            if(board.collisionLess[i][j][k][l][n] == null)
                                board.collisionLess[i][j][k][l][n] = new BigDecimal(0);
                            if(board.total[i][j][k][l][n] == null)
                                board.total[i][j][k][l][n] = new BigDecimal(0);

                            totalCollisionLess = totalCollisionLess.add(board.collisionLess[i][j][k][l][n]);
                            totalStates = totalStates.add(board.total[i][j][k][l][n]);
                        }
                    }
                }
            }
            System.out.println("For " + n + " moves");
            System.out.println("Collisionless paths : " + totalCollisionLess);
            System.out.println("Total paths : " + totalStates);
            System.out.println("Probability : " + totalCollisionLess.divide(totalStates, 8, BigDecimal.ROUND_HALF_UP));
            System.out.println();

//            board.print(n);
        }
    }

    private static void simulateMoves(Board board, final int n) {

        if (n > 0) {

            simulateMoves(board, n - 1);

            for(int i = 0 ; i < Board.BOARD_SIZE ; ++i) {
                for (int j = 0; j < Board.BOARD_SIZE; ++j) {
                    for (int k = 0; k < Board.BOARD_SIZE; ++k) {
                        for (int l = 0; l < Board.BOARD_SIZE; ++l) {

                            List<Vertex> whiteVertices = board.getVerticesReachableByKnight(new Vertex(i,j));
                            List<Vertex> blackVertices = board.getVerticesReachableByKnight(new Vertex(k,l));

                            for(Vertex previousWhiteVertex : whiteVertices) {
                                for(Vertex previousBlackVertex : blackVertices) {
                                    if(!previousWhiteVertex.equals(previousBlackVertex)) {

                                        if(board.collisionLess[i][j][k][l][n] == null)
                                            board.collisionLess[i][j][k][l][n] = new BigDecimal(0);

                                        if(board.collisionLess[previousWhiteVertex.x][previousWhiteVertex.y][previousBlackVertex.x][previousBlackVertex.y][n-1] == null)
                                            board.collisionLess[previousWhiteVertex.x][previousWhiteVertex.y][previousBlackVertex.x][previousBlackVertex.y][n-1] = new BigDecimal(0);

                                        board.collisionLess[i][j][k][l][n] = board.collisionLess[i][j][k][l][n].add(board.collisionLess[previousWhiteVertex.x][previousWhiteVertex.y][previousBlackVertex.x][previousBlackVertex.y][n-1]);
                                    }
                                    if(board.total[i][j][k][l][n] == null)
                                        board.total[i][j][k][l][n] = new BigDecimal(0);

                                    if(board.total[previousWhiteVertex.x][previousWhiteVertex.y][previousBlackVertex.x][previousBlackVertex.y][n-1] == null)
                                        board.total[previousWhiteVertex.x][previousWhiteVertex.y][previousBlackVertex.x][previousBlackVertex.y][n-1] = new BigDecimal(0);

                                    board.total[i][j][k][l][n] = board.total[i][j][k][l][n].add(board.total[previousWhiteVertex.x][previousWhiteVertex.y][previousBlackVertex.x][previousBlackVertex.y][n-1]);
                                }
                            }

                            // black and white are on same position.
                            if(i == k && j == l) {
                                board.collisionLess[i][j][k][l][n] = new BigDecimal(0);
                            }
                        }
                    }
                }
            }
        }
    }

}
