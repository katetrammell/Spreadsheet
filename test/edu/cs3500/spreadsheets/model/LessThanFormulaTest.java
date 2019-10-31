
package edu.cs3500.spreadsheets.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing LessThanFormula
 */

public class LessThanFormulaTest {

  BasicSpreadsheet spread1 = new BasicSpreadsheet(3, 3);

  @Before
  public void setUp() {
    spread1.setCell(new BasicDoubleCell(5.0), 1, 2);
    spread1.setCell(new BasicStringCell("hi"), 3, 1);
    spread1.setCell(new BasicStringCell(" there"), 5, 2);
    spread1.setCell(new BasicDoubleCell(10.0), 8, 3);
    spread1.setCell(new BasicDoubleCell(3.2), 1, 9);
    spread1.setCell(new BasicBooleanCell(true), 1, 1);
    spread1.setCell(new BasicDoubleCell(-1.0), 5, 11);
   // LessThanFormula f = new LessThanFormula(new Coord);
  }

  @Test
  public void testLessThan() {
    Formula t = new LessThanFormula(new Coord(11, 5),
        new Coord(9, 1), spread1);
    spread1.setCell(new BasicBooleanCell(t), 5, 5);
    Assert.assertTrue(
        (Boolean)spread1.getCellAt(5, 5).getFormula().evaluate());
  }

  @Test
  public void testLessF() {
    Formula t = new LessThanFormula(new Coord(9, 1),
        new Coord(11, 5), spread1);
    spread1.setCell(new BasicBooleanCell(t), 5, 5);
    Assert.assertFalse(
        (Boolean)spread1.getCellAt(5, 5).getFormula().evaluate());
  }

  // two strings
  @Test(expected = IllegalArgumentException.class)
  public void testIllegal() {
    Formula t = new LessThanFormula(new Coord(1, 3),
        new Coord(5, 2), spread1);
  }

  // string and boolean
  @Test(expected = IllegalArgumentException.class)
  public void testIllegal2() {
    Formula t = new LessThanFormula(new Coord(1, 3),
        new Coord(1, 1), spread1);
  }

  // boolean and double
  @Test(expected = IllegalArgumentException.class)
  public void testIllegal3() {
    Formula t = new LessThanFormula(new Coord(1, 1),
        new Coord(3, 8), spread1);
  }

  // nulls
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNull() {
    Formula t = new LessThanFormula(new Coord(20, 20),
        new Coord(40, 50), spread1);
  }






  /*
  @Test(expected = IllegalArgumentException.class)
  public void testError() {
    LessThanFormula ltform = new LessThanFormula(spread1.getCellAt(0, 1),
        spread1.getCellAt(5, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testError2() {
    LessThanFormula ltform = new LessThanFormula(spread1.getCellAt(5, 0),
        spread1.getCellAt(0, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testError3() {
    LessThanFormula ltform = new LessThanFormula(spread1.getCellAt(0, 2),
        spread1.getCellAt(0, 1));
  }

  @Test
  public void testTrue() {
    LessThanFormula ltform = new LessThanFormula(spread1.getCellAt(5, 0),
        spread1.getCellAt(0, 0));
    Assert.assertTrue(ltform.evaluate());
  }

  // tests opposite of above ^
  @Test
  public void testFalse() {
    LessThanFormula ltform = new LessThanFormula(spread1.getCellAt(0, 0),
        spread1.getCellAt(5, 0));
    Assert.assertFalse(ltform.evaluate());
  }
   */
}

