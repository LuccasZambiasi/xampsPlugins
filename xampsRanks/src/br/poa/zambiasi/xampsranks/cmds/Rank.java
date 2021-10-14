package br.poa.zambiasi.xampsranks.cmds;

import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampsranks.Main;
import br.poa.zambiasi.xampsranks.eventos.Eventos;

public class Rank implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (!p.hasPermission("xampsranks.cmd.rank")) {
			p.sendMessage(Main.pl.getConfig().getString("mensagens.SemPerm").replace("&", "§"));
			return true;
		}
		if (sender instanceof ConsoleCommandSender) {
			Bukkit.getConsoleSender().sendMessage("§9* §7Comando apenas para jogadores.");
		}
		
		if (args.length == 0) {	
			try {
				ResultSet rs = Main.getMysql().conectar().createStatement()
				.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + p.getName() + "';");
				if (rs.next()) {
					
					int kill = rs.getInt("kill");
					int death = rs.getInt("death");	
					
					String corPrimaria = Main.pl.getConfig().getString("cores_base.Primaria").replace("&", "§");
					String corSecundaria = Main.pl.getConfig().getString("cores_base.Secundaria").replace("&", "§");
					String corTercearia = Main.pl.getConfig().getString("cores_base.Tercearia").replace("&", "§");
					
					if (death > 0) {
					double a = kill / death;
					
					p.sendMessage("§a");
					p.sendMessage(corTercearia + "SEU RANK: " + corSecundaria + p.getName());
					p.sendMessage("§a");
					p.sendMessage(corPrimaria + "* §7Suas kills: " + rs.getInt("kill"));
					p.sendMessage(corPrimaria + "* §7Suas mortes: " + rs.getInt("death"));
					p.sendMessage(corPrimaria + "* §7Seu KDA: " + a);
					p.sendMessage(corPrimaria + "* §7Rank atual: " + Eventos.rankNick(rs.getInt("kill")) + " " + Eventos.rank(rs.getInt("kill")));
					p.sendMessage("§a");
					} else {
						p.sendMessage("§9* §7Para carregar suas estatisticas, é necessário ter pelo menos uma morte.");
					}
//					else {
//					double a = 1;
//					p.sendMessage("§a");
//					p.sendMessage(corTercearia + "SEU RANK: §9" + corSecundaria + p.getName());
//					p.sendMessage("§a");
//					p.sendMessage(corPrimaria + "* §7Suas kills: " + rs.getInt("kill"));
//					p.sendMessage(corPrimaria + "* §7Suas mortes: " + rs.getInt("death"));
//					p.sendMessage(corPrimaria + "* §7Seu KDA: " + a);
//					p.sendMessage(corPrimaria + "* §7Rank atual: " + Eventos.rankNick(rs.getInt("kill")) + " " + Eventos.rank(rs.getInt("kill")));
//					p.sendMessage("§a");
//					}
					rs.getStatement().getConnection().close();
				}
				} catch (Exception e) {
					p.sendMessage(Main.pl.getConfig().getString("mensagens.falha_MySQL").replace("&", "§"));
					e.printStackTrace();
				}	
		} else {
		if (args[0].equalsIgnoreCase("top")) {
//			p.sendMessage("§9* §7Comando desativado temporariamente para manutenção.");
			p.sendMessage("§a ");
			p.sendMessage(Main.pl.getConfig().getString("mensagens.top_cabecalho").replace("&", "§"));
			p.sendMessage("§a ");
			try {
				ResultSet rs = Main.getMysql().conectar().createStatement()
				.executeQuery("SELECT * FROM `ranks` WHERE xp < 5 ORDER BY `kill` DESC LIMIT 10");
				while (rs.next()) {
					
					int pos = rs.getRow();
					String nicks = rs.getString(1);
					String kills = rs.getString(2);
					String mortes = rs.getString(3);

					String ranknick = Eventos.rankNick(rs.getInt("kill"));
					String rank = Eventos.rank(rs.getInt("kill"));
				    p.sendMessage(Main.pl.getConfig().getString("mensagens.top").replace("%pos%", String.valueOf(pos)).replace("&", "§").replace("%rank%", rank).replace("%prefixo%", ranknick).replace("%mortes%", mortes).replace("%kills%", kills).replace("%top10%", nicks).replace("%jogador%", p.getName()));						
			}
			} catch (Exception e) {
				p.sendMessage(Main.pl.getConfig().getString("mensagens.falha_MySQL").replace("&", "§"));
				e.printStackTrace();
			}
			p.sendMessage("§a ");
		} else {
//			p.sendMessage("§9* §7Comando desativado temporariamente para manutenção.");
			Player jogador = Bukkit.getPlayer(args[0]);
			try {
				ResultSet rs = Main.getMysql().conectar().createStatement()
				.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + jogador.getName() + "';");
				if (rs.next()) {
					
					String corPrimaria = Main.pl.getConfig().getString("cores_base.Primaria").replace("&", "§");
					String corSecundaria = Main.pl.getConfig().getString("cores_base.Secundaria").replace("&", "§");
					String corTercearia = Main.pl.getConfig().getString("cores_base.Tercearia").replace("&", "§");
					
					int kill = rs.getInt("kill");
					int death = rs.getInt("death");	
					
					if (death > 0) {
					double a = kill / death;
					p.sendMessage("§a");
					p.sendMessage(corTercearia + "RANK DE: " + corSecundaria + jogador.getName());
					p.sendMessage("§a");
					p.sendMessage(corPrimaria + "* §7Kills: " + rs.getInt("kill"));
					p.sendMessage(corPrimaria + "* §7Mortes: " + rs.getInt("death"));
					p.sendMessage(corPrimaria + "* §7KDA: " + a);
					p.sendMessage(corPrimaria + "* §7Rank: " + Eventos.rankNick(rs.getInt("kill")) + " " + Eventos.rank(rs.getInt("kill")));
					p.sendMessage("§a");
					} else {
					double a = 1;
					p.sendMessage("§a");
					p.sendMessage(corTercearia + "RANK DE: " + corSecundaria + jogador.getName());
					p.sendMessage("§a");
					p.sendMessage(corPrimaria + "* §7Kills: " + rs.getInt("kill"));
					p.sendMessage(corPrimaria + "* §7Mortes: " + rs.getInt("death"));
					p.sendMessage(corPrimaria + "* §7KDA: " + a);
					p.sendMessage(corPrimaria + "* §7Rank: " + Eventos.rankNick(rs.getInt("kill")) + " " + Eventos.rank(rs.getInt("kill")));
					p.sendMessage("§a");
					}
					rs.getStatement().getConnection().close();
				}
				} catch (Exception e) {
					p.sendMessage(Main.pl.getConfig().getString("mensagens.jogadorInexistente").replace("&", "§").replace("%jogador%", args[0]));
				}		
		}
	}
		return false;
	}

}
