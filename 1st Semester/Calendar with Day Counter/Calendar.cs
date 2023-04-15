using System;

namespace Calendar
{
    class MainClass
    {
        public static void Main(string[] args)
        {
            string option = "1";
            do //With the do-while loop, the program can be run more than once
            {
                Console.Clear();
                Console.WriteLine("--------Caution before choosing the dates--------");
                Console.WriteLine("The dates you will enter must be after 01.01.2015");
                Console.WriteLine("You must write the name of the month!");
                Console.WriteLine("You should not write the month using numbers!");
                Console.WriteLine("");
                Console.WriteLine("");
                Console.WriteLine("Please enter the day 1 : "); //Taking first day and checking input
                string day1Str = Console.ReadLine();                              
                int day1;
                bool validityDay1 = Int32.TryParse(day1Str, out day1); 
                if (validityDay1)
                {
                }
                else
                {
                    InvalidEntry("You must enter the day with numbers! Try Again!");
                    continue;
                }
                Console.WriteLine("Please enter the month 1 :");
                string month1 = Console.ReadLine(); //Taking first month and checking input
                month1 = month1.ToLower();
                int month1_int = 0;
                if (Int32.TryParse(month1, out month1_int))
                {
                    InvalidEntry("You must enter the month as a string!!");
                    continue;
                }
                else if (month1 == "")
                {
                    InvalidEntry("Please, enter the month as a string.");
                    continue;
                }
                switch (month1)
                {
                    case "january":
                        month1_int = 1;
                        break;
                    case "february":
                        month1_int = 2;
                        break;
                    case "march":
                        month1_int = 3;
                        break;
                    case "april":
                        month1_int = 4;
                        break;
                    case "may":
                        month1_int = 5;
                        break;
                    case "june":
                        month1_int = 6;
                        break;
                    case "july":
                        month1_int = 7;
                        break;
                    case "august":
                        month1_int = 8;
                        break;
                    case "september":
                        month1_int = 9;
                        break;
                    case "october":
                        month1_int = 10;
                        break;
                    case "november":
                        month1_int = 11;
                        break;
                    case "december":
                        month1_int = 12;
                        break;
                } //Changing month string to month int
                Console.WriteLine("Please enter the year 1 :");
                string year1Str = Console.ReadLine(); //Taking first year and checking input
                int year1;
                bool validityYear1 = Int32.TryParse(year1Str, out year1);
                if (validityYear1)
                {
                }
                else
                {
                    InvalidEntry("You must enter the year as a number. Try Again!");
                    continue;
                }
                Validity(year1, month1_int, day1); //Validation for first date
                Console.WriteLine("Please enter the day 2 :");
                string day2Str = Console.ReadLine(); //Taking second day and checking input
                int day2;
                bool validityDay2 = Int32.TryParse(day2Str, out day2);
                if (validityDay2)
                {
                }
                else
                {
                    InvalidEntry("You must enter the day with numbers! Try Again!");
                    continue;
                }
                Console.WriteLine("Please enter the month 2 :");
                string month2 = Console.ReadLine(); //Taking second month and checking input
                month2 = month2.ToLower();
                int month2_int = 0;
                if (Int32.TryParse(month2, out month2_int))
                {
                    InvalidEntry("You must enter the month as a string!!");
                    continue;
                }
                else if (month2 == "")
                {
                    InvalidEntry("Please, enter the month as a string.");
                    continue;
                }
                switch (month2)
                {
                    case "january":
                        month2_int = 1;
                        break;
                    case "february":
                        month2_int = 2;
                        break;
                    case "march":
                        month2_int = 3;
                        break;
                    case "april":
                        month2_int = 4;
                        break;
                    case "may":
                        month2_int = 5;
                        break;
                    case "june":
                        month2_int = 6;
                        break;
                    case "july":
                        month2_int = 7;
                        break;
                    case "august":
                        month2_int = 8;
                        break;
                    case "september":
                        month2_int = 9;
                        break;
                    case "october":
                        month2_int = 10;
                        break;
                    case "november":
                        month2_int = 11;
                        break;
                    case "december":
                        month2_int = 12;
                        break;
                }//Changing month string to month int
                Console.WriteLine("Please enter the year 2 :");
                string year2Str = Console.ReadLine(); //Taking second year and checking input
                int year2;
                bool validityYear2 = Int32.TryParse(year2Str, out year2);
                if (validityYear2)
                {
                }
                else
                {
                    InvalidEntry("You must enter the day with numbers! Try Again!");
                    continue;
                }
                Validity(year2, month2_int, day2); //Validation for second date
                Console.WriteLine("What intervals would you like to sort the days between two dates?");
                int intervals = Convert.ToInt16(Console.ReadLine());
                Console.Clear();
                int day__ = 0;
                if (year1 < year2) //The main program
                {
                    for (int i = year1; i <= year2; i++)
                    {
                        if (i == year1)
                        {
                            day__ = PrintDays(year1, month1_int, 12, day1, intervals);
                        }
                        else if (i != year1 && i != year2)
                        {
                            day__ = PrintDays(i, 1, 12, day__, intervals);
                        }
                        else
                        {
                            PrintDays(year2, 1, month2_int, day__, intervals);
                            break;
                        }
                    }
                }
                else if (year1 == year2)
                {
                    if (month1_int < month2_int)
                    {
                        PrintDays(year1, month1_int, month2_int, day1, intervals);
                    }
                    else if (month1_int == month2_int)
                    {
                        if (day1 < day2)
                        {
                            for (int i = day1; i <= day2; i += intervals)
                            {
                                Console.Write("{0} {1} {2} ", i, month1, year1);
                                findTheDay(i, month1_int, year1);
                            }
                        }
                        else if (day1 == day2)
                        {
                            Console.Write("You entered the same dates! The date is {0} {1} {2} ", day1, month1, year1);
                            findTheDay(day1, month1_int, year1);
                        }
                        else
                        {
                            for (int i = day2; i <= day1; i += intervals)
                            {
                                Console.Write("{0} {1} {2} ", i, month2, year2);
                                findTheDay(i, month2_int, year2);
                            }
                        }
                    }
                    else
                    {
                        PrintDays(year2, month2_int, month1_int, day2, intervals);
                    }
                }
                else if (year1 > year2)
                {
                    for (int i = year2; i <= year1; i++)
                    {
                        if (i == year2)
                        {
                            day__ = PrintDays(year2, month2_int, 12, day2, intervals);
                        }
                        else if (i != year2 && i != year1)
                        {
                            day__ = PrintDays(i, 1, 12, day__, intervals);
                        }
                        else
                        {
                            PrintDays(year1, 1, month1_int, day__, intervals);
                        }
                    }
                }
                Console.WriteLine("");
                Console.WriteLine("");
                Console.WriteLine("If you want to enter new dates press 1");
                Console.WriteLine("If you want to quit press 0");
                option = Console.ReadLine();
                int option_;
                if (!(Int32.TryParse(option, out option_)))
                {
                    Console.WriteLine("Please make a choice ");
                    option = Console.ReadLine();
                    continue;
                }
                Console.Clear();
            } while (option == "1");
        }
        public static void InvalidEntry(string message)
        {
            Console.ForegroundColor = ConsoleColor.Red;
            Console.WriteLine(message);
            Console.ForegroundColor = ConsoleColor.White;
            Console.ReadLine();
        } //This method used for print invalid entry message and change the color of the string
        public static string SwitchMonths(int month)
        {
            switch (month)
            {
                case 1:
                    return "January";
                case 2:
                    return "February";
                case 3:
                    return "March";
                case 4:
                    return "April";
                case 5:
                    return "May";
                case 6:
                    return "June";
                case 7:
                    return "July";
                case 8:
                    return "August";
                case 9:
                    return "September";
                case 10:
                    return "October";
                case 11:
                    return "November";
                case 12:
                    return "December";
                default:
                    return " ";
            }
        } //Changing the month int's to strings for printing
        public static bool leapYear(int year)
        {
            if (year % 4 == 0)
            {
                if (year % 100 == 0)
                {
                    if (year % 400 == 0)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return true;
                }
            }
            else
            {
                return false;
            }
        } //Calculating the leap years
        public static int PrintDays(int year, int month1, int month2, int day, int intervals)
        {
            int lastDay = 0;
            for (int i = month1; i <= month2; i++)
            {
                string season = "";
                if (i == 1 || i == 2 || i == 12)
                {
                    season = "WINTER";
                }
                else if (i == 3 || i == 4 || i == 5)
                {
                    season = "SPRING";
                }
                else if (i == 6 || i == 7 || i == 8)
                {
                    season = "SUMMER";
                }
                else if (i == 9 || i == 10 || i == 11)
                {
                    season = "AUTUMN";
                }
                Console.WriteLine("");
                Console.WriteLine(season);
                Console.WriteLine("");
                if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12)
                {
                    for (int j = day; j <= 31; j += intervals)
                    {
                        string month_Str = SwitchMonths(i);
                        Console.Write("{0} {1} {2} ", j, month_Str, year);
                        findTheDay(j, i, year);
                        lastDay = j;
                    }
                    day = (lastDay - 31) + intervals;
                }
                else if (i == 2)
                {
                    if (leapYear(year) == true)
                    {
                        for (int j = day; j <= 29; j += intervals)
                        {
                            string month_Str = SwitchMonths(i);
                            Console.Write("{0} {1} {2} ", j, month_Str, year);
                            findTheDay(j, i, year);
                            lastDay = j;
                        }
                        day = (lastDay - 29) + intervals;
                    }
                    else
                    {
                        for (int j = day; j <= 28; j += intervals)
                        {
                            string month_Str = SwitchMonths(i);
                            Console.Write("{0} {1} {2} ", j, month_Str, year);
                            findTheDay(j, i, year);
                            lastDay = j;
                        }
                        day = (lastDay - 28) + intervals;
                    }
                }
                else
                {
                    for (int j = day; j <= 30; j += intervals)
                    {
                        string month_Str = SwitchMonths(i);
                        Console.Write("{0} {1} {2} ", j, month_Str, year);
                        findTheDay(j, i, year);
                        lastDay = j;
                    }
                    day = (lastDay - 30) + intervals;
                }
            }
            return day;
        } //This method used for printing the days between given dates
        public static void Validity(int year, int month, int day)
        {
            if (year >= 2015)
            {
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                {
                    if (day >= 1 && day <= 31)
                    {
                        string month_Str = SwitchMonths(month);
                        Console.Write("The date you choose : {0} {1} {2} ", day, month_Str, year);
                        findTheDay(day, month, year);
                        Console.WriteLine("Press any key to continue");
                        Console.ReadLine();
                        Console.Clear();
                    }
                    else
                    {
                        InvalidEntry("Invalid day entry! The first day entered must be between 1 and 31.");
                    }
                }
                else if (month == 2)
                {
                    if (year % 4 == 0)
                    {
                        if (day >= 1 && day <= 29)
                        {
                            string month_Str = SwitchMonths(month);
                            Console.Write("The date you choose : {0} {1} {2} ", day, month_Str, month);
                            findTheDay(day, month, year);
                            Console.WriteLine("Press any key to continue");
                            Console.ReadLine();
                            Console.Clear();
                        }
                        else
                        {
                            InvalidEntry("Invalid day entry! The first day entered must be between 1 and 29.");
                        }
                    }
                    else
                    {
                        if (day >= 1 && day <= 28)
                        {
                            string month_Str = SwitchMonths(month);
                            Console.Write("The date you choose : {0} {1} {2} ", day, month_Str, year);
                            findTheDay(day, month, year);
                            Console.WriteLine("Press any key to continue");
                            Console.ReadLine();
                            Console.Clear();
                        }
                        else
                        {
                            InvalidEntry("Invalid day entry! The first day entered must be between 1 and 28.");
                        }
                    }
                }
                else if (month == 4 || month == 6 || month == 9 || month == 11)
                {
                    if (day >= 1 && day <= 30)
                    {
                        string month_Str = SwitchMonths(month);
                        Console.Write("The date you choose : {0} {1} {2} ", day, month_Str, year);
                        findTheDay(day, month, year);
                        Console.WriteLine("Press any key to continue");
                        Console.ReadLine();
                        Console.Clear();
                    }
                    else
                    {
                        InvalidEntry("Invalid day entry! The first day entered must be between 1 and 30.");
                    }
                }
                else
                {
                    InvalidEntry("Invalid month entry! Try Again!");
                }
            }
            else
            {
                InvalidEntry("Invalid year entry! The first year entered must be greater than 2015.");
            }
        } //This method used for validation for dates
        static void findTheDay(int day, int month, int year)
        {
            if (month == 1)
            {
                month = 13;
                year--;
            }
            if (month == 2)
            {
                month = 14;
                year--;
            }
            int day_ = day;
            int month_ = month;
            int k = year % 100;
            int j = year / 100;
            int dayOfWeek = day_ + 13 * (month_ + 1) / 5 + k + k / 4 + j / 4 + 5 * j;
            dayOfWeek = dayOfWeek % 7;
            switch (dayOfWeek)
            {
                case 0:
                    Console.WriteLine("Saturday");
                    break;
                case 1:
                    Console.WriteLine("Sunday");
                    break;
                case 2:
                    Console.WriteLine("Monday");
                    break;
                case 3:
                    Console.WriteLine("Tuesday");
                    break;
                case 4:
                    Console.WriteLine("Wednesday");
                    break;
                case 5:
                    Console.WriteLine("Thursday");
                    break;
                case 6:
                    Console.WriteLine("Friday");
                    break;
            }
        } //This method used for find the day with Zeller's Congruence
    }
}
