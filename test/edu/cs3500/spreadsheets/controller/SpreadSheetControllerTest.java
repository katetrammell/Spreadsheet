package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.view.BasicSpreadSheetGraphicalViewEditable;
import edu.cs3500.spreadsheets.view.SpreadSheetGraphicalView;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test that the contoller makes the intended changes
 * to the model and otherwise.
 */
public class SpreadSheetControllerTest {

  public Spreadsheet spread;
  private BasicSpreadSheetGraphicalViewEditable view;
  private SpreadSheetGraphicalController controller;
  private Coord coord;


  @Before
  public void initialize() {
    spread = new BasicSpreadsheet(10, 10);
    view = new BasicSpreadSheetGraphicalViewEditable();
    controller = new SpreadSheetGraphicalController(spread, view);
    coord = new Coord(2, 2);
  }

  @Test
  public void testAddRow() {
    Assert.assertEquals(10, spread.getHeight());
    controller.addRow();
    Assert.assertEquals(11, spread.getHeight());
  }

  @Test
  public void testAddRowBulk() {
    Assert.assertEquals(10, spread.getHeight());
    for (int i = 0; i < 100; i++) {
      controller.addRow();
    }
    Assert.assertEquals(110, spread.getHeight());
  }

  @Test
  public void testAddCol() {
    Assert.assertEquals(10, spread.getWidth());
    controller.addCol();
    Assert.assertEquals(11, spread.getWidth());
  }

  @Test
  public void testAddColBulk() {
    Assert.assertEquals(10, spread.getWidth());
    for (int i = 0; i < 100; i ++) {
      controller.addCol();
    }
    Assert.assertEquals(110, spread.getWidth());
  }

  // replaces empty cell
  @Test
  public void testChangeCells() {
    Assert.assertEquals(null,
        spread.getCellAt(coord));
    controller.changeCell(coord, "122");
    Assert.assertEquals(122.0,
        spread.getCellAt(coord).getValue());
  }

  @Test
  public void changeCellsOverwrite() {
    Assert.assertEquals(null,
        spread.getCellAt(coord));
    controller.changeCell(coord, "122");
    Assert.assertEquals(122.0,
        spread.getCellAt(coord).getValue());
    controller.changeCell(coord, "134");
    Assert.assertEquals(134.0,
        spread.getCellAt(coord).getValue());
  }

  @Test
  public void changeCellFormula() {
    Assert.assertEquals(null,
        spread.getCellAt(coord));
    controller.changeCell(new Coord(1, 1), "10");
    controller.changeCell(new Coord(1, 7), "20");
    controller.changeCell(coord, "=(SUM A1 A7)");
    Assert.assertEquals("=(SUM A1 A7 )",
        spread.getCellAt(coord).getFormula().toString());
    Assert.assertEquals(30.0,
        spread.getCellAt(coord).getFormula().evaluate());
  }

  @Test
  public void changeCellString() {
    controller.changeCell(coord, "\"test\"");
    Assert.assertEquals("test", spread.getCellAt(coord).getValue());
  }

  @Test
  public void ActionPerformedAddCol() {
    int prevWidth = spread.getWidth();
    controller.actionPerformed(new ActionEvent(view, 1, "Add column"));
    Assert.assertEquals(spread.getWidth(), prevWidth + 1);
  }

  @Test
  public void ActionPerformedAddRow() {
    int prevHeight = spread.getHeight();
    controller.actionPerformed(new ActionEvent(view, 1, "Add Row"));
    Assert.assertEquals(spread.getHeight(), prevHeight + 1);
  }

  @Test
  public void ActionPerformedCheckButton() {
    controller.mouseClicked(new MouseEvent(new JFrame(), 1,
        18, 0, 12, 1, 1, false));

  }




}
