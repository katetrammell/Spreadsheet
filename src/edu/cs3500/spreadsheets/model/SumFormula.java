package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

public class SumFormula implements Formula<Double> {
  private List<Coord> coords;
  private List<Formula<Double>> forms;
  private List<Double> constants;
  BasicSpreadsheet spread;


  public SumFormula(ArrayList<Coord> c, ArrayList<Formula<Double>> forms, BasicSpreadsheet spread) {
    this.coords = c;
    this.forms = forms;
    this.spread = spread;
    this.constants = new ArrayList<Double>();
  }

  public SumFormula(BasicSpreadsheet s){
    this.coords = new ArrayList<Coord>();
    this.forms = new ArrayList<Formula<Double>>();
    this.spread = s;
    this.constants = new ArrayList<Double>();
  }

  @Override
  public Double evaluate() {
    Double totalSum = 0.0;
    for (Coord c : coords) {
     totalSum += spread.getCellAt(c).getNumericValue(0.0);
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
    String ans = "(Sum : ";
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
  public List<Coord> getCoords() {
    ArrayList<Coord> ans = new ArrayList<Coord>();
    for (Formula f : this.forms) {
      ans.addAll(f.getCoords());
    }
    for (Coord c : this.coords) {
      if (spread.getCellAt(c) != null && spread.getCellAt(c).getFormula() != null) {
        ans.addAll(spread.getCellAt(c).getFormula().getCoords());
      }
    }
    ans.addAll(this.coords);
    return ans;
  }
}
