package edu.cs3500.spreadsheets.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class that tests BasicStringCell().
 */
public class BasicStringCellTest {
  BasicSpreadsheet spread;
  BasicStringCell c1;
  ConcatFormula form1;
  BasicStringCell c2;

  @Before
  public void setUp() {
    spread = new BasicSpreadsheet();
    c1 = new BasicStringCell("hi there");
    form1 = new ConcatFormula(spread);
    form1.addConstant("my ");
    form1.addConstant("name ");
    form1.addConstant("is ");
    form1.addConstant("kate.");
    c2 = new BasicStringCell(form1);
  }

  /**
   * Test the isNumeric() method always returns false.
   */
  @Test
  public void testIsNumeric() {
    Assert.assertEquals(c1.isNumericValue(), false);
    Assert.assertEquals(c2.isNumericValue(), false);
  }

  /**
   * Test the getNumericValue() method always returns given base.
   */
  @Test
  public void testGetNumericValue() {
    Assert.assertEquals(c1.getNumericValue(3.789), 3.789, .0001);
    Assert.assertEquals(c2.getNumericValue(0.0), 0.0, .0000001);
    Assert.assertEquals(c2.getNumericValue(-134.9), -134.9, .0000001);
  }


}
