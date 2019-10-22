import java.util.List;

public class BasicCell<T> implements Cell<T> {
  protected T value;
  protected Formula<T> formula;
  protected List<Cell> dependencies;

  BasicCell(T val) {
    this.value = val;
  }

  @Override
  public T getValue() {
    return this.value;
  }

  @Override
  public Formula<T> getFormula() {
    return this.formula;
  }

  @Override
  public void setFormula(Formula<T> form) {
    //TODO check for circular reference
    this.formula = form;
  }

  @Override
  public void setValue(T val) {
    // TODO update dependencies 
    this.value = val;
  }

  @Override
  public List<Cell> getDependencies() {
    return this.dependencies;
  }

  @Override
  public  void setDependent(Cell c) {
    this.dependencies.add(c);
  }
}
