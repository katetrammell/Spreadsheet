package edu.cs3500.spreadsheets.provider.cellvalue;

/**
 * An abstracted function object for processing any {@link CellValue}.
 *
 * @param <R> The return type of this function
 */
public interface CellValueVisitor<R> {

  /**
   * Process a boolean value.
   *
   * @param b the value
   * @return the desired result
   */
  R visitBoolean(boolean b);

  /**
   * Process a String value.
   *
   * @param s the value
   * @return the desired result
   */
  R visitString(String s);

  /**
   * Process a numeric value.
   *
   * @param d the value
   * @return the desired result
   */
  R visitNumber(double d);

  /**
   * Process an empty value.
   *
   * @return the desired result
   */
  R visitEmpty();

  /**
   * Process an error value.
   *
   * @param err the error message to process
   * @return the desired result
   */
  R visitError(String err);
}

