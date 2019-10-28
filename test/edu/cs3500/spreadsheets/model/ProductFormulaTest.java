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


  @Before
  public void setUp() {
    spread1 = new BasicSpreadsheet(3, 3);
    spread1.setCell(new BasicDoubleCell(5.0), 0, 0);
    spread1.setCell(new BasicStringCell("hi"), 0, 1);
    spread1.setCell(new BasicStringCell(" there"), 0, 2);
    spread1.setCell(new BasicDoubleCell(10.0), 0, 3);
    spread1.setCell(new BasicDoubleCell(3.2), 1, 0);
    spread1.setCell(new BasicBooleanCell(true), 1, 1);
    spread1.setCell(new BasicDoubleCell(-1.0), 5, 0);
  }

  @Test
  public void testProduct() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    cells.add(spread1.getCellAt(0,0));
    ProductFormula prodForm1 = new ProductFormula(cells, new ArrayList<Formula<Double>>());

    Assert.assertEquals(prodForm1.evaluate(), 5.0, .001);
  }

  @Test
  public void testProdBoolString() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    cells.add(spread1.getCellAt(1, 1));
    cells.add(spread1.getCellAt(0, 1));
    ProductFormula prodForm1 = new ProductFormula(cells, new ArrayList<Formula<Double>>());
    Assert.assertEquals(0.0, prodForm1.evaluate(), .001);
  }

  @Test
  public void testProdTwoDoubles() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    cells.add(spread1.getCellAt(0, 0));
    cells.add(spread1.getCellAt(0, 3));
    ProductFormula prodForm1 = new ProductFormula(cells, new ArrayList<Formula<Double>>());
    Assert.assertEquals(50.0, prodForm1.evaluate(), .001);
  }

  @Test
  public void testNegN() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    cells.add(spread1.getCellAt(0, 0));
    cells.add(spread1.getCellAt(5, 0));
    ProductFormula prodForm1 = new ProductFormula(cells, new ArrayList<Formula<Double>>());
    Assert.assertEquals(-5.0, prodForm1.evaluate(), .001);
  }

  @Test
  public void testNoArgs() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    ProductFormula prodForm1 = new ProductFormula(cells, new ArrayList<Formula<Double>>());
    Assert.assertEquals(0.0, prodForm1.evaluate(), .001);
  }

  @Test
  public void testBoolStringN() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    // doubles
    cells.add(spread1.getCellAt(0, 0));
    cells.add(spread1.getCellAt(0, 3));
    //boolean
    cells.add(spread1.getCellAt(1, 1));
    //string
    cells.add(spread1.getCellAt(0, 1));
    ProductFormula prodForm1 = new ProductFormula(cells, new ArrayList<Formula<Double>>());
    Assert.assertEquals(50.0, prodForm1.evaluate(), .001);
  }

}
