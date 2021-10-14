package br.poa.zambiasi.xampschat;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.poa.zambiasi.xampschat.cmds.Comandos;
import br.poa.zambiasi.xampschat.eventos.Eventos;

public class Main extends JavaPlugin implements Listener {
	

	  private static Main m;
	  
	  public static Main getInstance() {
	    return m;
	  }
	
	public void onEnable() {
		Data();
	}
	
	public void Data() {
		saveDefaultConfig();
		m = this;
		Bukkit.getConsoleSender().sendMessage("§a§l(xampsChat) §7§oPlugin habilitado!");
		Bukkit.getConsoleSender().sendMessage("§a§l(xampsChat) §7§oFeito por LuccasZambiasi.");
		
		Eventos();
		Comandos();
		
	    plugin = this;
	    instance = this;
	}
	
	public void Eventos() {
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new Eventos(this), (Plugin)this);
		Bukkit.getServer().getPluginManager().registerEvents(this, (Plugin)this);
	}
	
	public void Comandos() {
		getCommand("xampschat").setExecutor(new Comandos());
	}
	
	public static Main instance;	
	public static Plugin plugin;
	

}
