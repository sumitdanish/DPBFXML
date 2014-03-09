/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author SUMMITM
 */
public class AppendingObjectInputStream extends ObjectInputStream
{
    public AppendingObjectInputStream(InputStream inp)throws IOException
    {
        super(inp);
    }
    
    @Override
    protected void readStreamHeader() throws IOException
    {
        reset();
    }
}
