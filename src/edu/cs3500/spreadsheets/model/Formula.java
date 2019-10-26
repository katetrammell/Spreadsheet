package edu.cs3500.spreadsheets.model;

public interface Formula<T> {

  /**
   * evaluates the formula
   * @return the result of the formula
   */
  T evaluate();


}
