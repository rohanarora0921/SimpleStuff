import java.util.HashSet;

public class Permutation {
	
	static HashSet <String> hash = new HashSet<>();
	
	public Permutation() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[])
	{
		String s = "FOO";
		String t = "BAR";
		int num = 256;
		int x;
		CountOneBits(num);
		
		int n=s.length();
		System.out.println(s.substring(3, 3)+"Substring here");
		
		
		permute(s);
		for(String s1:hash){
			System.out.println(s1);
		}
	}

	private static void CountOneBits(int num) {
		// TODO Auto-generated method stub
		int count=0;
		for(;num!=0;++count)
		{
			num&=(num-1);
			System.out.println("Iteration "+count);
		}
		
		System.out.println("Number of bits set" +count);
	}

	private static void permute(String s2) {
		// TODO Auto-generated method stub
		permuteUnique("",s2);
	}

	private static void permuteUnique(String prefix, String s2) {
		// TODO Auto-generated method stub
		int n = s2.length();
		
		if(n==1) {hash.add(prefix+s2);}
		else
		{
			for(int i=0;i<n;i++)
			{
				permuteUnique(prefix+s2.charAt(i),s2.substring(0, i)+s2.substring(i+1, n));
			}
		}
	}
	
}


