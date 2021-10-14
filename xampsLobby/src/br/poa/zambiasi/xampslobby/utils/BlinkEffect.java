package br.poa.zambiasi.xampslobby.utils;

public class BlinkEffect {
	
	private int i = 1;
	private String texto = "§6§lSCORE ANIMADO";
	public BlinkEffect(){
		
	}
	
	public void next(){
		if (i == 1){
			texto = "§f§lS§6§lCORE ANIMADO";
		}
		if (i == 2){
			texto = "§6§lS§f§lC§6§lORE ANIMADO";
		}
		if (i == 3){
			texto = "§6§lSC§f§lO§6§lRE ANIMADO";
		}
		if (i == 4){
			texto = "§6§lSCO§f§lR§6§lE ANIMADO";
		}
		if (i == 5){
			texto = "§6§lSCOR§f§lE §6§lANIMADO";
		}
		if (i == 6){
			texto = "§6§lSCORE §f§lA§6§lNIMADO";
		}
		if (i == 7){
			texto = "§6§lSCORE A§f§lN§6§lIMADO";
		}
		if (i == 8){
			texto = "§6§lSCORE AN§f§lI§6§lMADO";
		}
		if (i == 9){
			texto = "§6§lSCORE ANI§f§lM§6§lADO";
		}
		if (i == 10){
			texto = "§6§lSCORE ANIM§f§lA§6§lDO";
		}
		if (i == 11){
			texto = "§6§lSCORE ANIMA§f§lD§6§lO";
		}
		if (i == 12){
			texto = "§6§lSCORE ANIMAD§f§lO";
		}
		if (i == 13){
			texto = "";
		}
		if (i == 14){
			texto = "§6§lSCORE ANIMADO";
		}
		if (i == 15){
			texto = "";
		}
		if (i == 16){
			texto = "§6§lSCORE ANIMADO";
		}
		if (i == 17){
			texto = "§6§lSCORE ANIMADO";
			i = 0;
		}
		i++;
		
		
	}
	public String getText(){
		return texto;
	}

}
