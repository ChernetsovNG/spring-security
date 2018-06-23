package ru.nchernetsov.oauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nchernetsov.oauth.model.Foo;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@RestController
public class FooController {

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping(value = "/foo")
    @ResponseBody
    public Foo getRandomFoo() {
        return new Foo(Long.parseLong(randomNumeric(3)), randomAlphabetic(6));
    }
}
