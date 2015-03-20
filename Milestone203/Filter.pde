import java.util.*;
import java.sql.Timestamp;


public abstract class Filter{
 
 /* Attribute
 * Die Rückgabeliste, die in den Filtern beschrieben wird
 */

 
 TrackpointList filteredtpl;
 
 // Konstruktor
 public Filter(){
   filteredtpl = new TrackpointList();
 }
 
  
 abstract TrackpointList apply(TrackpointList trackpointlist);
     
  
  
}
