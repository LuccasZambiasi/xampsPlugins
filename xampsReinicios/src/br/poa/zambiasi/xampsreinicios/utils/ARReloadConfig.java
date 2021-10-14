package br.poa.zambiasi.xampsreinicios.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import br.poa.zambiasi.xampsreinicios.Main;

public class ARReloadConfig {

	private Main main;

	public ARReloadConfig(Main main) {
		this.main = main;
	}

	public void reloadConfigC() {
		main.configC = YamlConfiguration.loadConfiguration(main.config);
	}

}
