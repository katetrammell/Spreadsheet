import java.util.ArrayList;
import java.util.List;

// TODO should we make it immutable?
// make fields final, get rid of set values, and when we want to update, make a new cell?
abstract public class AbstractBasicCell<T> implements Cell<T> {
  private final T value;
  private final Formula<T> formula;
  private final List<Cell> dependencies;

  public AbstractBasicCell(T val) {
    this.value = val;
    this.formula = null;
    this.dependencies = new ArrayList<Cell>();
  }

  public AbstractBasicCell(Formula<T> form) {
    this.value = null;
    this.formula = form;
    this.dependencies = new ArrayList<Cell>();
  }

  public AbstractBasicCell(T val, List<Cell> deps) {
    this.value = val;
    this.formula = null;
    this.dependencies = deps;
  }

 public AbstractBasicCell(Formula<T> form, List<Cell> deps) {
    this.value = null;
    this.formula = form;
    this.dependencies = deps;
  }



  //TODO make copy of value?
  @Override
  public T getValue() {
    return  this.value;
  }

  //TODO make copy of formula?
  @Override
  public Formula<T> getFormula() {
    return this.formula;
  }

  @Override
  public List<Cell> getDependencies() {
    List<Cell> copy = new ArrayList<Cell>(this.dependencies);
    return copy;
  }

  @Override
  public  void setDependent(Cell c) {
    this.dependencies.add(c);
  }


}
