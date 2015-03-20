/*
 * Filter für Trackpoints nach Start- und Endzeit jedes Tages
 */

class FilterTime extends Filter{
 
 /*Attribute
 * starttime gibt den Startzeitpunkt, ab dem gefiltert wird an
 * endtime gibt den Endzeitpunkt, bis zu dem gefiltert wird an
 */
 int starttime;
 int endtime;
 
 /*
  * Konstruktor
  */
 public FilterTime(){
     super();
 }
  
  /* Setzt die Startzeit
  * @param starttime [int]: Die Startzeit, ab der gefiltert wird
  */
  public void setStarttime(int starttime){
    this.starttime = starttime;
  }

  /* Setzt die Endzeit
  * @param endtime [int]: Die Startzeit, ab der gefiltert wird
  */  
  public void setEndtime(int endtime){
    this.endtime = endtime;
  }
 
   /* Dieser Filter filtert nach Tageszeiten, dh. man gibt ein Intervall in STart und Endstunde an,
  * zwischen denen alle Trackpoints aller Tage rausgefiltert werden.
  * @param trackpointlist [TrackpointList]: Die zu filternde Trackpointlist
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
