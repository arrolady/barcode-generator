package my.barcodegenerator;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;


class EAN13Barcode implements Barcode{
	private boolean[] code; // for one-dimensional barcodes
	
	private static final int LINE_WIDTH=2; //pixels
	private static final int EXTRA_HEIGHT=4; //pixels
	private static final int EAN13_BIT_LENGTH=95; // lines 	
	private static final int DATA_LENGTH=13; // digits
	private static final int EAN13_LENGTH=12; // digits
	private static final int BITS=7; // number of bits in each digit's code
	private static final String EAN13="EAN13";
	
	private static final boolean W=false;
	private static final boolean B=true;
	
	@SuppressWarnings("unused")
	private EAN13Barcode(){
		
	}
	
	public EAN13Barcode(String data,StringBuilder attn){	
		int[] digits=parseData(data);
		if(digits==null){
			code=null;
			return;
		}			
		
		int control=controlDigit(digits);
		if(control!=digits[DATA_LENGTH-1])
			attn.append("The last control digit is incorrect! Should be "+control);
		
		code=guardSide();
		for(int i=0;i<EAN13_LENGTH;i++){			
			code=Util.concatenateArrays(code, codeDigit(digits[i+1],digits[0],i));
			if(i+1==EAN13_LENGTH/2){ // if in the middle, add guard lines
				code=Util.concatenateArrays(code, guardMiddle());
			}
		}
		code=Util.concatenateArrays(code, guardSide());
		
		Log.i(EAN13,"The length of the code "+code.length);
	}
	
	public int controlDigit(int[] digits){
		int even=0,odd=0;
		for(int i=0;i<EAN13_LENGTH/2;i++){
			even+=digits[2*i];
			odd+=digits[2*i+1];
		}
		return (10-(odd*3+even)%10)%10;
			
	}
	
	private int[] parseData(String data){
		if(data.length()>DATA_LENGTH){
			Log.e(EAN13,"The number is longer than 13 digits.");			
			return null;
		}
		
		int[] digits=new int[DATA_LENGTH]; // number divided into digits
		
		//fill the array with digits starting from the end; if shorter than 13, the first digits are zeros
		for(int i=data.length()-1, k=DATA_LENGTH-1;i>=0&&k>=0;i--,k--){			
			if(!Character.isDigit(data.charAt(i))){
				Log.e(EAN13,"The data must be a number.");				
				return null;
			}			
			digits[k]=Character.getNumericValue(data.charAt(i));						
		}		
		Log.i(EAN13,"Parsed the number successfully");
		return digits;
	}
	
	public Bitmap draw(){		
		if(code==null)
			return Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		
		int width=code.length*LINE_WIDTH;
		int height=width/2;
		
		int[] colors=drawRow(code);
		for(int i=1;i<height;i++){
			colors=Util.concatenateArrays(colors, drawRow(code));
		}			
		
		for(int i=0;i<EXTRA_HEIGHT;i++)
			colors=Util.concatenateArrays(colors, drawExtraRow(code));		
		
		return Bitmap.createBitmap(colors,width, height+EXTRA_HEIGHT, Bitmap.Config.RGB_565);
	}
	
	private int[] drawExtraRow(boolean[] code){
		int length=code.length;
		int[] colors=new int[length*LINE_WIDTH];
		
		for(int i=0;i<length;i++){
			if(i<3
				||i>=length-3
				||i<=(length+1)/2+1&&i>=(length+1)/2-3){				
				colors[2*i]=drawBit(code[i]);
				colors[2*i+1]=drawBit(code[i]);
			}
			else {
				colors[2*i]=drawBit(W);
				colors[2*i+1]=drawBit(W);
			}
		}
		return colors;
	}
	private int[] drawRow(boolean[] code){
		int[] colors=new int[code.length*LINE_WIDTH];
		for(int i=0;i<code.length;i++){
			colors[2*i]=drawBit(code[i]);
			colors[2*i+1]=drawBit(code[i]);
		}
		return colors;
	}
	
	private int drawBit(boolean bit){
		if(bit==B)
			return Color.BLACK;					
		else
			return Color.WHITE;
	}
	
	private boolean[] guardSide(){
		boolean[] code=new boolean[3];
		code[0]=B;
		code[1]=W;
		code[2]=B;
		return code;
	}
	private boolean[] guardMiddle(){
		boolean[] code=new boolean[5];
		code[0]=W;
		code[1]=B;
		code[2]=W;
		code[3]=B;
		code[4]=W;
		return code;
	}
	
	private boolean[] codeDigit(int n,int first,int position){
		switch (first){
		case 0:
			if(position<EAN13_LENGTH/2)
				return codeL(n);
			else
				return codeR(n);
		case 1:
			if(position==0||position==1||position==3)
				return codeL(n);
			else if(position==2||position==4||position==5)
				return codeG(n);
			else 
				return codeR(n);
		case 2:
			if(position==0||position==1||position==4)
				return codeL(n);
			else if(position==2||position==3||position==5)
				return codeG(n);
			else 
				return codeR(n);
		case 3:
			if(position==0||position==1||position==5)
				return codeL(n);
			else if(position==2||position==3||position==4)
				return codeG(n);
			else 
				return codeR(n);	
		case 4:
			if(position==0||position==2||position==3)
				return codeL(n);
			else if(position==1||position==4||position==5)
				return codeG(n);
			else 
				return codeR(n);
		case 5:
			if(position==0||position==3||position==4)
				return codeL(n);
			else if(position==1||position==2||position==5)
				return codeG(n);
			else 
				return codeR(n);
		case 6:
			if(position==0||position==4||position==5)
				return codeL(n);
			else if(position==1||position==2||position==3)
				return codeG(n);
			else 
				return codeR(n);
		case 7:
			if(position==0||position==2||position==4)
				return codeL(n);
			else if(position==1||position==3||position==5)
				return codeG(n);
			else 
				return codeR(n);
		case 8:
			if(position==0||position==2||position==5)
				return codeL(n);
			else if(position==1||position==3||position==4)
				return codeG(n);
			else 
				return codeR(n);
		case 9:
			if(position==0||position==3||position==5)
				return codeL(n);
			else if(position==1||position==2||position==4)
				return codeG(n);
			else 
				return codeR(n);
		default:
			return null;
		}
	}
	
	private boolean[] codeL(int n){
		switch(n){
		case 0:
			return code0L();
		case 1:
			return code1L();
		case 2:
			return code2L();
		case 3:
			return code3L();
		case 4:
			return code4L();
		case 5:
			return code5L();
		case 6:
			return code6L();
		case 7:
			return code7L();
		case 8:
			return code8L();
		case 9:
			return code9L();
		default:
			return null;
		}
	}
	
	private boolean[] codeR(int n){
		return Util.negateArray(codeL(n));		
	}
	
	private boolean[] codeG(int n){
		return Util.reverseArray(codeR(n));
	}
	private boolean[] code0L(){
		boolean[] code=new boolean[BITS];
		code[0]=W;
		code[1]=W;
		code[2]=W;
		code[3]=B;
		code[4]=B;
		code[5]=W;
		code[6]=B;
		return code;
	}
	private boolean[] code1L(){
		boolean[] code=new boolean[BITS];
		code[0]=W;
		code[1]=W;
		code[2]=B;
		code[3]=B;
		code[4]=W;
		code[5]=W;
		code[6]=B;
		return code;
	}	
	private boolean[] code2L(){
		boolean[] code=new boolean[BITS];
		code[0]=W;
		code[1]=W;
		code[2]=B;
		code[3]=W;
		code[4]=W;
		code[5]=B;
		code[6]=B;
		return code;
	}
	private boolean[] code3L(){
		boolean[] code=new boolean[BITS];
		code[0]=W;
		code[1]=B;
		code[2]=B;
		code[3]=B;
		code[4]=B;
		code[5]=W;
		code[6]=B;
		return code;
	}
	private boolean[] code4L(){
		boolean[] code=new boolean[BITS];
		code[0]=W;
		code[1]=B;
		code[2]=W;
		code[3]=W;
		code[4]=W;
		code[5]=B;
		code[6]=B;
		return code;
	}
	private boolean[] code5L(){
		boolean[] code=new boolean[BITS];
		code[0]=W;
		code[1]=B;
		code[2]=B;
		code[3]=W;
		code[4]=W;
		code[5]=W;
		code[6]=B;
		return code;
	}
	private boolean[] code6L(){
		boolean[] code=new boolean[BITS];
		code[0]=W;
		code[1]=B;
		code[2]=W;
		code[3]=B;
		code[4]=B;
		code[5]=B;
		code[6]=B;
		return code;
	}
	private boolean[] code7L(){
		boolean[] code=new boolean[BITS];
		code[0]=W;
		code[1]=B;
		code[2]=B;
		code[3]=B;
		code[4]=W;
		code[5]=B;
		code[6]=B;
		return code;
	}
	private boolean[] code8L(){
		boolean[] code=new boolean[BITS];
		code[0]=W;
		code[1]=B;
		code[2]=B;
		code[3]=W;
		code[4]=B;
		code[5]=B;
		code[6]=B;
		return code;
	}
	private boolean[] code9L(){
		boolean[] code=new boolean[BITS];
		code[0]=W;
		code[1]=W;
		code[2]=W;
		code[3]=B;
		code[4]=W;
		code[5]=B;
		code[6]=B;
		return code;
	}
}
