/*
 * Filter Trackpoints nach benutztem Service
 */

class FilterService extends Filter{
  
  String service;
  
 /*
  * Konstruktor
  */
  public FilterService(){
     super();
  }
     
  public void setService(String service){
    this.service = service;
  } 
    
  /*
  * Die Funktion filtert nach Service
  * @param service [String]: Art von benutztem Service
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
