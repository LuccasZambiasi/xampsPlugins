package br.poa.zambiasi.xampslobby.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class TpAll implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("xampslobby.cmd.tpall")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		for(Player p2:Bukkit.getOnlinePlayers()) {
			p2.teleport(p.getLocation());
			p2.sendMessage("§a* §7O staffer §a" + p.getName() + "§7 puxou todos jogadores!");
			p.sendMessage("§a* §7Você teleportou todos jogadores até sua localização!");
		}
		return false;
	}
}