package eu.frezilla.maze.viewer;

import eu.frezilla.maze.core.Cell;
import eu.frezilla.maze.core.Maze;
import java.io.PrintStream;
import java.util.Objects;

public final class AsciiMazePrinter {

    private final PrintStream ps;

    public AsciiMazePrinter(PrintStream ps) {
        this.ps = Objects.requireNonNull(ps, "Le flux d'affichage est null");
    }

    public void display(Maze maze) {
        if (maze != null) {
            int mazeHeight = maze.getHeight();
            int mazeWidth = maze.getWidth();
            
            int mazeAsciiHeight = 2 * mazeHeight + 1;
            int mazeAsciiWidth = 3 * mazeWidth + 1;
            char[][] mazeAscii = new char[mazeAsciiHeight][mazeAsciiWidth];
            
            for (int j = 0; j < mazeHeight; j++) {
                for (int i = 0; i < mazeWidth; i++) {
                    Cell currentCell = maze.getCell(i, j);
                    
                    int xWall = 3 * i;
                    int yWall = 2 * j;
                    
                    mazeAscii[yWall][xWall] = '+';
                    mazeAscii[yWall][xWall + 3] = '+';
                    mazeAscii[yWall + 2][xWall] = '+';
                    mazeAscii[yWall + 2][xWall + 3] = '+';
                    mazeAscii[yWall + 1][xWall + 1] = ' ';
                    mazeAscii[yWall + 1][xWall + 2] = ' ';
                    
                    if (currentCell.hasNorthCell()) {
                        mazeAscii[yWall][xWall + 1] = ' ';
                        mazeAscii[yWall][xWall + 2] = ' ';
                    } else {
                        mazeAscii[yWall][xWall + 1] = '-';
                        mazeAscii[yWall][xWall + 2] = '-';
                    }
                    
                    if (currentCell.hasSouthCell()) {
                        mazeAscii[yWall + 2][xWall + 1] = ' ';
                        mazeAscii[yWall + 2][xWall + 2] = ' ';
                    } else {
                        mazeAscii[yWall + 2][xWall + 1] = '-';
                        mazeAscii[yWall + 2][xWall + 2] = '-';
                    }
                    
                    if (currentCell.hasWestCell()) {
                        mazeAscii[yWall + 1][xWall] = ' ';
                    } else {
                        mazeAscii[yWall + 1][xWall] = '|';
                    }
                    
                    if (currentCell.hasEastCell()) {
                        mazeAscii[yWall + 1][xWall + 3] = ' ';
                    } else {
                        mazeAscii[yWall + 1][xWall + 3] = '|';
                    }
                    
                    
                }
            }
            
            for (int j = 0; j < mazeAsciiHeight; j++) {
                for (int i = 0; i < mazeAsciiWidth; i++) {
                    ps.print(mazeAscii[j][i]);
                }
                ps.println();
            }
        }
    }
}