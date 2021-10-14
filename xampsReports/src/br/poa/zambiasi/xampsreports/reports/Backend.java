package br.poa.zambiasi.xampsreports.reports;

import java.util.Collection;
import java.util.UUID;

public interface Backend {
  
  public void saveSubReports(Report report);

  public Report createReport(UUID uuid, String playerName);
  
  public boolean removeReportById(UUID uuid);
  
  public Report getReportById(UUID uuid);
  
  public Collection<Report> listReports();
}
