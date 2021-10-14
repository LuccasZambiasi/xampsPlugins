package br.poa.zambiasi.xampsreports.reports;

import java.util.UUID;

public class SubReport {
  
  private UUID reporter;
  
  public SubReport(UUID reporter) {
    this.reporter = reporter;
  }
  
  public UUID getReporter() {
    return reporter;
  }
}
