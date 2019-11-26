NOTE: If you're on a mac, un-comment line 186 in
 /src/edu/cs3500/spreadsheets/controller/SpreadSheetGraphicalController
 because the delete key on macs is actually the backspace key.

Brief overview of our Controller design:

  Our controller interface contains three methods, addRow(), addCol(), and updateCell()
  which are all necessary functions that all controllers but be able to perform.

  Our implementation of that interface also implements ActionListener and MouseListener
  so that the controller has the proper methods for controller our graphical view.
  The controller also uses the methods from the view and model interfaces to appropriately
  update them.



Changes to view:
* Added all of the necessary buttons to our view's render() method.
* Added the following public methods to our GraphicalView interface :
        - addCol()
              this is necessary to add a column to the view of the spreadsheet.
              Because of the way that JTable works, adding a column requires a method
               call to TableColumn, which is only accessible through the view, so this could
               not be done through explicitly through the controller like addRow() does
        - updateCell()
                Again, because of the way JTable works, to get the view to display the updated
                version of the model, a method needs to be called on the table, so this method is
                necessary.
        - getTextBox()
                  This is necessary to get the information from the view as to what
                   is in the textbox.
        - setTextBox()
                  This is necessary to set the textbox to the equation for the formula of the
                   selected cell
        - setListener()
                  This is necessary to link the buttons and mouse actions from the view to the
                  controller so that the model can be updated accordingly
        - getSelectedCell()
                  This is necessary to get the information as to what cell the user has selected
                  so that the controller can act accordingly.



