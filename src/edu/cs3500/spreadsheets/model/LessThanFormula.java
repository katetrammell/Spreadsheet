package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent lessThanFormula.
 */
public class LessThanFormula implements Formula<Boolean> {

  private Coord coord1;
  private Coord coord2;
  private Double d1;
  private Double d2;
  private BasicSpreadsheet spread;

  public LessThanFormula(Coord coord1, Coord coord2, BasicSpreadsheet spread) {
    this.spread = spread;
    if (!spread.getCellAt(coord1).isNumericValue() || !spread.getCellAt(coord2).isNumericValue()) {
      throw new IllegalArgumentException();
    }
    this.coord1 = coord1;
    this.coord2 = coord2;
    this.d1 = null;
    this.d2 = null;
  }

  public LessThanFormula(Double d1, Double d2, BasicSpreadsheet spread) {
    this.spread = spread;
    this.coord1 = null;
    this.coord2 = null;
    this.d1 = d1;
    this.d2 = d2;

  }

  public LessThanFormula(Coord coord1, Double d2, BasicSpreadsheet spread) {
    this.spread = spread;
    if (!spread.getCellAt(coord1).isNumericValue()) {
      throw new IllegalArgumentException();
    }
    this.coord1 = coord1;
    this.coord2 = null;
    this.d1 = null;
    this.d2 = d2;
  }

  public LessThanFormula(Double d1, Coord coord2, BasicSpreadsheet spread) {
    this.spread = spread;
    if (!spread.getCellAt(coord2).isNumericValue()) {
      throw new IllegalArgumentException();
    }
    this.coord1 = null;
    this.coord2 = coord2;
    this.d1 = d1;
    this.d2 = null;
  }

  @Override
  public Boolean evaluate() {
    // casted because at this point we know it is a double
    Double num1;
    Double num2;
    if (this.coord1 != null) {
      num1 = (double)spread.getCellAt(coord1).getNumericValue(0);
    } else {
      num1 = d1;
    }
    if (this.coord2 != null) {
      num2 = (double)spread.getCellAt(coord2).getNumericValue(0);
    } else {
      num2 = d2;
    }
    if (num1 == null|| num2 == null) {
      throw new IllegalArgumentException("Needs two num");
    }
    return num1 < num2;
  }

  @Override
  public void addFormula(Formula form) {
    throw new IllegalCallerException("LessThanFormula doesn't contain formula");
  }

  @Override
  public void addCoord(Coord c) {
    throw new IllegalCallerException("LessThanFormula has to be intialized with cells");
  }

  @Override
  public void addConstant(Object o) {
    if (!(o instanceof Double)) {
      throw new IllegalArgumentException("Less than formula takes two numbers");
    }
    if (this.coord1 == null) {
      this.d1 = (Double)o;
    } else if (this.coord2 == null) {
      this.d2 = (Double)o;
    } else {
      throw new IllegalArgumentException("Formula already has two arguments");
    }

  }

  @Override
  public List<Coord> getCoords() {
    ArrayList<Coord> ans = new ArrayList<Coord>();
    ans.add(this.coord1);
    ans.add(this.coord2);
    if (spread.getCellAt(coord1) != null && spread.getCellAt(coord1).getFormula() != null) {
      ans.addAll(spread.getCellAt(coord1).getFormula().getCoords());
    }
    if (spread.getCellAt(coord2) != null && spread.getCellAt(coord2).getFormula() != null) {
      ans.addAll(spread.getCellAt(coord2).getFormula().getCoords());
    }
    return ans;
  }

  @Override
  public String toString() {
    String ans = "(Less than : ";
    ans += this.coord1.toString() + " ";
    ans += this.coord2.toString();
    ans += this.d1.toString();
    ans += this.d2.toString() + ")";
    return ans;
  }

}
