import java.util.*;


class Filter {
  public int startHour; // Zeit
  public int endHour;
  public int startDay; // Datum
  public int startMonth;
  public int startYear;
  public int endDay;
  public int endMonth;
  public int endYear;

  //time | service | latitude | longitude
  public Filter() {
    startHour = 0;
    endHour = 24;
    startDay = 1;
    startMonth = 9;
    startYear = 2009;
  }
  
  public TrackpointList filterUhrzeit(TrackpointList trackpoints) {
    TrackpointList filteredTrackpoints = new TrackpointList();
    Calendar calendar = new GregorianCalendar();
    int currTime;
    for (int i=0; i<trackpoints.size(); i++) { // in Trackpoint schon zerlegen#
        calendar.setTime(trackpoints.get(i).time);
        currTime = calendar.get(Calendar.HOUR);        
        if (currTime >= startHour && currTime <= endHour) {
           filteredTrackpoints.add(trackpoints.get(i));
        }  
    }
    return filteredTrackpoints;  
  }
  
  /*
   * Filtere die Trackpoints nach Datum
   * Date = int ?
   */
  public TrackpointList filterDatum(TrackpointList trackpoints) {
    TrackpointList filteredTrackpoints = new TrackpointList();
    Calendar calendar = new GregorianCalendar();
    int currDay;
    int currMonth;
    int currYear;
    for (int i=0; i<trackpoints.size(); i++) { // in Trackpoint schon zerlegen#
        calendar.setTime(trackpoints.get(i).time);
        currDay = calendar.get(Calendar.DATE);
        currMonth = calendar.get(Calendar.MONTH);
        currYear = calendar.get(Calendar.YEAR);
        if (currYear == startYear && currMonth == startMonth){ 
          
          // ----------- currDay liefert IndexOutOfBoundsException, je nach Tag 1-31 bei verschiedenen Index/Size-Werten
          
         // if (currDay == startDay){
            //System.out.println(currYear + " / " + currMonth + " / " + currDay);  // welche Daten wurden genommen
            filteredTrackpoints.add(trackpoints.get(i));
          //}
        }  
    }
    return filteredTrackpoints;  
  }
}


 
