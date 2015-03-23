import java.util.*;
import java.sql.Timestamp;

/*
 * Filter Klasse, die alle Filter beinhaltet
 */

public class Filter{
 
 /* Attribute
  * Die Rückgabeliste, die in den Filtern beschrieben wird
  */

  TrackpointList filteredtpl;
  String type;
  Timestamp startdate; //Startdatum Timestamp ts1 = new Timestamp(2009,10,2,22,20,40,0); entspricht 2.10.2009 22 Uhr 20 Minuten 40 sek
  Timestamp enddate; //Enddatum
  int minfrequency;  //Mindesthäufigkeit
  int radius;  //Suchradius für Locationfilter Genauigkeit
  Location location; //Location
  String service; //zu filternder Service (SMS, Internet, GPRS)
  int starttime; //Startzeit (Stunde am Tag)
  int endtime; //Endzeit (Stunde am Tag)
  String wochentag;
 
  /*
   * set-Methode für Type
   * @param String: Möglichkeiten sind: Date, Frequency, Location, Service, Time, Weekday
   */
  public void setType(String type) {
    this.type = type;
  }
 
  /*
   * get-Methode für Type
   * @return String: gewählte Filtermethode
   */
  public String getType() {
    return this.type;
  }
 
  /*
   * Setzt Startdatum
   * @param startdate [Timestamp] : Startdatum
   */
  public void setStartDate(Timestamp startdate){
    this.startdate =startdate;
  }
  
  /*
   * Setzt Enddatum
   * @param enddate [Timestamp] : Enddatum
   */
  public void setEndDate(Timestamp enddate){
    this.enddate = enddate;
   }
 
  /*
   * Setzt Mindesthaeufigkeit
   * @param minf [int] : Mindesthaeufigkeit
   */

  public void setMinFrequency(int minf){
    minfrequency = minf;
  }
 
  /* Setzt den Radius
   * @param radius [int]: setzt den Radius, um den gefiltert wird
   */
  public void setRadius(int radius){
    this.radius = radius;
  }
  
  /* Setzt die Location
   * @param location [Location]: setzt den Ort, um den gefiltert wird
   */ 
  public void setLocation(Location location){
    this.location = location;
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
 
  /*
   * Setzt den Service
   * @param service [String]: Der service, nach dem gefiltert werden soll
   */
  public void setService(String service){
    this.service = service;
  } 
 
  /*
   * Setzt den Wochentag, nachdem zu filtern ist
   * @param wochentag [String]: der Wochentag, nach dem gefiltert wird.
   */
  public void setWochentag( String wochentag){
    this.wochentag = wochentag;
  }
 
  /*
   * Konstruktor fuer Filter
   * @return neues Objekt vom Typ Filter
   */
  public Filter(){
    filteredtpl = new TrackpointList();
  }
 

  public TrackpointList apply(TrackpointList trackpointlist) {
      if ((this.type == "Date") && (this.startdate != null) && (this.enddate != null)) {  //hier eventuell Fehlermeldung werfen?
        for(Trackpoint tp : trackpointlist){
          if(tp.getTimestamp().compareTo(startdate) >= 0 && tp.getTimestamp().compareTo(enddate) <= 0){
            filteredtpl.add(tp);
          }
        }
        return filteredtpl;
      }
      if (this.type == "Frequency") {
        for(Trackpoint tp : trackpointlist){
          if(trackpointlist.getFrequency(tp) >= minfrequency)
            filteredtpl.add(tp); 
        }  
        return filteredtpl;
      }
      if (this.type == "Location") {
        for( Trackpoint tp : trackpointlist ){       
          if (tp.locationDistanceTo(location) <= radius)
            filteredtpl.add(tp);
        }
        return filteredtpl;
      }
      if (this.type == "Service") {
        for(Trackpoint tp : trackpointlist){
          if(tp.getService().equals(service)){
            filteredtpl.add(tp);
          }
        }
        return filteredtpl;
      }
      if (this.type == "Time") {
        for(Trackpoint tp : trackpointlist){
          if(tp.getHour() >= starttime && tp.getHour() <= endtime){
            filteredtpl.add(tp); 
          } 
        }    
        return filteredtpl;
      }
      if (this.type == "Weekday") {
        for(Trackpoint tp : trackpointlist){
          if(tp.getDayOfTheWeek().equals(wochentag)){
            filteredtpl.add(tp); 
          }
        }
        return filteredtpl; 
      } 
      return filteredtpl; //leere FilteredListe falss Parameter nicht richtig gesetzt? bessere Lösung?
  }
}
