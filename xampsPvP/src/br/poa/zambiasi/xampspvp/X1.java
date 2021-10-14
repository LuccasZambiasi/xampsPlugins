package br.poa.zambiasi.xampspvp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import br.poa.zambiasi.xampspvp.utils.HabilidadeApi;
import br.poa.zambiasi.xampspvp.utils.Inventarios;
import br.poa.zambiasi.xampspvp.utils.ItemAPI;

public class X1 implements Listener {

	public static Map<String, String> convites = new HashMap<String, String>();
	public static Map<String, String> lutadores = new HashMap<String, String>();
	public static ArrayList<Player> hide = new ArrayList<>();
	public static ArrayList<Player> inx1 = new ArrayList<>();

	public static void sair1v1(Player p) {
		p.getInventory().clear();
		inx1.remove(p);
		Inventarios.resetar(p);
		p.teleport(p.getWorld().getSpawnLocation());
		if (ProtecaoSpawn.protegidos.contains(p)) {
			ProtecaoSpawn.protegidos.remove(p);
		}
		if (ProtecaoSpawn.Fogo.contains(p)) {
			ProtecaoSpawn.Fogo.add(p);
		}
	}

	public static void entrar1v1(Player p) {
		if (!ProtecaoSpawn.protegidos.contains(p)) {
			ProtecaoSpawn.protegidos.add(p);
		}
		if (!ProtecaoSpawn.Fogo.contains(p)) {
			ProtecaoSpawn.Fogo.add(p);
		}
		inx1.add(p);
		p.teleport(new Location(p.getWorld(), Main.cfg_x1.getDouble("x1.coords.spawn.x"),
				Main.cfg_x1.getDouble("x1.coords.spawn.y"), Main.cfg_x1.getDouble("x1.coords.spawn.z")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);

		p.getInventory().setItem(0,
				ItemAPI.Criar(Material.BLAZE_ROD, 1, 0, "§61v1", false));
		p.getInventory().setItem(8,
				ItemAPI.Criar(Material.REDSTONE, 1, 0, "§cSair", false));

		p.setHealth(20D);
		p.setExp(0);
		p.setLevel(0);
		HabilidadeApi.setarHB(p, "1v1");
		Inventarios.upDateScore(p);
	}

	public static void set1v1(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setItem(0,
				ItemAPI.Criar(Material.DIAMOND_SWORD, 1, 0, "§cEspada de Diamante", true, Enchantment.DAMAGE_ALL, 1));
		p.getInventory().setHelmet(ItemAPI.Criar(Material.IRON_HELMET, 1, 0, "§7Armadura", true));
		p.getInventory().setChestplate(ItemAPI.Criar(Material.IRON_CHESTPLATE, 1, 0, "§7Armadura", true));
		p.getInventory().setLeggings(ItemAPI.Criar(Material.IRON_LEGGINGS, 1, 0, "§7Armadura", true));
		p.getInventory().setBoots(ItemAPI.Criar(Material.IRON_BOOTS, 1, 0, "§7Armadura", true));
		Inventarios.upDateScore(p);
		for (int i = 1; i < 9; i++) {
			ItemStack sopa = ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0, "§7Sopa", false);
			p.getInventory().addItem(new ItemStack(sopa));
		}
		if (ProtecaoSpawn.Fogo.contains(p)) {
			ProtecaoSpawn.Fogo.remove(p);
		}
		p.updateInventory();
	}

	public static void aceitar(Player p1, Player p2) {
		p1.teleport(new Location(p1.getWorld(), Main.cfg_x1.getDouble("x1.coords.loc_1.x"),
				Main.cfg_x1.getDouble("x1.coords.loc_1.y"), Main.cfg_x1.getDouble("x1.coords.loc_1.z"),
				Float.valueOf((float) Main.cfg_x1.getDouble("x1.coords.loc_1.yaw")), Float.valueOf((float) Main.cfg_x1.getDouble("x1.coords.loc_1.pitch"))));
		p2.teleport(new Location(p1.getWorld(), Main.cfg_x1.getDouble("x1.coords.loc_2.x"),
				Main.cfg_x1.getDouble("x1.coords.loc_2.y"), Main.cfg_x1.getDouble("x1.coords.loc_2.z"),
				Float.valueOf((float) Main.cfg_x1.getDouble("x1.coords.loc_2.yaw")), Float.valueOf((float) Main.cfg_x1.getDouble("x1.coords.loc_2.pitch"))));
		
		set1v1(p1);
		set1v1(p2);
		lutadores.put(p1.getName(), p2.getName());
		lutadores.put(p2.getName(), p1.getName());
		convites.remove(p1.getName());
		for (Player pp : Bukkit.getOnlinePlayers()) {
			p1.hidePlayer(pp);
		}
		hide.add(p1);
		for (Player pp : Bukkit.getOnlinePlayers()) {
			p2.hidePlayer(pp);
		}
		hide.add(p2);
		p1.showPlayer(p2);
		p2.showPlayer(p1);
		p1.updateInventory();
		p2.updateInventory();
		p1.sendMessage("§a* §7O jogador " + p2.getName() + " aceitou o 1v1!");
		p2.sendMessage("§a* §7Você aceitou o pedido de 1v1 do jogador " + p1.getName());
	}

	@EventHandler
	public void interact(PlayerInteractEvent e) {
		if (HabilidadeApi.verHB(e.getPlayer()).equalsIgnoreCase("1v1")) {
			if (e.getAction().name().contains("RIGHT_CLICK")) {
				if (e.getPlayer().getItemInHand().getType().equals(Material.REDSTONE)) {
					sair1v1(e.getPlayer());
				}
			}
		}
	}

	@EventHandler
	public void entrar(PlayerJoinEvent e) {
		for (Player p : hide) {
			p.hidePlayer(e.getPlayer());
		}
	}

	@EventHandler
	public void cmd(PlayerCommandPreprocessEvent e) {
		if (HabilidadeApi.verHB(e.getPlayer()).equalsIgnoreCase("1v1")) {
			e.getPlayer().sendMessage(Mensagens.cor("§c* §7Sem comandos aqui!"));
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void kick(PlayerQuitEvent e) {
		if (lutadores.containsKey(e.getPlayer().getName())) {
			Player matou = Bukkit.getServer().getPlayerExact(lutadores.get(e.getPlayer().getName()));
			Player perdedor = e.getPlayer();
			matou.sendMessage("§a* §7Seu oponente desconectou-se!");
			hide.remove(perdedor);
			hide.remove(matou);
			for (Player pp : Bukkit.getOnlinePlayers()) {
				matou.showPlayer(pp);
				perdedor.showPlayer(pp);
			}
			entrar1v1(matou);
		}
	}

	@EventHandler
	public void kick(PlayerKickEvent e) {
		if (lutadores.containsKey(e.getPlayer().getName())) {
			Player matou = Bukkit.getServer().getPlayerExact(lutadores.get(e.getPlayer().getName()));
			Player perdedor = e.getPlayer();
			matou.sendMessage("§a* §7Seu oponente desconectou-se!");
			hide.remove(perdedor);
			hide.remove(matou);
			for (Player pp : Bukkit.getOnlinePlayers()) {
				matou.showPlayer(pp);
				perdedor.showPlayer(pp);
			}
			entrar1v1(matou);
		}
	}

	@EventHandler
	public void morrer(PlayerDeathEvent e) {
		Player p = e.getEntity().getPlayer();
		Player k = p.getKiller();
		if (!HabilidadeApi.verHB(p).equalsIgnoreCase("1v1"))
			return;
		if ((e.getEntity() instanceof Player)) {
			p.spigot().respawn();
			p.setFireTicks(0);
			for (PotionEffect effect : p.getActivePotionEffects())
				p.removePotionEffect(effect.getType());
		}
		if ((p instanceof Player) && (k instanceof Player)) {
			p.spigot().respawn();
			p.setFireTicks(0);
			for (PotionEffect effect : p.getActivePotionEffects())
				p.removePotionEffect(effect.getType());
		}
		if (lutadores.containsKey(e.getEntity().getPlayer().getName())) {
			new BukkitRunnable() {
				
				@Override
				public void run() {
					Player matou = Bukkit.getServer()
							.getPlayerExact(lutadores.get(e.getEntity().getPlayer().getName()));
					Player morreu = e.getEntity().getPlayer();
					morreu.spigot().respawn();
					lutadores.remove(morreu.getName());
					lutadores.remove(matou.getName());
					hide.remove(matou);
					hide.remove(morreu);
					entrar1v1(matou);
					entrar1v1(morreu);
					morreu.updateInventory();
					matou.updateInventory();
					for (Player online : Bukkit.getOnlinePlayers()) {
						morreu.showPlayer(online);
						matou.showPlayer(online);
					}

				}
			}.runTaskLater(Main.pl, 5);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void InteractItem(PlayerInteractEntityEvent e) {
		if (HabilidadeApi.verHB(e.getPlayer()).equalsIgnoreCase("1v1")) {
			if (((e.getRightClicked() instanceof Player))
					&& (e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD)) {
				Player p = e.getPlayer();
				Player target = (Player) e.getRightClicked();
				if (convites.containsKey(target.getName())) {
					if (((String) convites.get(target.getName())).equalsIgnoreCase(p.getName())) {
						aceitar(target, p);
					} else if (!convites.containsKey(p.getName())) {
						p.sendMessage("§a* §7Você chamou o jogador " + target.getName() + " pro 1v1!");
						target.sendMessage("§a* §7Você foi desafiado para o 1v1 por " + p.getName());
						convites.put(p.getName(), target.getName());
						Bukkit.getServer().getScheduler().runTaskLater(Main.pl, new BukkitRunnable() {

							@Override
							public void run() {
								if (convites.containsKey(p.getName())) {
									convites.remove(p.getName());
								}
							}
						}, 200L);
					} else {
						p.sendMessage("§a* §7Aguarde para iniciar outro 1v1!");
					}
				} else if (!convites.containsKey(p.getName())) {
					p.sendMessage("§a* §7Você chamou o jogador " + target.getName() + " pro 1v1!");
					target.sendMessage("§a* §7Você foi desafiado para o 1v1 por " + p.getName());
					convites.put(p.getName(), target.getName());
					Bukkit.getServer().getScheduler().runTaskLater(Main.pl, new BukkitRunnable() {

						@Override
						public void run() {
							if (convites.containsKey(p.getName())) {
								convites.remove(p.getName());
							}
						}
					}, 200L);
				} else {
					p.sendMessage("§a* §7Aguarde para iniciar outro 1v1!");
				}

			}
		}
	}

}
