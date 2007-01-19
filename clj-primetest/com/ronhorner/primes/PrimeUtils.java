package com.ronhorner.primes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Ron Horner <br>
 * Number Theory and Cryptography CSCI-8566-001 <br>
 * Project 1 <br>
 * 
 * <p>
 * This Utility class will hold basic functions for computations on big numbers
 * that are not available in the standard Java Library.
 * </p>
 * 
 */

public class PrimeUtils {
    /** Convienence variable for the BigDecimal Zero */
    private static BigDecimal ZERO = new BigDecimal("0");

    /** Convienence variable for the BigDecimal One */
    private static BigDecimal ONE = new BigDecimal("1");

    /** Convienence variable for the BigDecimal Two */
    private static BigDecimal TWO = new BigDecimal("2");

    /** Convienence variable for the BigInteger Zero */
    private static BigInteger ZERO_INT = new BigInteger("0");

    /** Convienence variable for the BigInteger One */
    private static BigInteger ONE_INT = new BigInteger("1");

    /** Convienence variable for the BigInteger Two */
    private static BigInteger TWO_INT = new BigInteger("2");

    /** The maximum number of iterations when calculating the square root */
    public static final int DEFAULT_MAX_ITERATIONS = 50;

    /** The default scale for division functions */
    public static final int DEFAULT_SCALE = 10;

    /** The amount that the square root could be wrong. */
    static private BigDecimal error;

    /** Counter for the number of iterations */
    static private int iterations;

    /** The scale for division functions */
    static private int scale = DEFAULT_SCALE;

    /** The maximum number of iterations when calculating the square root */
    static private int maxIterations = DEFAULT_MAX_ITERATIONS;

    /** Thirty Seconds in milliseconds */
    static public final long THIRTY_SECONDS = 30000;

    /** Static representation of e */
    static public final BigDecimal E = new BigDecimal(
            "2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427427466391932003059921817413596629043572900334295260595630738132328627943490763233829880753195251");

    /**
     * <p>
     * Find the initital approximation to begin the search for the square root
     * of the prime number
     * </p>
     * 
     * <p>
     * adapted from <a href="http://www.merriampark.com/bigsqrt.htm">
     * http://www.merriampark.com/bigsqrt.htm
     * </a>
     * </p>
     * 
     * @param n
     *            the number to ge the approximation of when starting the square
     *            root function
     * @return the intitial approximation
     */
    static private BigDecimal getInitialApproximation(BigDecimal n) {
        BigInteger integerPart = n.toBigInteger();
        int length = integerPart.toString().length();
        if ((length % 2) == 0) {
            length--;
        }
        length /= 2;
        BigDecimal guess = ONE.movePointRight(length);
        return guess;
    }

    /**
     * <p>
     * Wrapper function to getSquareRoot(BigDecimal n)
     * </p>
     * 
     * @param n
     *            BigInteger to find the square root of
     * @return square root approximation
     */

    static public BigDecimal getSquareRoot(BigInteger n) {
        return getSquareRoot(new BigDecimal(n));
    }

    /**
     * <p>
     * Here we use the algorithm of the ancient greek mathematitian Heron of
     * Alexandria to find the square root of the number. It can be found in his
     * anthology Metrica, book 1 chapter 8. The algorithm has been redone over
     * the centuries by Newton and Ibn al-Banna.
     * </p>
     * 
     * <p>
     * adapted from <a href="http://www.merriampark.com/bigsqrt.htm">
     * http://www.merriampark.com/bigsqrt.htm
     * </a>
     * </p>
     * 
     * @param n
     *            the BigDecimal to find the square root of
     * @return the approximate square root.
     */
    static public BigDecimal getSquareRoot(BigDecimal n) {

        // Make sure n is a positive number

        if (n.compareTo(ZERO) <= 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal initialGuess = getInitialApproximation(n);
        // System.out.println ("Initial guess " + initialGuess.toString ());
        BigDecimal lastGuess = ZERO;
        BigDecimal guess = new BigDecimal(initialGuess.toString());

        // Iterate

        iterations = 0;
        boolean more = true;
        while (more) {
            lastGuess = guess;
            guess = n.divide(guess, scale, BigDecimal.ROUND_HALF_UP);
            guess = guess.add(lastGuess);
            guess = guess.divide(TWO, scale, BigDecimal.ROUND_HALF_UP);
            // System.out.println ("Next guess " + guess.toString ());
            error = n.subtract(guess.multiply(guess));
            if (++iterations >= maxIterations) {
                more = false;
            } else if (lastGuess.equals(guess)) {
                more = error.abs().compareTo(ONE) >= 0;
            }
        }
        return guess;
    }

    /**
     * <p>
     * Check for primality using a very simple method.
     * </p>
     * <p>
     * We will iterate through the odd numbers X and use the big integer B to
     * check B = 0 (mod X). If this is true then B is not prime and we exit.
     * Otherwise we continue the search till we reach the square root of B.
     * </p>
     * 
     * <dd><b>Input</b> : <i>n</i> : An odd integer greater than 1 </dd>
     * <dd><b>Output</b> : the answer <u>composite</u> or <u>prime</u></dd>
     * <dd><i>sqrt</i> := square root of <i>n</i></dd>
     * <dd>for <i>i</i> = 2; <i>i</i> &lt; <i>sqrt</i>+2; <i>i</i>++</dd>
     * <dl>
     * <dd>if (<i>n</i> mod <i>i</i> == 0) return <u>composite</u></dd>
     * </dl>
     * <dd>return <u>prime</u></dd>
     * 
     * @param n
     *            BigInteger to check for primality
     * @return true if the BigInteger is prime
     */
    static public boolean checkPrimality(BigInteger n, boolean verboseOutput) {
        boolean rval = true;
        long time = System.currentTimeMillis();
        if (verboseOutput){
            System.out.println("Big Integer    = " + n);
            System.out.println("Starting time  = " + time);
        }
        if (n.mod(TWO_INT).compareTo(ZERO_INT) == 0) {
            rval = false;
            if (verboseOutput)
                System.out.println(n + " is divisible by 2");
        } else {
            BigInteger sqrt = getSquareRoot(n).toBigInteger();
            BigInteger modulo = new BigInteger("3");
            if (verboseOutput){
                System.out.println("Square Root    = " + sqrt);
                System.out.println("Current Modulo = " + modulo);
            }
            while ((modulo.compareTo(sqrt) <= 0) && rval) {
                time = checkTime(modulo, time);
                if (n.mod(modulo).compareTo(ZERO_INT) == 0) {
                    rval = false;
                    if (verboseOutput){
                        System.out.println("Current Modulo = " + modulo);
                        System.out.println(n + " is divisible by " + modulo);
                    }
                } else {
                    modulo = modulo.add(TWO_INT);
                }
            }
            if (rval) {
                if (verboseOutput){
                    System.out.println("Current Modulo = " + modulo);
                }
            }
        }
        return rval;
    }

    /**
     * <p>
     * Utility function to output the current modulo every 30 seconds
     * </p>
     * 
     * @param m
     *            The current modulo
     * @param t
     *            the time since the last print out
     * @return time since last printout
     */
    static private long checkTime(BigInteger m, long t) {
        long rval = t;
        if (System.currentTimeMillis() - t >= THIRTY_SECONDS) {
            System.out.println("Current Modulo = " + m);
            rval = System.currentTimeMillis();
        }
        return rval;
    }
   
    /**
     * This primality check is significantly quick with an accuracy of 4^(-k)
     * 
     * <p>
     * This function is a direct transcription of the following psuedo code into
     * native java as found at this address
     * </P>
     * 
     * <p>
     * <a href="http://www.cryptomathic.com/labs/rabinprimalitytest.html">
     * http://www.cryptomathic.com/labs/rabinprimalitytest.html </a>
     * </p>
     * <br>
     * Algorithm for the Miller-Rabin probabilistic primality test<br>
     * Miller-Rabin(n,t)<br>
     * <dd><b>Input</b> : <i>n</i> : An odd integer greater than 1, <i>t</i> : the number of witnesses</dd>
     * <dd><b>Output</b> : the answer <u>composite</u> or <u>prime</u></dd>
     * <dd>Write <i>n</i>-1 = 2<sup><i>s</i></sup> * <i>r</i> such that <i>r</i> is odd</dd>
     * <dd>Repeat from 1 to  <i>t</i></dd>
     * <dd>Choose a random integer <i>a</i> which satisfies 1 < <i>a</i> < <i>n</i>-1</dd>
     * <dd>Compute <i>y</i> = <i>a</i><sup><i>r</i></sup> mod <i>n</i></dd>
     * <dd>if <i>y</i> <> 1 and <i>y</i> <> <i>n</i>-1 then</dd>
     * <dl>
     * <dd><i>j</i> := 1</dd>
     * <dd>while <i>j</i> < <i>s</i> and <i>y</i> <> <i>n</i>-1 then</dd>
     * <dl>
     * <dd><i>y</i> := <i>y</i><sup>2</sup> mod <i>n</i></dd>
     * <dd>if <i>y</i> = 1 then return <u>composite</u></dd>
     * <dd><i>j</i> := <i>j</i> + 1</dd>
     * </dl>
     * <dd>if <i>y</i> <> <i>n</i>-1 then return <u>composite</u></dd>
     * </dl>
     * <dd>return <u>prime</u></dd>
     * 
     * @param n
     *            The number in question
     * @param t
     *            The number of witnesses.
     * @return true if the number is most likely prime given the number of
     *         witnesses t.
     */
    public static boolean checkMillerRabin(BigInteger n, int t) {
        boolean rval = true;
        SecureRandom srnd = new SecureRandom();
        BigInteger r = n.subtract(ONE_INT);
        BigInteger s = ZERO_INT;
        BigInteger a = ZERO_INT;
        BigInteger y = ZERO_INT;

        while (r.mod(TWO_INT).compareTo(ZERO_INT) == 0) {
            r = r.divide(TWO_INT);
            s = s.add(ONE_INT);
        }

        for (int i = 1; i <= t && rval; i++) {
            a = new BigInteger(n.subtract(TWO_INT).bitCount(), srnd).add(TWO_INT);
            y = a.modPow(r, n);
            if (y.compareTo(ONE_INT) != 0 && (y.compareTo(n.subtract(ONE_INT)) != 0)) {
                BigInteger j = ONE_INT;
                while (j.compareTo(s.subtract(ONE_INT)) <= 0 && y.compareTo(n.subtract(ONE_INT)) != 0 && rval) {
                    y = y.modPow(TWO_INT, n);
                    if (y.compareTo(ONE_INT) == 0) {
                        rval = false;
                    }
                    j = j.add(ONE_INT);
                }
                if (y.compareTo(n.subtract(ONE_INT)) != 0) {
                    rval = false;
                }
            }
        }
        return rval;
    }

    /**
     * This function simply multiplies the constant E by itself a number 
     * of times till it is larger than N.
     * 
     * @param n The number to find the natural log for
     * @return the ceiling of the natural log of n
     */
    public static BigDecimal findApproxNaturalLog(BigInteger n){
        BigDecimal rval = ONE;
        BigDecimal tempE = E;
        BigDecimal decN = new BigDecimal(n);
        while (tempE.compareTo(decN) == -1){
            tempE = tempE.multiply(E);
            rval = rval.add(ONE);
        }
        return rval;
    }
    
    /**
     * <p>
     * This function is a variation of the classic Miller-Rabin test in that it
     * makes the function deterministic. This is done by not chosing random
     * numbers but by working our way through all numbers from 2 to 2*ln(n)^2.
     * </p>
     * <a href="http://en.wikipedia.org/wiki/Miller-Rabin_test">
     * http://en.wikipedia.org/wiki/Miller-Rabin_test
     * </a>
     * @param n
     *            The number in question
     * @return true if the number is prime for all witnesses
     */
    public static boolean checkMRD(BigInteger n) {
        boolean rval = true;
        
        SecureRandom srnd = new SecureRandom();
        BigInteger r = n.subtract(ONE_INT);
        BigInteger s = ZERO_INT;
        BigInteger a = ZERO_INT;
        BigInteger y = ZERO_INT;

        while (r.mod(TWO_INT).compareTo(ZERO_INT) == 0) {
            r = r.divide(TWO_INT);
            s = s.add(ONE_INT);
        }
        
        BigInteger t = new BigInteger(n.bitLength()+"");
        t = t.pow(2);
        t = t.multiply(TWO_INT);
        for (BigInteger i = TWO_INT; i.compareTo(t) < 1 && rval; i = i.add(ONE_INT)) {
            a = i;
            y = a.modPow(r, n);
            if (y.compareTo(ONE_INT) != 0 && (y.compareTo(n.subtract(ONE_INT)) != 0)) {
                BigInteger j = ONE_INT;
                while (j.compareTo(s.subtract(ONE_INT)) <= 0 && y.compareTo(n.subtract(ONE_INT)) != 0 && rval) {
                    y = y.modPow(TWO_INT, n);
                    if (y.compareTo(ONE_INT) == 0) {
                        rval = false;
                    }
                    j = j.add(ONE_INT);
                }
                if (y.compareTo(n.subtract(ONE_INT)) != 0) {
                    rval = false;
                }
            }
        }
        return rval;
    }
    
    /**
     * <p>This is a really simple primality test.</p>
     * 
     * Read more about it here<br>
     * 
     * <a href="http://en.wikipedia.org/wiki/Fermat_primality_test">
     * http://en.wikipedia.org/wiki/Fermat_primality_test
     * </a><br>
     * 
     * <dd><b>Inputs</b> : <i>n </i>: a value to test for primality; <i>k </i>:
     * a parameter that determines the accuracy of the test</dd>
     * <dd><b>Output </b>: <u>composite </u> if <i>n </i> is composite,
     * otherwise <u>probably prime </u></dd>
     * 
     * <dd>repeat <i>k </i> times:
     * <dl>
     * <dd>pick <i>a</i> randomly in the range [1, n-1] </dd>
     * <dd>if <i>a</i><sup>(<i>n</i>-1)</sup> mod <i>n</i> <> 1 then return <u>composite</u></dd>
     * </dl>
     * </dd>
     * <dd>return <u>probably prime </u></dd>
     * 
     * @param n 
     *            The number in question
     * @param w
     *            The number of witnesses.
     * @return true if the number is most likely prime given the number of
     *         witnesses w.
     */
    public static boolean checkFermatPrimality(BigInteger n, int w){
        boolean rval = true;
        //Check if it is a carmichael number
        rval = !Carmichael.NUMBERS.contains(n);
        SecureRandom srnd = new SecureRandom();
        for (int i = 0; i < w && rval; i++){
            BigInteger a = new BigInteger(n.subtract(TWO_INT).bitCount(), srnd).add(TWO_INT);
            if (a.modPow(n.subtract(ONE_INT), n).compareTo(ONE_INT) != 0){
                rval = false;
            }
        }
        return rval;
    }
      
    /**
     * Computes the Jacobi Symbol of two integers a and n<br>
     * 
     * <dd><b>Inputs </b>: <i>a </i>: the numerator for the Jacobi Symbol; <i>n </i>:
     * the odd denominator for the Jacobi Symbol</dd>
     * <dd><b>Output </b>: <u>-1</u>, <u>0</u> or <u>1</u></dd>
     * 
     * <dl>
     * <dt>Jacobi(<i>a</i>,<i>n</i>) {</dt>
     * <dd><i>j</i> := 1</dd>
     * <dd>while (<i>a</i> not 0) do {</dd>
     * <dl>
     * <dd>while (<i>a</i> even) do {</dd>
     * <dd>
     * <dl>
     * <dd><i>a</i> := <i>a</i>/2</dd>
     * <dd>if (<i>n</i> = 3 (mod 8) or <i>n</i> = 5 (mod 8)) then <i>j</i> := -<i>j</i></dd>
     * </dl>
     * </dd>
     * <dd>}</dd>
     * <dd>interchange(<i>a</i>,<i>n</i>)</dd>
     * <dd>if (<i>a</i> = 3 (mod 4) and <i>n</i> = 3 (mod 4)) then <i>j</i> := -<i>j</i></dd>
     * <dd><i>a</i> := <i>a</i> mod <i>n</i></dd>
     * </dl>
     * <dd>}</dd>
     * <dd>if (<i>n</i> = 1) then return (<i>j</i>) else return(0)</dd>
     * <dt>}</dt>
     * </dl>
     * 
     * @param a
     *            any number greater than or equal to one and less than n
     * @param n
     *            any odd number
     * @return the value of the jacobi symbol (a/n)
     */
    public static int jacobiSymbol(BigInteger a, BigInteger n){
        int j = 1;
        BigInteger THREE = new BigInteger("3");
        BigInteger FOUR = new BigInteger("4");
        BigInteger FIVE = new BigInteger("5");
        BigInteger EIGHT = new BigInteger("8");
        BigInteger res;
        BigInteger temp;
        while (a.compareTo(ZERO_INT) != 0){
            while (a.mod(TWO_INT).compareTo(ZERO_INT) == 0){
                a = a.divide(TWO_INT);
                res = n.mod(EIGHT);
                if (res.compareTo(THREE) == 0 || res.compareTo(FIVE) == 0){
                    j = -1*j;
                }
            }
            temp = a;
            a = n;
            n = temp;
           
            if (a.mod(FOUR).compareTo(THREE) == 0 && n.mod(FOUR).compareTo(THREE) == 0){
                j = -1 * j;
            }
            a = a.mod(n);
        }
        
        if (n.compareTo(ONE_INT) != 0)
            j = 0;

        return j;
    }
    
    /**
     * This is a very good and very fast primality check.  It is remarkably accurate
     * when using around 100 witneses.  The accuracy is 2^(-k)
     * 
     * <br>
     * <br>
     * 
     * Read more about it here <br>
     * 
     * <a href="http://en.wikipedia.org/wiki/Solovay-Strassen_primality_test">
     * http://en.wikipedia.org/wiki/Solovay-Strassen_primality_test </a><br>
     * 
     * <dd><b>Inputs </b>: <i>n </i>: a value to test for primality; <i>k </i>:
     * a parameter that determines the accuracy of the test</dd>
     * <dd><b>Output </b>: <u>composite </u> if <i>n </i> is composite,
     * otherwise <u>probably prime </u></dd>
     * 
     * <dd>repeat <i>k </i> times:
     * <dl>
     * <dd><i>x </i>&lsaquo;&mdash; ( <i>a </i>/ <i>n </i>)</dd>
     * <dd>if <i>x </i>= 0 or <i>a </i> <sup>( <i>n</i>-1)/2 </sup> mod
     * <i>n </i> <> <i>x </i> then return <u>composite </u></dd>
     * 
     * </dl>
     * </dd>
     * <dd>return <u>probably prime </u></dd>
     * 
     * @param n The number to check for primality
     * @param w The number of witnesses
     * @return true if the number is most likely prime given the number of
     *         witnesses w.
     */
    public static boolean checkSolovayStrassen(BigInteger n, int w){
        boolean rval = true;
        BigInteger n_minus_one =  n.subtract(ONE_INT);
        BigInteger tmp;
        SecureRandom srnd = new SecureRandom();
        for (int i = 0; i < w && rval; i++){
            BigInteger a = new BigInteger(n.subtract(TWO_INT).bitCount(), srnd).add(TWO_INT);
            int x = jacobiSymbol(a, n);
            BigInteger X = new BigInteger(x+"");
            tmp = a.modPow(n_minus_one.divide(TWO_INT),n);
            if (x == 0 || (tmp.compareTo(X) != 0) && (tmp.compareTo(n_minus_one) != 0)){
                rval = false;
            }
        }
        return rval;
    }
}
