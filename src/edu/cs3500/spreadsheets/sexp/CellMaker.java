package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.BasicBooleanCell;
import edu.cs3500.spreadsheets.model.BasicDoubleCell;
import edu.cs3500.spreadsheets.model.BasicStringCell;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.ConcatFormula;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.LessThanFormula;
import edu.cs3500.spreadsheets.model.ProductFormula;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.SumFormula;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that utilizes visitor pattern to create cells.
 */
public class CellMaker implements SexpVisitor<Cell> {

  private Spreadsheet spread;


  public CellMaker(Spreadsheet s) {
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
        LessThanFormula lformula;
        try {
          lformula = new LessThanFormula(stringToCoord(l.get(1).toString()),
              stringToCoord(l.get(2).toString()), this.spread);
        } catch (IllegalArgumentException iAE) {
          try {
            lformula = new LessThanFormula(stringToCoord(l.get(1).toString()),
                l.get(2).accept(new CellMaker(this.spread)).getNumericValue(0), this.spread);
          } catch (Exception e1) {
            try {
              lformula = new LessThanFormula(
                  l.get(1).accept(new CellMaker(this.spread)).getNumericValue(0),
                  stringToCoord(l.get(2).toString()), this.spread);
            } catch (Exception e2) {
              try {
                lformula = new LessThanFormula(
                    l.get(1).accept(new CellMaker(this.spread)).getNumericValue(0),
                    l.get(1).accept(new CellMaker(this.spread)).getNumericValue(0), this.spread);
              } catch (Exception e3) {
                throw new IllegalArgumentException("Less than arguments invalid");
              }
            }
          }
        }

        return new BasicBooleanCell(lformula);
      default:
        throw new IllegalArgumentException("invalid symbol");
    }
  }

  /**
   * Turns strings into coordinates.
   *
   * @param s string from file.
   * @return coordinate that reflects the information from string
   */
  private Coord stringToCoord(String s) {
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
      throw new IllegalArgumentException("test");
    }
    return new Coord(col, row);
  }

  /**
   * Iterates through list of sexpressions and adds them to the given formula.
   *
   * @param formula formula to be modified
   * @param l       list of sexpressions to iterate through
   */
  private void addArguments(Formula formula, List<Sexp> l) {
    for (int i = 1; i < l.size(); i++) {
      if (l.get(i).accept(new IsSymbol())) {
        SSymbol currS = (SSymbol) l.get(i);
        if (currS.toString().contains(":")) {
          String[] splitUp = currS.toString().split(":");
          try { // trying form - A3 : B12
            Coord topLeft = stringToCoord(splitUp[0]);
            Coord bottomRight = stringToCoord(splitUp[1]);
            for (int row = topLeft.getY(); row <= bottomRight.getY(); row++) {
              for (int col = topLeft.getX(); col <= bottomRight.getX(); col++) {
                formula.addCoord(new Coord(col, row));
              }
            }
          } catch (Exception e) {
            try { // trying form A:C)
              int col1 = Coord.colNameToIndex(currS.toString().substring(0,1));
              int col2 = Coord.colNameToIndex(currS.toString().substring(2,3));
              for (int r = 1; r <= this.spread.getHeight(); r++) {
                formula.addCoord(new Coord(col1 , r));
                formula.addCoord(new Coord(col2, r));
              }
            } catch (Exception exp) {
              break;
            }
          }
        }
        try {
          Coord c = stringToCoord(l.get(i).toString());
          formula.addCoord(c);
        } catch (IllegalArgumentException e) {
          break;
        }
      } else if (l.get(i).accept(new IsList())) {
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
