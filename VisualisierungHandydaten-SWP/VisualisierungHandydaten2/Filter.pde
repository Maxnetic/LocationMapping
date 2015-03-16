import java.util.*;


class Filter {
  public int startHour; // Zeit
  public int endHour;
  public int startDay; // Datum
  public int endDay;

  //time | service | latitude | longitude
  public Filter() {
    startHour=0;
    endHour=24;
  }
  
  public TrackpointList filter(TrackpointList trackpoints) {
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
}


 
