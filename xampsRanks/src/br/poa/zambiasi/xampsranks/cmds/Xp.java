package br.poa.zambiasi.xampsranks.cmds;

import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampsranks.Main;

public class Xp implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (sender instanceof ConsoleCommandSender) {
			Bukkit.getConsoleSender().sendMessage("§9* §7Comando apenas para jogadores.");
		}
		if (!p.hasPermission("xampsranks.cmd.rank")) {
			p.sendMessage(Main.pl.getConfig().getString("mensagens.SemPerm").replace("&", "§"));
			return true;
		}
            if (sender.hasPermission("xampsranks.cmd.rank")) {
			        	try {
			        			ResultSet rs;
			    //    			rs = Main.getMysql().conectar().createStatement()
			    //    			.executeQuery("SELECT * FROM `ranks` WHERE `nick`='NICK';");
			        			rs = Main.getMysql().conectar().createStatement()
					        	.executeQuery("SELECT * FROM `ranks`;");
			        			if (rs.next()) {
			        				p.sendMessage("§9* §7Em desenvolvimento.");
			    //    				p.sendMessage("§9* §7Você setou o xp de §9NICK §7 em §9XP §7de xp!");
			    //    				Main.getMysql().conectar().createStatement()
				//					.executeUpdate("UPDATE `ranks` SET `xp`='XP' WHERE `nick`='NICK';");					        				
				        			rs.getStatement().getConnection().close();
			        			}
			        		} catch (Exception e) {
			        			e.printStackTrace();
								p.sendMessage(Main.pl.getConfig().getString("mensagens.falha_MySQL").replace("&", "§"));
							}			
            } else {
	            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.pl.getConfig().getString("mensagens.SemPerm")));
	            return false;	
            
            }
		return false;
	}

}
