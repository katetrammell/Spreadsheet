package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

public class BasicSpreadsheet implements Spreadsheet {

  private ArrayList<List<Cell>> grid;

  /**
   * Constructor for the class.
   * Sets the size of the gird to the given width and height.
   * Sets all cells in the grid to null
   */
  BasicSpreadsheet(int width, int height) {
    this.grid = new ArrayList<List<Cell>>();
    for (int row  = 0; row < height; row++) {
      ArrayList<Cell> currRow= new ArrayList<Cell>();
      for (int col = 0; col < width; col++) {
        currRow.add(null);
      }
      grid.add(currRow);
    }
  }


  @Override
  public Cell getCellAt(int row, int col) throws IllegalArgumentException {
    if (row > this.getHeight() | row < 0) {
      throw new IllegalArgumentException();
    }
    if (col > this.getWidth() | col < 0) {
      throw new IllegalArgumentException();
    }
    return grid.get(row).get(col);
  }

  @Override
  public void setCell(Cell c, int row, int col) {
    if (row < 0 | col < 0) {
      throw new IllegalArgumentException();
    }
    this.extendBoard(row - this.getHeight(),
        col - this.getWidth());
    this.grid.get(row).set(col, c);
  }

  /**
   * Will extend the board by additional rows and columns,
   *  filling empty spaces with nulls.
   * @param rowExpansion amount of rows to expand.
   * @param colExpansion amount of columns to expand.
   */
  protected void extendBoard(int rowExpansion, int colExpansion) {
    while (rowExpansion > 0) {
      ArrayList<Cell> newRow = new ArrayList<Cell>();
      for (int i = 0; i < this.getWidth(); i++) {
        newRow.add(null);
      }
      this.grid.add(newRow);
      rowExpansion--;
    }

    while (colExpansion > 0) {
      for (int i = 0; i < this.getHeight(); i++) {
        this.grid.get(i).add(null);
      }
      colExpansion--;
    }

  }

  @Override
  public int getWidth() {
    if (this.grid.size() == 0) {
      return 0;
    } else {
      return this.grid.get(0).size();
    }
  }

  @Override
  public int getHeight() {
    return this.grid.size();
  }
}
