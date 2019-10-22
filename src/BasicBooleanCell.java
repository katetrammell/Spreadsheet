import java.util.List;

public class BasicBooleanCell extends AbstractBasicCell<Boolean> {

  //test
  public BasicBooleanCell(Boolean val) {
    super(val);
  }

  public BasicBooleanCell(Formula<Boolean> form) {
    super(form);
  }

  public BasicBooleanCell(Boolean val, List<Cell> deps) {
    super(val, deps);
  }

  public BasicBooleanCell(Formula<Boolean> form, List<Cell> deps) {
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
