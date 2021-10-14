package br.poa.zambiasi.xampslobby.utils;

import br.poa.zambiasi.xampslobby.Main;

public class Mensagens {

	public static String cor(String s) {
		return s.replace("&", "§");
	}

	public static String consolePlayer = cor(Main.pl.getConfig().getString("Mensagens.Console"));
	
	public static String offlinePlayer = cor(Main.pl.getConfig().getString("Mensagens.Jogador_OFF"));
	
	public static String noPerm = cor(Main.pl.getConfig().getString("Mensagens.Sem_Permissao"));
	
}
