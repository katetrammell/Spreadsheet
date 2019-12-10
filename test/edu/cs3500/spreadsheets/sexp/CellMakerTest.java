package edu.cs3500.spreadsheets.sexp;

import edu.cs3500.spreadsheets.model.BasicBooleanCell;
import edu.cs3500.spreadsheets.model.BasicDoubleCell;
import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.BasicStringCell;
import edu.cs3500.spreadsheets.model.Cell;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Visitor class CellMaker.
 **/

public class CellMakerTest {

  BasicSpreadsheet spread;

  @Before
  public void setUp() {
    spread = new BasicSpreadsheet();
    spread.setCell(new BasicDoubleCell(1.1), 1, 1);
    spread.setCell(new BasicDoubleCell(4.0), 3, 3);
  }

  /**
   * Tests that, when given an Sexp that should result in a product formula,
   * the correct cell is made.
   */
  @Test
  public void testProduct() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("PRODUCT"));
    newList.add(new SNumber(3.4));
    newList.add(new SNumber(2.0));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker(spread));

    Assert.assertEquals((double) c1.getFormula().evaluate(), 6.8, .0001);
  }

  /**
   * Tests that, when given an Sexp that should result in a LessThan formula,
   * the correct cell is made.
   */
  @Test
  public void testLessThan() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("<"));
    newList.add(new SNumber(3.4));
    newList.add(new SNumber(2.0));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker(spread));

    Assert.assertEquals((boolean) c1.getFormula().evaluate(), false);
  }

  /**
   * Tests that, when given an Sexp that should result in a concat formula,
   * the correct cell is made.
   */
  @Test
  public void testConcat() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("CONCAT"));
    newList.add(new SNumber(3.4));
    newList.add(new SNumber(2.0));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker(spread));

    Assert.assertEquals((String) c1.getFormula().evaluate(), "3.42.0");
  }

  /**
   * Tests that, when given an Sexp that should result in a Sum formula,
   * the correct cell is made.
   */
  @Test
  public void testSum() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("SUM"));
    newList.add(new SNumber(3.4));
    newList.add(new SNumber(2.0));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker(spread));

    Assert.assertEquals((double) c1.getFormula().evaluate(), 5.4, .0001);
  }

  /**
   * Tests that, when given an Sexp that should result in a number cell,
   * the correct cell is made.
   */
  @Test
  public void testNumber() {
    SNumber num = new SNumber(1);
    Cell c1 = num.accept(new CellMaker(spread));

    Assert.assertEquals(c1, new BasicDoubleCell(1.0));
  }

  /**
   * Tests that, when given an Sexp that should result in a boolean cell,
   * the correct cell is made.
   */
  @Test
  public void testBoolean() {
    SBoolean bool = new SBoolean(true);
    Cell c1 = bool.accept(new CellMaker(spread));

    Assert.assertEquals(c1, new BasicBooleanCell(true));

    SBoolean bool2 = new SBoolean(false);
    Cell c2 = bool2.accept(new CellMaker(spread));

    Assert.assertEquals(c2, new BasicBooleanCell(false));
  }

  /**
   * Tests that, when given an Sexp that should result in a string cell,
   * the correct cell is made.
   */
  @Test
  public void testString() {
    SString str = new SString("abc");
    Cell c1 = str.accept(new CellMaker(spread));
    Assert.assertEquals(c1, new BasicStringCell("abc"));
  }

  /**
   * Tests that, when given an Sexp that is an SSymbol by itself (not in an SList),
   * an exception is thrown.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testSymbol() {
    SSymbol sym = new SSymbol("?");
    Cell c1 = sym.accept(new CellMaker(spread));
  }

  /**
   * Tests a single column reference
   */
  @Test
  public void testColRefSingle() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("SUM"));
    newList.add(new SSymbol("A"));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker(spread));
    Assert.assertEquals((Double)c1.getFormula().evaluate(), 1.1, .00001);
  }


  /**
   * Tests a multiple column reference
   */
  @Test
  public void testColRefMultiple() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("SUM"));
    newList.add(new SSymbol("A:C"));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker(spread));
    Assert.assertEquals((Double)c1.getFormula().evaluate(), 5.1, .00001);
  }


  /**
   * Tests a empty column reference
   */
  @Test
  public void testColRefEmpty() {
    ArrayList<Sexp> newList = new ArrayList<Sexp>();
    newList.add(new SSymbol("SUM"));
    newList.add(new SSymbol("B"));

    SList sList1 = new SList(newList);

    Cell c1 = sList1.accept(new CellMaker(spread));
    Assert.assertEquals((Double)c1.getFormula().evaluate(), 0.0, .00001);
  }



}
