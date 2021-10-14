package br.poa.zambiasi.xampslobby.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class Cc implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("xampslobby.cmd.cc")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		for (int i = 0; i < 150; i++) {
			for (Player p2 : Bukkit.getOnlinePlayers()) {
				p2.sendMessage(" ");
			}
		}
		for (Player p2 : Bukkit.getOnlinePlayers()) {
			p2.sendMessage("§a* §7Chat limpo por " + p.getName());
		}
		return false;
	}
}
