package xlike.extractor.tools;
import org.annolab.tt4j.TokenHandler;

import xlike.model.ConllSentence;

public class LemmaHandler implements TokenHandler<String>{

	private ConllSentence conllSentence = null;
	private int tokenCount;
	
	public LemmaHandler(ConllSentence conllSentence){
		this.conllSentence = conllSentence;
		tokenCount = 1;
	}
	@Override
	public void token(String token, String pos, String lemma) {
		conllSentence.updateTokenLemma(tokenCount, token, lemma);
		tokenCount++;
		
	}

	
}
