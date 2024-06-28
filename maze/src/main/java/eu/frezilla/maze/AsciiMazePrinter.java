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
            int mazeHeight = maze.getHeight();
            int mazeWidth = maze.getWidth();
            
            int mazeAsciiHeight = 2 * mazeHeight + 1;
            int mazeAsciiWidth = 4 * mazeWidth + 1;
            char[][] mazeAscii = new char[mazeAsciiHeight][mazeAsciiWidth];
            
            for (int j = 0; j < mazeHeight; j++) {
                for (int i = 0; i < mazeWidth; i++) {
                    Cell currentCell = maze.getCell(i, j);
                    
                    int xWall = 4 * i;
                    int yWall = 2 * j;
                    
                    if (j == 0) {
                        if (i == 0) mazeAscii[yWall][xWall] = '╔';
                        if (i + 1 == mazeWidth) mazeAscii[yWall][xWall + 4] = '╗';
                        mazeAscii[yWall][xWall + 1] = '═';
                        mazeAscii[yWall][xWall + 2] = '═';
                        mazeAscii[yWall][xWall + 3] = '═';
                    } 
                    if (i == 0) mazeAscii[yWall + 1][xWall] = '║';
                    if (i + 1 == mazeWidth) mazeAscii[yWall + 1][xWall + 4] = '║';
                    if (j + 1 == mazeHeight) {
                        if (i == 0) mazeAscii[yWall + 2][xWall] = '╚';
                        if (i + 1 == mazeWidth) mazeAscii[yWall + 2][xWall + 4] = '╝';
                        mazeAscii[yWall + 2][xWall + 1] = '═';
                        mazeAscii[yWall + 2][xWall + 2] = '═';
                        mazeAscii[yWall + 2][xWall + 3] = '═';
                    }
                    mazeAscii[yWall + 1][xWall + 1] = ' ';
                    mazeAscii[yWall + 1][xWall + 2] = ' ';
                    mazeAscii[yWall + 1][xWall + 3] = ' ';
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