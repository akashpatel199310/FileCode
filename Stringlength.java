package abcd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.StringTokenizer;

import io.netty.util.internal.StringUtil;

public class Stringlength {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StringTokenizer str = new StringTokenizer("1234 5678900");
		System.out.println(str.countTokens());
		
		 String word = "example"; // Replace "example" with any word you want to use
	        String paddedWord = padString(word, 10);
	        System.out.println(paddedWord+"a");
	        System.out.println(paddedWord.length());
	        
	        String recordType="",recordCount="",clientNumber="",fileCreationNumber="";
	        String fileCreationDate="",processingCenter="",reserved="",destinationCurrencyCode="",filler="";
	        
	        
	        int recordTypeSize=1,recordCountSize=9,clientNumberSize=10,fileCreationNumberSize=4,fileCreationDateSize=6;
	        	int	processingCenterSize=5,reservedSize=20,destinationCurrencyCodeSize=3,fillerSize=1406;
	        	
	        	recordType=String.valueOf(generateRandomPositiveLong(recordTypeSize));
	        	recordCount=String.valueOf(generateRandomPositiveLong(recordCountSize));
	        	clientNumber=String.valueOf(generateRandomPositiveLong(clientNumberSize));
	        	fileCreationNumber=String.valueOf(generateRandomPositiveLong(fileCreationNumberSize));
	        	fileCreationDate=String.valueOf(generateDate("today"));
	        	
	        	processingCenter=String.valueOf(generateRandomPositiveLong(processingCenterSize));
	        	reserved=String.valueOf(generateRandomPositiveLong(reservedSize));
	        	destinationCurrencyCode=String.valueOf(generateRandomString(destinationCurrencyCodeSize));
	        	filler=String.valueOf(generateRandomPositiveLong(fillerSize));
	        	
	        	System.out.println(recordType+recordCount+clientNumber+fileCreationNumber+fileCreationDate+processingCenter+reserved+destinationCurrencyCode+filler);
	        	
	        	System.out.println(System.getProperty("user.dir"));
	        	
	        	 String fileName = System.getProperty("user.dir")+"\\abc.txt";
	             String text = recordType+recordCount+clientNumber+fileCreationNumber+fileCreationDate+processingCenter+reserved+destinationCurrencyCode+filler;
	             
	             try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
	                 writer.write(text);
	                 System.out.println("Successfully wrote to file " + fileName);
	             } catch (IOException e) {
	                 System.err.println("Failed to write to file " + fileName);
	                 e.printStackTrace();
	             }
	        
//	        String word = "example"; // Replace "example" with any word you want to use
//	        String paddedWord = String.format("%1$-10s", word);
//	        System.out.println(paddedWord); // Outputs "example   "
	}
	public static String padString(String str, int length) {
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }
	public static long generateRandomPositiveLong(int numDigits) {
        Random random = new Random();
        long min = (long) Math.pow(10, numDigits - 1); // minimum value based on the number of digits
        long max = (long) Math.pow(10, numDigits) - 1; // maximum value based on the number of digits
        long randomNum = min + (long) (random.nextDouble() * (max - min)); // generate random number in the range
        return randomNum;
    }
	public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
	public static String generateDate(String input) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyy");

        if (input.equalsIgnoreCase("today")) {
            return formatter.format(date);
        } else if (input.startsWith("today+")) {
            String[] tokens = input.split("\\+");
            if (tokens.length != 2) {
                throw new IllegalArgumentException("Invalid input format");
            }
            String operation = tokens[1];
            int amount = Integer.parseInt(operation.split("_")[0]);
            String unit = operation.split("_")[1];
            if (unit.equalsIgnoreCase("days")) {
                date = date.plus(amount, ChronoUnit.DAYS);
            } else if (unit.equalsIgnoreCase("months")) {
                date = date.plus(amount, ChronoUnit.MONTHS);
            } else {
                throw new IllegalArgumentException("Invalid input format");
            }
            return formatter.format(date);
        } else {
            throw new IllegalArgumentException("Invalid input format");
        }
    }

}
