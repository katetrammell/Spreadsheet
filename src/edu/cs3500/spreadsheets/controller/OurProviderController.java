package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.provider.controller.SimpleWorksheetController;
import edu.cs3500.spreadsheets.provider.model.Coord;
import edu.cs3500.spreadsheets.provider.model.Worksheet;
import edu.cs3500.spreadsheets.provider.view.FeatureHandler;
import edu.cs3500.spreadsheets.provider.view.SimpleWorksheetWritableGuiView;

public class OurProviderController implements SimpleWorksheetController, FeatureHandler {
 private SimpleWorksheetWritableGuiView view;
  private Worksheet theirModel;
  //private SpreadSheetGraphicalController ourController;
  private Coord lastSelectedCell;

  public OurProviderController(SimpleWorksheetWritableGuiView v,
      Worksheet m
      //SpreadSheetGraphicalController c
  ){
    this.view = v;
    this.theirModel = m;
   // this.ourController = c;
  }

  @Override
  public void start() {
    view.setFeatureHandler(this);
    view.render();

  }

  @Override
  public void cellSelected(Coord cellLocation) {
    view.selectCell(cellLocation);
    this.lastSelectedCell = cellLocation;
    view.repaint();

  }

  /**
   * X button pressed
   */
  @Override
  public void cellDeselected() {
    view.selectCell(this.lastSelectedCell);

  }

  @Override
  public void textEntered(String text) {
    theirModel.editCell(this.lastSelectedCell, text);
    view.repaint();
  }

  @Override
  public void deleteSelectedCell() {

  }
}
