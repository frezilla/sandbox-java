package eu.frezilla.maze;

import java.util.ArrayList;
import java.util.List;

public final class Maze {
    
    private final int height;
    private final int width;
    private final List<Cell> cells;
    
    public Maze(int width, int height) {
        if (width < 1) throw new IllegalArgumentException("La largeur du labyrinthe doit être strictement positive");
        if (height < 1) throw new IllegalArgumentException("La hauteur du labyrinthe doit être strictement positive");
        this.width = width;
        this.height = height;
        this.cells = new ArrayList<>(height * width);
        
        for (int i = 0; i < this.width * this.height; i++) {
            this.cells.add(new Cell());
        }
    }
    
    public Cell getCell(int i, int j) {
        if (i <0 || i >= width) throw new ArrayIndexOutOfBoundsException();
        if (j < 0 || j >= height) throw new ArrayIndexOutOfBoundsException();
        
        int index = j * width + i;
        return cells.get(index);
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
}
