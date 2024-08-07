package eu.frezilla.maze.core;

public final class Cell {

    private Cell eastCell;
    private Cell northCell;
    private Cell southCell;
    private Cell westCell;

    public Cell() {
        this.eastCell = null;
        this.northCell = null;
        this.southCell = null;
        this.westCell = null;
    }

    public Cell getEastCell() {
        return eastCell;
    }

    public Cell getNorthCell() {
        return northCell;
    }

    public Cell getSouthCell() {
        return southCell;
    }

    public Cell getWestCell() {
        return westCell;
    }
    
    public boolean hasEastCell() {
        return eastCell != null;
    }
    
    public boolean hasNorthCell() {
        return northCell != null;
    }
    
    public boolean hasSouthCell() {
        return southCell != null;
    }
    
    public boolean hasWestCell() {
        return westCell != null;
    }

    void setEastCell(Cell eastCell) {
        this.eastCell = eastCell;
    }

    void setNorthCell(Cell northCell) {
        this.northCell = northCell;
    }

    void setSouthCell(Cell southCell) {
        this.southCell = southCell;
    }

    void setWestCell(Cell westCell) {
        this.westCell = westCell;
    }

}
