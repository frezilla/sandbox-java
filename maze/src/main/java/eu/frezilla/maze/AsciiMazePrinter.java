package eu.frezilla.maze;

import java.io.PrintStream;
import java.util.Objects;

public final class AsciiMazePrinter {

    private final PrintStream ps;

    public AsciiMazePrinter(PrintStream ps) {
        this.ps = Objects.requireNonNull(ps, "Le flux d'affichage est null");
    }

    public void display(Maze maze) {
        if (maze != null) {
            char[][] walls = new char[2*maze.getHeight() + 1][2*maze.getWidth() + 1];
            
            for (int j = 0; j < maze.getHeight(); j++) {
                for (int i = 0; i < maze.getWidth(); i++) {
                    Cell currentCell = maze.getCell(i, j);
                    
                    Cell nwCell, nCell, neCell, wCell, eCell, swCell, sCell, seCell;
                    nwCell = nCell = neCell = wCell = eCell = swCell = sCell = seCell = null;
                    
                    if (i > 0) wCell = maze.getCell(i - 1, j);
                    if (i + 1 < maze.getWidth()) eCell = maze.getCell(i + 1, j);
                    if (j > 0) nCell = maze.getCell(i, j - 1);
                    if (j + 1 < maze.getHeight()) sCell = maze.getCell(i, j + 1);
                    if (i > 0 && j > 0) nwCell = maze.getCell(i - 1, j - 1);
                    if (i + 1 < maze.getWidth() && j + 1 < maze.getHeight()) seCell = maze.getCell(i + 1, j + 1);
                    if (i > 0 && j + 1 < maze.getHeight()) swCell = maze.getCell(i - 1, j + 1);
                    if (i + 1 < maze.getWidth() && j > 0) neCell = maze.getCell(i + 1, j - 1);
                    
                    int xWall = 2 * i;
                    int yWall = 2 * j;
                    
                    if (nwCell == null && currentCell.getNorthWall().isClosed() && currentCell.getWestWall().isClosed()) walls[yWall][xWall] = '┌';
                    else walls[yWall][xWall] = ' ';
                    walls[yWall][xWall + 1] = currentCell.getNorthWall().isClosed() ? '─' : ' ';
                    if (neCell == null && currentCell.getNorthWall().isClosed() && currentCell.getEastWall().isClosed()) walls[yWall][xWall + 2] = '┐';
                    else walls[yWall][xWall + 2] = ' ';
                    walls[yWall + 1][xWall] = currentCell.getWestWall().isClosed() ? '│' : ' ';
                    walls[yWall + 1][xWall + 1] = ' ';
                    walls[yWall + 1][xWall + 2] = currentCell.getEastWall().isClosed() ? '│' : ' ';
                    walls[yWall + 2][xWall] = ' ';
                    walls[yWall + 2][xWall + 1] = currentCell.getSouthWall().isClosed() ? '─' : ' ';
                    walls[yWall + 2][xWall + 2] = ' ';
                }
            }
            
            for (int j = 0; j < 2*maze.getHeight() + 1; j++) {
                for (int i = 0; i < 2*maze.getWidth() + 1; i++) {
                    ps.print(walls[j][i]);
                }
                ps.println();
            }
        }
    }
}