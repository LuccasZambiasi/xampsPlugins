package br.poa.zambiasi.xampsreports.reports;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class ModuleLogger extends Logger {
  
  private String prefix;
  
  public ModuleLogger(String modulePath) {
    this(Bukkit.getLogger(), "[" + modulePath + "] ");
  }
  
  private ModuleLogger(Logger logger, String modulePath) {
    super(modulePath, null);
    this.setParent(logger);
    this.setLevel(Level.ALL);
    this.prefix = modulePath;
  }
  
  @Override
  public void log(LogRecord record) {
    record.setMessage(prefix + record.getMessage());
    super.log(record);
  }
  
  public ModuleLogger getModule(String modulePath) {
    String prefix = this.prefix + "[" + modulePath + "] ";
    return new ModuleLogger(this.getParent(), prefix);
  }
}
