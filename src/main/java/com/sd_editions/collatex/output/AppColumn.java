package com.sd_editions.collatex.output;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.Lists;
import com.sd_editions.collatex.permutations.Gap;
import com.sd_editions.collatex.permutations.Witness;
import com.sd_editions.collatex.permutations.Word;

public class AppColumn extends Column {

  private final ArrayList<Gap> gaps;

  public AppColumn(Gap... _gaps) {
    this.gaps = Lists.newArrayList(_gaps);
  }

  @Override
  public void toXML(StringBuilder xml) {
    xml.append("<app>");
    for (Gap gap : gaps) {
      xml.append("<rdg wit=\"#").append(gap.getWitness().id).append('"');
      if (!gap.hasGap())
        xml.append("/>");
      else
        xml.append(">").append(gap.toString()).append("</rdg>");
    }

    xml.append("</app>");
  }

  @Override
  public Word getWord(Witness witness) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addMatch(Witness witness, Word word) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsWitness(Witness witness) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<Word> getWords() {
    throw new UnsupportedOperationException();
  }

}
