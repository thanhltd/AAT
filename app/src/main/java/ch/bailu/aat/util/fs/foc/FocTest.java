package ch.bailu.aat.util.fs.foc;

import java.io.Closeable;

import ch.bailu.aat.description.DateDescription;
import ch.bailu.aat.util.ui.AppLog;
import ch.bailu.util_java.foc.Foc;

public class FocTest {

    private final Foc file;

    public FocTest(Foc f) {
        file = f;
    }



    public void rlog() {
        log();
        file.foreach(new Foc.Execute() {
            @Override
            public void execute(Foc child) {
                new FocTest(child).rlog();
            }
        });
    }

    public void log() {
        AppLog.d(this, "======= FocTest.log(" + file.getName() + ")");
        AppLog.d(this, file.getPathName());
        AppLog.d(this, file.getPath());

        StringBuilder b = new StringBuilder();

        b.append("s:");
        b.append(file.length());

        b.append(" m:");
        b.append(DateDescription.format(file.lastModified()));
        b.append(" [");

        if (file.isDir())
            b.append("D");

        if (file.isFile())
            b.append("F");

        if (file.exists())
            b.append("e");

        if (file.canWrite()) {
            b.append("w");
        }

        if (file.canRead()) {
            if (read()) b.append("R");
            else b.append("r");
        }

        b.append("]");
        AppLog.d(this, b.toString());
    }


    public boolean read() {
        Closeable c=null;
        try {
            c = file.openR();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            Foc.close(c);
        }
    }
}
