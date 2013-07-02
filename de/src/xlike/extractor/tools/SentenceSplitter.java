package xlike.extractor.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import org.apache.log4j.Logger;

public class SentenceSplitter {

	private static Logger log = Logger.getLogger(SentenceSplitter.class);
	private static final SentenceSplitter instance = new SentenceSplitter();	

	private SentenceDetectorME sentenceDetector = null;
	
	private SentenceSplitter(){
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("xlike_de.properties"));
		} catch (IOException e) {
			log.error("Error loading service properties");
			e.printStackTrace();
		}
		initSentenceSplitter(properties);
	}
	
	private void initSentenceSplitter(Properties properties) {
		InputStream sentenceModelIn = null;
		try {
			String senetenceModelPath = properties.getProperty("models.path") + properties.getProperty("sentence.model");
			log.debug("Loading sentence model from " + senetenceModelPath);
			sentenceModelIn = getClass().getClassLoader().getResourceAsStream(senetenceModelPath);
			 SentenceModel sentenceModel = new SentenceModel(sentenceModelIn);
			sentenceDetector = new SentenceDetectorME(sentenceModel);

		} catch (IOException e) {
			log.error("Error loading sentence model");
			e.printStackTrace();
		}
		if(sentenceModelIn != null){
			try {
				sentenceModelIn.close();
			} catch (IOException e) {
				//ignore
			}
		}
	}
	
	public static SentenceSplitter getInstance(){
		return instance;
	}
	
	public synchronized String[] getSenetnces(String text){
		return sentenceDetector.sentDetect(text);
	}


}
