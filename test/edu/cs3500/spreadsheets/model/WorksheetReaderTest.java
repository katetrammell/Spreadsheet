package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.WorksheetReader.OurBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SList;
import edu.cs3500.spreadsheets.sexp.SNumber;
import edu.cs3500.spreadsheets.sexp.SSymbol;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class that contains the tests for WorksheetReader class.
 */
public class WorksheetReaderTest {

  WorksheetBuilder<BasicSpreadsheet> b;

  /**
   * Initializes a WorksheetBuilder to be used in each test case.
   */
  @Before
  public void setUp() {
    b = new OurBuilder();
  }

  /**
   * Tests that create cell and createWorksheet work as expected
   */
  @Test
  public void testCreateWorksheet() {
    BasicDoubleCell c = (BasicDoubleCell) b.createCell(1, 1,
        "=(PRODUCT -1.0 -2.0)").createWorksheet().getCellAt(1, 1);
    Assert.assertEquals(c.getFormula().evaluate(), 2.0, .000001);
  }

  /**
   * Tests that read works as expected using a sample data.txt.
   */
  @Test
  public void testReader2() {
    FileReader f;
    try {
      f = new FileReader("data.txt");
    } catch (FileNotFoundException e) {
      System.out.print("file not found");
      return;
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);
    Assert.assertEquals(spread.getCellAt(1, 1),
        new BasicDoubleCell(3.0));
    ArrayList<Coord> lc = new ArrayList<Coord>();
    lc.add(new Coord(1, 1));
    lc.add(new Coord(2, 1));
    Assert.assertEquals(spread.getCellAt(2, 1),
        new BasicDoubleCell(new ProductFormula(lc, spread)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSelfReference() {
    FileReader f;
    try {
      f = new FileReader("ContainsSelfReference.txt");
    } catch (FileNotFoundException e) {
      System.out.print("file not found");
      return;
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCircleReference() {
    FileReader f;
    try {
      f = new FileReader("circRef.txt");
    } catch (FileNotFoundException e) {
      System.out.print("file not found");
      return;
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);
  }

  @Test
  public void testBiggerFile() {
    FileReader f;
    try {
      f = new FileReader("BigData.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Bad file");
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);
    Assert.assertEquals(spread.getCellAt(new Coord(1, 1)), new BasicDoubleCell(45.0));
    Assert.assertEquals(spread.getCellAt(2, 1), new BasicBooleanCell(true));
    Assert.assertEquals(spread.getCellAt(6, 2).getFormula().evaluate(), 45.0);
    Assert.assertEquals((Double) spread.getCellAt(82, 2).getFormula().evaluate(),
        (23.2 * 45), .0001);
  }


  @Test
  public void testAnotherFile() {
    FileReader f;
    try {
      f = new FileReader("AnotherFile.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Bad file");
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);

    Assert.assertEquals(spread.getCellAt(new Coord(1, 1)).getFormula(), null);
    Assert.assertEquals((Double)spread.getCellAt(new Coord(1, 2)).getFormula().evaluate(),
        144.0, .0001 );




  }
}
