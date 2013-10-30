package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettings;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKTokenizer;

import java.io.Reader;

public class IkTokenizerFactory extends AbstractTokenizerFactory {
  private boolean useSmart = false;
  private final boolean mergeSingleChar;

  @Inject
  public IkTokenizerFactory(Index index,@IndexSettings Settings indexSettings,@Assisted String name, @Assisted Settings settings) {
    super(index, indexSettings, name, settings);
    Dictionary.getInstance().Init(indexSettings);

    useSmart = settings.getAsBoolean("use_smart", false);
    mergeSingleChar = settings.getAsBoolean("merge_single_char", false);
  }

  @Override
  public Tokenizer create(Reader reader) {
    return new IKTokenizer(reader, useSmart, mergeSingleChar);
  }

}
