package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.Cell;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the Visitor class CellMaker.
 */
public class CellMakerTest {

  @Test
  public void testProduct() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("PRODUCT"));
    newList.add(new SNumber(3.4));
    newList.add(new SNumber(2.0));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker());

    Assert.assertEquals((double)c1.getFormula().evaluate(), 6.8, .0001);
  }

  @Test
  public void testLessThan() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("<"));
    newList.add(new SNumber(3.4));
    newList.add(new SNumber(2.0));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker());

    Assert.assertEquals((boolean)c1.getFormula().evaluate(), false);
  }

  @Test
  public void testConcat() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("="));
    newList.add(new SSymbol("("));
    newList.add(new SSymbol("CONCAT"));
    newList.add(new SNumber(3.4));
    newList.add(new SNumber(2.0));
    newList.add(new SSymbol(")"));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker());

    Assert.assertEquals((String) c1.getFormula().evaluate(), "3.42.0");
  }

  @Test
  public void testSum() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("SUM"));
    newList.add(new SNumber(3.4));
    newList.add(new SNumber(2.0));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker());

    Assert.assertEquals((double)c1.getFormula().evaluate(), 5.4, .0001);
  }
}
