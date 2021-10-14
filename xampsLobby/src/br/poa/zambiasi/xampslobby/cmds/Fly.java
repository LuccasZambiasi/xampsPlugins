package br.poa.zambiasi.xampslobby.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class Fly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("xampslobby.cmd.fly")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			if (p.getAllowFlight()) {
				p.setAllowFlight(false);
				p.sendMessage("§a* §7Modo voar desabilitado!");
			} else {
				p.setAllowFlight(true);
				p.sendMessage("§a* §7Modo voar habilitado!");
			}
		} else {
			if (!p.hasPermission("kitpvp.cmd.admin")) {
				p.sendMessage(Mensagens.noPerm);
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				p.sendMessage(Mensagens.offlinePlayer);
				return true;
			}
			if (target.getAllowFlight()) {
				target.setAllowFlight(false);
				target.sendMessage("§a* §7Modo voar desabilitado!");
				p.sendMessage("§a* §7Você desabilitou o fly de §a" + target.getName());
			} else {
				target.setAllowFlight(true);
				target.sendMessage("§a* §7Modo voar habilitado!");
				p.sendMessage("§a* §7Você habilitou o fly de §a" + target.getName());
			}
		}
		return false;
	}
}
