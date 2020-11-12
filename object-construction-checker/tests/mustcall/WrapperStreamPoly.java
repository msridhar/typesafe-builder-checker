// A simple test that must-call as a type annotation makes it so that so-called
// "polymorphic" streams like DataInputStream and DataOutputStream are treated as
// their constituent stream.

import java.io.*;
import java.net.Socket;
import org.checkerframework.checker.objectconstruction.qual.Owning;
import org.checkerframework.checker.objectconstruction.qual.NotOwning;


class WrapperStreamPoly {
    void test_no_close_needed(@Owning ByteArrayInputStream b) {
        // b doesn't need to be closed, so neither does this stream.
        DataInputStream d = new DataInputStream(b);
    }

    void test_close_needed(@Owning InputStream b) {
        // :: error: required.method.not.called
        DataInputStream d = new DataInputStream(b);
    }

    void test_notowning_param(InputStream b) {
        // no error since b is not @Owning
        DataInputStream fromNotOwningParam = new DataInputStream(b);
        // no error since fromNotOwningParam is not relevant
        DataInputStream fromLocal = new DataInputStream(fromNotOwningParam);
    }

    InputStream f;

    void test_field() {
        // no error since f is not owning
        DataInputStream fromField = new DataInputStream(f);
    }

    @NotOwning InputStream getF() {
        return f;
    }

    void test_notowning_getter() {
        // no error since getF() returns @NotOwning
        DataInputStream fromGetter = new DataInputStream(getF());
    }

    void test_wrapped_twice(@Owning Socket sock) throws IOException {
        try {
            DataOutputStream dout123 = null;
            BufferedOutputStream buf123 = new BufferedOutputStream(sock.getOutputStream());
            dout123 = new DataOutputStream(buf123);
        } finally {
            sock.close();
        }
    }

}
