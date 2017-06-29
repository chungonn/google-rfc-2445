package com.google.ical.compat.javatime;

import java.time.LocalDateTime;

import java.util.Iterator;

public interface LocalDateTimeIterator extends Iterator<LocalDateTime> {

  /**
   * skips all dates in the series before the given date.
   *
   * @param newStart non null.
   */
  void advanceTo(LocalDateTime newStart);

}
