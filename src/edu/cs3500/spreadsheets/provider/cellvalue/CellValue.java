package edu.cs3500.spreadsheets.provider.cellvalue;


/**
 * Interface to represent the value of a cell as a union data type. Can be one of: - String -
 * Boolean - Double
 *
 * <p>
 * CellValues are a general-purpose datatype, and so have no methods of their own. All processing on
 * CellValues is done through the visitor pattern: see
 * </p>
 */
public interface CellValue {

  <R> R accept(CellValueVisitor<R> visitor);
}

