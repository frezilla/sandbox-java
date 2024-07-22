package eu.frezilla.maze.generator;

import eu.frezilla.maze.core.Cell;
import java.util.Objects;

final class IterativeCell {

    private final Cell cell;
    private final int i;
    private final int j;
    
    public IterativeCell(Cell cell, int i, int j) {
        this.cell = Objects.requireNonNull(cell, "La cellule n'est pas renseignée");
        this.i = i;
        this.j = j;
    }
    
    public Cell getCell() {
        return cell;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    
}
