package xlike.extractor;

import org.apache.log4j.Logger;

import xlike.extractor.tools.SentenceSplitter;
import xlike.extractor.tools.TokenSplitter;
import xlike.model.Conll;
import xlike.model.ConllSentence;

public class TokenExtractor {

	private static Logger log = Logger.getLogger(TokenExtractor.class);
	private static TokenSplitter tokenizer = TokenSplitter.getInstance();
	private static SentenceSplitter sentenceSplitter = SentenceSplitter
			.getInstance();


	public Conll extract(String text) {
		log.debug("Extracting tokens...");
		Conll conll = new Conll();
		String[] sentences = sentenceSplitter.getSenetnces(text);
		for (int i = 0; i < sentences.length; i++) {
			String sentence = sentences[i];
			ConllSentence conllSentence = new ConllSentence();
			String[] tokens = tokenizer.getTokens(sentence);
			for (int j = 0; j < tokens.length; j++) {
				conllSentence.addNewTokenWord(tokens[j]);
			}
			conll.addSentence(conllSentence);
		}
		return conll;
	}

}
