package telnet;

import weather.Regex;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.output.TeeOutputStream;

public final class IOUtil {

    private static final Logger log = Logger.getLogger(IOUtil.class.getName());

    private static void readFromConsole(final OutputStream outputStream) {
        Thread read = new Thread() {

            @Override
            public void run() {
                int ch;

                try {
                    while ((ch = System.in.read()) != -1) {
                        outputStream.write(ch);
                        outputStream.flush();
                    }
                } catch (IOException ioe) {
                    log.warning(ioe.toString());
                }
            }
        };
        read.start();
    }

    private static void readInput(final InputStream inputStream) throws FileNotFoundException, IOException {
        Thread readInput = new Thread() {

            @Override
            public void run() {
                char ch = 0;
                int intVal = 0;
                StringBuilder sb = new StringBuilder();
                Regex rx = new Regex();

                try {
                    while ((intVal = inputStream.read()) != -1) {
                        ch = (char) intVal;
                        printToConsole(ch);
                        //logToFile(ch);
                        sb.append(ch);
                        if (intVal == 13) {
                            rx.parse(sb.toString());
                            sb = new StringBuilder();
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, null, ex);
                }


            }

            private void logToFile(char c) throws IOException {
                String fname = "weather.log";
                File f = new File(fname);
                f.createNewFile();
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fname, true)));
                out.print(c);
                out.flush();
                out.close();
            }

            private void printToConsole(char c) {
                System.out.print(c);
            }
        };
        readInput.start();
    }

    public static void readWriteLog(final InputStream inputStream, final OutputStream outputStream) throws FileNotFoundException, IOException {
        readFromConsole(outputStream);
        readInput(inputStream);
    }

    //                TeeOutputStream tee = new TeeOutputStream(inputStream, bis);   
    private static void tee(FileOutputStream fos) {
        TeeOutputStream tee = new TeeOutputStream(System.out, fos);

    }
}
