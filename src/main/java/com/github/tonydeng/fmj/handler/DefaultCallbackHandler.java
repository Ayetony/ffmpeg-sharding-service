package com.github.tonydeng.fmj.handler;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by tonydeng on 15/4/20.
 */
@Slf4j
public class DefaultCallbackHandler implements ProcessCallbackHandler {

    @Override
    public String handler(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        List<String> buffer = Lists.newArrayList();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.add(line);
            System.out.println(line);
        }
        return String.join("\n", buffer);
    }
}
