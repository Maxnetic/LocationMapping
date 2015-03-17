import java.util.*;

class Filter {
  public int startHour; // Zeit
  public int endHour;
  public String service; // GPS, Telefonie oder SMS

  //time | service | latitude | longitude
  public Filter() {
    startHour=0;
    endHour=24;
  }
  
  public TrackpointList filterZeit(TrackpointList trackpoints) {
    TrackpointList filteredTrackpoints = new TrackpointList();
    Calendar calendar = new GregorianCalendar();
    int currTime;
    for (int i=0; i<trackpoints.size(); i++) { // in Trackpoint schon zerlegen
        calendar.setTime(trackpoints.get(i).time);
        currTime = calendar.get(Calendar.HOUR);
        if (currTime >= startHour && currTime <= endHour) {
           filteredTrackpoints.add(trackpoints.get(i));
        }  
    }
    return filteredTrackpoints;
  }
  
  
  //Diese Routine dient dazu die Liste nach einem Service zu filtern
  public TrackpointList filterService(TrackpointList trackpoints){
    TrackpointList filteredTrackpoints = new TrackpointList();
    for (int i=0; i<trackpoints.size(); i++) {
      if (trackpoints.get(i).service.compareTo(service) == 0){ // Vergleich der Strings mit Service
        filteredTrackpoints.add(trackpoints.get(i)); 
      }
    }
    return filteredTrackpoints;
  }
}


 
