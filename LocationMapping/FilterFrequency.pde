
class FilterFrequency extends Filter{
  
// Attribute
/**
* Mindesthaeufigkeit, nach der gefiltert werden soll
*/
int minfrequency;

//Konstruktor
/**
* Konstruktor, ruft Konstruktor der Oberklasse Filter auf
* @return: neues Objekt vom Typ FilterFrequency
*/
public FilterFrequency(){
  super();
}

//Methoden
/**
* Setzt Mindesthaeufigkeit
* @param minf [int] : Mindesthaeufigkeit
*/
public void setMinFrequency(int minf){
  minfrequency = minf;
}

/**
* Filter, der alle Trackpoints filtert, die oefter als angegebene Mindesthaeufigkeit besucht wurden
* @param trackpointlist [TrackpointList] : die zu filternde Trackpointliste
* @return [TrackpointList] : gefilterte Trackpointliste
*/
public TrackpointList apply(TrackpointList trackpointlist){
  for(Trackpoint tp : trackpointlist){
   if(trackpointlist.getFrequency(tp) >= minfrequency)
    filteredtpl.add(tp); 
  }  
  return filteredtpl;
  
}
}
