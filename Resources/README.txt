Making rows and columns resizable:
* To change the size of the rows in the view we did this by adding JButtons to the editable
view which allow for changing row height. They change the row of the cell that is selected.
      To the SpreadSheetGraphicalView interface we added getter and setter methods for row heights,
       which these new buttons call to change the height in the view.
      To the Spreadsheet interface we also added getter and setter methods for row height.
      These affect a new field of the model class HashMap<Integer, Integer> rowHeights
      which allows the model to store changes to the SpreadSheet row height.
      The SpreadSheetGraphicalView and BasicSpreadsheet model are both updated through the
      SpreadSheetController in the actionerPeformed method

*Changing the column width is done in a similar manner as rows, except the column widths is
 done by mousing over a column and dragging. We added getter and setter methods for column widths
 in both the view and model, which are updated through the controller.

*In the SpreadsheetTextualView any changes to column widths or row heights are
stored in the form “COL INDEX NEWSIZE” and “ROW INDEX NEWSIZE”
(ex. ROW 4 20 -> row 4 is now size 20).
      This allows changes in row/ column sizes to be saved, or edited beforehand.
      Any row or columns that are not explicitly specified are assumed to be a default size.


Column References
*In CellMaker (under Sexp), added the capability to reference a column (a single capital letter)
or two columns (two capital letters separated by a column)
      Done in the same manner that our two cell space (e.g. A3:D4) was done.
      Just added another try catch branch for the two new refs
