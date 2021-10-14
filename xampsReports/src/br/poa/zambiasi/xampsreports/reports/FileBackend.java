package br.poa.zambiasi.xampsreports.reports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.collect.ImmutableList;

import br.poa.zambiasi.xampsreports.Main;



public class FileBackend implements Backend {

  private File file;
  private YamlConfiguration configuration;

  public FileBackend() {
    this.file = new File("plugins/xampsUtils/reports.yml");
    if (!file.exists()) {
      file.getParentFile().mkdirs();
      try {
        file.createNewFile();
      } catch (IOException ex) {
        throw new RuntimeException("Falha ao criar arquivo reports.yml: ", ex);
      }
    }

    this.configuration = YamlConfiguration.loadConfiguration(file);
    if (!configuration.contains("reports")) {
      configuration.createSection("reports");
      save();
    }
    
    ConfigurationSection section = configuration.getConfigurationSection("reports");
    for (String uuidString : section.getKeys(false)) {
      UUID uuid = UUID.fromString(uuidString);
      
      Report report = new Report(uuid, section.getString(uuidString + ".playerName"), section.getLong(uuidString + ".lastReported"));
      for (String serializedSubReport : section.getStringList(uuidString + ".reportTypes")) {
        ReportType type = ReportType.valueOf(serializedSubReport.split(" : ")[0]);
        SubReport subreport = new SubReport(UUID.fromString(serializedSubReport.split(" : ")[1]));
        List<SubReport> list = report.getAsMap().get(type);
        if (list == null) {
          list = new ArrayList<>();
        }
        
        list.add(subreport);
        report.getAsMap().put(type, list);
      }
      
      reports.put(uuid, report);
    }
  }
  
  private void save() {
    try {
      configuration.save(file);
    } catch (IOException ex) {
      // supostamente não ocorre nunca..
      Main.LOGGER.log(Level.WARNING, "Falha ao salvar config reports.yml: ", ex);
    }
  }
  
  private Map<UUID, Report> reports = new HashMap<>();

  @Override
  public void saveSubReports(Report report) {
    UUID uuid = report.getUniqueId();
    
    ConfigurationSection section = configuration.createSection("reports." + uuid.toString());
    if (section != null) {
      List<String> list = new ArrayList<>();
      for (Entry<ReportType, List<SubReport>> entry : report.getAsMap().entrySet()) {
        for (SubReport sr : entry.getValue()) {
          list.add(entry.getKey().name() + " : " + sr.getReporter().toString());
        }
      }
      
      section.set("playerName", report.getPlayerName());
      section.set("lastReported", report.getLastReport());
      section.set("reportTypes", list);
      save();
    }
  }
  
  @Override
  public Report createReport(UUID uuid, String playerName) {
    Report report = new Report(uuid, playerName, System.currentTimeMillis());
    ConfigurationSection section = configuration.createSection("reports." + uuid.toString());
    section.set("playerName", playerName);
    section.set("lastReported", report.getLastReport());
    section.set("reportTypes", new ArrayList<>());
    save();
    reports.put(uuid, report);
    return report;
  }

  @Override
  public boolean removeReportById(UUID uuid) {
    Report report = reports.get(uuid);
    if (report != null) {
      configuration.set("reports." + uuid.toString(), null);
      save();
    }
    
    reports.remove(uuid);
    return report != null;
  }

  @Override
  public Report getReportById(UUID uuid) {
    return reports.get(uuid) != null ? reports.get(uuid) : tryLoadingFromConfig(uuid);
  }
  
  @Override
  public Collection<Report> listReports() {
    return ImmutableList.copyOf(reports.values());
  }
  
  private Report tryLoadingFromConfig(UUID uuid) {
    Report report = null;
    ConfigurationSection section = configuration.getConfigurationSection("reports." + uuid.toString());
    if (section != null) {
      report = new Report(uuid, section.getString("playerName"), section.getLong("lastReported"));
      for (String serializedSubReport : section.getStringList("reportTypes")) {
        ReportType type = ReportType.valueOf(serializedSubReport.split(" : ")[0]);
        SubReport subreport = new SubReport(UUID.fromString(serializedSubReport.split(" : ")[1]));
        List<SubReport> list = report.getAsMap().get(type);
        if (list == null) {
          list = new ArrayList<>();
        }
        
        list.add(subreport);
        report.getAsMap().put(type, list);
      }
      
      reports.put(uuid, report);
    }
    
    return report;
  }
}
