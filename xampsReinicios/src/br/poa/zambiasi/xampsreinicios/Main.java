package br.poa.zambiasi.xampsreinicios;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import br.poa.zambiasi.xampsreinicios.cmds.Comandos;
import br.poa.zambiasi.xampsreinicios.utils.ARMkDir;
import br.poa.zambiasi.xampsreinicios.utils.ARReloadConfig;
import br.poa.zambiasi.xampsreinicios.utils.ARYamlLoader;

public class Main extends JavaPlugin {

    public Boolean autoReboot;

    public Boolean running;

    public int rebootTask;
    public int interval;
   
    public void onEnable() {

        running = false;

        generateFiles();
        cache();
        if (autoReboot) {
    		Bukkit.getConsoleSender().sendMessage("§a§l(xampsReinícios) §7§oPlugin habilitado!");
    		Bukkit.getConsoleSender().sendMessage("§a§l(xampsReinícios) §7§oFeito por LuccasZambiasi.");
            Bukkit.getConsoleSender().sendMessage("§a§l(xampsReinícios) §7§oServidor reiniciando em " + secondsToString(interval));
            startCounter();

        } else {
    		Bukkit.getConsoleSender().sendMessage("§a§l(xampsReinícios) §7§oPlugin habilitado!");
    		Bukkit.getConsoleSender().sendMessage("§a§l(xampsReinícios) §7§oFeito por LuccasZambiasi.");
            Bukkit.getConsoleSender().sendMessage("§a§l(xampsReinícios) §7§oAutoRestart desligado na config!");
        }
        registerCommands();
    }

    public void onDisable() {
        Bukkit.getServer().getScheduler().cancelTasks((Plugin) this);
    }

    public void onUnload() {
        Bukkit.getServer().getScheduler().cancelTasks((Plugin) this);
    }

    @SuppressWarnings("deprecation")
    public void startCounter() {

        running = true;

        rebootTask = this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            public void run() {
                interval--;
                if (getConfigC().getStringList("configs.tempo_mensagens").contains(Integer.toString(interval))) {
                    for (Player p: Bukkit.getOnlinePlayers()) {
                            p.sendMessage(getConfigC().getString("mensagens.reiniciando").replaceAll("&", "§").replace("%tempo%", secondsToString(interval)));
                    }
                }

                if (interval == 0) {
                    running = false;
                    runRestartCommands();
                    Bukkit.getScheduler().cancelTask(rebootTask);

                }
            }
        }, 20L, 20L);

    }

    public void runRestartCommands() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (String CMDs: getConfigC().getStringList("configs.comandos")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), CMDs);
                }
            }
        }.runTaskLater(this, 10);
    }

    public void cache() {
        interval = this.getConfigC().getInt("configs.intervalo");
        autoReboot = this.getConfigC().getBoolean("configs.habilitar");
    }

    private void registerCommands() {

        nt = System.nanoTime();
        this.getCommand("xampsreinicios").setExecutor(new Comandos(this));
        nt = System.nanoTime() - nt;
        a = (int) TimeUnit.NANOSECONDS.toMillis(nt);
    }



    public void generateFiles() {
        config = new File(getDataFolder(), "config.yml");
        mkdir.execute();
        loadyaml.loadYamlulus();
    }

    public ARMkDir mkdir = new ARMkDir(this);
    public ARReloadConfig rel = new ARReloadConfig(this);
    private ARYamlLoader loadyaml = new ARYamlLoader(this);


    public File config = null;
    public YamlConfiguration configC = new YamlConfiguration();

    public YamlConfiguration getConfigC() {
        return configC;
    }

    private int a;
    private long nt;


    public static String secondsToString(int seconds) {
        long hoursParsed = TimeUnit.SECONDS.toHours(seconds);
        long minutesParsed = TimeUnit.SECONDS.toMinutes(seconds) - (hoursParsed * 60);
        long secondsParsed = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(seconds) * 60;
        StringBuilder stringBuilder = new StringBuilder();

        if (hoursParsed > 0) {
            stringBuilder.append(hoursParsed).append(" ");

            if (hoursParsed > 1) {
                stringBuilder.append("horas, ");
            } else {
                if (minutesParsed > 0) {
                    stringBuilder.append("hora ");
                } else {
                    stringBuilder.append("hora");
                }

            }
        }

        if (minutesParsed > 0) {
            if (hoursParsed > 1 && secondsParsed == 0) {
                stringBuilder.append("e ");
            }

            stringBuilder.append(minutesParsed).append(" ");

            if (minutesParsed > 1) {
                stringBuilder.append("minutos ");
            } else {
                if (secondsParsed > 0) {
                    stringBuilder.append("minutos ");
                } else {
                    stringBuilder.append("minutos");
                }

            }
        }

        if (secondsParsed > 0) {
            if (minutesParsed > 0 || hoursParsed > 0) {
                stringBuilder.append("e ");
            }

            stringBuilder.append(secondsParsed).append(" ");

            if (secondsParsed > 1) {
                stringBuilder.append("segundos");
            } else {
                stringBuilder.append("segundo");
            }
        }
        String conversion = stringBuilder.toString();

        conversion = conversion.trim();

        if (conversion.endsWith(",")) {
            conversion = conversion.substring(0, conversion.length() - 1);
        }

        return stringBuilder.toString();
    }
}