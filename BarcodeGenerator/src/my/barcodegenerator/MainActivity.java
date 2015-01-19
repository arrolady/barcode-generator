package my.barcodegenerator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private int posDropdown=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);
		setDropdownListener();
		setButtonListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void setButtonListener(){
		final Button button=(Button) findViewById(R.id.generate);
		button.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				final EditText editText = (EditText) findViewById(R.id.data);
				final String[] barcodes=getResources().getStringArray(R.array.barcodes);
				
				StringBuilder alert=new StringBuilder();
				Barcode b=BarcodeFactory.createBarcode(MainActivity.this,barcodes[posDropdown], editText.getText().toString(),alert);
				
				final ImageView image=(ImageView) findViewById(R.id.image);
				if(b==null)
					image.setImageBitmap(Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)); // draw nothing
				else
					image.setImageBitmap(b.draw());
				
				final TextView textView=(TextView) findViewById(R.id.alerts);
				textView.setText(alert);
			}
		});
	}
	
	private void setDropdownListener(){
		final Spinner spinner=(Spinner) findViewById(R.id.dropdown);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> parent,View view, int pos, long id){
				posDropdown=pos;
			}
			
			public void onNothingSelected(AdapterView<?> parent){
				
			}
		});
	}
	

}
