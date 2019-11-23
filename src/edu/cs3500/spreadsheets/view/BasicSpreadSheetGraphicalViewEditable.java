package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


/**
 * A class for a graphical view of a table using JTable.
 */
public class BasicSpreadSheetGraphicalViewEditable implements SpreadSheetGraphicalView {

  private JFrame frame;
  private JTable table;
  private CellTableModel tableModel;
  private JTextField textBox;
  private JButton xButton;
  private JButton checkButton;
  private JButton addRowButton;
  private JButton addColumnButton;

  public BasicSpreadSheetGraphicalViewEditable() {
    frame = new JFrame();

  }

  /**
   * Renders the given model as a graphical view.
   *
   * @param model the spreadsheet to be rendered
   */
  @Override
  public void render(Spreadsheet model) {
      textBox = new JTextField(50);
      frame.setLayout(new FlowLayout());
      frame.add(textBox);

    xButton = new JButton("X");
    xButton.setActionCommand("X Button");
    frame.add(xButton);
    checkButton = new JButton("✓");
    checkButton.setActionCommand("Check Button");
    frame.add(checkButton);
    addRowButton = new JButton("+ Row");
    addRowButton.setActionCommand("Add Row");
    frame.add(addRowButton);
    addColumnButton = new JButton("+ Column");
    addColumnButton.setActionCommand("Add column");
    frame.add(addColumnButton);
    tableModel = new CellTableModel(model);
    table = new JTable(tableModel);
    for (int i = 0; i < model.getWidth() + 1; i++) {
      table.setDefaultRenderer(tableModel.getColumnClass(i),
          new CustomCellRenderer(this));
      System.out.println(tableModel.getColumnClass(i).toString());
    }
    table.setBounds(30, 40, 200, 300);
    JScrollPane scroll = new JScrollPane(table,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    frame.setTitle("test");
    frame.add(scroll);
    frame.setSize(500, 200);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void updateCell(Coord c, Cell cell) {
    // the + 1 at get x is to account for the labeled column
      String str;
      if (cell == null) {
        str = "";
      } else {
        str = cell.toString();
      }
      tableModel.setValueAt(str, c.getX(), c.getY());
      tableModel.fireTableCellUpdated(c.getX(), c.getY());
      tableModel.fireTableDataChanged();

  }

  @Override
  public void addCol(int colNum) {
    table.addColumn(new TableColumn(colNum,80,
        new CustomCellRenderer(this), new DefaultCellEditor(new JTextField())));
    tableModel.fireTableDataChanged();
  }

  @Override
  public String getTextBox() {
      return textBox.getText();
  }

  @Override
  public void setTextBox(String s) {
      textBox.setText(s);
  }

  @Override
  public void setListener(ActionListener listener) {
    checkButton.addActionListener(listener);
    xButton.addActionListener(listener);
    addColumnButton.addActionListener(listener);
    addRowButton.addActionListener(listener);
    // our controller will extend the action listener and
    // mouse listener interface
    table.addMouseListener((MouseListener) listener);
  }

  @Override
  public Coord getSelectedCell() {
    // indexed to one to account for label rows
    if (table.getSelectedRow() > 0 && table.getSelectedColumn() > 0) {
      return new Coord(table.getSelectedColumn(), table.getSelectedRow() + 1);
    } else {
      return new Coord(1,1);
    }
  }

  @Override
  public JTable getTable() {
    return this.table;
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
    BasicSpreadSheetGraphicalViewEditable view;

    public  CustomCellRenderer(BasicSpreadSheetGraphicalViewEditable view) {
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

}