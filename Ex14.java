package com.company;

/**
 *
 *
 * @author Daniel Levin ID 315048587
 * @version 16.1.2021
 *
 *
 */

public class Ex14 {

    public static void main(String[] args) {

    }

    /**
     * finds the lone number in an array filled with pairs of numbers
     * time complexity = O(log_n)
     * space complexity = O(1)
     *
     * @param a the array of numbers
     * @return the INDEX of the lone number, INDEX as requested in the maman
     */

    public static int findSingle(int[] a) {

        /*
       question 1 - binary search variation,

       O(log_n) time complexity, binary search
       0(1) space complexity, using only primitive variables that hold a set location in memory to solve the problem.

       using the logic that because there are fairs of identical numbers you need to check the n location, n is even,
       and n + 1 location to have the same pairs i.e if you have an array {0,0,1,1,3,2,2}
       number in EVEN location index 2 must be equal to index (2+1), index 3, so 1 = 1
       if NOT "something" shifted the array right, that something is the number without a pair, the number we are
       looking for.
        */

        if (a.length == 1)//checking the option the array is of size 1 and contains only the number we are looking for
            return 0;
        if (a[0] != a[1])//checks the first pairs of numbers
            return 0;
        if (a[a.length - 1] != a[a.length - 2])//checks the last pairs of numbers
            return a.length - 1;

        int middleCheck = a.length / 2;//getting the middle index of the array
        int previousMiddle = 0;

        while (middleCheck != previousMiddle) {//if we reach the number we are looking for if we get stuck on itself and exit the loop

            if (a[middleCheck] == a[middleCheck + 1] || a[middleCheck] == a[middleCheck - 1]) {//checks if the middle number equals its left or right partner

                if (a[middleCheck] == a[middleCheck + 1]) {//if the middle's partner is on the right side everything is ok and the number is on the right side of the middle

                    previousMiddle = middleCheck;//saving the current middle, in case the lone number is before the new middle.
                    middleCheck = (a.length + middleCheck) / 2;//getting the middle of the right side

                } else//if the middle's partner is on the left side, the lone number shifted the array to the right
                    middleCheck = (middleCheck + previousMiddle) / 2;//getting the new middle using the old middle.

            } else
                return middleCheck;//if we reached the lone number and he has no partners on the sides

        }

        return middleCheck;//if we exited the loop we reached the lone number.

    }

    /**
     * finds the smallest sub array of numbers in the given array that surpasses "x"
     * time complexity = O(n)
     * space complexity = O(1)
     *
     * @param arr the array of numbers that contain the possible sub array the surpasses "x"
     * @param x the number which you wish you surpass
     * @return the smallest size of the sub array if it exists, if not returns -1
     */

    public static int smallestSubSum(int arr[], int x) {

            /*
       question 2 - using 2 pointers,

       O(n) time complexity, needing to go over the whole array.
       0(1) space complexity, using only primitive variables that hold a set location in memory to solve the problem.

       adding the numbers from the beginning of the array until we surpass the "x" value, then we check if we can remove the first numbers in the start
       of the current "array" by comparing them to the last number added, then we save the number of numbers using to ger to that answer and then we
       move the "start number" pointer from the beginning of the array to the last answer, first num location + 1.
        */

        if (arr.length == 0)//checking edge case if the array is empty
            return -1;
        if (arr[0] > x)//if the first number is bigger then "x"
            return 1;
        if (arr[arr.length - 1] > x)//if the last number is bigger then "x"
            return 1;

        int sum = 0;
        int firstNum = 0;
        int lastNum = 0;
        int numOfNums = 0;
        int smallestNumber = 0;
        boolean firstTime = true;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > x)//check if there is 1 number in the array that is already bigger then "x" to return "1"
                return 1;

            sum += arr[i];

            if (sum > x) {

                lastNum = i;//setting the location of the last num of the current array

                while (sum > x) {//trying to "remove" the first numbers while still staying over "x"

                    sum = sum - arr[firstNum];
                    firstNum++;//shifting the start position pointer

                }

                firstNum--;//moving the pointer one step back because that number lets us surpass "x"

                numOfNums = lastNum - firstNum + 1;// getting the number of numbers used to get to the solution

                if (firstTime) {//if we just started the program

                    firstTime = false;
                    smallestNumber = numOfNums;

                }

                if (smallestNumber > numOfNums)
                    smallestNumber = numOfNums;

                sum = 0;//resetting the sum
                i = firstNum + 1;//moving the "i" to the next sub-array

            }

        }

        if (smallestNumber > 0)//if there is a sub array
            return smallestNumber;
        else
            return -1;

    }

    /**
     * finds all possible solutions to the equation x+y+z = num
     * and prints them
     *
     * @param num the number you wish to find all the combinations to
     * @return the number of possible combinations
     */

    public static int solutions(int num) {

        /*
        question 3 - using a private method to get all the combinations of x+y+z

        sending the "num" and x,y,z to a private method, the method gets all the combinations of the solution
        and then strings all the solutions together to remove duplicate solutions, i.e
        2+1+2
        2+1+2
        can be reached by :
        1+1,1,2
        2,1,1+1
         */

        int x = 1;
        int y = 1;
        int z = 1;
        String xyz = "";

        if (num < 3 || num > 30)//if there are no possible solutions
            return 0;
        else
            return removeDuplicate(solutions(num, x, y, z));

    }

    private static String solutions(int num, int x, int y, int z) {

        if (x + y + z == num) {

            return "" + x + y + z;//returns the answer via string

        }

        return (solutions(num, x, y, z + 1) +
                solutions(num, x, y + 1, z) +
                solutions(num, x + 1, y, z));

    }

    private static int removeDuplicate(String s) {

        if (s.equals(""))// if the received answer is empty
            return 0;

        if (s.substring(3).contains(s.substring(0, 3))) {//checks if there are duplicate answers

            s = s.substring(3);//removes the duplicate

        }

        System.out.println(s.charAt(0) + "+" + s.charAt(1) + "+" + s.charAt(2));//prints the 3 num combination
        return 1 + removeDuplicate(s.substring(3));//sends the next string without the last 3 numbers and counts how many answers there are

    }

    /**
     *counts all the "truth" areas in a 2D array
     *
     * @param mat the 2D array
     * @return the amount of truth areas in the array
     */

    public static int cntTrueReg(boolean[][] mat) {

        /*
        question 4 - going over the 2D array and if you find "1" check neighbors

        we will go over the 2D array and when we find "1" we will check the locations near it
        up, down, left, right we will have some edge cases, corners and sides where you can only check
        2 directions or 3
         */

        if (mat.length == 1)//if the 2D array is of size 1
            if (mat[0][0])
                return 1;
            else
                return 0;

        int verAxis = 0;//y coordinate of the 2D array
        int horAxis = 0;//x coordinate of the 2D array

        int count = 0;

        return cntTrueReg(mat, verAxis, horAxis, count);

    }

    private static int cntTrueReg(boolean[][] mat, int verAxis, int horAxis, int count) {

        if (mat[verAxis][horAxis]) {//if we reached a "truth area"

            mat[verAxis][horAxis] = false;//to prevent infinite loop and getting stuck on the same location

            if (verAxis == 0 && horAxis == 0)//left upper corner case
                if (!mat[verAxis][horAxis + 1] && !mat[verAxis + 1][horAxis])//if it has only "0" around it means its the end of the "1" area
                    count++;

            if (verAxis == 0 && horAxis == mat.length - 1)//right upper corner case
                if (!mat[verAxis][horAxis - 1] && !mat[verAxis + 1][horAxis])//if it has only "0" around it means its the end of the "1" area
                    count++;

            if (verAxis == mat.length - 1 && horAxis == 0)//lower left corner case
                if (!mat[verAxis - 1][horAxis] && !mat[verAxis][horAxis + 1])//if it has only "0" around it means its the end of the "1" area
                    count++;

            if (verAxis == mat.length - 1 && horAxis == mat.length - 1)//lower right corner case
                if (!mat[verAxis - 1][horAxis] && !mat[verAxis][horAxis - 1])//if it has only "0" around it means its the end of the "1" area
                    count++;

            if (verAxis == 0 && horAxis < mat.length - 1 && horAxis > 0)//upper parameter case
                if (!mat[verAxis + 1][horAxis] && !mat[verAxis][horAxis - 1] && !mat[verAxis][horAxis + 1])//if it has only "0" around it means its the end of the "1" area
                    count++;

            if (verAxis < mat.length - 1 && horAxis == 0 && verAxis > 0)//left parameter case
                if (!mat[verAxis + 1][horAxis] && !mat[verAxis - 1][horAxis] && !mat[verAxis][horAxis + 1])//if it has only "0" around it means its the end of the "1" area
                    count++;

            if (verAxis == mat.length - 1 && horAxis < mat.length - 1 && horAxis > 0)//lower parameter case
                if (!mat[verAxis - 1][horAxis] && !mat[verAxis][horAxis - 1] && !mat[verAxis][horAxis + 1])//if it has only "0" around it means its the end of the "1" area
                    count++;

            if (verAxis < mat.length - 1 && horAxis == mat.length - 1 && verAxis > 0)//right parameter case
                if (!mat[verAxis - 1][horAxis] && !mat[verAxis + 1][horAxis] && !mat[verAxis + 1][horAxis])//if it has only "0" around it means its the end of the "1" area
                    count++;

                //check all 4 directions, if it has only "0" around it means its the end of the "1" area
            if (horAxis > 0 && verAxis > 0 && verAxis < mat.length - 1 && horAxis < mat.length - 1 && !mat[verAxis][horAxis + 1] && !mat[verAxis][horAxis - 1] && !mat[verAxis + 1][horAxis] && !mat[verAxis - 1][horAxis])
                count++;

        }

        if (verAxis > mat.length - 2)// to prevent the recursion to go out of bounds
            if (horAxis == mat.length - 1 && verAxis == mat.length - 1)// reached the end of the 2D array
                return count;//number of truth areas
            else
                return cntTrueReg(mat, verAxis - (mat.length - 1), horAxis + 1, count);//check the next file
        else
            return cntTrueReg(mat, verAxis + 1, horAxis, count);

    }

}