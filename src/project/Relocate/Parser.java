package project.Relocate;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
	
	//Instance variables to hold data regarding where to find data
	private final String outlookName = "data/outlooks.csv";     
	private final String incomeName = "data/income.csv";
	
	//Outlook has several lines with commas that should not split in them, this helper function will handle extracting this
	/**
	 * Handles line splitting for a line of outlook data
	 * @param input A String object holding all the information read from a single line
	 * @return A String array with the data split properly
	 */
	private String[] splitLineForOutlook(String input){
		//Find the area between quotation marks to filter out
		int quote1 = input.indexOf("\"<p>");
		int quote2 = input.substring(quote1 + 1).toLowerCase().indexOf(">\"") + quote1 + 2;
		//Some entries are not wrapped in quotation marks so this next segment will be dedicated to detecting those
		if(quote1 == -1){
			quote1 = input.indexOf(",<p>") + 1;
			quote2 = input.substring(quote1 + 1).indexOf("</p>,") + quote1 + 1;
		}
		//Back up this region of text
		final String pulledData = cleanString(input.substring(quote1, quote2));
		//Remove the quoted data from the input string
		//Again to handle inputs with broken formatting, need to check if the leading entry is a comma
		input = input.substring(0, quote1) + input.substring(quote2);
		String[] retArray = input.split(","); //Split the array
		//If the array is greater than size 11 than the job was split by commas, so we need to put those back to preserve it
		if (retArray.length > 11){
			for(int i = 2; i < 2 + retArray.length - 11; i++){
					retArray[1] = retArray[1] + "," + retArray[i];
				}
			//Now need to shift all array elements down
			for(int i = 2; i < 11; i++){
				retArray[i] = retArray[i + retArray.length - 11];
			}
		}
		retArray[4] = pulledData; //Put the removed string into the array
		//
		//Return the array to the calling function
		return retArray;
	}
	//This will strip the string clear of all HTML elements
	/**
	 * Removes all HTML elements from the string
	 * @param input The input String object
	 * @return A String object free of HTML elements
	 */
	private String cleanString(String input){
		while(input.indexOf("<") != -1){
			input = input.substring(0, input.indexOf("<")) + " " + input.substring(input.indexOf(">") + 1);
		}
		return input;
	}
	
	//This will read back the information from outlooks.csv
	//Job of the GUI maker to catch an IOException and display an appropriate error politely telling the user to leave the files alone
	
	/**
	 * Parses the outlooks.csv file and turns every row into an object
	 * @return An array of OutlookData objects in 1-1 correspondence with the rows in the outlooks.csv file
	 * @throws IOException If the file is not found or otherwise cannot be read, this exception will be thrown
	 */
	
	public OutlookData[] getOutlookDataArray() throws IOException{
		Scanner fileReader = new Scanner(new File(outlookName));
		//The first line is always column headings so it will be skipped
		fileReader.nextLine();
		//Count the size of the dataset to create a new array
		int count = 0;
		while(fileReader.hasNextLine()){
			count++;
			fileReader.nextLine();
		}
		fileReader.close();
		//Create a new array of the appropriate size and go back through populating the array
		final OutlookData[] outlookArr = new OutlookData[count];
		count = 0;
		fileReader = new Scanner(new File(outlookName));
		fileReader.nextLine();
		while(fileReader.hasNextLine()){
			String[] splitArr = splitLineForOutlook(fileReader.nextLine());
			outlookArr[count++] = new OutlookData(Integer.parseInt(splitArr[0].substring(4)),splitArr[1],Integer.parseInt(splitArr[2]),splitArr[3],removeUnnecessaryCharacters(splitArr[4]),splitArr[5],splitArr[6],Integer.parseInt(splitArr[7]),splitArr[8],Integer.parseInt(splitArr[9]), splitArr[10], 0);
		}
		//Close the stream
		fileReader.close();
		return outlookArr;
	}
	//Gets information from income.csv
	//GUI maker again needs to catch this exception and display an appropriate error if the file is missing/locked/etc.
	
	/**
	 * Parses the income.csv file and turns every row into an object
	 * @return An array of CityData objects in 1-1 correspondence with the rows in the income.csv file
	 * @throws IOException If the file is not found or otherwise cannot be read, this exception will be thrown
	 */
	
	public CityData[] getCityDataArray() throws IOException{
		Scanner fileReader = new Scanner(new File(incomeName));
		//The first line is always column headings so it will be skipped
		fileReader.nextLine();
		//Count the size of the dataset to create a new array
		int count = 0;
		while(fileReader.hasNextLine()){
			count++;
			fileReader.nextLine();
		}
		fileReader.close();
		//Create a new array of the appropriate size and go back through populating the array
		final CityData[] cityArr = new CityData[count];
		count = 0;
		fileReader = new Scanner(new File(incomeName));
		fileReader.nextLine();
		//The split will be handled in the function since it's much easier than the outlook split
		while(fileReader.hasNextLine()){
			String[] splitArr = fileReader.nextLine().split(",");
			//There are some blank lines in the dataset for Geographical ID, these will be replaced with 0
			if (splitArr[2].equals("")){
				splitArr[2] = "0";
			}
			//If the value is "x" or ".." it becomes the error value of -1
			if (splitArr[6].equals("x") || splitArr[6].equals("..")){
				splitArr[6] = "-1";
			}
			//Use the same algorithm as in outlooks to recombine strings that were split incorrectly
			cityArr[count++] = new CityData(Integer.parseInt(splitArr[0]),splitArr[1],splitArr[2], removeUnnecessaryCharacters(splitArr[3]), splitArr[4], Double.parseDouble(splitArr[5]), Double.parseDouble(splitArr[6]));
		}
		//Close the stream
		fileReader.close();
		return cityArr;
	}	
	
	/**
	 * Strips leading and tailing quotes and spaces from the input
	 * @param in The string to format
	 * @return The same string with the unnecessary quotes and spaces removed
	 */
	private String removeUnnecessaryCharacters(String in){
		//Format leading characters
		while(in.startsWith("\"") || in.startsWith(" ")){
			in = in.substring(1);
		}
		//Format trailing characters
		while(in.endsWith("\"") || in.endsWith(" ")){
			in = in.substring(0, in.length() - 1);
		}
		return in;
	}
	
}