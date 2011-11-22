package com.abg.v8api.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.abg.v8api.domain.Person;
import com.abg.v8api.service.PersonService;

@Component("launcher")
public class Launcher {

	@Autowired
	private PersonService customerService;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("Loading context ...");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		Launcher launcher = (Launcher) ctx.getBean("launcher");

		// launcher.getAllPersons();
		// launcher.addPersonSerial(1000);
		int threads = 50;
		int personsPerThread = 10;
		launcher.addPersonParallel(threads, personsPerThread);
	}

	private void getAllPersons() {
		List<Person> personList = customerService.getAll();
	}

	private void addPersonSerial(int persons) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < persons; i++) {
			customerService.add(build("gigi duru", "gigi.duru@gmail.com", 1L));
		}
		System.out.println("add " + persons + " in: " + (System.currentTimeMillis() - start));
	}

	private void addPersonParallel(int no, int persons) {
		for (int i = 0; i < no; i++) {
			Thread t = new Thread(new PersonCreator(persons));
			t.start();
		}
	}

	private Person build(String name, String email, long languageId) {
		Person person = new Person();
		person.setName(name);
		person.setEmail(email);
		person.setLanguageId(languageId);

		return person;

	}

	private class PersonCreator implements Runnable {
		private int persons;

		public PersonCreator(int persons) {
			this.persons = persons;
		}

		public void run() {
			int i = 0;
			while (i++ < persons) {
				customerService.add(build("gigi duru", "gigi.duru@gmail.com", 1L));
			}
		}
	}
}
