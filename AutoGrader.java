import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.mail.*;
import javax.mail.internet.*;
/**
 * Program that automatically inserts a test file into all project folders and
 * compiles all the files there while keeping track of output.
 *
 * @author Rob Schreiber
 */
public class AutoGrader {
	private static enum classes {AP_COMPUTER_SCIENCE, ADVANCED_COMPUTER_SCIENCE};
	private static classes classType;
	private static String projectRoot;
	private static int projectNum;
	private static File output;
	private static ArrayList<Student> students = new ArrayList<Student>();
	private static Student tempStudent;
	private static Scanner kbReader = new Scanner(System.in);
	private static File testFile;
	private static String USER_NAME;
    private static String PASSWORD;
    private static boolean REGRADE;
    private static Hashtable<String, String> EMAILS = new Hashtable<String, String>();
	
	/**
	 * Inserts test file and compiles project directories and runs test file.
	 * @param args The class, The project #, The test file path
	 */
	public static void main(String[] args) {
		System.out.println();
		if (args.length != 2) {
			printUsage();
			System.exit(1);
		}
		else if ("-a".equals(args[0].toLowerCase())) classType = classes.AP_COMPUTER_SCIENCE;
		else if ("-g".equals(args[0].toLowerCase())) classType = classes.ADVANCED_COMPUTER_SCIENCE;
		else {
			printUsage();
			System.exit(1);
		}
		try {
			projectNum = Integer.parseInt(args[1].substring(1));
		}
		catch (NumberFormatException e) {
			if ("-r".equals(args[1].toLowerCase())) {
				REGRADE = true;
				projectNum = -1;
			}
			else {
				printUsage();
				System.exit(1);
			}

		}
		catch (Exception e) {
			printUsage();
			System.exit(1);
		}
		// Eventually refactor this following part to get the project root from the user and
		// store in a config file.
		projectRoot = System.getProperty("user.home") + "\\google drive\\CS Projects\\2015-2016\\";

		String testFilePath = "Tests\\";
		switch (classType) {
			case AP_COMPUTER_SCIENCE:
				testFilePath += "APCS\\";
				break;
			case ADVANCED_COMPUTER_SCIENCE:
				testFilePath += "ACS\\";

		}
		if (!REGRADE) {
			testFilePath += "Project" + projectNum + "Test.java";
			testFile = new File(testFilePath);
		}
		switch (classType) {
			case AP_COMPUTER_SCIENCE:
				projectRoot += "AP Computer Science\\";
				break;
			case ADVANCED_COMPUTER_SCIENCE:
				projectRoot += "Advanced Computer Science\\";
		}
		projectRoot += (!REGRADE)? ("Project " + projectNum + "\\") : ("Regrades\\");
		uncompile(projectRoot);
		try {
			Scanner sc = new Scanner(new File("credentials"));
			USER_NAME = sc.nextLine();
			PASSWORD = sc.nextLine();
			sc.close();
			sc = new Scanner(new File("emails.csv"));
			sc.useDelimiter(",");
			while (sc.hasNext()) {
				String[] temp = sc.nextLine().split(",");
				EMAILS.put(temp[0].toLowerCase(), temp[1].toLowerCase());
			}
			sc.close();
		}
		catch (IOException e) {
			System.out.println("Credentials or email list missing.");
			e.printStackTrace();
		}
		walkAndMakeStudents();
		Collections.sort(students);
		for (Student s : students) {
			System.out.println(s);
			if (REGRADE) {
				System.out.println(s.getOldGrade());
			}
			s.grade(((REGRADE)?s.getOldGrade() + "\nYour new grade: ":"") + kbReader.nextLine());
			for (int i = 0; i < 80; i++) System.out.println("-");
		}
		try {
			FileWriter fw = new FileWriter(new File(projectRoot + "\\grades.csv"));
			PrintWriter pw = new PrintWriter(fw);
			System.out.print("\nSending Emails.");
			for (Student s : students) {
				pw.println(s.getName() + "," + ((REGRADE)?("Project" + s.getProjectNumber() + ","): "") + s.getGrade());
				
				if (s.getEmail() != null) {
					sendFromGMail(USER_NAME, PASSWORD, new String[]{s.getEmail()}, "Your " + ((REGRADE)?"re":"") + "grade for Project " + s.getProjectNumber(), s.getTestOutput() + "\n\n" + s.getGrade());
					System.out.print(".");
				}
				else System.out.println(s.getName() + "'s email was not sent. Their email address is not in the emails.csv file.");
				if (REGRADE) {
					String oldProjectRoot = System.getProperty("user.home") + "\\google drive\\CS Projects\\2015-2016\\";
					switch (classType) {
						case AP_COMPUTER_SCIENCE:
							oldProjectRoot += "AP Computer Science\\";
							break;
						case ADVANCED_COMPUTER_SCIENCE:
							oldProjectRoot += "Advanced Computer Science\\";
					}
					oldProjectRoot += "Project " + s.getProjectNumber() + "\\";
					File oldGradeFile = null;
					try {
						oldGradeFile = new File(oldProjectRoot + "grades.csv");
					}
					catch (Exception e) {
						System.out.println("Can't find " + oldProjectRoot + "grades.csv");
						System.exit(1);
					}
					FileWriter oldfw = new FileWriter(oldGradeFile, true);
					PrintWriter oldpw = new PrintWriter(oldfw);
					oldpw.println(s.getName() + "REGRADE," + s.getGrade().substring(s.getGrade().indexOf("Your new grade: ") + 16));
					oldpw.close();
					oldfw.close();
				}
			}
			pw.close();
			fw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Recursively runs through the project root and creates student objects
	 * for each folder.
	 */
	public static void walkAndMakeStudents() {
		File root = new File(projectRoot);
		File[] list = root.listFiles();

            if (list == null) return;

            for ( File f : list ) {
                if ( f.isDirectory() ) {
                	int per = Integer.parseInt(f.getName().substring(0,1));
                	if (f.getName().indexOf("Project") == -1) {
                		System.out.println("invalid directory name: " + f.getName());
                		System.exit(1);
                	}
                	String name = f.getName().substring(1, f.getName().indexOf("Project"));
                	if (REGRADE) {

						String testFilePath = "Tests\\";
						switch (classType) {
							case AP_COMPUTER_SCIENCE:
								testFilePath += "APCS\\";
								break;
							case ADVANCED_COMPUTER_SCIENCE:
								testFilePath += "ACS\\";
						}

                		try {
                			projectNum = Integer.parseInt(f.getName().substring(f.getName().indexOf("Project") + 7));
                		}
                		catch (Exception e) {
                			e.printStackTrace();
                			System.out.println("Can't get project number");
                			System.exit(1);
                		}
                		testFilePath += "Project" + projectNum + "Test.java";
						testFile = new File(testFilePath);
                	}
                	tempStudent = new Student(per, name, projectNum);
                	students.add(tempStudent);
                	System.out.println("Testing " + tempStudent.getName() +"...");

                    walkAndCompile( f.getAbsolutePath() );
                    
                }
                
            }
	}
	/**
	 * Helper method that recursively compiles things and logs
	 * the results in the current student object.
	 * 
	 * @param path The recursive path that this iteration is at.
	 */
	public static void walkAndCompile(String path) {
		File root = new File(path);
		File[] list = root.listFiles();

            if (list == null) return;

            for ( File f : list ) {
                if ( f.isDirectory() && !f.getName().equals("__MACOSX")) {
                    walkAndCompile( f.getAbsolutePath() );
                }
                else if (!f.isDirectory() && f.getName().length() > 5 && f.getName().substring(f.getName().length() - 5).equals(".java")) {
                    try {
                    	if (testFile != null){
	                    	File testFileCopy = new File(f.getParent() + "\\" + testFile.getName());
	                    	if (testFileCopy.length() == 0) {
	                    		FileWriter fw = new FileWriter(testFileCopy);
	                    		PrintWriter pw = new PrintWriter(fw);
	                    		pw.print(stringify(testFile));
	                    		pw.close();
	                    		fw.close();
	                    		tempStudent.addDescription(compileWithLog(testFileCopy));
	                    		ProcessBuilder pb = new ProcessBuilder("java", testFileCopy.getName().substring(0, testFileCopy.getName().length() - 5));
	                    		pb.directory(f.getParentFile());
	                    		File log = new File(f.getParent() + "\\log.txt");
	                    		pb.redirectOutput(log);
	                    		Process p = pb.start();
	                    		try {
	                    			if (!p.waitFor(30, TimeUnit.SECONDS)) {
	                    				p.destroyForcibly();
	                    			}
	                    		}
	                    		catch(InterruptedException e) {
	                    			e.printStackTrace();
	                    		}
	                    		tempStudent.addTestOutput(stringify(log));
	                    	}
	                    }
                    }
                    catch (IOException e) {
                    	System.out.println(f.getPath());
                    	e.printStackTrace();
                    }
                    tempStudent.addDescription(compileWithLog(f));
                    tempStudent.addDescription(stringify(f));
                }
            }
	}
	/**
	 * Prints how to use the program
	 */
	private static void printUsage() {
		System.out.println("\n\tIn order to use this program, use the following arguments:\n");

		System.out.println("\targ0: -a or -A for AP Computer Science, or");
		System.out.println("\t      -g or -G for Advanced Computer Science.\n");

		System.out.println("\targ1: -# where # is the number of the project.\n");
		System.out.println("\t      or -r or -R for regrades\n");

		System.out.println("\te.g. java AutoGrader -a -1\n");
	}
	/**
	 * Runs javac on the file passed and outputs to a file log.txt.
	 * The content of log.txt is stringified and returned.
	 * 
	 * @param  f The file to be compiled
	 * @return   The String results of the compilation
	 */
	private static String compileWithLog(File f) {
		String result = "";
		try {
			ProcessBuilder pb = new ProcessBuilder ("javac", "-Xstdout", "log.txt", f.getName());
			pb.directory(f.getParentFile());
			Process p = pb.start();
			try {
				p.waitFor();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			result += stringify(new File(f.getParent() + "\\log.txt"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * Takes a text file and turns it into one long String.
	 * If the file is log.txt, it waits a bit before running
	 * to make sure that it's completely written.
	 * 
	 * @param  f The file to turn into a String
	 * @return   A String representing the parameter file.
	 */
	private static String stringify(File f) {
		String result = "";
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				result += sc.nextLine() + "\n";
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * Recursively deletes all files matching the test file.
	 * @param path Recursive path the directory walk is at
	 */
	private static void uncompile(String path) {
		File root = new File(path);
		File[] list = root.listFiles();
		if (list == null) return;
		for (File f : list) {
			if (f.isDirectory())
				uncompile(f.getAbsolutePath());
			else if (!f.isDirectory() && f.getName().indexOf("Test") >= 0 && f.getName().indexOf("Project") >= 0) {
				f.delete();
			}
		}
	}
	/**
	 * A class representing a student that is comparable.
	 * Objects have period, name, description, email and grade.
	 */
	private static class Student implements Comparable<Student> {
		private int period;
		private String name;
		private ArrayList<String> description = new ArrayList<String>();
		private String grade = "";
		private String testOutput; 
		private String email;
		private int projectNumber;
		public Student (int p, String n, int pn) {
			period = p;
			name = n;
			projectNumber = pn;
			email = EMAILS.get(name.toLowerCase());
		}
		public String toString() {
			String result = "";
			//try {	
				for (String s:description) {
					result += s;
				}
			//} catch (EmptyStackException e) {}
			return name + ":\n\n" + result + "\n" + name + ":\n\n" + testOutput;
		}
		public void addDescription (String newDescription) {
			description.add(newDescription);
		}
		public void addTestOutput (String testOutput) {
			this.testOutput = testOutput;
		}
		public void grade(String g) {
			grade = g;
		}
		public String getGrade() {
			return grade;
		}
		public String getName() {
			return name;
		}
		public String getTestOutput() {
			return testOutput;
		}
		public String getEmail() {
			return email;
		}
		public int getProjectNumber() {
			return projectNumber;
		}
		/**
		 * Scrapes the appropriate grades.csv file for the previous grades entered
		 * for this student on this project
		 * @return The old grades for this student for this project.
		 */
		public String getOldGrade() {
			String oldProjectRoot = System.getProperty("user.home") + "\\google drive\\CS Projects\\2015-2016\\";
			switch (classType) {
				case AP_COMPUTER_SCIENCE:
					oldProjectRoot += "AP Computer Science\\";
					break;
				case ADVANCED_COMPUTER_SCIENCE:
					oldProjectRoot += "Advanced Computer Science\\";
			}
			oldProjectRoot += "Project " + projectNumber + "\\";
			File oldGradeFile = null;
			Scanner sc = null;
			try {
				oldGradeFile = new File(oldProjectRoot + "grades.csv");
				sc = new Scanner(oldGradeFile);
			}
			catch (Exception e) {
				System.out.println("Can't find " + oldProjectRoot + "grades.csv");
				System.exit(1);
			}
			
			String oldGrade = "";
			int i = 1;
			while (sc.hasNext()) {
				String next = sc.nextLine();
				if (next.indexOf(getName()) != -1) {
					oldGrade += (oldGrade.equals("")?"First Grade:":"\nRegrade " + i++ + ":") + next.substring(next.indexOf(","));
				}
			}
			if (oldGrade.equals("")) {
				oldGrade = "No previous scores";
			}
			return oldGrade;

		}
		public int compareTo(Student s) {
			if (period != s.period) return period - s.period;
			return name.compareTo(s.name);
		}
	}

	private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

}
