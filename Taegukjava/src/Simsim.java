import java.util.Scanner;


class If2 {
	public static void main()   
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
			System.out.println("random number : " + n);
		}       
	}     
} 

public class Simsim 
{
	public static void main(String[] args)
	{
		If2.main();
		//upDownGame();
	}
	
	private static void mookJjiBba()
	{
		final int MOOK = 0;
		final int JJI = 1;
		final int BBA = 2;
		final int[] rel = { 0b110, 0b011, 0b101 };
		final String[] str = { "MOOK","JJI","BBA" };
		
		Scanner scin = new Scanner(System.in);
		
		while(true)
		{
			int comHand = (int)(3*Math.random());
			System.out.print("MOOK("+MOOK+"), JJI("+JJI+"), BBA("+BBA+") >> ");
			int userHand = scin.nextInt();
			
			System.out.println(" Computer's hand : " + str[comHand]);
			System.out.println("    Your hand    : " + str[userHand]);
			
		}
	}
	
	private static void upDownGame()
	{
		final int MINVAL = 1;
		final int MAXVAL = 50;
		
		Scanner scin = new Scanner(System.in);	
		int randomNum = (int)((MAXVAL+1)*Math.random()) + MINVAL;
		
		int tryCnt = 0;
		boolean isCorrect = false;
		while(tryCnt++ < 5)
		{
			System.out.print("Input Number(#"+tryCnt+") >> ");
			int num = scin.nextInt();
			
			if(num < randomNum)
				System.out.println("UP!");
			else if(num > randomNum )
				System.out.println("DOWN!");
			else {
				isCorrect = true;
				break;
			}
		}
		
		if(isCorrect) {
			System.out.println("žA ¿· ·¯ºê¼¦!!");
		} else {
			System.out.println("µ¿±¸¹ç °ú¼ö ¿ø¼¦! ºÒ½º ¿ø¼¦! (#"+randomNum+")");
		}
		
		scin.close();
	}
}
