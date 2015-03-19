
class FilterLocation extends Filter{
  
  //Attribute
  /**
  * Ort, um den herum gefiltert werden soll
  */
  Location location;
  /**
  * Radius, mit dem gefiltert werden soll
  */
 int radius;

 
   // Konstruktor
  /** 
  * Konstruktor, ruft Konstruktor der Oberklasse auf
  * @return neues Objekt vom Typ FilterLocation
  */
 public FilterLocation(){
     super();
 }
 
 // Methoden
 /**
 * Setzt Radius
 * @param radius [int] : Radius
 */
 public void setRadius(int radius){
    this.radius = radius;
  }
  
  /**
  * Setzt Ort
  * @param location [Location] : Ort
  */
  public void setLocation(Location location){
    this.location = location;
  }
  
  /*
   * Filter für einen bestimmten Radius um einen Ort
   * @param trackpointlist [TrackpointList] : zu filternde Trackpointliste
   * @return [TrackpointList] gefilterte Trackpointliste
   */
   public TrackpointList apply(TrackpointList trackpointlist){
     
     for( Trackpoint tp : trackpointlist ){       
       if (tp.locationDistanceTo(location) <= radius)
         filteredtpl.add(tp);
     }
     return filteredtpl;
   }  
  
}
