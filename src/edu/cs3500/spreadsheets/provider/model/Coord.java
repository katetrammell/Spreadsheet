package edu.cs3500.spreadsheets.provider.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * A value type representing coordinates in a worksheet
 */
public class Coord {

  public final int row;
  public final int col;

  /**
   * Constructor for Coord.
   *
   * @param col the column.
   * @param row the row.
   */
  public Coord(int col, int row) {
    if (row < 1 || col < 1) {
      throw new IllegalArgumentException("Coordinates should be strictly positive");
    }
    this.row = row;
    this.col = col;
  }

  /**
   * Creates a {@code Coord} from the given string.
   *
   * @param s the string to parse
   * @return the formed Coord
   * @throws IllegalArgumentException if s is not a valid string representation
   */
  public static Coord parseCoord(String s) {
    if (!validCoord(s)) {
      throw new IllegalArgumentException("Malformed reference");
    }

    StringBuilder colBuilder = new StringBuilder();
    StringBuilder rowBuilder = new StringBuilder();
    for (int i = 0; i < s.length(); i += 1) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        rowBuilder.append(c);
      } else {
        colBuilder.append(c);
      }
    }

    int colIndex = Coord.colNameToIndex(colBuilder.toString());
    int rowIndex = Integer.parseInt(rowBuilder.toString());

    return new Coord(colIndex, rowIndex);
  }

  /**
   * Determines if the given String represents a {@code Coord}.
   *
   * @param s the String to parse
   * @return true if s is a valid textual representation of a {@code Coord}
   */
  private static boolean validCoord(String s) {
    Pattern p = Pattern.compile("^[A-Z]*\\d*$");
    return p.matcher(s).matches();
  }

  /**
   * Converts from the A-Z column naming system to a 1-indexed numeric value.
   *
   * @param name the column name
   * @return the corresponding column index
   */
  public static int colNameToIndex(String name) {
    name = name.toUpperCase();
    int ans = 0;
    for (int i = 0; i < name.length(); i++) {
      ans *= 26;
      ans += (name.charAt(i) - 'A' + 1);
    }
    return ans;
  }

  /**
   * Converts a 1-based column index into the A-Z column naming system.
   *
   * @param index the column index
   * @return the corresponding column name
   */
  public static String colIndexToName(int index) {
    StringBuilder ans = new StringBuilder();
    while (index > 0) {
      int colNum = (index - 1) % 26;
      ans.insert(0, Character.toChars('A' + colNum));
      index = (index - colNum) / 26;
    }
    return ans.toString();
  }

  @Override
  public String toString() {
    return colIndexToName(this.col) + this.row;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coord coord = (Coord) o;
    return row == coord.row
        && col == coord.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }
}
