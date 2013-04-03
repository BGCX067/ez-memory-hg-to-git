package personal.eido.ezmemory;

import personal.eido.ezmemory.data.DataModel;
import personal.eido.ezmemory.data.WordProtos.Word;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private StateMachine sm;
	private DataModel dm;
	
	private TextView keyWord;
	private TextView shortDesc;
	private Button button_yes;
	private Button button_no;
	private Word currentWord;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        dm = new DataModel();
        sm = new StateMachine();
		keyWord = (TextView)findViewById(R.id.display_text);
		shortDesc = (TextView)findViewById(R.id.textView_shortDesc);
		button_yes = (Button)findViewById(R.id.button_yes);
		button_no = (Button)findViewById(R.id.button_no);
		currentWord = dm.getNextWord();
		keyWord.setText(currentWord.getKeyWord());
		shortDesc.setText(currentWord.getShortDescription());
        
//		for(int i = 0; i < dm.data.length; i++){
//			for(Word word : dm.data[i]){
//				Log.d("Msg", word.getKeyWord()
//						+ "  " + word.getShortDescription()
//						+ "  " + word.getRank());
//			}
//		}
//
//        Log.d("Eido", Environment.getExternalStorageDirectory().getAbsolutePath());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public boolean onTouchEvent(MotionEvent e){
		if(e.getAction() == MotionEvent.ACTION_UP){
			if(sm.currentState() == StateMachine.STATE_ONLY_WORD){
				sm.changeState(StateMachine.STATE_CONFIRMATION);
				changeViewTo(sm.currentState());
			}
		}
    	return true;
    }
    
    private void changeViewTo(int state){
    	switch(state){
    	case StateMachine.STATE_ONLY_WORD:
    		shortDesc.setVisibility(View.GONE);
    		button_yes.setVisibility(View.GONE);
    		button_no.setVisibility(View.GONE);
    		break;
    	case StateMachine.STATE_CONFIRMATION:
    		shortDesc.setVisibility(View.VISIBLE);
    		button_yes.setVisibility(View.VISIBLE);
    		button_no.setVisibility(View.VISIBLE);
    	}
    }
    
    private void updateWord(Word newWord){
    	this.currentWord = newWord;
    	this.keyWord.setText(currentWord.getKeyWord());
    	this.shortDesc.setText(currentWord.getShortDescription());
    }
    
    public void button_onClick(View target){
    	switch(target.getId()){
    	case R.id.button_yes:
    		dm.decWordRank(currentWord);
    		updateWord(dm.getNextWord());
    		sm.changeState(StateMachine.STATE_ONLY_WORD);
    		changeViewTo(sm.currentState());
    		break;
    	case R.id.button_no:
    		dm.incWordRank(currentWord);
    		updateWord(dm.getNextWord());
    		sm.changeState(StateMachine.STATE_ONLY_WORD);
    		changeViewTo(sm.currentState());
    		break;
    	}
    }
    
    class StateMachine{
    	public static final int STATE_ONLY_WORD = 0;
    	public static final int STATE_CONFIRMATION = 1;
    	private int currentState;
    	public StateMachine(){
    		currentState = StateMachine.STATE_ONLY_WORD;
    	}
    	public void changeState(int state){
    		this.currentState = state;
    	}
    	public int currentState(){
    		return this.currentState;
    	}
    }
    
}
