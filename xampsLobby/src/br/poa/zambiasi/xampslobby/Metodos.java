package br.poa.zambiasi.xampslobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.howaner.BungeeCordLib.BungeeCord;
import de.howaner.BungeeCordLib.server.BungeeServer;
import de.howaner.BungeeCordLib.server.ServerData;

public class Metodos {
	
	public static void VerificarServidor(String nome, String ip) {
		BungeeServer server = BungeeCord.getManager().addServer(nome, ip);
		ServerData data = server.getData();
		int slots = data.getSlots();
	}
	
	  public static void Aparecer(Player p){
		 Player[] arrayOfPlayer;
		 int j;
		 int i;
		  j = Bukkit.getOnlinePlayers().size();
		   for (i = 0; i < j; i++){
		     for (Player pl : Bukkit.getOnlinePlayers()){
		     p.showPlayer(pl);
		    }
		  }
	  }
	  
	  public static void Esconder(Player p){
		 Player[] arrayOfPlayer;
		 int j;
		 int i;
		  j = Bukkit.getOnlinePlayers().size();
	       for (i = 0; i < j; i++){
	         for (Player pl : Bukkit.getOnlinePlayers()){
	         p.hidePlayer(pl);
	        }
	       }
	  }

}
