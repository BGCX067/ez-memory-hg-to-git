package personal.eido.ezmemory.data;

import personal.eido.ezmemory.data.WordProtos.Word;

public interface DataModelInterface {
	public Word getNextWord();
	
	public void incWordRank(Word word);
	
	public void decWordRank(Word word);
}
