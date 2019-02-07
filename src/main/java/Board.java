import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Board is interpreted like this. Hence initial white knight is at (0,0) and black knight is at (7, 7)
 *
 * 7
 * 6
 * 5
 * 4
 * 3
 * 2
 * 1
 * 0
 *     0 1 2 3 4 5 6 7
 */
class Board {

    static final int BOARD_SIZE = 8;
    BigDecimal[][][][][] lastMoveCollisions;
    BigDecimal[][][][][] collisionLess;

    Board(final int n) {
        lastMoveCollisions = new BigDecimal[BOARD_SIZE][BOARD_SIZE][BOARD_SIZE][BOARD_SIZE][n+1];
        collisionLess = new BigDecimal[BOARD_SIZE][BOARD_SIZE][BOARD_SIZE][BOARD_SIZE][n+1];

        collisionLess[0][0][BOARD_SIZE - 1][BOARD_SIZE - 1][0] = new BigDecimal(1);
    }

    List<Vertex> getVerticesReachableByKnight(final Vertex vertex) {

        List<Vertex> vertices = new ArrayList<Vertex>();

        // right top
        if(validVertex(vertex.x + 1, vertex.y + 2))
            vertices.add(new Vertex(vertex.x + 1, vertex.y + 2));

        // top right
        if(validVertex(vertex.x + 2, vertex.y + 1))
            vertices.add(new Vertex(vertex.x + 2, vertex.y + 1));

        // right bottom
        if(validVertex(vertex.x - 1, vertex.y + 2))
            vertices.add(new Vertex(vertex.x - 1, vertex.y + 2));

        // bottom right
        if(validVertex(vertex.x - 2, vertex.y + 1))
            vertices.add(new Vertex(vertex.x - 2, vertex.y + 1));

        // left top
        if(validVertex(vertex.x + 1, vertex.y - 2))
            vertices.add(new Vertex(vertex.x + 1, vertex.y - 2));

        // top left
        if(validVertex(vertex.x + 2, vertex.y - 1))
            vertices.add(new Vertex(vertex.x + 2, vertex.y - 1));

        // left bottom
        if(validVertex(vertex.x - 1, vertex.y - 2))
            vertices.add(new Vertex(vertex.x - 1, vertex.y - 2));

        // bottom left
        if(validVertex(vertex.x - 2, vertex.y - 1))
            vertices.add(new Vertex(vertex.x - 2, vertex.y - 1));

        return vertices;

    }

    private boolean validVertex(final int x, final int y) {
        return (x >= 0 && x <= BOARD_SIZE - 1) && (y >= 0 && y <= BOARD_SIZE - 1);
    }

    void print(final int totalMoves) {
        for (int i = Board.BOARD_SIZE - 1; i >= 0; --i) {
            for (int j = 0; j < Board.BOARD_SIZE; ++j) {
                for (int k = Board.BOARD_SIZE - 1; k >= 0; --k) {
                    for (int l = 0; l < Board.BOARD_SIZE; ++l) {
                        System.out.print(collisionLess[i][j][k][l][totalMoves]);
                        System.out.print(",");
                    }
                    System.out.print(" ");
                }
                System.out.print("  ");
            }
            System.out.println();
            System.out.println();
        }

        System.out.println();

        for (int i = Board.BOARD_SIZE - 1; i >= 0; --i) {
            for (int j = 0; j < Board.BOARD_SIZE; ++j) {
                for (int k = Board.BOARD_SIZE - 1; k >= 0; --k) {
                    for (int l = 0; l < Board.BOARD_SIZE; ++l) {
                        System.out.print(lastMoveCollisions[i][j][k][l][totalMoves]);
                        System.out.print(",");
                    }
                    System.out.print(" ");
                }
                System.out.print("  ");
            }
            System.out.println();
            System.out.println();
        }
    }

}
