package edu.cs3500.spreadsheets.sexp;

import java.util.List;

/**
 * Helper class that tells if an item is a list.
 */
public class IsList implements SexpVisitor<Boolean> {
  @Override
  public Boolean visitBoolean(boolean b) {
    return false;
  }

  @Override
  public Boolean visitNumber(double d) {
    return false;
  }

  @Override
  public Boolean visitSList(List<Sexp> l) {
    return true;
  }

  @Override
  public Boolean visitSymbol(String s) {
    return false;
  }

  @Override
  public Boolean visitString(String s) {
    return false;
  }
}
