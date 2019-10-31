
package edu.cs3500.spreadsheets.model;

import org.junit.Before;

/**
 * Class for testing LessThanFormula
 */

public class LessThanFormulaTest {

  BasicSpreadsheet spread1 = new BasicSpreadsheet(3, 3);

  @Before
  public void setUp() {
    spread1.setCell(new BasicDoubleCell(5.0), 0, 0);
    spread1.setCell(new BasicStringCell("hi"), 0, 1);
    spread1.setCell(new BasicStringCell(" there"), 0, 2);
    spread1.setCell(new BasicDoubleCell(10.0), 0, 3);
    spread1.setCell(new BasicDoubleCell(3.2), 1, 0);
    spread1.setCell(new BasicBooleanCell(true), 1, 1);
    spread1.setCell(new BasicDoubleCell(-1.0), 5, 0);
    LessThanFormula f = new LessThanFormula(new Coord)
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

