package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
import edu.cs3500.spreadsheets.provider.cellvalue.CellValueVisitor;

public class ProviderBooleanCell implements CellValue {

  private Boolean bool;

  public ProviderBooleanCell(Boolean b) {
    this.bool = b;
  }

  @Override
  public <R> R accept(CellValueVisitor<R> visitor) {
    return visitor.visitBoolean(this.bool);
  }
}
