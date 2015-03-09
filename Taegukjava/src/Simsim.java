import java.util.Scanner;


public class Simsim 
{
	public static void main(String[] args)
	{
		
	}
	
	private void UpDownGame()
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
