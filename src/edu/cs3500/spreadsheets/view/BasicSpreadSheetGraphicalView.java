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


/**
 * A class for a graphical view of a table using JTable.
 */
public class BasicSpreadSheetGraphicalView implements SpreadSheetGraphicalView {

  private JFrame frame;
  private JTable table;
  private JTextField textBox;
  private JButton xButton;
  private JButton checkButton;
  private JButton addRowButton;
  private JButton addColumnButton;


  public BasicSpreadSheetGraphicalView() {
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
    CellTableModel tableModel = new CellTableModel(model);
    table = new JTable(tableModel);
    for (int i = 0; i < model.getWidth() + 1; i++) {
      table.setDefaultRenderer(tableModel.getColumnClass(i),
          new CustomCellRenderer());
    }
    table.setBounds(30, 40, 200, 300);
    JScrollPane scroll = new JScrollPane(table,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.setEditingColumn(0);
    table.setBackground(Color.BLACK);
    frame.setTitle("test");
    frame.add(scroll);
    frame.setSize(500, 200);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void updateCell(Coord c, Cell cell) {
    // the + 1 at get x is to account for the labeled column
    System.out.print(table.getValueAt(c.getY() - 1, c.getX()));
    table.setValueAt(cell.toString(), c.getY() -1 , c.getX());
    System.out.println(c.toString());
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
    // our controller will extend the action listener and
    // mouse listener interface
    table.addMouseListener((MouseListener) listener);
  }

  @Override
  public Coord getSelectedCell() {
    // indexed to one to account for label rows
    return new Coord(table.getSelectedColumn(), table.getSelectedRow() + 1);
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



