class Vertex {

    int x;
    int y;

    Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean equals(final Vertex otherVertex) {
        return this.x == otherVertex.x && this.y == otherVertex.y;
    }
}
