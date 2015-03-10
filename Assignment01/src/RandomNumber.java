
public class RandomNumber {
	public static void main(String[] args)
	{
		// array for storing selected numbers
		int store[] = new int[7];
		
		// select seven numbers
		for(int i=0; i<7; i++)
		{
			// select a number in [1,45]
			int n = (int)( 45*Math.random() ) + 1;
			// variable for checking duplication
			boolean isJungbok = false;
			// check duplication
			for(int j=0; j<i; j++) {
				if(store[j] == n) {
					isJungbok = true;
					break;
				}
			}
			// if duplicated, re-select
			if(isJungbok) {
				i--;
				continue;
			}
			store[i] = n;
			// print number
			System.out.println("random number #" + (i+1) + " : " + n);
		}
	}
}
