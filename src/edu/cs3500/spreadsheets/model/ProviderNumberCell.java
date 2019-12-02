package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.provider.cellvalue.CellValue;
import edu.cs3500.spreadsheets.provider.cellvalue.CellValueVisitor;

public class ProviderNumberCell implements CellValue {
  private Double num;

  public ProviderNumberCell(Double num) {
    this.num = num;
  }

  @Override
  public <R> R accept(CellValueVisitor<R> visitor) {
    return visitor.visitNumber(num);
  }
}
