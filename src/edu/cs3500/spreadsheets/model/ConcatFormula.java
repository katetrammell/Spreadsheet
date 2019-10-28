package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

public class ConcatFormula implements Formula<String>{
  private List<Cell> cells;
  private List<Formula> forms;

  public ConcatFormula() {
    this.cells = new ArrayList<Cell>();
    this.forms = new ArrayList<Formula>();
  }

  public ConcatFormula(List<Cell> c, List<Formula> f){
    this.cells = c;
    this.forms = f;
  }



  @Override
  public String evaluate() {
    String ans = "";
    for (Cell c : cells) {
      ans = ans + c.toString();
    }
    for (Formula f : forms) {
      ans = ans + f.evaluate().toString();
    }
    return ans;
  }

  @Override
  public void addFormula(Formula form) {
    this.forms.add(form);
  }

  @Override
  public void addCell(Cell cell) {
    this.cells.add(cell);
  }


}
