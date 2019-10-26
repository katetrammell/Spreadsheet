package edu.cs3500.spreadsheets.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SumFormulaTest {
  BasicSpreadsheet spread1;


  @Before
  public void setUp() {
    spread1 = new BasicSpreadsheet(3, 3);
    spread1.setCell(new BasicDoubleCell(5.0), 0, 0);
    spread1.setCell(new BasicStringCell("hi"), 0, 1);
    spread1.setCell(new BasicStringCell(" there"), 0, 2);

    spread1.setCell(new BasicDoubleCell(3.2), 1, 0);
    spread1.setCell(new BasicBooleanCell(true), 1, 1);
  }

  /**
   * Tests Evaluate with 1 num
   */
  @Test
  public void test1Num() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,0));
    Assert.assertEquals(sum1.evaluate(), 5.0, .00001);
  }

  /**
   * Tests Evaluate with multiple num
   */
  @Test
  public void test2Num() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,0));
    sum1.cells.add(new BasicDoubleCell(2.5));

    Assert.assertEquals(sum1.evaluate(), 7.5, .00001);
  }

  /**
   * Tests Evaluate with negative num
   */
  @Test
  public void testNegNum() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,0));
    sum1.cells.add(new BasicDoubleCell(-10.0));

    Assert.assertEquals(sum1.evaluate(), -5.0, .00001);
  }

  /**
   * Tests Evaluate with no num
   */
  @Test
  public void testNoNum() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,1));
    sum1.cells.add(spread1.getCellAt(0,2));
    Assert.assertEquals(sum1.evaluate(), 0, .00001);
  }

  /**
   * Tests Evaluate with mix of num and non num
   */
  @Test
  public void testMixNum() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,1));
    sum1.cells.add(spread1.getCellAt(0, 0));
    sum1.cells.add(new BasicDoubleCell(4.6));
    sum1.cells.add(spread1.getCellAt(0,2));
    Assert.assertEquals(sum1.evaluate(), 9.6, .00001);
  }

  /**
   * Tests Evaluate with a formula
   */
  @Test
  public void testFormula() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,1));
    sum1.cells.add(spread1.getCellAt(0, 0));
    sum1.cells.add(new BasicDoubleCell(4.6));
    sum1.cells.add(spread1.getCellAt(0,2));
    Assert.assertEquals(sum1.evaluate(), 9.6, .00001);

    SumFormula sum2 = new SumFormula();
    sum2.forms.add(sum1);
    sum2.cells.add(spread1.getCellAt(1, 1));
    sum2.cells.add(new BasicDoubleCell(3.3));

    Assert.assertEquals(sum2.evaluate(), 12.9, .000001);
  }






}
