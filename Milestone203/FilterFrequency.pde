/*
 * Filter Trackpoints nach minimaler Häufigkeit
 */

class FilterFrequency extends Filter{
  
  /**
  * Mindesthaeufigkeit
  */
int minfrequency;

/**
 * Konstruktor, ruft Konstruktor der Oberklasse auf
 * @return neues Objekt vom Typ FilterFrequency
 */
public FilterFrequency(){
  super();
}

/**
* Setzt Mindesthaeufigkeit
* @param minf [int] : Mindesthaeufigkeit
*/

public void setMinFrequency(int minf){
  minfrequency = minf;
}

/**
* Filter zum Filtern der am haeufigsten besuchten Orten
* @param trackpointlist [TrackpointList] : zu filternde Trackpointliste
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
