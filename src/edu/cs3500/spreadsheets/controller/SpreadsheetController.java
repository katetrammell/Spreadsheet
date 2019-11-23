package edu.cs3500.spreadsheets.controller;

import edu.cs3500.spreadsheets.model.Coord;

public interface SpreadsheetController  {

  void addRow();

  void addCol();

  void changeCell(Coord c, String s);

}
