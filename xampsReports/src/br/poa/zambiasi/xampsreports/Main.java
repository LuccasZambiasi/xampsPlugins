package br.poa.zambiasi.xampsreports;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.poa.zambiasi.xampsreports.reports.Backend;
import br.poa.zambiasi.xampsreports.reports.CommandBase;
import br.poa.zambiasi.xampsreports.reports.FileBackend;
import br.poa.zambiasi.xampsreports.reports.ModuleLogger;

public class Main extends JavaPlugin implements Listener{
	
	  private Backend backend;
	  public static final ModuleLogger LOGGER = new ModuleLogger("xampsReports");
	  public static Main instance;	
	  public static Plugin plugin;
		
	  public Main() {
		  instance = this;  
	  }
	  
	  public Backend getBackend() {
		  return this.backend;
	  }
	
	public static Main getInstance() {
		return instance;	
	}
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public void onEnable() {
		Data();
}
	
	public void onDisable() {
		quitmessage();
	}

	
	public void Data() {
		this.backend = new FileBackend();
		eventos();
		joinmessage();
		
		CommandBase.makeReports();
		
		plugin = this;
		instance = this;
		saveDefaultConfig();
		
		
	}
	
	public void eventos() {
	    Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	public void joinmessage() {
		Bukkit.getConsoleSender().sendMessage("§a§l(xampsReports) §7§oPlugin habilitado!");
		Bukkit.getConsoleSender().sendMessage("§a§l(xampsReports) §7§oFeito por LuccasZambiasi.");
	}
	
	public void quitmessage() {
		Bukkit.getConsoleSender().sendMessage("§a§l(xampsReports) §7§oPlugin desabilitado!");
		Bukkit.getConsoleSender().sendMessage("§a§l(xampsReports) §7§oFeito por LuccasZambiasi.");
	}
	  
}