package edu.cs3500.spreadsheets.model;

/**
 * Interface for formulas in cells. Allows formulas
 * to be evaluated.
 * @param <T> the data type of the formula.
 */
public interface Formula<T> {

  /**
   * evaluates the formula based on what cells it is given.
   * @return the result of the formula
   */
  T evaluate();

  /**
   * Adds a formula to the list of formulas.
   * @param form formula to be added.
   */
  void addFormula(Formula form);

  /**
   * Adds a cell to the list of cells.
   * @param cell to be added.
   */
  void addCell(Cell cell);


}
