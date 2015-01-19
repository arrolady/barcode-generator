package my.barcodegenerator;

import android.content.Context;
import android.util.Log;

public class BarcodeFactory {
	private static final String BCFACTORY = "BarcodeFactory";
	
	
	public static Barcode createBarcode(Context c,String name, String data,StringBuilder attn){
		Log.i(BCFACTORY, name+" "+data);
		if(name.equals(c.getResources().getText(R.string.ean13))) //compare strings, not indices since ordering of dropdown items isn't fixed
			return ean13(data,attn);
		else if (name.equals(c.getResources().getText(R.string.aztec)))
			return aztec(data);
		else if (name.equals(c.getResources().getText(R.string.qr)))
			return qr(data);
		else
			return null;
	}
	
	private static EAN13Barcode ean13(String data,StringBuilder attn){
		EAN13Barcode barcode=new EAN13Barcode(data,attn);
		
		return barcode;
	}
	
	private static AztecBarcode aztec(String data){
		return new AztecBarcode();
		//fill data2		
	}
	
	private static QRCode qr(String data){
		return new QRCode();
		//fill data2		
	}
	
}

