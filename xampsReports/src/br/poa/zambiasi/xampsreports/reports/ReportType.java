package br.poa.zambiasi.xampsreports.reports;

public enum ReportType {
  
  HACK,
  OFENSAS,
  AMEACA,
  ABUSANDO,
  COMBATLOG,
  FORGEHACK;
  
  public String getReadable() {
    return name().toUpperCase().substring(0, 1) + name().toLowerCase().substring(1);
  }
  
  public static ReportType getFromName(String name) {
    for (ReportType type : values()) {
      if (type.name().equalsIgnoreCase(name)) {
        return type;
      }
    }
    
    return null;
  }
}
