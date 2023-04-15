using System;
using System.Threading;

namespace Project02
{
    class MainClass
    {
        static void Main(string[] args)
        {
            // defining variables
            bool moving = true;
            bool keepPlaying = true;

            int generateNewTwoNumbers = 0;

            int xCursor = 1;
            int yCursor = 1;

            int width = 30;
            int height = 3;

            int[] line1 = new int[width];
            int[] line2 = new int[width];
            int[] line3 = new int[width];

            Random rand = new Random();

            int whichLine = 0;
            int whichColumn = 0;

            int score = 0;
            int nextNumber = 0;

            // randomly generates 30 numbers 
            for (int i = 0; i < width; i++)
            {
                whichLine = rand.Next(1, height + 1);
                whichColumn = rand.Next(0, width);

                if (whichLine == 1)
                {
                    if (line1[whichColumn] != 0)
                    {
                        i--;
                    }

                    line1[whichColumn] = rand.Next(1, 4);
                }
                else if (whichLine == 2)
                {
                    if (line2[whichColumn] != 0)
                    {
                        i--;
                    }
                    line2[whichColumn] = rand.Next(1, 4);
                }
                else if (whichLine == 3)
                {
                    if (line3[whichColumn] != 0)
                    {
                        i--;
                    }
                    line3[whichColumn] = rand.Next(1, 4);
                }
            }


            // game starts
            while (keepPlaying)
            {
                // checks again and again until there are no identical numbers next to each other      
                int isThereStillSameAdjacents = 1;
                while (isThereStillSameAdjacents != 0)
                {
                    generateNewTwoNumbers = 0;
                    isThereStillSameAdjacents = 0;

                    // controls adjacent digits    
                    for (int i = 0; i < width - 1; i++)
                    {
                        nextNumber = i + 1;

                        if (line1[i] == line1[nextNumber] && line1[i] != 0)
                        {
                            line1[i] = 0;
                            line1[nextNumber] = 0;
                            score += 10;
                            generateNewTwoNumbers++;
                        }
                        else if (line2[i] == line2[nextNumber] && line2[i] != 0)
                        {
                            line2[i] = 0;
                            line2[nextNumber] = 0;
                            score += 10;
                            generateNewTwoNumbers++;
                        }
                        else if (line3[i] == line3[nextNumber] && line3[i] != 0)
                        {
                            line3[i] = 0;
                            line3[nextNumber] = 0;
                            score += 10;
                            generateNewTwoNumbers++;
                        }
                    }

                    // generates new two numbers
                    if (generateNewTwoNumbers > 0)
                    {
                        for (int i = 0; i < generateNewTwoNumbers * 2; i++)
                        {
                            whichLine = rand.Next(1, height + 1);
                            whichColumn = rand.Next(0, width);

                            if (whichLine == 1)
                            {
                                if (line1[whichColumn] != 0)
                                {
                                    i--;
                                }
                                line1[whichColumn] = rand.Next(1, 4);
                            }
                            else if (whichLine == 2)
                            {
                                if (line2[whichColumn] != 0)
                                {
                                    i--;
                                }
                                line2[whichColumn] = rand.Next(1, 4);
                            }
                            else if (whichLine == 3)
                            {
                                if (line3[whichColumn] != 0)
                                {
                                    i--;
                                }
                                line3[whichColumn] = rand.Next(1, 4);
                            }
                        }
                    }
                    // controls adjacent digits again
                    for (int i = 0; i < width - 1; i++)
                    {
                        nextNumber = i + 1;

                        if (line1[i] == line1[nextNumber] && line1[i] != 0)
                        {
                            isThereStillSameAdjacents++;
                        }
                        else if (line2[i] == line2[nextNumber] && line2[i] != 0)
                        {
                            isThereStillSameAdjacents++;
                        }
                        else if (line3[i] == line3[nextNumber] && line3[i] != 0)
                        {
                            isThereStillSameAdjacents++;
                        }
                    }
                }

                // prints out game area
                Console.Clear();

                Console.WriteLine("+------------------------------+");

                Console.Write("|");
                foreach (int i in line1)
                {
                    if (i == 0)
                    {
                        Console.Write(" ");
                    }
                    else
                    {
                        Console.Write(i);
                    }
                }
                Console.WriteLine("|");

                Console.Write("|");
                foreach (int i in line2)
                {
                    if (i == 0)
                    {
                        Console.Write(" ");
                    }
                    else
                    {
                        Console.Write(i);
                    }
                }
                Console.WriteLine("|");

                Console.Write("|");
                foreach (int i in line3)
                {
                    if (i == 0)
                    {
                        Console.Write(" ");
                    }
                    else
                    {
                        Console.Write(i);
                    }
                }
                Console.WriteLine("|");

                Console.WriteLine("+------------------------------+");

                Console.WriteLine("\n \n Press ESC to exit");

                // prints out score
                Console.SetCursorPosition(40, 0);
                Console.Write("Score: " + score);
                Console.SetCursorPosition(xCursor, yCursor);

                // allows to move in game area
                while (moving)
                {
                    if (Console.KeyAvailable)
                    {
                        var command = Console.ReadKey(true).Key;

                        switch (command)
                        {
                            case ConsoleKey.DownArrow:
                                if (yCursor < height)
                                {
                                    yCursor++;
                                }
                                break;

                            case ConsoleKey.UpArrow:
                                if (yCursor > 1)
                                {
                                    yCursor--;
                                }
                                break;

                            case ConsoleKey.RightArrow:
                                if (xCursor < width)
                                {
                                    xCursor++;
                                }
                                break;

                            case ConsoleKey.LeftArrow:
                                if (xCursor > 1)
                                {
                                    xCursor--;
                                }
                                break;

                            case ConsoleKey.W:
                                if (yCursor == 2 && line2[xCursor - 1] != 0 && line1[xCursor - 1] == 0)
                                {
                                    line1[xCursor - 1] = line2[xCursor - 1];
                                    line2[xCursor - 1] = 0;
                                    yCursor--;

                                    moving = false;
                                }
                                else if (yCursor == 3 && line3[xCursor - 1] != 0 && line2[xCursor - 1] == 0)
                                {
                                    line2[xCursor - 1] = line3[xCursor - 1];
                                    line3[xCursor - 1] = 0;
                                    yCursor--;

                                    moving = false;
                                }
                                break;

                            case ConsoleKey.S:
                                if (yCursor == 1 && line1[xCursor - 1] != 0 && line2[xCursor - 1] == 0)
                                {
                                    line2[xCursor - 1] = line1[xCursor - 1];
                                    line1[xCursor - 1] = 0;
                                    yCursor++;

                                    moving = false;
                                }
                                else if (yCursor == 2 && line2[xCursor - 1] != 0 && line3[xCursor - 1] == 0)
                                {
                                    line3[xCursor - 1] = line2[xCursor - 1];
                                    line2[xCursor - 1] = 0;
                                    yCursor++;

                                    moving = false;
                                }
                                break;

                            case ConsoleKey.D:
                                if (xCursor < 30)
                                {
                                    if (yCursor == 1 && line1[xCursor - 1] != 0 && line1[xCursor] == 0)
                                    {
                                        line1[xCursor] = line1[xCursor - 1];
                                        line1[xCursor - 1] = 0;
                                        xCursor++;

                                        moving = false;
                                    }
                                    else if (yCursor == 2 && line2[xCursor - 1] != 0 && line2[xCursor] == 0)
                                    {
                                        line2[xCursor] = line2[xCursor - 1];
                                        line2[xCursor - 1] = 0;
                                        xCursor++;

                                        moving = false;
                                    }
                                    else if (yCursor == 3 && line3[xCursor - 1] != 0 && line3[xCursor] == 0)
                                    {
                                        line3[xCursor] = line3[xCursor - 1];
                                        line3[xCursor - 1] = 0;
                                        xCursor++;

                                        moving = false;
                                    }
                                }
                                break;

                            case ConsoleKey.A:
                                if (xCursor > 1)
                                    if (yCursor == 1 && line1[xCursor - 1] != 0 && line1[xCursor - 2] == 0)
                                    {
                                        line1[xCursor - 2] = line1[xCursor - 1];
                                        line1[xCursor - 1] = 0;
                                        xCursor--;

                                        moving = false;
                                    }
                                    else if (yCursor == 2 && line2[xCursor - 1] != 0 && line2[xCursor - 2] == 0)
                                    {
                                        line2[xCursor - 2] = line2[xCursor - 1];
                                        line2[xCursor - 1] = 0;
                                        xCursor--;

                                        moving = false;
                                    }
                                    else if (yCursor == 3 && line3[xCursor - 1] != 0 && line3[xCursor - 2] == 0)
                                    {
                                        line3[xCursor - 2] = line3[xCursor - 1];
                                        line3[xCursor - 1] = 0;
                                        xCursor--;

                                        moving = false;
                                    }
                                break;

                            case ConsoleKey.Escape:
                                xCursor = 0;
                                yCursor = 5;
                                moving = false;
                                keepPlaying = false;
                                break;

                        }
                        Console.SetCursorPosition(xCursor, yCursor);
                    }
                }
                moving = true;
            }
        }
    }
}
