package xlike.processor;

import xlike.extractor.LemmaExtractor;
import xlike.model.Conll;
/**
 * Processes the lemmas
 *
 */
public class LemmatizerProcessor implements IProcessor {
	
	private LemmaExtractor lemmaExtractor;

	public LemmatizerProcessor(){
		lemmaExtractor = new LemmaExtractor();
	}

	@Override
	public void process(Conll conll) {
		lemmaExtractor.extract(conll);
	}

}
