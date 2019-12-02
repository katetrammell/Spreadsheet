package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
import edu.cs3500.spreadsheets.provider.cellvalue.CellValueVisitor;

public class ProviderErrorCell implements CellValue {

  @Override
  public <R> R accept(CellValueVisitor<R> visitor) {
    return visitor.visitError("");
  }
}
