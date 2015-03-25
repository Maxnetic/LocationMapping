package locationmapping;

import java.sql.Timestamp;

import de.fhpotsdam.unfolding.geo.*;



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
  @SuppressWarnings("deprecation")
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
  @SuppressWarnings("deprecation")
public void setEndDate(String enddate){
  this.enddate = parseDate(enddate);
  this.enddate = new Timestamp(this.enddate.getYear(), this.enddate.getMonth(), this.enddate.getDate(), 23, 59, 59, 0);
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
 
 
  private String[] splitString(String str){
    int i = 0;
    int j = 0;
    String[] week = {"montag", "dienstag", "mittwoch", "donnerstag", "freitag", "samstag", "sonntag"};
    String[] weekday = str.split("-");
    for(i = 0; i < 7; i++){
      if(week[i].equals(weekday[0])){
        break;
       }
     }
    for(j = i; j <7; j++){
      if(week[j].equals(weekday[1])){
        break;
      }
    }
    int a = i;
    System.out.println(i);
    System.out.println(j);
    String[] weekday_helper = new String[j-i+1];
    for(i = a; i <= j; i++){
      weekday_helper[i-a] = week[i];
    }
    for(i = 0; i < weekday_helper.length; i++){
      //System.out.println(weekday_helper[i]);
    }
    return weekday_helper;   
  }
 
  /*
   * Setzt den Wochentag, nach dem zu filtern ist
   * @param wochentag [String]: der Wochentag, nach dem gefiltert wird.
   */
  public void setWeekday( String wochentag){
    String[] weekdaysplit;
    String[] returnweekday = new String[7];
    
    //Foramtierung der Eingabe
    wochentag = wochentag.toLowerCase();
    wochentag = wochentag.replace(" ","");
  
    weekday = new String[7];
    weekday = wochentag.split(",");
    for(int i = 0; i < weekday.length; i++){
      returnweekday[i] = weekday[i];
    }
    
    for(int i = 0; i < weekday.length; i++){
      if(weekday[i].contains("-")){
         weekdaysplit = splitString(weekday[i]);
         returnweekday[i] = weekdaysplit[0];
         for(int j = 1; j < weekdaysplit.length; j++){
          returnweekday[weekday.length + j] = weekdaysplit[j]; 
         }
      }          
    }
    weekday = returnweekday;
  }
 
  
   /*
   * Setzt den Wochentag, nach dem zu filtern ist
   * @param wochentag [String]: der Wochentag, nach dem gefiltert wird.
   */
  /*public void setWeekday( String wochentag){
    String day;
    int i = 0;
    int j = 0;
    int start = 0;
    wochentag.toLowerCase();
    wochentag = wochentag.replace(" ","");
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
    } else {
      weekday[0] = wochentag;
    }
  }
  */
  

 @SuppressWarnings("unused")
private TrackpointList dateFilter(TrackpointList trackpointlist){
     
      for(Trackpoint tp : trackpointlist){
         if(tp.getTimestamp().compareTo(startdate) < 0 || tp.getTimestamp().compareTo(enddate) > 0){
             tp.setVisible(false);
         }
      }
      return(trackpointlist);
    }
 
 private void frequencyFilter(TrackpointList trackpointlist){
       for(Trackpoint tp : trackpointlist){
         if(trackpointlist.getFrequency(tp) < minfrequency)
           tp.setVisible(false); 
       } 
 } 
 
 private void locationFilter(TrackpointList trackpointlist){
         for( Trackpoint tp : trackpointlist ){       
          if (tp.locationDistanceTo(location) > radius)
            tp.setVisible(false); 
        }
 }
 
 
 private void serviceFilter(TrackpointList trackpointlist){
          for(Trackpoint tp : trackpointlist){
          if(!(tp.getService().equals(service))){
            tp.setVisible(false);   
          }
        }
 } 
 
 private void timeFilter(TrackpointList trackpointlist){
   for(Trackpoint tp : trackpointlist){
     
     
          if(starttime != null){ 
            //System.out.println("Start: " + starttime.hour + "  " + starttime.minute);
            if(endtime != null){
              //System.out.println("Ende: " + endtime.hour + "   " + endtime.minute);
              if((tp.getHour() < starttime.hour || (tp.getHour() == starttime.hour &&  tp.getMinute() <= starttime.minute)) ||( tp.getHour() > endtime.hour || (tp.getHour() == endtime.hour && tp.getMinute() >= endtime.minute))){ //Startzeit wurde gesetzt und endzeit wurde gesetzt
                //System.out.println(tp.getHour() + " " + tp.getMinute() + " false gesetzt") ;
                tp.setVisible(false);   
                 
              } else{
             // System.out.println(tp.getHour() + " " + tp.getMinute() + " nicht gesetzt") ;
              }
            }else if ( tp.getHour() <= starttime.hour  && tp.getMinute() <= starttime.minute){ // Nur Strartzeit wurde gesetzt
               tp.setVisible(false); 
               //System.out.println(tp.getHour() + " " + tp.getMinute());
            }
              
          } else if(tp.getHour() >= endtime.hour  && tp.getMinute() >= endtime.minute){ // Nur Endzeit wurde gesetzt
            tp.setVisible(false);
           // System.out.println(tp.getHour() + " " + tp.getMinute());
          }
        }  
 }
 
 private void weekdayFilter(TrackpointList trackpointlist){
  Boolean delete = true;
        for(Trackpoint tp : trackpointlist){
          for(String str : weekday){
            if(tp.getDayOfTheWeek().equals(str)){
              delete = false;
            }
              
          }
          if(delete){
            tp.setVisible(false);
          }
          delete = true;
        } 
   
 }
  
  /*
  * apply umgeschrieben, so dass mehrere Filter hintereinander anwendet
  * dazu haben wir die herangehensweise gedreht und löschen die TP aus der Liste, statt sie hinzuzufügen
  * @param trackpointlist [TrackpointList]: Die TrackpointList, die gefiltert wird
  * @return gibt eine gefilterte Trackpointlist zurück
  */
  
  public TrackpointList apply(TrackpointList trackpointlist) { 
    
    // Filter einzeln debugged mit Ausnahme von Wochentagen 
   
   // Variablen anlegen 
    @SuppressWarnings("unused")
	TrackpointList deletetpl = new TrackpointList(); // Hier werden die später zu löschenden Trackpoints reingepackt
    TrackpointList filteredtpl = new TrackpointList(); // aus der Liste werden die Trackpoints später gelöscht
    //Trackpointliste wird komplett kopiert um java fehler zu vermeiden
    for (Trackpoint tp : trackpointlist){
      filteredtpl.add(tp);
    }

    // Datumsfilter 
    // debugged 
    if ((this.startdate != null) && (this.enddate != null)) {  //hier eventuell Fehlermeldung werfen?
      for(Trackpoint tp : trackpointlist){
         if(tp.getTimestamp().compareTo(startdate) < 0 || tp.getTimestamp().compareTo(enddate) > 0){
           tp.setVisible(false);
         }
      }
    }

     
     // Frequenzfilter
     if (minfrequency > 1) {
        frequencyFilter(trackpointlist);
     }  
      
      // Radiusfilter
      if (radius != 0 && location != null) {
          locationFilter(trackpointlist);
      }
  
      if (service != null) {
          serviceFilter(trackpointlist);
      } 
       if (starttime != null || endtime != null) { //checkt ob mindestens eins der beiden Argumente gesetzt wurde
       timeFilter(trackpointlist);
      } 
      if (weekday != null) {
        weekdayFilter(trackpointlist);
      } 
      

      return trackpointlist;
  }
}
