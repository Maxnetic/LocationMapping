
class FilterDate extends Filter{
  
  // Attribute
  //Beispiel Timestamp ts1 = new Timestamp(2009,10,2,22,20,40,0); entspricht 2.10.2009 22 Uhr 20 Minuten 40 sek
  /**
  * Timestamp, der Startdatum enthaelt
  * Beispiel fuer Timestamp : Timestamp tsl = new Timestamp(2009,10,2,22,20,40,0); entspricht 2.10.2009 20 Minuten 40 sek
  */
   Timestamp startdate;
   /**
   *Timestamp, der Enddatum enthaelt
   */
   Timestamp enddate;
   
  // Konstruktor
   /**
   * Konstruktor, ruft Konstruktor der Oberklasse Filter auf
   * @return: neues Objekt vom Typ FilterDate
   */
   public FilterDate(){
     super();
   }
   
   // Methoden   
  /**
  * Setzt Startdatum
  * @param startdate [Timestamp] : Startdatum das gesetzt werden soll
  */
  public void setStartDate(Timestamp startdate){
    this.startdate =startdate;
  }
  
  /**
  * Setzt Enddatum
  * @param enddate [Timestamp] : Enddatum das gesetzt werden soll
  */
  public void setEndDate(Timestamp enddate){
    this.enddate = enddate;
  }
   
     
  /* Dies ist ein Filter, der zwischen einer start und einer Endzeit, gegeben als Timestamps,
  * alle Trackpoints herrausfiltert und eine Trackpointlist ausgibt
  * @param trackpointlist [TrackpointList]: Die zu filternde Trackpointlist
  * @return [Trackpointlist] gefilterte Trackpoints
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
