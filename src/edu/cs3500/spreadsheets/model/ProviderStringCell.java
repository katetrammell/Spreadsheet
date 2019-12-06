package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
import edu.cs3500.spreadsheets.provider.cellvalue.CellValueVisitor;

/**
 * A class that implements our providers interface for cell
 * using the delegate pattern and representing String values.
 */
public class ProviderStringCell implements CellValue {
  private String str;

  public ProviderStringCell(String str) {
    this.str = str;
  }


  @Override
  public <R> R accept(CellValueVisitor<R> visitor) {
    return visitor.visitString(str);
  }
}
