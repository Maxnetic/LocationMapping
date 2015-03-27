package locationmapping;

import java.lang.String;
import java.util.*;

import org.joda.time.*;

public class TimeFilter extends Filter {
    /**
     * Startzeit fuer Filter
     */
    private LocalTime startTime;
    /**
     * Endzeit fuer Filter
     */
    private LocalTime endTime;
    /**
     * Map mit Zeitintervallen, die gefiltert werden sollen
     */
    private Map<LocalTime,LocalTime> timeIntervals = new LinkedHashMap<LocalTime,LocalTime>();


    /**
     * Konstruktor für TimeFilter Objekte
     *
     * @return neues TimeFilter Objekt
     */
    public TimeFilter(){
        super();
    }


    /**
     * Setzt Startzeit des Filters
     *
     * @param time Startzeit als Zeitobjekt
     */
    public TimeFilter setStartTime(LocalTime time){
        this.startTime = time;
        return this;
    }
    /**
     * Setzt Startzeit des Filters
     *
     * @param time Startzeit als Datumsobjekt
     */
    public TimeFilter setStartTime(DateTime time){
        this.setStartTime(new LocalTime(time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute()));
        return this;
    }
    /**
     * Setzt Startzeit des Filters
     *
     * @param timeString Startzeit im Format: 8:30 oder 18
     */
    public TimeFilter setStartTime(String timeString){
        return this.setStartTime(parseTime(timeString));
    }
    /**
     * Setzt Startzeit des Filters
     *
     * @param timeString Startzeit im Format: 8:30 oder 18
     */
    public TimeFilter from(String timeString){
        return this.setStartTime(timeString);
    }


    /**
     * Setzt Endtzeit des Filters
     *
     * @param time Endtzeit als Zeitobjekt
     */
    public TimeFilter setEndTime(LocalTime time){
        this.endTime = time;
        return this;
    }
    /**
     * Setzt Endtzeit des Filters
     *
     * @param time Endtzeit als Datumsobjekt
     */
    public TimeFilter setEndTime(DateTime time){
        this.setEndTime(new LocalTime(time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute()));
        return this;
    }
    /**
     * Setzt Endtzeit des Filters
     *
     * @param timeString Endtzeit im Format: 8:30 oder 18
     */
    public TimeFilter setEndTime(String timeString){
        return this.setEndTime(parseTime(timeString));
    }
    /**
     * Setzt Endtzeit des Filters
     *
     * @param timeString Endtzeit im Format: 8:30 oder 18
     */
    public TimeFilter to(String timeString){
        return this.setEndTime(timeString);
    }



    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param timeInterval hinzuzufuegendes Zeitinterval mit Startzeit in [0] und Endzeit in [1]
     */
    public TimeFilter addTimeInterval(LocalTime[] timeInterval){
        try {
            this.timeIntervals.put(timeInterval[0], timeInterval[1]);
        } catch(Exception e){
            throw new RuntimeException(Arrays.toString(timeInterval) + " doesn't represent time Interval [startTime, endTime]");
        }
        return this;
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param startTime Startpunkt des hinzuzufuegenden Zeitinterval
     * @param endTime Endpunkt des hinzuzufuegenden Zeitinterval
     */
    public TimeFilter addTimeInterval(LocalTime startTime, LocalTime endTime){
        this.timeIntervals.put(startTime, endTime);
        return this;
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param startTime Startpunkt des hinzuzufuegenden Zeitinterval
     * @param endTime Endpunkt des hinzuzufuegenden Zeitinterval
     */
    public TimeFilter addTimeInterval(DateTime startTime, DateTime endTime){
        LocalTime localStartTime = new LocalTime(startTime.getHourOfDay(), startTime.getMinuteOfHour(), startTime.getSecondOfMinute());
        LocalTime localEndTime = new LocalTime(endTime.getHourOfDay(), endTime.getMinuteOfHour(), endTime.getSecondOfMinute());
        return this.addTimeInterval(localStartTime, localEndTime);
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param timeIntervalString Zeitintervalstring im Format: HH:MM - HH:MM oder ohne Minuten
     */
    public TimeFilter addTimeInterval(String timeIntervalString){
        return this.addTimeInterval(parseTimeInterval(timeIntervalString));
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param timeIntervalString Zeitintervalstring im Format: HH:MM - HH:MM oder ohne Minuten
     */
    public TimeFilter between(String timeIntervalString){
        return this.addTimeInterval(timeIntervalString);
    }


    /**
     * Hilfsfunktion, die einen String in ein Zeitobjekt umwandelt
     *
     * @param timeString Zeitstring im Format: HH:MM or HH
     * @return gibt Zeitobjekt zurück, das eingegebene Zeit repräsentiert
     * @throws RuntimeException, falls String nicht geparsed werden kann
     */
    LocalTime parseTime(String timeString){
        timeString = timeString.trim();
        try {
            int hour;
            int minute = 0;
            if ( timeString.contains(":") || timeString.contains("\\.") ){
                String[] aux;
                if ( timeString.contains(":") )
                    aux = timeString.split(":");
                else
                    aux = timeString.split("\\.");
                hour = Integer.parseInt(aux[0].replaceAll("[\\D]", ""));
                minute = Integer.parseInt(aux[1].replaceAll("[\\D]", ""));
            } else {
                hour = Integer.parseInt(timeString.replaceAll("[\\D]", ""));
            }
            return new LocalTime(hour, minute);
        } catch (Exception e) {
            throw new RuntimeException("'" + timeString + "' not in parsable format H:MM or HH");
        }
    }

    /**
     * Hilfsfunktion, die einen String in ein Datumsobjekt umwandelt
     *
     * @param timeIntervalString Zeitintervalstring im Format: HH:MM - HH:MM oder ohne Minuten
     * @return gibt Zeitarray zurueck, welches eingegebenes Zeitinterval reprasesentiert, start in [0], ende in [1]
     * @throws RuntimeException, falls String nicht geparsed werden kann
     */
    LocalTime[] parseTimeInterval(String timeIntervalString){
        try {
            LocalTime[] interval = new LocalTime[2];
            // Eingabe an "-" splitten
            String[] aux = timeIntervalString.split("-");
            // Start und Enddatum parsen
            interval[0] = parseTime(aux[0].trim());
            interval[1] = parseTime(aux[1].trim());
            return interval;
        } catch(Exception e){
            throw new RuntimeException("'" + timeIntervalString + "' not in parsable format HH:MM - HH");
        }
    }

    /**
     * Filtert die Liste nach den angegebenen Zeitintervallen, falls welche angegben sind
     */
    private void filterByTime(){
        for ( Iterator<Trackpoint> iter = filteredList.iterator(); iter.hasNext(); ){
            Trackpoint trackpoint = iter.next();
            if ( !containedInTimeIntervals(trackpoint) )
                iter.remove();
        }
    }

    boolean containedInTimeIntervals(Trackpoint trackpoint){
        boolean contained = false;
        LocalTime tpTime = new LocalTime(trackpoint.getHour(), trackpoint.getMinute(), trackpoint.getSecond());
        if ( timeIntervals.isEmpty() ){
            contained = tpTime.compareTo(this.startTime) > 0 && tpTime.compareTo(this.endTime) < 0;
        } else {
            for ( LocalTime start : timeIntervals.keySet() ){
                LocalTime end = timeIntervals.get(start);
                contained = tpTime.compareTo(start) > 0 && tpTime.compareTo(end) < 0;
                if ( contained ) break;
            }
        }
        return contained;
    }


     /**
     * Filtert angegebene Trackpointliste und gibt neue gefilterte Liste zurueck
     *
     * @param list zu filternde Trackpointliste, wird nicht veraendert
     * @return neue gefilterte Trackpointliste
     */
     public TrackpointList apply(TrackpointList list){
        this.filterByTime();
        return this.filteredList;
    }

}