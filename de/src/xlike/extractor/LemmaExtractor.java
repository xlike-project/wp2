package xlike.extractor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import xlike.extractor.tools.LemmaTagger;
import xlike.extractor.tools.PosTagger;
import xlike.model.Conll;
import xlike.model.ConllSentence;

public class LemmaExtractor {
	private static Logger log = Logger.getLogger(LemmaExtractor.class);
	private static LemmaTagger lemmaTagger = LemmaTagger.getInstance();
	private static PosTagger posTagger = PosTagger.getInstance();

	public void extract(Conll conll) {
		log.debug("Extracting lemmas...");
		extractLemmas(conll);
		log.debug("Lemmas extracted result conll: " +conll.toFormattedString());
		log.debug("Extracting pos tags...");
		extractPosTags(conll);

	}

	private void extractLemmas(Conll conll) {
		for (int sentenceIndex : conll.getSentences().keySet()) {
			ConllSentence conllSentence = conll.getSentences().get(
					sentenceIndex);
			String[] wordList = conllSentence.getWordList();
			lemmaTagger.getLemmas(conllSentence, wordList);
		}
	}
//Get s the pos tags from entire text at once
//	private void extractPosTags(Conll conll) {
//		List<List<HasWord>> sentences = posTagger.getSentences(conll.getText());
//		int tokenIndex = 1;
//		int sentenceIndex = 1;
//		for (List<HasWord> sentence : sentences) {
//			String[] wordList = new String[sentence.size()];
//			int wordIndex = 0;
//			ConllSentence conllSentence = conll.getSentences().get(
//					sentenceIndex);
//			log.debug("About to tag sentence: ");
//			log.debug("CONLL SENTENCE: "+conllSentence.getText());
//			String s="";
//			for (HasWord word : sentence) {
//				s+=word.word()+" ";
//			}
//			log.debug("POS TAGGER SENT:"+s);
//			
//			ArrayList<TaggedWord> tSentence = posTagger.getPosTags(sentence);
//			log.debug("Pos tagger found "+tSentence.size()+" tokens");
//			for (TaggedWord t : tSentence) {
//				log.debug("About to update conll for sentence " +sentenceIndex+" and token id "+tokenIndex);
//				conllSentence.updateTokenPosTag(tokenIndex, t.word(), t.tag(),
//						t.beginPosition(), t.endPosition());
//				tokenIndex++;
//				wordList[wordIndex] = t.word();
//				wordIndex++;
//			}
//
//			sentenceIndex++;
//			tokenIndex = 1;
//		}
//	}
	//Gets the pos tags sentence by sentence
	private void extractPosTags(Conll conll) {
		
		for(int sentenceIndex: conll.getSentences().keySet()){
			String sentenceString=conll.getSentences().get(sentenceIndex).getText();
			List<List<HasWord>> sentences = posTagger.getSentences(sentenceString);
			List<HasWord>sentence= new ArrayList<HasWord>();
			for(List<HasWord> sent:sentences){
				for(HasWord word:sent){
					sentence.add(word);
				}
			}
			ConllSentence conllSentence = conll.getSentences().get(
					sentenceIndex);
			int tokenIndex = 1;
			ArrayList<TaggedWord> tSentence = posTagger.getPosTags(sentence);
			log.debug("Pos tagger found "+tSentence.size()+" tokens");
			for (TaggedWord t : tSentence) {
				log.debug("About to update conll for sentence " +sentenceString+" and token id "+tokenIndex);
				conllSentence.updateTokenPosTag(tokenIndex, t.word(), t.tag(),
						t.beginPosition(), t.endPosition());
				tokenIndex++;
			
			}
		
		
		}
	}
}
