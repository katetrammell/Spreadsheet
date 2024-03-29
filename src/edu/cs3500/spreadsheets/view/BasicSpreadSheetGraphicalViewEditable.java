package edu.cs3500.spreadsheets.view;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import java.awt.FlowLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.table.TableColumn;


/**
 * A class for a graphical view of a table using JTable.
 */
public class BasicSpreadSheetGraphicalViewEditable extends BasicSpreadSheetGraphicalView {

  private JFrame frame;
  private JTable table;
  private CellTableModel tableModel;
  private JTextField textBox;
  private JButton xButton;
  private JButton checkButton;
  private JButton addRowButton;
  private JButton addColumnButton;
  private JButton saveButton;
  private JTextField saveText;
  private JButton increaseRowButton;
  private JButton decreaseRowButton;

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
    if (model == null) {
      throw new IllegalArgumentException();
    }

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
    saveButton = new JButton("Save file as:");
    saveButton.setActionCommand("Save Test");
    frame.add(saveButton);
    saveText = new JTextField(50);
    frame.add(saveText);
    increaseRowButton = new JButton("+ Selected Row Height");
    increaseRowButton.setActionCommand("Increase Row Height");
    frame.add(increaseRowButton);
    decreaseRowButton = new JButton("- Selected Row Height");
    decreaseRowButton.setActionCommand("Decrease Row Height");
    frame.add(decreaseRowButton);


    tableModel = new CellTableModel(model);
    table = new JTable(tableModel);

    table.getTableHeader().setResizingAllowed(true);
    for (int i = 0; i < model.getWidth() + 1; i++) {
      table.setDefaultRenderer(tableModel.getColumnClass(i),
          new CustomCellRenderer(this));
    }
    table.setBounds(30, 40, 200, 300);
    for (int i = 0; i < model.getWidth() + 1; i ++) {
      setColWidth(i, getColWidth(i));
      table.getColumnModel().getColumn(i).setPreferredWidth(model.getColWidth(i));
    }
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

  /**
   * Updates a cell in the view.
   *
   * @param c    coordinate of the cell to be updated.
   * @param cell cell to replace the previous cell.
   */
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
    table.addColumn(new TableColumn(colNum, 80,
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
    table.addKeyListener((KeyListener) listener);
    saveButton.addActionListener(listener);
    increaseRowButton.addActionListener(listener);
    decreaseRowButton.addActionListener(listener);

  }

  @Override
  public Coord getSelectedCell() {
    // indexed to one to account for label rows
    if (table.getSelectedRow() >= 0 && table.getSelectedColumn() > 0) {
      return new Coord(table.getSelectedColumn(), table.getSelectedRow() + 1);
    } else {
      return new Coord(1, 1);
    }
  }

  @Override
  public String getSaveBox() {
    return saveText.getText();
  }

  @Override
  public void setSaveBox(String s) {
    saveText.setText(s);
  }

  @Override
  public void setRowHeight(int row, int height) {
    if (height < 1) {
      table.setRowHeight(row, 1);
    }
    else {
      table.setRowHeight(row, height);
    }
  }

  @Override
  public int getRowHeight(int row) {
    return table.getRowHeight(row);
  }

  @Override
  public void setColWidth(int col, int width) {
    table.getColumnModel().getColumn(col).setWidth(width);
  }

  @Override
  public int getColWidth(int index) {
    return table.getColumnModel().getColumn(index).getWidth();
  }

}