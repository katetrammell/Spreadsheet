import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SumFormulaTest {
  Spreadsheet spread1;

  @Before
  public void setUp() {
    spread1 = new BasicSpreadsheet(3, 3);
    spread1.setCell(new BasicDoubleCell(5.0), 0, 0);
    spread1.setCell(new BasicStringCell("hi"), 0, 1);
    spread1.setCell(new BasicStringCell(" there"), 0, 2);

    spread1.setCell(new BasicDoubleCell(3.2), 1, 0);
    spread1.setCell(new BasicBooleanCell(true), 1, 1);
  }

  /**
   * Tests Evaluate with 1 num
   */
  @Test
  public void test1Num() {
    SumFormula sum1 = new SumFormula();
    sum1.cells.add(spread1.getCellAt(0,0));
    Assert.assertEquals(sum1.evaluate(), 5.0, .00001);
  }

}
