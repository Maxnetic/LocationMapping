
 //Diese Klasse filtert die TrackpointList nach einem Wochentag
class FilterWeekday extends Filter{

  
  
 /*Attribute
 * wochentag ist der Tag der Woche, nach dem gefiltert werden soll
 */ 
 String wochentag;
 
 //Konstruktor
 /**
 * Konstruktor, ruft Konstruktor der Oberklasse auf
 * @return neues Objekt vom Typ FilterWeekday
 */
 public FilterWeekday(){
     super();
 }
 
 
  /* Set Methode für FilterWeekday
  * @param wochentag [String]: Der Wochentag, nach dem gefilter werden soll
  */
  public void setWochentag( String wochentag){
    this.wochentag = wochentag;
  }
  
   /*
  * Die Funktion wendet den Filter mit den gesetzten Werten an und filtert nach einem Wochentag
  * @param trackpointlist [TrackpointList] : zu filternde Trackpointliste
  * @return [TrackpointList] gibt eine gefilterte trackpointList zurück
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
