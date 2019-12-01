package edu.cs3500.spreadsheets.provider.model;


/**
 * Represents a worksheet in a spreadsheet program.
 */
public interface Worksheet extends WorksheetReadOnly {

  /**
   * Edits the contents of the cell at {@code location} to the formula/value of {@code contents}.
   *
   * <p>When not precluded by "=", contents will become: - boolean if "true" or "false" - number if
   * integer or decimal string - cleared if "" - else string.
   * </p>
   *
   * <p>When precluded by "=", contents will become: - boolean if "true" or "false" - number if
   * integer or decimal string - string if enclosed in inner quotations - the empty string if "" -
   * the contents of another cell if the name of a cell (defined in {@link Coord}) - else the result
   * of given functions
   * </p>
   *
   * <p>In particular, a cell can only be cleared if {@code contents.equals("")} and can
   * only contain the empty string when {@code contents.equals("=\"\"")}
   * </p>
   *
   * @param location The cell to edit
   * @param contents The formula or value to set cell to
   * @throws IllegalArgumentException if {@code location} or {@code contents} is null
   */
  void editCell(Coord location, String contents);
}

