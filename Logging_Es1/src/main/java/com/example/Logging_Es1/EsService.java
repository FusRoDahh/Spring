package com.example.Logging_Es1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EsService {
    Logger logger = LoggerFactory.getLogger(EsService.class);
    @Autowired
    private Environment environment;
    @Value("${var1}")
    private int var1;
    @Value("${var2}")
    private int var2;

    public int exp(){
        int potenza = (int) Math.pow(var1,var2);
        logger.info("Risultato Potenza: " + potenza);
        return potenza;
    }
}
