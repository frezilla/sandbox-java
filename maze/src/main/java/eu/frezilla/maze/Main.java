package eu.frezilla.maze;

import eu.frezilla.maze.viewer.AsciiMazePrinter;
import eu.frezilla.maze.core.Maze;
import eu.frezilla.maze.generator.IterativeImplementationGenerator;
import eu.frezilla.maze.generator.MazeGenerator;

public class Main {

    public static void main(String[] args) {
        MazeGenerator generator = new IterativeImplementationGenerator();
        Maze maze = generator.generate(10, 10);
        AsciiMazePrinter printer = new AsciiMazePrinter(System.out);
        printer.display(maze);
    }
}
