package edu.cs3500.spreadsheets.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class that tests BasicBooleanCell.
 */
public class BasicBooleanCellTest {

  BasicSpreadsheet spread;
  BasicBooleanCell btrue;
  LessThanFormula form1;
  BasicBooleanCell bFlase;

  @Before
  public void setUp() {
    spread = new BasicSpreadsheet();
    btrue = new BasicBooleanCell(true);
    form1 = new LessThanFormula(16.23, 12.0, spread);
    bFlase = new BasicBooleanCell(form1);
  }

  /**
   * Test the isNumeric() method always returns false.
   */
  @Test
  public void testIsNumeric() {
    Assert.assertEquals(btrue.isNumericValue(), false);
    Assert.assertEquals(bFlase.isNumericValue(), false);
  }

  /**
   * Test the getNumericValue() method always returns the given base.
   */
  @Test
  public void testGetNumericValue() {
    Assert.assertEquals(btrue.getNumericValue(3.789), 3.789, .0001);
    Assert.assertEquals(bFlase.getNumericValue(0.0), 0.0, .0000001);
    Assert.assertEquals(bFlase.getNumericValue(-134.9), -134.9, .0000001);
  }


}
