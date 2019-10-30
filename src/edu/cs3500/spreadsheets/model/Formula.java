package edu.cs3500.spreadsheets.model;

import java.util.List;

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
   * @param c to be added.
   */
  void addCoord(Coord c);

  void addConstant(Object t);

  List<Coord> getCoords();




}
