import java.util.ArrayList;
import java.util.List;

public class ProductFormula implements Formula<Double> {

  List<Cell> cells;
  List<Formula<Double>> forms;

  public ProductFormula(ArrayList<Cell> cells, ArrayList<Formula<Double>> forms) {
    this.cells = cells;
    this.forms = forms;
  }

  @Override
  public Double evaluate() {
    boolean hitNumericYet = false;

    Double totalProduct = 0.0;
    for (Cell c : cells) {
      if (hitNumericYet) {
        totalProduct = totalProduct * c.getNumericValue(1.0);
      } else if (c.isNumericValue()) {
        hitNumericYet = true;
        totalProduct = c.getNumericValue(1.0);
      }
    }
    for (Formula<Double> f : forms) {
      totalProduct = totalProduct * f.evaluate();
    }

    return totalProduct;
  }


}