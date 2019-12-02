package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.provider.controller.SimpleWorksheetController;
import edu.cs3500.spreadsheets.provider.model.Coord;
import edu.cs3500.spreadsheets.provider.view.FeatureHandler;
import edu.cs3500.spreadsheets.provider.view.SimpleWorksheetWritableGuiView;

public class OurProviderController implements SimpleWorksheetController, FeatureHandler {
 private SimpleWorksheetWritableGuiView view;
  private Spreadsheet model;
  //private SpreadSheetGraphicalController ourController;

  public OurProviderController(SimpleWorksheetWritableGuiView v,
      Spreadsheet m
      //SpreadSheetGraphicalController c
  ){
    this.view = v;
    this.model = m;
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

  }

  @Override
  public void cellDeselected() {

  }

  @Override
  public void textEntered(String text) {


  }

  @Override
  public void deleteSelectedCell() {

  }
}
