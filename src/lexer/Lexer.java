// Bryce Doyle
// Jaden Hebert

package lexer;
import java.io.*;
import java.util.TreeMap;

public class Lexer {  
    
    static TreeMap <String, String> map = new TreeMap<>();
    static String lexeme = "";
    
    public static void Tokanize(String fileName) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int cr = 0;
        lookupTable();
        boolean isDigit = false;
        
        while (cr != -1)
        {
            cr = br.read();
            Character c = (char) cr;
            
            if (Character.isLetter(c) && map.get(c.toString()) == null)
            {
                if(isDigit)
                {
                    System.out.println("SYNTAX ERROR: INVALID IDENTIFIER NAME");
                    break;
                }
                lexeme += c;
            }
            
            else if (Character.isDigit(c))
            {
                if(lexeme.isEmpty())
                    isDigit = true;
                lexeme += c;
            }
            
            else if (Character.isWhitespace(c))
            {
                if(isDigit)
                {
                    System.out.println("INT_LIT:" + lexeme);
                    
                    if(!(c.toString().isEmpty()))
                        System.out.println(map.get(c.toString()));    
                }
                
                if (map.get(lexeme) == null && (!(lexeme.isEmpty())))
                {
                    System.out.println("IDENT:" + lexeme);
                    lexeme = "";
                }
                
                else if (lexeme.isEmpty())
                    lexeme = "";
                
                else
                {
                    System.out.println(map.get(lexeme));
                    lexeme = "";
                }
            }
            
            else if (map.get(lexeme) != null)
            {
                System.out.println(map.get(lexeme));
                lexeme = "";
                
                if (map.get(c.toString()) != null)
                    System.out.println(map.get(c.toString()));  
            }
            
            else if (lexeme.isEmpty())
            {
                if(!(c.toString().isEmpty()))
                    duo(c, cr, br);
            }
            else
            {
                if(isDigit)
                {
                    System.out.println("INT_LIT:" + lexeme);
                    
                    if(!(c.toString().isEmpty()))
                        System.out.println(map.get(c.toString()));
                    
                    lexeme = "";
                    isDigit = false;
                }
                
                else
                {
                    System.out.println("IDENT:" + lexeme);
                    lexeme = "";
                    duo(c, cr, br);
                }
            }
        }
    }

    
    public static void duo(Character c, int cr, BufferedReader br) throws IOException
    {
        Character b = ' ';
        
        if(c == '+')
        {
            lexeme += c;
            cr = br.read();
            b = (char)cr;

            if (b == '+')
            {
                lexeme += b;
                System.out.println(map.get(lexeme));
                lexeme = "";
            }
            
            else
            {
                System.out.println(map.get(c.toString()));
                lexeme = "";
            }
            
            if (Character.isLetter(b))
                    lexeme += b;
            
        }
        
        else if(c == '>')
        {
            lexeme += c;
            cr = br.read();
            b = (char)cr;
            
            if(b == '=')
            {
                lexeme += b;
                System.out.println(map.get(lexeme));
                lexeme = "";
            }
            
            else
            {
                System.out.println(map.get(c.toString()));
                lexeme = "";
            }
            
            if (Character.isLetter(b))
                    lexeme += b;
                
        }
        
        else if(c == '<')
        {
            lexeme += c;
            cr = br.read();
            b = (char)cr;
            
            if(b == '=')
            {
                lexeme += b;
                System.out.println(map.get(lexeme));
                lexeme = "";
            }
            
            else
            {
                System.out.println(map.get(c.toString()));
                lexeme = "";
            }
            
            if (Character.isLetter(b))
                    lexeme += b;
        }
        
        else if(c == '=')
        {
            lexeme += c;
            cr = br.read();
            b = (char)cr;
            
            if(b == '=')
            {
                lexeme += b;
                System.out.println(map.get(lexeme));
                lexeme = "";
            }
            
            else
            {
                System.out.println(map.get(c.toString()));
                lexeme = "";
            }
            
            if (Character.isLetter(b))
                    lexeme += b;
        }
        
        else
            System.out.println(map.get(c.toString()));
    }
    
    
    private static void lookupTable()
    {
        map.put("=", "ASSIGN");
        map.put("+", "ADD");
        map.put("-", "SUB");
        map.put("*", "MUL");
        map.put("/", "DIV");
        map.put("%", "MOD");
        map.put(">", "GT");
        map.put("<", "LT");
        map.put(">=", "GE");
        map.put("<=", "LE");
        map.put("++", "INC");
        map.put("(", "LP");
        map.put(")", "RP");
        map.put("{", "LB");
        map.put("}", "RB");
        map.put("|", "OR");
        map.put("&", "AND");
        map.put("==", "EE");
        map.put("!", "NEG");
        map.put(",", "COMMA");
        map.put(";", "SEMI");
        map.put("if", "IF");
        map.put("for", "FOR");
        map.put("while", "WHILE");
        map.put("function", "FUNCTION");
        map.put("return", "RETURN");
        map.put("int", "INT");
        map.put("else", "ELSE");
        map.put("do", "DO");
        map.put("break", "BREAK");
        map.put("end", "END");
    }
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        Lexer.Tokanize("testcase.txt");
    }
}