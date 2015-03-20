/*
 * Filter Trackpoints nach minimaler Häufigkeit
 */

class FilterFrequency extends Filter{
  
int minfrequency;

/*
 * Konstruktor
 */
public FilterFrequency(){
  super();
}

public void setMinFrequency(int minf){
  minfrequency = minf;
}

public TrackpointList apply(TrackpointList trackpointlist){
  for(Trackpoint tp : trackpointlist){
   if(trackpointlist.getFrequency(tp) >= minfrequency)
    filteredtpl.add(tp); 
  }  
  return filteredtpl;
  
}
  
  
  
  
}
