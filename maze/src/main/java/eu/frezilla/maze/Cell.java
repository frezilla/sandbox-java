package eu.frezilla.maze;

public final class Cell {
    
    private final Wall eastWall;
    private final Wall northWall;
    private final Wall southWall;
    private final Wall westWall;
    
    public Cell() {
        this.eastWall = new Wall();
        this.northWall = new Wall();
        this.southWall = new Wall();
        this.westWall = new Wall();
    }
    
    public Wall getEastWall() {
        return eastWall;
    }

    public Wall getNorthWall() {
        return northWall;
    }

    public Wall getSouthWall() {
        return southWall;
    }

    public Wall getWestWall() {
        return westWall;
    }
    
}
