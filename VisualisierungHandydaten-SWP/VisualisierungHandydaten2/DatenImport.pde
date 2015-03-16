import java.util.*;

class DatenImport {
  public TrackpointList ladeStandardCSV(String file) {
  trackdata = loadStrings(file);
  TrackpointList trackpoints = new TrackpointList();
  for (int i = 0; i < trackdata.length; i++) {
    String[] pieces = split(trackdata[i], ";"); // jede Zeile in Array laden
    //time | service | latitude | longitude
    // neuen Trackpoint erzeugen
    Trackpoint t = new Trackpoint (pieces);
    trackpoints.add(t);
  }
  return trackpoints;
 }  
}
