package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.*;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellMaker implements SexpVisitor<Cell>{
  BasicSpreadsheet spread;

  public CellMaker(BasicSpreadsheet s) {
    this.spread = s;
  }
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
    if (l.size() < 2) {
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
        SumFormula formula = new SumFormula(this.spread);
        this.addArguments(formula, l);
        return new BasicDoubleCell(formula);
      case "PRODUCT":
       ProductFormula form = new ProductFormula(this.spread);
       this.addArguments(form, l);
       return new BasicDoubleCell(form);
      case "CONCAT":
        ConcatFormula cForm = new ConcatFormula(this.spread);
        this.addArguments(cForm, l);
        return new BasicStringCell(cForm);
      case "<":
        if (l.size() != 3) {
          throw new IllegalArgumentException();
        }
        Coord c1 = StringToCoord(l.get(1).toString());
        Coord c2 = StringToCoord(l.get(2).toString());
        LessThanFormula lformula = new LessThanFormula(c1, c2, this.spread);
        return new BasicBooleanCell(lformula);
      default:
        throw new IllegalArgumentException("invalid symbol");
    }
  }

  private Coord StringToCoord(String s) {
    Scanner scan = new Scanner(s);
    final Pattern cellRef = Pattern.compile("([A-Za-z]+)([1-9][0-9]*)");
    scan.useDelimiter("\\s+");
    int col;
    int row;
    while (scan.hasNext("#.*")) {
      scan.nextLine();
      scan.skip("\\s*");
    }
    String cell = scan.next();
    Matcher m = cellRef.matcher(cell);
    if (m.matches()) {
      col = Coord.colNameToIndex(m.group(1));
      row = Integer.parseInt(m.group(2));
    } else {
      throw new IllegalArgumentException();
    }
    return new Coord(col, row);
  }

  /**
   * Iterates through list of sexpressions and adds them to the given formula.
   * @param formula formula to be modified
   * @param l list of sexpressions to iterate through
   */
  private void addArguments(Formula formula, List<Sexp> l) {
    for (int i = 1; i < l.size(); i++) {
      if (l.get(i).accept(new IsSymbol())) {
        formula.addCoord(StringToCoord(l.get(i).toString()));
      }
      else if (l.get(i).accept(new IsList())) {
        formula.addFormula(l.get(i).accept(new CellMaker(this.spread)).getFormula());
      } else {
        formula.addConstant(l.get(i).accept(new CellMaker(this.spread)).getValue());
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
