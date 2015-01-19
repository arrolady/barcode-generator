package my.barcodegenerator;

class Util {
	public static boolean[] reverseArray(boolean[] array){
		if(array==null)
			return null;
		int n=array.length;
		boolean[] revArray=new boolean[n];
		for(int i=0;i<n;i++){
			revArray[i]=array[n-1-i];
		}
		return revArray;		
	}
	
	public static boolean[] negateArray(boolean[] array){
		if(array==null)
			return null;
		int n=array.length;
		boolean[] revArray=new boolean[n];
		for(int i=0;i<n;i++){
			revArray[i]=!array[i];
		}
		return revArray;		
	}
	
	public static boolean[] concatenateArrays(boolean[] a1,boolean[] a2){
		int l1=a1.length;
		int l2=a2.length;
		boolean[] a=new boolean[l1+l2];
		for(int i=0;i<l1;i++)
			a[i]=a1[i];
		for(int i=0;i<l2;i++)
			a[i+l1]=a2[i];
		return a;
	}
	
	public static int[] concatenateArrays(int[] a1,int[] a2){
		int l1=a1.length;
		int l2=a2.length;
		int[] a=new int[l1+l2];
		for(int i=0;i<l1;i++)
			a[i]=a1[i];
		for(int i=0;i<l2;i++)
			a[i+l1]=a2[i];
		return a;
	}
}
