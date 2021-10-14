package br.poa.zambiasi.xampslimiter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Main extends JavaPlugin implements Listener {
	
	private HashMap<String, Integer> limits = new HashMap<>();
	private HashMap<String, HashMap<String, Integer>> blocks = new HashMap<>();
	public static Plugin pl;
	private static Mysql mysql;

	public static Mysql getMysql() {
		return mysql;
	}
	
	@Override
	public void onEnable() {
		pl = this;
		saveDefaultConfig();
		reloadConfig();
		
		mysql = new Mysql(pl.getConfig().getString("mysql.database"), pl.getConfig().getString("mysql.host"),
				pl.getConfig().getString("mysql.porta"), pl.getConfig().getString("mysql.senha"),
				pl.getConfig().getString("mysql.user"));

		try {
			mysql.conectar().createStatement().executeUpdate(
					"CREATE TABLE IF NOT EXISTS `limite` (`nick` varchar(64), `kill` int, `death` int, `money` int, `xp` int, `click` int)");
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§a ");
			Bukkit.getConsoleSender().sendMessage("§c* §7xampsLimiter: ERRO NO MYSQL!");
			Bukkit.getConsoleSender().sendMessage("§c* §7desenvolvido por xampS#0443");
			Bukkit.getConsoleSender().sendMessage("§a ");
			Bukkit.getServer().getPluginManager().disablePlugin(Main.pl);
			return;
		}
		
		for (String s : getConfig().getStringList("Limites")) {
			String[] args = s.split(",");
			if (args.length < 2) continue;
			if (!(args[0].contains(":"))) continue;
			try {
				int limit = Integer.valueOf(args[1]);
				if (limit < 0) continue;
				limits.put(args[0], limit);
			} catch (NumberFormatException e) {
				continue;
			}
		}
		
		load();
		
		Bukkit.getPluginManager().registerEvents(this, this);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				save();
			}
		}.runTaskTimerAsynchronously(this, 6000L, 6000L);
	}
	
	@Override
	public void onDisable() {
		save();
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlace(BlockPlaceEvent e) {
		if (e.isCancelled()) return;
		
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (!(getWG().canBuild(p, b))) return;

		
		if (!(canPlace(p, b))) {
			if (p.hasPermission("xampslimiter.bypass")) {
				e.setCancelled(false);
			} else {
				e.setCancelled(true);
				p.sendMessage(getConfig().getString("LimiteAtingido").replace('&', '§'));
				return;	
			}
		}
		
		placeBlock(p, b);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBreak(BlockBreakEvent e) {
		if (e.isCancelled()) return;
		
		Player p = e.getPlayer();
		Block b = e.getBlock();
		
		if (!(getWG().canBuild(p, b))) return;
		
		breakBlock(p, b);
	}
	 
	private boolean canPlace(Player p, Block b) {
		String hash = getHash(b);
		if (!(limits.containsKey(hash))) return true;
		
		if (!(blocks.containsKey(p.getName())))
			blocks.put(p.getName(), new HashMap<String, Integer>());
		
		if (!(blocks.get(p.getName()).containsKey(hash)))
			blocks.get(p.getName()).put(hash, 0);
		
		int placeds = blocks.get(p.getName()).get(hash);
		int limit = limits.get(hash);
		
		return (placeds + 1) <= limit;
	}
	
	private void placeBlock(Player p, Block b) {
		String hash = getHash(b);
		
		if (!(limits.containsKey(hash))) return;
		
		int placeds = blocks.get(p.getName()).get(hash);
		blocks.get(p.getName()).put(hash, placeds + 1);
	}
	
	private void breakBlock(Player p, Block b) {
		String hash = getHash(b);
		
		if (!(limits.containsKey(hash))) return;
		if (!(blocks.containsKey(p.getName()))) return;
		if (!(blocks.get(p.getName()).containsKey(hash))) return;
		
		int placeds = blocks.get(p.getName()).get(hash);
		blocks.get(p.getName()).put(hash, placeds - 1);
	}
	
	private String getHash(Block b) {
		return b.getTypeId() + ":" + b.getData();
	}
	
	private WorldGuardPlugin getWG() {
		Plugin wg = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if (wg instanceof WorldGuardPlugin)
			return (WorldGuardPlugin) wg;
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		try {
			File f = new File(getDataFolder(), "limit.dat");
			if (f.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				blocks = (HashMap<String, HashMap<String, Integer>>) ois.readObject();
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void save() {
		try {
			File f = new File(getDataFolder(), "limit.dat");
			if (!(f.exists()))
				f.createNewFile();
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(blocks);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
