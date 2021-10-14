package br.poa.zambiasi.xampsranks.cmds;

import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampsranks.Main;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class xampsranks implements CommandExecutor {
	
	FileConfiguration config = Main.pl.getConfig();
	
	public boolean isInt(String str) {
	    try {
	        Integer.parseInt(str);
	    } catch (Throwable e) {
	        return false;
	    }
	    return true;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (sender instanceof Player) {
	      Player p = (Player)sender;
	        if (args.length == 0) {
	        	if (!p.hasPermission("xampsranks.cmd.xampsranks")) {
		            p.sendMessage("§a* §7Plugin de ranks desenvolvido por xampS#0443.");
	        	} else {
	        		p.sendMessage("§a ");
	        		p.sendMessage("§9xampsRanks - desenvolvido por xampS#0443");
	        		p.sendMessage("§a ");
	        		p.sendMessage("§c* §7/xampsranks reload");
	        		p.sendMessage("§c* §7/xampsranks setkills (jogador) (número)");
	        		p.sendMessage("§c* §7/xampsranks setdeaths (jogador) (número)");
	        		p.sendMessage("§c* §7/xampsranks reset (jogador|all)");
	        		p.sendMessage("§9* §7/ranks");
	        		p.sendMessage("§9* §7/rank (jogador)");
	        		p.sendMessage("§a ");
	        		p.sendMessage("§9§lOBS: §7Ações feitas com o comando /xampsranks não são reversíveis.");
	        		p.sendMessage("§a ");
	        		p.sendMessage("§9* §7Versão do xampsranks: §9§lBeta 1.5");
	        		p.sendMessage("§a ");
	        	}
	        }  else {
	        	
		          if (args[0].equals("reset")) {
			            if (sender.hasPermission("xampsranks.cmd.reset")) {
			            	sender.sendMessage("§9* §7Comando desabilitado para manutenção.");
//		        			if (args.length == 2) {
//		        				if (args[1].equalsIgnoreCase("all")) {
//					        		try {
//					        			ResultSet rs;
//					        			rs = Main.getMysql().conectar().createStatement()
//					        					.executeQuery("SELECT * FROM `ranks`;");
//					        			if (rs.next()) {
//					        				p.sendMessage("§9* §7Você resetou as kills, mortes e ranks de §9todos§7 jogadores!");
//					        				Main.getMysql().conectar().createStatement()
//											.executeUpdate("UPDATE `ranks` SET `kill`='0';");
//					        				Main.getMysql().conectar().createStatement()
//											.executeUpdate("UPDATE `ranks` SET `death`='0';");
//							        				
//							        				int killOLD = rs.getInt("kill");
//							        				int killNEW = 0;
//							        				
//							        			for (Player p2 : Bukkit.getOnlinePlayers()) {
//							        				if (killOLD >= config.getInt("rank.rank17.kills")) {
//							        					if (killNEW <= config.getInt("rank.rank17.kills")) {
//							    							for (String s : config.getStringList("rank.rank17.permissoes")) {
//							    								PermissionsEx.getPermissionManager().getUser(p2).removePermission(s);							
//							    							}
//							        					}
//							        				}
//							        			}
//							        			
//							        			}						        				
//						        				rs.getStatement().getConnection().close();
//					        		} catch (Exception e) {
//					        			e.printStackTrace();
//										p.sendMessage(config.getString("mensagens.falha_MySQL").replace("&", "§"));
//									}	
//		        				} else {
//					            	Player jogador = Bukkit.getPlayer(args[1]);
//					        		if (jogador == null) {
//					        			p.sendMessage(config.getString("mensagens.jogadorInexistente").replace("&", "§").replace("%jogador%", args[1]));
//					        		} else {
//								        		try {
//								        			ResultSet rs;
//								        			rs = Main.getMysql().conectar().createStatement()
//								        					.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + args[1] + "';");
//								        			if (rs.next()) {
//								        				p.sendMessage("§9* §7Você resetou as kills, mortes e rank de §9" + args[1] + "§7!");
//								        				Main.getMysql().conectar().createStatement()
//														.executeUpdate("UPDATE `ranks` SET `kill`='0' WHERE `nick`='" + args[1] + "';");
//								        				Main.getMysql().conectar().createStatement()
//														.executeUpdate("UPDATE `ranks` SET `death`='0' WHERE `nick`='" + args[1] + "';");
//
//										        			ResultSet rs2;
//										        			rs2 = Main.getMysql().conectar().createStatement()
//										        					.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + args[1] + "';");
//										        			if (rs2.next()) {										        				
//										        				int killOLD = rs.getInt("kill");
//										        				int killNEW = rs2.getInt("kill");
//										        				
//										        				if (killOLD >= config.getInt("rank.rank17.kills")) {
//										        					if (killNEW <= config.getInt("rank.rank17.kills")) {
//										    							for (String s : config.getStringList("rank.rank17.permissoes")) {
//										    								PermissionsEx.getPermissionManager().getUser(jogador).removePermission(s);							
//										    							}
//										        					}
//										        				}
//										        				
//										        				if (killOLD < config.getInt("rank.rank17.kills")) {
//										        					if (killNEW > config.getInt("rank.rank17.kills")) {
//										    							for (String s : config.getStringList("rank.rank17.permissoes")) {
//										    								PermissionsEx.getPermissionManager().getUser(jogador).addPermission(s);							
//										    							}
//										        					}
//										        				}
//										        			}						        				
//									        				rs.getStatement().getConnection().close();
//									        				rs2.getStatement().getConnection().close();
//								        			}
//								        		} catch (Exception e) {
//								        			e.printStackTrace();
//													p.sendMessage(config.getString("mensagens.falha_MySQL").replace("&", "§"));
//												}		
//					        		}	
//		        				}
//
//		        			} else {
//		        				p.sendMessage(config.getString("sintaxes.reset").replace("&", "§"));
//		        			}
			            } else {
				            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("mensagens.SemPerm")));
				            return false;	
			            
			            }
			          } 
		          
	        
	        
	          if (args[0].equals("reload")) {
	            if (sender.hasPermission("xampsranks.cmd.reload")) {
	            		if (sender.getName().equalsIgnoreCase("kamibr")) {
	          		            	Main.pl.reloadConfig();
	          			          sender.sendMessage("§9* §7Plugin xampsRanks recarregado com sucesso!");
	          			          Main.pl.saveConfig();
	            		} else {
		            		sender.sendMessage("§9* §7Você não tem permissão para executar esse comando.");	            			
	            		}
	              return true;
	            } else {
		            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("mensagens.SemPerm")));
		            return false;	
	            }
	          }
	        
	          if (args[0].equals("setkills")) {
		            if (sender.hasPermission("xampsranks.cmd.setkills")) {
		            	sender.sendMessage("§9* §7Comando desativado para manutenção.");
//	        			if (args.length == 3) {
//		            	Player jogador = Bukkit.getPlayer(args[1]);
//		        		if (jogador == null) {
//		        			p.sendMessage(config.getString("mensagens.jogadorInexistente").replace("&", "§").replace("%jogador%", args[1]));
//		        		} else {
//			        			if (isInt(args[2])) {
//					        		try {
//					        			ResultSet rs;
//					        			rs = Main.getMysql().conectar().createStatement()
//					        					.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + args[1] + "';");
//					        			if (rs.next()) {
//					        				p.sendMessage("§9* §7Você setou as kills de §9" + args[1] + "§7 em §9" + args[2] + "§7 kills!");
//					        				Main.getMysql().conectar().createStatement()
//											.executeUpdate("UPDATE `ranks` SET `kill`='" + args[2] + "' WHERE `nick`='" + args[1] + "';");
//
//							        			ResultSet rs2;
//							        			rs2 = Main.getMysql().conectar().createStatement()
//							        					.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + args[1] + "';");
//							        			if (rs2.next()) {
//							        				p.sendMessage("§9* §7Kill atualizado: " + rs2.getInt("kill"));
//							        				p.sendMessage("§9* §7Kill antigo: " + rs.getInt("kill"));
//							        				
//							        				int killOLD = rs.getInt("kill");
//							        				int killNEW = rs2.getInt("kill");
//							        				
//							        				if (killOLD >= config.getInt("rank.rank17.kills")) {
//							        					if (killNEW <= config.getInt("rank.rank17.kills")) {
//							    							for (String s : config.getStringList("rank.rank17.permissoes")) {
//							    								PermissionsEx.getPermissionManager().getUser(jogador).removePermission(s);							
//							    							}
//							        					}
//							        				}
//							        				
//							        				if (killOLD < config.getInt("rank.rank17.kills")) {
//							        					if (killNEW > config.getInt("rank.rank17.kills")) {
//							    							for (String s : config.getStringList("rank.rank17.permissoes")) {
//							    								PermissionsEx.getPermissionManager().getUser(jogador).addPermission(s);							
//							    							}
//							        					}
//							        				}
//							        			}						        				
//						        				rs.getStatement().getConnection().close();
//						        				rs2.getStatement().getConnection().close();
//					        			}
//					        		} catch (Exception e) {
//					        			e.printStackTrace();
//										p.sendMessage(config.getString("mensagens.falha_MySQL").replace("&", "§"));
//									}		
//			        			} else {
//			        				p.sendMessage(config.getString("mensagens.invalid_arg").replace("&", "§").replace("%arg%", args[2]));
//			        			}
//		        		}	
//	        			} else {
//	        				p.sendMessage(config.getString("sintaxes.setkills").replace("&", "§"));
//	        			}
		            } else {
			            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("mensagens.SemPerm")));
			            return false;	
		            
		            }
		          } 
	         
	          if (args[0].equals("setdeaths")) {
		            if (sender.hasPermission("xampsranks.cmd.setdeaths")) {
		            	sender.sendMessage("§9* §7Comando desativado para mantuenção.");
//	        			if (args.length == 3) {
//		            	Player jogador = Bukkit.getPlayer(args[1]);
//		        		if (jogador == null) {
//		        			p.sendMessage(config.getString("mensagens.jogadorInexistente").replace("&", "§").replace("%jogador%", args[1]));
//		        		} else {
//			        			if (isInt(args[2])) {
//					        		try {
//					        			ResultSet rs;
//					        			rs = Main.getMysql().conectar().createStatement()
//					        					.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + args[1] + "';");
//					        			if (rs.next()) {
//					        				p.sendMessage("§9* §7Você setou as mortes de §9" + args[1] + "§7 em §9" + args[2] + "§7 mortes!");
//					        				Main.getMysql().conectar().createStatement()
//											.executeUpdate("UPDATE `ranks` SET `death`='" + args[2] + "' WHERE `nick`='" + args[1] + "';");
//					        				rs.getStatement().getConnection().close();
//					        			}
//					        		} catch (Exception e) {
//										p.sendMessage(config.getString("mensagens.falha_MySQL").replace("&", "§"));
//									}		
//			        			} else {
//			        				p.sendMessage(config.getString("mensagens.invalid_arg").replace("&", "§").replace("%arg%", args[2]));
//			        			}
//		        		}	
//	        			} else {
//	        				p.sendMessage(config.getString("sintaxes.setdeaths").replace("&", "§"));
//	        			}
		            } else {
			            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("mensagens.SemPerm")));
			            return false;	
		            
		            }
		          } 
	        }
	          return false;
	    } else {
	    	 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("mensagens.somente_players")));
	    } 
	    return false;
	  }

}
