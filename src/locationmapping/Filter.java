package locationmapping;

import java.util.*;
import java.sql.Timestamp;




/**
 * Eine Filter Klasse, die alle Filter beinhaltet, sowie eine apply Methode zur Verfügung stellt, die eine TrackpointList filtert.
 */

public class Filter{
 
     /** 
      *Attribute
      * Die Rückgabeliste, die in den Filtern beschrieben wird
       */ 
  
      /*
      * Timestamps, die zum Datumsvergleich benutzt werden
      */
      Timestamp startdate; //Startdatum Timestamp ts1 = new Timestamp(2009,10,2,22,20,40,0); entspricht 2.10.2009 22 Uhr 20 Minuten 40 sek
      Timestamp enddate; //Enddatum
  
      /*
      * Mindestfrequenz für den Frequenzfilter
      */
      int minfrequency = 0;
  
      /*
      * radius und Location, die im Radiusfilter genutzt werden
      */
      int radius = 0;  
      Location location; 
  
      /*
      * Service, der im Servicefilter gefiltert wird
      */
      String service; //zu filternder Service (SMS, Internet, GPRS)
  
      /*
      *Zeittupel, das als innere Klasse zur Darstellung von Stunden und Minuten genutzt wird
      */
      TimeTupel starttime; //Startzeit (Stunde am Tag) -1 heißt variable wurde nicht gesetzt
      TimeTupel endtime; //Endzeit (Stunde am Tag)
  
      /*
      *Ein Array, in dem die Wochentage, die gefiltert werden sollen, gespeichert werden
      */
      String[] weekday;
  
 
  
  
      /*
       * Konstruktor fuer Filter
       * 
       */
      public Filter(){
      }
  
      /* Innere Klasse
      * Darstellung der Zeit als Tupel mit Stunde und Minute (wird für Konvertierung aus String gebraucht) 
      */
      
      private class TimeTupel{
            /*
            *Attribute
            *hour entspricht Stunde
            *minute entspricht Minute
            */
            int hour;
            int minute;
    
            /*Konstruktor
            *@param hour [int]: Die Stunde des TimeTupel Objektes
            *@param minute [int]: Die Minute des TimeTuple Objektes
            *@return erzeugt ein neues TimeTupel Objekt
            */
            private TimeTupel(int hour, int minute){
                this.hour = hour;
                this.minute = minute;
            }
      }
 
       /*
       * Setzt das Startdatum des Filters
       * @param startdate [String] : Das Startdatum im Format: YYYY/MM/DD
       */
       public void setStartDate(String startdate){

          this.startdate = parseDate(startdate);
    
       }
  
      /*
       * Setzt das Enddatum
       * @param enddate [String] : Das Enddatum im Format: YYYY/MM/DD
       */
      public void setEndDate(String enddate){
          this.enddate = parseDate(enddate);
          this.enddate = new Timestamp(this.enddate.getYear(), this.enddate.getMonth(), this.enddate.getDate(), 23, 59, 59, 0);// Anpassung der Stunden und Minuten, damit der Endtag noch mit enthalten ist
      }
   
      /*
      * Hilfsfunktion, die einen String in einen Timestamp umwandelt
      * @param str [String]: Datumsstring, der umgewandelt wird
      * @return gibt einen Timestamp zurück, der die eingegebene Zeit repräsentiert
      */
      private Timestamp parseDate(String str){
          int year = Integer.parseInt(str.substring(0,4));
          int month = Integer.parseInt(str.substring(5,7));
          int day = Integer.parseInt(str.substring(8,10));
          return (new Timestamp(year-1900,month-1,day,0,0,0,0));
      }
  

 
      /*
       * Setzt die Mindesthaeufigkeit
       * @param minf [int] : Mindesthaeufigkeit
       */

      public void setMinFrequency(int minf){
          minfrequency = minf;
      }
 
      /* Setzt den Radius
       * @param radius [int]: der Radius, um den gefiltert wird
       */
       public void setRadius(int radius){
           this.radius = radius;
       }
  
      /* Setzt die Location
       * @param location [Location]: der Ort, um den gefiltert wird
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
       * @param endtime [int]: Die Endzeit, ab der gefiltert wird
       */  
      public void setEndtime(String endtime){
          this.endtime = parseTime(endtime);
      }
 
      /*
       * Setzt den Service
       * @param service [String]: Der Service, nach dem gefiltert werden soll
       */
      public void setService(String service){
          this.service = service;
      } 
 
 
      /*
      *Hilfsfunktion, die eine String-Eingabe an "-" trennt und in ein String-Array packt
      * @param str [String]: Der Eingabestring, der umgewandelt wird
      * @return gibt ein String-Array zurück, das alle gewünschten Tage einzeln enthält
      */
      private String[] splitString(String str){
          //Laufvariablen
          int i = 0;
          int j = 0;
   
      //Vergleichsarray
          String[] week = {"montag", "dienstag", "mittwoch", "donnerstag", "freitag", "samstag", "sonntag"};
    
      //Trennung an Bindestrich
          String[] weekday = str.split("-");
    
      //Verpacken des Strings
      //bestimmt Zahlenwert für den ersten Tag
          for(i = 0; i < 7; i++){
              if(week[i].equals(weekday[0])){
               break;
               }
          }
       //bestimmt Zahlenwert für den letzten Tag
          for(j = i; j <7; j++){
              if(week[j].equals(weekday[1])){
              break;
              }
          }  
          int a = i;
          String[] weekday_helper = new String[j-i+1];
    
          //fügt alle Tage zwischen Start und Endtag in das Array ein
          for(i = a; i <= j; i++){
              weekday_helper[i-a] = week[i];
          }
          return weekday_helper;   
      }
 
      /*
       * Setzt den Wochentag, nach dem zu filtern ist
       * @param wochentag [String]: die Wochentage, nach denen gefiltert wird. Eingabe Trennung erfolgt mit "," oder "-"
       */
      public void setWeekday( String wochentag){
          String[] weekdaysplit;
          String[] returnweekday = new String[7];
    
      //Formatierung der Eingabe: Umwandlung in Kleinbuchstaben und Entfernung von Leerzeichen
          wochentag = wochentag.toLowerCase();
          wochentag = wochentag.replace(" ","");
  
          weekday = new String[7];
      //Trennt die Eingabe an allen Komma
          weekday = wochentag.split(",");
          for(int i = 0; i < weekday.length; i++){
              returnweekday[i] = weekday[i];
          }
    
          for(int i = 0; i < weekday.length; i++){
              if(weekday[i].contains("-")){
              //Benutzt splitString, das Eingaben mit Bindestrich in ein Array umwandelt
                  weekdaysplit = splitString(weekday[i]);
              //schreibt den ersten Tag in das Feld, in dem der Bindestrich war
                  returnweekday[i] = weekdaysplit[0];
              //schreibt die in splitString erzeugten Strings in das Rückgabe Array
                  for(int j = 1; j < weekdaysplit.length; j++){
                      returnweekday[weekday.length + j] = weekdaysplit[j]; 
                  }
               }          
           }
          weekday = returnweekday;
        }
 
   
      /*
      * Filtert nach Datum
      * @param trackpointlist [TrackpointList]: Die Liste, die gefiltert werden soll
      */
      private void dateFilter(TrackpointList trackpointlist){
     
          for(Trackpoint tp : trackpointlist){             
              if(startdate != null){
                  if(enddate != null){
                     if(tp.getTimestamp().compareTo(startdate) < 0 || tp.getTimestamp().compareTo(enddate) > 0){
                          tp.setVisible(false);
                     }
                  }else{
                      if(tp.getTimestamp().compareTo(startdate) < 0){
                          tp.setVisible(false);
                      }
                  }
              }else{
                  if(tp.getTimestamp().compareTo(enddate) > 0){
                      tp.setVisible(false);
                  }
              }
          }
     }    

      /*
      * Filtert nach Frequenz
      * @param trackpointlist [TrackpointList]: Die Liste, die gefiltert werden soll
      */ 
      private void frequencyFilter(TrackpointList trackpointlist){
           for(Trackpoint tp : trackpointlist){
               if(trackpointlist.getFrequency(tp) < minfrequency)
                   tp.setVisible(false); 
           } 
      } 
 
     /*
      * Filtert nach Location und Radius
      * @param trackpointlist [TrackpointList]: Die Liste, die gefiltert werden soll
      */
      private void locationFilter(TrackpointList trackpointlist){
         for( Trackpoint tp : trackpointlist ){       
            if (tp.locationDistanceTo(location) > radius)
                tp.setVisible(false); 
         }
      }
 
     /*
      * Filtert nach Service
      * @param trackpointlist [TrackpointList]: Die Liste, die gefiltert werden soll
      */
      private void serviceFilter(TrackpointList trackpointlist){
          for(Trackpoint tp : trackpointlist){
              if(!(tp.getService().equals(service))){
                  tp.setVisible(false);   
              }
          }
      } 
 
      /*
      * Filtert nach Uhrzeit
      * @param trackpointlist [TrackpointList]: Die Liste, die gefiltert werden soll
      */
      private void timeFilter(TrackpointList trackpointlist){
         for(Trackpoint tp : trackpointlist){
     
     
            if(starttime != null){ 
                if(endtime != null){ // Start und Endzeit wurde gesetzt              
                    if((tp.getHour() < starttime.hour || (tp.getHour() == starttime.hour &&  tp.getMinute() <= starttime.minute)) ||( tp.getHour() > endtime.hour || (tp.getHour() == endtime.hour && tp.getMinute() >= endtime.minute))){ 
                        tp.setVisible(false);                  
                    }
                    //Nur Startzeit wurde gesetzt
                    }else if ( tp.getHour() <= starttime.hour  && tp.getMinute() <= starttime.minute){
                       tp.setVisible(false); 
                }
                // Nur Endzeit wurde gesetzt    
            } else if(tp.getHour() >= endtime.hour  && tp.getMinute() >= endtime.minute){ 
                tp.setVisible(false);
            }
        }  
     }
 
      /*
      * Filtert nach Wochentag
      * @param trackpointlist [TrackpointList]: Die Liste, die gefiltert werden soll
      */
     private void weekdayFilter(TrackpointList trackpointlist){
        Boolean delete = true;
        for(Trackpoint tp : trackpointlist){
            // Überprüft, ob der Tag in dem Wochen-Array ist 
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
      * apply Methode, die je nachdem, wie die Attribute gesetzt wurden filtert
      * Es wird überprüft, ob und welche Attribute gesetzt wurden, so dass bestimmte Filter aufgerufen werden
      * @param trackpointlist [TrackpointList]: Die TrackpointList, die gefiltert wird
      * @return gibt eine TrackpointList zurück, in der die visible Eigenschaft verändert wurde
      */
  
      public TrackpointList apply(TrackpointList trackpointlist) { 
    
         // Datumsfilter
         if ((this.startdate != null) || (this.enddate != null)) {  
            dateFilter(trackpointlist);
         }
     
         // Frequenzfilter
         if (minfrequency > 1) {
            frequencyFilter(trackpointlist);
         }  
      
         // Radiusfilter
         if (radius != 0 && location != null) {
            locationFilter(trackpointlist);
         }
         
         //Servicefilter
         if (service != null) {
            serviceFilter(trackpointlist);
         } 
   
         //Zeitfilter
         if (starttime != null || endtime != null) { 
             timeFilter(trackpointlist);
         } 
      
         //Wochentagsfilter
         if (weekday != null) {
            weekdayFilter(trackpointlist);
         } 
         
         //überschreiben der Rauszufilternden Punkte
         TrackpointList filteredtpl = new TrackpointList();   
         for(Trackpoint tp : trackpointlist){
             if(tp.getVisible() == true){
                 filteredtpl.add(tp);
             } 
         }
         return filteredtpl;
      }
}
