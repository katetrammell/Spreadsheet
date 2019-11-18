package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to facilitate summing cells.
 */
public class SumFormula implements Formula<Double> {

  private List<Coord> coords;
  private List<Formula<Double>> forms;
  private List<Double> constants;
  private Spreadsheet spread;


  /**
   * Constructor for the class.
   * @param c list of coordinates
   * @param forms list of formulas
   * @param spread spreadsheet
   */
  public SumFormula(ArrayList<Coord> c, ArrayList<Formula<Double>> forms, Spreadsheet spread) {
    this.coords = c;
    this.forms = forms;
    this.spread = spread;
    this.constants = new ArrayList<Double>();
  }

  /**
   * Constructor for the class.
   * @param s spreadsheet
   */
  public SumFormula(Spreadsheet s) {
    this.coords = new ArrayList<Coord>();
    this.forms = new ArrayList<Formula<Double>>();
    this.spread = s;
    this.constants = new ArrayList<Double>();
  }

  @Override
  public Double evaluate() {
    Double totalSum = 0.0;
    for (Coord c : coords) {
      if (spread.getCellAt(c) != null) {
        totalSum += spread.getCellAt(c).getNumericValue(0.0);
      }
    }
    for (Formula<Double> f : forms) {
      totalSum += f.evaluate();
    }
    for (Double d : constants) {
      totalSum += d;
    }
    return totalSum;
  }

  @Override
  public void addFormula(Formula form) {
    this.forms.add(form);

  }

  @Override
  public void addCoord(Coord c) {

    this.coords.add(c);
  }

  @Override
  public void addConstant(Object t) {
    if (!(t instanceof Double)) {
      throw new IllegalArgumentException("not a num");
    }
    this.constants.add((Double) t);

  }

  @Override
  public String toString() {
    String ans = "=(SUM ";
    for (Coord c : this.coords) {
      ans += c.toString() + " ";
    }
    for (Formula f : this.forms) {
      ans += f.toString() + " ";
    }
    for (Double d : constants) {
      ans += d.toString() + " ";
    }
    ans += ")";
    return ans;
  }

  @Override
  public HashMap<Coord, Integer> getCoords(HashMap<Coord, HashMap<Coord, Integer>> currHash) {
    HashMap<Coord, Integer> ans = new HashMap<Coord, Integer>();
    for (Formula<?> f : this.forms) {
      ans.putAll(f.getCoords(currHash));
    }
    for (Coord c : this.coords) {
      if (c != null) {
        if (currHash.containsKey(c)) {
          ans.putAll(currHash.get(c));
        } else {
          Cell bs = spread.getCellAt(c);
          if (bs != null && bs.getFormula() != null) {
            Formula<?> form = bs.getFormula();
            ans.putAll(form.getCoords(currHash));
          }
        }
      }
    }
    for (Coord cord : this.coords) {
      ans.put(cord, 0);
    }
    return ans;
  }

}
