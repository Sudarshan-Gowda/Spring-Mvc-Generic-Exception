package com.star.sud.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BasicController {

	private static final Logger logger = Logger.getLogger(BasicController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginPage(Model model) {

		return "index/index";

	}

	@RequestMapping(value = "/{type:.+}", method = RequestMethod.GET)
	public String getPages(Model model, @PathVariable("type") String type, HttpServletRequest request) {

		String loc = "D://log-file/admin.log";

		// updateLog4jConfiguration(loc + "admin" + ".log");
		updateLog4jConfiguration(loc);
		logger.debug("-- Debug message --");
		logger.info("-- Displaying info message--");

		if ("class-cast".equals(type)) {

			Object i = Integer.valueOf(42);
			String s = (String) i;
			System.out.println(s);

		} else if ("arithmatic".equals(type)) {

			int r = 10 / 0;
			System.out.println(r);

		}

		else if ("null-pointer".equals(type)) {

			String val = null;
			System.out.println(val.toString());

		}

		else if ("nub-form-exp".equals(type)) {

			String val = "Test";
			int i = Integer.parseInt(val);
			System.out.println(i);

		}

		return "welcome/welcome-page";

	}

	private void updateLog4jConfiguration(String logFile) {
		Properties props = new Properties();
		try {
			InputStream configStream = getClass().getResourceAsStream("/log4j.properties");
			props.load(configStream);
			configStream.close();
		} catch (IOException e) {
			System.out.println("Error not laod configuration file ");
		}

		props.setProperty("log4j.rootLogger", "DEBUG,Appender2");
		props.setProperty("log4j.appender.Appender2", "org.apache.log4j.RollingFileAppender");
		props.setProperty("log4j.appender.Appender2.File", logFile);
		props.setProperty("log4j.appender.Appender2.MaxFileSize", "10MB");
		props.setProperty("log4j.appender.Appender2.layout", "org.apache.log4j.PatternLayout");

		props.setProperty("log4j.appender.Appender2.layout.ConversionPattern",
				"%d{dd MMM yyyy HH:mm:ss} [%t] %-5p %c %x - %m%n");

		props.setProperty("log4j.logger.org.hibernate", "error");
		props.setProperty("log4j.logger.org.springframework", "error");
		props.setProperty("log4j.logger.org.thymeleaf", "error");

		LogManager.resetConfiguration();
		PropertyConfigurator.configure(props);

	}

}
