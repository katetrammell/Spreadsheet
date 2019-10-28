package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

public class ProductFormula implements Formula<Double> {

  private List<Cell> cells;
  private List<Formula<Double>> forms;

  public ProductFormula(ArrayList<Cell> cells, ArrayList<Formula<Double>> forms) {
    this.cells = cells;
    this.forms = forms;
  }

  public ProductFormula() {
    this.cells = new ArrayList<Cell>();
    this.forms = new ArrayList<Formula<Double>>();
  }

  @Override
  public Double evaluate() {
    boolean hitNumericYet = false;

    Double totalProduct = 0.0;
    for (Cell c : cells) {
      if (hitNumericYet) {
        totalProduct = totalProduct * c.getNumericValue(1.0);
      } else if (c.isNumericValue()) {
        hitNumericYet = true;
        totalProduct = c.getNumericValue(1.0);
      }
    }
    for (Formula<Double> f : forms) {
      totalProduct = totalProduct * f.evaluate();
    }

    return totalProduct;
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