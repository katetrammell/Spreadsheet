package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to facilitate concatenating cells.
 */
public class ConcatFormula implements Formula<String> {

  private List<Coord> coords;
  private List<Formula> forms;
  private List<String> constants;
  private BasicSpreadsheet spread;

  /**
   * Constructor with just a spreadsheet.
   *
   * @param s spredsheet.
   */
  public ConcatFormula(BasicSpreadsheet s) {
    this.coords = new ArrayList<Coord>();
    this.forms = new ArrayList<Formula>();
    this.constants = new ArrayList<String>();
    this.spread = s;
  }

  /**
   * Constructor with coordinates and spreadshet.
   *
   * @param c arraylsit of coordinates on spreadsheet.
   * @param s spreadsheet in question.
   */
  public ConcatFormula(ArrayList<Coord> c, BasicSpreadsheet s) {
    this.coords = c;
    this.forms = new ArrayList<Formula>();
    this.spread = s;
    this.constants = new ArrayList<String>();

  }

  /**
   * Constructor with coordaintes, formulas, and spreadsheet.
   *
   * @param c coordiantes on spreadsheet.
   * @param f formulas that are relevant.
   * @param s spreadhseet to be used.
   */
  public ConcatFormula(List<Coord> c, List<Formula> f, BasicSpreadsheet s) {
    this.coords = c;
    this.spread = s;
    this.forms = f;
    this.constants = new ArrayList<String>();

  }


  @Override
  public String evaluate() {
    String ans = "";
    for (Coord c : coords) {
      if (spread.getCellAt(c) != null) {
        ans = ans + spread.getCellAt(c).toString();
      }
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
    String ans = "=(CONCAT ";
    for (Coord c : this.coords) {
      ans = ans + c.toString() + " ";

    }
    for (Formula f : this.forms) {
      ans = ans + f.toString() + " ";
    }
    for (String s : constants) {
      ans = ans + s;
    }
    ans += ")";
    return ans;
  }

}
