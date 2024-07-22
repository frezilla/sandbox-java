package eu.frezilla.maze.generator;

import eu.frezilla.maze.core.Cell;
import eu.frezilla.maze.core.Direction;
import eu.frezilla.maze.core.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class IterativeImplementationGenerator implements MazeGenerator {
    
    @Override
    public Maze generate(int width, int height) {
        Maze maze = new Maze(width, height);
        
        List<IterativeCell> unvisitedCells = new ArrayList<>();
        
        for (int j = 0; j < maze.getHeight(); j++) {
            for (int i = 0 ; i < maze.getWidth(); i++) {
                IterativeCell iterativeCell = new IterativeCell(maze.getCell(i, j), i, j);
                unvisitedCells.add(iterativeCell);
            }
        }
        
        Random rand = new Random();
        
        while (!unvisitedCells.isEmpty()) {
            int indexCell = rand.nextInt(unvisitedCells.size());
            IterativeCell iterativeCell = unvisitedCells.remove(indexCell);
            
            Cell cell = iterativeCell.getCell();
            int i = iterativeCell.getI();
            int j = iterativeCell.getJ();
                        
            List<Direction> directions = new ArrayList<>();
            directions.addAll(Arrays.asList(Direction.values()));
            
            if (i == 0 || cell.hasWestCell()) directions.remove(Direction.WEST);
            if (i + 1 == width || cell.hasEastCell()) directions.remove(Direction.EAST);
            if (j == 0 || cell.hasNorthCell()) directions.remove(Direction.NORTH);
            if (j +1 == height || cell.hasSouthCell()) directions.remove(Direction.SOUTH);
            
            if (!directions.isEmpty()) {
                int indexDirection = rand.nextInt(directions.size());
                maze.openCrossing(i, j, directions.get(indexDirection));
            }
        }
        
        return maze;
        
    }
        
}
