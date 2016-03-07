package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Fields for a sport
 * @author Rova
 *
 */
@Entity
@Table(name = "sport")
public class Sport {
		
		@Id
		@Column(name = "name", length = 50, nullable = false)
		private String name;
		
		@Column(name = "description", length = 150)
		private String description;

		public Sport() {
		}
		public Sport(String name) {
			this.name = name;
		}
		public Sport(String name, String description) {
			this.name = name;
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "Sport [name=" + name + "]";
		}
		
			
}
