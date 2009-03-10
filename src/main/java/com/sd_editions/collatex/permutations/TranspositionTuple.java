package com.sd_editions.collatex.permutations;

public class TranspositionTuple {

  private final Tuple2<MatchSequence> tuple;

  public TranspositionTuple(Tuple2<MatchSequence> _tuple) {
    this.tuple = _tuple;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TranspositionTuple)) {
      return false;
    }
    TranspositionTuple tuple2 = (TranspositionTuple) obj;
    boolean result = tuple2.getLeftWordCode().equals(getLeftWordCode()) && tuple2.getRightWordCode().equals(getRightWordCode());
    //    System.out.println("comparing: " + this.toString() + " && " + tuple2.toString() + " result: " + result);
    return result;
  }

  @Override
  public int hashCode() {
    return getLeftWordCode().hashCode() + getRightWordCode().hashCode();
  }

  @Override
  public String toString() {
    return tuple.toString();
  }

  MatchSequence getLeftSequence() {
    return tuple.left;
  }

  MatchSequence getRightSequence() {
    return tuple.right;
  }

  Integer getRightWordCode() {
    return getRightSequence().code;
  }

  Integer getLeftWordCode() {
    return getLeftSequence().code;
  }

}
