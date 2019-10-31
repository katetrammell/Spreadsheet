package edu.cs3500.spreadsheets.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class that tests BasicDoubleCell.
 */
public class BasicDoubleCellTest {

  BasicSpreadsheet spread;
  BasicDoubleCell c1;
  ProductFormula form1;
  BasicDoubleCell c2;

  @Before
  public void setUp() {
    spread = new BasicSpreadsheet();
    c1 = new BasicDoubleCell(2.34);
    form1 = new ProductFormula(spread);
    form1.addConstant(1.0);
    form1.addConstant(4.0);
    form1.addConstant(1.0);
    form1.addConstant(3.0);
    c2 = new BasicDoubleCell(form1);
  }

  /**
   * Test the isNumeric() method always returns true.
   */
  @Test
  public void testIsNumeric() {
    Assert.assertEquals(c1.isNumericValue(), true);
    Assert.assertEquals(c2.isNumericValue(), true);
  }

  /**
   * Test the isNumeric() method returns the evaluation of the formula if the formula
   * is not null, and returns the value otherwise.
   */
  @Test
  public void testGetNumericValue() {
    Assert.assertEquals(c1.getNumericValue(3.789), 2.34, .0001);
    Assert.assertEquals(c2.getNumericValue(0.0), 12.0, .0000001);
    Assert.assertEquals(c2.getNumericValue(-134.9), 12.0, .0000001);
  }

}
