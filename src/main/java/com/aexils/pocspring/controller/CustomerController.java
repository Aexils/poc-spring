package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.CartDTO;
import com.aexils.pocspring.dto.CustomerDTO;
import com.aexils.pocspring.entity.Cart;
import com.aexils.pocspring.entity.User;
import com.aexils.pocspring.mapper.CustomerMapper;
import com.aexils.pocspring.service.CustomerService;
import com.aexils.pocspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final UserService userService;
    private final CustomerService customerService;

    @PutMapping()
    public ResponseEntity<Void> updateCustomer(@AuthenticationPrincipal Jwt principal, @RequestBody CustomerDTO dto) {
        User user = userService.findById(principal.getClaim("sub"));
        customerService.update(user, dto);
        return ResponseEntity.ok().build();
    }
}
