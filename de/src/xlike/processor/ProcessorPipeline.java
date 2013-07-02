package xlike.processor;

import java.util.ArrayList;
import java.util.List;

import xlike.model.Conll;
import xlike.util.ProcessingLevel;

public class ProcessorPipeline {
	private List<IProcessor> processors;

	public ProcessorPipeline() {
		processors = new ArrayList<IProcessor>();
	}

	/**
	 * Configures the processing pipeline.
	 * 
	 * @param input
	 *            tokens, lemmas, entities
	 * @param target
	 *            tokens, lemmas, entities, relations
	 */
	public void configure(ProcessingLevel input, ProcessingLevel target) {
		switch (input) {
		case TOKENS:
			configurePipelineFromTokens(target);
			break;
		case LEMMAS:
			configurePipelineFromLemmas(target);
			break;
		case ENTITIES:
			configurePipelineFromEntities(target);
		default:
			break;
		}

	}

	/**
	 * Creates conll object filled with tokens from free text
	 * 
	 * @param text
	 *            text to tokenize
	 * @return conll object representation
	 */
	public Conll getTokenConll(String text) {
		TokenizerProcessor tokenizer = new TokenizerProcessor();
		return tokenizer.process(text);
	}

	/**
	 * Process the conll in the configured pipeline
	 * 
	 * @param conll
	 *            to fill with data
	 */
	public void process(Conll conll) {

		for (IProcessor processor : processors) {
			processor.process(conll);
		}
	}
	
	private void configurePipelineFromTokens(ProcessingLevel target) {
		switch (target) {
		case LEMMAS:
			LemmatizerProcessor lemmatizer = new LemmatizerProcessor();
			processors.add(lemmatizer);
			break;
		case ENTITIES:
			LemmatizerProcessor lemmatizer1 = new LemmatizerProcessor();
			processors.add(lemmatizer1);
			NERCProcessor neAnalyzer = new NERCProcessor();
			processors.add(neAnalyzer);
			break;
		case RELATIONS:
			LemmatizerProcessor lemmatizer2 = new LemmatizerProcessor();
			processors.add(lemmatizer2);
			NERCProcessor neAnalyzer1 = new NERCProcessor();
			processors.add(neAnalyzer1);
			RelationProcessorMock relationAnalyzer = new RelationProcessorMock();
			processors.add(relationAnalyzer);
			break;
		default:
			break;
		}
	}

	private void configurePipelineFromLemmas(ProcessingLevel target) {
		switch (target) {
		case ENTITIES:
			NERCProcessor neAnalyzer = new NERCProcessor();
			processors.add(neAnalyzer);
			break;
		case RELATIONS:
			NERCProcessor neAnalyzer1 = new NERCProcessor();
			processors.add(neAnalyzer1);
			RelationProcessorMock relationAnalyzer = new RelationProcessorMock();
			processors.add(relationAnalyzer);
			break;
		default:
			break;
		}
	}
	private void configurePipelineFromEntities(ProcessingLevel target) {
		switch (target) {
		case RELATIONS:
			RelationProcessorMock relationAnalyzer = new RelationProcessorMock();
			processors.add(relationAnalyzer);
			break;
		default:
			break;
		}
	}
}
