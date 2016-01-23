package test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "sport")
public class Sport {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private String id;
		
		@Column(name = "name", length = 50, nullable = false)
		private String name;
		
		public Sport() {
		}

		public Sport(String name) {
			this.name = name;
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "Sport [id=" + id + ", name=" + name + "]";
		}
		
			
}
