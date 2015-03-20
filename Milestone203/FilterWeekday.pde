/*
 * Filter Trackpoints nach einem bestimmtem Wochentag
 */

class FilterWeekday extends Filter{
 
 /*Attribute
 * wochentag nach dem zu filtern ist
 */ 
 
 String wochentag;
 
 /*
  * Konstruktor
  */
 public FilterWeekday(){
     super();
 }
  
  /*
  * Setzt den Wochentag, nachdem zu filtern ist
  * @param wochentag [String]: der Wochentag, nach dem gefiltert wird.
  */
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
