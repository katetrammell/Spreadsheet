NOTE: If you're on a mac, un-comment line 186 in
 /src/edu/cs3500/spreadsheets/controller/SpreadSheetGraphicalController
 because the delete key on macs is actually the backspace key.

EXTRA CREDIT:
We implemented the following:
1) a user can save their edited spreadsheet from within the UI. They need to input the name of the file that they want to save it as and then press the save button.
2) The user can use arrow keys to move along the spreadsheet.
3) The user can use the delete key to clear the contents of a cell.

Brief overview of our Controller design:

  Our controller interface contains three methods, addRow(), addCol(), and updateCell()
  which are all necessary functions that all controllers but be able to perform.

  Our implementation of that interface also implements ActionListener and MouseListener
  so that the controller has the proper methods for controller our graphical view.
  The controller also uses the methods from the view and model interfaces to appropriately
  update them.


