package com.adga.springdc.log;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SpringLoggerT {

	private String rootLoggerLevel;
	private String printedLoggerLevel;
	private boolean enableLogger = true;

	public void setRootLoggerLevel(String rootLoggerLevel) {
		this.rootLoggerLevel = rootLoggerLevel;
	}
	public void setPrintedLoggerLevel(String printedLoggerLevel) {
		this.printedLoggerLevel = printedLoggerLevel;
	}
	public void setEnableLogger(boolean enableLogger) {
		this.enableLogger = enableLogger;
	}

	public void initLogger() {

		if(this.enableLogger) {
			Level rootLevel = Level.parse(rootLoggerLevel);
			Level printedLevel = Level.parse(printedLoggerLevel);

			Logger applicationContextLogger = Logger.getLogger(AnnotationConfigApplicationContext.class.getName());

			Logger loggerParent = applicationContextLogger.getParent();

			loggerParent.setLevel(rootLevel);

			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(printedLevel);
			consoleHandler.setFormatter(new SimpleFormatter());

			loggerParent.addHandler(consoleHandler);
		} else {
			System.out.println("Logger disabled");
		}
	}

	public void initLogger(String rootLoggerLevel, String printedLoggerLevel, boolean enableLogger) {

		if(this.enableLogger) {
			Level rootLevel = Level.parse(rootLoggerLevel);
			Level printedLevel = Level.parse(printedLoggerLevel);

			Logger applicationContextLogger = Logger.getLogger(AnnotationConfigApplicationContext.class.getName());

			Logger loggerParent = applicationContextLogger.getParent();

			loggerParent.setLevel(rootLevel);

			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(printedLevel);
			consoleHandler.setFormatter(new SimpleFormatter());

			loggerParent.addHandler(consoleHandler);
		} else {
			System.out.println("Logger disabled");
		}
	}
}
