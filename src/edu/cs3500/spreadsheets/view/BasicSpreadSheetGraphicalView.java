package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;

public class BasicSpreadSheetGraphicalView implements SpreadsheetView {

  @Override
  public void render(Spreadsheet model) {
    JFrame window = new JFrame();
    JTable spread;
    HashMap<Coord, Cell> spreadData = model.getAllCells();
    int spreadHeight = model.getHeight();
    int spreadWidth = model.getWidth();
    // +1 to account for extra index column
    String[][] spreadStrings = new String[spreadHeight][spreadWidth + 1];
    // gets all the data from model
    for (int i = 0; i < spreadHeight; i++) {
      for (int j = 0; j < spreadWidth + 1; j++) {
        String cell;
        if (j == 0 ) {
          cell = Integer.toString(i + 1);
        }
        else if (spreadData.containsKey(new Coord(j , i + 1))) {
          Cell currentCell = model.getCellAt(i + 1, j );
          if (currentCell.getFormula() == null) {
            cell = currentCell.toString();
          }
          else {
            cell = currentCell.getFormula().evaluate().toString();
          }
        }
        else {
          cell = "";
        }
        spreadStrings[i][j] = cell;
      }
    }
    // to get column names from model
    String[] columnNames = new String[spreadWidth + 1];
    columnNames[0] = "";
    for (int i = 2; i <= spreadWidth + 1; i++) {
      columnNames[i - 1] = Coord.colIndexToName(i);
    }

    spread = new JTable(spreadStrings, columnNames);
    spread.setBounds(30, 40, 200, 300);
    JScrollPane scroll = new JScrollPane(spread,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    spread.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    spread.setEditingColumn(0);
    spread.setBackground(Color.BLACK);
    window.setTitle("test");
    window.add(scroll);
    window.setSize(500, 200);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  private static class CellTableModel extends AbstractTableModel {
    private final Spreadsheet model;

    private CellTableModel(Spreadsheet model) {
      this.model = model;
    }

    @Override
    public int getRowCount() {
      return model.getHeight();
    }

    @Override
    public int getColumnCount() {
      return model.getWidth() + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      String cell;
      if (columnIndex == 0 ) {
        cell = Integer.toString(rowIndex + 1);
      }
      else if (model.getAllCells().containsKey(new Coord(columnIndex, rowIndex + 1))) {
        Cell currentCell = model.getCellAt(rowIndex + 1, columnIndex );
        if (currentCell.getFormula() == null) {
          cell = currentCell.toString();
        }
        else {
          cell = currentCell.getFormula().evaluate().toString();
        }
      }
      else {
        cell = "";
    }
    return cell;
    }
  }

  private static class CustomCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
      if (row == 0 | column == 0) {
        setBackground(Color.LIGHT_GRAY);
      }
      else {
        setBackground(Color.WHITE);
      }
      return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
  }
}
