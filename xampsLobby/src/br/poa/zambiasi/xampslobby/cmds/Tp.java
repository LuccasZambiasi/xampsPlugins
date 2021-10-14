
package br.poa.zambiasi.xampslobby.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class Tp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("xampslobby.cmd.tp")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§e* §7Use /tp <jogador 1> <jogador 2>");
			return true;
		}
		
		if (args.length == 1) {
			Player t = Bukkit.getPlayer(args[0]);
			if (t == null) {
				p.sendMessage(Mensagens.offlinePlayer);
				return true;
			}
			p.teleport(t);
			p.sendMessage("§a* §7Teleportado com sucesso!");
		}
		if(args.length == 2) {
			Player t = Bukkit.getPlayer(args[0]);
			if (t == null) {
				p.sendMessage(Mensagens.offlinePlayer);
				return true;
			}
			Player t2 = Bukkit.getPlayer(args[1]);
			if (t2 == null) {
				p.sendMessage(Mensagens.offlinePlayer);
				return true;
			}
			p.sendMessage("§a* §7Teleportado com sucesso!");
			t.teleport(t2);
		}
		return false;
	}
}
