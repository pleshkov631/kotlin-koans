package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int {
        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }
}


operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(n:Int): MultiTimeInterval{
    return MultiTimeInterval(this, n)
}

class MultiTimeInterval(val timeInterval: TimeInterval, val num: Int = 1)

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {
    operator fun contains(d: MyDate): Boolean {
        return start <= d && endInclusive >= d
    }

    override operator fun iterator(): Iterator<MyDate>  = DateIterator(this)
}

class DateIterator(val dateRange: DateRange): Iterator<MyDate>{

    var current: MyDate = dateRange.start

    override fun hasNext(): Boolean {
        return current <= dateRange.endInclusive
    }

    override fun next(): MyDate {
        var temp = current
        current = current.nextDay()
        return temp
    }
}

operator fun MyDate.plus(timeInterval: TimeInterval):MyDate {
     return this.addTimeIntervals(timeInterval, 1)
}


operator fun MyDate.plus(multiTimeInterval: MultiTimeInterval):MyDate {
    return this.addTimeIntervals(multiTimeInterval.timeInterval, multiTimeInterval.num)
}

