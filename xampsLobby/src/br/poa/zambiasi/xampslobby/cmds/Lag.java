package br.poa.zambiasi.xampslobby.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.poa.zambiasi.xampslobby.Main;
import br.poa.zambiasi.xampslobby.utils.Mensagens;

public class Lag implements CommandExecutor {


	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("xampslobby.cmd.lag")) {
			sender.sendMessage(Mensagens.noPerm);
			return true;
		}
		double tps = br.poa.zambiasi.xampslobby.utils.Lag.getTPS();
		double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
		sender.sendMessage("§a* §7O TPS atual é §a" + tps + "§7 tps.");
		sender.sendMessage("§a* §7Aproximadamente §a" + lag + "%§7 de lag!");
		sender.sendMessage("§a");
		sender.sendMessage("§a* §7Versão do xampslobby em uso §a" + Main.version + "§7!");
		return false;
	}
}
