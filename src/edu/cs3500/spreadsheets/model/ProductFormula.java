package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Formula class that enables the product function.
 */
public class ProductFormula implements Formula<Double> {

  private List<Coord> coords;
  private List<Formula<Double>> forms;
  private List<Double> constants;
  private BasicSpreadsheet spread;

  public ProductFormula(ArrayList<Coord> coords, ArrayList<Formula<Double>> forms,
      BasicSpreadsheet spread) {
    this.coords = coords;
    this.forms = forms;
    this.spread = spread;
    this.constants = new ArrayList<Double>();
  }

  public ProductFormula(ArrayList<Coord> c, BasicSpreadsheet spread) {
    this.coords = c;
    this.forms = new ArrayList<Formula<Double>>();
    this.constants = new ArrayList<Double>();
    this.spread = spread;
  }

  public ProductFormula( BasicSpreadsheet spread) {
    this.coords = new ArrayList<Coord>();
    this.forms = new ArrayList<Formula<Double>>();
    this.constants = new ArrayList<Double>();
    this.spread = spread;
  }


  @Override
  public Double evaluate() {
    boolean hitNumericYet = false;

    Double totalProduct = 0.0;
    for (Coord c : coords) {
      if (hitNumericYet) {
        totalProduct = totalProduct * spread.getCellAt(c).getNumericValue(1.0);
      } else if (spread.getCellAt(c).isNumericValue()) {
        hitNumericYet = true;
        totalProduct = spread.getCellAt(c).getNumericValue(1.0);
      }
    }
    for (Formula<Double> f : forms) {
      totalProduct = totalProduct * f.evaluate();
    }
    for (Double d : constants) {
      totalProduct = totalProduct * d;
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
  public List<Coord> getCoords() {
    ArrayList<Coord> ans = new ArrayList<Coord>();
    for (Formula<Double> f : this.forms) {
      ans.addAll(f.getCoords());
    }
    ans.addAll(this.coords);
    for (Coord c : this.coords) {
      if (spread.getCellAt(c) != null && spread.getCellAt(c).getFormula() != null) {
        ans.addAll(spread.getCellAt(c).getFormula().getCoords());
      }
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

}