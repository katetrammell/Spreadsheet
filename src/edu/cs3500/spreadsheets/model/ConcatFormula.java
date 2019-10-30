package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

public class ConcatFormula implements Formula<String>{
  private List<Coord> coords;
  private List<Formula> forms;
  private List<String> constants;
  private BasicSpreadsheet spread;

  public ConcatFormula(BasicSpreadsheet s) {
    this.coords = new ArrayList<Coord>();
    this.forms = new ArrayList<Formula>();
    this.constants = new ArrayList<String>();
    this.spread = s;
  }

  public ConcatFormula(ArrayList<Coord> c, BasicSpreadsheet s) {
    this.coords = c;
    this.forms = new ArrayList<Formula>();
    this.spread = s;
    this.constants = new ArrayList<String>();
  }

  public ConcatFormula(List<Coord> c, List<Formula> f, BasicSpreadsheet s){
      this.coords = c;
      this.spread = s;
      this.forms = f;
      this.constants = new ArrayList<String>();
  }




  @Override
  public String evaluate() {
    String ans = "";
    for (Coord c : coords) {
      ans = ans + spread.getCellAt(c).toString();
    }
    for (Formula f : forms) {
      ans = ans + f.evaluate();
    }
    for (String s : constants) {
      ans = ans + s;
    }
    return ans;
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
  public void addConstant(Object s) {
    this.constants.add(s.toString());

  }

  @Override
  public List<Coord> getCoords() {
    ArrayList<Coord> ans = new ArrayList<Coord>();
    for (Formula<String> f : this.forms) {
      ans.addAll(f.getCoords());
    }
    for (Coord c : this.coords) {
      Cell bs = spread.getCellAt(c);
      if (bs != null && bs.getFormula() != null) {
        ans.addAll(bs.getFormula().getCoords());
      }
    }
    ans.addAll(this.coords);
    return ans;
  }

  @Override
  public String toString() {
    String ans = "(Concat : ";
    for (Coord c : this.coords) {
      ans = ans + c.toString() + " ";
    }
    for (Formula f : this.forms) {
      ans = ans + f.toString() + " ";
    }
    for (String s: constants){
      ans = ans + s;
    }
    ans += ")";
    return ans;
  }

}
