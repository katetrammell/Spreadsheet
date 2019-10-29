package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import org.junit.Test;

public class ConcatFormulaTest {

  @Test (expected = IllegalArgumentException.class)
  public void testCycle() {
    BasicSpreadsheet spread = new BasicSpreadsheet();
    ArrayList<Coord> c = new ArrayList<Coord>();
    c.add(new Coord(1,1));
    spread.setCell(new BasicDoubleCell(new ProductFormula(c, spread)), 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCycle2() {
    BasicSpreadsheet spread = new BasicSpreadsheet();
    spread.setCell(new BasicStringCell("ajdfh"), 1, 1);
    spread.setCell(new BasicStringCell("hi"),  1, 2);
    ArrayList<Coord> aC = new ArrayList<Coord>();
    aC.add(new Coord(1, 1));
    aC.add(new Coord(1, 2));
    spread.setCell(new BasicStringCell(new ConcatFormula(aC, spread)), 1, 3);
    ArrayList<Coord> aC2 = new ArrayList<Coord>();
    aC2.add(new Coord(1, 3));
    spread.setCell(new BasicDoubleCell(new ProductFormula(aC2, spread)), 1, 1);
  }

}
