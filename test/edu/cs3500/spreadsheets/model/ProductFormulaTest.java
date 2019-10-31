
package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing ProductFormula.
 */

public class ProductFormulaTest {


  BasicSpreadsheet spread1;
  ArrayList<Coord> coords = new ArrayList<Coord>();


  @Before
  public void setUp() {
    spread1 = new BasicSpreadsheet(3, 3);
    spread1.setCell(new BasicDoubleCell(5.0), 5, 5);
    spread1.setCell(new BasicStringCell("hi"), 6, 1);
    spread1.setCell(new BasicStringCell(" there"), 7, 2);
    spread1.setCell(new BasicDoubleCell(10.0), 8, 3);
    spread1.setCell(new BasicDoubleCell(3.2), 1, 9);
    spread1.setCell(new BasicBooleanCell(true), 1, 1);
    spread1.setCell(new BasicDoubleCell(-1.0), 5, 3);
  }

  @Test
  public void testDefault() {
    ProductFormula f = new ProductFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(0.0, f.evaluate(), .001);
  }

  @Test
  public void testDefaultWithNulls() {
    coords.add(new Coord(50, 50));
    ProductFormula f = new ProductFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(0.0, f.evaluate(), .001);
  }

  @Test
  public void testDefaultWithStrings() {
    coords.add(new Coord(1, 6));
    coords.add(new Coord(2, 7));
    ProductFormula f = new ProductFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(0.0, f.evaluate(), .001);
  }

  @Test
  public void testDefaultWithStringsAndBool() {
    coords.add(new Coord(1, 6));
    coords.add(new Coord(2, 7));
    coords.add(new Coord(1, 1));
    ProductFormula f = new ProductFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(0.0, f.evaluate(), .001);
  }

  @Test
  public void testProd() {
    coords.add(new Coord(3, 8));
    coords.add(new Coord(5, 5));
    ProductFormula f = new ProductFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(50.0, f.evaluate(), .001);
  }

  @Test
  public void testProdWithStringsBoolsAndNulls() {
    coords.add(new Coord(3, 8));
    coords.add(new Coord(5, 5));
    coords.add(new Coord(50, 50));
    coords.add(new Coord(1, 6));
    coords.add(new Coord(1, 1));
    ProductFormula f = new ProductFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(50.0, f.evaluate(), .001);
  }


}

