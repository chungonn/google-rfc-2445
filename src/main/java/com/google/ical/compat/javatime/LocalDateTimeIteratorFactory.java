package com.google.ical.compat.javatime;

import com.google.ical.iter.RecurrenceIterable;
import com.google.ical.iter.RecurrenceIterator;
import com.google.ical.iter.RecurrenceIteratorFactory;
import com.google.ical.values.DateTimeValueImpl;
import com.google.ical.values.DateValue;
import com.google.ical.values.TimeValue;


import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * a factory for converting RRULEs and RDATEs into
 * <code>Iterator&lt;DateTime&gt;</code> and
 * <code>Iterable&lt;DateTime&gt;</code>.
 *
 * @see RecurrenceIteratorFactory
 *
 * @author mikesamuel+svn@gmail.com (Mike Samuel)
 */
public class LocalDateTimeIteratorFactory {

  /**
   * given a block of RRULE, EXRULE, RDATE, and EXDATE content lines, parse
   * them into a single date time iterator.
   * @param rdata RRULE, EXRULE, RDATE, and EXDATE lines.
   * @param start the first occurrence of the series.
   * @param tz the local timezone -- used to interpret start and any dates in
   *   RDATE and EXDATE lines that don't have TZID params.
   * @param strict true if any failure to parse should result in a
   *   ParseException.  false causes bad content lines to be logged and ignored.
   */
  public static LocalDateTimeIterator createLocalDateTimeIterator(
          String rdata, LocalDateTime start, TimeZone tz, boolean strict)
      throws ParseException {
    return new RecurrenceIteratorWrapper(
        RecurrenceIteratorFactory.createRecurrenceIterator(
            rdata, dateTimeToDateValue(start),
            tz, strict));
  }

  /**
   * given a block of RRULE, EXRULE, RDATE, and EXDATE content lines, parse
   * them into a single date time iterable.
   * @param rdata RRULE, EXRULE, RDATE, and EXDATE lines.
   * @param start the first occurrence of the series.
   * @param tz the local timezone -- used to interpret start and any dates in
   *   RDATE and EXDATE lines that don't have TZID params.
   * @param strict true if any failure to parse should result in a
   *   ParseException.  false causes bad content lines to be logged and ignored.
   */
  public static LocalDateTimeIterable createLocalDateTimeIterable(
      String rdata, LocalDateTime start, TimeZone tz, boolean strict)
      throws ParseException {
    return new RecurrenceIterableWrapper(
        RecurrenceIteratorFactory.createRecurrenceIterable(
            rdata, dateTimeToDateValue(start),
            tz, strict));
  }

  /**
   * creates a date-time iterator given a recurrence iterator from
   * {@link RecurrenceIteratorFactory}.
   */
  public static LocalDateTimeIterator createLocalDateTimeIterator(RecurrenceIterator rit) {
    return new RecurrenceIteratorWrapper(rit);
  }

  private static final class RecurrenceIterableWrapper
      implements LocalDateTimeIterable {
    private final RecurrenceIterable it;

    public RecurrenceIterableWrapper(RecurrenceIterable it) { this.it = it; }

    public LocalDateTimeIterator iterator() {
      return new RecurrenceIteratorWrapper(it.iterator());
    }
  }

  private static final class RecurrenceIteratorWrapper
      implements LocalDateTimeIterator {
    private final RecurrenceIterator it;
    RecurrenceIteratorWrapper(RecurrenceIterator it) { this.it = it; }
    public boolean hasNext() { return it.hasNext(); }
    public LocalDateTime next() { return dateValueToDateTime(it.next()); }
    public void remove() { throw new UnsupportedOperationException(); }
    public void advanceTo(LocalDateTime d) {
      it.advanceTo(dateTimeToDateValue(d));
    }
  }

  static LocalDateTime dateValueToDateTime(DateValue dvUtc) {
    if (dvUtc instanceof TimeValue) {
      TimeValue tvUtc = (TimeValue) dvUtc;
      return LocalDateTime.of(
          dvUtc.year(),
          dvUtc.month(),  // java.util's dates are zero-indexed
          dvUtc.day(),
          tvUtc.hour(),
          tvUtc.minute(),
          tvUtc.second(),
          0);
    } else {
      return LocalDateTime.of(
          dvUtc.year(),
          dvUtc.month(),  // java.util's dates are zero-indexed
          dvUtc.day(),
          0,
          0,
          0,
          0);
    }
  }

  static DateValue dateTimeToDateValue(LocalDateTime dt) {
    return new DateTimeValueImpl(
        dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(),
        dt.getHour(), dt.getMinute(), dt.getSecond());
  }

  private LocalDateTimeIteratorFactory() {
    // uninstantiable
  }
}
