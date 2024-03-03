package com.example.demo.controller;

import com.example.demo.contants.Config;
import com.example.demo.payload.response.ServiceResult;
import com.example.demo.service.VnPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/vnPay")
@RequiredArgsConstructor
public class VnPayController {

    private final VnPayService vnPayService;

    @GetMapping("/payment")
    public ResponseEntity<?> payWithVnpay(
            @RequestParam("amount") Long amount
    ){
        return ResponseEntity.ok().body(this.vnPayService.payWithVnpay(amount));
    }

}
