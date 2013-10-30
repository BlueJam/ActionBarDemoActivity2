package uk.co.bimrose.android.actionbardemoactivity2;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockListActivity implements TextView.OnEditorActionListener {
	
	private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
	"amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
	"ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
	"erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
	"augue", "purus" };
	private ArrayList<String> words = null;
	private ArrayAdapter<String> adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initAdapter();
		
	}
	
	public void initAdapter(){
		words = new ArrayList<String>();
		
		for(int i = 0; i < items.length; i++){
			words.add(items[i]);
		}
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
		setListAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.action, menu);
		
		configureActionItem(menu);
		
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		
		if(event == null || event.getAction() == KeyEvent.ACTION_UP){
			adapter.add(v.getText().toString());
			v.setText("");
		}
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == R.id.reset){
			initAdapter();
			return true;
		}
		return (super.onOptionsItemSelected(item));
	}
	
	private void configureActionItem(Menu menu){
		EditText add = (EditText) menu.findItem(R.id.add).getActionView().findViewById(R.id.title);
		
		add.setOnEditorActionListener(this);
	}

}
