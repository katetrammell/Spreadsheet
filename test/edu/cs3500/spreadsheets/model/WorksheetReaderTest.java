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
import org.junit.Test;

public class WorksheetReaderTest {

  @Test
  public void testReader() {
    WorksheetBuilder<BasicSpreadsheet> b = new OurBuilder();
    BasicDoubleCell c = (BasicDoubleCell)b.createCell(0,0,
        "=(PRODUCT -1.0 -2.0)").createWorksheet().getCellAt(0,0);
    Assert.assertEquals(c.getFormula().evaluate(), 2.0, .000001);
  }

  @Test
  public void testParser() {
    Sexp s = Parser.parse("(PRODUCT 1.0 4.2)");
    ArrayList<Sexp> slist = new ArrayList<Sexp>();
    slist.add(new SSymbol("PRODUCT"));
    slist.add(new SNumber(1.0));
    slist.add(new SNumber(4.2));
    Assert.assertEquals(s, new SList(slist));
  }

  @Test
  public void testReader2() throws FileNotFoundException {
    WorksheetBuilder<BasicSpreadsheet> builder = new OurBuilder();
    FileReader f = new FileReader("data.txt");
    BasicSpreadsheet spread = WorksheetReader.read(builder, f);
    Assert.assertEquals(spread.getCellAt(1,1),
        new BasicDoubleCell(3.0));
    ArrayList<Coord> lc = new ArrayList<Coord>();
    lc.add(new Coord(1, 1));
    lc.add(new Coord(2,1));
    Assert.assertEquals(spread.getCellAt(2, 1),
        new BasicDoubleCell(new ProductFormula(lc, spread)));

  }

}
