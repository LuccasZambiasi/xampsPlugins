package br.poa.zambiasi.xampsreports.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import br.poa.zambiasi.xampsreports.Main;

public class Report {
  
  private UUID uuid;
  private String playerName;
  private long lastReport;
  private Map<ReportType, List<SubReport>> reports = new HashMap<>();
  
  public Report(UUID uuid, String playerName, long lastReport) {
    this.uuid = uuid;
    this.playerName = playerName;
    this.lastReport = lastReport;
  }
  
  public void increaseByType(ReportType type, UUID reporter) {
    List<SubReport> list = reports.get(type);
    if (list == null) {
      list = new ArrayList<>();
    }
    
    list.add(new SubReport(reporter));
    reports.put(type, list);
    this.lastReport = System.currentTimeMillis();
    Main.getInstance().getBackend().saveSubReports(this);
  }
  
  public boolean reject() {
    return Main.getInstance().getBackend().removeReportById(uuid);
  }
  
  public UUID getUniqueId() {
    return uuid;
  }
  
  public String getPlayerName() {
    return playerName;
  }
  
  public long getLastReport() {
    return lastReport;
  }
  
  public int getReportLevel(ReportType type) {
    return reports.containsKey(type) ? reports.get(type).size() : 0;
  }
  
  public boolean hasSubReportById(UUID reporter) {
    for (Entry<ReportType, List<SubReport>> entry : getAsMap().entrySet()) {
      for (SubReport sr : entry.getValue()) {
        if (sr.getReporter().equals(reporter)) {
          return true;
        }
      }
    }
    
    return false;
  }
  
  public Map<ReportType, List<SubReport>> getAsMap() {
    return reports;
  }
}
