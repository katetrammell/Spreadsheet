package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

public class SumFormula implements Formula<Double> {
  private List<Cell> cells;
  private List<Formula<Double>> forms;


  public SumFormula(ArrayList<Cell> cells, ArrayList<Formula<Double>> forms) {
    this.cells = cells;
    this.forms = forms;
  }

  public SumFormula(){
    this.cells = new ArrayList<Cell>();
    this.forms = new ArrayList<Formula<Double>>();
  }

  @Override
  public Double evaluate() {
    Double totalSum = 0.0;
    for (Cell c : cells) {
     totalSum += c.getNumericValue(0.0);
    }
    for (Formula<Double> f : forms) {
      totalSum += f.evaluate();
    }
    return totalSum;
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
