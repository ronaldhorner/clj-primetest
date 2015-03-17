Test for Prime Numbers

Summary
> This project encompasses 5 different tests for primality.  The methods implemented are Miller-Rabin, Miller-Rabin Deterministic, Solovay-Strassen, Fermat’s Primality Test and a Brute Force method.  In addition to the 5 primality tests, the program is also capable of finding the closest integer to a given upper bound.  The largest number it is capable of handling is a 2147483647 bit integer.

Primality Tests

Miller-Rabin
> This primality test is a probabilistic primality test that has a degree of certainty of 4^(-k) where k is the number of unique witnesses less than n-1.  This primality test is very fast and very accurate.  This is the default primality test for the project.


Miller-Rabin Deterministic
> A variation on the Miller-Rabin test, it uses an unproven conjecture for finding deterministically if the number in question is prime.  It does this by checking all numbers from 2 to 2 **(log n)^2.**


Solovay-Strassen
> Solovay-Strassen is a variation on the Miller-Rabin test.  It is accurate to 2^(-k) where k is the number of unique witnesses less than n-1.  While not as accurate as the Miller-Rabin test it is very quick.


Fermat’s Primality test
> Fermat’s little theorem is a simple primality test.  While the algorithm is susceptible to Carmichael numbers, this implementation is not.  It is known that there are an infinite number of Carmichael numbers.  While this is true, there are only a handful of known Carmichael numbers.  Because of this we can use Fermat’s test for a very fast and very accurate primality test simply by verifying if the number in question is a known Carmichael number.


Brute Force
> This is the simplest of all primality tests.  We simply divide the number by every prime less than the square root of the number.  Since it takes a good deal of time to calculate all primes less than the square root of a very large number, this algorithm simply divides by all odds between 3 and the square root of the number rounded up.


The Application

Requirements

1.	Java Runtime 1.5 or later installed.  This can be obtained from http://java.sun.com

2.	Access to the command line interface of your operating system

3.	A web browser such as Internet Explorer, Mozilla Firefox, Opera or Safari.

Run the application
1.	open up your command line interface.

> a.	On Windows

> i.	Click start

> ii.	Click run

> iii.	Type cmd and click okay

> iv.	Change directories to where you have the PrimeTest.jar saved

> cd c:\directory\to\Primtest\

> b.	On Unix (Linux, Mac OS X, Solaris)

> i.	Open your terminal

> ii.	Change directories to where you have the PrimeTest.jar saved,
> > cd /directory/to/PrimeTest/

2.	type java –version to ensure you have java installed.  The version reported should be 1.5 or later.

3.	Follow any of the commands illustrated below

4.	To get the source or java docs simply unzip the jar file using your favorite unzip utility such as WinZip, WinRar or unzip

Usage

> There are a number of options supported by the application.  General usage is as follows:

java –jar PrimeTest.jar 

&lt;method&gt;

 [-f -v] <number type> 

&lt;number&gt;

 [-w 

&lt;number&gt;

]
Method Options
-b
> Brute Force check if a number is prime
-mr
> Miller-Rabin primality test. Must be used in conjunction with -w. See below for information.
-mrd
> Miller-Rabin Deterministic primality test.
-fp
> Fermat primality test. Checks against Carmichael numbers. Must be used in conjunction with -w. See below for information.
-ss
> Solovay-Strassen primality test. Must be used in conjunction with -w. See below for information.
-h
> output this help message

Number Type Options
-n number
> Use the number specified.
-g number
> Generate a number of number digits

Witnesses Option
-w number
> Use number witnesses in the Miller-Rabin, Fermat, or Solovay-Strassen tests.

Other Options
-v
> Show verbose output.
-f
> Find the closest prime less than or equal to the specified or generated number.

Examples:
java -jar PrimeTest.jar -b -n 19827362
will brute force check the primality of 19827362

java -jar PrimeTest.jar -mr -g 20 -w 30
will use Miller-Rabin to check a random number of 20 digits using 30 witnesses.

java -jar PrimeTest.jar -mr -f -g 20 -w 30
will use Miller-Rabin to check a random number of 20 digits using 30 witnesses. If the generated number is not prime then it will proceed to find the largest prime smaller than the generated. number.

Documentation

All of the documentation is included in the jar file.  Follow step 4 of the Run the Application  section.

Project Layout
The project is distributed as an Eclipse Project.  Simply check out the source code and import it into our Eclipse workspace.


License

Copyright (c) 2006 Ron Horner

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the 'Software'), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

If we meet some day, and you think this stuff is worth it, you can buy me a beer in return.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.