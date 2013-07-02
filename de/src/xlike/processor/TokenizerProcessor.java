package xlike.processor;

import xlike.extractor.TokenExtractor;
import xlike.model.Conll;

/**
 * 
 * Processes the tokens
 *
 */
public class TokenizerProcessor {
	private TokenExtractor tokenExtractor;

	public TokenizerProcessor() {
		tokenExtractor = new TokenExtractor();
	}

	public Conll process(String text) {

		return tokenExtractor.extract(text);
	}

}
