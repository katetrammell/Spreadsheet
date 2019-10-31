package edu.cs3500.spreadsheets.model;

import java.util.List;

/**
 * Class to represent cells in a spreadsheet that contain string values.
 */
public class BasicStringCell extends AbstractBasicCell<String> {


  public BasicStringCell(String val) {
    super(val);
  }

  public BasicStringCell(Formula<String> form) {
    super(form);
  }

  public BasicStringCell(String val, List<Coord> deps) {
    super(val, deps);
  }

  public BasicStringCell(Formula<String> form, List<Coord> deps) {
    super(form, deps);
  }

  @Override
  public boolean isNumericValue() {
    return false;
  }

  @Override
  public Double getNumericValue(double base) {
    return base;
  }

  @Override
  public boolean equals(Object other) {
    if(!(other instanceof BasicStringCell)) {
      return false;
    } else {
      BasicStringCell otherBC = (BasicStringCell) other;
      return (this.getValue().equals(otherBC.getValue())
          && this.getFormula() == otherBC.getFormula());
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return this.getValue();
  }
}
