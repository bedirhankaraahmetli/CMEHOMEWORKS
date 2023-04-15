using System;
using System.Threading;

namespace DEU_Game
{
    class MainClass
    {
        static int areaLength = 15;
        static string[] lineA1 = new string[areaLength];
        static string[] lineA2 = new string[areaLength];
        static string[] lineA3 = new string[areaLength];
        static int roundCounter = 1;
        static int player1score = 120;
        static int player2score = 120;

        public static void Main(string[] args)
        {
            string[] names = { "Derya", "Elife", "Fatih", "Ali", "Azra", "Sibel", "Cem", "Nazan", "Mehmet", "Nil", "Can", "Tarkan" };
            int[] scores = { 100, 100, 95, 90, 85, 80, 80, 70, 55, 50, 30, 30 };
            string[] namesNew = new string[names.Length + 1];
            int[] scoresNew = new int[scores.Length + 1];
            for (int i = 0; i < areaLength; i++)
            {
                lineA1[i] = " ";
                lineA2[i] = " ";
                lineA3[i] = " ";
            }
            
            bool check = true;
            
            while (check)
            {
                if ((roundCounter % 2) == 1)
                {
                    player1score -= 5;
                    Console.ForegroundColor = ConsoleColor.Magenta;
                    Console.WriteLine(roundCounter + ". round");
                    Console.ForegroundColor = ConsoleColor.White;
                    Console.WriteLine("");
                    Console.ForegroundColor = ConsoleColor.Cyan;
                    Console.Write("Player 1     ");
                    Console.ForegroundColor = ConsoleColor.White;
                    Console.WriteLine("(P1-" + player1score + "  " + "P2-" + player2score + ")");
                    WriteLetter();
                    Console.Write("A1 ");
                    for (int i = 0; i < areaLength; i++)
                    {
                        Console.Write(lineA1[i]);
                    }
                    Console.WriteLine();
                    Console.Write("A2 ");
                    for (int i = 0; i < areaLength; i++)
                    {
                        Console.Write(lineA2[i]);
                    }
                    Console.WriteLine();
                    Console.Write("A3 ");
                    for (int i = 0; i < areaLength; i++)
                    {
                        Console.Write(lineA3[i]);
                    }
                    Console.WriteLine();

                    for (int i = 0; i < areaLength-2; i++)
                    {

                        if (
                            (lineA1[i] == "D" && lineA1[i + 1] == "E" && lineA1[i + 2] == "U") || //lineA1 horizontal DEU
                            (lineA2[i] == "D" && lineA2[i + 1] == "E" && lineA2[i + 2] == "U") || //lineA2 horizontal DEU
                            (lineA3[i] == "D" && lineA3[i + 1] == "E" && lineA3[i + 2] == "U") || //lineA3 horizontal DEU
                            (lineA1[i] == "U" && lineA1[i + 1] == "E" && lineA1[i + 2] == "D") || //lineA1 horizontal UED
                            (lineA1[i] == "U" && lineA1[i + 1] == "E" && lineA1[i + 2] == "D") || //lineA2 horizontal UED
                            (lineA1[i] == "U" && lineA1[i + 1] == "E" && lineA1[i + 2] == "D") || //lineA3 horizontal UED
                            (lineA1[i] == "D" && lineA2[i] == "E" && lineA3[i] == "U") || //From top to bottom DEU
                            (lineA1[i] == "U" && lineA1[i] == "E" && lineA1[i] == "D") || //From top to bottom UED
                            (lineA1[i] == "D" && lineA2[i + 1] == "E" && lineA3[i + 2] == "U") || //From top to bottom diagonal DEU to right
                            (lineA1[i] == "U" && lineA2[i + 1] == "E" && lineA3[i + 2] == "D") || //From top to bottom diagonal UED to left
                            (lineA1[i + 2] == "D" && lineA2[i + 1] == "E" && lineA3[i] == "U") || //From bottom to top diagonal DEU to left
                            (lineA1[i + 2] == "U" && lineA2[i + 1] == "E" && lineA3[i] == "D")    //From bottom to top diagonal UED to right
                            )
                        {
                            int highScoreCounter = 0;
                            for (int j = 0; j < names.Length; j++)
                            {
                                if (scores[j] >= player1score)
                                {
                                    namesNew[j] = names[j];
                                    scoresNew[j] = scores[j];
                                    highScoreCounter += 1;
                                }

                            }
                            namesNew[highScoreCounter] = "Player1";
                            scoresNew[highScoreCounter] = player1score;
                            for (int j = highScoreCounter; j < names.Length; j++)
                            {
                                namesNew[j + 1] = names[j];
                                scoresNew[j + 1] = scores[j];
                            }
                            Console.WriteLine();
                            Console.WriteLine("Congratulations! Player1 has won.");
                            Console.WriteLine();
                            Console.WriteLine("\x1b[1m   HIGH SCORE TABLE   \x1b[0m");
                            Console.WriteLine("Name   Score");
                            for (int j = 0; j < namesNew.Length; j++)
                            {
                                Console.Write(namesNew[j]);
                                Console.WriteLine("\t" + scoresNew[j]);
                            }
                            check = false;
                        }
                        else
                        {

                        }
                        if (lineA1[lineA1.Length - 1] != " " && lineA2[lineA2.Length - 1] != " " && lineA3[lineA3.Length - 1] != " ")
                        {
                            Console.WriteLine("The game is draw");
                            Console.WriteLine();
                            Console.WriteLine("\x1b[1m   HIGH SCORE TABLE   \x1b[0m");
                            Console.WriteLine("Name   Score");
                            for (int j = 0; j < names.Length; j++)
                            {
                                Console.Write(names[j]);
                                Console.WriteLine("\t" + scores[j]);
                            }
                            check = false;
                            break;
                        }
                    }
                    roundCounter += 1;
                    Console.WriteLine(" ");
                    Thread.Sleep(500);
                    
                }
                else if (roundCounter % 2 == 0)
                {
                    player2score -= 5;
                    Console.ForegroundColor = ConsoleColor.Magenta;
                    Console.WriteLine(roundCounter + ". round");
                    Console.ForegroundColor = ConsoleColor.White;
                    Console.WriteLine("");
                    Console.ForegroundColor = ConsoleColor.Yellow;
                    Console.Write("Player 2     ");
                    Console.ForegroundColor = ConsoleColor.White;
                    Console.WriteLine("(P1-" + player1score + "  " + "P2-" + player2score + ")");
                    WriteLetter();
                    Console.Write("A1 ");
                    for (int i = 0; i < areaLength; i++)
                    {
                        Console.Write(lineA1[i]);
                    }
                    Console.WriteLine();
                    Console.Write("A2 ");
                    for (int i = 0; i < areaLength; i++)
                    {
                        Console.Write(lineA2[i]);
                    }
                    Console.WriteLine();
                    Console.Write("A3 ");
                    for (int i = 0; i < areaLength; i++)
                    {
                        Console.Write(lineA3[i]);
                    }
                    Console.WriteLine();
                    for (int i = 0; i < areaLength-2; i++)
                    {
                        if (
                            (lineA1[i] == "D" && lineA1[i + 1] == "E" && lineA1[i + 2] == "U") || //lineA1 horizontal DEU
                            (lineA2[i] == "D" && lineA2[i + 1] == "E" && lineA2[i + 2] == "U") || //lineA2 horizontal DEU
                            (lineA3[i] == "D" && lineA3[i + 1] == "E" && lineA3[i + 2] == "U") || //lineA3 horizontal DEU
                            (lineA1[i] == "U" && lineA1[i + 1] == "E" && lineA1[i + 2] == "D") || //lineA1 horizontal UED
                            (lineA1[i] == "U" && lineA1[i + 1] == "E" && lineA1[i + 2] == "D") || //lineA2 horizontal UED
                            (lineA1[i] == "U" && lineA1[i + 1] == "E" && lineA1[i + 2] == "D") || //lineA3 horizontal UED
                            (lineA1[i] == "D" && lineA2[i] == "E" && lineA3[i] == "U") || //From top to bottom DEU
                            (lineA1[i] == "U" && lineA1[i] == "E" && lineA1[i] == "D") || //From top to bottom UED
                            (lineA1[i] == "D" && lineA2[i + 1] == "E" && lineA3[i + 2] == "U") || //From top to bottom diagonal DEU to right
                            (lineA1[i] == "U" && lineA2[i + 1] == "E" && lineA3[i + 2] == "D") || //From top to bottom diagonal UED to left
                            (lineA1[i + 2] == "D" && lineA2[i + 1] == "E" && lineA3[i] == "U") || //From bottom to top diagonal DEU to left
                            (lineA1[i + 2] == "U" && lineA2[i + 1] == "E" && lineA3[i] == "D")    //From bottom to top diagonal UED to right
                            )
                        {
                            int highScoreCounter = 0;
                            for (int j = 0; j < names.Length; j++)
                            {
                                if (scores[j] >= player2score)
                                {
                                    namesNew[j] = names[j];
                                    scoresNew[j] = scores[j];
                                    highScoreCounter += 1;
                                }
                            }
                            namesNew[highScoreCounter] = "Player2";
                            scoresNew[highScoreCounter] = player2score;
                            for (int j = highScoreCounter; j < names.Length; j++)
                            {
                                namesNew[j + 1] = names[j];
                                scoresNew[j + 1] = scores[j];
                                highScoreCounter += 1;
                            }
                            Console.WriteLine();
                            Console.WriteLine("Congratulations! Player2 has won.");
                            Console.WriteLine();
                            Console.WriteLine("\x1b[1m   HIGH SCORE TABLE   \x1b[0m");
                            Console.WriteLine("Name    Score");
                            for (int j = 0; j < namesNew.Length; j++)
                            {
                                Console.Write(namesNew[j]);
                                Console.WriteLine("\t" + scoresNew[j]);
                            }
                            check = false;
                        }
                        else
                        {

                        }
                        if (lineA1[lineA1.Length-1] != " " && lineA2[lineA2.Length - 1] != " " && lineA3[lineA3.Length - 1] != " ")
                        {
                            Console.WriteLine("The game is draw");
                            Console.WriteLine();
                            Console.WriteLine("\x1b[1m   HIGH SCORE TABLE   \x1b[0m");
                            Console.WriteLine("Name   Score");
                            for (int j = 0; j < names.Length; j++)
                            {
                                Console.Write(names[j]);
                                Console.WriteLine("\t" + scores[j]);
                            }
                            check = false;
                            break;
                        }
                    }
                    roundCounter += 1;
                    Console.WriteLine(" ");
                    Thread.Sleep(500);
                }
            }
        }
        static void WriteLetter()
        {
            Random lineNumber = new Random();
            Random letter = new Random();
            Random letterPosition = new Random();
            switch (lineNumber.Next(1,4))
            {
                case 1:
                    switch (letter.Next(1,4))
                    {
                        case 1:
                            if (lineA1[lineA1.Length-1] == " ")
                            {
                                for (int i = 0; i < 15; i++)
                                {
                                    if (lineA1[i] == " ")
                                    {
                                        lineA1[i] = "D";
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                roundCounter -= 1;
                                if (roundCounter % 2 == 1)
                                {
                                    player2score += 5;
                                }
                                else
                                {
                                    player1score += 5;
                                }
                            }
                            break;
                        case 2:
                            if (lineA1[lineA1.Length-1] == " ")
                            {
                                for (int i = 0; i < 15; i++)
                                {
                                    if (lineA1[i] == " ")
                                    {
                                        lineA1[i] = "E";
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                roundCounter -= 1;
                                if (roundCounter % 2 == 1)
                                {
                                    player2score += 5;
                                }
                                else
                                {
                                    player1score += 5;
                                }
                            }
                            break;
                        case 3:
                            if (lineA1[lineA1.Length-1] == " ")
                            {
                                for (int i = 0; i < 15; i++)
                                {
                                    if (lineA1[i] == " ")
                                    {
                                        lineA1[i] = "U";
                                        break;
                                    }

                                }
                            }
                            else
                            {
                                roundCounter -= 1;
                                if (roundCounter % 2 == 1)
                                {
                                    player2score += 5;
                                }
                                else
                                {
                                    player1score += 5;
                                }
                            }
                            break;
                    }
                    break;
                case 2:
                    switch (letter.Next(1, 4))
                    {
                        case 1:
                            if (lineA2[lineA2.Length-1] == " ")
                            {
                                for (int i = 0; i < 15; i++)
                                {
                                    if (lineA2[i] == " ")
                                    {
                                        lineA2[i] = "D";
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                roundCounter -= 1;
                                if (roundCounter % 2 == 1)
                                {
                                    player2score += 5;
                                }
                                else
                                {
                                    player1score += 5;
                                }
                            }
                            break;
                        case 2:
                            if (lineA2[lineA2.Length-1] == " ")
                            {
                                for (int i = 0; i < 15; i++)
                                {
                                    if (lineA2[i] == " ")
                                    {
                                        lineA2[i] = "E";
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                roundCounter -= 1;
                                if (roundCounter % 2 == 1)
                                {
                                    player2score += 5;
                                }
                                else
                                {
                                    player1score += 5;
                                }
                            }
                            break;
                        case 3:
                            if (lineA2[lineA2.Length-1] == " ")
                            {
                                for (int i = 0; i < 15; i++)
                                {
                                    if (lineA2[i] == " ")
                                    {
                                        lineA2[i] = "U";
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                roundCounter -= 1;
                                if (roundCounter % 2 == 1)
                                {
                                    player2score += 5;
                                }
                                else
                                {
                                    player1score += 5;
                                }
                            }
                            break;
                    }
                    break;
                case 3:
                    switch (letter.Next(1, 4))
                    {
                        case 1:
                            if (lineA3[lineA3.Length-1] == " ")
                            {
                                for (int i = 0; i < 15; i++)
                                {
                                    if (lineA3[i] == " ")
                                    {
                                        lineA3[i] = "D";
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                roundCounter -= 1;
                                if (roundCounter % 2 == 1)
                                {
                                    player2score += 5;
                                }
                                else
                                {
                                    player1score += 5;
                                }
                            }
                            break;
                        case 2:
                            if (lineA3[lineA3.Length-1] == " ")
                            {
                                for (int i = 0; i < 15; i++)
                                {
                                    if (lineA3[i] == " ")
                                    {
                                        lineA3[i] = "E";
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                roundCounter -= 1;
                                if (roundCounter % 2 == 1)
                                {
                                    player2score += 5;
                                }
                                else
                                {
                                    player1score += 5;
                                }
                            }
                            break;
                        case 3:
                            if (lineA3[lineA3.Length-1] == " ")
                            {
                                for (int i = 0; i < 15; i++)
                                {
                                    if (lineA3[i] == " ")
                                    {
                                        lineA3[i] = "U";
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                roundCounter -= 1;
                                if (roundCounter % 2 == 1)
                                {
                                    player2score += 5;
                                }
                                else
                                {
                                    player1score += 5;
                                }
                            }
                            break;
                    }
                    break;
            }
        }
    }
}
