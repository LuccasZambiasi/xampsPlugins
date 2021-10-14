package br.poa.zambiasi.xampschat.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampschat.Main;

public class Comandos implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (sender instanceof Player) {
	      Player p = (Player)sender;
	      if (cmd.getName().equalsIgnoreCase("xampschat")) {
	        if (args.length == 0) {
	            p.sendMessage("§aPlugin desenvolvido por xampS#0443.");
	        } 
	        if (args.length == 1) { 
	          if (args[0].equals("reload")) {
	            if (sender.hasPermission("xampschat.admin")) {
	              Main.plugin.reloadConfig();
	              sender.sendMessage("§a Plugin recarregado com sucesso!");
	              return true;
	            } else {
		            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("mensagens.sem_perm")));
		            return false;	
	            }
	          } 
	          return false;
	        } 
	      } 
	    } else {
	    	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("mensagens.somente_players")));
	    } 
	    return false;
	  }

}
