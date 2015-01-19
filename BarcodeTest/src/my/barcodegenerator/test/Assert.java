package my.barcodegenerator.test;

public class Assert extends org.junit.Assert{
	public static void assertArrayEquals(boolean[] b1, boolean[] b2) {
		if(b1==null){
			assert (b2==null);
			return;
		}
		assert (b1.length==b2.length);
		for(int i=0;i<b1.length;i++)
			assert (b1[i]==b2[i]);
		
	}
}
