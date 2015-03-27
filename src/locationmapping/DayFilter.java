package locationmapping;

import java.lang.String;
import java.util.*;

import org.joda.time.*;

public class DayFilter extends Filter {
    /**
     * Startdatum fuer Filter
     */
    private DateTime startDate;
    /**
     * Endatum fuer Filter
     */
    private DateTime endDate;
    /**
     * Liste mit Datumsintervallen, die gefiltert werden sollen;
     */
    private ArrayList<Interval> dateIntervals = new ArrayList<Interval>();
    /**
     * Array mit Wahrheitswerten uber zu filternde Wochentage, default alle false
     */
    private boolean[] weekDays = new boolean[8];


    /**
     * Konstruktor für DayFilter Objekte
     *
     * @return neues DayFilter Objekt
     */
    public DayFilter(){
        super();
    }


    /**
     * Setzt das Startdatum des Filters
     *
     * @param date das  Startdatum als Datumsobjekt
     */
    public DayFilter setStartDate(DateTime date){
        this.startDate = date;
        return this;
    }
    /**
     * Setzt das Startdatum des Filters
     *
     * @param dateString Das Startdatum im Format: YYYY/MM/DD oder D.M.YY bzw DD.MM.YYYY
     */
    public DayFilter setStartDate(String dateString){
        LocalDate date = parseDate(dateString);
        return setStartDate(new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), 0, 0));
    }
    /**
     * Setzt das Startdatum des Filters
     *
     * @param dateString Das Startdatum im Format: YYYY/MM/DD oder D.M.YY bzw DD.MM.YYYY
     */
    public DayFilter from(String dateString){
        return this.setStartDate(dateString);
    }


    /**
     * Setzt das Enddatum
     *
     * @param date das Enddatum als Datumsobjekt
     */
    public DayFilter setEndDate(DateTime date){
        this.endDate = date;
        return this;
    }
    /**
     * Setzt das Enddatum
     *
     * @param dateString Das Enddatum im Format: YYYY/MM/DD oder D.M.YY bzw DD.MM.YYYY
     */
    public DayFilter setEndDate(String dateString){
        LocalDate date = parseDate(dateString);
        return setEndDate(new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), 23, 59, 59));
    }
    /**
     * Setzt das Enddatum
     *
     * @param dateString Das Enddatum im Format: YYYY/MM/DD oder D.M.YY bzw DD.MM.YYYY
     */
    public DayFilter to(String dateString){
        return this.setEndDate(dateString);
    }

    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param dateInterval hinzuzufuegendes Zeitinterval
     */
    public DayFilter addDayInterval(Interval dateInterval){
        this.dateIntervals.add(dateInterval);
        return this;
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param startDate Startpunkt des hinzuzufuegenden Zeitinterval
     * @param endDate Endpunkt des hinzuzufuegenden Zeitinterval
     */
    public DayFilter addDayInterval(DateTime startDate, DateTime endDate){
        return this.addDayInterval(new Interval(startDate, endDate));
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param dateIntervalString Datumsintervalstring im Format: YYYY/MM/DD-YYYY/MM/DD oder D.M.YY-D.M.YY
     */
    public DayFilter addDayInterval(String dateIntervalString){
        return this.addDayInterval(parseDateInterval(dateIntervalString));
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param dateIntervalString Datumsintervalstring im Format: YYYY/MM/DD-YYYY/MM/DD oder D.M.YY-D.M.YY
     */
    public DayFilter between(String dateIntervalString){
        return this.addDayInterval(dateIntervalString);
    }

    /**
     * Fuegt Tag zu den zu filternden Zeitintervallen hinzu
     *
     * @param dateInterval hinzuzufuegendes Zeitinterval
     */
    public DayFilter setDay(LocalDate day){
        DateTime start = new DateTime(day.getYear(), day.getMonthOfYear(), day.getDayOfMonth(), 0, 0);
        DateTime end = new DateTime(day.getYear(), day.getMonthOfYear(), day.getDayOfMonth(), 23, 59, 59);
        this.dateIntervals.add(new Interval(start, end));
        return this;
    }
    /**
     * Fuegt Tag zu den zu filternden Zeitintervallen hinzu
     *
     * @param dayString Datumsstring des hinzuzufuegenden Tages im Format YYYY/MM/DD und D.M.YY
     */
    public DayFilter setDay(String dayString){
        return this.setDay(parseDate(dayString));
    }
    /**
     * Fuegt Tag zu den zu filternden Zeitintervallen hinzu
     *
     * @param dayString Datumsstring des hinzuzufuegenden Tages im Format YYYY/MM/DD und D.M.YY
     */
    public DayFilter on(String dayString){
        return this.setDay(dayString);
    }

    /**
     * Fuegt Tage zu den zu filternden Zeitintervallen hinzu
     *
     * @param daysString Datumsstring als kommagetrennter Werte im Format YYYY/MM/DD und D.M.YY
     * @throws RuntimeException, falls String nicht geparsed werden kann
     */
    public DayFilter setDays(String daysString){
        String[] aux = daysString.split(",");
        for ( String str : aux ) {
            try {
                this.setDay(str.trim());
            } catch(Exception e){
                throw new RuntimeException("'" + daysString + "' not in parsable format comma-seperated YYYY/MM/DD and D.M.YY");
            }
        }
        return this;
    }
    /**
     * Fuegt Tage zu den zu filternden Zeitintervallen hinzu
     *
     * @param daysString Datumsstring als kommagetrennter Werte im Format YYYY/MM/DD und D.M.YY
     * @throws RuntimeException, falls String nicht geparsed werden kann
     */
    public DayFilter forDays(String daysString){
        return this.setDays(daysString);
    }


    /**
     * Hilfsfunktion, die einen String in ein Datumsobjekt umwandelt
     *
     * @param dateString Datumsstring im Format: YYYY/MM/DD oder D.M.YY bzw DD.MM.YYYY
     * @return gibt Datumsobjekt zurück, der die eingegebene Zeit repräsentiert
     * @throws RuntimeException, falls String nicht geparsed werden kann
     */
    LocalDate parseDate(String dateString){
        dateString = dateString.trim();
        try {
            int year, month, day;
            if ( dateString.contains(".") ){
                String[] aux = dateString.split("\\.");
                day = Integer.parseInt(aux[0].replaceAll("[\\D]", ""));
                month = Integer.parseInt(aux[1].replaceAll("[\\D]", ""));
                year = Integer.parseInt(aux[2].replaceAll("[\\D]", ""));
                if ( year < 100 ){
                    if ( year < (new DateTime().getYear()-2000) )
                        year += 2000;
                    else
                        year += 1900;
                }
            } else {
                year = Integer.parseInt(dateString.substring(0,4));
                month = Integer.parseInt(dateString.substring(5,7));
                day = Integer.parseInt(dateString.substring(8,10));
            }
            return new LocalDate(year, month, day);
        } catch (Exception e) {
            throw new RuntimeException("'" + dateString + "' not in parsable format YYYY-MM-DD or D.M.YY");
        }
    }

    /**
     * Hilfsfunktion, die einen String in ein Datumsintervalobjekt umwandelt
     *
     * @param dateIntervalString Datumsintervalstring im Format: YYYY/MM/DD-YYYY/MM/DD oder D.M.YY-D.M.YY
     * @return gibt Zeitintervalobjekt zurueck, welches eingegebenes Zeitinterval reprasesentiert
     * @throws RuntimeException, falls String nicht geparsed werden kann
     */
    Interval parseDateInterval(String dateIntervalString){
        try {
            // Eingabe an "-" splitten
            String[] aux = dateIntervalString.split("-");
            // Start und Enddatum parsen
            LocalDate startDate = parseDate(aux[0].trim());
            LocalDate endDate = parseDate(aux[1].trim());
            DateTime start = new DateTime(startDate.getYear(), startDate.getMonthOfYear(), startDate.getDayOfMonth(), 0, 0);
            DateTime end = new DateTime(endDate.getYear(), endDate.getMonthOfYear(), endDate.getDayOfMonth(), 23, 59, 59);
            return new Interval(start, end);
        } catch(Exception e){
            throw new RuntimeException("'" + dateIntervalString + "' not in parsable format YYYY/MM/DD-YYYY/MM/DD or D.M.YY-D.M.YY");
        }
    }

    /**
     * Setzt zu filternde Wochentage
     *
     * @param weekDayString Eingabestring mit Wochentagen, der Form "mo - dienstag, friday" (erkennt alles)
     */
    public DayFilter setWeekDays(String weekDayString){
        weekDayString.toLowerCase();

        // Loesche alle Leerzeichen um -
        weekDayString = weekDayString.replace(" - ", "-");
        weekDayString = weekDayString.replace(" -", "-");
        weekDayString = weekDayString.replace("- ", "-");

        // Splitte String an allen ',' und ' '
        ArrayList<String> splittedString = new ArrayList<String>();
        String[] aux = weekDayString.split(",");
        for ( String str1 : aux ) {
            for ( String str2 : str1.split(" ") ){
                if ( !str2.isEmpty() )
                    splittedString.add(str2);
            }
        }

        // Parse Tage aus String
        for ( String str : splittedString ){
            if ( str.contains("-") ){
                // Parse Wochentag-Ranges aus String
                aux = new String[2];
                try {
                    aux = str.split("-");
                    int d = parseWeekDay(aux[0]);
                    while( d != dateInc(parseWeekDay(aux[1])) ){
                        this.weekDays[d] = true;
                        d = dateInc(d);
                    }
                } catch(Exception e) {
                    throw new RuntimeException("'" + str + "' isn't parsable");
                }
            } else {
                // Parse einzelne Tage aus String
                this.weekDays[parseWeekDay(str)] = true;
            }
        }
        return this;
    }
    /**
     * Setzt zu filternde Wochentage
     *
     * @param weekDayString Eingabestring mit Wochentagen, der Form "mo - dienstag, friday" (erkennt alles)
     */
    public DayFilter forWeekdays(String weekDayString){
        return setWeekDays(weekDayString);
    }

    /**
     * Incrementiert Wochentagszahl, beachtet So nach Sa
     *
     * @param d Zahl zwischen 0 und 6 fuer Wochentag
     * @return Zahl zwischen 0 und 6 fuer folgenden Wochentag
     */
    private int dateInc(int d) {
        if ( d > 0 && d < 7 )
            return d+1;
        if ( d == 7 )
            return 1;
        else
            throw new RuntimeException(d + " doesn't represent a weekday 1-7");
    }

    /**
     * Gibt Zahl des Wochentage aus
     *
     * @param weekDayString Wochentagname der Zeitvariable in deutsch ("montag") oder englisch ("monday"), ausgeschrieben oder mit zwei Buchstaben Abkürzung ("mo")
     * @return Wochentag als Zahl mo=1 bis so=7
     */
    public int parseWeekDay(String weekDayString){
        weekDayString.toLowerCase();
        switch(weekDayString){
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
            case "sonntag":
            case "so":
            case "sunday":
            case "su":
                return 7;
        }
        throw new RuntimeException("'" + weekDayString + "' doesn't represent a weekday");
    }

    /**
     * Initialisiert die filteredList und schreankt sie dabei evtl. auf gesetzte Zeitintervalle fest
     *
     * @param list Trackpointliste, die gefiltert werden soll
     */
    private void setFilteredList(TrackpointList list){
        Iterator<Trackpoint> iter;

        if ( !dateIntervals.isEmpty() ){
            // Falls Intervalle gesetzt wurden, verwende diese zum Filtern
            for ( Interval interval : dateIntervals ){
                iter = list.iterator(interval.getStart());
                while ( iter.hasNext() ){
                    Trackpoint trackpoint = iter.next();
                    if ( trackpoint.compareTimeTo(interval.getEnd()) > 0 )
                        break;
                    filteredList.add(trackpoint);
                }
            }
        } else {
            // Falls keine Intervalle gesetzt wurden nutze Startpunkt oder starte vorne in Liste
            if ( this.startDate != null ){
                if ( list.getFirst().compareTimeTo(this.startDate) > 0 )
                    System.out.println("startdate of filter " + startDate + " before first entry in list " + list.getFirst().getTime());
                iter = list.iterator(startDate);
            } else {
                iter = list.iterator();
            }
            while ( iter.hasNext() ) {
                Trackpoint trackpoint = iter.next();
                if ( this.endDate != null ){
                    if ( trackpoint.compareTimeTo(endDate) > 0 )
                        break;
                }
                filteredList.add(trackpoint);
            }
        }
    }

    /**
     * Filtered die Liste nach den angegebenen Wochentagen, falls welche angegben sind
     */
    private void filterByWeekday(){
        if ( this.weekDaysSet() ){
            for ( Iterator<Trackpoint> iter = filteredList.iterator(); iter.hasNext(); ){
                Trackpoint trackpoint = iter.next();
                if ( !weekDays[trackpoint.getDayOfWeek()] )
                    iter.remove();
            }
        }
    }

    /**
     * Tested ob ein Wochentagfilter gesetzt ist
     *
     * @return Wahrheitswert ob Wochentagfilter gesetzt ist
     */
    boolean weekDaysSet(){
        for ( boolean b : this.weekDays ){
            if ( b ) return true;
        }
        return false;
    }

     /**
     * Filtert angegebene Trackpointliste und gibt neue gefilterte Liste zurueck
     *
     * @param list zu filternde Trackpointliste, wird nicht veraendert
     * @return neue gefilterte Trackpointliste
     */
     public TrackpointList apply(TrackpointList list){
        this.setFilteredList(list);

        this.filterByWeekday();
        return this.filteredList;
    }

}