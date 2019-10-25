import java.util.List;

 public class BasicDoubleCell extends AbstractBasicCell<Double> {

  public BasicDoubleCell(Double val) {
    super(val);
  }

  public BasicDoubleCell(Formula<Double> form) {
    super(form);
  }

  public BasicDoubleCell(Double val, List<Cell> deps) {
    super(val, deps);
  }

  public BasicDoubleCell(Formula<Double> form, List<Cell> deps) {
    super(form, deps);
  }

   @Override
   public boolean isNumericValue() {
     return true;
   }

   public Double getNumericValue(double base) {
    return this.getValue();
  }


   @Override
   public boolean equals(Object other) {
     if(!(other instanceof BasicDoubleCell)) {
       return false;
     } else {
       BasicDoubleCell otherBC = (BasicDoubleCell) other;
       return (this.getValue() == otherBC.getValue()
           && this.getDependencies() == otherBC.getDependencies()
           && this.getFormula() == otherBC.getFormula());
     }
   }
}
