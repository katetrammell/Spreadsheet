package edu.cs3500.spreadsheets.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to represent a spreadsheet. Enables creating and access of
 * cells on a spreadsheet.
 */
public class BasicSpreadsheet implements Spreadsheet {

  private final Map<Coord, Cell> grid;
  private int width;
  private int height;

  /**
   * Constructor for the class.
   * Sets the size of the gird to the given width and height.
   * Sets all cells in the grid to null
   */
  public BasicSpreadsheet(int width, int height) {
    this.grid = new HashMap<>();
    this.width = width;
    this.height = height;
  }

  /**
   * Default constructor for BasicSpreadsheet.
   * Sets fields to defaults.
   */
  public BasicSpreadsheet() {
    this.grid = new HashMap<>();
    this.width = 0;
    this.height = 0;
  }

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
  public void setCell(Cell c, int row, int col) throws IllegalArgumentException{
    if (row <= 0 || col <= 0) {
      throw new IllegalArgumentException("Invalid row or column");
    }

    if (row > this.height) {
      this.height = row;
    }
    if (col > this.width) {
      this.width = col;
    } if (c.getFormula() != null) {
      List<?> cc = c.getFormula().getCoords();
      if (cc.contains(new Coord(col, row))) {
        throw new IllegalArgumentException("Cyclic reference");
      }
    }
      this.grid.put(new Coord(col , row ), c);
    if (c.getFormula() != null) {
      Formula<?> f = c.getFormula();
      for (Coord coord : f.getCoords()) {
        if (coord != null) {
          if (this.getCellAt(coord) != null) {
            this.getCellAt(coord).setDependent(new Coord(col, row));
          }
        }
      }

    }
  }


  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }
}
