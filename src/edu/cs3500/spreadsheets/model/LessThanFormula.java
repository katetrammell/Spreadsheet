package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent lessThanFormula.
 */
public class LessThanFormula implements Formula<Boolean> {

  private Coord coord1;
  private Coord coord2;
  private BasicSpreadsheet spread;

  public LessThanFormula(Coord coord1, Coord coord2, BasicSpreadsheet spread) {
    this.spread = spread;
    if (!spread.getCellAt(coord1).isNumericValue() | !spread.getCellAt(coord2).isNumericValue()) {
      throw new IllegalArgumentException();
    }
    this.coord1 = coord1;
    this.coord2 = coord2;
  }


  @Override
  public Boolean evaluate() {
    // casted because at this point we know it is a double
    return (double) spread.getCellAt(coord1).getValue()
        < (double) spread.getCellAt(coord2).getValue();
  }

  @Override
  public void addFormula(Formula form) {
    throw new IllegalCallerException("LessThanFormula doesn't contain formula");
  }

  @Override
  public void addCoord(Coord c) {
    throw new IllegalCallerException("LessThanFormula has to be intialized with cells");
  }

  @Override
  public List<Coord> getCoords() {
    ArrayList<Coord> ans = new ArrayList<Coord>();
    ans.add(this.coord1);
    ans.add(this.coord2);
    if (spread.getCellAt(coord1) != null && spread.getCellAt(coord1).getFormula() != null) {
      ans.addAll(spread.getCellAt(coord1).getFormula().getCoords());
    }
    if (spread.getCellAt(coord2) != null && spread.getCellAt(coord2).getFormula() != null) {
      ans.addAll(spread.getCellAt(coord2).getFormula().getCoords());
    }
    return ans;
  }

  @Override
  public String toString() {
    String ans = "(Less than : ";
    ans += this.coord1.toString() + " ";
    ans += this.coord2.toString() + ")";
    return ans;
  }

}
