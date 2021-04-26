package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

public class Main {

    public static void main(String[] args) {
        //Application.start();
        PropertyReaderUtil.loadProperties();
        NassaContext context = NassaContext.getInstance();
        try {
            context.init();
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
    }
}