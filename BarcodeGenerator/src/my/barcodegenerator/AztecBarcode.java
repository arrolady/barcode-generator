package my.barcodegenerator;

import android.graphics.Bitmap;

public class AztecBarcode implements Barcode{
	private boolean[][] code; // for two-dimensional barcodes
	
	private static final int SQUARE_SIZE=4; //pixels
	
	public Bitmap draw(){
		return Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
	}
}
