
public class RandomNumber {
	public static void main(String[] args)
	{
		int store[] = new int[7];
		for(int i=0; i<7; i++)
		{
			int n = (int)( 45*Math.random() ) + 1;
			boolean isJungbok = false;
			for(int j=0; j<i; j++) {
				if(store[j] == n) {
					isJungbok = true;
					break;
				}
			}
			if(isJungbok) {
				i--;
				continue;
			}
			store[i] = n;
			System.out.println("random number #" + (i+1) + " : " + n);
		}
	}
}
