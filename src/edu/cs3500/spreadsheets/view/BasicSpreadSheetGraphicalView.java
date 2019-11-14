package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;


/**
 * A class for a graphical view of a table using JTable.
 */
public class BasicSpreadSheetGraphicalView implements SpreadsheetView {

  /**
   * Renders the given model as a graphical view.
   *
   * @param model the spreadsheet to be rendered
   */
  @Override
  public void render(Spreadsheet model) {
    JFrame window = new JFrame();
    JTable spread;
    CellTableModel table = new CellTableModel(model);
    spread = new JTable(table);
    for (int i = 0; i < model.getWidth() + 1; i++) {
      spread.setDefaultRenderer(table.getColumnClass(i),
          new CustomCellRenderer());
    }
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

    //scroll.getHorizontalScrollBar().addAdjustmentListener();
  }


  /**
   * A custom TableModel class to implement the graphical view.
   */
  private static class CellTableModel extends AbstractTableModel {

    private final Spreadsheet model;

    private CellTableModel(Spreadsheet model) {
      this.model = model;
    }

    /**
     * Gets the number of rows.
     *
     * @return an int representing the number of rows
     */
    @Override
    public int getRowCount() {
      return model.getHeight();
    }

    /**
     * Gets the number of columns.
     *
     * @return an int representing the number of columns.
     */
    @Override
    public String getColumnName(int i) {
      if (i == 0) {
        return "";
      }
      return Coord.colIndexToName(i);
    }

    @Override
    public int getColumnCount() {
      return model.getWidth() + 1;
    }

    /**
     * gets the value at a given column and row.
     *
     * @param rowIndex    the row.
     * @param columnIndex the column.
     * @return the value in the model at the given row and col.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      String cell;
      if (columnIndex == 0) {
        cell = Integer.toString(rowIndex + 1);
      } else if (model.getAllCells().containsKey(new Coord(columnIndex, rowIndex + 1))) {
        Cell currentCell = model.getCellAt(rowIndex + 1, columnIndex);
        if (currentCell.getFormula() == null) {
          cell = currentCell.toString();
        } else {
          cell = currentCell.getFormula().evaluate().toString();
        }
      } else {
        cell = "";
      }
      if (cell.length() > 7) {
        cell = cell.substring(0, 7);
      }
      return cell;
    }
  }

  /**
   * A custom TableCellRenderer class.
   */
  private static class CustomCellRenderer extends DefaultTableCellRenderer {

    /**
     * renders the cell.
     *
     * @param table      the table to get the cell from
     * @param value      the value to be rendered
     * @param isSelected if the cell is currently selected.
     * @param hasFocus   if the call has focus.
     * @param row        the row of the cell.
     * @param column     the column of the cell.
     * @return a component of the cell
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
      if (column == 0) {
        setBackground(Color.LIGHT_GRAY);
      } else {
        setBackground(Color.WHITE);
      }
      return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
  }

}



