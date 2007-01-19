package com.ronhorner.primes;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ron Horner <br>
 * Number Theory and Cryptography CSCI-8566-001 <br>
 * Project 1 <br>
 * 
 * This program encapsulates both assignments in the project.<br>
 * <br>
 * <p>
 * <b>Problem 1</b>
 * </p>
 * To answer the first question use the following<br>
 * <br>
 * <dl><dt><b>Brute Force Method</b></dt>
 * <dt>java -jar PrimeTest.jar -b -n <i>number</i></dt>
 * <dd>Where <i>number</i> is the number to check for primality</dd>
 * </dl><br>
 * 
 * <dl><dt><b>Miller-Rabin Method</b></dt>
 * <dt>java -jar PrimeTest.jar -mr -n <i>number</i> -w <i>witnesses</i></dt>
 * <dd>Where <i>number</i> is the number to check for primality and
 * <i>witnesses</i> is the number of witnesses for the Miller-Rabin Test</dd>
 * </dl><br>
 * 
 * <dl><dt><b>Deterministic Miller-Rabin Method</b></dt>
 * <dt>java -jar PrimeTest.jar -mrd -n <i>number</i></dt>
 * <dd>Where <i>number</i> is the number to check for primality</dd>
 * </dl><br>
 * 
 * <dl><dt><b>Fermat's Method</b></dt>
 * <dt>java -jar PrimeTest.jar -fp -n <i>number</i> -w <i>witnesses</i></dt>
 * <dd>Where <i>number</i> is the number to check for primality and
 * <i>witnesses</i> is the number of witnesses for the Fermat Test</dd>
 * </dl><br>
 * 
 * <dl><dt><b>Solovay-Strassen Method</b></dt>
 * <dt>java -jar PrimeTest.jar -ss -n <i>number</i> -w <i>witnesses</i></dt>
 * <dd>Where <i>number</i> is the number to check for primality and
 * <i>witnesses</i> is the number of witnesses for the Solovay-Strassen Test</dd>
 * </dl><br>
 * 
 * <p>
 * <b>Problem 2</b>
 * </p>
 * To test the second problem use the following<br>
 * <br>
 * <dl><dt><b>Closest prime less than or equal to <i>number</i> using Solovay-Strassen</b></dt>
 * <dt>java -jar PrimeTest.jar -ss -f -n <i>number</i> -w <i>witnesses</i></dt>
 * <dd>Where <i>number</i> is the upper bound to check numbers less
 * than for primality and <i>witnesses</i> is the number of witnesses for the Solovay-Strassen test. </dd>
 * <dt><br><i>it is possible to replace the -ss flag with any other method as outlined above
 * or below in the help documentation.</i></dt>
 * </dl><br>
 *
 * <p><b>Help documentation</b></p>
 * PrimeTest is a small application to test for the primality of a number or the
 * closest prime to an upper bound. <br>
 * <br>
 * Usage PrimeTest &lt;method&gt; [-f] &lt;number type&gt; &lt;number&gt; [-w
 * &lt;number&gt;]<br>
 * <br>
 * <dl>
 * <dt>Method Options </dt>
 * <dd>-b</dd>
 * <dd>
 * <dl>
 * <dd>Brute Force check if a number is prime</dd>
 * </dl>
 * </dd>
 * <dd> -mr </dd>
 * <dd>
 * <dl>
 * <dd>Miller-Rabin primality test. Must be used in conjunction with -w. See
 * below for information.</dd>
 * </dl>
 * </dd>
 * <dd> -mrd </dd>
 * <dd>
 * <dl>
 * <dd>Miller-Rabin Deteriministic primality test.</dd>
 * </dl>
 * </dd>
 * <dd> -fp </dd>
 * <dd>
 * <dl>
 * <dd>Fermat primality test. Checks against Carmichael numbers. Must be used
 * in conjunction with -w. See below for information.</dd>
 * </dl>
 * </dd>
 * <dd> -ss </dd>
 * <dd>
 * <dl>
 * <dd>Solovay-Strassen primality test. Must be used in conjunction with -w.
 * See below for information.</dd>
 * </dl>
 * </dd>
 * <dd> -h </dd>
 * <dd>
 * <dl>
 * <dd>output this help message</dd>
 * </dl>
 * </dd>
 * 
 * 
 * <dt>Number Type Options</dt>
 * <dd>-n <i>number</i></dd>
 * <dd>
 * <dl>
 * <dd>Use the <i>number</i> specified.</dd>
 * </dl>
 * </dd>
 * 
 * <dd>-g <i>number</i></dd>
 * <dd>
 * <dl>
 * <dd>Generate a number of <i>number</i> digits</dd>
 * </dl>
 * </dd>
 * 
 * <dt>Witnesses Option</dt>
 * <dd>-w <i>number</i></dd>
 * <dd>
 * <dl>
 * <dd>Use <i>number</i> witnesses in the Miller-Rabin, Fermat, or Solovay-Strassen
 * tests.</dd>
 * </dl>
 * </dd>
 * 
 * <dt>Other Options</dt>
 * <dd>-v</dd>
 * <dd>
 * <dl>
 * <dd>Show verbose output.</dd>
 * </dl>
 * </dd>
 * 
 * <dd>-f</dd>
 * <dd>
 * <dl>
 * <dd>Find the closest prime less than or equal to the specified or generated
 * number.</dd>
 * </dl>
 * </dd>
 * 
 * <dt>Examples:</dt>
 * 
 * <dd>java -jar PrimeTest.jar -b -n 19827362</dd>
 * <dd>
 * <dl>
 * <dd>will brute force check the primality of 19827362</dd>
 * </dl>
 * </dd>
 * 
 * <dd>java -jar PrimeTest.jar -mr -g 20 -w 30</dd>
 * <dd>
 * <dl>
 * <dd>will use Miller-Rabin to check a random number of 20 digits using 30
 * witnesses.</dd>
 * </dl>
 * </dd>
 * 
 * <dd>java -jar PrimeTest.jar -mr -f -g 20 -w 30</dd>
 * <dd>
 * <dl>
 * <dd>will use Miller-Rabin to check a random number of 20 digits using 30
 * witnesses. If a the generated number is not prime then it will proceed to
 * find the largest prime smaller than the generated. number.</dd>
 * </dl>
 * </dd>
 * </dl>
 * 
 * 
 */

public class PrimeTest {

    /** Convienence variable for the BigInteger Zero */
    private static final BigInteger ZERO_INT = new BigInteger("0");
    
    /** Convienence variable for the BigInteger One */
    private static final BigInteger ONE_INT = new BigInteger("1");
    
    /** Convienence variable for the BigInteger Two */
    private static final BigInteger TWO_INT = new BigInteger("2");

    /** Flag to find the closest prime less than the upper bound*/
    private boolean findClosest = false;
    
    /** Flag to perform the brute force check*/
    private boolean bruteForce = false;
    
    /** Flag to perform the Miller-Rabin test*/
    private boolean millerRabin = false;
    
    /** Flag to perform the Deterministic Miller-Rabin test */
    private boolean millerRabinD = false;
    
    /** Flag to perform Fermat's Primality Test */
    private boolean fermatPrimality = false;
    
    /** Flag to perform the Solovay-Strassen test */
    private boolean solovayStrassen = false;

    /** Flag to print the help message */
    private boolean printHelp = false;
    
    /** Flag to print verbose output messages */
    private boolean verboseOutput = false;
    
    /** The prime number that will be checked for primality*/
    private BigInteger input = ZERO_INT;
    
    /** The number of witnesses for testing primality in Miller-Rabin, Fermat and Solovay-Strassen*/
    private int numWitnesses = -1;
    
    /** List of all the method flags: -b, -mr, -mrd, -fp, -ss, -h*/
    private static ArrayList METHODS = new ArrayList();
    
    /** List of all the number flags: -n, -g, -w*/
    private static ArrayList NUMBERS = new ArrayList();
    
    /** List of all the option flags: -f, -v, -gui*/
    private static ArrayList OPTIONS = new ArrayList();
    static {
        METHODS.add("-b");
        METHODS.add("-mr");
        METHODS.add("-mrd");
        METHODS.add("-fp");
        METHODS.add("-ss");
        METHODS.add("-h");
        NUMBERS.add("-n");
        NUMBERS.add("-g");
        NUMBERS.add("-w");
        OPTIONS.add("-f");
        OPTIONS.add("-v");
        OPTIONS.add("-gui");
    }

    /**
     * Sorts through all of the input flags and then runs the tests
     * 
     * @param args Program arguments passed in at the command line
     */
    public PrimeTest(String[] args){
      
        for (int i = 0; i < args.length; i++){
            String flag = args[i].trim();
            if (METHODS.contains(flag)){
                setMethod(flag);
            }
            if (NUMBERS.contains(flag)){
                if (i+1 < args.length){
                    setNumber(flag, args[i+1]);
                    i++;
                }
                else {
                    System.out.println("Missing trailing number for flag " + flag + ". Use -h to show help");
                    return;
                }
            }
            if (OPTIONS.contains(flag)){
                setOptions(flag);
            }
        }
        
        if ( args.length == 0){
            System.out.println("Use -h to show help information. Proceeding with default operations");
            verboseOutput = true;
        }
        if (printHelp){
            printHelp();
            return;
        }
        if (!millerRabin && !fermatPrimality && !millerRabinD && !solovayStrassen && !bruteForce){
            if (verboseOutput){
                System.out.println("Setting method to default method Miller-Rabin");
            }
            millerRabin = true;
        }
        if (millerRabin || fermatPrimality || solovayStrassen ){
            if (numWitnesses == -1){
                if (verboseOutput)
                    System.out.println("Setting number of witnesses to default value 100");
                numWitnesses = 100;
            }
        }
        if (input.compareTo(ZERO_INT) == 0){
            if (verboseOutput)
                System.out.println("No number specified.  Generating a random number less than 1000\n");
            SecureRandom srnd = new SecureRandom();
            int random = (int)(srnd.nextDouble() * 1000);
            setNumber("-g", random+"" );
        }
        if ( args.length == 0){
            verboseOutput = false;
        }
        run();
    }
    
    /**
     * Sets the flag for the method to use.
     * 
     * @param flag the string representation of command line argument for the method
     */
    private void setMethod(String flag){
        if (flag.equals("-b")){
            bruteForce = true;                
        }
        else if (flag.equals("-mr")){
            millerRabin = true;
        }
        else if (flag.equals("-mrd")){
            millerRabinD = true;
        }
        else if (flag.equals("-fp")){
            fermatPrimality = true;
        }
        else if (flag.equals("-ss")){
            solovayStrassen = true;
        }
        else if (flag.equals("-h")){
            printHelp = true; 
        }
    }
    
    /**
     * Sets extra options for the program.
     * 
     * @param flag the string representation of command line argument for the option
     */
    private void setOptions(String flag){
        if (flag.equals("-v")){
            verboseOutput = true;
        }
        if (flag.equals("-f")){
            findClosest = true;
        }
    }
    
    /**
     * Sets the number to use for the tests.
     * 
     * @param flag the string representation of command line argument for the method
     * @param num the number to use for the given flag
     */
    private void setNumber(String flag, String num){
        try {
            if (flag.equals("-n")){
                input = new BigInteger(num.trim());
            }
            else if (flag.equals("-w")){
                numWitnesses = Integer.parseInt(num.trim());
            }
            else if (flag.equals("-g")){
                int digits = Integer.parseInt(num.trim());
                if (digits < 1) {
                    printNumberHelp(num);
                }
                input = generateBigInt(digits);
            }
        }catch(Exception e) {
            System.out.println("Either the number following the " + flag + " flag was not specified or " + num + " is not a valid number");
        }
    }
    
    /**
     * Executes the tests based on the input given at the command line.
     */
    private void run(){
        long startTime = System.currentTimeMillis();
        
        if (findClosest){
            if (verboseOutput){
                System.out.println("Upper Bound       : " + input + "\n");
            }
            BigInteger q = input;
            if (input.mod(TWO_INT).compareTo(ZERO_INT) == 0){
                q = input.subtract(ONE_INT);
            }
                
            boolean foundPrime = false;
            while (!foundPrime && q.compareTo(ZERO_INT) == 1)
            {
                if (verboseOutput){
                    System.out.println("Currently Checking: " + q );
                }
                if (bruteForce){
                    foundPrime = PrimeUtils.checkPrimality(q, verboseOutput);
                }
                if (millerRabin){
                    foundPrime = PrimeUtils.checkMillerRabin(q, numWitnesses);
                }
                if (millerRabinD){
                    foundPrime = PrimeUtils.checkMRD(q);
                }
                if (fermatPrimality){
                    foundPrime = PrimeUtils.checkFermatPrimality(q, numWitnesses);
                }
                if (solovayStrassen){
                    foundPrime = PrimeUtils.checkSolovayStrassen(q, numWitnesses);
                }
                if (!foundPrime) {
                    q = q.subtract(TWO_INT);
                }
            }
            System.out.println("\nUpper Bound:\n  " + input + "\n\nClosest Prime:\n  " + q + "\n");
        }
        else {
            boolean isPrime = false;
            if (bruteForce)
                isPrime = PrimeUtils.checkPrimality(input, verboseOutput);
            else if (millerRabin)
                isPrime = PrimeUtils.checkMillerRabin(input, numWitnesses);
            else if (millerRabinD)
                isPrime = PrimeUtils.checkMRD(input);
            else if (fermatPrimality)
                isPrime = PrimeUtils.checkFermatPrimality(input, numWitnesses);
            else if (solovayStrassen)
                isPrime = PrimeUtils.checkSolovayStrassen(input, numWitnesses);
            
            if(isPrime){
                System.out.println(input + "\nis prime.");
            }
            else {
                System.out.println(input + "\nis not prime.");
            }
        }
        printTime(startTime);
    }
    
    /**
     * Passes along the arguments to the PrimeTest constructor. 
     * 
     * @param args arguments to the program
     */
    public static void main(String[] args) {
        new PrimeTest(args);
    }

    /**
     * <p>Prints the elapsed time of the program.</p>
     * 
     * <p>Outputs the form "Elapsed time: HHh MMm SSs"</p>
     * 
     * @param startTime the milliseconds value of the start time of the program
     */
    private static void printTime(long startTime) {
        long x = (System.currentTimeMillis() - startTime)/1000;
        long h = x / 3600;
        x = x - (h * 3600);
        long m = x / 60;
        x = x - (m * 60);
        long s = x;
        System.out.println("Elapsed time: " + h + "h " + m + "m " + s + "s");
    }
    
    /**
     * <p>Generates a random integer of a specified length.  The seed to
     * the random function is the systems current time.  While this
     * does not guarantee perfect randomness, it is good enough for the
     * purposes of this excersise.</p>
     * 
     * @param digits the number of digits in the generated number
     * @return the generated number
     */
    private BigInteger generateBigInt(int digits){
        StringBuffer b = new StringBuffer();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < digits; i++){
            b.append(r.nextInt(10));
        }
        return new BigInteger(b.toString());
    }
    
    /**
     * Prints the help for numbers
     * 
     */
    private void printNumberHelp(String num){
        StringBuffer buff = new StringBuffer();
        buff.append(num + " is not a valid number. Please ensure the number is");
        buff.append(" a positive integer.\n");
        System.out.println(buff.toString());
    }
    
    /**
     * <p>Prints the help message.</p>
     */
    private void printHelp(){
        StringBuffer help = new StringBuffer();
        help.append("Prime test is a small application to test for the ");
        help.append("primality of a number or the closest prime to an ");
        help.append("upper bound.\n\n");
        help.append("Usage PrimeTest <method> [-f] <number type> <number> [<-w> <number>]\n\n");
        help.append(" Method Options \n");
        help.append("  -b \n");
        help.append("         Brute Force check if a number is prime\n");
        help.append("  -mr \n");
        help.append("         Miller-Rabin primality test.  Must also specify -w flag and number\n");
        help.append("  -mrd \n");
        help.append("         Miller-Rabin Deteriministic primality test.\n");
        help.append("  -fp \n");
        help.append("         Fermat primality test. Checks against Carmichael numbers. Must also specify -w flag and number\n");
        help.append("  -ss \n");
        help.append("         Solovay-Strassen primality test. Must also specify -w flag and number\n");
        help.append("  -h \n");
        help.append("         output this help message\n\n");
        help.append("  Number Type Options \n");
        help.append("  -n <number>\n");
        help.append("         Use the <number> specified.\n\n");
        help.append("  -g <number>\n");
        help.append("         Generate a number of <number> digits\n\n");
        help.append("  Witnesses Option \n");
        help.append("  -w <number>\n");
        help.append("         Use <number> witnesses in the Miller-Rabin, Fermat, or Solovay-Strassen tests.\n\n");
        help.append("  Other Options \n");
        help.append("  -v \n");
        help.append("         Show verbose output.\n\n");
        help.append("  -f \n");
        help.append("         Find the closest prime less than or equal to the specified or generated number.\n\n");
        help.append("Examples:\n\n");
        help.append("  java -jar PrimeTest.jar -b -n 19827362\n");
        help.append("     will brute force check the primality of 19827362\n\n");
        help.append("  java -jar PrimeTest.jar -mr -g 20 -w 30\n");
        help.append("     will use Miller-Rabin to check a random number of 20 digits\n ");
        help.append("     using 30 witnesses.\n\n");
        help.append("  java -jar PrimeTest.jar -mr -f -g 20 -w 30\n");
        help.append("     will use Miller-Rabin to check a random number of 20 digits\n");
        help.append("     using 30 witnesses. If a the generated number is not prime then\n");
        help.append("     it will proceed to find the largest prime smaller than the generated.\n");
        help.append("     number.\n\n");
        System.out.println(help.toString());
    }
}
