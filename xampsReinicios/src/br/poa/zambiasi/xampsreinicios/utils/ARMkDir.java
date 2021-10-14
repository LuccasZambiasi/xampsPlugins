package br.poa.zambiasi.xampsreinicios.utils;

import br.poa.zambiasi.xampsreinicios.Main;

public class ARMkDir {

	private Main main;

	public ARMkDir(Main main) {
		this.main = main;
	}

	public void execute() {
		if (!main.config.exists()) {
			main.saveResource("config.yml", false);
		}
	}

}
