package eu.frezilla.maze.generator;

import eu.frezilla.maze.core.Maze;

public interface MazeGenerator {
    
    Maze generate(int width, int height);
}
