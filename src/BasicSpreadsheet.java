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
  public double sum(Cell... range) {
    return 0;
  }

  @Override
  public double product(Cell... range) {
    return 0;
  }

  @Override
  public boolean greaterThan(Cell cell1, Cell cell2) {
    return false;
  }

  @Override
  public String concat(Cell... range) {
    return "";
  }

  @Override
  public Cell getCellAt(int row, int col) {
    return null;
  }

  @Override
  public void setCell(Cell c, int row, int col) {

  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }
}
