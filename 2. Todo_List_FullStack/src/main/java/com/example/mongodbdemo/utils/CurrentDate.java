package com.example.mongodbdemo.utils;

import java.time.*;
import java.time.format.*;

public class CurrentDate {

    public static String today() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d");
        return currentDate.format(formatter);
    }
}
