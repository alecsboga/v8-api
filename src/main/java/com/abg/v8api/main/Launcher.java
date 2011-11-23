package com.abg.v8api.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.abg.v8api.domain.User;
import com.abg.v8api.exception.ApplicationException;
import com.abg.v8api.exception.SystemException;
import com.abg.v8api.service.UserService;

@Component("launcher")
public class Launcher {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("Loading context ...");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		Launcher launcher = (Launcher) ctx.getBean("launcher");

		// launcher.getAllUsers();
		// launcher.addInSerial(1000);
		int threads = 50;
		int usersPerThread = 10;
		launcher.addInParallel(threads, usersPerThread);
	}

	protected void getAllUsers() {
		List<User> userList = userService.getAll();
	}

	protected void addInSerial(int users) throws SystemException, ApplicationException {
		long start = System.currentTimeMillis();
		for (int i = 0; i < users; i++) {
			userService.add(new User("gigi duru", "gigi.duru@gmail.com"));
		}
		System.out.println("add " + users + " in: " + (System.currentTimeMillis() - start));
	}

	private void addInParallel(int no, int persons) {
		for (int i = 0; i < no; i++) {
			Thread t = new Thread(new UserCreator(persons));
			t.start();
		}
	}

	private class UserCreator implements Runnable {
		private int users;

		public UserCreator(int users) {
			this.users = users;
		}

		public void run() {
			int i = 0;
			while (i++ < users) {
				try {
					userService.add(new User("gigi duru", "gigi.duru@gmail.com"));
				} catch (SystemException e) {
					e.printStackTrace();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
