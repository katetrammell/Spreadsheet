import java.util.List;

public class BasicStringCell extends AbstractBasicCell<String>{


  public BasicStringCell(String val) {
    super(val);
  }

  public BasicStringCell(Formula<String> form) {
    super(form);
  }

  public BasicStringCell(String val, List<Cell> deps) {
    super(val, deps);
  }

  public BasicStringCell(Formula<String> form, List<Cell> deps) {
    super(form, deps);
  }

  @Override
  public boolean isNumericValue() {
    return false;
  }

  @Override
  public Double getNumericValue(double base) {
    return base;
  }
}
