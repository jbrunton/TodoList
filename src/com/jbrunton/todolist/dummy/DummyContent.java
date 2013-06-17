package com.jbrunton.todolist.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<Task> ITEMS = new ArrayList<Task>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

	static {
		// Add 3 sample items.
		addItem(new Task("1", "Item 1"));
		addItem(new Task("2", "Item 2"));
		addItem(new Task("3", "Item 3"));
	}

	private static void addItem(Task item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * An item representing a piece of content.
	 */
	public static class Task {
		public String id;
		public String title;
		public boolean complete;
		public String details;

		public Task(String id, String content) {
			this.id = id;
			this.title = content;
		}

		@Override
		public String toString() {
			return title;
		}
	}
}
