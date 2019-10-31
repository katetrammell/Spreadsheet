package edu.cs3500.spreadsheets.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasas for testing sumFormula.
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
    SumFormula f = new SumFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(3.2, f.evaluate(),.0001);
  }

  @Test
  public void twoNum() {
    coords.add(new Coord(3, 1));
    coords.add(new Coord(1, 1));
    SumFormula f = new SumFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(8.2, f.evaluate(),.0001);
  }

  @Test
  public void twoNumWithString() {
    coords.add(new Coord(3, 1));
    coords.add(new Coord(1, 1));
    coords.add(new Coord(2, 3));
    SumFormula f = new SumFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(8.2, f.evaluate(),.0001);
  }

  @Test
  public void twoNumWithStringBoolean() {
    coords.add(new Coord(3, 1));
    coords.add(new Coord(1, 1));
    coords.add(new Coord(2, 3));
    coords.add(new Coord(1, 4));
    SumFormula f = new SumFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(8.2, f.evaluate(),.0001);
  }

  @Test
  public void twoNumWithStringBooleanNull() {
    coords.add(new Coord(3, 1));
    coords.add(new Coord(1, 1));
    coords.add(new Coord(2, 3));
    coords.add(new Coord(1, 4));
    coords.add(new Coord(20, 20));
    SumFormula f = new SumFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(8.2, f.evaluate(),.0001);
  }

  @Test
  public void testDefault() {
    SumFormula f = new SumFormula((ArrayList<Coord>) coords,
        new ArrayList<Formula<Double>>(), spread1);
    Assert.assertEquals(0.0, f.evaluate(), .001);
  }



}
