package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Formula class that enables the product function.
 */
public class ProductFormula implements Formula<Double> {

  private List<Coord> coords;
  private List<Formula<Double>> forms;
  private List<Double> constants;
  private BasicSpreadsheet spread;

  /**
   * Constructor for the class.
   * @param coords list of coordinates
   * @param forms list of formulas to be evaluates
   * @param spread spreadsheet
   */
  public ProductFormula(ArrayList<Coord> coords, ArrayList<Formula<Double>> forms,
      BasicSpreadsheet spread) {
    this.coords = coords;
    this.forms = forms;
    this.spread = spread;
    this.constants = new ArrayList<Double>();
  }

  /**
   * Constructor for the class.
   * @param c list of coordinates
   * @param spread spreadsheet
   */
  public ProductFormula(ArrayList<Coord> c, BasicSpreadsheet spread) {
    this.coords = c;
    this.forms = new ArrayList<Formula<Double>>();
    this.constants = new ArrayList<Double>();
    this.spread = spread;
  }

  /**
   * Constructor for the class.
   * @param spread spreadsheet
   */
  public ProductFormula( BasicSpreadsheet spread) {
    this.coords = new ArrayList<Coord>();
    this.forms = new ArrayList<Formula<Double>>();
    this.constants = new ArrayList<Double>();
    this.spread = spread;
  }


  @Override
  public Double evaluate() {
    boolean hitNumericYet = false;

    Double totalProduct = 1.0;
    for (Coord c : coords) {
      if (spread.getCellAt(c) != null) {
        if (hitNumericYet) {
          totalProduct = totalProduct * spread.getCellAt(c).getNumericValue(1.0);
        } else if (spread.getCellAt(c).isNumericValue()) {
          hitNumericYet = true;
          totalProduct = spread.getCellAt(c).getNumericValue(1.0);
        }
      }
    }
    for (Formula<Double> f : forms) {
      hitNumericYet = true;
      totalProduct = totalProduct * f.evaluate();
    }
    for (Double d : constants) {
      hitNumericYet = true;
      totalProduct = totalProduct * d;
    }
    if (!hitNumericYet) {
      return 0.0;
    }

    return totalProduct;
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


  @Override
  public String toString() {
    String ans = "(Product : ";
    for (Coord c : this.coords) {
      ans += c.toString() + " ";
    }
    for (Formula<Double> f : this.forms) {
      ans += f.toString() + " ";
    }
    for (Double d : constants) {
      ans += d.toString() + " ";
    }
    ans += ")";
    return ans;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof ProductFormula)) {
      return false;
    } else {
      ProductFormula otherP = (ProductFormula) other;
      return this.coords.containsAll(((ProductFormula) other).coords)
          && otherP.coords.containsAll(this.coords)
          && this.forms.containsAll(otherP.forms)
          && otherP.forms.containsAll(this.forms)
          && this.constants.containsAll(otherP.constants)
          && otherP.constants.containsAll(this.constants);
    }
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

}