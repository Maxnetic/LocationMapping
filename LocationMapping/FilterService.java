

// Die Klasse filtert nach genutztem Service
class FilterService extends Filter{
  
  /*Attribute
  * service bezeichnet den Dienst, der genutzt wurde
  */
  String service;
  
  // Konstruktor
  /**
  * Konstruktor, ruft Konstruktor der Oberklasse auf
  * @return neues Objekt vom Typ FilterService
  */
  public FilterService(){
     super();
  }
  
  /* 
  * Setzt die service Eigenschaft
  * @param service [String]: Der gewählte service, nach dem sortiert werden soll
  */ 
  public void setService(String service){
    this.service = service;
  } 
  
  
  /*
  * Die Funktion wendet den Filter an und filtert nach Service
  * @param trackpointlist [TrackpointList]: Die zu filternde TrackpointList
  * @return [TrackpointList] gibt eine gefilterte TrackpointList zurück
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
