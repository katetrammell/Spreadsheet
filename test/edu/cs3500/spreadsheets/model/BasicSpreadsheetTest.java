package edu.cs3500.spreadsheets.model;
import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the BasicSpreadsheet class.
 */

public class BasicSpreadsheetTest {

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

  // Tests constructor fills with nulls
  @Test
  public void testConstructor() {
    BasicSpreadsheet spread = new BasicSpreadsheet(5, 5);
    Assert.assertEquals(spread.getWidth(), 5);
    Assert.assertEquals(spread.getHeight(), 5);
    Assert.assertEquals(spread.getCellAt(0, 3), null);
  }

/*  //tests that sum of non numbers = 0
  @Test
  public void testSumNonNum() {
    Assert.assertEquals(0.0,
        spread1.sum(spread1.getCellAt(0, 1), spread1.getCellAt(0, 2)),
        .001);
  }

  //tests that sum of nothing = 0
  @Test
  public void testSumEmpty() {
    Assert.assertEquals(0.0,
        spread1.sum(),
        .001);
  }

  // tests that sum works correctly with multiple nums
  @Test
  public void testSumOnlyNum() {
    Assert.assertEquals(5.2,
        spread1.sum(spread1.getCellAt(0, 0), spread1.getCellAt(1, 0)),
        .001);
  }

  // test that sum works correctly with one num
  @Test
  public void testSumOneNum() {
    Assert.assertEquals(5.0,
        spread1.sum(spread1.getCellAt(0, 1)),
        .001);
  }

  // tests sum with mix of num and non num
  @Test
  public void testSumMix() {
    Assert.assertEquals(5.2,
        spread1.sum(spread1.getCellAt(0, 1), spread1.getCellAt(0, 0),
            spread1.getCellAt(1, 0), spread1.getCellAt(0, 2)),
        .001);
  }


  //tests that product of non numbers = 0
  @Test
  public void tesProductNonNum() {
    Assert.assertEquals(0.0,
        spread1.product(spread1.getCellAt(0, 1), spread1.getCellAt(0, 2)),
        .001);
  }

  //tests that product of nothing = 0
  @Test
  public void testProductEmpty() {
    Assert.assertEquals(0.0,
        spread1.product(),
        .001);
  }

  // tests that product works correctly with multiple nums
  @Test
  public void testProductOnlyNum() {
    Assert.assertEquals(16.0,
        spread1.sum(spread1.getCellAt(0, 0), spread1.getCellAt(1, 0)),
        .001);
  }

  // test that product works correctly with one num
  @Test
  public void testProductOneNum() {
    Assert.assertEquals(0.0,
        spread1.product(spread1.getCellAt(0, 1)),
        .001);
  }

  // tests product with mix of num and non num
  @Test
  public void testProductMix() {
    Assert.assertEquals(16.0,
        spread1.sum(spread1.getCellAt(0, 1), spread1.getCellAt(0, 0),
            spread1.getCellAt(1, 0), spread1.getCellAt(0, 2)),
        .001);
  }

  // tests > with 1st greater
  @Test
  public void testGreaterTrue() {
    Assert.assertEquals(true, spread1.greaterThan(spread1.getCellAt(0, 0),
        spread1.getCellAt(1, 0)));
  }

  // tests > with 1st less than
  @Test
  public void testGreaterFalse() {
    Assert.assertEquals(false, spread1.greaterThan(spread1.getCellAt(1, 0),
        spread1.getCellAt(0, 0)));
  }

  // tests > with 1st greater
  @Test
  public void testGreaterEqual() {
    Assert.assertEquals(false, spread1.greaterThan(spread1.getCellAt(1, 0),
        spread1.getCellAt(1, 0)));
  }

  //test > throws when 2nd input is non num
  @Test(expected = IllegalArgumentException.class)
  public void testGreaterNonNum() {
    spread1.greaterThan(spread1.getCellAt(0, 0), spread1.getCellAt(0, 1));
  }

  //test > throws when 1st input is non num
  @Test(expected = IllegalArgumentException.class)
  public void testGreaterNonNum2() {
    spread1.greaterThan(spread1.getCellAt(0, 1), spread1.getCellAt(0, 0));
  }


  //tests that concat of non numbers = ""
  @Test
  public void testConcatNonStr() {
    Assert.assertEquals("",
        spread1.concat(spread1.getCellAt(0, 0), spread1.getCellAt(1, 1)));
  }

  //tests that concat of nothing = ""
  @Test
  public void testConcatEmpty() {
    Assert.assertEquals("", spread1.concat());
  }

  // tests that concat works correctly with multiple Strings
  @Test
  public void testConcatOnlyStr() {
    Assert.assertEquals("hi there",
        spread1.concat(spread1.getCellAt(0, 1), spread1.getCellAt(0, 2)));
  }

  // test that concat works correctly with one string
  @Test
  public void testConcatOneNum() {
    Assert.assertEquals("",
        spread1.concat(spread1.getCellAt(0, 1)));
  }

  // tests concat with mix of string and non string
  @Test
  public void testConcatMix() {
    Assert.assertEquals("hi there",
        spread1.concat(spread1.getCellAt(0, 1), spread1.getCellAt(0, 0),
            spread1.getCellAt(1, 0), spread1.getCellAt(0, 2)));
  }*/

  //tests get cell at and set cell
  @Test
  public void testGetAndSetCell() {
    Assert.assertEquals(spread1.getCellAt(0, 1), new BasicStringCell("hi"));
    spread1.setCell(new BasicBooleanCell(false), 2, 2);
    Assert.assertEquals(spread1.getCellAt(2, 2), new BasicBooleanCell(false));
  }


  // test that get cell throws invalid col
  @Test (expected = IllegalArgumentException.class)
  public void testGetInvalidCol() {
    spread1.getCellAt(2, -1);
  }

  // test that set cell throws invalid col
  @Test (expected = IllegalArgumentException.class)
  public void testSetInvalidCol() {
    spread1.setCell(new BasicDoubleCell(2.1),2, -1);
  }

}


