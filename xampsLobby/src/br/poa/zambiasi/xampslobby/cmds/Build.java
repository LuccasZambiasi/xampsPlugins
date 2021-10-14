package br.poa.zambiasi.xampslobby.cmds;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class Build implements CommandExecutor, Listener {

	static ArrayList<String> player = new ArrayList<>();

	@EventHandler
	void sair(PlayerQuitEvent e) {
		if (player.contains(e.getPlayer().getName())) {
			player.remove(e.getPlayer().getName());
		}
	}

	@EventHandler
	void kick(PlayerKickEvent e) {
		if (player.contains(e.getPlayer().getName())) {
			player.remove(e.getPlayer().getName());
		}
	}

	@EventHandler
	void block(BlockPlaceEvent e) {
		if (player.contains(e.getPlayer().getName()))
			return;
		e.setCancelled(true);
	}

	@EventHandler
	void block(BlockBreakEvent e) {
		if (player.contains(e.getPlayer().getName()))
			return;
		e.setCancelled(true);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("xampslobby.cmd.build")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}

		if (player.contains(p.getName())) {
			player.remove(p.getName());
			p.sendMessage("§a* §7Você saiu do modo de construção.");
		} else {
			player.add(p.getName());
			p.sendMessage("§a* §7Você entrou no modo de construção.");
		}

		return false;
	}
}
