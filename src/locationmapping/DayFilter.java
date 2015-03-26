package locationmapping;

import java.lang.String;
import java.util.*;

import org.joda.time.*;

public class DayFilter extends Filter {
    /**
     * Startdatum fuer Filter
     */
    LocalDate startDate;
    /**
     * Endatum fuer Filter
     */
    LocalDate endDate;
    /**
     * Array mit Wahrheitswerten uber zu filternde Wochentage, default alle false
     */
    boolean[] weekDays = new boolean[7];

    /**
     * Setzt das Startdatum des Filters
     *
     * @param dateString Das Startdatum im Format: YYYY-MM-DD
     */
    public void setStartDate(String dateString){
        this.startDate = parseDate(dateString);
    }
    /**
     * Setzt das Startdatum des Filters
     *
     * @param date das  Startdatum als Datumsobjekt
     */
    public void setStartDate(Object date){
        this.startDate = new LocalDate(date);
    }

    /**
     * Setzt das Enddatum
     *
     * @param dateString Das Enddatum im Format: YYYY-MM-DD
     */
    public void setEndDate(String dateString){
        this.endDate = parseDate(dateString);
    }
    /**
     * Setzt das Enddatum
     *
     * @param date das Enddatum als Datumsobjekt
     */
    public void setEndDate(Object date){
        this.endDate = new LocalDate(date);
    }

    /**
     * Hilfsfunktion, die einen String in ein Datumsobjekt umwandelt
     *
     * @param dateString Datumsstring der Form YYYY-MM-DD mit beliebigen Trennzeichen
     * @return gibt Datumsobjekt zurück, der die eingegebene Zeit repräsentiert
     * @throws RuntimeException, falls String nicht gepased werden kann
     */
    private LocalDate parseDate(String dateString){
        dateString = dateString.trim();
        try {
            int year = Integer.parseInt(dateString.substring(0,4));
            int month = Integer.parseInt(dateString.substring(5,7));
            int day = Integer.parseInt(dateString.substring(8,10));
            return new LocalDate(year, month, day);
        } catch (Exception e) {
            throw new RuntimeException("'" + dateString + "' not parsable Format YYYY-MM-DD");
        }
    }

    /**
     * Setzt zu filternde Wochentage
     *
     * @param weekDayString Eingabestring mit Wochentagen, der Form "mo - dienstag, friday" (erkennt alles)
     */
    public void setWeekDays(String weekDayString){
        weekDayString.toLowerCase();

        // Loesche alle Leerzeichen um -
        weekDayString = weekDayString.replace(" - ", "-");
        weekDayString = weekDayString.replace(" -", "-");
        weekDayString = weekDayString.replace("- ", "-");

        // Splitte String an allen ',' und ' '
        ArrayList<String> splittedString = new ArrayList<String>();
        String[] aux = weekDayString.split(",");
        for ( String str1 : aux ) {
            for ( String str2 : weekDayString.split(" ") ){
                if ( !str2.isEmpty() )
                    splittedString.add(str2);
            }
        }

        for ( String str : splittedString ){
            if ( str.contains("-") ){
                aux = new String[2];
                try {
                    aux = str.split("-");
                    for( int d=parseWeekDay(aux[0]); d==parseWeekDay(aux[1]); d=dateInc(d) )
                        this.weekDays[d] = true;
                } catch(Exception e) {
                    throw new RuntimeException("'" + str + "' isn't parsable");
                }
            } else {
                this.weekDays[parseWeekDay(str)] = true;
            }
        }
    }

    /**
     * Incrementiert Wochentagszahl, beachtet So nach Sa
     *
     * @param d Zahl zwischen 0 und 6 fuer Wochentag
     * @return Zahl zwischen 0 und 6 fuer folgenden Wochentag
     */
    private int dateInc(int d) {
        if ( d >= 0 && d < 6 )
            return d++;
        if ( d == 6 )
            return 0;
        else
            throw new RuntimeException(d + " doesn't represent a weekday 0-6");
    }

    /**
     * Gibt Zahl des Wochentags aus
     *
     * @param weekDayString Wochentagname der Zeitvariable in deutsch ("montag") oder englisch ("monday"), ausgeschrieben oder mit zwei Buchstaben Abkürzung ("mo")
     * @return Wochentag als Zahl
     */
    public int parseWeekDay(String weekDayString){
        weekDayString.toLowerCase();
        switch(weekDayString){
            case "sonntag":
            case "so":
            case "sunday":
            case "su":
                return 0;
            case "montag":
            case "monday":
            case "mo":
                return 1;
            case "dienstag":
            case "di":
            case "tuesday":
            case "tu":
                return 2;
            case "mittwoch":
            case "mi":
            case "wednesday":
            case "we":
                return 3;
            case "donnerstag":
            case "do":
            case "thursday":
            case "th":
                return 4;
            case "freitag":
            case "friday":
            case "fr":
                return 5;
            case "samstag":
            case "saturday":
            case "sa":
                return 6;
        }
        throw new RuntimeException("'" + weekDayString + "' doesn't represent a weekday");
    }

    private void filterByDate(TrackpointList list){
        Iterator<Trackpoint> iter;
        if ( this.startDate != null ){
            DateTime startDateTime = new DateTime(startDate);
            iter = list.iterator(startDateTime);
        } else
            iter = list.iterator();
        while ( iter.hasNext() ) {
            Trackpoint trackpoint = iter.next();
            if ( this.endDate != null ){
                DateTime endDateTime = new DateTime(endDate);
                if ( trackpoint.compareTimeTo(endDateTime) > 0 )
                    break;
            }
            filteredList.add(trackpoint);
        }
    }

    private void filterByWeekday(TrackpointList list){
        for ( Trackpoint trackpoint : list ){
            if ( weekDays[trackpoint.getDayOfWeek()] )
                filteredList.add(trackpoint);
        }
     }


}