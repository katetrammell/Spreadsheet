package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

/**
 * Class that represent lessThanFormula.
 */
public class LessThanFormula implements Formula<Boolean> {

  private Coord coord1;
  private Coord coord2;
  private Double d1;
  private Double d2;
  private BasicSpreadsheet spread;


  /**
   * Constructor for the class.
   * @param coord1 coordinate 1
   * @param coord2 coordinate 2
   * @param spread spreadsheet
   */
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

  /**
   * Constructor for the class.
   * @param d1 double 1
   * @param d2 double 2
   * @param spread spreadsheet
   */
  public LessThanFormula(Double d1, Double d2, BasicSpreadsheet spread) {
    this.spread = spread;
    this.coord1 = null;
    this.coord2 = null;
    this.d1 = d1;
    this.d2 = d2;

  }

  /**
   * Constructor for the class.
   * @param coord1 coordinate 1
   * @param d2 double 2
   * @param spread spreadsheet
   */
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

  /**
   * Constructor for the class.
   * @param d1 double 1
   * @param coord2 coordinate 2
   * @param spread spreadsheet
   */
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
      num1 = (double) spread.getCellAt(coord1).getNumericValue(0);
    } else {
      num1 = d1;
    }
    if (this.coord2 != null) {
      num2 = (double) spread.getCellAt(coord2).getNumericValue(0);
    } else {
      num2 = d2;
    }
    if (num1 == null || num2 == null) {
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
      this.d1 = (Double) o;
    } else if (this.coord2 == null) {
      this.d2 = (Double) o;
    } else {
      throw new IllegalArgumentException("Formula already has two arguments");
    }

  }

  @Override
  public HashMap<Coord, Integer> getCoords(HashMap<Coord, HashMap<Coord, Integer>> currHash) {
    HashMap<Coord, Integer> ans = new HashMap<Coord, Integer>();
    if (this.coord1 != null && spread.getCellAt(coord1) != null) {
      if (currHash.containsKey(coord1)) {
        ans.putAll(currHash.get(coord1));
      } else {
        ans.put(coord1,0);
        if (spread.getCellAt(coord1).getFormula() != null) {
          ans.putAll(spread.getCellAt(coord1).getFormula().getCoords(currHash));
        }
      }
    }
    if (this.coord2 != null && spread.getCellAt(coord2) != null) {
      if (currHash.containsKey(coord2)) {
        ans.putAll(currHash.get(coord2));
      } else {
        ans.put(coord2,0);
        if (spread.getCellAt(coord2).getFormula() != null) {
          ans.putAll(spread.getCellAt(coord2).getFormula().getCoords(currHash));
        }
      }
    }

    return ans;
  }


  @Override
  public String toString() {
    String ans = "=(< ";
    if (coord1 != null) {
      ans += this.coord1.toString() + " ";
    }
    if (coord2 != null) {
      ans += this.coord2.toString() + " ";
    }
    if (d1 != null) {
      ans += this.d1.toString() + " ";
    }
    if (d2 != null) {
      ans += this.d2.toString() + " ";
    }
    ans = ans + ")";
    return ans;
  }

}
