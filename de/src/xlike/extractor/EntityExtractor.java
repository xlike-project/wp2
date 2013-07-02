package xlike.extractor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import xlike.extractor.tools.NERClassifier;
import xlike.model.Conll;
import xlike.model.ConllSentence;
import edu.stanford.nlp.ling.CoreAnnotations.AnswerAnnotation;
import edu.stanford.nlp.ling.CoreLabel;

public class EntityExtractor {
	private static Logger log = Logger.getLogger(EntityExtractor.class);
	private static NERClassifier nerClassifier = NERClassifier.getInstance();

	//gets ne tags from entire text
	// public void extract(Conll conll) {
	// log.debug("Extracting entities...");
	// List<List<CoreLabel>> out = nerClassifier.getNERCTags(conll.getText());
	//
	// int sentenceIndex = 1;
	// int tokenIndex = 1;
	// for (List<CoreLabel> sentence : out) {
	// ConllSentence conllSentence = conll.getSentences().get(
	// sentenceIndex);
	// for (CoreLabel word : sentence) {
	// conllSentence.updateTokenNERCTags(tokenIndex, word.word(),
	// word.get(AnswerAnnotation.class), word.beginPosition(),
	// word.endPosition());
	// tokenIndex++;
	// }
	// sentenceIndex++;
	// tokenIndex = 1;
	// }
	//
	
	//gets ne tags on sentence level
	public void extract(Conll conll) {
		log.debug("Extracting entities...");
		for (int sentenceIndex : conll.getSentences().keySet()) {
			String sentenceStr = conll.getSentences().get(sentenceIndex)
					.getText();
			List<List<CoreLabel>> out = nerClassifier.getNERCTags(sentenceStr);
			List<CoreLabel> sentence = new ArrayList<CoreLabel>();
			for (List<CoreLabel> sent : out) {
				for (CoreLabel word : sent) {
					sentence.add(word);
				}
			}
			ConllSentence conllSentence = conll.getSentences().get(
					sentenceIndex);
			int tokenIndex = 1;
			for (CoreLabel word : sentence) {
				conllSentence.updateTokenNERCTags(tokenIndex, word.word(),
						word.get(AnswerAnnotation.class), word.beginPosition(),
						word.endPosition());
				tokenIndex++;
			}

		}
	}

}
