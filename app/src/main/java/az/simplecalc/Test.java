import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class Test {
  public static void main(String[] args) throws Exception{
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    String foo = "40000+2123*1012312%5123/222222+3222-111*4335";
    System.out.println(engine.eval(foo));
    } 
}
