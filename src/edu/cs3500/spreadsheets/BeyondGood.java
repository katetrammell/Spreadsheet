package edu.cs3500.spreadsheets;


import edu.cs3500.spreadsheets.controller.OurProviderController;
import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.OurProviderModel;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.WorksheetReader.OurBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import edu.cs3500.spreadsheets.provider.view.SimpleWorksheetWritableGuiView;
import edu.cs3500.spreadsheets.view.BasicSpreadSheetGraphicalView;
import edu.cs3500.spreadsheets.view.BasicSpreadSheetGraphicalViewEditable;
import edu.cs3500.spreadsheets.view.BasicSpreadsheetTextualView;
import edu.cs3500.spreadsheets.controller.SpreadSheetGraphicalController;

import edu.cs3500.spreadsheets.view.SpreadSheetGraphicalView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for our program.
 */
public class BeyondGood {

  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */
  public static void main(String[] args) {
    switch (args[0]) {
      case "-in":
        if (args[2].equals("-eval")) {
          inEval(args);
          break;
        }
        else if (args[2].equals("-save")) {
          inSave(args);
          break;
        }
        else if (args[2].equals("-gui")) {
          inGui(args);
          break;
        }
        else if (args[2].equals("-edit")) {
          inEdit(args);
          break;
        }
        else if (args[2].equals("-provider")) {
          inProvider(args);
          break;
        }
        else {
          throw new IllegalArgumentException(
              "-in but invalid third argument");
        }
      case "-gui":
        inGuiBlank();
        break;
      case "-edit":
        inEditBlank();
        break;
      case "-provider":
        inProviderBlank();
        break;
      default:
        throw new IllegalArgumentException("invalid first keyword");
    }
  }

  private static void inProviderBlank() {
    BasicSpreadsheet spread = new BasicSpreadsheet(20,20);
    OurProviderModel model = new OurProviderModel(spread);
    SimpleWorksheetWritableGuiView view = new SimpleWorksheetWritableGuiView(model);
    OurProviderController controller =
        new OurProviderController(view, model);
    controller.start();
  }

  private static void inProvider(String[] args) {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    FileReader f;
    try {
      f = new FileReader(args[1]);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);
    OurProviderModel model = new OurProviderModel(spread);
    SimpleWorksheetWritableGuiView view = new SimpleWorksheetWritableGuiView(model);
    OurProviderController controller =
        new OurProviderController(view, model);
    controller.start();

  }

  /**
   * Creates the MVC for an editable bank spreadsheet.
   */
  private static void inEditBlank() {
    BasicSpreadsheet spread = new BasicSpreadsheet(10, 5);
    BasicSpreadSheetGraphicalViewEditable view = new BasicSpreadSheetGraphicalViewEditable();
    SpreadSheetGraphicalController controller =
        new SpreadSheetGraphicalController(spread, view);
  }

  /**
   * Creates the MVC for a non-editable spreadsheet.
   * @param args string array containing the filename
   */
  private static void inGui(String[] args) {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    FileReader f;
    try {
      f = new FileReader(args[1]);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
    Spreadsheet spread = WorksheetReader.read(b, f);
    SpreadSheetGraphicalView view = new BasicSpreadSheetGraphicalView();
    SpreadSheetGraphicalController controller =
        new SpreadSheetGraphicalController(spread, view);
  }

  /**
   * Creates the MVC for an editable bank spreadsheet.
   * @param args string array that the user passed in.
   */
  private static void inEdit(String[] args) {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    FileReader f;
    try {
      f = new FileReader(args[1]);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);
    BasicSpreadSheetGraphicalViewEditable view = new BasicSpreadSheetGraphicalViewEditable();
    SpreadSheetGraphicalController controller =
        new SpreadSheetGraphicalController(spread, view);
  }

  /**
   * Creates a non-editable blank spreadsheet (MCV).
   */
  private static void inGuiBlank() {
    BasicSpreadsheet spread = new BasicSpreadsheet(10, 5);
    BasicSpreadSheetGraphicalView view = new BasicSpreadSheetGraphicalView();
    view.render(spread);
  }

  /**
   * Takes in given file, saves as new file.
   * @param args arguments from run configuration as described in assignment
   */
  private static void inSave(String[] args) {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    FileReader f;
    try {
      f = new FileReader(args[1]);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);
    BasicSpreadsheetTextualView view = new BasicSpreadsheetTextualView();
    try {
      PrintWriter writer = new PrintWriter(args[3]);
      view.render(spread, writer);
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Writing failed");
    }
  }

  /**
   * Takes in spreadsheet data and evaluates a cell.
   * @param args arguments as described in assignment from run configurations
   */
  private static void inEval(String[] args) {
    WorksheetBuilder<BasicSpreadsheet> builder = new OurBuilder();
    FileReader fileR;
    BasicSpreadsheet spread;

    try {
      fileR = new FileReader(args[1]);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Please give the name of a file");
    }
    spread = WorksheetReader.read(builder, fileR);
    try {
      String cellV;
      if (spread.getCellAt(BeyondGood.stringToCoord(args[3])).getFormula() != null) {
        if (spread.getCellAt(BeyondGood.stringToCoord(args[3])).isNumericValue()) {
          cellV = String.format("%f",
              spread.getCellAt(BeyondGood.stringToCoord(args[3])).getFormula().evaluate());
        } else {
          cellV = spread.getCellAt(BeyondGood.stringToCoord(args[3])).getFormula()
              .evaluate().toString();
        }
      } else {
        cellV = spread.getCellAt(BeyondGood.stringToCoord(args[3])).getValue().toString();
      }
      System.out.print(cellV);

    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Must give valid row and col arguments");
    }
  }


  /**
   * Converts a string into a coordinate.
   * @param s the string to be converted.
   * @return a coordinate version of the given string
   */
  private static Coord stringToCoord(String s) {
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