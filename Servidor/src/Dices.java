import java.util.Random;

public class Dices {
	private String[] dice0 = {"R","I","F","O","B","X"};
	private String[] dice1 = {"I","F","E","H","E","Y"};
	private String[] dice2 = {"D","E","N","O","W","S"};
	private String[] dice3 = {"U","T","O","K","N","D"};
	private String[] dice4 = {"H","M","S","R","A","O"};
	private String[] dice5 = {"L","U","P","E","T","S"};
	private String[] dice6 = {"A","C","I","T","O","A"};
	private String[] dice7 = {"Y","L","G","K","U","E"};
	private String[] dice8 = {"Qu","B","M","J","O","A"};
	private String[] dice9 = {"E","H","I","S","P","N"};
	private String[] dice10 = {"V","E","T","I","G","N"};
	private String[] dice11 = {"B","A","L","I","Y","T"};
	private String[] dice12 = {"E","Z","A","V","N","D"};
	private String[] dice13 = {"R","A","L","E","S","C"};
	private String[] dice14 = {"U","W","I","L","R","G"};
	private String[] dice15 = {"P","A","C","E","M","D"};
	
	private String[][] dicess= {dice0, dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8, dice9, dice10, dice11, dice12, dice13, dice14, dice15};
	
	
	public String[] randomize() {
		Random gerar = new Random();
		String[] ditto = new String[16];
		for (int i = 0; i < 16; i++) {
			ditto[i] = dicess[i][gerar.nextInt(6)];	
		}
		return ditto;
	}
}
