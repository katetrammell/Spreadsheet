package edu.cs3500.spreadsheets.sexp;

import java.util.List;

/**
 * Checks the if different sexpressions are a list.
 */
public class IsSymbol implements SexpVisitor<Boolean> {
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
    return false;
  }

  @Override
  public Boolean visitSymbol(String s) {
    return true;
  }

  @Override
  public Boolean visitString(String s) {
    return false;
  }
}
