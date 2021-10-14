package br.poa.zambiasi.xampsranks;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.devpaulo.legendchat.api.Legendchat;
import br.com.devpaulo.legendchat.channels.ChannelManager;
import br.poa.zambiasi.xampsranks.cmds.Rank;
import br.poa.zambiasi.xampsranks.cmds.Ranks;
import br.poa.zambiasi.xampsranks.cmds.Xp;
import br.poa.zambiasi.xampsranks.cmds.xampsranks;
import br.poa.zambiasi.xampsranks.eventos.Eventos;
import br.poa.zambiasi.xampsranks.utils.Mysql;

public class Main extends JavaPlugin implements Listener {

	public static Plugin pl;
	public static String version;
	
	private static Mysql mysql;
	
	public static Mysql getMysql() {
		return mysql;
	}

	@Override
	public void onEnable() {
		pl = this;
	    @SuppressWarnings("unused")
		ChannelManager channelManager = Legendchat.getChannelManager();

		if (!getDescription().getName().equals("xampsranks")) {
			Bukkit.getServer().getPluginManager().disablePlugin(Main.pl);
			Bukkit.getConsoleSender().sendMessage("§a ");
			Bukkit.getConsoleSender().sendMessage("§c* §7xampsRanks: N§O RENOMEIE O PLUGIN!");
			Bukkit.getConsoleSender().sendMessage("§c* §7desenvolvido por xampS#0443");
			Bukkit.getConsoleSender().sendMessage("§a ");
			return;
		}
		version = getDescription().getVersion();
		
		saveDefaultConfig();

		mysql = new Mysql(pl.getConfig().getString("mysql.database"), pl.getConfig().getString("mysql.host"),
				pl.getConfig().getString("mysql.porta"), pl.getConfig().getString("mysql.senha"),
				pl.getConfig().getString("mysql.user"));

		try {
			mysql.conectar().createStatement().executeUpdate(
					"CREATE TABLE IF NOT EXISTS `ranks` (`nick` varchar(64), `kill` int, `death` int, `xp` int)");
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§a ");
			Bukkit.getConsoleSender().sendMessage("§c* §7xampsRanks: ERRO NO MYSQL!");
			Bukkit.getConsoleSender().sendMessage("§c* §7desenvolvido por xampS#0443");
			Bukkit.getConsoleSender().sendMessage("§a ");
			Bukkit.getServer().getPluginManager().disablePlugin(Main.pl);
			return;
		}
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new Eventos(), this);
		getCommand("ranks").setExecutor(new Ranks());
		getCommand("rank").setExecutor(new Rank());
		getCommand("xampsranks").setExecutor(new xampsranks());
		getCommand("xp").setExecutor(new Xp());
		
		Bukkit.getConsoleSender().sendMessage("§a ");
		Bukkit.getConsoleSender().sendMessage("§a* §7xampsRanks habilitado com sucesso!");
		Bukkit.getConsoleSender().sendMessage("§a* §7desenvolvido por xampS#0443");
		Bukkit.getConsoleSender().sendMessage("§a ");
		

	}

	@EventHandler
	public void loginMSQL(PlayerLoginEvent e) {
		new BukkitRunnable() {

			@Override
			public void run() {
				String nick = e.getPlayer().getName();
				ResultSet rs;
				try {
					rs = Main.getMysql().conectar().createStatement()
							.executeQuery("SELECT * FROM `ranks` WHERE `nick`='" + nick + "';");
					if (!rs.next()) {
						Main.getMysql().conectar().createStatement().executeUpdate(
						"INSERT INTO `ranks` (`nick` , `kill` , `death`, `xp`) VALUES ('" + nick
						+ "', '0', '0', '0');");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e1) {
				}
			}
		}.runTaskAsynchronously(Main.pl);
	}


}
