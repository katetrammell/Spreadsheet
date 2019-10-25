import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductFormulaTest {

  BasicSpreadsheet spread1;


  @Before
  public void setUp() {
    spread1 = new BasicSpreadsheet(3, 3);
    spread1.setCell(new BasicDoubleCell(5.0), 0, 0);
    spread1.setCell(new BasicStringCell("hi"), 0, 1);
    spread1.setCell(new BasicStringCell(" there"), 0, 2);

    spread1.setCell(new BasicDoubleCell(3.2), 1, 0);
    spread1.setCell(new BasicBooleanCell(true), 1, 1);
  }

  @Test
  public void testProduct() {
    ArrayList<Cell> cells = new ArrayList<Cell>();
    cells.add(spread1.getCellAt(0,0));
    ProductFormula prodForm1 = new ProductFormula(cells, new ArrayList<Formula<Double>>());

    Assert.assertEquals(prodForm1.evaluate(), 5.0, .001);
  }
}
