package com.jackshenorion.smarts.util.mapper;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SampleClient {


    public static void main(String[] args) {

        List<SampleMessage> msgs = Arrays.asList(
                new SampleMessage().setName("Jack Shen").setAddress("1 Princess Street").setCity("Sydney"),
                new SampleMessage().setName("Jack Shen").setAddress("1 Princess Street").setCity("Sydney"),
                new SampleMessage().setName("Jack Shen").setAddress("1 Princess Street").setCity("Sydney")
        );

        System.out.println("Before translation:\n" + msgs);

        Map<String, String> nameMapping = Maps.newHashMap();
        nameMapping.put("Jack Shen", "Jian Shen");
        Translator nameTranslator = new Translator().setMapping(nameMapping);

        Map<String, String> addressMapping = Maps.newHashMap();
        addressMapping.put("1 Princess Street", "1 Princess St");
        Translator addressTranslator = new Translator().setMapping(addressMapping);


        MapBinding nameBinding = new MapBinding<SampleMessage>(msg -> msg.getName(), (msg, newValue) -> msg.setName(newValue));
        MapBinding addressBinding = new MapBinding<SampleMessage>(msg -> msg.getAddress(), (msg, newValue) -> msg.setAddress(newValue));


        msgs.forEach(msg -> {
            nameTranslator.translate(nameBinding, msg);
            addressTranslator.translate(addressBinding, msg);
        });

        System.out.println("After translation:\n" + msgs);
    }
}
