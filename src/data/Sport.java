package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "sport")
public class Sport {
		/*@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private String id;*/
		
		@Id
		@Column(name = "name", length = 50, nullable = false)
		private String name;
		
		@Column(name = "description", length = 150)
		private String description;

		public Sport() {
		}
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}


		public Sport(String name) {
			this.name = name;
		}
		
		/*
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}*/

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
