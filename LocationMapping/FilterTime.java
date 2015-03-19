  /*
  * Dieser Filter filtert nach Tageszeiten, dh. man gibt ein Intervall in STart und Endstunde an,
  * zwischen denen alle Trackpoints aller Tage rausgefiltert werden.
  */
  
class FilterTime extends Filter{
 
 /* Attribute
 * starttime ist der Zeitwert, ab dem gefiltert wird
 */
 int starttime;
 
 /*
 * endtime ist der Zeitwert, bis zu dme gefiltert wird
 */
 int endtime;
 
 // Konstruktor
 /**
 * Konstruktor,ruft Konstruktor der Oberklasse auf
 * @return neues Objekt vom Typ FilterTime
 */
 public FilterTime(){
     super();
 }
 
 
  /*
  * Setzt die Startzeit
  * @param starttime [int]: die zu setzende Startzeit
  */ 
  public void setStarttime(int starttime){
    this.starttime = starttime;
  }
  
  /*
  * Setzt die Endzeit
  * @param starttime [int]: die zu setzende Entzeit
  */   
  public void setEndtime(int endtime){
    this.endtime = endtime;
  }
 
   /* Wendet den gesetzten Filter an 
  * @param trackpointlist [TrackpointList]: Die zu filternde Trackpointlist
  * @return [TrackpointList] gibt eine gefilterte trackpointList zurück
  */
  public TrackpointList apply(TrackpointList trackpointlist){
   for(Trackpoint tp : trackpointlist){
     if(tp.getHour() >= starttime && tp.getHour() <= endtime){
      filteredtpl.add(tp); 
     } 
   }    
    return filteredtpl;
  }
  
  
}
