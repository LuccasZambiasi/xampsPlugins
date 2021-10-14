package br.poa.zambiasi.xampslobby.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import br.poa.zambiasi.xampslobby.Eventos;
import br.poa.zambiasi.xampslobby.Main;
import br.poa.zambiasi.xampslobby.cmds.Score;

public class Inventarios {

	public static void resetar(Player p) {
		Eventos.players.put(p, "ativado");
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setItem(4, ItemAPI.Criar(Material.COMPASS, 1, 0, "§bServidores §8(§eClique§8)", false));
		p.getInventory().setItem(3, ItemAPI.Criar(Material.MAGMA_CREAM, 1, 0, "§cDesativar Jogadores §8(§eClique§8)", false));
		p.getInventory().setItem(5, ItemAPI.Criar(Material.NETHER_STAR, 1, 0, "§aHats §8(§eClique§8)", false));
		p.setGameMode(GameMode.ADVENTURE);
		p.setLevel(0);
		p.setFireTicks(0);
		p.setExp(0);
		p.setHealth(20);
		p.setHealthScale(20);
		p.getInventory().setHeldItemSlot(0);
		p.setWalkSpeed(0.2F);
		p.setAllowFlight(false);
		p.setFlying(false);
		if (p.getVehicle() != null) {
			p.getVehicle().eject();
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				upDateScore(p);
			}
		}.runTaskLater(Main.pl, 5);
		for (PotionEffect effect : p.getActivePotionEffects())
			p.removePotionEffect(effect.getType());
	}

	@SuppressWarnings("deprecation")
	public static void upDateScore(Player p) {
		if (Score.player.contains(p.getName()))
			return;
		if (p.getScoreboard() == null || p.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) {
			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective("bj", "bunda");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			// Scroller sc = new Scroller(Main.pl.getConfig().getString("ScoreBoard").replace("&", "§"), 20, 5, '&');
			Scroller sc = new Scroller("§f§lNemesis§5§lMC", 20, 5, '&');
			new BukkitRunnable() {
				@Override
				public void run() {
					obj.setDisplayName(sc.next());
				}
			}.runTaskTimer(Main.pl, 0, 3);
			obj.getScore("                           ").setScore(14);
			obj.getScore("§fPlayer §8» §b").setScore(13);
			obj.getScore("§fGrupo §8» §b").setScore(11);
			obj.getScore(" ").setScore(10);
			obj.getScore("§fRank §8» §b").setScore(9);
			obj.getScore("§fXp §8» §b0").setScore(8);
			obj.getScore("§fMoney §8» §b").setScore(7);
			obj.getScore("").setScore(6);
			obj.getScore("§fLobby §8» §b1").setScore(5);
			obj.getScore("§fOnline §8» §b").setScore(4);
			obj.getScore("   ").setScore(3);
			obj.getScore("§7" + Main.pl.getConfig().getString("Gerais.Discord")).setScore(2);
			board.registerNewTeam("p").addPlayer(new FastOfflinePlayer("§fPlayer §8» §b"));
			board.registerNewTeam("g").addPlayer(new FastOfflinePlayer("§fGrupo §8» §b"));
			board.registerNewTeam("r").addPlayer(new FastOfflinePlayer("§fRank §8» §b"));
			board.registerNewTeam("x").addPlayer(new FastOfflinePlayer("§fXp §8» §b"));
			board.registerNewTeam("m").addPlayer(new FastOfflinePlayer("§fMoney §8» §b"));
			board.registerNewTeam("o").addPlayer(new FastOfflinePlayer("§fOnline §8» §b"));
			p.setScoreboard(board);
		}
		try {
			ResultSet rs;
			rs = Main.getMysql().conectar().createStatement()
					.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + p.getName() + "';");
			if (rs.next()) {
				p.getScoreboard().getTeam("p").setSuffix(String.valueOf(p.getName()));
				p.getScoreboard().getTeam("g").setSuffix(grupo(p));
				p.getScoreboard().getTeam("r").setSuffix(String.valueOf(rank(rs.getInt("xp"))));
				p.getScoreboard().getTeam("x").setSuffix(String.valueOf(rs.getInt("xp")));
				p.getScoreboard().getTeam("m").setSuffix(String.valueOf(rs.getInt("money")));
				p.getScoreboard().getTeam("o").setSuffix(
						Main.pl.getServer().getOnlinePlayers().size() + "/" + Main.pl.getServer().getMaxPlayers());
				rs.getStatement().getConnection().close();
			}
			rs.getStatement().getConnection().close();

		} catch (SQLException e) {
		}

	}

	public static String rankNick(int i) {
		if (i <= 1000) {
			return "§6-";
		} else if (i <= 2500) {
			return "§6☰";
		} else if (i <= 4000) {
			return "§6☱";
		} else if (i <= 5500) {
			return "§6☲";
		} else if (i <= 7000) {
			return "§8✷";
		} else if (i <= 8500) {
			return "§8✻";
		} else if (i <= 10000) {
			return "§8✭";
		} else if (i <= 11500) {
			return "§8✵";
		} else if (i <= 13000) {
			return "§e⭙";
		} else if (i <= 14500) {
			return "§eஃ";
		} else if (i <= 16000) {
			return "§e✪";
		} else if (i <= 17500) {
			return "§e❆";
		} else {
			return "§b✪";
		}
	}
	
	public static String grupo(Player p) {
		if (p.getName().equalsIgnoreCase("kamibr")) {
			return "§3§lDev";
		} else if (p.hasPermission("xampslobby.tag.fundador")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.fundador"));
		} else if (p.hasPermission("xampslobby.tag.diretor")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.diretor"));
		} else if (p.hasPermission("xampslobby.tag.admin")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.admin"));
		} else if (p.hasPermission("xampslobby.tag.modplus")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.modplus"));
		} else if (p.hasPermission("xampslobby.tag.modgc")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.modgc"));
		} else if (p.hasPermission("xampslobby.tag.mod")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.mod"));
		} else if (p.hasPermission("xampslobby.tag.trial")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.trial"));
		} else if (p.hasPermission("xampslobby.tag.helper")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.helper"));
		} else if (p.hasPermission("xampslobby.tag.builder")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.builder"));
		} else if (p.hasPermission("xampslobby.tag.ruby")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.ruby"));
		} else if (p.hasPermission("xampslobby.tag.sapphire")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.sapphire"));
		} else if (p.hasPermission("xampslobby.tag.streamer")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.streamer"));
		} else if (p.hasPermission("xampslobby.tag.youtuberplus")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.youtuberplus"));
		} else if (p.hasPermission("xampslobby.tag.youtuber")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.youtuber"));
		} else if (p.hasPermission("xampslobby.tag.miniyt")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.miniyt"));
		} else if (p.hasPermission("xampslobby.tag.beta")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.beta"));
		} else if (p.hasPermission("xampslobby.tag.alpha")) {
			return Mensagens.cor(Main.pl.getConfig().getString("tags.alpha"));
		} else if (p.hasPermission("xampslobby.tag.normal")) {
			return "§7Normal";
		} else {
			return "§7Normal";
		}
	}

	public static String rank(int i) {
		if (i <= 1000) {
			return Main.cfg_rank.getString("ranks.rank1");
		} else if (i <= 2500) {
			return Main.cfg_rank.getString("ranks.rank2");
		} else if (i <= 4000) {
			return Main.cfg_rank.getString("ranks.rank3");
		} else if (i <= 5500) {
			return Main.cfg_rank.getString("ranks.rank4");
		} else if (i <= 7000) {
			return Main.cfg_rank.getString("ranks.rank5");
		} else if (i <= 8500) {
			return Main.cfg_rank.getString("ranks.rank6");
		} else if (i <= 10000) {
			return Main.cfg_rank.getString("ranks.rank7");
		} else if (i <= 11500) {
			return Main.cfg_rank.getString("ranks.rank8");
		} else if (i <= 13000) {
			return Main.cfg_rank.getString("ranks.rank9");
		} else if (i <= 14500) {
			return Main.cfg_rank.getString("ranks.rank10");
		} else if (i <= 16000) {
			return Main.cfg_rank.getString("ranks.rank11");
		} else if (i <= 17500) {
			return Main.cfg_rank.getString("ranks.rank12");
		} else {
			return Main.cfg_rank.getString("ranks.rank13");
		}
	}
}
