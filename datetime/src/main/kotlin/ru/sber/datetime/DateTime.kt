package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*
import kotlin.collections.ArrayList

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val result = ArrayList<String>()
    val zones = ZoneId.getAvailableZoneIds()
    zones.forEach { zone ->
        if (ZoneId.of(zone).rules.getOffset(Instant.now()).totalSeconds % 3600 != 0)
            result.add(zone)
    }
    return result.sorted().toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val result = ArrayList<String>(12)
    Month.values().forEach { month ->
        result.add(LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString())
    }
    return result
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var result = 0
    for (i in 1..12) {
        if (LocalDate.of(year, i, 13).dayOfWeek == DayOfWeek.FRIDAY)
            result++
    }
    return result
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm", Locale.US).format(dateTime)
}



