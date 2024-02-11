/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Scanner;
import java.io.File;

/**
 * Supports the computation of the total disk space usage within a file system.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class DiskSpace {

    /**
     * Calculates the total disk usage (in bytes) of the portion of the file system rooted
     * at the given path, while printing a summary akin to the standard 'du' Unix tool.
     * @param root designates a location in the file system
     * @return total number of bytes used by the designated portion of the file system
     */
    public static long diskUsage(File root) {
        long total = root.length();                          // start with direct disk usage
        if (root.isDirectory()) {                            // and if this is a directory,
            for (String childname : root.list()) {             // then for each child
                File child = new File(root, childname);          // compose full path to child
                total += diskUsage(child);                       // add child's usage to total
            }
        }
        System.out.println(total + "\t" + root);             // descriptive output
        return total;                                        // return the grand total
    }

    /**
     * Recursively finds all entries of the file system rooted at the given path having the given file name.
     * @param root the root directory to start the search from
     * @param filename the name of the file to find
     */
    public static void find(File root, String filename) {
        if (root.isDirectory()) {
            for (String childname : root.list()) {
                File child = new File(root, childname);
                if (child.isDirectory()) {
                    find(child, filename);  // recursively search subdirectories
                } else if (child.getName().equals(filename)) {
                    System.out.println("Found: " + child.getAbsolutePath());  // print the path of the file if its name matches
                }
            }
        }
    }

    /**
     * Computes disk usage of the path given as a command line argument.
     * Sample usage:  java DiskSpace C:\Users\Michael
     */
    public static void main(String[] args) {
        String start;
        if (args.length > 0) {
            start = args[0];
        } else {
            System.out.print("Enter the starting location: "); // C:\COMP254-ANM\Assignment3\
            start = new Scanner(System.in).next();
        }
        File root = new File(start);
        diskUsage(root);  // Calculate disk usage
        System.out.println("Searching for file 'lab3exercise3.txt'...");
        find(root, "lab3exercise3.txt");  // Search for file with name 'example.txt'
    }
}
