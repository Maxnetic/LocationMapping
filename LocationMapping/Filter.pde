import java.util.*;
import java.sql.Timestamp;


class Filter{
 
 //Return für Filter 
 TrackpointList filteredtrackpointlist = new TrackpointList();
  
  
  
  // Filter für die Zeit in Stunden, die Funktion bekommt eine Start und Endzeit
  public TrackpointList filterTime(TrackpointList trackpointlist, Timestamp starttime, Timestamp endtime){
   for(Trackpoint tp : trackpointlist){
      if(tp.getTimestamp().compareTo(starttime) >= 0 && tp.getTimestamp().compareTo(endtime) <= 0){
        filteredtrackpointlist.add(tp);
      }
   }
   return filteredtrackpointlist;
  }

  
  /*
   * Filter für einen bestimmten Radius
   * @param location
   * @param radius in km
   */
   public TrackpointList filterRadius(TrackpointList trackpointlist, Location location, int radius){
     
     for( Trackpoint tp : trackpointlist ){       
       if (tp.locationDistanceTo(location) <= radius)
         filteredtrackpointlist.add(tp);
     }
     return filteredtrackpointlist;
   }
  
  
}
