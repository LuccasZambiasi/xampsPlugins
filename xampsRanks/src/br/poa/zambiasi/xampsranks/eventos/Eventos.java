package br.poa.zambiasi.xampsranks.eventos;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import br.poa.zambiasi.xampsranks.Main;
import nuvemplugins.solaryeconomy.app.SolaryEconomy;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Eventos implements Listener {

	@EventHandler
	private void onChat(ChatMessageEvent e) {
	    if(e.getTags().contains("xampsranks")) {
			try {
				ResultSet rs;
				rs = Main.getMysql().conectar().createStatement()
						.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + e.getSender().getName() + "';");
				if (rs.next()) {
					e.setTagValue("xampsranks", rankNick(rs.getInt("kill")));
				}
				rs.getStatement().getConnection().close();
			 } catch (SQLException e1) {
				 Bukkit.getConsoleSender().sendMessage("§eERRO!");
				 e1.printStackTrace();
			 }
	  	    }
	    
	}
	
	@EventHandler
	void onPlayerCLickInventry(InventoryClickEvent e) {
		if ((e.getInventory().getTitle().equalsIgnoreCase(Main.pl.getConfig().getString("config.gui_name").replace("&", "§"))  && (e.getCurrentItem() != null))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void morrer(PlayerDeathEvent e) {
		Player matou = e.getEntity().getKiller();
		Player morreu = e.getEntity().getPlayer();
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getEntity().getKiller() instanceof Player))
			return;
		new BukkitRunnable() {

			@Override
			public void run() {
				try {
					ResultSet rs2 = Main.getMysql().conectar().createStatement()
							.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + matou.getName() + "';");
					if (rs2.next()) {
						
						Main.getMysql().conectar().createStatement()
						.executeUpdate("UPDATE `ranks` SET `kill`='" + String.valueOf((rs2.getInt("kill") + 1))
						+ "' WHERE `nick`='" + matou.getName() + "';");
					}
					rs2.getStatement().getConnection().close();
					
//					ResultSet rs = Main.getMysql().conectar().createStatement()
//				.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + matou.getName() + "';");
//					if (rs.next()) {						
//						if (Main.pl.getConfig().getBoolean("config.money_per_rank") == true) {
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank1.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank1.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank2.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank2.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank2.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank3.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank3.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank3.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank4.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));	
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank4.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank4.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank5.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank5.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank5.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank6.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank6.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank6.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank7.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank7.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank7.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank8.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank8.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank8.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank9.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank9.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank9.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank10.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank10.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank10.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank11.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank11.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank11.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank12.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank12.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank12.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank13.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank13.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank13.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank14.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank14.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank14.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank15.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank15.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank15.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank16.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank16.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank16.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank17.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank17.kills")) {
//							SolaryEconomy.economia.addBalance(matou.getName(), 
//							new BigDecimal(Main.pl.getConfig().getInt("rank.rank17.money")));
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouUltimoRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank17.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//					} else {
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank1.kills")) {							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank2.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank2.kills")) {							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank3.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank3.kills")) {							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank4.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));	
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank4.kills")) {							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank5.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank5.kills")) {							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank6.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank6.kills")) {							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank7.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank7.kills")) {
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank8.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank8.kills")) {
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank9.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank9.kills")) {
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank10.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank10.kills")) {
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank11.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank11.kills")) {
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank12.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank12.kills")) {
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank13.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank13.kills")) {
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank14.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank14.kills")) {
//							
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank115.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank15.kills")) {
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank16.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank16.kills")) {
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank17.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//						}
//						
//						if(rs.getInt("kill") == Main.pl.getConfig().getInt("rank.rank17.kills")) {
//							matou.sendMessage(Main.pl.getConfig().getString("mensagens.upouUltimoRank")
//							.replace("%rank%", Main.pl.getConfig().getString("rank.rank17.nome"))
//							.replace("%nick%", matou.getName()).replace("&", "§"));
//							for (String s : Main.pl.getConfig().getStringList("rank.rank17.permissoes")) {
//								PermissionsEx.getPermissionManager().getUser(matou).addPermission(s);								
//							}
//						}
//					}
//					} 
//					rs.getStatement().getConnection().close();
					
					ResultSet rs1 = Main.getMysql().conectar().createStatement()
							.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + morreu.getName() + "';");
					if (rs1.next()) {
						
						Main.getMysql().conectar().createStatement()
						.executeUpdate("UPDATE `ranks` SET `death`='" + String.valueOf((rs1.getInt("death") + 1))
						+ "' WHERE `nick`='" + morreu.getName() + "';");
					}
					rs1.getStatement().getConnection().close();

				} catch (SQLException e1) {
				}

			}
		}.runTaskAsynchronously(Main.pl);
	}
	
	@EventHandler
	void morrerRespawn(PlayerDeathEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getEntity().getPlayer() instanceof Player) {
					e.getEntity().getPlayer().spigot().respawn();
				}
			}
		}.runTask(Main.pl);
	}
	
	public static String rankNick(int kills) {
		if (kills <= Main.pl.getConfig().getInt("rank.rank1.kills")) {
			return Main.pl.getConfig().getString("rank.rank1.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank2.kills")) {
			return Main.pl.getConfig().getString("rank.rank2.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank3.kills")) {
			return Main.pl.getConfig().getString("rank.rank3.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank4.kills")) {
			return Main.pl.getConfig().getString("rank.rank4.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank5.kills")) {
			return Main.pl.getConfig().getString("rank.rank5.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank6.kills")) {
			return Main.pl.getConfig().getString("rank.rank6.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank7.kills")) {
			return Main.pl.getConfig().getString("rank.rank7.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank8.kills")) {
			return Main.pl.getConfig().getString("rank.rank8.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank9.kills")) {
			return Main.pl.getConfig().getString("rank.rank9.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank10.kills")) {
			return Main.pl.getConfig().getString("rank.rank10.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank11.kills")) {
			return Main.pl.getConfig().getString("rank.rank11.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank12.kills")) {
			return Main.pl.getConfig().getString("rank.rank12.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank13.kills")) {
			return Main.pl.getConfig().getString("rank.rank13.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank14.kills")) {
			return Main.pl.getConfig().getString("rank.rank14.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank15.kills")) {
			return Main.pl.getConfig().getString("rank.rank15.simbolo").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank16.kills")) {
			return Main.pl.getConfig().getString("rank.rank16.simbolo").replace("&", "§");
		
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank17.kills")) {
			return Main.pl.getConfig().getString("rank.rank17.simbolo").replace("&", "§");
		
		} else {
			return Main.pl.getConfig().getString("rank.rank17.simbolo").replace("&", "§");
		}
	}
	
	public static String rank(int kills) {
		if (kills <= Main.pl.getConfig().getInt("rank.rank1.kills")) {
			return Main.pl.getConfig().getString("rank.rank1.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank2.kills")) {
			return Main.pl.getConfig().getString("rank.rank2.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank3.kills")) {
			return Main.pl.getConfig().getString("rank.rank3.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank4.kills")) {
			return Main.pl.getConfig().getString("rank.rank4.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank5.kills")) {
			return Main.pl.getConfig().getString("rank.rank5.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank6.kills")) {
			return Main.pl.getConfig().getString("rank.rank6.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank7.kills")) {
			return Main.pl.getConfig().getString("rank.rank7.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank8.kills")) {
			return Main.pl.getConfig().getString("rank.rank8.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank9.kills")) {
			return Main.pl.getConfig().getString("rank.rank9.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank10.kills")) {
			return Main.pl.getConfig().getString("rank.rank10.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank11.kills")) {
			return Main.pl.getConfig().getString("rank.rank11.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank12.kills")) {
			return Main.pl.getConfig().getString("rank.rank12.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank13.kills")) {
			return Main.pl.getConfig().getString("rank.rank13.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank14.kills")) {
			return Main.pl.getConfig().getString("rank.rank14.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank15.kills")) {
			return Main.pl.getConfig().getString("rank.rank15.nome").replace("&", "§");
			
		} else if (kills <= Main.pl.getConfig().getInt("rank.rank16.kills")) {
			return Main.pl.getConfig().getString("rank.rank16.nome").replace("&", "§");
		
		}else if (kills <= Main.pl.getConfig().getInt("rank.rank17.kills")) {
			return Main.pl.getConfig().getString("rank.rank17.nome").replace("&", "§");
		
		} else {
			return Main.pl.getConfig().getString("rank.rank17.nome").replace("&", "§");
		}
	}

}
