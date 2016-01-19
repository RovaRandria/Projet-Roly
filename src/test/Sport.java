package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sport")
public class Sport {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private String id;
		
		@Column(name = "name", length = 50, nullable = false)
		private String name;
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Practice.class)
		@JoinTable(name = "practice_sport", joinColumns = @JoinColumn(name = "practice_id"), inverseJoinColumns = @JoinColumn(name = "sport_id"))
		private List<Practice> practicesList = new ArrayList<Practice>();
		
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
		
		public List<Practice> getPracticesList() {
			return practicesList;
		}

		public void setPracticesList(List<Practice> practicesList) {
			this.practicesList = practicesList;
		}

		@Override
		public String toString() {
			return "Sport [id=" + id + ", name=" + name + "]";
		}
			
}
