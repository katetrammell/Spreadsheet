package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Abstract class for cells. Enables reuse of code for different cell types.
 * Fields are final, so modifying cell values will have to be done be done by
 * creating a new cell altogether.
 *
 * @param <T> The data type of cthe cell that extends the class.
 */
abstract public class AbstractBasicCell<T> implements Cell<T> {
  private final T value;
  private final Formula<T> formula;
  private final List<Coord> dependencies;

  /**
   * Constructor for BasicCell that only takes in a value.
   * Sets other fields to default.
   *
   * @param val value to be set.
   */
  public AbstractBasicCell(T val) {
    this.value = val;
    this.formula = null;
    this.dependencies = new ArrayList<Coord>();
  }

  /**
   * Constructor that takes only a formula.
   * Sets other fields to default values.
   *
   * @param form - formula to be set.
   */
  public AbstractBasicCell(Formula<T> form) {
    this.value = null;
    this.formula = form;
    this.dependencies = new ArrayList<Coord>();

  }

  /**
   * Constructor that takes in a value and a list of dependencies.
   * Formula set to null.
   *
   * @param val  value to be set
   * @param deps depencies to be set
   */
  public AbstractBasicCell(T val, List<Coord> deps) {
    this.value = val;
    this.formula = null;
    this.dependencies = deps;
  }

  /**
   * Constructor that takes in formula and a list of dependcies,
   * value set to null.
   *
   * @param form formulas to be set
   * @param deps dependencies to bet set
   */
  public AbstractBasicCell(Formula<T> form, List<Coord> deps) {
    this.value = null;
    this.formula = form;
    this.dependencies = deps;
  }

  @Override
  public T getValue() {
    return this.value;
  }

  @Override
  public Formula<T> getFormula() {
    return this.formula;
  }

  @Override
  public List<Coord> getDependencies() {
    return this.dependencies;
  }

  @Override
  public void setDependent(Coord c) {
    this.dependencies.add(c);
  }

  @Override
  public String toString() {
    if (this.getFormula() != null) {
      return this.getFormula().toString();
    } else if (this.getValue() != null) {
      return this.getValue().toString();
    } else {
      return "";
    }

  }
}
