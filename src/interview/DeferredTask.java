package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

/**
 *
 // Implement class Deferred

 Deferred d = new Deferred();
 d.then(res->{
 System.out.println("1 "+res);
 Deferred d1 = new Deferred();
 new Timer().schedule(new TimerTask() {
@Override
public void run() { d1.resolve("a"); }
}, 1500);
 return d1;
 });
 d.then(res->{System.out.println("2 "+res); return "b";});
 d.then(res->{System.out.println("3 "+res); return "c";});
 // at this point, nothing is printed to console (yet)
 d.resolve("hello");

 // output of usage example
 1 hello
 // 1.5s later.. the rest should be printed
 2 a
 3 b
 // =============
 */
public class DeferredTask {

    static class Deferred<R> {
        private List<Function<String, R>> functions = new ArrayList<>();
        //private boolean isResolved = false;
        private String result;

        public void then(Function<String, R> f) {
            functions.add(f);
        }

        public String resolve(String s) {
            result = s;
            for (int i = 0; i < functions.size(); i++) {
                Function<String, R> f = functions.get(i);
                R o = f.apply(result);
                if (o instanceof String) {
                    result = (String) o;
                } else if (o instanceof Deferred) {
                    Deferred deferred = (Deferred) o;
                    //what if already resolved
                    //deferred.getFunctions().addAll(functions.subList(i + 1, functions.size()));
                    functions.subList(i + 1, functions.size())
                            .forEach(fun -> deferred.then(fun));
                    break;
                }
            }
            functions.clear();
            //isResolved = true;
            return result;
        }

        private String getResult() {
            return result;
        }
    }

    public static void main(String[] args) {
        Deferred d = new Deferred();
        d.then(res -> {
            System.out.println("1 " + res);
            Deferred d1 = new Deferred();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    d1.resolve("a");
                }
            }, 1500);
            return d1;
        });
        d.then(res -> {
            System.out.println("2 " + res);
            return "b";
        });
        d.then(res -> {
            System.out.println("3 " + res);
            return "c";
        });
        d.resolve("hello");

    }
}
