package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;

@Entity
@Table(name="entries")
public class Entry extends Model {
	public String word;
	
	public static final Finder<Long, Entry> find = new Finder<>(Entry.class);
	
	public static Entry getRandomEntry() {
		String sql = "SELECT word FROM word_scramble.entries\n" + 
				 	 "ORDER BY RAND()\n" + 
				 	 "LIMIT 1";
		RawSql rawSql = RawSqlBuilder.parse(sql).create();
		
		List<Entry> randomEntry = Entry.find.query().setRawSql(rawSql).findList();
		
		if (!randomEntry.isEmpty()) {
			return randomEntry.get(0);
		}		
		return null;
	}
	
	public static boolean isWordExists(String word) {
		List<Entry> entries = Entry.find.query().select("word").setDistinct(true).where().eq("word", word).findList();
		
		if (entries.isEmpty()) {
			return false;
		}
		return true;
	}
}
