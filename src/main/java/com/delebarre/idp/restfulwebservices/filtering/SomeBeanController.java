package com.delebarre.idp.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
public class SomeBeanController {
    @GetMapping("/filtering")
    public MappingJacksonValue getSomeBean() {
        SomeBean someBean = new SomeBean("value1","value2","value3");
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);

        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue getListOfBeans() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));

        MappingJacksonValue mapping = new MappingJacksonValue(someBeans);

        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mapping.setFilters(filters);

        return mapping;
    }
}
