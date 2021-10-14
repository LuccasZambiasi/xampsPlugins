
package br.poa.zambiasi.xampslobby.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class TpHere implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("xampslobby.cmd.tphere")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§e* §7Use /tphere <jogador>");
			return true;
		}
		
		if (args.length == 1) {
			Player t = Bukkit.getPlayer(args[0]);
			if (t == null) {
				p.sendMessage(Mensagens.offlinePlayer);
				return true;
			}
			t.teleport(p);
			p.sendMessage("§a* §7Jogador §a" + t.getName() + "§7 foi teleportado com sucesso!");
		}
		return false;
	}
}
