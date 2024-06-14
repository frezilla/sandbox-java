package eu.frezilla.maze;

public final class Cell {
    
    private Wall eastWall;
    private Wall northWall;
    private Wall southWall;
    private Wall westWall;
    
    public Cell() {
        this.eastWall = Wall.CLOSE;
        this.northWall = Wall.CLOSE;
        this.southWall = Wall.CLOSE;
        this.westWall = Wall.CLOSE;
    }
    
    public Wall getEastWall() {
        return eastWall;
    }
    
    public Wall getNortWall() {
        return northWall;
    }
    
    public Wall getSouthWall() {
        return southWall;
    }
    
    public Wall getWestWall() {
        return westWall;
    }
}
