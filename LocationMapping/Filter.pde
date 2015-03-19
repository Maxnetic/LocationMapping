import java.util.*;
import java.sql.Timestamp;


class Filter{
 
 //Return für Filter 
 TrackpointList filteredtrackpointlist = new TrackpointList();
  
  
  
  /* Dies ist ein Filter, der zwischen einer start und einer Endzeit, gegeben als Timestamps,
  * alle Trackpoints herrausfiltert und eine Trackpointlist ausgibt
  */
  public TrackpointList filterTime(TrackpointList trackpointlist, Timestamp starttime, Timestamp endtime){
   for(Trackpoint tp : trackpointlist){
      if(tp.getTimestamp().compareTo(starttime) >= 0 && tp.getTimestamp().compareTo(endtime) <= 0){
        filteredtrackpointlist.add(tp);
      }
   }
   return filteredtrackpointlist;
  }


  /* Dieser Filter filtert nach Tageszeiten, dh. man gibt ein Intervall in STart und Endstunde an,
  * zwischen denen alle Trackpoints aller Tage rausgefiltert werden.
  */
  public TrackpointList filterTimeOfDay(TrackpointList trackpointlist, int starttime, int endtime){
   for(Trackpoint tp : trackpointlist){
     if(tp.getHour() >= starttime && tp.getHour() <= endtime){
      filteredtrackpointlist.add(tp); 
     } 
   }    
    return(filteredtrackpointlist);
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
