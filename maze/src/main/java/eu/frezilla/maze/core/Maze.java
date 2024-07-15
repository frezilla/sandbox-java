package eu.frezilla.maze.core;

import static eu.frezilla.maze.core.Direction.EAST;
import static eu.frezilla.maze.core.Direction.NORTH;
import static eu.frezilla.maze.core.Direction.SOUTH;
import static eu.frezilla.maze.core.Direction.WEST;
import java.util.ArrayList;
import java.util.List;

public final class Maze {

    private final int height;
    private final int width;
    private final List<Cell> cells;

    public Maze(int width, int height) {
        if (width < 1) {
            throw new IllegalArgumentException("La largeur du labyrinthe doit être strictement positive");
        }
        if (height < 1) {
            throw new IllegalArgumentException("La hauteur du labyrinthe doit être strictement positive");
        }
        this.width = width;
        this.height = height;
        this.cells = new ArrayList<>(height * width);

        for (int i = 0; i < this.width * this.height; i++) {
            this.cells.add(new Cell());
        }
    }

    private void changeCrossing(int i, int j, Direction direction, boolean close) {
        Cell cell1 = getCell(i, j);

        if (direction == null) {
            throw new IllegalArgumentException("La direction n'est pas renseignée");
        }

        switch (direction) {
            case EAST: {
                if (i + 1 == getWidth()) {
                    throw new ArrayIndexOutOfBoundsException("La cellule n'a pas de cellule voisine à l'est");
                }
                Cell cell2 = getCell(i + 1, j);
                if (close) {
                    cell1.setEastCell(cell2);
                    cell2.setWestCell(cell1);
                } else {
                    cell1.setEastCell(null);
                    cell2.setWestCell(null);
                }
                break;
            }
            case NORTH: {
                if (j == 0) {
                    throw new ArrayIndexOutOfBoundsException("La cellule n'a pas de cellule voisine au nord");
                }
                Cell cell2 = getCell(i, j - 1);
                if (close) {
                    cell1.setNorthCell(cell2);
                    cell2.setSouthCell(cell1);
                } else {
                    cell1.setNorthCell(null);
                    cell2.setSouthCell(null);
                }
                break;
            }
            case SOUTH: {
                if (j + 1 == getHeight()) {
                    throw new ArrayIndexOutOfBoundsException("La cellule n'a pas de cellule voisine au sud");
                }
                Cell cell2 = getCell(i, j + 1);
                if (close) {
                    cell1.setSouthCell(cell2);
                    cell2.setNorthCell(cell1);
                } else {
                    cell1.setSouthCell(null);
                    cell2.setNorthCell(null);
                }
                break;
            }
            case WEST: {
                if (i == 0) {
                    throw new ArrayIndexOutOfBoundsException("La cellule n'as pas de cellule voisine à l'ouest");
                }
                Cell cell2 = getCell(i - 1, j);
                if (close) {
                    cell1.setWestCell(cell2);
                    cell2.setEastCell(cell1);
                } else {
                    cell1.setWestCell(null);
                    cell2.setEastCell(null);
                }
                break;
            }
        }
    }

    public void closeCrossing(int i, int j, Direction direction) {
        changeCrossing(i, j, direction, true);
    }

    public Cell getCell(int i, int j) {
        if (i < 0 || i >= width) {
            throw new ArrayIndexOutOfBoundsException("La valeur de l'abscisse n'est pas valide ");
        }
        if (j < 0 || j >= height) {
            throw new ArrayIndexOutOfBoundsException("La valeur de l'ordonnée n'est pas valide");
        }

        int index = j * width + i;
        return cells.get(index);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void openCrossing(int i, int j, Direction direction) {
        changeCrossing(i, j, direction, false);
    }
}
