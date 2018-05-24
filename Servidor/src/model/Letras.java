package model;

import java.util.Random;

public class Letras {
	private static String[] dice0 = {"R","I","F","O","B","X"};
	private static String[] dice1 = {"I","F","E","H","E","Y"};
	private static String[] dice2 = {"D","E","N","O","W","S"};
	private static String[] dice3 = {"U","T","O","K","N","D"};
	private static String[] dice4 = {"H","M","S","R","A","O"};
	private static String[] dice5 = {"L","U","P","E","T","S"};
	private static String[] dice6 = {"A","C","I","T","O","A"};
	private static String[] dice7 = {"Y","L","G","K","U","E"};
	private static String[] dice8 = {"Qu","B","M","J","O","A"};
	private static String[] dice9 = {"E","H","I","S","P","N"};
	private static String[] dice10 = {"V","E","T","I","G","N"};
	private static String[] dice11 = {"B","A","L","I","Y","T"};
	private static String[] dice12 = {"E","Z","A","V","N","D"};
	private static String[] dice13 = {"R","A","L","E","S","C"};
	private static String[] dice14 = {"U","W","I","L","R","G"};
	private static String[] dice15 = {"P","A","C","E","M","D"};
	
	private static String[][] dicess= {dice0, dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8, dice9, dice10, dice11, dice12, dice13, dice14, dice15};
	
	
	public static String[] randomize() {
		Random gerar = new Random();
		String[] ditto = new String[16];
		for (int i = 0; i < 16; i++) {
			ditto[i] = dicess[i][gerar.nextInt(6)];	
		}
		return ditto;
	}
	
    public static String getLetras() {
    	String[] temp = randomize();
    	String go = "";
    	for(int i=0;i<temp.length;i++){
    		if(i == temp.length-1){
    			go += temp[i];
    		}else{
    			go += temp[i] + ",";
    		}
    	}
        return go;
    }

}
