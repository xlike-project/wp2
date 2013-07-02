package xlike.service.params;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class defining parameters for POST requests.
 * 
 */
@XmlRootElement
public class Analyze {

	private String text;

	private String target = "entities"; // default value

	private String input = "text";// default value

	private String conll = "false";// default value

	@XmlElement
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@XmlElement
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@XmlElement
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	@XmlElement
	public String getConll() {
		return conll;
	}

	public void setConll(String conll) {
		this.conll = conll;
	}

}
