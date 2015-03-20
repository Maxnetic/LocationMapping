import java.util.*;
import java.sql.Timestamp;

/*
 * Filter Oberklasse
 */

public abstract class Filter{
 
 /* Attribute
 * Die Rückgabeliste, die in den Filtern beschrieben wird
 */

 TrackpointList filteredtpl;
 
 /**
 * Konstruktor fuer Filter
 * @return neues Objekt vom Typ Filter
 */
 public Filter(){
   filteredtpl = new TrackpointList();
 }
 

 abstract TrackpointList apply(TrackpointList trackpointlist);
     
  
  
}
