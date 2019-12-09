package edu.cs3500.spreadsheets.model;

import java.util.HashMap;
import java.util.List;

/**
 * Class to represent a spreadsheet. Enables creating and access of cells on a spreadsheet.
 */
public class BasicSpreadsheet implements Spreadsheet {


  private final HashMap<Coord, Cell> grid;
  private int width;
  private int height;
  private HashMap<Coord, HashMap<Coord, Integer>> listOfDep;
  private List<Integer> colWidths;
  private HashMap<Integer, Integer> rowHeights;
  // Public so that other classes have a reference for default size,
  // prevents tight coupling
  public static int DEFAULT_ROW_HEIGHT = 15;

  /**
   * Constructor for the class. Sets the size of the gird to the given width and height. Sets all
   * cells in the grid to null
   */
  public BasicSpreadsheet(int width, int height) {
    this.grid = new HashMap<>();
    this.width = width;
    this.height = height;
    listOfDep = new HashMap<Coord, HashMap<Coord, Integer>>();
    rowHeights = new HashMap<Integer, Integer>();
  }

  /**
   * Default constructor for BasicSpreadsheet. Sets fields to defaults.
   * Default spreadsheet is 10 x 10.
   */
  public BasicSpreadsheet() {
    this.grid = new HashMap<>();
    this.width = 10;
    this.height = 10;
    listOfDep = new HashMap<Coord, HashMap<Coord, Integer>>();
    rowHeights = new HashMap<Integer, Integer>();
  }

  /**
   * Get cell at specified location.
   *
   * @param c coordinate of cell
   * @return cell at lovation
   * @throws IllegalArgumentException if coord is null
   */
  public Cell<?> getCellAt(Coord c) throws IllegalArgumentException {
    if (c == null) {
      throw new IllegalArgumentException("coord cannot be null");
    }
    return this.grid.get(c);
  }

  @Override
  public Cell getCellAt(int row, int col) throws IllegalArgumentException {
    if (row <= 0 || col <= 0) {
      throw new IllegalArgumentException("Invalid row or column");
    }
    return this.grid.get(new Coord(col, row));
  }

  @Override
  public HashMap<Coord, Cell> getAllCells() {
    return this.grid;
  }

  @Override
  public void setCell(Cell c, int row, int col) throws IllegalArgumentException {
    if (row <= 0 || col <= 0) {
      throw new IllegalArgumentException("Invalid row or column");
    }

    if (row > this.height) {
      this.height = row;
    }
    if (col > this.width) {
      this.width = col;
    }
    if (c.getFormula() != null) {
      Formula<?> form = c.getFormula();
      HashMap<Coord, Integer> cc = form.getCoords(this.listOfDep);
      if (cc.containsKey(new Coord(col, row))) {
        throw new IllegalArgumentException("Cyclic reference");
      }
      listOfDep.put(new Coord(col, row), cc);
    }
    this.grid.put(new Coord(col, row), c);
  }

  @Override
  public void setCell(Cell c, Coord coord) throws IllegalArgumentException {
    setCell(c, coord.getY(), coord.getX());

  }


  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void setWidth(int width) {
    if (width < this.getWidth()) {
      throw new IllegalArgumentException("Cannot reduce spreadsheet size");
    }
    this.width = width;
  }

  @Override
  public void setHeight(int height) {
    if (height < this.getHeight()) {
      throw new IllegalArgumentException("Cannot reduce spreadsheet size");
    }
    this.height = height;
  }

  @Override
  public void removeCell(Coord c) {
    this.grid.remove(c);
  }

  @Override
  public int getRowHeight(int row) {
    if (row > height) {
      throw new IllegalArgumentException("Row not on spreadsheet");
    }
    else if (!rowHeights.containsKey(row)) {
      return DEFAULT_ROW_HEIGHT;
    }
    return rowHeights.get(row);
  }

  @Override
  public void setRowHeight(int rowN, int rowH) {
    if (rowH < 1) {
      rowHeights.put(rowN, 1);
    }
    else {
      rowHeights.put(rowN, rowH);
    }
  }

  @Override
  public List<Integer> getColWidths() {
    return this.colWidths;
  }

  @Override
  public void updateColWidths(int colNum, int newWidth) {
    this.colWidths.set(colNum , newWidth);

  }

}
