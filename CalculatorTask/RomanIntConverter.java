package CalculatorTask;

import java.util.Scanner;

public class RomanIntConverter {

    static int value(char r) {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if( r == 'M')
            return 1000;

        return -1;
    }

    static char romanValue(int a){
        if(a == 1)
            return 'I';
        if(a == 5)
            return 'V';
        if(a == 10)
            return'X';
        if(a == 50)
            return 'L';
        if(a == 100)
            return 'C';
        if(a == 500)
            return 'D';
        if(a == 1000)
            return 'M';

        return '$';
    }



    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        calculatorCycle(console);


    }

        static void calculatorCycle(Scanner console){
            while(true){
                printMessages();
                String input = console.nextLine();

                if(input==""){
                    System.out.println("Please enter data\n");
                    continue;
                }
                else if(input.equalsIgnoreCase("Exit"))
                {
                    System.out.println("Program ended. Thank you");
                    break;
                }
                else
                {
                    calculatorProgram(input);
                }
            }
        }//Cycle of the calculator to perform continuous
        static void calculatorProgram(String str){

            while(!str.isBlank()) {
                String input = str;
                String[] elements = input.split(" ");
                if (isArabicNum(elements[0]) || isArabicNum(elements[2])) {
                    if(Integer.parseInt(elements[0])>10 || Integer.parseInt(elements[2])>10){
                        System.out.println("One of Arabic numbers is bigger than 10. Try again");
                        break;
                    }// Restriction to limit numbers till 10 to satisfy condition -3  in task
                    arabicNumberOperation(input, elements);
                } else if (isRomanNum(elements[0]) || isRomanNum(elements[2])) {
                    if(romanToIntConverter(elements[0])>10||romanToIntConverter(elements[2])>10){
                        System.out.println("One of the Roman number is bigger than X. Try again");
                        break;
                    }// Restriction to limit numbers till 10 to satisfy condition -3  in task
                    romanNumberOperation(input, elements);
                }
                str="";

            }

          } // Calculator Program in 1 method
        static int romanToIntConverter(String roman) {
            int length = roman.length();
            int sum = 0;
            if(length>1) {
                for (int i = 0; i < length; i++) {
                    int value = value(roman.charAt(i));

                    if (i == length - 1) {
                        if (value <= value(roman.charAt(i - 1))) {
                            sum = sum + value;
                            continue;
                        } else {
                            sum = value - sum;
                            continue;
                        }

                    }
                    if (i == 0) {
                        sum = value;
                        continue;
                    }
                    if (value > value(roman.charAt(i - 1))) {
                        sum = value - sum;
                    } else {
                        sum += value;
                    }
                }
            }
            else
            {
                sum=value(roman.charAt(0));
            }
            return sum;
        }// roman -> int converter Works

        static String integerToRomanConverter(int number){
        String romanNumber="";
        String numbstr = String.valueOf(number);
        int size = numbstr.length();

        while(number>10 ||number==1){
            if(number/100>0){
                romanNumber+=romanToIntCounter(number,100);
                number=number - (number/100*100);
            }
            if(number/50>0){
                romanNumber+=romanToIntCounter(number,50);
                number = number - (number/50*50);
            }
            if(number/10>0){
                romanNumber+=romanToIntCounter(number,10);
                number = number - (number/10*10);
            }
            if(number<=10){
                romanNumber= romanTenValue(number,romanNumber);
                break;
            }


        }


        return romanNumber;
        }//int -> roman converter

        static String romanToIntCounter(int number,int delimeter){
        String result = "";
        int charCounter = number/delimeter;
        char value = romanValue(delimeter);
        while(charCounter>0){
            result +=value;
            charCounter--;
        }

        return result;
        }//for numbers 11-100
        static String  romanTenValue(int a,String roman){
        String result = roman;
        String value;
        switch (a){
            case 1:
                value=result+"I";
                break;
            case 2:
                value=result+"II";
                break;
            case 3:
                value=result+"III";
                break;
            case 4:
                value=result+"IV";
                break;
            case 5:
                value=result+"V";
                break;
            case 6:
                value=result+"VI";
                break;
            case 7:
                value=result+"VII";
                break;
            case 8:
                value=result+"VIII";
                break;
            case 9:
                value=result+"IX";
                break;
            case 10:
                value=result+"X";
                break;
            default:
                value= result;


            }
        return value;
        }//Roman values 1-10
         static void arabicNumberOperation(String inputLine, String[] elements){


        int a, b;
        a = Integer.parseInt(elements[0]);
        b = Integer.parseInt(elements[2]);
        switch (elements[1]) {
            case "*":
                System.out.println(inputLine + " = " + (a * b));
                break;
            case "/":
                System.out.println(inputLine + " = " + (a / b));
                break;
            case "+":
                System.out.println(inputLine + " = " + (a + b));
                break;
            case "-":
                System.out.println(inputLine + " = " + (a - b));
                break;
            default:
                throw new RuntimeException();
        }


    }//complete Arabic numbers operation algorithm

        static void romanNumberOperation(String inputLine,String[] elements){
            int result=0;
            int a,b;
            a=romanToIntConverter(elements[0]);
            b=romanToIntConverter(elements[2]);


            switch (elements[1]) {
                case "*":
                   result = a * b;
                    break;
                case "/":
                    result=a / b;
                    break;
                case "+":
                    result=a+b;
                    break;
                case "-":
                    if(b>a)
                        throw new RuntimeException();

                    result=a-b;
                    break;
                default:
                    throw new RuntimeException();
            }
            String answer = result >10?integerToRomanConverter(result):String.valueOf(romanTenValue(result,""));

            System.out.println(inputLine+" = " +answer);

        }//complete Roman numbers operation algorithm
         static boolean isArabicNum(String s){
        try
        {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    } //checks if number is arabic
        static boolean isRomanNum(String roman){
        try{
            romanToIntConverter(roman);
            return true;
        }catch(Exception e){
            return false;
        }
    }
        static void printMessages(){
        System.out.println("\nEnter you expression ,Like examples below. Possible operation ( + , - , / , * )");
        System.out.println("Arabic example : (6 + 8) ");
        System.out.println("Roman example : (VII * X) ");
            System.out.println("type \"exit\" to end program");
    }
}
