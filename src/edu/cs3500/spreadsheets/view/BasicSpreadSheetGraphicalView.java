package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;


/**
 * A class for a graphical view of a table using JTable.
 */
public class BasicSpreadSheetGraphicalView implements SpreadSheetGraphicalView {

  /**
   * Renders the given model as a graphical view.
   *
   * @param model the spreadsheet to be rendered
   */
  @Override
  public void render(Spreadsheet model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    JFrame frame = new JFrame();
    JTable spread;
    CellTableModel table = new CellTableModel(model);
    spread = new JTable(table);
    for (int i = 0; i < model.getWidth() + 1; i++) {
      spread.setDefaultRenderer(table.getColumnClass(i),
          new CustomCellRenderer(this));
    }

    spread.setBounds(30, 40, 200, 300);
    JScrollPane scroll = new JScrollPane(spread,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    spread.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    spread.setEditingColumn(0);
    spread.setBackground(Color.BLACK);
    frame.setTitle("spreadsheet");
    frame.add(scroll);
    frame.setSize(500, 200);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    HashMap<Integer, Integer> rowHeights = makeRowHash(model);
    for (Integer i: rowHeights.keySet()) {
      spread.setRowHeight(i, rowHeights.get(i));
    }
  }

  @Override
  public void addCol(int colNum) {
    //Suppress this method
  }

  @Override
  public void updateCell(Coord c, Cell cell) {
    //Suppress this method
  }

  @Override
  public String getTextBox() {
    return null;
  }

  @Override
  public void setTextBox(String s) {
    //Suppress this method
  }

  @Override
  public void setListener(ActionListener listener) {
    //Suppress this method
  }

  @Override
  public Coord getSelectedCell() {
    return null;
  }

  @Override
  public String getSaveBox() {
    return null;
  }

  @Override
  public void setSaveBox(String s) {
    //Suppress this method
  }

  @Override
  public void setRowHeight(int row, int height) {
    // suppress
  }

  @Override
  public int getRowHeight(int row) {
    return 0;
  }

  @Override
  public void setColWidth(int col, int width) {
    return;
  }

  @Override
  public int getColWidth(int index) {
    return 0;
  }


  /**
   * A custom TableModel class to implement the graphical view.
   */
  protected static class CellTableModel extends AbstractTableModel {

    private final Spreadsheet model;

    /**
     * A constructor for the class.
     *
     * @param model the spreadsheet to be rendered
     */
    protected CellTableModel(Spreadsheet model) {
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

  protected static class CustomCellRenderer extends DefaultTableCellRenderer {

    SpreadSheetGraphicalView view;

    /**
     * A constructor for the class.
     *
     * @param view the view to be rendered.
     */
    protected CustomCellRenderer(SpreadSheetGraphicalView view) {
      this.view = view;
    }

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
      boolean reallySelected;
      try {
        reallySelected = (row == table.getSelectedRow()
            && column == table.getSelectedColumn());
      } catch (IllegalArgumentException e) {
        reallySelected = false;
      }
      Component cell = super.getTableCellRendererComponent(table, value,
          false, false, row, column);
      if (column == 0) {
        cell.setBackground(Color.LIGHT_GRAY);
      } else if (reallySelected) {
        cell.setBackground(Color.YELLOW);
      } else {
        cell.setBackground(Color.white);
      }
      return cell;
    }
  }

  /**
   * Makes a hashMap with all the nonstandard row and rowHeights combos.
   * @param spread the spreadsheet model.
   * @return row - rowHeights map.
   */
  protected HashMap<Integer, Integer> makeRowHash(Spreadsheet spread) {
    HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
    for (int i = 0; i < spread.getHeight(); i++) {
      if (spread.getRowHeight(i) != BasicSpreadsheet.DEFAULT_ROW_HEIGHT) {
        result.put(i, spread.getRowHeight(i));
      }
    }
    return result;
  }
}



