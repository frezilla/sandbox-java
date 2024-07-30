package eu.frezilla.maze.generator;

import eu.frezilla.maze.core.Cell;
import java.util.Objects;

final class IterativeCell {

    private final Cell cell;
    private final int i;
    private final int j;
    private int groupId;
    
    public IterativeCell(Cell cell, int i, int j, int groupId) {
        this.cell = Objects.requireNonNull(cell, "La cellule n'est pas renseignée");
        this.i = i;
        this.j = j;
        this.groupId = groupId;
    }
    
    public Cell getCell() {
        return cell;
    }
    
    public int getGroupId() {
        return groupId;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    
    
}
