import java.util.ArrayList;
import java.util.List;

public class SumFormula implements Formula<Double> {
  List<Cell> cells;
  List<Formula<Double>> forms;


  public SumFormula(ArrayList<Cell> cells, ArrayList<Formula<Double>> forms) {
    this.cells = cells;
    this.forms = forms;
  }

  public SumFormula(){
    this.cells = new ArrayList<Cell>();
    this.forms = new ArrayList<Formula<Double>>();
  }

  @Override
  public Double evaluate() {
    Double totalSum = 0.0;
    for (Cell c : cells) {
     totalSum += c.getNumericValue(0.0);
    }
    for (Formula<Double> f : forms) {
      totalSum += f.evaluate();
    }
    return totalSum;
  }
}
