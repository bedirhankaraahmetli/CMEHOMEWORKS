using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Cengers
{
    class Program
    {

        static char[,] board = new char[8, 8];
        static int x = 8, y = 8, selx = 0, sely = 0, jumpX = 0, jumpY = 0, yIndex, maxMoveIndex, preMoveX = 0, preMoveY = 0, counter = 0;
        static int[,] PositionOfOs = new int[,] { {0, 1, 2, 0, 1, 2, 0, 1, 2}, //X axis of the pieces
                                                  {0, 0, 0, 1, 1, 1, 2, 2, 2}, //Y axis of the pieces
                                                  {0, 0, 0, 0, 0, 0, 0, 0, 0}};//Number of moves they can do
        static int[,] TempMovesOfOs = new int[2, 9];
        static bool DJumpFlag = true, RJumpFlag = true, DStepFlag = true, RStepFlag = true;
        static Random rnd = new Random();
        static ConsoleKeyInfo cki;

        static void Main(string[] args)
        {
            int round = 1;

            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    board[i, j] = '.';
                }
            }
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    board[i, j] = 'O';
                }
            }
            for (int i = 7; i > 4; i--)
            {
                for (int j = 7; j > 4; j--)
                {
                    board[i, j] = 'X';
                }
            }
            Console.Write("  12345678 \n" +
                          " +--------+\n");
            for (int i = 0; i < 8; i++)
            {
                Console.Write(i + 1 + "|");
                for (int j = 0; j < 8; j++)
                {

                    Console.Write(board[i, j]);
                }
                Console.Write("|\n");
            }
            Console.Write(" +--------+\n");

            Console.SetCursorPosition(15, 2);
            Console.Write("Round: {0}", round);

            while (true)
            {
                Console.SetCursorPosition(15, 4);
                Console.Write("Turn: X");
                controls();
                Console.SetCursorPosition(15, 4);
                Console.Write("Turn: O");
                AIControl(PositionOfOs[0, 8], PositionOfOs[1, 8], true);
                round++;
                Console.SetCursorPosition(15, 2);
                Console.Write("Round: {0}", round);
                WinChecker();
            }

        }
        static void controls()
        {
            bool flag = false, flagJump = false;
            while (true)
            {
                Console.SetCursorPosition(x, y);
                if (Console.KeyAvailable)
                {

                    cki = Console.ReadKey(true);
                    switch (cki.Key)
                    {
                        case ConsoleKey.LeftArrow:
                            if (x > 2)
                                x--;
                            break;
                        case ConsoleKey.RightArrow:
                            if (x < 9)
                                x++;
                            break;
                        case ConsoleKey.UpArrow:
                            if (y > 2)
                                y--;
                            break;
                        case ConsoleKey.DownArrow:
                            if (y < 9)
                                y++;
                            break;
                        case ConsoleKey.Escape:
                            Environment.Exit(1);
                            break;
                        case ConsoleKey.Z:
                            if (board[x - 2, y - 2] == 'X')
                            {
                                selx = x - 2;
                                sely = y - 2;

                            }
                            break;
                        case ConsoleKey.X:
                            if (board[x - 2, y - 2] == '.' && board[selx, sely] == 'X' && !flagJump)
                            {
                                if ((Math.Abs(selx - x + 2) == 1 && sely + 2 == y) || (Math.Abs(sely - y + 2) == 1 && selx + 2 == x))
                                {
                                    board[x - 2, y - 2] = 'X';
                                    board[selx, sely] = '.';
                                    Console.SetCursorPosition(selx + 2, sely + 2);
                                    Console.Write(".");
                                    Console.SetCursorPosition(x, y);
                                    Console.Write("X");
                                    selx = 0;
                                    sely = 0;
                                    flag = true;
                                }
                            }
                            //Up Jump
                            if (sely + 2 > 3 && !flagJump && board[x - 2, y - 2] == '.' && (board[selx, sely - 1] == 'O' || board[selx, sely - 1] == 'X') ||
                                sely + 2 > 3 && flagJump && selx == jumpX && sely == jumpY && board[x - 2, y - 2] == '.' && (board[selx, sely - 1] == 'O' || board[selx, sely - 1] == 'X'))
                            {
                                if (selx == x - 2 && sely - 2 == y - 2)
                                {
                                    board[x - 2, y - 2] = 'X';
                                    board[selx, sely] = '.';
                                    Console.SetCursorPosition(selx + 2, sely + 2);
                                    Console.Write(".");
                                    Console.SetCursorPosition(x, y);
                                    Console.Write("X");
                                    flagJump = true;
                                    jumpX = x - 2;
                                    jumpY = y - 2;
                                }
                            }
                            //Left Jump
                            if (selx + 2 > 3 && !flagJump && board[x - 2, y - 2] == '.' && (board[selx - 1, sely] == 'O' || board[selx - 1, sely] == 'X') ||
                                selx + 2 > 3 && flagJump && selx == jumpX && sely == jumpY && board[x - 2, y - 2] == '.' && (board[selx - 1, sely] == 'O' || board[selx - 1, sely] == 'X'))
                            {
                                if (sely == y - 2 && selx - 2 == x - 2)
                                {
                                    board[x - 2, y - 2] = 'X';
                                    board[selx, sely] = '.';
                                    Console.SetCursorPosition(selx + 2, sely + 2);
                                    Console.Write(".");
                                    Console.SetCursorPosition(x, y);
                                    Console.Write("X");
                                    flagJump = true;
                                    jumpX = x - 2;
                                    jumpY = y - 2;
                                }
                            }
                            //Down Jump
                            if (sely + 2 < 8 && !flagJump && board[x - 2, y - 2] == '.' && (board[selx, sely + 1] == 'O' || board[selx, sely + 1] == 'X') ||
                                sely + 2 < 8 && flagJump && selx == jumpX && sely == jumpY && board[x - 2, y - 2] == '.' && (board[selx, sely + 1] == 'O' || board[selx, sely + 1] == 'X'))
                            {
                                if (selx == x - 2 && sely + 2 == y - 2)
                                {
                                    board[x - 2, y - 2] = 'X';
                                    board[selx, sely] = '.';
                                    Console.SetCursorPosition(selx + 2, sely + 2);
                                    Console.Write(".");
                                    Console.SetCursorPosition(x, y);
                                    Console.Write("X");
                                    flagJump = true;
                                    jumpX = x - 2;
                                    jumpY = y - 2;
                                }
                            }
                            //Right Jump
                            if (selx + 2 < 8 && !flagJump && board[x - 2, y - 2] == '.' && (board[selx + 1, sely] == 'O' || board[selx + 1, sely] == 'X') ||
                                selx + 2 < 8 && flagJump && selx == jumpX && sely == jumpY && board[x - 2, y - 2] == '.' && (board[selx + 1, sely] == 'O' || board[selx + 1, sely] == 'X'))
                            {
                                if (sely == y - 2 && selx + 2 == x - 2)
                                {
                                    board[x - 2, y - 2] = 'X';
                                    board[selx, sely] = '.';
                                    Console.SetCursorPosition(selx + 2, sely + 2);
                                    Console.Write(".");
                                    Console.SetCursorPosition(x, y);
                                    Console.Write("X");
                                    flagJump = true;
                                    jumpX = x - 2;
                                    jumpY = y - 2;
                                }
                            }
                            break;
                        case ConsoleKey.C:
                            flag = true;
                            break;
                    }
                    if (flag)
                        break;

                }
            }
        }
        static void WinChecker()
        {
            if (board[0, 0] == 'X' && board[0, 1] == 'X' && board[0, 2] == 'X'
             && board[1, 0] == 'X' && board[1, 1] == 'X' && board[1, 2] == 'X'
             && board[2, 0] == 'X' && board[2, 1] == 'X' && board[2, 2] == 'X')
            {
                Console.SetCursorPosition(15, 6);
                //Console.ForegroundColor = ConsoleColor.Red;
                Console.Write("CONGRATULATIONS!! YOU WON!");
                Console.SetCursorPosition(15, 9);
                Console.Write("Press Enter to Exit");
               // Console.ForegroundColor = ConsoleColor.White;
                Console.ReadLine();
                Environment.Exit(1);

            }
            if (board[5, 5] == 'O' && board[5, 6] == 'O' && board[5, 7] == 'O'
              && board[6, 5] == 'O' && board[6, 6] == 'O' && board[6, 7] == 'O'
              && board[7, 5] == 'O' && board[7, 6] == 'O' && board[6, 7] == 'O')
             {
                 Console.SetCursorPosition(15, 6);
                 //Console.ForegroundColor = ConsoleColor.Red;
                 Console.Write("COMPUTER WON!!");
                Console.SetCursorPosition(15, 9);
                 Console.Write("Press Enter to Exit");
               // Console.ForegroundColor = ConsoleColor.White;
                Console.ReadLine();
                 Environment.Exit(1);
             }
        }
        static void AIControl(int tempX, int tempY, bool newRound, bool newMove = true, bool step = false)
        {
            if (newRound)
                yIndex = 8;


            //Checkers
            //Down Jump check
            if (tempY <= 5 && tempY >= 3)
            {
                for (int i = 0; i < 8; i++)
                {
                    if (board[i, tempY + 2] == 'O')
                        counter++;
                }
                if (counter >= 3)
                {
                    DJumpFlag = false;
                }
                counter = 0;
            }
            if (tempX <= 5 && tempX >= 3)
            {
                for (int i = 0; i < 8; i++)
                {
                    if (board[tempX + 2, i] == 'O')
                        counter++;
                }
                if (counter >= 3)
                {
                    RJumpFlag = false;
                }
                counter = 0;
            }
            if (tempX <= 6 && tempX >= 4)
            {
                for (int i = 0; i < 8; i++)
                {
                    if (board[tempX + 1, i] == 'O')
                        counter++;
                }
                if (counter >= 3)
                {
                    RStepFlag = false;
                }
                counter = 0;
            }
            if (tempY <= 6 && tempY >= 4)
            {
                for (int i = 0; i < 8; i++)
                {
                    if (board[i, tempY + 1] == 'O')
                        counter++;
                }
                if (counter >= 3)
                {
                    DStepFlag = false;
                }
                counter = 0;
            }

            //Down Jump
            if (tempY < 6 && (board[tempX, tempY + 1] == 'X' || board[tempX, tempY + 1] == 'O') && board[tempX, tempY + 2] == '.' && step == false && DJumpFlag)
            {

                PositionOfOs[2, yIndex] += 2;
                DJumpFlag = true;
                RJumpFlag = true;
                DStepFlag = true;
                RStepFlag = true;
                AIControl(tempX, tempY + 2, false, false);

            }
            //Right Jump
            else if (tempX < 6 && (board[tempX + 1, tempY] == 'X' || board[tempX + 1, tempY] == 'O') && board[tempX + 2, tempY] == '.' && step == false && RJumpFlag)
            {
                PositionOfOs[2, yIndex] += 2;
                DJumpFlag = true;
                RJumpFlag = true;
                DStepFlag = true;
                RStepFlag = true;
                AIControl(tempX + 2, tempY, false, false);
            }
            //Down Step
            else if (tempY < 7 && board[tempX, tempY + 1] == '.' && newMove == true && DStepFlag)
            {
                PositionOfOs[2, yIndex] += 1;
                DJumpFlag = true;
                RJumpFlag = true;
                DStepFlag = true;
                RStepFlag = true;
                AIControl(tempX, tempY + 1, false, false, true);
            }
            //Right Step
            else if (tempX < 7 && board[tempX + 1, tempY] == '.' && newMove == true && RStepFlag)
            {
                PositionOfOs[2, yIndex] += 1;
                DJumpFlag = true;
                RJumpFlag = true;
                DStepFlag = true;
                RStepFlag = true;
                AIControl(tempX + 1, tempY, false, false, true);
            }

            else
            {
                TempMovesOfOs[0, yIndex] = tempX;
                TempMovesOfOs[1, yIndex] = tempY;

                DJumpFlag = true;
                RJumpFlag = true;
                DStepFlag = true;
                RStepFlag = true;

                if (yIndex == 0)
                {
                    maxMoveIndex = 8;
                    for (int i = 8; i >= 0; i--)
                    {
                        if (PositionOfOs[2, maxMoveIndex] < PositionOfOs[2, i])
                        {
                            maxMoveIndex = i;
                        }
                    }
                    for (int i = 0; preMoveX == TempMovesOfOs[0, maxMoveIndex] && preMoveY == TempMovesOfOs[1, maxMoveIndex] && i < 9 && PositionOfOs[2, maxMoveIndex] == 0; i++)
                    {
                        maxMoveIndex = rnd.Next(0, 9);
                    }

                    if (PositionOfOs[2, maxMoveIndex] == 0)
                        ReverseControl(PositionOfOs[0, 8], PositionOfOs[1, 8], true);

                    else
                    {

                        preMoveX = TempMovesOfOs[0, maxMoveIndex];
                        preMoveY = TempMovesOfOs[1, maxMoveIndex];
                        Console.SetCursorPosition(PositionOfOs[0, maxMoveIndex] + 2, PositionOfOs[1, maxMoveIndex] + 2);
                        Console.Write('.');
                        board[PositionOfOs[0, maxMoveIndex], PositionOfOs[1, maxMoveIndex]] = '.';
                        PositionOfOs[0, maxMoveIndex] = TempMovesOfOs[0, maxMoveIndex];
                        PositionOfOs[1, maxMoveIndex] = TempMovesOfOs[1, maxMoveIndex];
                        Console.SetCursorPosition(PositionOfOs[0, maxMoveIndex] + 2, PositionOfOs[1, maxMoveIndex] + 2);
                        Console.Write('O');
                        board[PositionOfOs[0, maxMoveIndex], PositionOfOs[1, maxMoveIndex]] = 'O';
                        for (int i = 0; i <= 8; i++)
                            PositionOfOs[2, i] = 0;
                    }
                }
                else
                {
                    yIndex--;
                    AIControl(PositionOfOs[0, yIndex], PositionOfOs[1, yIndex], false);
                }
            }
        }
        static void ReverseControl(int tempX, int tempY, bool newRound)
        {
            if (newRound)
                yIndex = 8;
            if (!(tempX >= 5 && tempY >= 5))
            {
                if (tempY <= 4)
                {
                    //Left Step
                    if (tempX > 0 && board[tempX - 1, tempY] == '.')
                    {
                        PositionOfOs[2, yIndex] += 1;
                        tempX -= 1;
                    }
                }

                else if (tempX <= 4)
                {
                    //Up Step
                    if (tempY > 0 && board[tempX, tempY - 1] == '.')
                    {
                        PositionOfOs[2, yIndex] += 1;
                        tempY -= 1;

                    }
                }

                TempMovesOfOs[0, yIndex] = tempX;
                TempMovesOfOs[1, yIndex] = tempY;

                if (yIndex == 0)
                {
                    maxMoveIndex = rnd.Next(0, 9);
                    for (int i = 0; preMoveX == TempMovesOfOs[0, maxMoveIndex] && preMoveY == TempMovesOfOs[1, maxMoveIndex] && i < 9 && PositionOfOs[2, maxMoveIndex] == 0; i++)
                    {
                        maxMoveIndex = rnd.Next(0, 9);
                    }

                    preMoveX = TempMovesOfOs[0, maxMoveIndex];
                    preMoveY = TempMovesOfOs[1, maxMoveIndex];
                    Console.SetCursorPosition(PositionOfOs[0, maxMoveIndex] + 2, PositionOfOs[1, maxMoveIndex] + 2);
                    Console.Write('.');
                    board[PositionOfOs[0, maxMoveIndex], PositionOfOs[1, maxMoveIndex]] = '.';
                    PositionOfOs[0, maxMoveIndex] = TempMovesOfOs[0, maxMoveIndex];
                    PositionOfOs[1, maxMoveIndex] = TempMovesOfOs[1, maxMoveIndex];
                    Console.SetCursorPosition(PositionOfOs[0, maxMoveIndex] + 2, PositionOfOs[1, maxMoveIndex] + 2);
                    Console.Write('O');
                    board[PositionOfOs[0, maxMoveIndex], PositionOfOs[1, maxMoveIndex]] = 'O';
                    for (int i = 0; i <= 8; i++)
                        PositionOfOs[2, i] = 0;

                }
                else
                {
                    yIndex--;
                    ReverseControl(PositionOfOs[0, yIndex], PositionOfOs[1, yIndex], false);
                }

            }
            else
            {
                if (yIndex == 0)
                {
                    maxMoveIndex = 8;
                    for (int i = 8; i >= 0; i--)
                    {
                        if (PositionOfOs[2, maxMoveIndex] < PositionOfOs[2, i])
                        {
                            maxMoveIndex = i;
                        }
                    }

                    Console.SetCursorPosition(PositionOfOs[0, maxMoveIndex] + 2, PositionOfOs[1, maxMoveIndex] + 2);
                    Console.Write('.');
                    board[PositionOfOs[0, maxMoveIndex], PositionOfOs[1, maxMoveIndex]] = '.';
                    PositionOfOs[0, maxMoveIndex] = TempMovesOfOs[0, maxMoveIndex];
                    PositionOfOs[1, maxMoveIndex] = TempMovesOfOs[1, maxMoveIndex];
                    Console.SetCursorPosition(PositionOfOs[0, maxMoveIndex] + 2, PositionOfOs[1, maxMoveIndex] + 2);
                    Console.Write('O');
                    board[PositionOfOs[0, maxMoveIndex], PositionOfOs[1, maxMoveIndex]] = 'O';
                    for (int i = 0; i <= 8; i++)
                        PositionOfOs[2, i] = 0;
                }
                else
                {
                    yIndex--;
                    ReverseControl(PositionOfOs[0, yIndex], PositionOfOs[1, yIndex], false);
                }
            }


        }
    }
}
