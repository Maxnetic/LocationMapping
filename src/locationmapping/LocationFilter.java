package locationmapping;

import java.util.*;

import de.fhpotsdam.unfolding.geo.*;



public class LocationFilter extends Filter {
    /**
     * Mindestfrequenz für den Frequenzfilter
     */
    int minfrequency = 0;
    /**
    * Location, nach der gefiltert wird
    */
    Location location;
    /**
    * Radius um Location, die im Radiusfilter genutzt werden
    */
    int radius = 0;
    /**
    * Service, der im Servicefilter gefiltert wird (SMS, Internet, GPRS)
    */
    String service;


    /**
    * Setzt die Mindesthaeufigkeit
    * @param minf Mindesthaeufigkeit
    */
    public void setMinFrequency(int minf){
        minfrequency = minf;
    }

    /**
    * Setzt den Radius
    *
    * @param radius Radius, um den gefiltert wird
    */
    public void setRadius(int radius){
        this.radius = radius;
    }

    /**
    * Setzt die Location
    *
    * @param location Ort, um den gefiltert wird
    */
    public void setLocation(Location location){
        this.location = location;
    }

    /**
    * Setzt den Service
    *
    * @param service Service, nach dem gefiltert werden soll
    */
    public void setService(String service){
        this.service = service;
    }

    // zeichne Trackpoint nur, wenn beide innerhalb des Radius waren
    public TrackpointList compareLocation(TrackpointList eingabetpl1, TrackpointList eingabetpl2, int radius){
        TrackpointList newtpl = new TrackpointList();
        for ( Trackpoint tp1 : eingabetpl1 ){
           for ( Trackpoint tp2 : eingabetpl2 ){
                if ( tp1.locationDistanceTo(tp2.getLocation()) <= radius ){
                    newtpl.add(tp1);
                    break;
                }
            }
        }
        return newtpl;
    }

    //zeichne Trackpoint nur, wenn Ort und Zeit übereinstimmen
    public TrackpointList compareLocationAndTime(TrackpointList eingabetpl1, TrackpointList eingabetpl2, int radius, int minutes){
        TrackpointList newtpl = new TrackpointList();
        for (Trackpoint tp1 : eingabetpl1){
            for (Trackpoint tp2 : eingabetpl2){
                if (  (tp1.locationDistanceTo(tp2.getLocation()) <= radius) && (tp1.locationDistanceTo(tp2) < minutes * 60) ){
                    newtpl.add(tp1);
                 break;
                }
            }
        }
        return newtpl;
    }


    /**
    * Filtert nach Frequenz
    * @param trackpointlist Liste, die gefiltert werden soll
    */
    private void frequencyFilter(TrackpointList trackpointlist){
        for(Trackpoint tp : trackpointlist){
            if(trackpointlist.getFrequency(tp) < minfrequency)
                tp.setVisible(false);
        }
    }

    /**
    * Filtert nach Location und Radius
    * @param trackpointlist Liste, die gefiltert werden soll
    */
    private void locationFilter(TrackpointList trackpointlist){
        for( Trackpoint tp : trackpointlist ){
            if (tp.locationDistanceTo(location) > radius)
                tp.setVisible(false);
        }
    }

    /**
    * Filtert nach Service
    * @param trackpointlist Liste, die gefiltert werden soll
    */
    private void serviceFilter(TrackpointList trackpointlist){
        for(Trackpoint tp : trackpointlist){
            if(!(tp.getService().equals(service))){
                tp.setVisible(false);
            }
        }
    }


    /**
     * apply Methode, die je nachdem, wie die Attribute gesetzt wurden filtert
     * Es wird ueberprueft, ob und welche Attribute gesetzt wurden, so dass bestimmte Filter aufgerufen werden
     *
     * @param trackpointlist Die TrackpointList, die gefiltert wird
     * @return gibt eine TrackpointList zurueck, in der die visible Eigenschaft veraendert wurde
    */
    public TrackpointList apply(TrackpointList trackpointlist) {

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

     //ueberschreiben der Rauszufilternden Punkte
     TrackpointList filteredtpl = new TrackpointList();
     for(Trackpoint tp : trackpointlist){
         if(tp.getVisible() == true){
             filteredtpl.add(tp);
         }else{
             tp.setVisible(true);
         }
     }
     return filteredtpl;
    }
}
