package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.sexp.CellMaker;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A factory for reading inputs and producing Worksheets: given a
 * {@link WorksheetBuilder} to produce the new Worksheet itself,
 * this class can parse the given input and populate the worksheet.
 */
public final class WorksheetReader {
  /**
   * A builder pattern for producing Worksheets.
   * @param <T> the type of Worksheet to produce
   */
  public interface WorksheetBuilder<T> {
    /**
     * Creates a new cell at the given coordinates and fills in its raw contents.
     * @param col the column of the new cell (1-indexed)
     * @param row the row of the new cell (1-indexed)
     * @param contents the raw contents of the new cell: may be {@code null}, or any string.
     *                 Strings beginning with an {@code =} character should be
     *                 treated as formulas; all other strings should be treated as number or
     *                 boolean values if possible, and string values otherwise.
     * @return this {@link WorksheetBuilder}
     */
    WorksheetBuilder<T> createCell(int col, int row, String contents);

    /**
     * Finalizes the construction of the worksheet and returns it.
     * @return the fully-filled-in worksheet
     */
    T createWorksheet();
  }

  public static class OurBuilder implements WorksheetBuilder<BasicSpreadsheet> {
    private BasicSpreadsheet basicSpread;

    public OurBuilder(BasicSpreadsheet basicSpread) {
      this.basicSpread = basicSpread;
    }
    public OurBuilder() {
      this.basicSpread = new BasicSpreadsheet();
    }

    @Override
    public WorksheetBuilder<BasicSpreadsheet> createCell(int col, int row, String contents) {

      new Parser();
      Sexp sexp = Parser.parse(contents);
      Cell c = sexpToCell(sexp);
      this.basicSpread.setCell(c, row, col);

      /*
      try {
       Double i =  Double.valueOf(contents);
       basicSpread.setCell(new BasicDoubleCell(i), row, col);
      } catch (NumberFormatException e) {
        if (contents.equalsIgnoreCase("true")) {
          basicSpread.setCell(new BasicBooleanCell(true), row, col);
        } else if (contents.equalsIgnoreCase("false")) {
          basicSpread.setCell(new BasicBooleanCell(false), row, col);
        } else if (contents.equals(null)) {
          basicSpread.setCell(null, row, col);
        } else {
          basicSpread.setCell(new BasicStringCell(contents), row, col);
        }

      }

       */
      return this;
    }

    /**
     * Helper methot to convert S expressions to
     * cells.
     * @param sexp s expression from parser.
     * @return Cell that has been converted from sexp.
     */
    private Cell sexpToCell(Sexp sexp) {
      return sexp.accept(new CellMaker());

    }

    @Override
    public BasicSpreadsheet createWorksheet() {
      return this.basicSpread;
    }
  }

  /**
   * <p>A factory for producing Worksheets.  The file format is</p>
   * <pre>
   *   &lt;cell coordinates, in A# format&gt; &lt;the raw contents of the cell&gt;
   *   ...
   * </pre>
   * <p>e.g.</p>
   * <pre>
   *   A1 5
   *   A2 6
   *   A3 =(SUM A1 A2:A2)
   * </pre>
   * <p>Line-comments are indicated by <code>#</code>, and are allowed either at the start
   * of a line or between the cell coordinate and its contents.</p>
   * <p>There is no requirement that cells are filled in in order of their dependencies,
   * since no cell evaluation occurs during this creation process.</p>
   * @param builder The source of the new Worksheet object
   * @param readable the input source for the contents of this Worksheet
   * @param <T> the type of Worksheet to produce
   * @return the fully-filled-in Worksheet
   */
  public static <T> T read(WorksheetBuilder<T> builder, Readable readable) {
    Scanner scan = new Scanner(readable);
    final Pattern cellRef = Pattern.compile("([A-Za-z]+)([1-9][0-9]*)");
    scan.useDelimiter("\\s+");
    while (scan.hasNext()) {
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
        throw new IllegalStateException("Expected cell ref");
      }
      scan.skip("\\s*");
      while (scan.hasNext("#.*")) {
        scan.nextLine();
        scan.skip("\\s*");
      }
      String contents = scan.nextLine();
      builder = builder.createCell(col, row, contents);
    }

    return builder.createWorksheet();
  }
}
