package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
import edu.cs3500.spreadsheets.provider.cellvalue.CellValueVisitor;

/**
 * A class that implements our providers interface for cell
 * using the delegate pattern and representing empty values.
 */
public class ProviderEmptyCell implements CellValue {

  @Override
  public <R> R accept(CellValueVisitor<R> visitor) {
    return visitor.visitEmpty();
  }
}
