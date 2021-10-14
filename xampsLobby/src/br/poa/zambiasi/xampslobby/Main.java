package br.poa.zambiasi.xampslobby;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.nametagedit.plugin.NametagEdit;

import br.poa.zambiasi.xampslobby.cmds.Build;
import br.poa.zambiasi.xampslobby.cmds.Cc;
import br.poa.zambiasi.xampslobby.cmds.Chat;
import br.poa.zambiasi.xampslobby.cmds.Checar;
import br.poa.zambiasi.xampslobby.cmds.Discord;
import br.poa.zambiasi.xampslobby.cmds.Fly;
import br.poa.zambiasi.xampslobby.cmds.Gm;
import br.poa.zambiasi.xampslobby.cmds.InvSee;
import br.poa.zambiasi.xampslobby.cmds.Lag;
import br.poa.zambiasi.xampslobby.cmds.Score;
import br.poa.zambiasi.xampslobby.cmds.Tag;
import br.poa.zambiasi.xampslobby.cmds.Tell;
import br.poa.zambiasi.xampslobby.cmds.Tp;
import br.poa.zambiasi.xampslobby.cmds.TpAll;
import br.poa.zambiasi.xampslobby.cmds.TpHere;
import br.poa.zambiasi.xampslobby.utils.Mysql;

public class Main extends JavaPlugin implements Listener {

	public static Plugin pl;
	public static String version;

	public static File file_rank = new File("plugins/xampslobby", "rank.yml");
	public static FileConfiguration cfg_rank;
	

	private static Mysql mysql;

	public static Mysql getMysql() {
		return mysql;
	}

	@Override
	public void onEnable() {
		pl = this;

		if (!getDescription().getName().equals("xampslobby")) {
			Bukkit.getServer().getPluginManager().disablePlugin(Main.pl);
			Bukkit.getConsoleSender().sendMessage("§a ");
			Bukkit.getConsoleSender().sendMessage("§c* §7xampsLobby: NÃO RENOMEIE O PLUGIN!");
			Bukkit.getConsoleSender().sendMessage("§c* §7desenvolvido por xampS#0443");
			Bukkit.getConsoleSender().sendMessage("§a ");
			return;
		}
		version = getDescription().getVersion();

		saveDefaultConfig();
		if (!file_rank .exists()) {
			saveResource("rank.yml", false);
		}

		try {
			cfg_rank.load(file_rank);
		} catch (IOException | InvalidConfigurationException e1) {
			System.out.println(e1.getMessage());
		}

		mysql = new Mysql(pl.getConfig().getString("mysql.database"), pl.getConfig().getString("mysql.host"),
				pl.getConfig().getString("mysql.porta"), pl.getConfig().getString("mysql.senha"),
				pl.getConfig().getString("mysql.user"));

		try {
			mysql.conectar().createStatement().executeUpdate(
					"CREATE TABLE IF NOT EXISTS `pvp` (`nick` varchar(64), `kill` int, `death` int, `money` int, `xp` int, `click` int)");
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§a ");
			Bukkit.getConsoleSender().sendMessage("§c* §7xampsLobby: ERRO NO MYSQL!");
			Bukkit.getConsoleSender().sendMessage("§c* §7desenvolvido por xampS#0443");
			Bukkit.getConsoleSender().sendMessage("§a ");
			Bukkit.getServer().getPluginManager().disablePlugin(Main.pl);
			return;
		}
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new Eventos(), this);
		getServer().getPluginManager().registerEvents(new Score(), this);
		getServer().getPluginManager().registerEvents(new Tag(), this);
		getServer().getPluginManager().registerEvents(new Build(), this);
		getCommand("score").setExecutor(new Score());
		getCommand("tag").setExecutor(new Tag());
		getCommand("chat").setExecutor(new Chat());
		getCommand("build").setExecutor(new Build());
		getCommand("checar").setExecutor(new Checar());
		getCommand("lag").setExecutor(new Lag());
		getCommand("cc").setExecutor(new Cc());
		getCommand("chat").setExecutor(new Chat());
		getCommand("gm").setExecutor(new Gm());
		getCommand("tell").setExecutor(new Tell());
		getCommand("invsee").setExecutor(new InvSee());
		getCommand("tpall").setExecutor(new TpAll());
		getCommand("discord").setExecutor(new Discord());
		getCommand("fly").setExecutor(new Fly());
		getCommand("tp").setExecutor(new Tp());
		getCommand("tphere").setExecutor(new TpHere());
		
		Bukkit.getConsoleSender().sendMessage("§a ");
		Bukkit.getConsoleSender().sendMessage("§a* §7xampsLobby habilitado com sucesso!");
		Bukkit.getConsoleSender().sendMessage("§a* §7desenvolvido por xampS#0443");
		Bukkit.getConsoleSender().sendMessage("§a ");
		
		new BukkitRunnable() {
			@Override
			public void run() {
				getServer().getWorld("world").setStorm(false);
			}
		}.runTaskTimerAsynchronously(this, 120, 120);

		if (pl.getConfig().getBoolean("automsg.habilitar")) {
			String asd = removeUltimoChar(pl.getConfig().getStringList("automsg.msgs").toString().replace("&", "§").replace("%discord%", pl.getConfig().getString("Gerais.Discord")));
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				String[] items = asd.split(",");

				@Override
				public void run() {
					Bukkit.getServer()
							.broadcastMessage(removeFirstChar(items[new java.util.Random().nextInt(items.length)]));

				}
			}, 0L, pl.getConfig().getInt("automsg.delay") * 20L);
		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void loginMSQL(PlayerLoginEvent e) {
		new BukkitRunnable() {

			@Override
			public void run() {
				String nick = e.getPlayer().getName();
				ResultSet rs;
				try {
					rs = Main.getMysql().conectar().createStatement()
							.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + nick + "';");
					if (!rs.next()) {
						Main.getMysql().conectar().createStatement().executeUpdate(
								"INSERT INTO `pvp` (`nick` , `kill` , `death` , `money`, `xp`, `click`) VALUES ('" + nick
										+ "', '0', '0', '0', '0', '0');");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e1) {
					e1.printStackTrace();
					Bukkit.getConsoleSender().sendMessage("§aERRO");
					Bukkit.getConsoleSender().sendMessage("§aERRO");
					Bukkit.getConsoleSender().sendMessage("§aERRO");
					Bukkit.getConsoleSender().sendMessage("§aERRO");
					Bukkit.getConsoleSender().sendMessage("§aERRO");
					Bukkit.getConsoleSender().sendMessage("§aERRO");
					Bukkit.getConsoleSender().sendMessage("§aERRO");
					Bukkit.getConsoleSender().sendMessage("§aERRO");
					Bukkit.getConsoleSender().sendMessage("§aERRO");

				}
			}
		}.runTaskAsynchronously(Main.pl);
	}
	
	static {
		cfg_rank = YamlConfiguration.loadConfiguration(Main.file_rank);
	}
	
	

	public static String removeFirstChar(String s) {
		return s.substring(1);
	}

	public static String removeUltimoChar(String s) {
		int tamanho = s.length();
		s = s.substring(0, tamanho - 1);
		return s;
	}

	public static void setarTag(Player p, String tag) {
		new BukkitRunnable() {

			@Override
			public void run() {
				p.setDisplayName(tag + p.getName() + "§r");
				NametagEdit.getApi().setPrefix(p, tag);
			}
		}.runTaskAsynchronously(pl);
	}
	


}
