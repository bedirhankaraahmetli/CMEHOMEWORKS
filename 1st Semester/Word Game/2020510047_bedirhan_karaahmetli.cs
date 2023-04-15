using System;
using System.Linq;

namespace Homework03
{
    class MainClass
    {
        static string option = "y";
        public static void Main(string[] args)
        {
            while (option == "y")
            {
                char[] textLetters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ',', ' ' };
                char[] patternLetters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '-', '*' };
                string[] words;
                string[] tempWords;
                string pattern;
                Console.WriteLine("-----------------------------------------------------------------------------------------------");
                Console.WriteLine("The text can contain only English alphabet letters and two punctuations dot (.) and comma (,)!!");
                Console.WriteLine("Please enter the text that you want to search :");
                string tempText = "Miss Polly had a poor dolly, who was sick. She called for the talled doctor Solly to come quick.He knocked on the DOOR like a actor in the healthcare sector.";
                string text = tempText;
                text = text.Replace('.', ' '); //removing dot's and comma's
                text = text.Replace(',', ' ');
                tempWords = text.Split(' '); //spliting words to array
                tempText = tempText.ToLower();
                tempText = tempText.Replace('.', ' ');
                tempText = tempText.Replace(',', ' ');
                words = tempText.Split(' ');
                bool isTextInvalid = false;
                for (int i = 0; i < tempText.Length && !isTextInvalid; i++) //checking text
                {
                    for (int j = 0; j < textLetters.Length; j++)
                    {
                        if (tempText[i] == textLetters[j])
                        {
                            isTextInvalid = false;
                            break;
                        }
                        else
                        {
                            isTextInvalid = true;
                        }
                    }
                    if (isTextInvalid)
                    {
                        Console.WriteLine();
                        Console.WriteLine("Invalid text!");
                        Console.WriteLine("The text can contain only English alphabet letters and two punctuations dot (.) and comma (,)!!");
                        Console.WriteLine();                      
                    }
                }
                if (isTextInvalid)
                {
                    break;
                }
                for (int i = 0; i < words.Length; i++)
                {
                    if (words[i] == " " || words[i] == "  ")
                    {
                        words[i] = null;
                    }
                }
                for (int i = 0; i < tempWords.Length; i++)
                {
                    if (tempWords[i] == " " || tempWords[i] == "  ")
                    {
                        tempWords[i] = null;
                    }
                }
                Console.WriteLine();
                Console.WriteLine("The pattern can contain letters, as well as either the character(s) of “*” or the character(s) of “-”, but not both of them.");
                Console.WriteLine("Please enter the word or pattern you want to search in the text :");
                pattern = Console.ReadLine().ToLower();
                
                Console.WriteLine();
                bool isPatternInvalid = false;
                for (int i = 0; i < pattern.Length && !isPatternInvalid; i++) //Checking pattern
                {
                    for (int j = 0; j < patternLetters.Length; j++)
                    {
                        if (pattern[i] == patternLetters[j])
                        {
                            isPatternInvalid = false;
                            break;
                        }
                        else
                        {
                            isPatternInvalid = true;
                        }
                    }
                    if (isPatternInvalid)
                    {
                        Console.WriteLine();
                        Console.WriteLine("Invalid pattern or words for search");
                        Console.WriteLine("The pattern can contain letters, as well as either the character(s) of “*” or the character(s) of “-”, but not both of them.");
                        Console.WriteLine();
                    }
                }
                if (isPatternInvalid)
                {
                    break;
                }
                if (!pattern.Contains('-') && !pattern.Contains('*')) //if user search only word
                {
                    for (int i = 0; i < words.Length; i++)
                    {
                        if (words[i] == pattern)
                        {
                            Console.WriteLine();
                            Console.WriteLine("This word found in the text: {0}",words[i]);
                            break;
                        }
                        else
                        {
                            Console.WriteLine();
                            Console.WriteLine("");
                        }
                    }
                }
                else if (pattern.Contains('-') && !pattern.Contains('*'))//if user search word with '-'
                {
                    for (int i = 0; i < words.Length; i++)
                    {
                        if (pattern.Length == words[i].Length)
                        {
                            for (int k = 0; k < pattern.Length; k++)
                            {
                                if (pattern[k].Equals('-') && k != pattern.Length - 1)
                                {
                                    continue;
                                }
                                else if (pattern[k] != words[i][k] && k != pattern.Length - 1)
                                {
                                    break;
                                }
                                else if (k == pattern.Length - 1)
                                {
                                    Console.WriteLine(tempWords[i]);
                                }
                            }
                        }
                    }
                }
                else if (pattern.Contains('*') && !pattern.Contains('-')) //if user search word with '*'
                {
                    int index = pattern.IndexOf('*');
                    for (int i = 0; i < words.Length; i++)
                    {
                        if (index == 0 && words[i].Contains(pattern.Substring(1)) &&
                            words[i][words[i].Length-1] == pattern[pattern.Length-1])
                        {
                            Console.WriteLine(tempWords[i]);
                        }
                        else if (index == pattern.Length-1 && index != 0 &&
                            words[i].Contains(pattern.Substring(0,pattern.Length-2)) && words[i][0] == pattern[0])
                        {
                            Console.WriteLine(tempWords[i]);
                        }
                        else if (index == 0 && pattern.Length == 1)
                        {
                            Console.WriteLine(tempWords[i]);
                        }
                        else if (words[i].Contains(pattern.Substring(0,index)) && words[i].Contains(pattern.Substring(index+1)) &&
                            words[i][words[i].Length-1] == pattern[pattern.Length-1])
                        {
                            Console.WriteLine(tempWords[i]);
                        }
                    }
                }
                else
                {
                    Console.WriteLine("Wrong pattern");
                }
                Console.WriteLine();
                Console.WriteLine("Would you like the enter the text again? (Enter 'y' for Yes, 'n' for No.)");
                option = Console.ReadLine();
                Console.Clear();

            }
            
        }
    }
}
