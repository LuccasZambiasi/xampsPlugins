package br.poa.zambiasi.xampslobby.cmds;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampslobby.Main;
import br.poa.zambiasi.xampslobby.utils.Inventarios;
import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class Checar implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("xampslobby.cmd.checar")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§e* §7Use /checar <jogador>");
			return true;
		}
		Player checado = Bukkit.getPlayer(args[0]);
		if (checado == null) {
			p.sendMessage("§c* §7Jogador offline!");
			return true;
		}
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
		p.sendMessage("§a");
		p.sendMessage("§aJogador: §7" + checado.getName());
		p.sendMessage("§aGamemode:§7 " + checado.getGameMode());
		p.sendMessage("§aVida:§7 " + checado.getHealth());
		p.sendMessage("§aLocalização:§7 X:" + checado.getLocation().getBlockX() + " Y:" + checado.getLocation().getBlockY()
				+ " Z:" + checado.getLocation().getBlockZ());
		p.sendMessage("§aIp:§7 " + checado.getAddress().getHostString());
		
		try {
			ResultSet rs;
			rs = Main.getMysql().conectar().createStatement()
					.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + checado.getName() + "';");
			if (rs.next()) {
				p.sendMessage("§a");
				p.sendMessage("§aAbates:§7 " + rs.getInt("kill"));
				p.sendMessage("§aMortes:§7 " + rs.getInt("death"));
				p.sendMessage("§aXp:§7 " + rs.getInt("xp"));
				p.sendMessage("§aRank:§7 " + Inventarios.rank(rs.getInt("xp")));
				p.sendMessage("§a");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
		}
		return false;
	}
}
