package br.poa.zambiasi.xampsreports.reports;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

public abstract class CommandBase extends Command {
  
  public CommandBase(String name) {
    super(name);
    SimpleCommandMap commandMap = ((CraftServer) Bukkit.getServer()).getCommandMap();
    commandMap.register(name, "xampsUtils", this);
  }
  
  public static void makeReports() {
    new ReportCommand();
    new ReportsCommand();
  }
}
