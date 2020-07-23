import org.checkerframework.checker.objectconstruction.qual.*;
import org.checkerframework.common.returnsreceiver.qual.*;
import java.util.function.Function;
import java.io.IOException;

class ACRegularExitPointTest {

    @AlwaysCall("a")
    class Foo {
        void a() {}
        @This Foo b() {
            return this;
        }
        void c(@CalledMethods({"a"}) Foo this) {}

    }


    Foo makeFoo(){
        return new Foo();
    }

    void testStoringInLocalWrong() {
        // :: error: missing.alwayscall
        Foo foo = makeFoo();
    }


}