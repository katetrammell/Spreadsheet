package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.model.WorksheetReader.OurBuilder;
import edu.cs3500.spreadsheets.model.WorksheetReader.WorksheetBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

public class BasicSpreadsheetGraphicalViewTest {


  @Test
  public void renderGUI() {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    FileReader f;
    try {
      f = new FileReader("BigData.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Bad file");
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);

    BasicSpreadSheetGraphicalView gui = new BasicSpreadSheetGraphicalView();
    gui.render(spread);
  }



  public static void main(String[] args) {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    FileReader f;
    try {
      f = new FileReader("BigHorizontal.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Bad file");
    }
    BasicSpreadsheet spread = WorksheetReader.read(b, f);

    BasicSpreadSheetGraphicalView gui = new BasicSpreadSheetGraphicalView();
    gui.render(spread);
  }

}
