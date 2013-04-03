package personal.eido.ezmemory.data;

import java.io.File;
import java.util.ArrayList;

import android.os.Environment;

import personal.eido.ezmemory.data.WordProtos.Word;
import personal.eido.ezmemory.data.WordProtos.WordList;


public class DataModel implements DataModelInterface{
	static public final int RANK_LENGTH = 5;
	static public final int RANK_MAX = 4;
	static public final int RANK_MIN = 0;
	static public final int RANK_DEFAULT = 2;
	
	public ArrayList<Word>[] data;
	private WordList wordList;
	
	@SuppressWarnings("unchecked")
	public DataModel(){
		wordList = DataLoader.loadData(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "testdata.csv"));
		
		data = (ArrayList<Word>[]) new ArrayList[DataModel.RANK_LENGTH];
		for(int i = 0; i < data.length; i++){
			data[i] = new ArrayList<Word>();
		}
		for(Word word : wordList.getWordList()){
			data[regularRank(word.getRank())].add(word);  
		}
	}


	public static boolean isRankValid(int rank){
		if(rank < DataModel.RANK_MIN){
			return false;
		} else if (rank > DataModel.RANK_MAX){
			return false;
		} else {
			return true;
		}
	}
	
	public static int regularRank(int rank){
		if(rank < DataModel.RANK_MIN){
			return DataModel.RANK_MIN;
		} else if(rank > DataModel.RANK_MAX){
			return DataModel.RANK_MAX;
		} else {
			return rank;
		}
	}


	/**
	 * Rank	Percentage	random range
	 *	4		40			61-99
	 *	3		25			36-60
	 *	2		20			16-35
	 *	1		10			6-15
	 *	0		5			0-5
	 */
	@Override
	public Word getNextWord() {
		int random = (int)(Math.random()*100);
		if(random > 60){
			return getWordWithRank(4);
		} else if (random > 35){
			return getWordWithRank(3);
		} else if (random > 15){
			return getWordWithRank(2);
		} else if (random > 5){
			return getWordWithRank(1);
		} else {
			return getWordWithRank(0);
		}
	}
	
	private Word getWordWithRank(int rank){
		if(Math.abs(rank) > DataModel.RANK_LENGTH){ //in case "data" is empty
			return null;
		} else if(data[rank].isEmpty()){
			return getWordWithRank((rank-1)%DataModel.RANK_LENGTH);
		} else {
			Word word = data[rank].remove(0);
			data[rank].add(word);	//shift the selected item from list beginning to list end.
			return word;
		}
	}


	@Override
	public void incWordRank(Word word) {
		data[DataModel.regularRank(word.getRank())].remove(word);
		Word newWord = Word.newBuilder(word).setRank(DataModel.regularRank(word.getRank()+1)).build();
		data[newWord.getRank()].add(newWord);
	}


	@Override
	public void decWordRank(Word word) {
		data[DataModel.regularRank(word.getRank())].remove(word);
		Word newWord = Word.newBuilder(word).setRank(DataModel.regularRank(word.getRank()-1)).build();
		data[newWord.getRank()].add(newWord);		
	}
}
