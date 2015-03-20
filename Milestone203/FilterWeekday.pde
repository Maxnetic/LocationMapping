/*
 * Filter Trackpoints nach bestimmten Wochentag
 */

class FilterWeekday extends Filter{
  
  
 String wochentag;
 
 /*
  * Konstruktor
  */
 public FilterWeekday(){
     super();
 }
  
  public void setWochentag( String wochentag){
    this.wochentag = wochentag;
  }
  
   /*
  * Die Funktion filtert nach Wochentag
  * @param wochentag [String]: Der Wochentag, nach dem gefiltert wird
  * @return gibt eine gefilterte trackpointList zurück
  */
  public TrackpointList apply(TrackpointList trackpointlist){
    for(Trackpoint tp : trackpointlist){
     if(tp.getDayOfTheWeek().equals(wochentag)){
      filteredtpl.add(tp); 
     }
    }
    return filteredtpl;
  }
  
   
  
  
  
  
}
