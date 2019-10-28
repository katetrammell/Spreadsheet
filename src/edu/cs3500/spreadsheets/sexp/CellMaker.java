package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.*;

import java.util.List;

public class CellMaker implements SexpVisitor<Cell>{
  @Override
  public Cell visitBoolean(boolean b) {
    return new BasicBooleanCell(b);
  }

  @Override
  public Cell visitNumber(double d) {
    return new BasicDoubleCell(d);
  }

  @Override
  public Cell visitSList(List<Sexp> l) {
    if (l.size() < 3) {
      throw new IllegalArgumentException(
          "need at least one symbol and two arguments");
    }
    if (!l.get(0).accept(new IsSymbol())) {
      throw new IllegalArgumentException(
          "first item is not a symbol");
    }
    SSymbol first = (SSymbol) l.get(0);
    switch (first.name) {
      case "SUM":
        SumFormula formula = new SumFormula();
        this.addArguments(formula, l);
        return new BasicDoubleCell(formula);
      case "PRODUCT":
       ProductFormula form = new ProductFormula();
       this.addArguments(form, l);
       return new BasicDoubleCell(form);
      case "CONCAT":
        //TODO: Implmenet concat formula
        /*
        formula = new ConcatFormula();
         */
        break;
      case "<":
        if (l.size() != 3) {
          throw new IllegalArgumentException();
        }
        Cell c1 = l.get(1).accept(new CellMaker());
        Cell c2 = l.get(2).accept(new CellMaker());
        LessThanFormula lformula = new LessThanFormula(c1, c2);
        return new BasicBooleanCell(lformula);
      default:
        throw new IllegalArgumentException("invalid symbol");
    }
    throw new IllegalArgumentException("invalid symbol");
  }

  /**
   * Iterates through list of sexpressions and adds them to the given formula.
   * @param formula formula to be modified
   * @param l list of sexpressions to iterate through
   */
  private void addArguments(Formula formula, List<Sexp> l) {
    for (int i = 1; i < l.size(); i++) {
      if (l.get(i).accept(new IsList())) {
        formula.addFormula(l.get(i).accept(new CellMaker()).getFormula());
      } else {
        formula.addCell(l.get(i).accept(new CellMaker()));
      }
    }
  }

  @Override
  public Cell visitSymbol(String s) {
    throw new IllegalArgumentException("Can't have just a symbol");
  }

  @Override
  public Cell visitString(String s) {
    return new BasicStringCell(s);
  }
}
