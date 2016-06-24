package com.viseo.fake.db;

import java.util.List;

public interface DAOFacade {

	<T, K> T find(Class<T> clazz, K key);
	
	<T> List<T> getList(String request, Parameter... params);
	
	<T> T merge(T entity);

	<T> void remove(T entity);

	<T> void persist(T entity);

	void clear();

	public static class Parameter{
		String name;
		Object value;

		public Parameter(String name, Object value){
			this.name = name;
			this.value = value;
		}

		public Object getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static Parameter param(String name, Object value){
			return new Parameter(name, value);
		}
	}

}
