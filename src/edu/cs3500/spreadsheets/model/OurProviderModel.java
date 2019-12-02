package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
import edu.cs3500.spreadsheets.provider.model.Coord;
import edu.cs3500.spreadsheets.provider.model.Worksheet;
import edu.cs3500.spreadsheets.sexp.CellMaker;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.Sexp;
import java.util.ArrayList;
import java.util.List;

public class OurProviderModel implements Worksheet {
  private BasicSpreadsheet ourModel;

  public OurProviderModel(BasicSpreadsheet spread) {
    this.ourModel = spread;
  }

  @Override
  public void editCell(Coord location, String contents) {
    try {
      if (contents.substring(0, 1).equals("=")) {
        contents = contents.substring(1);
      }
      Sexp sexp = Parser.parse(contents);
      Cell c = sexp.accept(new CellMaker(ourModel));
      ourModel.setCell(c, edu.cs3500.spreadsheets.model.Coord.parseCoord(location.toString()));
    } catch (Exception e) {
      ourModel.setCell(new BasicStringCell("Error"),
          edu.cs3500.spreadsheets.model.Coord.parseCoord(location.toString()));
    }
  }

  @Override
  public CellValue getValue(Coord location) {
    CellValue ans;
    try {
      ans = ourModel.getCellAt(edu.cs3500.spreadsheets.model.Coord.parseCoord(location.toString()))
          .toProviderCell();
    } catch (Exception e) {
      ans = new ProviderEmptyCell();
    }
    return ans;
  }

  @Override
  public String getRawContent(Coord location) {
    if ( ourModel.getCellAt(edu.cs3500.spreadsheets.model.Coord.parseCoord(location.toString()))
        != null) {
      return ourModel.getCellAt(edu.cs3500.spreadsheets.model.Coord.parseCoord(location.toString()))
          .toString();
    } else {
      return "";
    }
  }

  @Override
  public List<Coord> getActiveCoords() {
    List<edu.cs3500.spreadsheets.provider.model.Coord> ans =
        new ArrayList<edu.cs3500.spreadsheets.provider.model.Coord>();
    for (edu.cs3500.spreadsheets.model.Coord c : ourModel.getAllCells().keySet()) {
      ans.add(edu.cs3500.spreadsheets.provider.model.Coord.parseCoord(c.toString()));
    }
    return ans;
  }
}
