package br.poa.zambiasi.xampsreinicios.cmds;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import br.poa.zambiasi.xampsreinicios.Main;

public class Comandos implements CommandExecutor {

    private Main main;

    public Comandos(Main main) {
        this.main = main;
    }

    private int a;
    private long nt;

    String placeholder;

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("xampsreinicios")) {
            if (args.length == 0) {
                if (sender.hasPermission("xampsreinicios.admin")) {
                    sender.sendMessage("");
                    sender.sendMessage("§a* §7/xampsreinicios reiniciar (tempo)");
                    sender.sendMessage("§a* §7/xampsreinicios reload");
                    sender.sendMessage("§a* §7/xampsreinicios cancelar");
                    sender.sendMessage("§a* §7/xampsreinicios tempo");
                    sender.sendMessage("§a* §7/xampsreinicios creditos");
                    sender.sendMessage("");
                } else {
                    if (main.running) {
                        placeholder = main.secondsToString(main.interval);
                    } else {
                        placeholder = "§4§lERRO";
                    }
                    sender.sendMessage(main.getConfigC().getString("mensagens.reiniciando").replace("%tempo%", placeholder).replaceAll("&", "§"));
                }
                
            } else if (args.length == 1) {
            	if (args[0].equalsIgnoreCase("creditos")) {
                    if (sender.hasPermission("xampsreinicios.admin")) {
                            sender.sendMessage("§9* §7Plugin desenvolvido por §9xampS#0443§7 para §9+Rammy#1524§7.");
                    } else {
                        sender.sendMessage("§c* §7Você não tem permissão para isso.");
                    }
                    
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("xampsreinicios.admin")) {
                        nt = System.nanoTime();
                        main.rel.reloadConfigC();
                        nt = System.nanoTime() - nt;
                        a = (int) TimeUnit.NANOSECONDS.toMillis(nt);
                            sender.sendMessage("§a* §7Plugin recarregado com sucesso!");
                    } else {
                        sender.sendMessage("§c* §7Você não tem permissão para isso.");
                    }
                    
                } else if (args[0].equalsIgnoreCase("reiniciar")) {
                    if (sender.hasPermission("xampsreinicios.admin")) {
                            sender.sendMessage("§c* §7Use: /xampsreinicios reiniciar (tempo)");
                    } else {
                        sender.sendMessage("§c* §7Você não tem permissão para isso.");
                    }
                } else if (args[0].equalsIgnoreCase("cancelar")) {
                    if (sender.hasPermission("xampsreinicios.admin")) {
                        if (main.autoReboot) {
                            sender.sendMessage("§a* §7Reinício automático cancelado!");
                            Bukkit.getScheduler().cancelTask(main.rebootTask);
                        } else {
                            sender.sendMessage("§c* §7A opção de auto reinicio está desligada!");
                        }
                    } else {
                        sender.sendMessage("§c* §7Você não tem permissão para isso.");

                    }

                } else if (args[0].equalsIgnoreCase("tempo")) {
                    if (sender.hasPermission("xampsreinicios.admin")) {
                        if (main.running) {
                            placeholder = main.secondsToString(main.interval);
                        } else {
                            placeholder = "§c§lERRO";
                        }
                           sender.sendMessage("§a* §7O servidor irá reiniciar em §a" + placeholder + "§7.");
                    }
                }

            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("reiniciar")) {
                    if (sender.hasPermission("xampsreinicios.admin")) {
                        if (isInt(args[1])) {
                            int forcedInterval = Integer.parseInt(args[1]);
                            if (forcedInterval > 0) {
                            	sender.sendMessage("§a* §7Você forçou o reinício em §a" + args[1] + " §7segundos.");
                                if (!main.autoReboot) {
                                    main.interval = forcedInterval;
                                    main.startCounter();
                                } else {
                                   main.interval = forcedInterval;
                                   main.startCounter();
                                }
                            } else {
                                sender.sendMessage("§c* §7Intervalo muito pequeno! Use pelo menos 1.");
                            }
                        } else {
                            sender.sendMessage("§c* §7O algarismo §c(§7" + args[1] + "§c)§7 não é um número!");
                        }
                    } else {
                        sender.sendMessage("§c* §7Você não tem permissão para isso.");
                    }
                }
            }
            
        }
        return false;
    }

}