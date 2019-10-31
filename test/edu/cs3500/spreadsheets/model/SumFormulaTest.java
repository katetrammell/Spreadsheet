package edu.cs3500.spreadsheets.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasas for testing sumFormulaTest
 */
public class SumFormulaTest {
  BasicSpreadsheet spread1;
  List<Coord> coords = new ArrayList<Coord>();
  List<Formula<Double>> fs = new ArrayList<>();

  @Before
  public void setUp() {
    spread1 = new BasicSpreadsheet(3, 3);
    spread1.setCell(new BasicDoubleCell(5.0), 1, 1);
    spread1.setCell(new BasicStringCell("hi"), 2, 1);
    spread1.setCell(new BasicStringCell(" there"), 3, 2);

    spread1.setCell(new BasicDoubleCell(3.2), 1, 3);
    spread1.setCell(new BasicBooleanCell(true), 4, 1);
  }

  @Test
  public void oneNum() {
    coords.add(new Coord(3, 1));
    SumFormula f = new SumFormula((ArrayList<Coord>) coords, new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(3.2, f.evaluate(),.0001);
  }

  @Test
  public void twoNum() {
    coords.add(new Coord(3, 1));
    coords.add(new Coord(1, 1));
    SumFormula f = new SumFormula((ArrayList<Coord>) coords, new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(8.2, f.evaluate(),.0001);
  }

  @Test
  public void twoNumWithString() {
    coords.add(new Coord(3, 1));
    coords.add(new Coord(1, 1));
    coords.add(new Coord(2, 3));
    SumFormula f = new SumFormula((ArrayList<Coord>) coords, new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(8.2, f.evaluate(),.0001);
  }

  @Test
  public void twoNumWithStringBoolean() {
    coords.add(new Coord(3, 1));
    coords.add(new Coord(1, 1));
    coords.add(new Coord(2, 3));
    coords.add(new Coord(1, 4));
    SumFormula f = new SumFormula((ArrayList<Coord>) coords, new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(8.2, f.evaluate(),.0001);
  }

  @Test
  public void twoNumWithStringBooleanNull() {
    coords.add(new Coord(3, 1));
    coords.add(new Coord(1, 1));
    coords.add(new Coord(2, 3));
    coords.add(new Coord(1, 4));
    coords.add(new Coord(20, 20));
    SumFormula f = new SumFormula((ArrayList<Coord>) coords, new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(8.2, f.evaluate(),.0001);
  }

  @Test
  public void testDefault() {
    SumFormula f = new SumFormula((ArrayList<Coord>) coords, new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(0.0, f.evaluate(), .001);
  }






/**
   * Tests Evaluate with 1 num
   *//*

  @Test
  public void test1Num() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,0));
    Assert.assertEquals(sum1.evaluate(), 5.0, .00001);
  }

  */
/**
   * Tests Evaluate with multiple num
   *//*

  @Test
  public void test2Num() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,0));
    sum1.cells.add(new BasicDoubleCell(2.5));

    Assert.assertEquals(sum1.evaluate(), 7.5, .00001);
  }

  */
/**
   * Tests Evaluate with negative num
   *//*

  @Test
  public void testNegNum() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,0));
    sum1.cells.add(new BasicDoubleCell(-10.0));

    Assert.assertEquals(sum1.evaluate(), -5.0, .00001);
  }

  */
/**
   * Tests Evaluate with no num
   *//*

  @Test
  public void testNoNum() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,1));
    sum1.cells.add(spread1.getCellAt(0,2));
    Assert.assertEquals(sum1.evaluate(), 0, .00001);
  }

  */
/**
   * Tests Evaluate with mix of num and non num
   *//*

  @Test
  public void testMixNum() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,1));
    sum1.cells.add(spread1.getCellAt(0, 0));
    sum1.cells.add(new BasicDoubleCell(4.6));
    sum1.cells.add(spread1.getCellAt(0,2));
    Assert.assertEquals(sum1.evaluate(), 9.6, .00001);
  }

  */
/**
   * Tests Evaluate with a formula
   *//*

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


*/




}
