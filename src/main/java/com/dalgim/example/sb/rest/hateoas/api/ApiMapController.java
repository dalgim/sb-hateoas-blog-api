package com.dalgim.example.sb.rest.hateoas.api;

import com.dalgim.example.sb.rest.hateoas.api.assembler.ApiMapAssembler;
import com.dalgim.example.sb.rest.hateoas.api.resource.ApiMapResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ApiMapController {

    private final ApiMapAssembler apiMapAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ApiMapResource> apiMap() {
        return ResponseEntity.ok(apiMapAssembler.createApiMap());
    }
}
