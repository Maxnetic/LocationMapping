


class FilterTime extends Filter{
 
 //Attribute

 int starttime;
 int endtime;
 
 public FilterTime(){
     super();
 }
 
 
   
  public void setStarttime(int starttime){
    this.starttime = starttime;
  }
  
  public void setEndtime(int endtime){
    this.endtime = endtime;
  }
 
   /* Dieser Filter filtert nach Tageszeiten, dh. man gibt ein Intervall in STart und Endstunde an,
  * zwischen denen alle Trackpoints aller Tage rausgefiltert werden.
  * @param startzeit [int]: ein startzeitpunkt
  * @param endzeit [int] Der Endzeitpunkt
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
