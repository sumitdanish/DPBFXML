/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dpbfxml;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author SUMMITM
 */
public class AppendingObjectOutputStream extends ObjectOutputStream
{
    public AppendingObjectOutputStream(OutputStream out) throws IOException
    {
        super(out);
    }
     @Override
    protected void writeStreamHeader() throws IOException {
      reset();
    }
}
