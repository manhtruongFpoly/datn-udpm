package com.example.demo.payload.statistical;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusStatisticDto {
    private Integer soDonChoXacNhan;
    private Integer soDonDangXuLy;
    private Integer soDonDangVanChuyen;
    private Integer soDonDaGiao;
    private Integer soDonDaHuy;
    private Integer soDonDaHoanThanh;
}
