package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.WorksheetReader.OurBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    BasicSpreadsheet spread;

    try {
      fileR = new FileReader(args[1]);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Please give the name of a file");
    }
   // try {
      spread = WorksheetReader.read(builder, fileR);
    //} catch (Exception e) {
    //  System.out.print("Bad Spreadsheet");
    //  return;
   // }
    try {
      String cellV;
      if ( spread.getCellAt(BeyondGood.StringToCoord(args[3])).getFormula() != null) {
        if (spread.getCellAt(BeyondGood.StringToCoord(args[3])).isNumericValue()) {
          cellV = String.format("%f",
              spread.getCellAt(BeyondGood.StringToCoord(args[3])).getFormula().evaluate());
        } else {
          cellV = spread.getCellAt(BeyondGood.StringToCoord(args[3])).getFormula()
              .evaluate().toString();
        }
      } else {
        cellV = spread.getCellAt(BeyondGood.StringToCoord(args[3])).getValue().toString();
      }
         System.out.print(cellV);

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

  private static Coord StringToCoord(String s) {
    Scanner scan = new Scanner(s);
    final Pattern cellRef = Pattern.compile("([A-Za-z]+)([1-9][0-9]*)");
    scan.useDelimiter("\\s+");
    int col;
    int row;
    while (scan.hasNext("#.*")) {
      scan.nextLine();
      scan.skip("\\s*");
    }
    String cell = scan.next();
    Matcher m = cellRef.matcher(cell);
    if (m.matches()) {
      col = Coord.colNameToIndex(m.group(1));
      row = Integer.parseInt(m.group(2));
    } else {
      throw new IllegalArgumentException();
    }
    return new Coord(col, row);
  }
}
