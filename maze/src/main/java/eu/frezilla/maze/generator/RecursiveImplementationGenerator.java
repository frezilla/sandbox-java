package eu.frezilla.maze.generator;

import eu.frezilla.maze.core.Maze;
import java.util.ArrayList;
import java.util.List;
import jdk.internal.net.http.common.Pair;

public final class RecursiveImplementationGenerator implements MazeGenerator {
    
    @Override
    public Maze generate(int width, int height) {
        Maze initMaze = new Maze(width, height);
        
        List<Pair<Integer, Integer>> unvisitedCells = new ArrayList<>();
        
        for (int j = 0; j < initMaze.getHeight(); j++) {
            for (int i = 0 ; i < initMaze.getWidth(); i++) {

            }
        }
        
        return null;
        
    }
        
}
