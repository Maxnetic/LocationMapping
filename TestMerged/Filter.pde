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
  Timestamp startdate; //Startdatum Timestamp ts1 = new Timestamp(2009,10,2,22,20,40,0); entspricht 2.10.2009 22 Uhr 20 Minuten 40 sek
  Timestamp enddate; //Enddatum
  int minfrequency = 0;  //Mindesthäufigkeit
  int radius = 0;  //Suchradius für Locationfilter Genauigkeit
  Location location; //Location
  String service; //zu filternder Service (SMS, Internet, GPRS)
  TimeTupel starttime; //Startzeit (Stunde am Tag) -1 heißt variable wurde nicht gesetzt
  TimeTupel endtime; //Endzeit (Stunde am Tag)
  String[] weekday;
  
 
  
  
  /*
   * Konstruktor fuer Filter
   * @return neues Objekt vom Typ Filter
   */
  public Filter(){
    filteredtpl = new TrackpointList();
  }
  
  /* Innere Klasse
  * Darstellung der Zeit als Tupel (wird für konvertierung aus String gebraucht) 
  */
  class TimeTupel{
    int hour;
    int minute;
    
    //Konstruktor
    private TimeTupel(int x, int y){
      hour = x;
      minute = y;
    }
  }
 
  /*
   * Setzt Startdatum
   * @param startdate [String] : Das Startdatum im Format: YYYY/MM/DD
   */
  public void setStartDate(String startdate){

  this.startdate = parseDate(startdate);
    
  }
  
  /*
  * Hilfsfunktion, die einen String in einen Timestamp umwandelt
  * @param str [String]: Datumsstring, der umgewandelt wird
  * @return gibt einen Timestamp zurück
  */
  private Timestamp parseDate(String str){
    int year = Integer.parseInt(str.substring(0,4));
    int month = Integer.parseInt(str.substring(5,7));
    int day = Integer.parseInt(str.substring(8,10));
    return (new Timestamp(year-1900,month-1,day,0,0,0,0));
  }
  
  /*
   * Setzt Enddatum
   * @param enddate [String] : Das Enddatum im Format: YYYY/MM/DD-HH:MM
   */
  public void setEndDate(String enddate){
  this.enddate = parseDate(enddate);
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
 
 /* Hilfsfunktion, die einen Timestring in ein Tupel umwandelt
 * String Format HH:MM
 */
 
 private TimeTupel parseTime(String str){
   TimeTupel time = new TimeTupel(Integer.parseInt(str.substring(0,2)), Integer.parseInt(str.substring(3,5)));
   return time;
   
   
 }
 
  /* Setzt die Startzeit
   * @param starttime [int]: Die Startzeit, ab der gefiltert wird
   */
  public void setStarttime(String starttime){
    this.starttime = parseTime(starttime);
  }

  /* Setzt die Endzeit
   * @param endtime [int]: Die Startzeit, ab der gefiltert wird
   */  
  public void setEndtime(String endtime){
    this.endtime = parseTime(endtime);
  }
 
  /*
   * Setzt den Service
   * @param service [String]: Der service, nach dem gefiltert werden soll
   */
  public void setService(String service){
    this.service = service;
  } 
 
  /*
   * Setzt den Wochentag, nach dem zu filtern ist
   * @param wochentag [String]: der Wochentag, nach dem gefiltert wird.
   */
  public void setWeekday( String wochentag){
    String day;
    int i = 0;
    int j = 0;
    int start = 0;
    wochentag.toLowerCase();
    wochentag = wochentag.replace(" ","");
    System.out.println(wochentag);
    String[] week = {"montag", "dienstag", "mittwoch", "donnerstag", "freitag", "samstag", "sonntag"};
    weekday = new String[7];
    if(wochentag.contains(",")){
      weekday = wochentag.split(",");
    } else if (wochentag.contains("-")){
      weekday = wochentag.split("-");
      for(i = 0; i < 7; i++){
        if(week[i] == weekday[0]){
         break;
        }
      }
      for(j = i; j <7; j++){
        if(week[j] == weekday[1]){
          break;
        }
      }
      int a = i;
      System.out.println(i + j);
      for(i = a; i < j; i++){
        weekday[i-a] = week[i];
      }
    }
  }
 

 
  
  
  /*
  * apply umgeschrieben, so dass mehrere Filter hintereinander anwendet
  * dazu haben wir die herangehensweise gedreht und löschen die TP aus der Liste, statt sie hinzuzufügen
  * @param trackpointlist [TrackpointList]: Die TrackpointList, die gefiltert wird
  * @return gibt eine gefilterte Trackpointlist zurück
  */
  
  public TrackpointList apply(TrackpointList trackpointlist) { 
   
   // Variablen anlegen 
    TrackpointList deletetpl = new TrackpointList(); // Hier werden die später zu löschenden Trackpoints reingepackt
    TrackpointList filteredtpl = new TrackpointList(); // aus der Liste werden die Trackpoints später gelöscht
    //Trackpointliste wird komplett kopiert um java fehler zu vermeiden
    for (Trackpoint tp : trackpointlist){
      filteredtpl.add(tp);
    }

    // Datumsfilter
    if ((this.startdate != null) && (this.enddate != null)) {  //hier eventuell Fehlermeldung werfen?
      for(Trackpoint tp : trackpointlist){
        if(tp.getTimestamp().compareTo(startdate) < 0 || tp.getTimestamp().compareTo(enddate) > 0){
          deletetpl.add(tp);         
        }          
      }
     }
     
     // Frequenzfilter
     if (minfrequency > 1) {
       for(Trackpoint tp : trackpointlist){
         if(trackpointlist.getFrequency(tp) < minfrequency)
           deletetpl.add(tp);   
       }  
      }
      
      // Radiusfilter
      if (radius != 0 && location != null) {
        for( Trackpoint tp : trackpointlist ){       
          if (tp.locationDistanceTo(location) > radius)
            deletetpl.add(tp);   
        }
      }
      
      
      // Servicefilter
      if (service != null) {
        for(Trackpoint tp : trackpointlist){
          if(!(tp.getService().equals(service))){
            deletetpl.add(tp);   
          }
        }
      }
      
      
      /*Zeitfilter
      * Veraendert, damit er auch nur mit end oder startzeit filtert
      */
      if (starttime != null || endtime != null) { //checkt ob mindestens eins der beiden Argumente gesetzt wurde
        for(Trackpoint tp : trackpointlist){
          if(starttime != null){ 
            
            if(endtime != null){
              if((tp.getHour() < starttime.hour && tp.getMinute() < starttime.minute) ||( tp.getHour() > endtime.hour && tp.getMinute() > endtime.minute)){ //Startzeit wurde gesetzt und endzeit wurde gesetzt
                deletetpl.add(tp);   
              } 
              
            }else if ( tp.getHour() < starttime.hour  && tp.getMinute() < starttime.minute){ // Nur Strartzeit wurde gesetzt
                deletetpl.add(tp);    
              }
              
          } else if(tp.getHour() > endtime.hour  && tp.getMinute() > endtime.minute){ // Nur Endzeit wurde gesetzt
            deletetpl.add(tp);   
          }
        }    
  
      }
      
      // Wochentagsfilter
      if (weekday != null) {
        for(Trackpoint tp : trackpointlist){
          for(String str : weekday){
            if(tp.getDayOfTheWeek().equals(str)){
              break;
            }
            deletetpl.add(tp);
          }
        }
      } 
      
      // Hier werden die Trackpoints aus der Liste rausgeschmissen
      for(Trackpoint tp : deletetpl){
        filteredtpl.deleteTrackpoint(tp);
      }
        

      return filteredtpl;
  }
}
