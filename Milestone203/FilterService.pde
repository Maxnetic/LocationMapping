/*
 * Filter Trackpoints nach benutztem Service
 */

class FilterService extends Filter{
  
  /* Attribute
  * service gibt den zu filternden Service an
  */
  String service;
  
 /*
  * Konstruktor
  */
  public FilterService(){
     super();
  }
   
  /*
  * Setzt den Service
  * @param service [String]: Der service, nach dem gefiltert werden soll
  */
  public void setService(String service){
    this.service = service;
  } 
    
  /*
  * Die Funktion filtert nach Service
  * @param trackpointlist [TrackpointList]: Die zu filternde TrackpointList
  * @return gibt eine gefilterte TrackpointList zurück
  */
  public TrackpointList apply(TrackpointList trackpointlist){
   for(Trackpoint tp : trackpointlist){
     if(tp.getService().equals(service)){
       filteredtpl.add(tp);
     }
   }
   return filteredtpl;
  } 
  
  
}
