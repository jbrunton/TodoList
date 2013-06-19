package com.jbrunton.todolist;

import java.util.Calendar;

public class Moment {

	private Calendar now;
	public void setNow(Calendar now) {
		this.now = now;
	}

	public Moment() {
	}

	public Moment(Calendar now) {
		this.now = now;
	}

	public Calendar getNow() {
		if (this.now == null) {
			return Calendar.getInstance();
		} else {
			return (Calendar) this.now.clone();
		}
	}

	public Calendar getToday() {
		Calendar date = getNow();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		return date;
	}

	// TODO: suspect a number of these methods should be implemented with 'roll', not 'add'?
	// TODO: check, fix and add tests.
	public Calendar getTomorrow() {
		Calendar date = getToday();
		date.add(Calendar.DAY_OF_YEAR, 1);
		return date;
	}

	public Calendar getDayAfterTomorrow() {
		Calendar date = getTomorrow();
		date.add(Calendar.DAY_OF_YEAR, 1);
		return date;
	}

	public Calendar getNextWeek() {
		Calendar date = getToday();
		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);  
		if (dayOfWeek != Calendar.MONDAY)  
		{  
		    // calculate how much to add  
		    // the 2 is the difference between Saturday and Monday  
		    int daysToAdd = (Calendar.SATURDAY - dayOfWeek + 2) % 7;  
		    date.add(Calendar.DAY_OF_YEAR, daysToAdd);  
		}  
		return date;
	}

	public Calendar getWeekAfterNext() {
		Calendar date = getNextWeek();
		date.add(Calendar.WEEK_OF_YEAR, 1);
		return date;
	}

	public Calendar getNextMonth() {
		Calendar date = getToday();
		date.add(Calendar.MONTH, 1);
		date.set(Calendar.DAY_OF_MONTH, 1);
		return date;
	}

	public Calendar getMonthAfterNext() {
		Calendar date = getNextMonth();
		date.add(Calendar.MONTH, 1);
		return date;
	}

}
