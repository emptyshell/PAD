package common;

import java.io.IOException;

public interface IIO {
    public void write(String s) throws IOException ;
    public String read() throws IOException ;
}