package my.barcodegenerator.test;

import org.junit.Test;
import my.barcodegenerator.Util;

public class UtilTest {

	@Test
	public void reverseArrayTest(){
		boolean[] a={true,false,false};
		boolean[] b={false,false,true};
		Assert.assertArrayEquals(Util.reverseArray(a),b);
	}
	
	@Test
	public void reverseArrayNullTest(){
		Assert.assertNull(Util.reverseArray(null));
	}
	
	@Test
	public void negateArrayTest(){
		boolean[] a={true,false,false};
		boolean[] b={false,true,true};
		Assert.assertArrayEquals(Util.negateArray(a),b);
	}
	
	@Test
	public void negateArrayNullTest(){
		Assert.assertNull(Util.negateArray(null));
	}
	
	@Test
	public void concatenateArraysBoolTest(){
		boolean[] a={true,false};
		boolean[] b={false,true};
		boolean[] c={true,false,false,true};
		Assert.assertArrayEquals(Util.concatenateArrays(a,b),c);
	}
	
	@Test
	public void concatenateArraysBoolNull1Test(){
		boolean[] a={true,false};
		boolean[] b=null;
		boolean[] c={true,false};
		Assert.assertArrayEquals(Util.concatenateArrays(a,b),c);
	}
	
	@Test
	public void concatenateArraysBoolNull2Test(){
		boolean[] a=null;
		boolean[] b={false,true};
		boolean[] c={false,true};
		Assert.assertArrayEquals(Util.concatenateArrays(a,b),c);
	}
	
	@Test
	public void concatenateArraysBoolNull3Test(){
		boolean[] a=null,b=null,c=null;	
		Assert.assertArrayEquals(Util.concatenateArrays(a,b),c);
	}
	
	@Test
	public void concatenateArraysIntTest(){
		int[] a={1,2};
		int[] b={0,-5};
		int[] c={1,2,0,-5};
		Assert.assertArrayEquals(Util.concatenateArrays(a,b),c);
	}
	
	@Test
	public void concatenateArraysIntNull1Test(){
		int[] a={1,2};
		int[] b=null;
		int[] c={1,2};
		Assert.assertArrayEquals(Util.concatenateArrays(a,b),c);
	}
	
	@Test
	public void concatenateArraysIntNull2Test(){
		int[] a=null;
		int[] b={1,2};
		int[] c={1,2};
		Assert.assertArrayEquals(Util.concatenateArrays(a,b),c);
	}
	
	@Test
	public void concatenateArraysIntNull3Test(){
		int[] a=null,b=null,c=null;	
		Assert.assertArrayEquals(Util.concatenateArrays(a,b),c);
	}
	
	@Test
	public void copyOfBoolTest(){
		boolean[] b={true,false};
		Assert.assertArrayEquals(Util.copyOf(b), b);
		Assert.assertNotSame(Util.copyOf(b), b);
	}
	
	@Test
	public void copyOfIntTest(){
		int[] b={1,0};
		Assert.assertArrayEquals(Util.copyOf(b), b);
		Assert.assertNotSame(Util.copyOf(b), b);
	}
}
