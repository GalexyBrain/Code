package CN.Prg7;

import java.util.*;
import java.net.*;
import java.io.*;

class myclient
{
    public static void main(String[] args)throws Exception
    {
        Socket cs=new Socket("localhost",8000);
        DataInputStream din=new DataInputStream(cs.getInputStream());
        DataOutputStream dout=new DataOutputStream(cs.getOutputStream());
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a filename:");
        String fname=in.next();
        System.out.println("file name sent"+fname);
        dout.writeUTF(fname);
        String msg;
        try
        {
            do
            {
                msg=din.readUTF();
                System.out.println(msg);
            }while(!msg.equals("stop"));
        }
        catch(Exception e)
        {
            System.out.println("file doesnt exsist");
        }
        finally
        {
            din.close();
            cs.close();
        }
    }
}