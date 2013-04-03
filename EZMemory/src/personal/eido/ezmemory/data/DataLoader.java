package personal.eido.ezmemory.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import personal.eido.ezmemory.data.WordProtos.Word;
import personal.eido.ezmemory.data.WordProtos.WordList;
import au.com.bytecode.opencsv.CSVReader;

public class DataLoader {
	static public final int INDEX_KEYWORD = 0;
	static public final int INDEX_SHORT = 1;
	static public final int INDEX_LONG = 2;
	static public final int INDEX_RANK = 3;
	
	public static WordList loadData(File input){
		
		WordList.Builder wordList = WordList.newBuilder();
		try {
//			BufferedReader br = new BufferedReader(new InputStreamReader(input));
//			String line;
//			br.readLine();	//read the first column names line
			String[] rowData;
			CSVReader cr = new CSVReader(new FileReader(input));
			cr.readNext(); //read the first column names line
			while((rowData = cr.readNext())!=null){
				Word.Builder word = Word.newBuilder();
				word.setKeyWord(rowData[DataLoader.INDEX_KEYWORD])
					.setShortDescription(rowData[DataLoader.INDEX_SHORT])
					.setLongDescription(rowData[DataLoader.INDEX_LONG]);
				if(isValidInt(rowData[DataLoader.INDEX_RANK])){				
					word.setRank(DataModel.regularRank(Integer.parseInt(rowData[DataLoader.INDEX_RANK])));
				} 
				wordList.addWord(word.build());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return wordList.build();
	}
	
	private static boolean isValidInt(String data){
		try{
			Integer.parseInt(data);
		} catch(NumberFormatException e){
			return false;
		}
		return true;
	}
}
