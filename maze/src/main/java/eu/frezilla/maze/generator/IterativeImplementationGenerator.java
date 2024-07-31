package eu.frezilla.maze.generator;

import eu.frezilla.maze.core.Cell;
import eu.frezilla.maze.core.Direction;
import static eu.frezilla.maze.core.Direction.EAST;
import static eu.frezilla.maze.core.Direction.NORTH;
import static eu.frezilla.maze.core.Direction.SOUTH;
import static eu.frezilla.maze.core.Direction.WEST;
import eu.frezilla.maze.core.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public final class IterativeImplementationGenerator implements MazeGenerator {
    
    @Override
    public Maze generate(int width, int height) {
        Maze maze = new Maze(width, height);
        
        List<IterativeCell> unvisitedCells = new ArrayList<>(width * height);
        List<IterativeCell> allCells = new ArrayList<>(width * height);
        Map<Integer, List<IterativeCell>> cellsByGroupIdMap = new HashMap<>();
        
        for (int j = 0; j < maze.getHeight(); j++) {
            for (int i = 0 ; i < maze.getWidth(); i++) {
                int groupId = (i + j * maze.getHeight());
                IterativeCell iterativeCell = 
                        new IterativeCell(
                                maze.getCell(i, j), 
                                i, 
                                j, 
                                groupId
                        );
                List<IterativeCell> cells = new ArrayList<>();
                cells.add(iterativeCell);
                cellsByGroupIdMap.put(groupId, cells);
                        
                allCells.add(iterativeCell);
            }
        }
        unvisitedCells.addAll(allCells);
        
        Random rand = new Random();
        
        while (!unvisitedCells.isEmpty()) {
            int indexCell = rand.nextInt(unvisitedCells.size());
            IterativeCell iterativeCell = unvisitedCells.remove(indexCell);
            
            Cell cell = iterativeCell.getCell();
            int i = iterativeCell.getI();
            int j = iterativeCell.getJ();
            int groupId = iterativeCell.getGroupId();
            
            IterativeCell westCell = getNeighboringCell(i, j, Direction.WEST, allCells, width, height);
            IterativeCell eastCell = getNeighboringCell(i, j, Direction.EAST, allCells, width, height);
            IterativeCell northCell = getNeighboringCell(i, j, Direction.NORTH, allCells, width, height);
            IterativeCell soutCell = getNeighboringCell(i, j, Direction.SOUTH, allCells, width, height);
                        
            List<Direction> directions = new ArrayList<>();
            directions.addAll(Arrays.asList(Direction.values()));
            
            if (i == 0 || cell.hasWestCell() || (westCell != null && Objects.equals(westCell.getGroupId(), groupId))) directions.remove(Direction.WEST);
            if (i + 1 == width || cell.hasEastCell() || (eastCell != null && Objects.equals(eastCell.getGroupId(), groupId))) directions.remove(Direction.EAST);
            if (j == 0 || cell.hasNorthCell() || (northCell != null && Objects.equals(northCell.getGroupId(), groupId))) directions.remove(Direction.NORTH);
            if (j +1 == height || cell.hasSouthCell() || (soutCell != null && Objects.equals(soutCell.getGroupId(), groupId))) directions.remove(Direction.SOUTH);
            
            if (!directions.isEmpty()) {
                int indexDirection = rand.nextInt(directions.size());
                Direction direction = directions.get(indexDirection);
                maze.openCrossing(i, j, direction);
                
                switch (direction) {
                    case EAST:
                        eastCell.getGroupId();
                        break;
                    case NORTH:
                        northCell.getGroupId();
                        break;                        
                    case SOUTH:
                        soutCell.getGroupId();
                        break;
                    case WEST:
                        westCell.getGroupId();
                        break;                        
                    default:
                        throw new AssertionError();
                }
            }
        }
        
        return maze;
    }
    
    private IterativeCell getNeighboringCell(int i, int j, Direction direction, List<IterativeCell> cells, int width, int height) {
        int destI = i;
        int destJ = j;
        switch (direction) {
            case EAST:
                if (i + 1 < width) destI++;
                break;
            case NORTH:
                if (j > 0) destJ--;
                break;
            case SOUTH:
                if (j + 1 < height) destJ++;
                break;
            case WEST:
                if (i > 0) destI--;
                break;
            default:
                throw new AssertionError("La direction spécifiée n'est pas gérée");
        }
        
        if (destI != i || destJ != j) {
            return cells.get(destI + destJ * width);
        } else {
            return null;
        }
        
    }
        
}
