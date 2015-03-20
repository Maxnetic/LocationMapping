/*
 * Filter Trackpoints nach Start- und Enddatum
 */

class FilterDate extends Filter{
  
  /**
  * Startdatum
  * Beispiel Timestamp ts1 = new Timestamp(2009,10,2,22,20,40,0); entspricht 2.10.2009 22 Uhr 20 Minuten 40 sek
  */
   Timestamp startdate;
   
   /**
   * Enddatum
   */
   Timestamp enddate;

   /**
   * Konstruktor, ruft Konstruktor der Oberklasse auf
   * @return neues Objekt vom Typ FilterDate
   */
   public FilterDate(){
     super();
   }
   
   /**
   * Setzt Startdatum
   * @param startdate [Timestamp] : Startdatum
   */
  public void setStartDate(Timestamp startdate){
    this.startdate =startdate;
  }
  
  /**
  * Setzt Enddatum
  * @param enddate [Timestamp] : Enddatum
  */
  public void setEndDate(Timestamp enddate){
    this.enddate = enddate;
  }
   
     
  /* Dies ist ein Filter, der zwischen einer start und einer Endzeit, gegeben als Timestamps,
  * alle Trackpoints herrausfiltert und eine Trackpointlist ausgibt
  * @param startdate [Timestamp]: ein Timestamp, der die Startzeit enthält
  * @param enddate [Timestamp]: ein Timestamp, der eine Endzeit enthält
  * @param trackpointlist [TrackpointList]: Die zu filternde Trackpointlist
  */
  public TrackpointList apply(TrackpointList trackpointlist){
   for(Trackpoint tp : trackpointlist){
      if(tp.getTimestamp().compareTo(startdate) >= 0 && tp.getTimestamp().compareTo(enddate) <= 0){
        filteredtpl.add(tp);
      }
   }
   return filteredtpl;
  }

   
   
   
   
   
   
   
}
