package com.codepath.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.R.string;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculatorActivity extends Activity {

	private int currentCustomPercent; 
	private RadioGroup tipPercentRadio;
	private RadioButton selectedTipButton;
	private TextView tip;
	private TextView totalAmount;
	private TextView taxValue;
	private TextView customTip;
	private TextView tvTaxVal;
	private CheckBox  includeTax;
	private EditText totalBill;
	private SeekBar seekbarTax;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
	
		tipPercentRadio = (RadioGroup) findViewById(R.id.rgSelectTip);
		totalBill = (EditText)findViewById(R.id.etTotalBill);
		totalAmount = (TextView)findViewById(R.id.tvTotalValue);
		includeTax = (CheckBox)findViewById(R.id.checkBox1);
		tvTaxVal = (TextView)findViewById(R.id.tvTaxValue);
		taxValue = (TextView)findViewById(R.id.tvTaxTotal);
		seekbarTax = (SeekBar) findViewById(R.id.sbCustomTip);  
		customTip = (TextView) findViewById(R.id.tvCustomTipPercent);  
		tip = (TextView)findViewById(R.id.tvTipValue);	
		
		seekbarTax.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {     
			  public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {     
			        // TODO Auto-generated method stub      
				  String progressValue = Integer.toString(progress);
				  customTip.setText(progressValue + "%");
				  displayTheTipValues((double)progress/100);
			  }

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}  
		});	
		
		
		
	}
	
	// Radio Group on click
	public void onRGTipClick(View v){		
		int selected = tipPercentRadio.getCheckedRadioButtonId();

		double tipPercent = 1;
		if(selected != -1){
			switch(selected){
				case R.id.radio_10percent:
					tipPercent = 0.1;
					break;
					
				case R.id.radio_15percent:
					tipPercent = 0.15;
					break;
				
				case R.id.radio_20percent:
					tipPercent = 0.2;
					break;		
			}
		
		}
		
		displayTheTipValues(tipPercent);
	
	}
	
	private void displayTheTipValues(double tipPercent){
		
		if(totalBill.getText().toString() == null || totalBill.getText().toString().isEmpty())
			return;
		
		try{	
			double enteredBill = Double.parseDouble(totalBill.getText().toString());
			double tax = 0;
			
			if(includeTax.isChecked())
				tax = enteredBill * 0.0985;
			
			double tipValue = enteredBill * tipPercent;
			double total = enteredBill + tipValue + tax;
				
			DecimalFormat df = new DecimalFormat("0.00##");
			String tipValueDecimal = df.format(tipValue);
			String taxValueDecimal = df.format(tax);
			String totalValueDecimal = df.format(total);
			
			tip.setText(tipValueDecimal);
			taxValue.setText(taxValueDecimal);		
			totalAmount.setText(totalValueDecimal); 
		}
		catch(Exception e){
			// log exception			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_calculator, menu);
		return true;
	}

}
