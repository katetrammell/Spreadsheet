package edu.cs3500.spreadsheets.provider.view;


import edu.cs3500.spreadsheets.provider.model.Coord;

/**
 * Represents the various features the view communicates back a callback. These are the things the
 * view tells the controller.
 */
public interface FeatureHandler {

  /**
   * Handles what to do when the cell at {@code cellLocation} has been clicked on.
   *
   * @param cellLocation the Coord of the clicked cell.
   */
  void cellSelected(Coord cellLocation);

  /**
   * Handles what to do when any selected cell is deselected.
   */
  void cellDeselected();

  /**
   * Handles what to do when the given text has been input.
   *
   * @param text the text the user input.
   */
  void textEntered(String text);

  /**
   * Handles the deletion of the contents of a cell.
   */
  void deleteSelectedCell();
}