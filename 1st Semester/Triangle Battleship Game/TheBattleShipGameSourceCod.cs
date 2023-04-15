using System;
using System.Text;

namespace CME1251
{
    class MainClass
    {
        public static void Main(string[] args)
        {
            //Creating necessary variables
            string xA = null;
            string yA = null;
            string xB = null;
            string yB = null;
            string xC = null;
            string yC = null;
            string name = null;
            double score = 0;
            int number = 0;
            double areaOfTheShip = 0;

            //Printing the Menu
            do
            {
                Console.WriteLine("\x1b[1m----------------MENU----------------\x1b[0m\nPLEASE SELECT FROM THE OPTIONS BELOW:\n1 - Enter ship location\n2 - Ship info\n3 - Shoot at the ship\n4 – Show high score table\n5 – Exit  ");
                string choosing0 = Console.ReadLine();
                while (choosing0 != "1" && choosing0 != "2" && choosing0 != "3" && choosing0 != "4" && choosing0 != "5")
                {
                    Console.Clear();
                    Console.WriteLine("\x1b[1m----------------MENU----------------\x1b[0m\nPLEASE SELECT FROM THE OPTIONS BELOW:\n1 - Enter ship location\n2 - Ship info\n3 - Shoot at the ship\n4 – Show high score table\n5 – Exit  ");
                    Console.ForegroundColor = ConsoleColor.Red;
                    Console.WriteLine("Please select a number between 1-5");
                    Console.ForegroundColor = ConsoleColor.White;
                    choosing0 = Console.ReadLine();
                    Console.Clear();

                }

                int choosing = Convert.ToInt32(choosing0);

                Console.Clear();

                //If the user choose the 1
                if (choosing == 1)
                {
                    bool check = true;
                    while (check)
                    {
                        Console.WriteLine("Caution before determining ship coordinates!");
                        Console.WriteLine("The x coordinate of your ship must be between 0 and 30.");
                        Console.WriteLine("The y coordinate of your ship must be between 0 and 12");
                        Console.WriteLine("Your ship should be triangular in shape");
                        Console.WriteLine("You can not select the same point twice.");
                        Console.WriteLine("");
                        Console.WriteLine("");
                        Console.WriteLine("Please enter the x coordinate of point A :");
                        xA = Console.ReadLine();
                        if (!(int.TryParse(xA, out number))) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate");
                            Console.ReadLine();
                            Console.Clear();
                            xA = null;
                            break;

                        }

                        if (Convert.ToInt32(xA) < 1 || Convert.ToInt32(xA) > 30) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate. X coordinates must be between 1 and 30");
                            check = false;
                            Console.ReadLine();
                            Console.Clear();
                            xA = null;
                            break;

                        }

                        Console.Clear();
                        Console.WriteLine("Caution before determining ship coordinates!");
                        Console.WriteLine("The x coordinate of your ship must be between 0 and 30.");
                        Console.WriteLine("The y coordinate of your ship must be between 0 and 12");
                        Console.WriteLine("Your ship should be triangular in shape");
                        Console.WriteLine("You can not select the same point twice.");
                        Console.WriteLine("");
                        Console.WriteLine("");
                        Console.WriteLine("Please enter the y coordinate of point A :");
                        yA = Console.ReadLine();
                        if (!(int.TryParse(yA, out number))) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate");
                            Console.ReadLine();
                            Console.Clear();
                            yA = null;
                            break;

                        }
                        if (Convert.ToInt32(yA) < 1 || Convert.ToInt32(yA) > 12) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate. Y coordinates must be between 1 and 12");
                            check = false;
                            Console.ReadLine();
                            Console.Clear();
                            yA = null;
                            break;

                        }
                        Console.Clear();
                        Console.WriteLine("Caution before determining ship coordinates!");
                        Console.WriteLine("The x coordinate of your ship must be between 0 and 30.");
                        Console.WriteLine("The y coordinate of your ship must be between 0 and 12");
                        Console.WriteLine("Your ship should be triangular in shape");
                        Console.WriteLine("You can not select the same point twice.");
                        Console.WriteLine("");
                        Console.WriteLine("");
                        Console.WriteLine("Please enter the x coordinate of point B :");
                        xB = Console.ReadLine();
                        if (!(int.TryParse(xB, out number))) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate");
                            Console.ReadLine();
                            Console.Clear();
                            xB = null;
                            break;

                        }
                        if (Convert.ToInt32(xB) < 1 || Convert.ToInt32(xB) > 30) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate. X coordinates must be between 1 and 30");
                            check = false;
                            Console.ReadLine();
                            Console.Clear();
                            xB = null;
                            break;

                        }
                        Console.Clear();
                        Console.WriteLine("Caution before determining ship coordinates!");
                        Console.WriteLine("The x coordinate of your ship must be between 0 and 30.");
                        Console.WriteLine("The y coordinate of your ship must be between 0 and 12");
                        Console.WriteLine("Your ship should be triangular in shape");
                        Console.WriteLine("You can not select the same point twice.");
                        Console.WriteLine("");
                        Console.WriteLine("");
                        Console.WriteLine("Please enter the y coordinate of point B :");
                        yB = Console.ReadLine();
                        if (!(int.TryParse(yB, out number))) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate");
                            Console.ReadLine();
                            Console.Clear();
                            yB = null;
                            break;

                        }
                        if (Convert.ToInt32(yB) < 1 || Convert.ToInt32(yB) > 12) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate. Y coordinates must be between 1 and 12");
                            check = false;
                            Console.ReadLine();
                            Console.Clear();
                            yB = null;
                            break;

                        }
                        Console.Clear();
                        Console.WriteLine("Caution before determining ship coordinates!");
                        Console.WriteLine("The x coordinate of your ship must be between 0 and 30.");
                        Console.WriteLine("The y coordinate of your ship must be between 0 and 12");
                        Console.WriteLine("Your ship should be triangular in shape");
                        Console.WriteLine("You can not select the same point twice.");
                        Console.WriteLine("");
                        Console.WriteLine("");
                        Console.WriteLine("Please enter the x coordinate of point C :");
                        xC = Console.ReadLine();
                        if (!(int.TryParse(xC, out number))) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate");
                            Console.ReadLine();
                            Console.Clear();
                            xC = null;
                            break;

                        }
                        if (Convert.ToInt32(xC) < 1 || Convert.ToInt32(xC) > 30) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate. X coordinates must be between 1 and 30");
                            check = false;
                            Console.ReadLine();
                            Console.Clear();
                            xC = null;
                            break;

                        }
                        Console.Clear();
                        Console.WriteLine("Caution before determining ship coordinates!");
                        Console.WriteLine("The x coordinate of your ship must be between 0 and 30.");
                        Console.WriteLine("The y coordinate of your ship must be between 0 and 12");
                        Console.WriteLine("Your ship should be triangular in shape");
                        Console.WriteLine("You can not select the same point twice.");
                        Console.WriteLine("");
                        Console.WriteLine("");
                        Console.WriteLine("Please enter the y coordinate of point C :");
                        yC = Console.ReadLine();
                        if (!(int.TryParse(yC, out number))) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate");
                            Console.ReadLine();
                            Console.Clear();
                            yC = null;
                            break;

                        }
                        if (Convert.ToInt32(yC) < 1 || Convert.ToInt32(yC) > 13) //Invalid input check
                        {
                            Console.WriteLine("You entered an invalid coordinate. Y coordinates must be between 1 and 12");
                            check = false;
                            Console.ReadLine();
                            Console.Clear();
                            yC = null;
                            break;

                        }
                        areaOfTheShip = Math.Abs((((Convert.ToDouble(xA) * Convert.ToDouble(yB)) + (Convert.ToDouble(xB) * Convert.ToDouble(yC)) + (Convert.ToDouble(xC) * Convert.ToDouble(yA))) - (Convert.ToDouble(xB) * Convert.ToDouble(yA) + (Convert.ToDouble(xC) * Convert.ToDouble(yB)) + (Convert.ToDouble(xA) * Convert.ToDouble(yC)))) / 2);
                        Console.Clear();

                        if (areaOfTheShip != 0)
                        {
                            // Creating Cartesian coordinate system and placing the points
                            Console.WriteLine("12|\n11|\n10|\n 9|\n 8|\n 7|\n 6|\n 5|\n 4|\n 3|\n 2|\n 1|\n  +------------------------------\n   123456789012345678901234567890 \n \n \n");
                            Console.SetCursorPosition(Convert.ToInt32(xA) + 2, 12 - Convert.ToInt32(yA));
                            Console.WriteLine("A");
                            Console.SetCursorPosition(Convert.ToInt32(xB) + 2, 12 - Convert.ToInt32(yB));
                            Console.WriteLine("B");
                            Console.SetCursorPosition(Convert.ToInt32(xC) + 2, 12 - Convert.ToInt32(yC));
                            Console.WriteLine("C");

                            Console.SetCursorPosition(0, 15);
                            Console.WriteLine("Your ship's coordinates are as follows :");
                            Console.WriteLine("A({0},{1})", xA, yA);
                            Console.WriteLine("B({0},{1})", xB, yB);
                            Console.WriteLine("C({0},{1})", xC, yC);

                            Console.WriteLine();
                            Console.WriteLine();
                            Console.ReadLine();
                            Console.Clear();
                            check = false;
                        }
                        else
                        {
                            Console.WriteLine("Such a triangle cannot be drawn");
                            Console.ReadLine();
                            Console.Clear();
                            break;
                        }

                    }

                }
                /*
                      Basic Properties:
                */

                //Size of the ship
                double lineAB = Math.Sqrt(Math.Pow(Convert.ToInt16(xA) - Convert.ToInt16(xB), 2) + Math.Pow(Convert.ToInt16(yA) - Convert.ToInt16(yB), 2));
                double lineAC = Math.Sqrt(Math.Pow(Convert.ToInt16(xA) - Convert.ToInt16(xC), 2) + Math.Pow(Convert.ToInt16(yA) - Convert.ToInt16(yC), 2));
                double lineBC = Math.Sqrt(Math.Pow(Convert.ToInt16(xB) - Convert.ToInt16(xC), 2) + Math.Pow(Convert.ToInt16(yB) - Convert.ToInt16(yC), 2));
                //Perimeter of the ship
                double perimeter = lineAB + lineAC + lineBC;
                //Area of the ship
                areaOfTheShip = Math.Abs((((Convert.ToDouble(xA) * Convert.ToDouble(yB)) + (Convert.ToDouble(xB) * Convert.ToDouble(yC)) + (Convert.ToDouble(xC) * Convert.ToDouble(yA))) - (Convert.ToDouble(xB) * Convert.ToDouble(yA) + (Convert.ToDouble(xC) * Convert.ToDouble(yB)) + (Convert.ToDouble(xA) * Convert.ToDouble(yC)))) / 2);
                //The angles of the ship
                double angleA = Math.Round(180 * Math.Acos((Math.Pow(lineAB, 2) + Math.Pow(lineAC, 2) - Math.Pow(lineBC, 2)) / (2 * lineAB * lineAC)) / Math.PI, 2);
                double angleB = Math.Round(180 * Math.Acos((Math.Pow(lineAB, 2) + Math.Pow(lineBC, 2) - Math.Pow(lineAC, 2)) / (2 * lineAB * lineBC)) / Math.PI, 2);
                double angleC = Math.Round(180 * Math.Acos((Math.Pow(lineAC, 2) + Math.Pow(lineBC, 2) - Math.Pow(lineAB, 2)) / (2 * lineAC * lineBC)) / Math.PI, 2);
                //Median points of the ship
                double medianABx = ((Convert.ToDouble(xA) + Convert.ToDouble(xB)) / 2);
                double medianABy = ((Convert.ToDouble(yA) + Convert.ToDouble(yB)) / 2);
                double medianACx = ((Convert.ToDouble(xA) + Convert.ToDouble(xC)) / 2);
                double medianACy = ((Convert.ToDouble(yA) + Convert.ToDouble(yC)) / 2);
                double medianBCx = ((Convert.ToDouble(xB) + Convert.ToDouble(xC)) / 2);
                double medianBCy = ((Convert.ToDouble(yB) + Convert.ToDouble(yC)) / 2);
                //Centroid point of the ship
                double centroidX = Math.Round(((Convert.ToDouble(xA) + Convert.ToDouble(xB) + Convert.ToDouble(xC)) / 3), 2);
                double centroidY = Math.Round(((Convert.ToDouble(yA) + Convert.ToDouble(yB) + Convert.ToDouble(yC)) / 3), 2);
                //Bisector length of the point A
                double bisectorA = Math.Round(Math.Sqrt(((lineAB * lineAC) / Math.Pow((lineAB + lineAC), 2)) * (Math.Pow((lineAB + lineAC), 2) - Math.Pow(lineBC, 2))), 2);
                //Circles
                double radius1 = (2 * areaOfTheShip) / (lineAB + lineAC + lineBC);
                double innercirclesarea = Math.PI * Math.Pow(radius1, 2);
                double radius2 = (lineBC * lineAC * lineAB) / (4 * areaOfTheShip);
                double outercirclesarea = Math.PI * Math.Pow(radius2, 2);
                int randomX = 0;
                int randomY = 0;

                //If the use choose 2 
                if (choosing == 2)
                {
                    if (areaOfTheShip != 0
                && int.TryParse(xA, out number) && int.TryParse(xB, out number) && int.TryParse(xC, out number)
                && int.TryParse(yA, out number) && int.TryParse(yB, out number) && int.TryParse(yC, out number)
                && Convert.ToDouble(xA) > 0 && Convert.ToDouble(xB) > 0 && Convert.ToDouble(xC) > 0
                && Convert.ToDouble(yA) > 0 && Convert.ToDouble(yB) > 0 && Convert.ToDouble(yC) > 0
                && Convert.ToDouble(xA) <= 30 && Convert.ToDouble(xB) <= 30 && Convert.ToDouble(xC) <= 30
                && Convert.ToDouble(yA) <= 12 && Convert.ToDouble(yB) <= 12 && Convert.ToDouble(yC) <= 12)
                    {
                        Console.WriteLine("\x1b[1m-----SHIP INFO-----\x1b[0m");

                        Console.WriteLine("The size of the ship (length of the edges) => |AB| : {0} , |AC| : {1} , |BC| : {2}", Math.Round(lineAB, 2), Math.Round(lineAC, 2), Math.Round(lineBC, 2));

                        Console.WriteLine("The perimeter of the ship : {0}", Math.Round(perimeter, 2));


                        Console.WriteLine("The area of the ship : {0}", areaOfTheShip);


                        Console.WriteLine("The angles of the ship => A({0}) B({1}) C({2})", angleA, angleB, angleC);


                        Console.WriteLine("The median points of the ship => |AB| : ({0} , {1}) |AC| : ({2} , {3}) |BC| : ({4} , {5})", medianABx, medianABy, medianACx, medianACy, medianBCx, medianBCy);


                        Console.WriteLine("Centroid of the ship : ({0} , {1})", centroidX, centroidY);


                        Console.WriteLine("The bisector length of point A : {0}", bisectorA);

                        Console.WriteLine("The area of the inscribed circle of the triangle : {0}", Math.Round((innercirclesarea), 2));

                        Console.WriteLine("The area of the circumscribed circle of the triangle : {0}", Math.Round((outercirclesarea), 2));
                        //The type of the triangle (Equilateral, Isosceles, Scalene)
                        if (lineAB == lineAC && lineAC == lineBC)
                        {
                            Console.WriteLine("The triangle is equilateral");
                        }
                        else if (lineAB != lineAC && lineAC != lineBC && lineAB != lineBC)
                        {
                            Console.WriteLine("The triangle is scalene");
                        }
                        else
                        {
                            Console.WriteLine("The triangle is isosceles");
                        }

                        //The type of the triangle (Acute-angled, Right-angled, Obtuse-angled)
                        if ((angleA == 90 || angleB == 90) || angleC == 90)
                        {
                            Console.WriteLine("The triangle is right-angled");
                        }
                        else if (angleA > 90 || angleB > 90 || angleC > 90)
                        {
                            Console.WriteLine("The triangle is obtuse-angled");
                        }
                        else
                        {
                            Console.WriteLine("The triangle is acute-angled");
                        }
                        Console.WriteLine();
                        Console.ReadLine();
                        Console.Clear();
                    }
                    else
                    {
                        Console.WriteLine("You did not define a triangle. Please first define triangle.");
                        Console.ReadLine();
                        Console.Clear();
                    }

                }
                //If the user choose 3
                if (choosing == 3)
                {
                    if (areaOfTheShip != 0
                && int.TryParse(xA, out number) && int.TryParse(xB, out number) && int.TryParse(xC, out number)
                && int.TryParse(yA, out number) && int.TryParse(yB, out number) && int.TryParse(yC, out number)
                && Convert.ToDouble(xA) > 0 && Convert.ToDouble(xB) > 0 && Convert.ToDouble(xC) > 0
                && Convert.ToDouble(yA) > 0 && Convert.ToDouble(yB) > 0 && Convert.ToDouble(yC) > 0
                && Convert.ToDouble(xA) <= 30 && Convert.ToDouble(xB) <= 30 && Convert.ToDouble(xC) <= 30
                && Convert.ToDouble(yA) <= 12 && Convert.ToDouble(yB) <= 12 && Convert.ToDouble(yC) <= 12)
                    {
                        Random shootX = new Random(); //Making random shoot
                        randomX = shootX.Next(1, 31);
                        Random shootY = new Random();
                        randomY = shootY.Next(1, 13);
                        Console.WriteLine("Shoot : ({0},{1})", randomX, randomY);
                        Console.WriteLine("12|\n11|\n10|\n 9|\n 8|\n 7|\n 6|\n 5|\n 4|\n 3|\n 2|\n 1|\n  +------------------------------\n   123456789012345678901234567890 \n \n \n");
                        Console.SetCursorPosition(Convert.ToInt32(xA) + 2, 13 - Convert.ToInt32(yA));
                        Console.WriteLine("A");
                        Console.SetCursorPosition(Convert.ToInt32(xB) + 2, 13 - Convert.ToInt32(yB));
                        Console.WriteLine("B");
                        Console.SetCursorPosition(Convert.ToInt32(xC) + 2, 13 - Convert.ToInt32(yC));
                        Console.WriteLine("C");
                        Console.SetCursorPosition(Convert.ToInt32(randomX) + 2, 13 - Convert.ToInt32(randomY));
                        Console.WriteLine("X");
                        Console.SetCursorPosition(0, 15);
                        double Area1 = Math.Abs((Convert.ToDouble(xB) * Convert.ToDouble(randomY)) + (Convert.ToDouble(randomX) * Convert.ToDouble(yC)) + (Convert.ToDouble(xC) * Convert.ToDouble(yB)) - ((Convert.ToDouble(yB) * Convert.ToDouble(randomX)) + (Convert.ToDouble(randomY) * Convert.ToDouble(xC)) + (Convert.ToDouble(yC) * Convert.ToDouble(xB)))) / 2;
                        double Area2 = Math.Abs((Convert.ToDouble(xA) * Convert.ToDouble(randomY)) + (Convert.ToDouble(randomX) * Convert.ToDouble(yC)) + (Convert.ToDouble(xC) * Convert.ToDouble(yA)) - ((Convert.ToDouble(yA) * Convert.ToDouble(randomX)) + (Convert.ToDouble(randomY) * Convert.ToDouble(xC)) + (Convert.ToDouble(yC) * Convert.ToDouble(xA)))) / 2;
                        double Area3 = Math.Abs((Convert.ToDouble(xA) * Convert.ToDouble(yB)) + (Convert.ToDouble(xB) * Convert.ToDouble(randomY)) + (Convert.ToDouble(randomX) * Convert.ToDouble(yA)) - ((Convert.ToDouble(yA) * Convert.ToDouble(xB)) + (Convert.ToDouble(yB) * Convert.ToDouble(randomX)) + (Convert.ToDouble(randomY) * Convert.ToDouble(xA)))) / 2;
                        double ImageArea = Area1 + Area2 + Area3;
                        Console.WriteLine("");
                        Console.WriteLine("");
                        if (areaOfTheShip == ImageArea)
                        {
                            Console.WriteLine("Your ship sank! Total score is 0!");
                        }
                        else
                        {
                            score = areaOfTheShip;
                            Console.WriteLine("Your ship is survived. Your total score is : " + areaOfTheShip);
                            Console.Write("Please enter your name: ");
                            name = Console.ReadLine();
                            while (name.Length > 26)
                            {
                                Console.ForegroundColor = ConsoleColor.Red;
                                Console.WriteLine("Please enter a shorter name");
                                Console.ForegroundColor = ConsoleColor.White;
                                name = Console.ReadLine();
                            }


                        }
                        Console.ReadLine();
                        Console.Clear();
                        choosing = 4;
                    }
                    else
                    {
                        Console.WriteLine("You did not define a triangle. Please first define triangle.");
                        Console.ReadLine();
                        Console.Clear();
                    }



                }
                //If the user choose 4
                if (choosing == 4)
                {

                    Console.WriteLine("\x1b[1m--------HIGH SCORE TABLE--------\x1b[0m");
                    Console.WriteLine("********************************");
                    Console.WriteLine("\x1b[1mName                       Score\x1b[0m");
                    if (score <= 10)
                    {
                        Console.WriteLine("Nazan Kaya	           60\nAli Kurt                   30\nSibel Arslan               10");
                    }
                    if (score > 10 && score <= 30)
                    {
                        Console.WriteLine("Nazan Kaya	           60\nAli Kurt                   30\n" + name);
                        Console.SetCursorPosition(27, 5);
                        Console.Write(score);
                    }
                    if (score > 30 && score <= 60)
                    {
                        Console.WriteLine("Nazan Kaya                 60\n" + name);
                        Console.SetCursorPosition(27, 4);
                        Console.Write(score);
                        Console.WriteLine("\nAli Kurt                   30");
                    }
                    if (score > 60)
                    {
                        Console.WriteLine(name);
                        Console.SetCursorPosition(27, 3);
                        Console.Write(score);
                        Console.WriteLine("\nNazan Kaya	           60\nAli Kurt                   30");
                    }
                    Console.ReadLine();
                    Console.Clear();
                }
                //If the user choose 5
                if (choosing == 5)
                {
                    Environment.Exit(0);
                }
            } while (true);
        }
    }
}