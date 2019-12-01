package edu.cs3500.spreadsheets.provider.view;


import edu.cs3500.spreadsheets.provider.model.Coord;

/**
 * Interface for a worksheet view. Allows user to render a spreadsheet and set callbacks to
 * communicate with a controller.
 */
public interface WorksheetView {

  /**
   * Renders the worksheet into a visual form.
   */
  void render();

  /**
   * Visually select the cell at the given coordinate.
   *
   * @param cellCoord the cell to select
   */
  void selectCell(Coord cellCoord);

  /**
   * If a cell is currently selected, deselect it for visualization purposes.
   */
  void deselectCell();

  /**
   * Sets a callback to be used by event handlers.
   *
   * @param handler the callback to save.
   */
  void setFeatureHandler(FeatureHandler handler);
}