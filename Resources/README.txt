How we made our model and controller work with their view
	1) Made a class OurProviderController that implemented their controller interface
				Had a field that was our old controller (adapter pattern)
	2) Made a class OurProividerModel that implemented their model interface
				Had a field that was our old model (adapter pattern)
	3) Made various classes OurCellValue___ that implemented their Cell interface
				They all had a field that was our old cell representation (adapter pattern)

We were able to implement all of the functionality from assignment 7 using the strategy above.