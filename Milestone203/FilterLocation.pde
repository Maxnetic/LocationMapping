/*
 * Filter Trackpoints nach Ort mit Radius
 */

class FilterLocation extends Filter{
  
  /*Attribute
  * radius gibt den umkreis an, in dem gefiltert wird
  * location gibt den Mittelpunkt an, um den gefiltert werden soll
  */
  
 int radius;

 Location location;
 
/*
 * Konstruktor
 */
 public FilterLocation(){
     super();
 }
 
 /* Setzt den Radius
 * @param radius [int]: setzt den Radius, um den gefiltert wird
 */
 public void setRadius(int radius){
    this.radius = radius;
  }
  
/* Setzt die Location
 * @param location [Location]: setzt den Ort, um den gefiltert wird
 */ 
  public void setLocation(Location location){
    this.location = location;
  }
  
  /*
   * Filter für einen bestimmten Radius
   * @param trackpointlist [TrackpointList]: die Liste, die gefiltert werden soll
   */
   public TrackpointList apply(TrackpointList trackpointlist){
     
     for( Trackpoint tp : trackpointlist ){       
       if (tp.locationDistanceTo(location) <= radius)
         filteredtpl.add(tp);
     }
     return filteredtpl;
   }
   
 
  
  
}
