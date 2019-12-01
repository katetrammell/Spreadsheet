package edu.cs3500.spreadsheets.provider.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Visual processing of user input in a spreadsheet application.
 */
class InputPanel extends JPanel {

  private JTextField inputField;
  private JButton approvalButton;
  private JButton cancelButton;

  /**
   * Creation of the user input dialog.
   */
  InputPanel() {
    this.inputField = new JTextField() {
      @Override
      public void paintComponent(Graphics g) {
        antiAlias(g);
        super.paintComponent(g);
      }
    };

    this.approvalButton = new JButton("✓") {
      @Override
      public void paintComponent(Graphics g) {
        antiAlias(g);
        super.paintComponent(g);
      }
    };

    this.cancelButton = new JButton("X") {
      @Override
      public void paintComponent(Graphics g) {
        antiAlias(g);
        super.paintComponent(g);
      }
    };

    this.inputField.setBackground(new Color(245, 245, 245));

    this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    this.add(cancelButton);
    this.add(approvalButton);
    this.add(inputField);
  }

  // makes text for buttons and input more readable
  private void antiAlias(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
  }

  // set callback for button events
  void setFeatureHandler(FeatureHandler handler) {
    this.approvalButton.addActionListener(e -> handler.textEntered(this.inputField.getText()));
    this.cancelButton.addActionListener(e -> handler.cellDeselected());
    this.inputField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent keyEvent) {
        // not needed
      }

      @Override
      public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
          handler.textEntered(inputField.getText());
        }
      }

      @Override
      public void keyReleased(KeyEvent keyEvent) {
        // not needed
      }
    });
  }

  // update input text display
  void updateTextBox(String contents) {
    this.inputField.setText(contents);
  }
}
