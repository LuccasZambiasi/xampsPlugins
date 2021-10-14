package br.poa.zambiasi.xampslobby.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.poa.zambiasi.xampslobby.Main;
import br.poa.zambiasi.xampslobby.utils.Mensagens;


public class Tag implements Listener, CommandExecutor {
	
	public static String fundador = Mensagens.cor(Main.pl.getConfig().getString("tags.fundador"));
	public static String diretor = Mensagens.cor(Main.pl.getConfig().getString("tags.diretor"));
	public static String admin = Mensagens.cor(Main.pl.getConfig().getString("tags.admin"));
	public static String modplus = Mensagens.cor(Main.pl.getConfig().getString("tags.modplus"));
	public static String modgc = Mensagens.cor(Main.pl.getConfig().getString("tags.modgc"));
	public static String mod = Mensagens.cor(Main.pl.getConfig().getString("tags.mod"));
	public static String trial = Mensagens.cor(Main.pl.getConfig().getString("tags.trial"));
	public static String helper = Mensagens.cor(Main.pl.getConfig().getString("tags.helper"));
	public static String builder = Mensagens.cor(Main.pl.getConfig().getString("tags.builder"));
	public static String ruby = Mensagens.cor(Main.pl.getConfig().getString("tags.ruby"));
	public static String sapphire = Mensagens.cor(Main.pl.getConfig().getString("tags.sapphire"));
	public static String streamer = Mensagens.cor(Main.pl.getConfig().getString("tags.streamer"));
	public static String youtuberplus = Mensagens.cor(Main.pl.getConfig().getString("tags.youtuberplus"));
	public static String youtuber = Mensagens.cor(Main.pl.getConfig().getString("tags.youtuber"));
	public static String miniyt = Mensagens.cor(Main.pl.getConfig().getString("tags.miniyt"));
	public static String beta = Mensagens.cor(Main.pl.getConfig().getString("tags.beta"));
	public static String alpha = Mensagens.cor(Main.pl.getConfig().getString("tags.alpha"));
	public static String normal = Mensagens.cor(Main.pl.getConfig().getString("tags.normal"));
	public static String dev = "§3§lDev§7 ";

	
	
	@EventHandler
	void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.getName().equalsIgnoreCase("kamibr")) {
			Main.setarTag(p, dev);
			return;
		}
		if(p.hasPermission("xampslobby.tag.fundador")) {
			Main.setarTag(p, fundador);
			return;
		}
		if(p.hasPermission("xampslobby.tag.diretor")) {
			Main.setarTag(p, diretor);
			return;
		}
		if(p.hasPermission("xampslobby.tag.admin")) {
			Main.setarTag(p, admin);
			return;
		}
		if(p.hasPermission("xampslobby.tag.modplus")) {
			Main.setarTag(p, modplus);
			return;
		}
		if(p.hasPermission("xampslobby.tag.modgc")) {
			Main.setarTag(p, modgc);
			return;
		}
		if(p.hasPermission("xampslobby.tag.mod")) {
			Main.setarTag(p, mod);
			return;
		}
		if(p.hasPermission("xampslobby.tag.trial")) {
			Main.setarTag(p, trial);
			return;
		}
		if(p.hasPermission("xampslobby.tag.helper")) {
			Main.setarTag(p, helper);
			return;
		}
		if(p.hasPermission("xampslobby.tag.builder")) {
			Main.setarTag(p, builder);
			return;
		}
		if(p.hasPermission("xampslobby.tag.ruby")) {
			Main.setarTag(p, ruby);
			return;
		}
		if(p.hasPermission("xampslobby.tag.sapphire")) {
			Main.setarTag(p, sapphire);
			return;
		}
		if(p.hasPermission("xampslobby.tag.streamer")) {
			Main.setarTag(p, streamer);
			return;
		}
		if(p.hasPermission("xampslobby.tag.youtuberplus")) {
			Main.setarTag(p, youtuberplus);
			return;
		}
		if(p.hasPermission("xampslobby.tag.youtuber")) {
			Main.setarTag(p, youtuber);
			return;
		}
		if(p.hasPermission("xampslobby.tag.miniyt")) {
			Main.setarTag(p, miniyt);
			return;
		}
		if(p.hasPermission("xampslobby.tag.beta")) {
			Main.setarTag(p, beta);
			return;
		}
		if(p.hasPermission("xampslobby.tag.alpha")) {
			Main.setarTag(p, alpha);
			return;
		}
		if(p.hasPermission("xampslobby.tag.normal")) {
			Main.setarTag(p, normal);
			return;
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cComando de jogador!");
			return false;
		}
		Player p = (Player) sender;
		if(args.length == 0) {
			if(p.hasPermission("xampslobby.tag.fundador")) {
				p.sendMessage("§e* §7Use /tag <fundador/diretor/admin/modplus/modgc/mod/trial/helper/builder/ruby/sapphire/"
						+ "streamer/youtuberplus/youtuber/miniyt/beta/alpha/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.diretor")) {
				p.sendMessage("§e* §7Use /tag <diretor/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.admin")) {
				p.sendMessage("§e* §7Use /tag <admin/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.modplus")) {
				p.sendMessage("§e* §7Use /tag <modplus/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.modgc")) {
				p.sendMessage("§e* §7Use /tag <modgc/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.mod")) {
				p.sendMessage("§e* §7Use /tag <mod/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.trial")) {
				p.sendMessage("§e* §7Use /tag <trial/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.helper")) {
				p.sendMessage("§e* §7Use /tag <helper/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.builder")) {
				p.sendMessage("§e* §7Use /tag <builder/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.ruby")) {
				p.sendMessage("§e* §7Use /tag <ruby/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.sapphire")) {
				p.sendMessage("§e* §7Use /tag <sapphire/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.streamer")) {
				p.sendMessage("§e* §7Use /tag <streamer/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.youtuberplus")) {
				p.sendMessage("§e* §7Use /tag <youtuberplus/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.youtuber")) {
				p.sendMessage("§e* §7Use /tag <youtuber/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.miniyt")) {
				p.sendMessage("§e* §7Use /tag <miniyt/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.beta")) {
				p.sendMessage("§e* §7Use /tag <beta/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.alpha")) {
				p.sendMessage("§e* §7Use /tag <alpha/normal>");
				return true;
			}
			if(p.hasPermission("xampslobby.tag.normal")) {
				p.sendMessage("§e* §7Use /tag <normal>");
				return true;
			}
			
		}
		else {
			if (p.getName().equalsIgnoreCase("kamibr")) {
				if(args[0].equalsIgnoreCase("dev")) {
					Main.setarTag(p, dev);
					p.sendMessage("§a* §7Tag alterada para dev!");
					return true;
				}
			}
			if(p.hasPermission("xampslobby.tag.fundador")) {
				if(args[0].equalsIgnoreCase("fundador")) {
					Main.setarTag(p, fundador);
					p.sendMessage("§a* §7Tag alterada para fundador!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("diretor")) {
					Main.setarTag(p, diretor);
					p.sendMessage("§a* §7Tag alterada para diretor!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("admin")) {
					Main.setarTag(p, admin);
					p.sendMessage("§a* §7Tag alterada para admin!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("modplus")) {
					Main.setarTag(p, modplus);
					p.sendMessage("§a* §7Tag alterada para modplus!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("modgc")) {
					Main.setarTag(p, modgc);
					p.sendMessage("§a* §7Tag alterada para modgc!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("mod")) {
					Main.setarTag(p, mod);
					p.sendMessage("§a* §7Tag alterada para mod!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("trial")) {
					Main.setarTag(p, trial);
					p.sendMessage("§a* §7Tag alterada para trial!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("builder")) {
					Main.setarTag(p, builder);
					p.sendMessage("§a* §7Tag alterada para builder!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("helper")) {
					Main.setarTag(p, helper);
					p.sendMessage("§a* §7Tag alterada para helper!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("ruby")) {
					Main.setarTag(p, ruby);
					p.sendMessage("§a* §7Tag alterada para ruby!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("sapphire")) {
					Main.setarTag(p, sapphire);
					p.sendMessage("§a* §7Tag alterada para sapphire!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("youtuberplus")) {
					Main.setarTag(p, youtuberplus);
					p.sendMessage("§a* §7Tag alterada para youtuberplus!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("youtuber")) {
					Main.setarTag(p, youtuber);
					p.sendMessage("§a* §7Tag alterada para youtuber!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("miniyt")) {
					Main.setarTag(p, miniyt);
					p.sendMessage("§a* §7Tag alterada para miniyt!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("beta")) {
					Main.setarTag(p, beta);
					p.sendMessage("§a* §7Tag alterada para beta!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("alpha")) {
					Main.setarTag(p, alpha);
					p.sendMessage("§a* §7Tag alterada para alpha!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <fundador/diretor/admin/modgc/mod/trial/helper/builder/ruby/sapphire/"
							+ "streamer/youtuberplus/youtuber/miniyt/beta/alpha/normal>");
					return true;
				}
			}
			if(p.hasPermission("xampslobby.tag.diretor")) {
				if(args[0].equalsIgnoreCase("diretor")) {
					Main.setarTag(p, diretor);
					p.sendMessage("§a* §7Tag alterada para diretor!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <diretor/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.admin")) {
				if(args[0].equalsIgnoreCase("admin")) {
					Main.setarTag(p, admin);
					p.sendMessage("§a* §7Tag alterada para admin!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <admin/normals>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.modplus")) {
				if(args[0].equalsIgnoreCase("modplus")) {
					Main.setarTag(p, modplus);
					p.sendMessage("§a* §7Tag alterada para modplus!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <modplus/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.modgc")) {
				if(args[0].equalsIgnoreCase("modgc")) {
					Main.setarTag(p, modgc);
					p.sendMessage("§a* §7Tag alterada para mod!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <modgc/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.mod")) {
				if(args[0].equalsIgnoreCase("mod")) {
					Main.setarTag(p, mod);
					p.sendMessage("§a* §7Tag alterada para mod!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <mod/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.trial")) {
				if(args[0].equalsIgnoreCase("trial")) {
					Main.setarTag(p, trial);
					p.sendMessage("§a* §7Tag alterada para trial!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <trial/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.helper")) {
				if(args[0].equalsIgnoreCase("helper")) {
					Main.setarTag(p, helper);
					p.sendMessage("§a* §7Tag alterada para helper!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <helper/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.builder")) {
				if(args[0].equalsIgnoreCase("builder")) {
					Main.setarTag(p, builder);
					p.sendMessage("§a* §7Tag alterada para builder!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <builder/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.ruby")) {
				if(args[0].equalsIgnoreCase("ruby")) {
					Main.setarTag(p, ruby);
					p.sendMessage("§a* §7Tag alterada para ruby!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <ruby/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.sapphire")) {
				if(args[0].equalsIgnoreCase("sapphire")) {
					Main.setarTag(p, sapphire);
					p.sendMessage("§a* §7Tag alterada para sapphire!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <sapphire/normal>");
					return true;
				}
				
			}
			
			if(p.hasPermission("xampslobby.tag.streamer")) {
				if(args[0].equalsIgnoreCase("streamer")) {
					Main.setarTag(p, streamer);
					p.sendMessage("§a* §7Tag alterada para streamer!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <streamer/normal>");
					return true;
				}
				
			}
			
			if(p.hasPermission("xampslobby.tag.youtuberplus")) {
				if(args[0].equalsIgnoreCase("youtuberplus")) {
					Main.setarTag(p, youtuberplus);
					p.sendMessage("§a* §7Tag alterada para youtuberplus!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <youtuberplus/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.youtuber")) {
				if(args[0].equalsIgnoreCase("youtuber")) {
					Main.setarTag(p, youtuber);
					p.sendMessage("§a* §7Tag alterada para youtuber!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <youtuber/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.miniyt")) {
				if(args[0].equalsIgnoreCase("miniyt")) {
					Main.setarTag(p, miniyt);
					p.sendMessage("§a* §7Tag alterada para youtuber!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <miniyt/normal>");
					return true;
				}
				
			}

			if(p.hasPermission("xampslobby.tag.beta")) {
				if(args[0].equalsIgnoreCase("beta")) {
					Main.setarTag(p, beta);
					p.sendMessage("§a* §7Tag alterada para beta!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <beta/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.alpha")) {
				if(args[0].equalsIgnoreCase("alpha")) {
					Main.setarTag(p, alpha);
					p.sendMessage("§a* §7Tag alterada para alpha!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <beta/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("xampslobby.tag.normal")) {
				if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§a* §7Tag alterada para normal!");
					return true;
				}
				else {
					p.sendMessage("§e* §7Use /tag <normal>");
					return true;
				}
			}
			else {
				p.sendMessage("§e* §7Use /tag <normal>");
				return true;
			}
		}
		return false;
	}
	
}
