package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.provider.controller.SimpleWorksheetController;

public class OurProviderController implements SimpleWorksheetController {
 private SpreadSheetGraphicalView ourView;
  private Spreadsheet model;

  public OurProviderController(SpreadSheetGraphicalView v, Spreadsheet m) {
    this.ourView = v;
    this.model = m;
  }

  @Override
  public void start() {
    ourView.render(model);

  }
}
