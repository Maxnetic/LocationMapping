package locationmapping;

import java.lang.String;
import java.util.*;

import org.joda.time.*;

public class DateTimeFilter extends Filter {
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
    private LinkedHashMap<LocalTime,LocalTime> timeIntervals = new LinkedHashMap<LocalTime,LocalTime>();
    /**
     * Array mit Wahrheitswerten uber zu filternde Wochentage, default alle false
     */
    private boolean[] weekDays = new boolean[8];



    /**
     * Konstruktor fuer DateTimeFilter Objekte
     *
     * @return neues DateTimeFilter Objekt
     */
    public DateTimeFilter(){
        super();
    }


    /**
     * Setzt Startzeit des Filters
     *
     * @param time Startzeit als Zeitobjekt
     */
    public DateTimeFilter setStartTime(LocalTime time){
        this.startTime = time;
        return this;
    }
    /**
     * Setzt Startzeit des Filters
     *
     * @param time Startzeit als Datumsobjekt
     */
    public DateTimeFilter setStartTime(DateTime time){
        this.setStartTime(new LocalTime(time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute()));
        return this;
    }
    /**
     * Setzt Startzeit des Filters
     *
     * @param timeString Startzeit im Format: 8:30 oder 18
     */
    public DateTimeFilter setStartTime(String timeString){
        return this.setStartTime(parseTime(timeString));
    }
    /**
     * Setzt Startzeit des Filters
     *
     * @param timeString Startzeit im Format: 8:30 oder 18
     */
    public DateTimeFilter fromTime(String timeString){
        return this.setStartTime(timeString);
    }


    /**
     * Setzt Endzeit des Filters
     *
     * @param time Endzeit als Zeitobjekt
     */
    public DateTimeFilter setEndTime(LocalTime time){
        this.endTime = time;
        return this;
    }
    /**
     * Setzt Endzeit des Filters
     *
     * @param time Endzeit als Datumsobjekt
     */
    public DateTimeFilter setEndTime(DateTime time){
        this.setEndTime(new LocalTime(time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute()));
        return this;
    }
    /**
     * Setzt Endzeit des Filters
     *
     * @param timeString Endzeit im Format: 8:30 oder 18
     */
    public DateTimeFilter setEndTime(String timeString){
        return this.setEndTime(parseTime(timeString));
    }
    /**
     * Setzt Endzeit des Filters
     *
     * @param timeString Endzeit im Format: 8:30 oder 18
     */
    public DateTimeFilter toTime(String timeString){
        return this.setEndTime(timeString);
    }



    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param timeInterval hinzuzufuegendes Zeitinterval mit Startzeit in [0] und Endzeit in [1]
     */
    public DateTimeFilter addTimeInterval(LocalTime[] timeInterval){
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
    public DateTimeFilter addTimeInterval(LocalTime startTime, LocalTime endTime){
        this.timeIntervals.put(startTime, endTime);
        return this;
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param startTime Startpunkt des hinzuzufuegenden Zeitinterval
     * @param endTime Endpunkt des hinzuzufuegenden Zeitinterval
     */
    public DateTimeFilter addTimeInterval(DateTime startTime, DateTime endTime){
        LocalTime localStartTime = new LocalTime(startTime.getHourOfDay(), startTime.getMinuteOfHour(), startTime.getSecondOfMinute());
        LocalTime localEndTime = new LocalTime(endTime.getHourOfDay(), endTime.getMinuteOfHour(), endTime.getSecondOfMinute());
        return this.addTimeInterval(localStartTime, localEndTime);
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param timeIntervalString Zeitintervalstring im Format: HH:MM - HH:MM oder ohne Minuten
     */
    public DateTimeFilter addTimeInterval(String timeIntervalString){
        return this.addTimeInterval(parseTimeInterval(timeIntervalString));
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param timeIntervalString Zeitintervalstring im Format: HH:MM - HH:MM oder ohne Minuten
     */
    public DateTimeFilter betweenTimes(String timeIntervalString){
        return this.addTimeInterval(timeIntervalString);
    }





    /**
     * Setzt das Startdatum des Filters
     *
     * @param date das Startdatum als Datumsobjekt
     */
    public DateTimeFilter setStartDate(DateTime date){
        this.startDate = date;
        return this;
    }
    /**
     * Setzt das Startdatum des Filters
     *
     * @param dateString Das Startdatum im Format: YYYY/MM/DD oder D.M.YY bzw DD.MM.YYYY
     */
    public DateTimeFilter setStartDate(String dateString){
        LocalDate date = parseDate(dateString);
        return setStartDate(new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), 0, 0));
    }
    /**
     * Setzt das Startdatum des Filters
     *
     * @param dateString Das Startdatum im Format: YYYY/MM/DD oder D.M.YY bzw DD.MM.YYYY
     */
    public DateTimeFilter fromDate(String dateString){
        return this.setStartDate(dateString);
    }


    /**
     * Setzt das Enddatum
     *
     * @param date das Enddatum als Datumsobjekt
     */
    public DateTimeFilter setEndDate(DateTime date){
        this.endDate = date;
        return this;
    }
    /**
     * Setzt das Enddatum
     *
     * @param dateString Das Enddatum im Format: YYYY/MM/DD oder D.M.YY bzw DD.MM.YYYY
     */
    public DateTimeFilter setEndDate(String dateString){
        LocalDate date = parseDate(dateString);
        return setEndDate(new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), 23, 59, 59));
    }
    /**
     * Setzt das Enddatum
     *
     * @param dateString Das Enddatum im Format: YYYY/MM/DD oder D.M.YY bzw DD.MM.YYYY
     */
    public DateTimeFilter toDate(String dateString){
        return this.setEndDate(dateString);
    }

    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param dateInterval hinzuzufuegendes Zeitinterval
     */
    public DateTimeFilter addDayInterval(Interval dateInterval){
        this.dateIntervals.add(dateInterval);
        return this;
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param startDate Startpunkt des hinzuzufuegenden Zeitinterval
     * @param endDate Endpunkt des hinzuzufuegenden Zeitinterval
     */
    public DateTimeFilter addDayInterval(DateTime startDate, DateTime endDate){
        return this.addDayInterval(new Interval(startDate, endDate));
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param dateIntervalString Datumsintervalstring im Format: YYYY/MM/DD-YYYY/MM/DD oder D.M.YY-D.M.YY
     */
    public DateTimeFilter addDayInterval(String dateIntervalString){
        return this.addDayInterval(parseDateInterval(dateIntervalString));
    }
    /**
     * Fuegt zu filterndes Zeitinterval hinzu
     *
     * @param dateIntervalString Datumsintervalstring im Format: YYYY/MM/DD-YYYY/MM/DD oder D.M.YY-D.M.YY
     */
    public DateTimeFilter betweenDates(String dateIntervalString){
        return this.addDayInterval(dateIntervalString);
    }

    /**
     * Fuegt Tag zu den zu filternden Zeitintervallen hinzu
     *
     * @param dateInterval hinzuzufuegendes Zeitinterval
     */
    public DateTimeFilter setDay(LocalDate day){
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
    public DateTimeFilter setDay(String dayString){
        return this.setDay(parseDate(dayString));
    }
    /**
     * Fuegt Tag zu den zu filternden Zeitintervallen hinzu
     *
     * @param dayString Datumsstring des hinzuzufuegenden Tages im Format YYYY/MM/DD und D.M.YY
     */
    public DateTimeFilter onDate(String dayString){
        return this.setDay(dayString);
    }

    /**
     * Fuegt Tage zu den zu filternden Zeitintervallen hinzu
     *
     * @param daysString Datumsstring als kommagetrennter Werte im Format YYYY/MM/DD und D.M.YY
     * @throws RuntimeException, falls String nicht geparsed werden kann
     */
    public DateTimeFilter setDays(String daysString){
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
    public DateTimeFilter forDays(String daysString){
        return this.setDays(daysString);
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
    public DateTimeFilter setWeekDays(String weekDayString){
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
    public DateTimeFilter forWeekdays(String weekDayString){
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
     * Filtert die Liste nach den angegebenen Zeitintervallen, falls welche angegben sind
     */
    private void filterByTime(){
        for ( Iterator<Trackpoint> iter = filteredList.iterator(); iter.hasNext(); ){
            Trackpoint trackpoint = iter.next();
            if ( !containedInTimeIntervals(trackpoint) )
                iter.remove();
        }
    }

    /**
     * Hilfsmethode um zu ueberpruefen, ob Trackpoint in einem der spezifizierten Zeitintervalle liegt
     *
     * @param trackpoint Trackpoint, der ueberprueft werden soll
     * @return Wahrheitswert darueber, ob Trackpoint in einem der Filter Zeitintervalle liegt, falls keines spezifiziert ist immer whar
     */
    boolean containedInTimeIntervals(Trackpoint trackpoint){
        boolean contained = false;
        LocalTime tpTime = new LocalTime(trackpoint.getHour(), trackpoint.getMinute(), trackpoint.getSecond());
        if ( !this.timeIntervals.isEmpty() ){
            // Zeitintervalle gesetzte
            for ( LocalTime start : this.timeIntervals.keySet() ){
                LocalTime end = this.timeIntervals.get(start);
                contained = tpTime.compareTo(start) > 0 && tpTime.compareTo(end) < 0;
                if ( contained ) break;
            }
        } else if ( this.startTime != null && this.endTime != null ){
            // Start- und Endzeit gesetzte
            contained = tpTime.compareTo(this.startTime) > 0 && tpTime.compareTo(this.endTime) < 0;
        } else {
            // kein Zeitfilter gesetzt
            contained = true;
        }
        return contained;
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
        this.filterByTime();

        return this.filteredList;
    }
}