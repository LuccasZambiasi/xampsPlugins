package br.poa.zambiasi.xampslobby.cmds;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;

import br.poa.zambiasi.xampslobby.utils.Inventarios;
import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class Score implements CommandExecutor, Listener {

	public static ArrayList<String> player = new ArrayList<>();

	@EventHandler
	void sair(PlayerQuitEvent e) {
		if (player.contains(e.getPlayer().getName())) {
			player.remove(e.getPlayer().getName());
		}
	}

	@EventHandler
	void kick(PlayerQuitEvent e) {
		if (player.contains(e.getPlayer().getName())) {
			player.remove(e.getPlayer().getName());
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;

		if (player.contains(p.getName())) {
			player.remove(p.getName());
			p.sendMessage("§a* §7Você habilitou a scoreboad!");
			Inventarios.upDateScore(p);
		} else {
			player.add(p.getName());
			p.sendMessage("§a* §7Você desabilitou a scoreboad!");
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		}

		return false;
	}
}
