package edu.cs3500.spreadsheets.provider.view;


import edu.cs3500.spreadsheets.provider.model.Coord;
import edu.cs3500.spreadsheets.provider.model.WorksheetReadOnly;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A graphical view of a worksheet that contains the mechanisms necessary to edit the model.
 */
public class SimpleWorksheetWritableGuiView extends SimpleWorksheetReadableGuiView {

  private final InputPanel inputPanel;

  /**
   * Constructor for the visual view.
   *
   * @param ws the read-only worksheet to display.
   */
  public SimpleWorksheetWritableGuiView(WorksheetReadOnly ws) {
    super(ws);
    this.inputPanel = new InputPanel();
    this.add(inputPanel, BorderLayout.NORTH);
  }

  @Override
  public void setFeatureHandler(FeatureHandler handler) {
    super.setFeatureHandler(handler);

    this.inputPanel.setFeatureHandler(handler);

    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent keyEvent) {
        // not needed
      }

      @Override
      public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_DELETE) {
          handler.deleteSelectedCell();
        }
      }

      @Override
      public void keyReleased(KeyEvent keyEvent) {
        // not needed
      }
    });
  }

  @Override
  public void selectCell(Coord cellCoord) {
    super.selectCell(cellCoord);

    String formula = ws.getRawContent(cellCoord);
    if (!formula.isBlank()) {
      this.inputPanel.updateTextBox("=" + ws.getRawContent(cellCoord));
    } else {
      this.inputPanel.updateTextBox("");
    }
  }

  @Override
  public void deselectCell() {
    super.deselectCell();
    this.inputPanel.updateTextBox("");
  }
}