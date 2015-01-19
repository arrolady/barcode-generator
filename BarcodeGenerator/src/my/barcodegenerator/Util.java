package my.barcodegenerator;

import java.util.Arrays;

public class Util {
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
	
	public static boolean[] copyOf(boolean[] a){
		if(a==null)
			return null;
		int l=a.length;
		boolean[] b=new boolean[l];
		for(int i=0;i<l;i++){
			b[i]=a[i];
		}
		return b;
	}
	
	public static int[] copyOf(int[] a){
		if(a==null)
			return null;
		int l=a.length;
		int[] b=new int[l];
		for(int i=0;i<l;i++){
			b[i]=a[i];
		}
		return b;
	}
	
	public static boolean[] concatenateArrays(boolean[] a1,boolean[] a2){
		if(a1==null)
			return copyOf(a2);
		if(a2==null)
			return copyOf(a1);
		
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
		if(a1==null)
			return copyOf(a2);
		if(a2==null)
			return copyOf(a1);
		
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
