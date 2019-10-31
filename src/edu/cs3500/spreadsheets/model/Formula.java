package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

/**
 * Interface to represent spreadsheet formulas.
 * @param <T> the return type.
 */
public interface Formula<T> {

  /**
   * Evaluates the formula.
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

  /**
   * Adds constant to the list to be used in formulas.
   * @param t constant ot be added
   */
  void addConstant(Object t);

  /**
   * Gets all the coordinates involved with this formula.
   * If a formula references a cell that just contains a cell
   * it will add that cell, if that cell also has formulas, it will includes
   * those cells and recursively gather any cells those cells refernce.
   * @return the list o coordiantes involves with a formula
   */
  HashMap<Coord, Integer> getCoords(HashMap<Coord, HashMap<Coord, Integer>> currhash);





}
