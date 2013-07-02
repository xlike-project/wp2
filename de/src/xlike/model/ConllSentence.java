package xlike.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ConllSentence {
	private static final String WHITESPACE = " ";
	private static Logger log = Logger.getLogger(ConllSentence.class);
	private Map<Integer, ConllToken> conllTokens;
	private int tokenCount;

	public ConllSentence() {
		conllTokens = new LinkedHashMap<Integer, ConllToken>();
		tokenCount = 0;
	}

	public boolean isEmpty(){
		if(conllTokens.size() == 0){
			return true;
		}
		return false;
	}
	public String getText() {
		StringBuilder sb = new StringBuilder();

		for (int tokenIndex : conllTokens.keySet()) {
			ConllToken token = conllTokens.get(tokenIndex);
			String word = token.getWord();
			if (sb.length() == 0) {
				sb.append(word);
			} else {
				if (!".".equals(word) && !",".equals(word) && !":".equals(word)
						&& !";".equals(word)) {
					sb.append(WHITESPACE);
				}
				sb.append(word);
			}
		}
		return sb.toString();
	}

	public String[] getWordList() {
		String[] words = new String[conllTokens.size()];
		int i = 0;
		for (int tokenIndex : conllTokens.keySet()) {
			ConllToken conllToken = conllTokens.get(tokenIndex);
			words[i] = conllToken.getWord();
			i++;
		}
		return words;
	}

	public void addNewTokenWord(String word) {
		tokenCount++;
		ConllToken attrs = new ConllToken(tokenCount, word);
		conllTokens.put(tokenCount, attrs);
	}
	public void updateTokenWord(int index, String word) {
		ConllToken token = conllTokens.get(index);
		if (token != null) {
			if (token.getId() == index) {
				token.setWord(word);
			} else {
				log.error("Error updating token. Token " + word
						+ " does not exist");
			}
		} else {
			log.debug("Token " + word + "does not exist. Creating new token.");
			ConllToken newToken = new ConllToken(index, word, null, null, null,
					null);
			conllTokens.put(index, newToken);
		}
	}


	public void updateTokenLemma(int tokenIndex, String word, String lemma) {
		ConllToken token = conllTokens.get(tokenIndex);
		if (token != null) {
			if (token.getWord().equals(word)) {
				token.setLemma(lemma);
			} else {
				log.error("Error updating token. Token " + word
						+ " does not exist");
			}
		} else {
			log.error("Error updating token. Token " + word + " does not exist ");
		}
	}
	private ConllToken findToken(String word){
		if(word.equals("-RRB-")){
			word=")";
		}else if(word.equals("-LRB-")){
			word="(";
		}
		for(int id: conllTokens.keySet()){
			ConllToken token = conllTokens.get(id);
			if(token.getWord().equals(word)){
			log.debug("Token found for word " + word);
				return token;
			}
		}
		log.debug("Token "+word+"not found in sentence:" +this.getText());
		return null;
	}
	public void updateTokenPosTag(int tokenIndex, String word, String pos,
			int start, int end) {
		ConllToken token =findToken(word);//conllTokens.get(tokenIndex);
		if (token != null) {
			if(word.equals("-RRB-")){
				word=")";
			}else if(word.equals("-LRB-")){
				word="(";
			}
			if (token.getWord().equals(word)) {
				token.setPos(pos);
				token.setMsd(pos);
				token.setStart(start);
				token.setEnd(end);
			} else {
				log.error("Error updating token. Token " + word
						+ " does not exist");
			}
		} else {
			log.error("Error updating token. Token " + word + " does not exist token== null");
		}
	}
	
	public void updateTokenNERCTags(int tokenIndex, String word, String namedEntity, int start,
			int end) {
		ConllToken token = findToken(word);// conllTokens.get(tokenIndex);
		if (token != null) {
			if(word.equals("-RRB-")){
				word=")";
			}else if(word.equals("-LRB-")){
				word="(";
			}
			if (token.getWord().equals(word)) {
				token.setNe(namedEntity);
				token.setStart(start);
				token.setEnd(end);
			} else {
				log.error("Error updating token. Token " + word
						+ " does not exist");
			}
		} else {
			log.error("Error updating token. Token " + word
					+ " does not exist");
		}
	}

	

	public void addToken(ConllToken token) {
		tokenCount++;
		conllTokens.put(token.getId(), token);
	}

	public Map<Integer, ConllToken> getTokens() {
		return conllTokens;
	}
}
