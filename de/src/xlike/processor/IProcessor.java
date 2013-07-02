package xlike.processor;

import xlike.model.Conll;

/**
 * 
 * Interface for processor implementations
 * 
 */
public interface IProcessor {
	/**
	 * Processes the input
	 * 
	 * @param conll
	 *            conll to update with appropriate tags
	 */
	public void process(Conll conll);

}
