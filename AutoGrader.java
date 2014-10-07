import java.io.*;
import java.util.*;
/**
 * Program that automatically inserts a test file into all project folders and
 * compiles all the files there while keeping track of output.
 *
 * @author Rob Schreiber
 */
public class AutoGrader {
	private static enum classes {AP_COMPUTER_SCIENCE, ADVANCED_COMPUTER_SCIENCE};
	private static classes classType;
	private static String projectRoot = "C:\\Users\\schrer56\\google drive\\CS Projects\\2014-2015\\";
	private static int projectNum;
	private static File output;
	private static ArrayList<Student> students = new ArrayList<Student>();
	private static Student tempStudent;
	private static Scanner kbReader = new Scanner(System.in);
	private static File testFile;
	
	/**
	 * Inserts test file and compiles project directories and runs test file.
	 * @param args The class, The project #, The test file path, and an undo flag
	 */
	public static void main(String[] args) {
		System.out.println();
		if (args.length < 2 || args.length > 4) {
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
		catch (Exception e) {
			printUsage();
			System.exit(1);
		}
		if (args.length >= 3)
			testFile = new File(args[2]);
		switch (classType) {
			case AP_COMPUTER_SCIENCE:
				projectRoot += "AP Computer Science\\";
				break;
			case ADVANCED_COMPUTER_SCIENCE:
				projectRoot += "Advanced Computer Science\\";
		}
		projectRoot += "Project " + projectNum + "\\";
		if (args.length == 4 && "-u".equals(args[3].toLowerCase())) uncompile(projectRoot);
		walkAndCompile();
		Collections.sort(students);
		for (Student s : students) {
			System.out.println(s);
			s.grade(kbReader.nextLine());
		}
		try {
			FileWriter fw = new FileWriter(new File(projectRoot + "\\grades.csv"));
			PrintWriter pw = new PrintWriter(fw);
			for (Student s : students) {
				pw.println(s.getName() + ", " + s.getGrade());
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
	public static void walkAndCompile() {
		File root = new File(projectRoot);
		File[] list = root.listFiles();

            if (list == null) return;

            for ( File f : list ) {
                if ( f.isDirectory() ) {
                	int per = Integer.parseInt(f.getName().substring(0,1));
                	String name = f.getName().substring(1, f.getName().length() - 8);
                	tempStudent = new Student(per, name);
                	students.add(tempStudent);
                    walkAndCompile( f.getAbsolutePath() );
                    //System.out.println( "Dir:" + f.getName() );
                }
                /*else {
                    System.out.println( "File:" + f.getAbsoluteFile() );
                }*/
            }
	}
	/**
	 * Helper method that actually recursively compiles things and logs
	 * the results in the current student object.
	 * 
	 * @param path The recursive path that this iteration is at.
	 */
	public static void walkAndCompile(String path) {
		File root = new File(path);
		File[] list = root.listFiles();

            if (list == null) return;

            for ( File f : list ) {
                if ( f.isDirectory() ) {
                    walkAndCompile( f.getAbsolutePath() );
                }
                else if (!f.isDirectory() && f.getName().substring(f.getName().length() - 5).equals(".java")) {
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
	                    			p.waitFor();
	                    		}
	                    		catch(InterruptedException e) {
	                    			e.printStackTrace();
	                    		}
	                    		tempStudent.addDescription(stringify(log));
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

		System.out.println("\targ2: filename of the tester file.\n");

		System.out.println("\targ3: -u or -U for undoing the last run before running\n");

		System.out.println("\ti.e. java AutoGrader -a -1\n");
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
			else if (!f.isDirectory() && f.getName().equals(testFile.getName())) {
				f.delete();
			}
		}
	}
	/**
	 * A class representing a student that is comparable.
	 * Objects have period, name, description, and grade.
	 */
	private static class Student implements Comparable<Student> {
		private int period;
		private String name;
		private String description = "\n";
		private String grade = "";
		public Student (int p, String n) {
			period = p;
			if (n.equals("LonghurstHay")) name = "PickardHay";
			else if (n.equals("LonghurstHayLate")) name = "PickardHayLate";
			else name = n;
		}
		public String toString() {
			return name + ":\n\n" + description;
		}
		public void addDescription (String newDescription) {
			if (description.equals("\n")) description += newDescription;
			else description += "\n" + newDescription;
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
		public int compareTo(Student s) {
			if (period != s.period) return period - s.period;
			return name.compareTo(s.name);
		}
	}

}