package pl.edu.wat.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import pl.edu.wat.model.HashResult;

@Local
@Stateless
@Singleton
public class HashServiceImpl implements HashService {

    private static final URL JS_CODE = HashServiceImpl.class.getClassLoader().getResource("scripts/md6.js");

    @Override
    public HashResult generateHash(final String input) throws ScriptException, NoSuchMethodException, URISyntaxException, FileNotFoundException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader(new File(JS_CODE.toURI())));

        Invocable invocable = (Invocable) engine;
        Map<String, String> result = (Map<String, String>) invocable.invokeFunction("_bundle", input);
        return new HashResult(result.get("hash128"), result.get("hash256"), result.get("hash512"));
    }
}
