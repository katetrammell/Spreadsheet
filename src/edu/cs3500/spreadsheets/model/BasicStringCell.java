package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
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
  public CellValue toProviderCell() {
    if (this.getFormula() == null) {
      return new ProviderStringCell(this.getValue());
    } else {
      return new ProviderStringCell(this.getFormula().evaluate());
    }
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof BasicStringCell)) {
      return false;
    } else {
      BasicStringCell otherBC = (BasicStringCell) other;
      boolean equalValue = true;
      boolean equalForm = true;
      if (this.getValue() != null) {
        equalValue = this.getValue().equals(otherBC.getValue());
      }
      if (this.getFormula() != null) {
        equalForm = this.getFormula() == otherBC.getFormula();
      }
      return equalForm && equalValue;

    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    if (this.getFormula() == null) {
      return "\"" + this.getValue() + "\"";
    } else {
      return this.getFormula().toString();
    }
  }
}
