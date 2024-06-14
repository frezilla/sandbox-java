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
            
            char[][] arrays = new char[maze.getHeight() + 1][maze.getWidth() + 1];
            
            for (int j = 0; j < maze.getHeight(); j++) {
                for (int i = 0; i < maze.getWidth(); i++) {
                    Cell cell = maze.getCell(i, j);
                    
                    Cell nwCell = null;
                    Cell nCell = null;
                    Cell neCell = null;
                    Cell wCell = null;
                    Cell eCell = null;
                    Cell swCell = null;
                    Cell sCell = null;
                    Cell seCell = null;
                    if (j -1 >= 0) {
                        if (i > 0) nwCell = maze.getCell(i - 1 , j - 1);
                        nCell = maze.getCell(i, j - 1);
                        if (i + 1 < maze.getWidth()) neCell = maze.getCell(i + 1, j - 1);
                    }
                    if (i > 0) wCell = maze.getCell(i - 1, j);
                    if (i + 1 < maze.getWidth()) eCell = maze.getCell(i + 1, j);
                    if (j + 1 < maze.getHeight()) {
                        if (i > 0) swCell = maze.getCell(i - 1, j + 1);
                        sCell = maze.getCell(i, j + 1);
                        if (i + 1 < maze.getWidth()) seCell = maze.getCell(i, j);
                    }
                    
                    
                    
                }
            }
            
        }
    }
    
    private char[][] formatCell(Cell cell) {
        char[][] charArray = new char[3][3];
        
        Wall eastWall = cell.getEastWall();
        Wall northWall = cell.getNortWall();
        Wall southWall = cell.getSouthWall();
        Wall westWall = cell.getWestWall();
        
        if (northWall == Wall.CLOSE && westWall == Wall.CLOSE) charArray[0][0] = '╔';
        if (northWall == Wall.CLOSE) charArray[0][1] = '═';
        if (northWall == Wall.CLOSE && eastWall == Wall.CLOSE) charArray[0][2] = '╗';
        if (westWall == Wall.CLOSE) charArray[1][0] = '║';
        charArray[1][1] = ' ';
        if (eastWall == Wall.CLOSE) charArray[1][2] = '║';
        if (southWall == Wall.CLOSE && westWall == Wall.CLOSE) charArray[2][0] = '╚';
        if (southWall == Wall.CLOSE) charArray[2][1] = '═';
        if (southWall == Wall.CLOSE && eastWall == Wall.CLOSE) charArray[2][2] = '╝';
        
        return charArray;
    }
    
}
