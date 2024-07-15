/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package eu.frezilla.maze;

import eu.frezilla.maze.viewer.AsciiMazePrinter;
import eu.frezilla.maze.core.Maze;

/**
 *
 * @author f.balme
 */
public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(10, 10);
        AsciiMazePrinter printer = new AsciiMazePrinter(System.out);
        printer.display(maze);
    }
}
