package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.WorksheetReader.OurBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   * @param args any command-line arguments
   */
  public static void main(String[] args) {
    WorksheetBuilder<BasicSpreadsheet> builder = new OurBuilder();

    FileReader fileR;

    try {
      fileR = new FileReader(args[0]);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Please give the name of a file");
    }
   BasicSpreadsheet spread = WorksheetReader.read(builder, fileR);
    try {
      System.out.print("Cell is: " +
          spread.getCellAt(Integer.parseInt(args[1]), Integer.parseInt(args[2])).toString()
          + "\n");

    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Must give valid row and col arguments");
    }


    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it,
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */
  }
}
