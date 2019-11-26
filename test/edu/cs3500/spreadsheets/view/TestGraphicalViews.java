package edu.cs3500.spreadsheets.view;

import org.junit.Test;

/**
 * Class to test graphical views.
 */
public class TestGraphicalViews {

  @Test(expected = IllegalArgumentException.class)
  public void nullModel() {
    SpreadsheetView view = new BasicSpreadSheetGraphicalView();
    view.render(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullModel2() {
    SpreadsheetView view = new BasicSpreadSheetGraphicalViewEditable();
    view.render(null);
  }


}
