package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.BasicBooleanCell;
import edu.cs3500.spreadsheets.model.BasicDoubleCell;
import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.WorksheetReader.OurBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;

public class BasicSpreadsheetTextualViewTest {

  @Test
  public void testRender1() {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    FileReader f;
    try {
      f = new FileReader("BigData.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Bad file");
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);

    BasicSpreadsheetTextualView v = new BasicSpreadsheetTextualView();
    try {
      PrintWriter testPW = new PrintWriter("ShouldBeBigData.txt");
      v.render(spread, testPW);
      testPW.close();
    } catch (IOException e) {
      System.out.print("didn't work");
      return;
    }




  }

}
