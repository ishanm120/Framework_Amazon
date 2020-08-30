package utilsPackage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.dom4j.DocumentException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EmailCode extends CommonUtils{

	public static int totaltestcases;
	public static int passTestCases;
	public static float passPercentage;
	public static float pass;
	public static String totalTime;
	public static String totalTestCases;
	public static String totalFail;
	public static String totalPass;
	public static String senderEmailId= properties.getProperty("senderEmail");
	public static String senderEmailPassword= properties.getProperty("senderPassword");
	public static String[] recipitentEmail= CommonUtils.properties.getProperty("recipitentEmail").split(";");
	public static String secondaryRecipitant= CommonUtils.properties.getProperty("secondaryRecipitant");
	public static String reportEmailSubject= CommonUtils.properties.getProperty("reportEmailSubject");
	public static String minimumPassPercentage= CommonUtils.properties.getProperty("minimumPassPercentage");

	public static void sendEmailReport(String reportfolderPath, String reportPath, String emailSubject,
			String senderEmail, String senderPassword, String[] recipient, String minimumPasspercentage)
			throws EmailException {
		try {

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			Date date = new Date();
			String d = formatter.format(date);
			pass = Float.parseFloat(minimumPasspercentage);
			String content = getHTMLContent1(reportfolderPath);
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(senderEmail, senderPassword));
			email.setSSL(true);
			email.setFrom(senderEmail);
			email.setSubject(emailSubject.replace("_", " ") + " " + d);
			if (passPercentage > Float.parseFloat(minimumPasspercentage)
					|| passPercentage == Float.parseFloat(minimumPasspercentage)) {
				for (String recipitentmail : recipitentEmail) {
					email.addTo(recipitentmail);
				}
			} else {
				logger.info(
						"Pass percentage " + passPercentage + " is less than expeceted " + minimumPasspercentage);
				email.addTo(secondaryRecipitant);
			}
			MimeMultipart multipart = setMulti(reportPath, content);
			email.setContent(multipart);
			email.send();
			logger.info("-------------- Email sent Successfully -----------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static MimeMultipart setMulti(String reportPath, String htmlContent) throws MessagingException {
		MimeMultipart multipart = new MimeMultipart();
		multipart.addBodyPart(fileAttachment(reportPath));
		multipart.addBodyPart(addBody(htmlContent));
		return multipart;
	}

	public static BodyPart addBody(String htmlContent) throws MessagingException {
		BodyPart body = new MimeBodyPart();
		body.setContent(htmlContent, "text/html");
		return body;

	}

	public static MimeBodyPart fileAttachment(String filepath) throws MessagingException {
		DataSource source = new FileDataSource(filepath);
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();
		messageBodyPart2.setDataHandler(new DataHandler(source));
		messageBodyPart2.setFileName(CommonUtils.suiteName+"_Report.html");
		return messageBodyPart2;
	}

	private static void parseReports1(String folder) throws IOException {
		List<File> lstFiles = getHTMLFiles(folder);
		for (File f : lstFiles) {
			Document doc = Jsoup.parse(f, "UTF-8");
			Elements list1 = doc.select("div[class='card-panel dashboard-categories'] table tbody tr+tr");
			Elements totalteststime = doc.select("div[class='col s2'] div div[class='panel-lead']");
			Elements passfail = doc.select("div[class='block text-small'] span");

			totalTestCases = totalteststime.get(0).text();

			totalTime = totalteststime.get(4).text();

			totalPass = passfail.get(1).text();

			totalFail = passfail.get(2).text();

			totaltestcases = Integer.parseInt(totalTestCases);

			passTestCases = Integer.parseInt(totalPass);
			passPercentage = (passTestCases * 100) / totaltestcases;
			logger.info("Pass percentage is " + passPercentage);
		}
	}

	@SuppressWarnings("unused")
	private static String getHTMLContent1(String path)
			throws DocumentException, IOException, ParserConfigurationException {
		parseReports1(path);
		String text = "";
		String passpercent = "";
		int count = 0;

		if (passPercentage > pass || passPercentage == pass) {
			passpercent = "<p><b><font face=verdana color=black>Total Pass Percentage: <font color=green>"
					+ passPercentage + "%</font></p></font></b>";
		} else {
			passpercent = "<p><b><font face=verdana color=black>Total Pass Percentage: <font color=red>"
					+ passPercentage + "%</font></p></font></b>";

		}
		final String summary = "<h1 align='center'>Automation Summary Report</h1> "
				+ "<p><b><font face=verdana color=black> Total Time Taken: " + totalTime
				+ "</font></b></p><p><b><font face=verdana color=black> Total Test Cases: " + totalTestCases
				+ "</font></b></p><p><b><font face=verdana color=green> Total Pass Count: " + totalPass
				+ "</font></b></p><p><b><font face=verdana color=red> Total Fail Count: " + totalFail
				+ "</font></b></p>" + passpercent;
		final String note1 = "<div style=" + "clear:both"
				+ "><importantLink><font face=verdana color=red><i><br> *Note: Detailed Report is attached in the email. To view the detailed results download it and open it in browser window.</i></importantLink></font></div>";
		return summary + note1;
	}

	private static List<File> getHTMLFiles(String folder) {
		File parent = new File(folder);
		String[] extensions = new String[] { "html" };
		List<File> files = (List<File>) FileUtils.listFiles(parent, extensions, true);
		return files;
	}
}