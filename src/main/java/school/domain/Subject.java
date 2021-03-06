/*
 * Copyright [2011-2016] "Neo Technology"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */
package school.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Subject {

	@GraphId
	private Long id;

	private String name;

	@Relationship(type = "CURRICULUM", direction = Relationship.INCOMING)
	private Department department;

	@Relationship(type = "TAUGHT_BY")
	private Set<Teacher> teachers;

	@Relationship(type = "SUBJECT_TAUGHT")
	private Set<Course> courses;

	public Subject(String name) {
		this();
		this.name = name;
	}

	public Subject() {
		this.teachers = new HashSet<>();
		this.courses = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public Department getDepartment() {
		return department;
	}

	public String getName() {
		return name;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	@Override
	public String toString() {
		return "Subject{" +
				"id=" + getId() +
				", name='" + name + '\'' +
				", department=" + department +
				", teachers=" + teachers.size() +
				'}';
	}

	public void updateFrom(Subject subject) {
		this.name = subject.getName();
	}
}
