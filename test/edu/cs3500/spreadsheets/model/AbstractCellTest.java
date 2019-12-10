package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A class that tests AbstractCell.
 */
public class AbstractCellTest {

  BasicSpreadsheet spread;
  BasicDoubleCell d1;
  ProductFormula pform;
  BasicDoubleCell d2;
  BasicStringCell s1;
  ConcatFormula cform;
  BasicStringCell s2;
  BasicBooleanCell b1;
  LessThanFormula lform;
  BasicBooleanCell b2;
  BasicSpreadsheet spread2;

  @Before
  public void setUp() {
    spread = new BasicSpreadsheet();
    d1 = new BasicDoubleCell(2.34);
    pform = new ProductFormula(spread);
    pform.addConstant(1.0);
    pform.addConstant(4.0);
    pform.addConstant(1.0);
    pform.addConstant(3.0);
    d2 = new BasicDoubleCell(pform);

    s1 = new BasicStringCell("hi");
    cform = new ConcatFormula(spread);
    cform.addConstant("my");
    cform.addConstant(" name");
    cform.addConstant(" is");
    cform.addConstant(" kate.");
    s2 = new BasicStringCell(cform);

    b1 = new BasicBooleanCell(true);
    lform = new LessThanFormula(13.0, 19.23, spread);
    b2 = new BasicBooleanCell(lform);
    spread2 = new BasicSpreadsheet(10, 10);
  }

  /**
   * Tests the getValue() method.
   */
  @Test
  public void testGetValue() {
    Assert.assertEquals((Double)d1.getValue(), 2.34, .00001);
    Assert.assertEquals((Double)d2.getValue(), null);
    Assert.assertEquals((String)s1.getValue(), "hi");
    Assert.assertEquals((String)s2.getValue(), null);
    Assert.assertEquals((Boolean)b1.getValue(), true);
    Assert.assertEquals((Boolean)b2.getValue(), null);
  }

  /**
   * Test the getFormula() method.
   */
  @Test
  public void testGetFormula() {
    Assert.assertEquals(d1.getFormula(), null);
    Assert.assertEquals(d2.getFormula(), pform);
    Assert.assertEquals(s1.getFormula(), null);
    Assert.assertEquals(s2.getFormula(), cform);
    Assert.assertEquals(b1.getFormula(), null);
    Assert.assertEquals(b2.getFormula(), lform);
  }

  /**
   * Tests the getDependincies() and setDependencies() methods.
   */
  @Test
  public void testGetandSetDep() {
    Assert.assertEquals(d1.getDependencies(), new ArrayList<Coord>());
    d1.setDependent(new Coord(1, 5));
    List<Coord> list1 = new ArrayList<Coord>();
    list1.add(new Coord(1, 5));
    Assert.assertEquals(d1.getDependencies(), list1);
  }

  /**
   * Tests the toString() method.
   */
  @Test
  public void testToString() {
    Assert.assertEquals(d1.toString(), "2.340000");
    Assert.assertEquals(d2.toString(), "(Product : 1.0 4.0 1.0 3.0 )");
    Assert.assertEquals(s1.toString(), "hi");
    Assert.assertEquals(b1.toString(), "true");
    Assert.assertEquals(b2.toString(), "(Less than : 13.0 19.23 )");
  }

  @Test
  public void testGetRows() {
    // default
    Assert.assertEquals(15, spread2.getRowHeight(5));
    spread2.setRowHeight(5, 100);
    Assert.assertEquals(100, spread2.getRowHeight(5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRowsF() {
    spread2.getRowHeight(20);
  }

  @Test
  public void testGetHeight() {
    Assert.assertEquals(75, spread2.getColWidth(5));
    spread2.setRowHeight(5, 100);
    Assert.assertEquals(100, spread2.getColWidth(5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetHeightF() {
    spread2.getRowHeight(15);
  }


}
