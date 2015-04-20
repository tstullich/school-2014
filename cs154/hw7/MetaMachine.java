package meta;

import java.lang.reflect.*;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class MetaMachine {
    public void execute(String ... args) throws Exception {
        try {
            Class c = Class.forName(args[0]);
            Method meth = c.getMethod(args[1], String[].class);
            Object object = c.newInstance();
            String[] arguments = Arrays.copyOfRange(args, 2, args.length);
            meth.invoke(object, (Object) arguments);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
