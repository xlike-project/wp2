package xlike.util;
/**
 *  Level of processing in input/output
 *
 */
public enum ProcessingLevel {
	TEXT, TOKENS, LEMMAS, ENTITIES, RELATIONS, UNKNOWN;
	
	public static  ProcessingLevel convert(String type){
		if(type.toLowerCase().equals("text")){
			return ProcessingLevel.TEXT;
		}else if(type.toLowerCase().equals("tokens")){
			return ProcessingLevel.TOKENS;
		}else if(type.toLowerCase().equals("lemmas")){
			return ProcessingLevel.LEMMAS;
		}else if(type.toLowerCase().equals("entities")){
			return ProcessingLevel.ENTITIES;
		}else if(type.toLowerCase().equals("relations")){
			return ProcessingLevel.RELATIONS;
		}else{
			return ProcessingLevel.UNKNOWN;
		}
	}
}
