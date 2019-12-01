package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
import edu.cs3500.spreadsheets.provider.cellvalue.CellValueVisitor;

public class OurCellValue implements CellValue {
  private AbstractBasicCell ourCell;

  public OurCellValue(AbstractBasicCell c) {
    ourCell = c;
  }

  @Override
  public <R> R accept(CellValueVisitor<R> visitor) {
    return null;
  }


}
