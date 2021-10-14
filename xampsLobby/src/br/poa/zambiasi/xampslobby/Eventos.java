package br.poa.zambiasi.xampslobby;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import br.poa.zambiasi.xampslobby.utils.Gui;
import br.poa.zambiasi.xampslobby.utils.Inventarios;
import br.poa.zambiasi.xampslobby.utils.ItemAPI;
import br.poa.zambiasi.xampslobby.utils.TitleAPI;
import de.howaner.BungeeCordLib.BungeeCord;
import de.howaner.BungeeCordLib.server.BungeeServer;
import de.howaner.BungeeCordLib.server.ServerData;

public class Eventos implements Listener {
	
	public static HashMap<Player, String> players =  new HashMap();
	public static ArrayList<Player> hat = new ArrayList();
	
	@EventHandler
	void ping(ServerListPingEvent e) {
		e.setMotd(Main.pl.getConfig().getString("Motd").replace("&", "§").replace("%n%", "\n").replace("%players%",
				String.valueOf(Bukkit.getOnlinePlayers().size())));
		e.setMaxPlayers(Main.pl.getConfig().getInt("Slots"));
	}

	@EventHandler
	void entrar(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("xampslobby.joinmessage")) {
			Player p = e.getPlayer();
			try {
				ResultSet rs;
				rs = Main.getMysql().conectar().createStatement()
						.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + p.getName() + "';");
				if (rs.next()) {
					e.setJoinMessage(Main.pl.getConfig().getString("Mensagens.Entrou").replace("%rank%", Inventarios.rankNick(rs.getInt("xp"))).replace("%grupo%", Inventarios.grupo(p)).replace("&", "§").replace("%player%", p.getName()));
				}
				rs.getStatement().getConnection().close();
			  } catch (SQLException e1) {
			  }
		} else {
			e.setJoinMessage(null);
		}
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().sendMessage("§aBem vindo ao NemesisMC, §7" + e.getPlayer().getName() + "§a!");
		e.getPlayer().sendMessage("§a ");
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
			TitleAPI.sendTabTitle(e.getPlayer(),
				Main.pl.getConfig().getString("Tab_Superior").replace("&", "§").replace("%linha%", "\n").replace("%discord%", Main.pl.getConfig().getString("Gerais.Discord")),
				Main.pl.getConfig().getString("Tab_Inferior").replace("&", "§").replace("%linha%", "\n").replace("%discord%", Main.pl.getConfig().getString("Gerais.Discord")));
			Inventarios.resetar(e.getPlayer());
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent e) {
	  String[] msg = e.getMessage().split(" ");
	  List<String> plugins = new ArrayList<>();
	  plugins.add("pl");
	  plugins.add("plugin");
	  plugins.add("ver" + msg);
	  plugins.add("help" + msg);
	  plugins.add("say");
	  plugins.add("say" + msg);
	  plugins.add("ver");
	  plugins.add("help");
	  plugins.add("?");
	  plugins.add("me");
	  plugins.add("bukkit:help");
	  plugins.add("plugins");
	  plugins.add("minecraft:me");
	  for (String Loop : plugins) {
	if (e.getPlayer().getName().equalsIgnoreCase("kamibr")) {
		e.setCancelled(false);
	} else {
	    if (msg[0].equalsIgnoreCase("/" + Loop)) {
	        e.getPlayer().sendMessage("§7Os plugins foram desenvolvidos por §3xampS#0443§7 §7para §3NemesisMC!");
	        e.setCancelled(true);
	    }	
	}
	   }
	}
	
	@EventHandler
	void drops(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	void sair(PlayerQuitEvent e) {
		if (e.getPlayer().hasPermission("xampslobby.quitmessage")) {
			Player p = e.getPlayer();
			try {
				ResultSet rs;
				rs = Main.getMysql().conectar().createStatement()
						.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + p.getName() + "';");
				if (rs.next()) {
					e.setQuitMessage(Main.pl.getConfig().getString("Mensagens.Saiu").replace("%rank%", Inventarios.rankNick(rs.getInt("xp"))).replace("%grupo%", Inventarios.grupo(p)).replace("&", "§").replace("%player%", p.getName()));
				}
				rs.getStatement().getConnection().close();
			  } catch (SQLException e1) {
			  }
		} else {
			e.setQuitMessage(null);
		}
	}
	
	@EventHandler
	void spawn(CreatureSpawnEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	void kabum(BlockExplodeEvent e) {
		e.setCancelled(true);
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

	public void conectarBungee(Player p, String s) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(s);
		Player player = Bukkit.getPlayerExact(p.getName());
		player.sendPluginMessage(Main.pl, "BungeeCord", out.toByteArray());
	}
	
	@EventHandler
	void Chat(AsyncPlayerChatEvent e) {
		if (br.poa.zambiasi.xampslobby.cmds.Chat.chat == false) {
			if (!e.getPlayer().hasPermission("xampslobby.bypass.chat")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§c* §7Chat indisponível! :/");
				return;
			}
		}
			try {
				ResultSet rs;
				rs = Main.getMysql().conectar().createStatement()
						.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + e.getPlayer().getName() + "';");
				rs.next();
				if (Main.pl.getConfig().getBoolean("Gerais.LowerCase")) {
					e.setFormat("§7[" + Inventarios.rankNick(rs.getInt("xp")) + "§7] " + e.getPlayer().getDisplayName() + "§r: " + e.getMessage().toLowerCase());					
				} else {
					e.setFormat("§7[" + Inventarios.rankNick(rs.getInt("xp")) + "§7] " + e.getPlayer().getDisplayName() + "§r: " + e.getMessage());
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
			}

	}

	@EventHandler
	void fome(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	void placa(SignChangeEvent e) {
		for (int i = 0; i < 4; i++) {
			e.setLine(i, e.getLine(i).replace("&", "§"));
		}
		if (e.getLine(0).toLowerCase().contains("server")) {
			e.setLine(0, "§5§lCONECTAR");
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction().name().contains("BLOCK")) {
			if (e.getClickedBlock().getType() == Material.WALL_SIGN
					|| e.getClickedBlock().getType() == Material.SIGN_POST) {
				Sign placa = (Sign) e.getClickedBlock().getState();
				if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
					return;
				if (placa.getLine(1).equalsIgnoreCase("§5§lCONECTAR")) {
					e.setCancelled(true);
					String linha2 = placa.getLine(1);
					conectarBungee(e.getPlayer(), linha2);
				} 
			}
		}
	}


	@EventHandler
	void flecha(ProjectileHitEvent e) {
		Entity entity = e.getEntity();
		if (entity.getType() == EntityType.ARROW) {
			entity.remove();
		}
	}
	
	@EventHandler
	public void clicar(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if (e.getAction().equals(Action.LEFT_CLICK_AIR))
			return;
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
			return;
		if(p.getItemInHand().getType() == Material.COMPASS) {
			// PVP
			BungeeServer serverPVP = BungeeCord.getManager().addServer(Main.pl.getConfig().getString("servidores.kitpvp.nome"),
					Main.pl.getConfig().getString("servidores.kitpvp.ip"));
			ServerData dataPVP = serverPVP.getData();
			// HG
			BungeeServer serverHG = BungeeCord.getManager().addServer(Main.pl.getConfig().getString("servidores.hg.nome"),
					Main.pl.getConfig().getString("servidores.hg.ip"));
			ServerData dataHG = serverHG.getData();
			
			if (Main.pl.getConfig().getBoolean("Gerais.habilitar_hg") == true) {
			if (dataPVP == null || dataHG == null) {
				
				if (p.hasPermission("xampslobby.admin")) {
					if (dataPVP == null) {
						p.sendMessage("§c* §7Sistema offline: §cKitPvP");
					}
					if (dataHG == null) {
						p.sendMessage("§c* §7Sistema offline: §cHG");
					}
					if (dataPVP == null && dataHG == null) {
						p.sendMessage("§c* §7Sistemas offlines: §cKitPvP §7e §cHG");
					}
				} else {
					p.sendMessage("§a* §7Sistema em manutenção!");					
				}
			} else {
				Gui.servidores(p);	
			}
		} else {
			if (p.hasPermission("xampslobby.admin")) {
				if (dataPVP == null) {
					p.sendMessage("§c* §7Sistema offline: §cKitPvP");
				} else {
					Gui.servidores(p);
				}
			}  else {
				if (dataPVP == null) {
					p.sendMessage("§a* §7Sistema em manutenção!");	
					} else {
					Gui.servidores(p);
				}				
			}
		}
			
		}
		
		if(p.getItemInHand().getType() == Material.NETHER_STAR) {
			Gui.hats(p);
		}
		
		if(p.getItemInHand().getType() == Material.MAGMA_CREAM){
			if(p.getItemInHand().getItemMeta().getDisplayName().equals("§cDesativar Jogadores §8(§eClique§8)")){
				if(players.get(p) == "ativado"){
					players.put(p, "desativado");
					Metodos.Esconder(p);
					p.getInventory().remove(Material.MAGMA_CREAM);
					p.getInventory().setItem(3, ItemAPI.Criar(Material.MAGMA_CREAM, 1, 0, "§aAtivar Jogadores §8(§eClique§8)", false));
					p.updateInventory();
					p.sendMessage("§a* §7Jogadores desativados!");
					p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
					return;
				}
			}
			if(p.getItemInHand().getItemMeta().getDisplayName().equals("§aAtivar Jogadores §8(§eClique§8)")){
				if(players.get(p) == "desativado"){
					players.put(p, "ativado");
					Metodos.Aparecer(p);
					p.getInventory().remove(Material.MAGMA_CREAM);
					p.getInventory().setItem(3, ItemAPI.Criar(Material.MAGMA_CREAM, 1, 0, "§cDesativar Jogadores §8(§eClique§8)", false));
					p.updateInventory();
					p.sendMessage("§a* §7Jogadores ativados!");
					p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
					return;
				}
			}
		}
		
	}
	
	@EventHandler
	void onPlayerCLickInventry(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if ((e.getInventory().getTitle().equalsIgnoreCase("§6Servidores")) && (e.getCurrentItem() != null)) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.IRON_SWORD) {
				p.sendMessage("§a* §7Movendo você para o servidor de KitPvP!");
				
				BungeeServer serverPVP = BungeeCord.getManager().addServer(Main.pl.getConfig().getString("servidores.kitpvp.nome"),
						Main.pl.getConfig().getString("servidores.kitpvp.ip"));
				try {
					serverPVP.teleportPlayer(p);
				} catch (Exception e1) {
					p.sendMessage("§c* §7Erro ao direcionar você para o servidore de KITPVP!");
					e1.printStackTrace();
				}
				return;
			}
			
			if (e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
				p.sendMessage("§a* §7Movendo você para o servidor de HG!");
				
				BungeeServer serverHG = BungeeCord.getManager().addServer(Main.pl.getConfig().getString("servidores.hg.nome"),
						Main.pl.getConfig().getString("servidores.hg.ip"));
				try {
					serverHG.teleportPlayer(p);
				} catch (Exception e1) {
					p.sendMessage("§c* §7Erro ao direcionar você para o servidore de HG!");
					e1.printStackTrace();
				}
				
				return;
			}
		}
		
		
		// HATS
		
	    if (e.getInventory().getTitle().equals("§aHats")){
		      p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
		      e.setCancelled(true);
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Vidro")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.GLASS, "§6Vidro"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Bloco de Ouro")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.GOLD_BLOCK, "§6Bloco de Ouro"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Bloco de Diamante")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.DIAMOND_BLOCK, "§6Bloco de Diamante"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Beacon")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.BEACON, "§6Beacon"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6TNT")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.TNT, "§6TNT"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Pistao")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.PISTON_BASE, "§6Pistao"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Ejetor")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.DISPENSER, "§6Ejetor"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6MobSpawner")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.MOB_SPAWNER, "§6MobSpawner"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6de Bloco de Feno")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.HAY_BLOCK, "§6Bloco de Feno"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Portal do Fim")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.ENDER_PORTAL_FRAME, "§6Portal do Fim"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Slime Block")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.SLIME_BLOCK, "§6Slime Block"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Fornalha")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.FURNACE, "§6Fornalha"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Ender Chest")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.ENDER_CHEST, "§6Ender Chest"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Bau")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.CHEST, "§6Bau"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Folha")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.LEAVES, "§6Folha"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Cascalho")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.GRAVEL, "§6Cascalho"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Estante")){
		    	  p.closeInventory();
		    	  hat.add(p);
		    	  p.sendMessage("§a* §7Hat selecionada com sucesso!");
		    	  p.getInventory().setHelmet(ItemAPI.criarItem(Material.BOOKSHELF, "§6Estante"));
		      }
		      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRemover")){
		    	  if(!hat.contains(p)){
		    		  p.closeInventory();
		    		  p.sendMessage("§e* §7Voce precisa estar usando uma Hat para fazer isto!");
		    		  return;
		    	  }
		    	  hat.remove(p);
		    	  p.closeInventory();
		    	  p.sendMessage("§a* §7Hat removido!");
		    	  p.getInventory().setHelmet(null);
		      }
		    }
	    
	}
	
	

}
