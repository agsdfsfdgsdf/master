package com.deyi.daxie.cloud.operation.domain.dto;

import lombok.Data;

@Data
public class CountType {
    private int one;
    private int two;


    public int getOne() {
        return one;
    }

    public int setOne(int one) {
        this.one = one;
        return one;
    }

    public int getTwo() {
        return two;
    }

    public int setTwo(int two) {
        this.two = two;
        return two;
    }
}
