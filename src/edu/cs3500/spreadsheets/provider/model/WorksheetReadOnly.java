package edu.cs3500.spreadsheets.provider.model;


import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
import java.util.List;

/**
 * Represents a read-only worksheet.
 */
public interface WorksheetReadOnly {

  /**
   * Returns the value of the cell at {@code location}.
   *
   * @param location the coordinates of the cell
   * @return the value of the cell at the given position
   * @throws IllegalArgumentException if {@code location} is null
   */
  CellValue getValue(Coord location);

  /**
   * Return the raw contents (formula used to evaluate a cell) of a cell at {@code location}.
   *
   * @param location the coordinates of the cell.
   * @return the raw contents of the cell.
   */
  String getRawContent(Coord location);

  /**
   * Returns a list of coordinates where there are non-empty cells.
   *
   * @return a list of coordinates where there are non-empty cells.
   */
  List<Coord> getActiveCoords();
}

