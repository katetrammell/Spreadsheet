package edu.cs3500.spreadsheets.model;

public interface Formula<T> {

  /**
   * evaluates the formula
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
