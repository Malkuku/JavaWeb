package com.itheima;

import com.example.HeaderParser;
import com.example.MyImportSelector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(MyImportSelector.class)
@SpringBootTest
public class SpringbootWebTests {
    @Autowired
    private HeaderParser headerParser;

    @Test
    void testHeaders() {
        headerParser.parse();
    }
}
