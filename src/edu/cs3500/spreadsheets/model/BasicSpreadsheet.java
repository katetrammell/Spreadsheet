package edu.cs3500.spreadsheets.model;
import java.util.HashMap;
import java.util.Map;

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

  public BasicSpreadsheet() {
    this.grid = new HashMap<>();
    this.width = 0;
    this.height = 0;
  }


  @Override
  public Cell getCellAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Invalid row or column");
    }
    return this.grid.get(new Coord(row, col));
  }

  @Override
  public void setCell(Cell c, int row, int col) {
    if (row < 0 | col < 0) {
      throw new IllegalArgumentException("Invalid row or column");
    }
    if (row > this.height) {
      this.height = row;
    }
    if (col > this.width) {
      this.width = col;
    }
    this.grid.put(new Coord(row, col), c);
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
