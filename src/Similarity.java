/*	Daniel Fishel
 *  df0450748@swccd.edu
 *  0450748	
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Similarity 
{


	public static void main(String[] args) throws FileNotFoundException
	{
		argsCheck(args.length);

		ArrayList<String> major = new ArrayList<String>(); //list what is in the major file
		major = stringFile(args[0], major);
		
		int dataSizeMajors = major.size();

		File inputMajor = new File(args[0]);	//hash the list of txt in major txt
		Scanner majorScanner = new Scanner(inputMajor);
		ArrayList <ArrayList<Integer>> hashMajor = hashed(majorScanner);

		//System.out.println(hashMajor); (printing list to check hash #'s

		
		ArrayList<String> student = new ArrayList<String>(); //print list of students courses
		student = stringFile(args[1], student);
		

		File inputStudent = new File(args[1]);
		Scanner studentScanner = new Scanner(inputStudent);
		ArrayList<Integer> hashStudent = hashItems(studentScanner);

		//System.out.println(hashStudent); print the hash #'s for student classes

		Results[] r = new Results [dataSizeMajors];

		int majorList = hashMajor.size(); // get comparisons
		for( int i = 0; i < majorList; i++) {
			ArrayList<Integer> similars = new ArrayList<Integer>(hashStudent);
			similars.retainAll(hashMajor.get(i));
			double comparisons = ((double)similars.size()/(hashMajor.get(i).size() + (hashStudent.size()-similars.size() ) ) )* 100;

			r[i] = new Results(comparisons, major.get(i));
		}

	
		Arrays.sort(r, Collections.reverseOrder());
		System.out.println("Top 5 Similarities:");
		System.out.println("-----------------------------------------------");
		for(int i =0; i < 5;i++) {
			System.out.println(r[i]);
		}
		System.out.println();
		System.out.println("Similarity List:");
		System.out.println("-----------------------------------------------");
		for(int i = 0; i < r.length; i++) {
			System.out.println(r[i]);
		}
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();
		System.out.println("Major Courses:");
		System.out.println(major);
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();
		System.out.println("Classes Student has taken:");
		System.out.println(student);

	}	


	public static void argsCheck (int args) {

		if (args < 1 || args >2) {
			System.err.println("Invalid # of arguments: " + args + "\nARguments required: 2" + "\nExitingProgram.");
			System.exit(0);
		}
	}	

	public static ArrayList<String> stringFile(String fileName, ArrayList<String>inputLines) {

		try {
			File text = new File(fileName);
			Scanner scanner = new Scanner(text);
			while (scanner.hasNextLine()) {
				inputLines.add(scanner.nextLine());
			}
			scanner.close();

		}	catch (FileNotFoundException e) {

		}
		return inputLines;
	}

	public static ArrayList<Integer> hashItems(Scanner file){
		ArrayList<Integer> array = new ArrayList<Integer>();

		while (file.hasNext() ) {
			array.add( new Integer(file.nextLine().hashCode() ) );
		}
		return array;
	}

	public static ArrayList<ArrayList<Integer>> hashed (Scanner file) throws FileNotFoundException {

		ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();

		while( file.hasNext() ) {

			Scanner course = new Scanner( new File( file.nextLine()) );
			array.add( new ArrayList<Integer>( hashItems( course )  ) );
		}

		return array;
	}
}