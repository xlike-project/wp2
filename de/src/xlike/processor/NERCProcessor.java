package xlike.processor;

import xlike.extractor.EntityExtractor;
import xlike.model.Conll;
/**
 * 
 * Processes the entities
 *
 */
public class NERCProcessor implements IProcessor {

	EntityExtractor entityExtractor;

	public NERCProcessor() {
		entityExtractor = new EntityExtractor();
	}

	@Override
	public void process(Conll conll) {		
		entityExtractor.extract(conll);
	}
}
