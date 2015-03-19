import java.util.*;
import java.sql.Timestamp;

public abstract class Filter{
 
 /* Attribute
 * Die Rückgabeliste, die in den Filtern beschrieben wird
 */
 TrackpointList filteredtpl;
 
 // Konstruktor
 /**
 * Konstruktor, erzeugt Trackpointliste
 * @return Objekt vom Typ Filter
 */
 public Filter(){
   filteredtpl = new TrackpointList();
 }
 
  /**
  * Methode, zum Filtern
  */
 abstract TrackpointList apply(TrackpointList trackpointlist);
     
  
  
}
