/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package eu.frezilla.maze;

import eu.frezilla.maze.core.Direction;
import eu.frezilla.maze.viewer.AsciiMazePrinter;
import eu.frezilla.maze.core.Maze;
import eu.frezilla.maze.generator.IterativeImplementationGenerator;
import eu.frezilla.maze.generator.MazeGenerator;

/**
 *
 * @author f.balme
 */
public class Main {

    public static void main(String[] args) {
        MazeGenerator generator = new IterativeImplementationGenerator();
        Maze maze = generator.generate(10, 10);
        AsciiMazePrinter printer = new AsciiMazePrinter(System.out);
        printer.display(maze);
    }
}
