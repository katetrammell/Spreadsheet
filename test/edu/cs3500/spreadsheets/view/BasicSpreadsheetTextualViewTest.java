package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.WorksheetReader.OurBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.Assert;
import org.junit.Test;


/**
 * A class for testing the BasicSpreadsheetTextualView class.
 */
public class BasicSpreadsheetTextualViewTest {

  /**
   * Tests that an exception is thrown from render() when the model arg is null.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testModelExp() {
    new BasicSpreadsheetTextualView().render(null, new StringWriter());
  }

  /**
   * Tests that an exception is thrown from render() when the appendable arg is null.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAppendableExp() {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    FileReader f;
    try {
      f = new FileReader("BigData.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Bad file");
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);

    BasicSpreadsheetTextualView v = new BasicSpreadsheetTextualView();

    v.render(spread, null);
  }

  /**
   * Tests that an exception is thrown from render() with 1 arg when the model arg is null.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testModelOnlyExp() {
    new BasicSpreadsheetTextualView().render(null);
  }

  /**
   * Circularly tests the rendering of a file that contains
   */
  @Test
  public void testRender1() {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    FileReader f;
    try {
      f = new FileReader("BigData.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Bad file");
    }
    BasicSpreadsheet spread1 = WorksheetReader.read(b, f);

    BasicSpreadsheetTextualView v = new BasicSpreadsheetTextualView();
    try {
      PrintWriter testPW = new PrintWriter("ShouldBeBigData.txt");
      v.render(spread1, testPW);
      testPW.close();
    } catch (IOException e) {
      System.out.print("didn't work");
      return;
    }
    try {
      f = new FileReader("ShouldBeBigData.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Bad file");
    }
    BasicSpreadsheet spread2 = WorksheetReader.read(b, f);
    Assert.assertEquals(spread1.getHeight(), spread2.getHeight());
    Assert.assertEquals(spread1.getWidth(), spread2.getWidth());
    Assert.assertEquals(spread1.getCellAt(1, 1), spread2.getCellAt(1,1));
    Assert.assertEquals(spread1.getCellAt(2, 1), spread2.getCellAt(2,1));
    Assert.assertEquals(spread1.getCellAt(8, 1), spread2.getCellAt(8,1));
    Assert.assertEquals(spread1.getCellAt(6, 2), spread2.getCellAt(6,2));
    Assert.assertEquals(spread1.getCellAt(82, 6), spread2.getCellAt(82,6));
    Assert.assertEquals(spread1.getCellAt(5, 3), spread2.getCellAt(5,3));
    Assert.assertEquals(spread1.getCellAt(6, 3), spread2.getCellAt(6,3));
    Assert.assertEquals(spread1.getCellAt(2,5), spread2.getCellAt(2, 5));
  }

  /**
   * Tests that rendering of an empty spreadsheet gives an empty file
   */
  @Test
  public void render1() {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    BasicSpreadsheetTextualView v = new BasicSpreadsheetTextualView();
    BasicSpreadsheet spread = new BasicSpreadsheet();
    try {
      PrintWriter testPW = new PrintWriter("EmptyFile.txt");
      v.render(spread, testPW);
      testPW.close();
    } catch (IOException e) {
      System.out.print("didn't work");
      return;
    }
  }
}
