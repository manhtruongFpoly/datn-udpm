package com.example.demo.controller;


import com.example.demo.model.dto.ProductDto;
import com.example.demo.payload.response.SampleResponse;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/orderDetail")
@AllArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final OrderService orderService;



    @PostMapping("/create-order-detail")
    public ResponseEntity<?> createOrderDetail(
            @RequestBody ProductDto productDto
    ) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Cập nhập thành công")
                .data(orderDetailService.addOrderDetail(productDto))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
