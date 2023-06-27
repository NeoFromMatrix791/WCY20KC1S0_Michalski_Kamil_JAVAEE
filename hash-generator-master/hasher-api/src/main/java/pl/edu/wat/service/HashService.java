package pl.edu.wat.service;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javax.script.ScriptException;

import pl.edu.wat.model.HashResult;

public interface HashService {

    HashResult generateHash(final String input) throws ScriptException, NoSuchMethodException, URISyntaxException, FileNotFoundException;
}
